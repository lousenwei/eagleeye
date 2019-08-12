package com.eagleeye.statistics.blh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.taobao.api.security.SecurityClient;
import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.blh.IChatLogBlh;
import com.eagleeye.eservice.dao.impl.ChatPeersDAO;
import com.eagleeye.eservice.dao.impl.TradeSoldDAO;
import com.eagleeye.eservice.eo.ChatPeerEO;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.statistics.blh.IBelongTypeBlh;
import com.eagleeye.statistics.dao.BelongTypeDAO;
import com.eagleeye.statistics.eo.BelongTypeEO;
import com.eagleeye.user.dao.GroupMemberDAO;
import com.eagleeye.user.eo.GroupMemberEO;
import com.taobao.api.domain.MsgList;

public class BelongTypeBlhImpl implements IBelongTypeBlh {
    BelongTypeDAO belongTypeDao;
    TradeSoldDAO tradeSoldDao;
    ChatPeersDAO chatPeersDao;
    IChatLogBlh chatLogBlh;
    GroupMemberDAO groupMemberDao;

    private Logger log = Logger.getLogger(BelongTypeBlhImpl.class);

    @Override
    public void saveItem(BelongTypeEO belongType) {
        // TODO Auto-generated method stub
        belongTypeDao.saveOrUpdate(belongType);
    }

    @Override
    public BelongTypeEO getBelongTypeById(String managerId) {
        // TODO Auto-generated method stub
        return belongTypeDao.getBelongTypeEOByManagerId(managerId);
    }

    @Override
    @Deprecated
    public void deleteByItemId(String managerId) {
        // TODO Auto-generated method stub

    }

    public BelongTypeDAO getBelongTypeDao() {
        return belongTypeDao;
    }

    public void setBelongTypeDao(BelongTypeDAO belongTypeDao) {
        this.belongTypeDao = belongTypeDao;
    }

    @Override
    public String getBelongTypeByManagerId(String managerId) {
        // TODO Auto-generated method stub
        BelongTypeEO belongType = this.getBelongTypeById(managerId);
        if (belongType != null && belongType.getType() != null && !belongType.getType().isEmpty()) {
            return belongType.getType();
        }
        return EagleConstants.BELONG_TYPE_PAY_LAST_TALK;
    }

    @Override
    public void runTradePersonalStatFirstContactByPayDay(String managerId, String sessionKey,
                                                         List<TradeEO> needAssignTrades) throws Exception {
        this.runFirstContact(managerId, sessionKey, needAssignTrades, EagleConstants.BELONG_TYPE_PAY_FIRST_TALK);
    }

    @Override
    public void runTradePersonalStatLastContactByPayDay(String managerId, String sessionKey,
                                                        List<TradeEO> needAssignTrades) throws Exception {
        this.runLastContact(managerId, sessionKey, needAssignTrades, EagleConstants.BELONG_TYPE_PAY_LAST_TALK);
    }

    @Override
    public void runTradePersonalStatFirstContactByCreateDay(String managerId, String sessionKey,
                                                            List<TradeEO> needAssignTrades) throws Exception {
        // TODO Auto-generated method stub
        this.runFirstContact(managerId, sessionKey, needAssignTrades, EagleConstants.BELONG_TYPE_CREATE_FIRST_TALK);
    }

    @Override
    public void runTradePersonalStatLastContactByCreateDay(String managerId, String sessionKey,
                                                           List<TradeEO> needAssignTrades) throws Exception {
        // TODO Auto-generated method stub
        this.runLastContact(managerId, sessionKey, needAssignTrades, EagleConstants.BELONG_TYPE_CREATE_LAST_TALK);
    }

    @Override
    public void runTradePersonalStatMostContactByCreateDay(String managerId, String sessionKey,
                                                           List<TradeEO> needAssignTrades) throws Exception {
        // TODO Auto-generated method stub
        this.runMostContactOwner(managerId, sessionKey, needAssignTrades, EagleConstants.BELONG_TYPE_CREATE_MOST_TALK);
    }

