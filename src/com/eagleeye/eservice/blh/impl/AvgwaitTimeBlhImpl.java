package com.eagleeye.eservice.blh.impl;

import com.eagleeye.eservice.blh.IAvgwaitTimeBlh;
import com.eagleeye.eservice.dao.impl.AvgwaitTimeDAO;
import com.eagleeye.eservice.eo.WaitingTimesOnDayEO;
import com.eagleeye.user.dao.GroupMemberDAO;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;


public class AvgwaitTimeBlhImpl implements IAvgwaitTimeBlh {

	private Logger log = Logger.getLogger(AvgwaitTimeBlhImpl.class);

	AvgwaitTimeDAO avgwaitTimeDao;

	GroupMemberDAO groupMemberDao;

//	public void loadAvgwaitTime(String serviceStaffId, Date startTime,
//			Date endTime, String sessionKey) throws Exception {
//		// TODO Auto-generated method stub
//		// 得到sessionkey
//		if (sessionKey == null)
//			sessionKey = (String) SessionManager
//					.getSessionByKey(EagleConstants.TOPSESSIONKEY);
//		if (sessionKey == null)
//			throw new Exception(serviceStaffId + " no session key!");
//		// 封装taobaorequest
//		TaobaoClient client = TaoBaoClientUtil
//				.getDefaultTaoBaoClient(EagleConstants.FORMAT_XML);
//		WangwangEserviceAvgwaittimeGetRequest req = new WangwangEserviceAvgwaittimeGetRequest();
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
//			// 调用taobaoclient
//			WangwangEserviceAvgwaittimeGetResponse response = client.execute(
//					req, sessionKey);
//			// 取得数据包
//			List<WaitingTimesOnDay> waitTimesOnDayList = response
//					.getWaitingTimeListOnDays();
//			// 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
//			// 最多重跑3次
//			if (waitTimesOnDayList == null && response.getErrorCode() != null
//					&& TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
//				int count = 0;
//				Boolean needReRun = true;
//				while (count < 3 && needReRun) {
//					// 线程休眠半秒
//					Thread.sleep(500);
//					// count自增
//					count++;
//					response = client.execute(req, sessionKey);
//					waitTimesOnDayList = response.getWaitingTimeListOnDays();
//					if (waitTimesOnDayList != null
//							|| response.getErrorCode() == null) {
//						needReRun = false;
//					}
//				}
//				log.error(serviceStaffId + " " + sessionKey + " " + startTime
//						+ "-" + endTime + " AVGWAITTIME Rerun time:" + count);
//			}
//			// end
//			Map<WaitingTimesOnDayEOId, WaitingTimesOnDayEO> avgwaitTimeMap = new HashMap();
//			// 解析过程
//			if (waitTimesOnDayList != null) {
//				// 成功取得数据并解析
//				for (WaitingTimesOnDay tempTimes : waitTimesOnDayList) {
//					if (tempTimes.getWaitingTimeByIds() != null
//							&& !tempTimes.getWaitingTimeByIds().isEmpty()) {
//						for (WaitingTimeById tempById : tempTimes
//								.getWaitingTimeByIds()) {
//							if (tempById != null
//									&& tempById.getAvgWaitingTimes() != null) {
//								WaitingTimesOnDayEO avgwaitTime = new WaitingTimesOnDayEO();
//								WaitingTimesOnDayEOId id = new WaitingTimesOnDayEOId();
//								String staffId = TaoBaoUtil
//										.removeEServicePrefix(tempById
//												.getServiceStaffId());
//								id.setServiceStaffId(staffId);
//								avgwaitTime.setCreatedAt(new Date());
//								avgwaitTime.setUpdatedAt(new Date());
//								avgwaitTime.setAvgWaitingTimes(tempById
//										.getAvgWaitingTimes());
//								id.setWaitingDate(DateUtil.parse(
//										tempTimes.getWaitingDate(),
//										DateUtil.datePatternYYMMDD));
//								avgwaitTime.setManagerId(groupMember.get(0)
//										.getId().getManagerId());
//								avgwaitTime.setId(id);
//								if (avgwaitTimeMap.get(id) == null) {
//									avgwaitTimeMap.put(id, avgwaitTime);
//									log.info(staffId + " "
//											+ tempTimes.getWaitingDate() + "\n");
//								}
//							}
//						}
//					}
//				}
//				if (avgwaitTimeMap != null && !avgwaitTimeMap.isEmpty()) {
//					avgwaitTimeDao.saveOrUpdateBatch(avgwaitTimeMap.values());
//					log.info(startTime + "-" + endTime + ":" + serviceStaffId
//							+ "平均客户接待等待时间保存成功");
//				}
//			} else if (response.getErrorCode() != null
//					&& !response.getErrorCode().isEmpty()) {
//				// 取得失败，返回淘宝失败详细信息
//				// log.error(response.getErrorCode()+":"+response.getMsg());
//				throw new Exception(response.getMsg());
//				// log.error(response.getMsg());
//			}
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}

	@Override
	public void loadAvgwaitTime(String serviceStaffId, Date startTime, Date endTime, String sessionKey) throws Exception {
		//TODO
	}

	@Override
	public List<WaitingTimesOnDayEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return avgwaitTimeDao.findByServiceStaffId(serviceStaffId);
	}

	public AvgwaitTimeDAO getAvgwaitTimeDao() {
		return avgwaitTimeDao;
	}

	public void setAvgwaitTimeDao(AvgwaitTimeDAO avgwaitTimeDao) {
		this.avgwaitTimeDao = avgwaitTimeDao;
	}

	public GroupMemberDAO getGroupMemberDao() {
		return groupMemberDao;
	}

	public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
		this.groupMemberDao = groupMemberDao;
	}

}
