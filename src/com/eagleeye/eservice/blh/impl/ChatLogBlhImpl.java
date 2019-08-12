package com.eagleeye.eservice.blh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.blh.IChatLogBlh;
import com.eagleeye.eservice.dao.impl.ChatLogDAO;
import com.eagleeye.eservice.dao.impl.ChatPeersDAO;
import com.eagleeye.eservice.eo.ChatLogEO;
import com.eagleeye.user.dao.GroupMemberDAO;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.MsgList;
import com.taobao.api.request.WangwangAbstractInitializeRequest;
import com.taobao.api.request.WangwangAbstractLogqueryRequest;
import com.taobao.api.response.WangwangAbstractInitializeResponse;
import com.taobao.api.response.WangwangAbstractLogqueryResponse;

public class ChatLogBlhImpl implements IChatLogBlh {
    private Logger log = Logger.getLogger(ChatLogBlhImpl.class);

    GroupMemberDAO groupMemberDao;

    ChatPeersDAO chatPeersDao;

    ChatLogDAO chatLogDao;

//	@Override
//	public void loadChatLog(String serviceStaffId, Date startTime, Date endTime, String sessionKey) throws Exception {
//		// long a = System.currentTimeMillis();
//		// 得到sessionkey
//		if (sessionKey == null)
//			sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
//		if (sessionKey == null)
//			throw new Exception(serviceStaffId + " no session key!");
//		// 封装taobaorequest
//		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);
//		WangwangEserviceChatlogGetRequest req = new WangwangEserviceChatlogGetRequest();
//		List<GroupMemberEO> groupMember = groupMemberDao.findByServiceStaffId(serviceStaffId);
//		// 判断组员信息
//		if (groupMember == null || groupMember.isEmpty()) {
//			throw new Exception("没有此组员!");
//		} else if (groupMember.size() != 1) {
//			throw new Exception("此客服id不唯一！");
//		}
//		// 根据日期查询出对应聊天对象
//		List<Map> uids = chatPeersDao.getDistinctUidByStaffIdandTimePeriod(serviceStaffId, startTime, endTime);
//		// System.out.println("\nuids执行耗时 : " + (System.currentTimeMillis() - a)
//		// 1000f + " 秒 ");
//		// 判断淘宝客户id是否cntaobao为前缀，不是则添加
//		if (!serviceStaffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
//			serviceStaffId = TaoBaoUtil.addEServicePrefix(serviceStaffId);
//		}
//		// System.out.println("\nchatlog接口开始执行耗时 : "
//		// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
//		// 根据聊天和日期取一天的聊天记录
//		if (uids != null) {
//			List<ChatLogEO> chatLogs = new ArrayList();
//			for (Map temp : uids) {
//
//				String uid = (String) temp.get("uid");
//				req.setFromId(serviceStaffId);
//				// 判断淘宝客户id是否cntaobao为前缀，不是则添加
//				if (!uid.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
//					uid = TaoBaoUtil.addEServicePrefix(uid);
//				}
//				req.setToId(uid);
//				req.setStartDate(DateUtil.getTimeByCustomPattern(startTime, DateUtil.datePatternYY_MM_DD).toString());
//				req.setEndDate(DateUtil.getTimeByCustomPattern(endTime, DateUtil.datePatternYY_MM_DD).toString());
//				try {
//					WangwangEserviceChatlogGetResponse response = client.execute(req, sessionKey);
//					List<Msg> msgs = response.getMsgs();
//					if (msgs != null && !msgs.isEmpty()) {
//						for (Msg msg : msgs) {
//							ChatLogEO chatLog = new ChatLogEO();
//							chatLog.setContent(msg.getContent());
//							chatLog.setCreatedAt(new Date());
//							chatLog.setDirection(msg.getDirection());
//							String staffId = TaoBaoUtil.removeEServicePrefix(serviceStaffId);
//							String toid = TaoBaoUtil.removeEServicePrefix(uid);
//							chatLog.setServiceStaffId(staffId);
//							chatLog.setMessageTime(DateUtil.getDateByString(msg.getTime(),
//									DateUtil.datePatternYY_MM_DDHHMMSS));
//							chatLog.setToId(toid);
//							// chatLog.setId(chatLog);
//							chatLogs.add(chatLog);
//						}
//					} else if (response.getMsg() != null) {
//						throw new Exception(response.getMsg());
//					}
//				} catch (Exception e) {
//					log.error(e);
//				}
//			}
//			// System.out.println("\nchatlog接口执行结束耗时 : "
//			// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
//			if (!chatLogs.isEmpty()) {
//				// System.out.println("\n开始删除执行耗时 : "
//				// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
//				String staffId = TaoBaoUtil.removeEServicePrefix(serviceStaffId);
//				chatLogDao.deleteByStaffIdandTimePeriod(staffId, startTime, endTime);
//				// System.out.println("\n开始存储执行耗时 : "
//				// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
//				chatLogDao.saveAllObject(chatLogs);
//				// log.info(staffId + "聊天记录添加成功");
//			}
//		}
//	}

//	/**
//	 * 得到聊天记录
//	 * 
//	 * @param staffId
//	 * @param buyNick
//	 * @param startTime
//	 * @param endTime
//	 * @return
//	 * @throws Exception
//	 */
//	@Deprecated
//	public WangwangEserviceChatlogGetResponse getChatLogResponse(String staffId, String buyNick, Date startTime,
//			Date endTime, String sessionKey) throws Exception {
//		if (sessionKey == null)
//			sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
//		if (sessionKey == null)
//			throw new Exception(staffId + " no session key!");
//		if (!staffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
//			staffId = TaoBaoUtil.addEServicePrefix(staffId);
//		}
//		if (!buyNick.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
//			buyNick = TaoBaoUtil.addEServicePrefix(buyNick);
//		}
//		WangwangEserviceChatlogGetRequest req = new WangwangEserviceChatlogGetRequest();
//		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_XML);
//		req.setFromId(staffId);
//		req.setToId(buyNick);
//		req.setStartDate(DateUtil.getTimeByCustomPattern(startTime, DateUtil.datePatternYY_MM_DD).toString());
//		req.setEndDate(DateUtil.getTimeByCustomPattern(endTime, DateUtil.datePatternYY_MM_DD).toString());
//		try {
//			WangwangEserviceChatlogGetResponse response = client.execute(req, sessionKey);
//			// 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
//			// 最多重跑3次
//			if (response == null || (response.getMsgs() == null && response.getErrorCode() != null)) {
//				int count = 0;
//				Boolean needReRun = true;
//				while (count < 3 && needReRun) {
//					// 线程休眠半秒
//					Thread.sleep(500);
//					// count自增
//					count++;
//					response = client.execute(req, sessionKey);
//					if (response != null && (response.getMsgs() != null || response.getErrorCode() == null)) {
//						needReRun = false;
//					}
//				}
//				log.error(staffId + " " + sessionKey + " " + startTime + "-" + endTime + " ChatLog Rerun time:" + count);
//			}
//			// end
//			return response;
//		} catch (Exception e) {
//			log.error(e);
//			return null;
//		}
//	}