    @Override
    public void runTradePersonalStatMostContactByPayDay(String managerId, String sessionKey,
                                                        List<TradeEO> needAssignTrades) throws Exception {
        // TODO Auto-generated method stub
        this.runMostContactOwner(managerId, sessionKey, needAssignTrades, EagleConstants.BELONG_TYPE_PAY_MOST_TALK);
    }

    @Override
    public void runTradePersonalStatFlag(String managerId, String sessionKey, List<TradeEO> needAssignTrades)
            throws Exception {
        // TODO Auto-generated method stub
        this.runFlagOwner(managerId, sessionKey, needAssignTrades, EagleConstants.BELONG_TYPE_PAY_MOST_TALK);
    }

    /**
     * 聊天局数最多的客户
     *
     * @param managerId
     * @param sessionKey
     * @param needAssignTrades
     * @param dateType         日期类型，有根据付款日期，交易创建日期两种
     * @throws Exception
     */
    public void runFlagOwner(String managerId, String sessionKey, List<TradeEO> needAssignTrades, String dateType)
            throws Exception {
        if (needAssignTrades != null && !needAssignTrades.isEmpty()) {
            List<GroupMemberEO> members = groupMemberDao.findByManagerId(managerId);
            if (members != null) {
                Map<Long, String> membersMap = new HashMap();
                for (GroupMemberEO member : members) {
                    membersMap.put(member.getFlag(), member.getId().getServiceStaffId());
                }
                List<TradeEO> ownerTrades = new ArrayList();
                for (TradeEO temp : needAssignTrades) {
                    if (temp.getSellerFlag() != null) {
                        if (membersMap.get(temp.getSellerFlag()) != null) {
                            temp.setTradeOwner(membersMap.get(temp.getSellerFlag()));
                        } else {
                            temp.setTradeOwner(EagleConstants.DEFAULT_OWNER);
                        }
                    } else {
                        temp.setTradeOwner(EagleConstants.DEFAULT_OWNER);
                    }
                    ownerTrades.add(temp);
                }
                if (!ownerTrades.isEmpty()) {
                    tradeSoldDao.saveOrUpdateBatch(ownerTrades);
                }
            }
        }
    }

    /**
     * 根据类型，选择返回付款日期or交易创建日期
     *
     * @param trade
     * @param type
     * @return
     */
    private Date chooseDateType(TradeEO trade, String type) {
        if (EagleConstants.BELONG_TYPE_CREATE_FIRST_TALK.equalsIgnoreCase(type)
                || EagleConstants.BELONG_TYPE_CREATE_LAST_TALK.equalsIgnoreCase(type)
                || EagleConstants.BELONG_TYPE_CREATE_MOST_TALK.equalsIgnoreCase(type)) {
            return trade.getCreated();
        }
        if (EagleConstants.BELONG_TYPE_PAY_FIRST_TALK.equalsIgnoreCase(type)
                || EagleConstants.BELONG_TYPE_PAY_LAST_TALK.equalsIgnoreCase(type)
                || EagleConstants.BELONG_TYPE_PAY_MOST_TALK.equalsIgnoreCase(type)) {
            return trade.getPayTimeAdd();
        }
        return null;
    }

