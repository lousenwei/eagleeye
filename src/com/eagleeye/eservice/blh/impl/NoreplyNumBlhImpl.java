package com.eagleeye.eservice.blh.impl;

import com.eagleeye.eservice.blh.INoreplyNumBlh;
import com.eagleeye.eservice.dao.impl.NoreplyNumDAO;
import com.eagleeye.eservice.eo.NonReplyStatOnDayEO;
import com.eagleeye.user.dao.GroupMemberDAO;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;


public class NoreplyNumBlhImpl implements INoreplyNumBlh {
	private Logger log = Logger.getLogger(NoreplyNumBlhImpl.class);
	GroupMemberDAO groupMemberDao;
	NoreplyNumDAO noreplyNumDao;

//	@Override
//	public void loadNoreplyNum(String serviceStaffId, Date startTime,
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
//		WangwangEserviceNoreplynumGetRequest req = new WangwangEserviceNoreplynumGetRequest();
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
//			WangwangEserviceNoreplynumGetResponse response = client.execute(
//					req, sessionKey);
//			List<NonReplyStatOnDay> NonReplyStatOnDayList = response
//					.getNonReplyStatOnDays();
//			// 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
//			// 最多重跑3次
//			if (NonReplyStatOnDayList == null
//					&& response.getErrorCode() != null
//					&& TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
//				int count = 0;
//				Boolean needReRun = true;
//				while (count < 3 && needReRun) {
//					// 线程休眠半秒
//					Thread.sleep(500);
//					// count自增
//					count++;
//					response = client.execute(req, sessionKey);
//					NonReplyStatOnDayList = response.getNonReplyStatOnDays();
//					if (NonReplyStatOnDayList != null
//							|| response.getErrorCode() == null) {
//						needReRun = false;
//					}
//				}
//				log.error(serviceStaffId + " " + sessionKey + " " + startTime
//						+ "-" + endTime + " NoreplyNum Rerun time:" + count);
//			}
//			// end
//			Map<NonReplyStatOnDayEOId, NonReplyStatOnDayEO> NoreplyNumMap = new HashMap();
//			if (NonReplyStatOnDayList != null) {
//				for (NonReplyStatOnDay temp : NonReplyStatOnDayList) {
//					if (temp.getNonreplyStatByIds() != null) {
//						for (NonreplyStatById tempId : temp
//								.getNonreplyStatByIds()) {
//							if (tempId.getNonReplyNum() != null) {
//								NonReplyStatOnDayEO noreplyNum = new NonReplyStatOnDayEO();
//								NonReplyStatOnDayEOId id = new NonReplyStatOnDayEOId();
//								String staffId = TaoBaoUtil
//										.removeEServicePrefix(tempId
//												.getServiceStaffId());
//								// String customIds = TaoBaoUtil
//								// .removeEServiceAllPrefix(tempId
//								// .getNonReplyCustomId());
//								noreplyNum.setCreatedAt(new Date());
//								id.setNonreplyDate(DateUtil.parse(temp
//										.getNonreplyDate(),DateUtil.datePatternYYMMDD));
//								id.setServiceStaffId(staffId);
//								noreplyNum.setNonReplyNum(tempId
//										.getNonReplyNum());
//								noreplyNum.setUpdatedAt(new Date());
//								noreplyNum.setManagerId(groupMember.get(0)
//										.getId().getManagerId());
//								noreplyNum.setId(id);
//								if (NoreplyNumMap.get(id) == null) {
//									NoreplyNumMap.put(id, noreplyNum);
//									// log.info(staffId + " " +
//									// temp.getNonreplyDate()
//									// + "\n");
//								}
//							}
//						}
//					}
//				}
//				if (!NoreplyNumMap.isEmpty()) {
//					noreplyNumDao.saveOrUpdateBatch(NoreplyNumMap.values());
//					log.info(startTime + "-" + endTime + ":" + serviceStaffId
//							+ "未回复对象保存成功");
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
	public void loadNoreplyNum(String serviceStaffId, Date startTime, Date endTime, String sessionKey) throws Exception {

	}

	@Override
	public List<NonReplyStatOnDayEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return noreplyNumDao.findByServiceStaffId(serviceStaffId);
	}

	public GroupMemberDAO getGroupMemberDao() {
		return groupMemberDao;
	}

	public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
		this.groupMemberDao = groupMemberDao;
	}

	public NoreplyNumDAO getNoreplyNumDao() {
		return noreplyNumDao;
	}

	public void setNoreplyNumDao(NoreplyNumDAO noreplyNumDao) {
		this.noreplyNumDao = noreplyNumDao;
	}

}