    /**
     * 得到聊天记录 2013-5-22
     *
     * @param staffId
     * @param buyNick
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public WangwangAbstractLogqueryResponse getChatLogResponseNew(String staffId, String buyNick, Date startTime,
                                                                  Date endTime, String sessionKey) throws Exception {
        if (sessionKey == null)
            sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
        if (sessionKey == null)
            throw new Exception(staffId + " no session key!");
        if (!staffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
            staffId = TaoBaoUtil.addEServicePrefix(staffId);
        }
        if (!buyNick.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
            buyNick = TaoBaoUtil.addEServicePrefix(buyNick);
        }
        TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);
        // 初始化模糊查询
        WangwangAbstractInitializeRequest req1 = new WangwangAbstractInitializeRequest();
        req1.setCharset(EagleConstants.CHARSET_UTF8);
        try {
            WangwangAbstractInitializeResponse response1 = client.execute(req1, sessionKey);
            if (response1 == null || response1.getRetCode() != 0) {
                int count = 0;
                Boolean needReRun = true;
                while (count < 3 && needReRun) {
                    // 线程休眠半秒
                    Thread.sleep(500);
                    // count自增
                    count++;
                    response1 = client.execute(req1, sessionKey);
                    if (response1 != null && response1.getRetCode() != 0) {
                        needReRun = false;
                    }
                }
                log.error(staffId + " " + sessionKey + " " + startTime + "-" + endTime
                        + " WangwangAbstractInitializeRequest Rerun time:" + count);
            }
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        // 模糊查询聊天记录
        WangwangAbstractLogqueryRequest req = new WangwangAbstractLogqueryRequest();

        req.setFromId(staffId);
        req.setToId(buyNick);
        req.setCharset(EagleConstants.CHARSET_UTF8);
        req.setStartDate(startTime.getTime());
        req.setEndDate(endTime.getTime());
        try {
            WangwangAbstractLogqueryResponse response = client.execute(req, sessionKey);
            // 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
            // 最多重跑3次
            if (response == null || (response.getMsgLists() == null && response.getErrorCode() != null)) {
                int count = 0;
                Boolean needReRun = true;
                while (count < 3 && needReRun) {
                    // 线程休眠半秒
                    Thread.sleep(500);
                    // count自增
                    count++;
                    response = client.execute(req, sessionKey);
                    if (response != null && (response.getMsgLists() != null || response.getErrorCode() == null)) {
                        needReRun = false;
                    }
                }
                log.error(staffId + " " + sessionKey + " " + startTime + "-" + endTime + " ChatLog Rerun time:" + count);
            }
            // end
            return response;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }


    private List<ChatLogEO> parseChatLogs(List<MsgList> msgList, String staffId, String uid) {
        if (msgList != null && !msgList.isEmpty()) {
            staffId = TaoBaoUtil.removeEServicePrefix(staffId);
            uid = TaoBaoUtil.removeEServicePrefix(uid);
            List<ChatLogEO> chatLogs = new ArrayList<ChatLogEO>();
            int seq = 1;
            for (MsgList msg :
                    msgList) {
                ChatLogEO chatLog = new ChatLogEO();
                chatLog.setId(seq++);
                chatLog.setContent(msg.getContent());
                chatLog.setCreatedAt(new Date());
                chatLog.setDirection(msg.getDirection());
                chatLog.setMessageTime(DateUtil.parse(msg.getTime(), DateUtil.datePatternMMMDDYYYY_HHMMSS));
                chatLog.setServiceStaffId(staffId);
                chatLog.setToId(uid);
                chatLog.setType(msg.getType());
                chatLog.setLength(msg.getLength());
                chatLogs.add(chatLog);
            }
            return chatLogs;
        }
        return null;
    }

    private List<MsgList> loadChatLogsFromTB(String staffId, String buyNick, Date startTime, Date endTime, String sessionKey) throws Exception {
        {
            if (sessionKey == null)
                sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
            if (sessionKey == null)
                throw new Exception(staffId + " no session key!");
            if (!staffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
                staffId = TaoBaoUtil.addEServicePrefix(staffId);
            }
            if (!buyNick.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
                buyNick = TaoBaoUtil.addEServicePrefix(buyNick);
            }
            TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);
            // 初始化模糊查询
            WangwangAbstractInitializeRequest req1 = new WangwangAbstractInitializeRequest();
            req1.setCharset(EagleConstants.CHARSET_UTF8);
            try {
                WangwangAbstractInitializeResponse response1 = client.execute(req1, sessionKey);
                if (response1 == null || response1.getRetCode() != 0) {
                    int count = 0;
                    Boolean needReRun = true;
                    while (count < 3 && needReRun) {
                        // 线程休眠半秒
                        Thread.sleep(500);
                        // count自增
                        count++;
                        response1 = client.execute(req1, sessionKey);
                        if (response1 != null && response1.getRetCode() != 0) {
                            needReRun = false;
                        }
                    }
                    log.error(staffId + " " + sessionKey + " " + startTime + "-" + endTime
                            + " WangwangAbstractInitializeRequest Rerun time:" + count);
                }
            } catch (Exception e) {
                log.error(e);
                return null;
            }
            List<MsgList> msgs = new ArrayList();
            // 模糊查询聊天记录
            WangwangAbstractLogqueryRequest req = new WangwangAbstractLogqueryRequest();
            req.setFromId(staffId);
            req.setToId(buyNick);
            // 每100句为一个round
            req.setCount(100L);
            req.setCharset(EagleConstants.CHARSET_UTF8);
            req.setStartDate(startTime.getTime());
            req.setEndDate(endTime.getTime());
            Boolean hasNext = true;
            while (hasNext) {
                try {
                    WangwangAbstractLogqueryResponse response = client.execute(req, sessionKey);
                    // 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
                    // 最多重跑3次
                    if (response == null || (response.getMsgLists() == null && response.getErrorCode() != null)) {
                        int count = 0;
                        Boolean needReRun = true;
                        while (count < 3 && needReRun) {
                            // 线程休眠半秒
                            Thread.sleep(1300);
                            // count自增
                            count++;
                            response = client.execute(req, sessionKey);
                            if (response == null) {
                                continue;
                            }
                            if (response != null && (response.getMsgLists() != null || response.getErrorCode() == null)) {
                                needReRun = false;
                            }
                        }
                        log.error(staffId + " " + sessionKey + " " + startTime + "-" + endTime + " ChatLog Rerun time:"
                                + count);
                    }
                    // end
                    if (response == null) {
                        log.error("ChatLog response is null!");
                        return null;
                    }
                    if (response.getIsSliced() == null || response.getIsSliced() != 1 || response.getNextKey() == null
                            || response.getNextKey().isEmpty()) {
                        hasNext = false;
                    }
                    req.setNextKey(response.getNextKey());
                    // 累加聊天记录
                    if (response.getMsgLists() != null && !response.getMsgLists().isEmpty()) {
                        log.info("Chatlog add:" + response.getMsgLists().size());
                        response.getMsgLists().addAll(msgs);
                        msgs = response.getMsgLists();
                    }
                } catch (Exception e) {
                    log.error(e);
                    hasNext = false;
                    return null;
                }
            }
            return msgs;
        }
    }

    /**
     * 得到聊天记录 2013-12-13
     *
     * @param staffId
     * @param buyNick
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public List<MsgList> getChatLogs(String staffId, String buyNick, Date startTime, Date endTime, String sessionKey)
            throws Exception {
        if (sessionKey == null)
            sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
        if (sessionKey == null)
            throw new Exception(staffId + " no session key!");
        if (!staffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
            staffId = TaoBaoUtil.addEServicePrefix(staffId);
        }
        if (!buyNick.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
            buyNick = TaoBaoUtil.addEServicePrefix(buyNick);
        }
        TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);
        // 初始化模糊查询
        WangwangAbstractInitializeRequest req1 = new WangwangAbstractInitializeRequest();
        req1.setCharset(EagleConstants.CHARSET_UTF8);
        try {
            WangwangAbstractInitializeResponse response1 = client.execute(req1, sessionKey);
            if (response1 == null || response1.getRetCode() != 0) {
                int count = 0;
                Boolean needReRun = true;
                while (count < 3 && needReRun) {
                    // 线程休眠半秒
                    Thread.sleep(500);
                    // count自增
                    count++;
                    response1 = client.execute(req1, sessionKey);
                    if (response1 != null && response1.getRetCode() != 0) {
                        needReRun = false;
                    }
                }
                log.error(staffId + " " + sessionKey + " " + startTime + "-" + endTime
                        + " WangwangAbstractInitializeRequest Rerun time:" + count);
            }
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        List<MsgList> msgs = new ArrayList();
        // 模糊查询聊天记录
        WangwangAbstractLogqueryRequest req = new WangwangAbstractLogqueryRequest();
        req.setFromId(staffId);
        req.setToId(buyNick);
        // 每100句为一个round
        req.setCount(100L);
        req.setCharset(EagleConstants.CHARSET_UTF8);
        req.setStartDate(startTime.getTime());
        req.setEndDate(endTime.getTime());
        Boolean hasNext = true;
        while (hasNext) {
            try {
                WangwangAbstractLogqueryResponse response = client.execute(req, sessionKey);
                // 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
                // 最多重跑3次
                if (response == null || (response.getMsgLists() == null && response.getErrorCode() != null)) {
                    int count = 0;
                    Boolean needReRun = true;
                    while (count < 3 && needReRun) {
                        // 线程休眠半秒
                        Thread.sleep(1300);
                        // count自增
                        count++;
                        response = client.execute(req, sessionKey);
                        if (response == null) {
                            continue;
                        }
                        if (response != null && (response.getMsgLists() != null || response.getErrorCode() == null)) {
                            needReRun = false;
                        }
                    }
                    log.error(staffId + " " + sessionKey + " " + startTime + "-" + endTime + " ChatLog Rerun time:"
                            + count);
                }
                // end
                if (response == null) {
                    log.error("ChatLog response is null!");
                    return null;
                }
                if (response.getIsSliced() == null || response.getIsSliced() != 1 || response.getNextKey() == null
                        || response.getNextKey().isEmpty()) {
                    hasNext = false;
                }
                req.setNextKey(response.getNextKey());
                // 累加聊天记录
                if (response.getMsgLists() != null && !response.getMsgLists().isEmpty()) {
                    log.info("Chatlog add:" + response.getMsgLists().size());
                    response.getMsgLists().addAll(msgs);
                    msgs = response.getMsgLists();
                }
            } catch (Exception e) {
                log.error(e);
                hasNext = false;
                return null;
            }
        }
        return msgs;
    }

    @Override
    public List<ChatLogEO> loadChatLogs(String staffId, String buyNick, Date startTime, Date endTime, String sessionKey) throws Exception {
        try {
            List<MsgList> msgs = this.loadChatLogsFromTB(staffId, buyNick, startTime, endTime, sessionKey);
            if (msgs != null && !msgs.isEmpty()) {
                List<ChatLogEO> chatLogs = this.parseChatLogs(msgs, staffId, buyNick);
                if (chatLogs != null && !chatLogs.isEmpty()) {
                    chatLogDao.saveOrUpdateBatch(chatLogs);
                }
            }
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        return null;
    }

//	/**
//	 * 实时得到聊天记录对象
//	 * 
//	 * @param staffId
//	 * @param buyNick
//	 * @param startTime
//	 * @param endTime
//	 * @param sessionKey
//	 * @return
//	 */
//	@Deprecated
//	public List<Msg> getChatLogByStaffIdAndUid(String staffId, String buyNick, Date startTime, Date endTime,
//			String sessionKey) {
//		try {
//			WangwangEserviceChatlogGetResponse response = this.getChatLogResponse(staffId, buyNick, startTime, endTime,
//					sessionKey);
//			if (response != null && response.getMsgs() != null) {
//				return response.getMsgs();
//			}
//		} catch (Exception e) {
//			log.error(e);
//		}
//		return null;
//	}