    /**
     * 首先接触客户
     *
     * @param managerId
     * @param sessionKey
     * @param needAssignTrades
     * @param dateType         日期类型，有根据付款日期，交易创建日期两种
     * @throws Exception
     */
    private void runFirstContact(String managerId, String sessionKey, List<TradeEO> needAssignTrades, String dateType)
            throws Exception {
        if (needAssignTrades != null && !needAssignTrades.isEmpty()) {
            List<TradeEO> ownerTrades = new ArrayList();
            for (TradeEO temp : needAssignTrades) {
                // 设置初始归属为直拍
                String owner = EagleConstants.DEFAULT_OWNER;
                Date calTime = this.chooseDateType(temp, dateType);
                //
                // 如果paytime=null
                // 则直接设置为direct buyer
                Boolean directPay = false;
                if (calTime == null) {
                    directPay = true;
                }
                // 如果是当天则返回
                else if (!calTime.before(DateUtil.getSimpleDate(new Date()))) {
                    continue;
                }
                // 如果buyNick=null
                // 则直接设置为direct buyer
                String buyNick = temp.getBuyerNick();
                if (buyNick == null || buyNick.isEmpty()) {
                    directPay = true;
                } else {
                    //2017-9-7,解密buyNick
                    if (SecurityClient.isEncryptData(buyNick, "nick")) {
                        SecurityClient client = TaoBaoClientUtil.getSecurityTaobaoClient();
                        buyNick = client.decrypt(buyNick, "nick", sessionKey);
                    }
                }
                List<ChatPeerEO> chatPeers = new ArrayList();
                // 默认X小时前
                if (!directPay) {
                    Date endTime = calTime;
                    Date startTime = DateUtil.getPreviousDay(DateUtil.getSimpleDate(calTime),
                            2 * EagleConstants.DEFAULT_SET_OWNER_PERIOD);
                    // 得到聊天对象
                    chatPeers = chatPeersDao.getChatPeerEOsByBuyNickandTimePeriod(temp.getSellerNick(), buyNick,
                            DateUtil.parse(startTime, DateUtil.datePatternYY_MM_DDHHMMSS),
                            DateUtil.parse(endTime, DateUtil.datePatternYY_MM_DDHHMMSS), EagleConstants.ORDERBY_ASC);
                }
                Map<String, Date> earlyChatPeers = new HashMap();
                if (!directPay && chatPeers != null && !chatPeers.isEmpty()) {

                    for (ChatPeerEO tempChat : chatPeers) {
                        if (earlyChatPeers.get(tempChat.getId().getServiceStaffId()) == null) {
                            earlyChatPeers.put(tempChat.getId().getServiceStaffId(), tempChat.getId().getChatDate());
                        } else if (earlyChatPeers.get(tempChat.getId().getServiceStaffId()).after(
                                tempChat.getId().getChatDate())) {
                            earlyChatPeers.put(tempChat.getId().getServiceStaffId(), tempChat.getId().getChatDate());
                        }
                    }

                    // 如果接待客服等于1，则业务归属该客服
                    // if (earlyChatPeers.size() == 1) {
                    // String staffId =
                    // earlyChatPeers.get(0).getId().getServiceStaffId();
                    // if (staffId != null && !staffId.isEmpty()) {
                    // owner = staffId;
                    // } else {
                    // owner = EagleConstants.DEFAULT_OWNER;
                    // }
                    // // 如果接待客服大于1，则业务查询具体聊天时间
                    // } else
                    if (earlyChatPeers.size() >= 1) {
                        Date earlyTime = new Date();
                        Date startTime = DateUtil.getPreviousDay(calTime, 2 * EagleConstants.DEFAULT_SET_OWNER_PERIOD);
                        for (String staffId : earlyChatPeers.keySet()) {
                            if (staffId != null && earlyChatPeers.get(staffId) != null
                                    && earlyChatPeers.get(staffId).after(DateUtil.getPreviousDay(new Date(), 89))) {
                                List<MsgList> MsgLists = new ArrayList();
                                if (earlyChatPeers.get(staffId).before(startTime)) {
                                    MsgLists = chatLogBlh.getChatLogs(staffId, buyNick, startTime, calTime, sessionKey);
                                } else {
                                    MsgLists = chatLogBlh.getChatLogs(staffId, buyNick, earlyChatPeers.get(staffId),
                                            calTime, sessionKey);
                                }
                                if (MsgLists != null && !MsgLists.isEmpty()) {
                                    for (MsgList msg : MsgLists) {
                                        if (msg.getDirection() == 0) {
                                            Date chatTime = DateUtil.getDateByString(msg.getTime(),
                                                    DateUtil.datePatternYY_MM_DDHHMMSS);
                                            if (chatTime.before(earlyTime) && chatTime.before(calTime)) {
                                                earlyTime = chatTime;
                                                owner = staffId;
                                                break;
                                            }
                                        }
                                    }
                                } else if (MsgLists == null) {
                                    owner = EagleConstants.PENDING_OWNER;
                                    log.error(managerId + "chatlog response is null!");
                                }
                            } else {
                                owner = EagleConstants.DEFAULT_OWNER;
                            }
                        }
                        log.info("业务归属" + owner);
                        // 其他，如果没有客服接待，则设置为直接购买
                    }
                } else {
                    owner = EagleConstants.DEFAULT_OWNER;

                }
                // 设置最终归属
                temp.setTradeOwner(owner);
                // 增加如列表
                ownerTrades.add(temp);
            }
            if (!ownerTrades.isEmpty()) {
                for (TradeEO temp : ownerTrades)
                    tradeSoldDao.updateData(temp);
            }
        }

    }

