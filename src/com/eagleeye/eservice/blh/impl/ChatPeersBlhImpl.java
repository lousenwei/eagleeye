/**
 * 
 */
package com.eagleeye.eservice.blh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.ReturnMo;
import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.blh.IChatPeersBlh;
import com.eagleeye.eservice.dao.impl.ChatPeersDAO;
import com.eagleeye.eservice.eo.ChatPeerEO;
import com.eagleeye.eservice.eo.ChatPeerEOId;
import com.eagleeye.eservice.mo.ChatPeerMO;
import com.eagleeye.eservice.mo.ChatPeerQueryParametersMO;
import com.eagleeye.user.dao.GroupMemberDAO;
import com.eagleeye.user.eo.GroupMemberEO;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Chatpeer;
import com.taobao.api.request.WangwangEserviceChatpeersGetRequest;
import com.taobao.api.response.WangwangEserviceChatpeersGetResponse;

/**
 * @author lousenwei
 * 
 */
public class ChatPeersBlhImpl implements IChatPeersBlh {
	private Logger log = Logger.getLogger(ChatPeersBlhImpl.class);

	GroupMemberDAO groupMemberDao;

	ChatPeersDAO chatPeersDao;

	@Override
	public void loadChatPeers(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) throws Exception {
		// TODO Auto-generated method stub
		// 得到sessionkey
		if (sessionKey == null)
			sessionKey = (String) SessionManager
					.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		if (sessionKey == null)
			throw new Exception(serviceStaffId + " no session key!");
		// 封装taobaorequest
		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);
		//TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_XML);
		WangwangEserviceChatpeersGetRequest req = new WangwangEserviceChatpeersGetRequest();
		List<GroupMemberEO> groupMember = groupMemberDao
				.findByServiceStaffId(serviceStaffId);
		// 判断组员信息
		if (groupMember == null || groupMember.isEmpty()) {
			throw new Exception("没有此组员!");
		} else if (groupMember.size() != 1) {
			throw new Exception("此客服id不唯一！");
		}
		// 判断淘宝客户id是否cntaobao为前缀，不是则添加
		if (!serviceStaffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
			serviceStaffId = TaoBaoUtil.addEServicePrefix(serviceStaffId);
		}
		req.setChatId(serviceStaffId);
		req.setStartDate(DateUtil.getTimeByCustomPattern(startTime,
				DateUtil.datePatternYY_MM_DD).toString());
		req.setEndDate(DateUtil.getTimeByCustomPattern(endTime,
				DateUtil.datePatternYY_MM_DD).toString());
		try {
			WangwangEserviceChatpeersGetResponse response = client.execute(req,
					sessionKey);
			List<Chatpeer> chatPeersInterfaceList = response.getChatpeers();
			// 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
			// 最多重跑3次
			if (chatPeersInterfaceList == null
					&& response.getErrorCode() != null
					&& TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
				int count = 0;
				Boolean needReRun = true;
				while (count < 3 && needReRun) {
					// 线程休眠半秒
					Thread.sleep(1300);
					// count自增
					count++;
					response = client.execute(req, sessionKey);
					chatPeersInterfaceList = response.getChatpeers();
					if (chatPeersInterfaceList != null
							|| response.getErrorCode() == null) {
						needReRun = false;
					}
				}
				log.error(serviceStaffId + " " + sessionKey + " " + startTime
						+ "-" + endTime + " ChatPeers Rerun time:" + count);
			}
			// end
			Map<ChatPeerEOId, ChatPeerEO> chatPeersMap = new HashMap();
			if (chatPeersInterfaceList != null) {
				for (Chatpeer temp : chatPeersInterfaceList) {
					if (temp.getUid() != null) {
						ChatPeerEO chatPeers = new ChatPeerEO();
						ChatPeerEOId id = new ChatPeerEOId();
						chatPeers.setCreatedAt(new Date());
						id.setChatDate(DateUtil.getDateByString(temp.getDate(),
								DateUtil.datePatternYY_MM_DD));
						String staffId = TaoBaoUtil
								.removeEServicePrefix(serviceStaffId);
						id.setServiceStaffId(staffId);
						String uid = TaoBaoUtil.removeEServicePrefix(temp
								.getUid());
						id.setUid(uid);
						chatPeers.setId(id);
						if (chatPeersMap.get(id) == null) {
							chatPeersMap.put(id, chatPeers);
							// log.info(staffId + " " + uid + "\n");
						}
					}
				}
				if (chatPeersMap != null && !chatPeersMap.isEmpty()) {
					chatPeersDao.saveOrUpdateBatch(chatPeersMap.values());
					log.info(serviceStaffId + " 聊天对象列表保存成功");
				}
			} else if (response.getErrorCode() != null
					&& !response.getErrorCode().isEmpty()) {
				throw new Exception(response.getMsg());
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 组装ChatPeerMO
	 * 
	 * @param managerId
	 * @param buyNick
	 * @param start
	 * @param end
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	public List getChatPeerEOsByBuyNickandTimePeriod(String managerId,
			String buyNick, Date start, Date end, String orderBy)
			throws Exception {
		List<ChatPeerEO> chatPeers = chatPeersDao
				.getChatPeerEOsByBuyNickandTimePeriod(managerId, buyNick,
						start, end, orderBy);
		if (chatPeers != null && !chatPeers.isEmpty()) {
			Map<Date, ChatPeerMO> chatPeerMoMap = new HashMap();
			for (ChatPeerEO tmp : chatPeers) {
				if (tmp.getId().getChatDate() != null) {
					if (chatPeerMoMap.get(tmp.getId().getChatDate()) != null) {
						ChatPeerMO chatPeerMo = new ChatPeerMO();
						chatPeerMo.setChatDate(tmp.getId().getChatDate());
						chatPeerMo.getChatPeers().add(tmp);
						chatPeerMoMap
								.put(tmp.getId().getChatDate(), chatPeerMo);
					} else {
						chatPeerMoMap.get(tmp.getId().getChatDate())
								.getChatPeers().add(tmp);
					}
				}
			}
			return new ArrayList(chatPeerMoMap.values());
		}
		return null;
	}

	/**
	 * 获取distinct对象
	 * 
	 * @param mo
	 * @return
	 */
	public List getDistinctRecordByBuyNickandTimePeriod(
			ChatPeerQueryParametersMO mo) {
		try {
			List<Map> chatPeerDates = chatPeersDao
					.getDistinctRecordByBuyNickandTimePeriod(mo);
			if (chatPeerDates != null && !chatPeerDates.isEmpty()) {
				List results = new ArrayList();
				int i = 0;
				for (Map tmp : chatPeerDates) {
					ReturnMo returnMo = new ReturnMo();
					returnMo.setData(tmp.get(mo.getField()));
					returnMo.setId(i++);
					results.add(returnMo);
				}
				if (!results.isEmpty())
					return results;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public List<ChatPeerEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return chatPeersDao.findByServiceStaffId(serviceStaffId);
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

}