    /**
     * 实时得到聊天记录对象 2013-5-22
     *
     * @param staffId
     * @param buyNick
     * @param startTime
     * @param endTime
     * @param sessionKey
     * @return
     */
    public List<MsgList> getChatLogByStaffIdAndUidNew(String staffId, String buyNick, Date startTime, Date endTime,
                                                      String sessionKey) {
        try {
            WangwangAbstractLogqueryResponse response = this.getChatLogResponseNew(staffId, buyNick, startTime,
                    endTime, sessionKey);
            if (response != null && response.getMsgLists() != null) {
                return response.getMsgLists();
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<ChatLogEO> findAllByStaffId(String serviceStaffId) {
        // TODO Auto-generated method stub
        return null;
    }

    public GroupMemberDAO getGroupMemberDao() {
        return groupMemberDao;
    }

    public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
        this.groupMemberDao = groupMemberDao;
    }

    public ChatPeersDAO getChatPeersDao() {
        return chatPeersDao;
    }

    public void setChatPeersDao(ChatPeersDAO chatPeersDao) {
        this.chatPeersDao = chatPeersDao;
    }

    public ChatLogDAO getChatLogDao() {
        return chatLogDao;
    }

    public void setChatLogDao(ChatLogDAO chatLogDao) {
        this.chatLogDao = chatLogDao;
    }

    @Override
    public List<ChatLogEO> findAllByStaffId(String serviceStaffId, Date start, Date end) {
        // TODO Auto-generated method stub
        return chatLogDao.findAllByStaffId(serviceStaffId, start, end);
    }
}