    /**
     * 最后接触客户
     *
     * @param managerId
     * @param sessionKey
     * @param needAssignTrades
     * @param dateType         日期类型，有根据付款日期，交易创建日期两种
     * @throws Exception
     */
    private void runLastContact(String managerId, String sessionKey, List<TradeEO> needAssignTrades, String dateType)
            throws Exception {
        if (needAssignTrades != null && !needAssignTrades.isEmpty()) {
            List<TradeEO> ownerTrades = new ArrayList();
            for (TradeEO temp : needAssignTrades) {
                // 设置初始归属为直拍
                String owner = EagleConstants.DEFAULT_OWNER;
                Date calTime = this.chooseDateType(temp, dateType);
                // 如果paytime=null
                // 则直接设置为direct buyer
                Boolean directPay = false;
                if (calTime == null) {
                    directPay = true;
                } // 如果是当天则返回
                else if (!calTime.before(DateUtil.getSimpleDate(new Date()))) {
                    continue;
                }
                // 如果buyNick=null
                // 则直接设置为direct buyer
                String buyNick = temp.getBuyerNick();
                if (buyNick == null || buyNick.isEmpty()) {
                    directPay = true;
                } else {
                    //2017-9-7,解密buyNick
                    if (SecurityClient.isEncryptData(buyNick, "nick")) {
                        SecurityClient client = TaoBaoClientUtil.getSecurityTaobaoClient();
                        buyNick = client.decrypt(buyNick, "nick", sessionKey);
                    }
                }
                List<ChatPeerEO> chatPeers = new ArrayList();
                // 默认X小时前
                if (!directPay) {
                    Date endTime = calTime;
                    Date startTime = DateUtil.getPreviousDay(DateUtil.getSimpleDate(calTime),
                            2 * EagleConstants.DEFAULT_SET_OWNER_PERIOD);
                    // 得到聊天对象
                    chatPeers = chatPeersDao.getChatPeerEOsByBuyNickandTimePeriod(temp.getSellerNick(), buyNick,
                            DateUtil.parse(startTime, DateUtil.datePatternYY_MM_DDHHMMSS),
                            DateUtil.parse(endTime, DateUtil.datePatternYY_MM_DDHHMMSS), EagleConstants.ORDERBY_DESC);
                }
                Map<String, Date> LastChatPeers = new HashMap();
                if (!directPay && chatPeers != null && !chatPeers.isEmpty()) {
                    for (ChatPeerEO tempChat : chatPeers) {
                        if (LastChatPeers.get(tempChat.getId().getServiceStaffId()) == null) {
                            LastChatPeers.put(tempChat.getId().getServiceStaffId(), tempChat.getId().getChatDate());
                        } else if (LastChatPeers.get(tempChat.getId().getServiceStaffId()).after(
                                tempChat.getId().getChatDate())) {
                            LastChatPeers.put(tempChat.getId().getServiceStaffId(), tempChat.getId().getChatDate());
                        }
                    }

                    // 如果接待客服等于1，则业务归属该客服
                    // if (LastChatPeers.size() == 1) {
                    // String staffId = LastChatPeers.get(0).getId()
                    // .getServiceStaffId();
                    // if (staffId != null && !staffId.isEmpty()) {
                    // owner = staffId;
                    // } else {
                    // owner = EagleConstants.DEFAULT_OWNER;
                    // }
                    // // 如果接待客服大于等于1，则业务查询具体聊天时间
                    // } else
                    if (LastChatPeers.size() >= 1) {
                        Date lastTime = DateUtil.getPreviousDay(calTime, 2 * EagleConstants.DEFAULT_SET_OWNER_PERIOD);
                        for (String staffId : LastChatPeers.keySet()) {
                            if (staffId != null && LastChatPeers.get(staffId) != null
                                    && LastChatPeers.get(staffId).after(DateUtil.getPreviousDay(new Date(), 89))) {
                                List<MsgList> MsgLists = new ArrayList();
                                if (LastChatPeers.get(staffId).before(lastTime)) {
                                    MsgLists = chatLogBlh.getChatLogs(staffId, buyNick, lastTime, calTime, sessionKey);
                                } else {
                                    MsgLists = chatLogBlh.getChatLogs(staffId, buyNick, LastChatPeers.get(staffId),
                                            calTime, sessionKey);
                                }
                                if (MsgLists != null && !MsgLists.isEmpty()) {
                                    // 获取聊天记录的大小
                                    int i = MsgLists.size() - 1;
                                    while (i >= 0) {
                                        MsgList msg = MsgLists.get(i);
                                        i--;
                                        if (msg.getDirection() == 0) {
                                            Date chatTime = DateUtil.getDateByString(msg.getTime(),
                                                    DateUtil.datePatternYY_MM_DDHHMMSS);
                                            if (chatTime.after(lastTime) && chatTime.before(calTime)) {
                                                lastTime = chatTime;
                                                owner = staffId;
                                                break;
                                            }
                                        }
                                    }

                                } else if (MsgLists == null) {
                                    owner = EagleConstants.PENDING_OWNER;
                                    log.error(managerId + "chatlog response is null!");
                                }
                            } else {
                                owner = EagleConstants.DEFAULT_OWNER;
                            }
                        }
                        log.info("业务归属" + owner);
                        // 其他，如果没有客服接待，则设置为直接购买
                    }
                } else {
                    owner = EagleConstants.DEFAULT_OWNER;

                }
                // 设置最终归属
                temp.setTradeOwner(owner);
                // 增加入列表
                ownerTrades.add(temp);
            }
            if (!ownerTrades.isEmpty()) {
                for (TradeEO temp : ownerTrades)
                    tradeSoldDao.updateData(temp);
            }
        }

    }

