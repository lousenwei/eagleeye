package com.eagleeye.eservice.blh.impl;

import com.eagleeye.eservice.blh.IOnlineTimeBlh;
import com.eagleeye.eservice.dao.impl.OnlineTimeDAO;
import com.eagleeye.eservice.eo.OnlineTimesOnDayEO;
import com.eagleeye.user.dao.GroupMemberDAO;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class OnlineTimeBlhImpl implements IOnlineTimeBlh {
	private Logger log = Logger.getLogger(OnlineTimeBlhImpl.class);
	GroupMemberDAO groupMemberDao;
	OnlineTimeDAO onlineTimeDao;

//	@Override
//	public void loadOnlineTime(String serviceStaffId, Date startTime,
//			Date endTime, String sessionKey) throws Exception {
//		// TODO Auto-generated method stub
//		// 得到sessionkey
//		if (sessionKey == null)
//			sessionKey = (String) SessionManager
//					.getSessionByKey(EagleConstants.TOPSESSIONKEY);
//		if (sessionKey == null)
//			throw new Exception(serviceStaffId + " no session key!");
//		// 封装taobaorequest
//		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_XML);
//		WangwangEserviceOnlinetimeGetRequest req = new WangwangEserviceOnlinetimeGetRequest();
//		List<GroupMemberEO> groupMember = groupMemberDao
//				.findByServiceStaffId(serviceStaffId);
//		// 判断组员信息
//		if (groupMember == null || groupMember.isEmpty()) {
//			throw new Exception("没有此组员!");
//		} else if (groupMember.size() != 1) {
//			throw new Exception("此客服id不唯一！");
//		}
//		// 判断淘宝客户id是否cntaobao为前缀，不是则添加
//		if (!serviceStaffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
//			serviceStaffId = TaoBaoUtil.addEServicePrefix(serviceStaffId);
//		}
//		req.setServiceStaffId(serviceStaffId);
//		req.setStartDate(startTime);
//		req.setEndDate(endTime);
//		try {
//			WangwangEserviceOnlinetimeGetResponse response = client.execute(
//					req, sessionKey);
//			List<OnlineTimesOnDay> onlineTimesOnDayList = response
//					.getOnlineTimesListOnDays();
//			// 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
//			// 最多重跑3次
//			if (onlineTimesOnDayList == null && response.getErrorCode() != null
//					&& TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
//				int count = 0;
//				Boolean needReRun = true;
//				while (count < 3 && needReRun) {
//					// 线程休眠半秒
//					Thread.sleep(500);
//					// count自增
//					count++;
//					response = client.execute(req, sessionKey);
//					onlineTimesOnDayList = response.getOnlineTimesListOnDays();
//					if (onlineTimesOnDayList != null
//							|| response.getErrorCode() == null) {
//						needReRun = false;
//					}
//				}
//				log.error(serviceStaffId + " " + sessionKey + " " + startTime
//						+ "-" + endTime + " OnlineTime Rerun time:" + count);
//			}
//			// end
//			Map<OnlineTimesOnDayEOId, OnlineTimesOnDayEO> onlineTimeMap = new HashMap();
//			if (onlineTimesOnDayList != null) {
//				for (OnlineTimesOnDay temp : onlineTimesOnDayList) {
//					for (OnlineTimeById tempId : temp.getOnlineTimeByIds()) {
//						if (tempId.getOnlineTimes() != null) {
//							OnlineTimesOnDayEO onlineTime = new OnlineTimesOnDayEO();
//							OnlineTimesOnDayEOId id = new OnlineTimesOnDayEOId();
//							onlineTime.setCreatedAt(new Date());
//							id.setOnlineDate(temp.getOnlineDate());
//							String staffId = TaoBaoUtil
//									.removeEServicePrefix(tempId
//											.getServiceStaffId());
//							id.setServiceStaffId(staffId);
//							onlineTime.setUpdatedAt(new Date());
//							onlineTime.setOnlineTimes(tempId.getOnlineTimes());
//							onlineTime.setManagerId(groupMember.get(0).getId()
//									.getManagerId());
//							onlineTime.setId(id);
//							if (onlineTimeMap.get(id) == null) {
//								onlineTimeMap.put(id, onlineTime);
//								// log.info(staffId + " " + temp.getOnlineDate()
//								// + "\n");
//							}
//						}
//					}
//				}
//				if (!onlineTimeMap.isEmpty()) {
//					onlineTimeDao.saveOrUpdateBatch(onlineTimeMap.values());
//					log.info(startTime + "-" + endTime + ":" + serviceStaffId
//							+ "在线时间保存成功");
//				}
//			} else if (response.getErrorCode() != null
//					&& !response.getErrorCode().isEmpty()) {
//				throw new Exception(response.getMsg());
//			}
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}

	@Override
	public void loadOnlineTime(String serviceStaffId, Date startTime, Date endTime, String sessionKey) throws Exception {

	}

	@Override
	public List<OnlineTimesOnDayEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return onlineTimeDao.findByServiceStaffId(serviceStaffId);
	}

	public GroupMemberDAO getGroupMemberDao() {
		return groupMemberDao;
	}

	public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
		this.groupMemberDao = groupMemberDao;
	}

	public OnlineTimeDAO getOnlineTimeDao() {
		return onlineTimeDao;
	}

	public void setOnlineTimeDao(OnlineTimeDAO onlineTimeDao) {
		this.onlineTimeDao = onlineTimeDao;
	}

}