    /**
     * 聊天局数最多的客户
     *
     * @param managerId
     * @param sessionKey
     * @param needAssignTrades
     * @param dateType         日期类型，有根据付款日期，交易创建日期两种
     * @throws Exception
     */
    public void runMostContactOwner(String managerId, String sessionKey, List<TradeEO> needAssignTrades, String dateType)
            throws Exception {
        if (needAssignTrades != null && !needAssignTrades.isEmpty()) {
            for (TradeEO temp : needAssignTrades) {
                // 设置初始归属为直拍
                String owner = EagleConstants.DEFAULT_OWNER;
                Date calTime = this.chooseDateType(temp, dateType);
                // 如果是当天则返回
                if (!calTime.before(DateUtil.getSimpleDate(new Date()))) {
                    continue;
                }
                // 如果buyNick=null
                // 则直接设置为direct buyer
                String buyNick = temp.getBuyerNick();
                //
                Boolean manualJudge = false;
                if (buyNick == null || buyNick.isEmpty()) {
                    manualJudge = true;
                } else {
                    //2017-9-7,解密buyNick
                    if (SecurityClient.isEncryptData(buyNick, "nick")) {
                        SecurityClient client = TaoBaoClientUtil.getSecurityTaobaoClient();
                        buyNick = client.decrypt(buyNick, "nick", sessionKey);
                    }
                }
                List<ChatPeerEO> chatPeers = new ArrayList();
                // 默认X小时前
                if (!manualJudge) {
                    Date endTime = calTime;
                    Date startTime = DateUtil.getPreviousDay(DateUtil.getSimpleDate(calTime),
                            2 * EagleConstants.DEFAULT_SET_OWNER_PERIOD);
                    // 得到聊天对象
                    chatPeers = chatPeersDao.getDistinctRecordByBuyNickandTimePeriod(temp.getSellerNick(), buyNick,
                            DateUtil.parse(startTime, DateUtil.datePatternYY_MM_DDHHMMSS),
                            DateUtil.parse(endTime, DateUtil.datePatternYY_MM_DDHHMMSS), EagleConstants.ORDERBY_DESC);
                }
                if (!manualJudge && chatPeers != null && !chatPeers.isEmpty()) {
                    if (chatPeers.size() >= 1) {
                        Date startTime = DateUtil.getPreviousDay(calTime, 2 * EagleConstants.DEFAULT_SET_OWNER_PERIOD);
                        int chatNum = 0;
                        for (ChatPeerEO chatPeer : chatPeers) {
                            String staffId = chatPeer.getId().getServiceStaffId();
                            if (staffId != null && startTime.after(DateUtil.getPreviousDay(new Date(), 89))) {
                                List<MsgList> result = chatLogBlh.getChatLogs(staffId, buyNick, startTime, calTime,
                                        sessionKey);
                                if (result != null && !result.isEmpty()) {
                                    // 必须超过客户设置的有效对话次数
                                    log.info(staffId + " 客服聊天句数：" + result.size());
                                    // 获取聊天记录的大小
                                    if (result.size() > chatNum) {
                                        chatNum = result.size();
                                        owner = staffId;
                                    }
                                } else if (result == null) {
                                    owner = EagleConstants.PENDING_OWNER;
                                }
                            } else {
                                owner = EagleConstants.DEFAULT_OWNER;
                            }
                        }

                        // 其他，如果没有客服接待，则设置为直接购买
                    } else {
                        owner = EagleConstants.DEFAULT_OWNER;
                    }
                } else if (manualJudge) {
                    owner = EagleConstants.PENDING_OWNER;
                } else {
                    owner = EagleConstants.DEFAULT_OWNER;
                }
                log.info("业务归属" + owner);
                // 设置最终归属
                temp.setTradeOwner(owner);
            }
            if (!needAssignTrades.isEmpty()) {
                for (TradeEO temp : needAssignTrades)
                    tradeSoldDao.updateData(temp);
            }
        }
    }

    public TradeSoldDAO getTradeSoldDao() {
        return tradeSoldDao;
    }

    public void setTradeSoldDao(TradeSoldDAO tradeSoldDao) {
        this.tradeSoldDao = tradeSoldDao;
    }

    public ChatPeersDAO getChatPeersDao() {
        return chatPeersDao;
    }

    public void setChatPeersDao(ChatPeersDAO chatPeersDao) {
        this.chatPeersDao = chatPeersDao;
    }

    public IChatLogBlh getChatLogBlh() {
        return chatLogBlh;
    }

    public void setChatLogBlh(IChatLogBlh chatLogBlh) {
        this.chatLogBlh = chatLogBlh;
    }

    public GroupMemberDAO getGroupMemberDao() {
        return groupMemberDao;
    }

    public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
        this.groupMemberDao = groupMemberDao;
    }

}
