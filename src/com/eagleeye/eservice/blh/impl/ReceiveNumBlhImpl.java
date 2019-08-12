package com.eagleeye.eservice.blh.impl;

import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.blh.IReceiveNumBlh;
import com.eagleeye.eservice.dao.impl.ReceiveNumDAO;
import com.eagleeye.eservice.eo.ReplyStatOnDayEO;
import com.eagleeye.eservice.eo.ReplyStatOnDayEOId;
import com.eagleeye.user.dao.GroupMemberDAO;
import com.eagleeye.user.eo.GroupMemberEO;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiveNumBlhImpl implements IReceiveNumBlh {
    private Logger log = Logger.getLogger(ReceiveNumBlhImpl.class);
    GroupMemberDAO groupMemberDao;
    ReceiveNumDAO receiveNumDao;

//	@Override
//	public void loadReceiveNum(String serviceStaffId, Date startTime,
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
//		WangwangEserviceReceivenumGetRequest req = new WangwangEserviceReceivenumGetRequest();
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
//			WangwangEserviceReceivenumGetResponse response = client.execute(
//					req, sessionKey);
//			List<ReplyStatOnDay> replyStatOnDayList = response
//					.getReplyStatListOnDays();
//			// 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
//			// 最多重跑3次
//			if (replyStatOnDayList == null && response.getErrorCode() != null
//					&& TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
//				int count = 0;
//				Boolean needReRun = true;
//				while (count < 3 && needReRun) {
//					// 线程休眠半秒
//					Thread.sleep(500);
//					// count自增
//					count++;
//					response = client.execute(req, sessionKey);
//					replyStatOnDayList = response.getReplyStatListOnDays();
//					if (replyStatOnDayList != null
//							|| response.getErrorCode() == null) {
//						needReRun = false;
//					}
//				}
//				log.error(serviceStaffId + " " + sessionKey + " " + startTime
//						+ "-" + endTime + " ReplyStat Rerun time:" + count);
//			}
//			// end
//			Map<String, ReplyStatOnDayEO> receiveNumMap = new HashMap();
//			Map<String, Boolean> dateToRunChatPeers = new HashMap();
//			if (replyStatOnDayList != null) {
//				for (ReplyStatOnDay temp : replyStatOnDayList) {
//					for (ReplyStatById tempId : temp.getReplyStatByIds()) {
//						if (tempId.getReplyNum() != null) {
//							ReplyStatOnDayEO receiveNum = new ReplyStatOnDayEO();
//							ReplyStatOnDayEOId id = new ReplyStatOnDayEOId();
//							receiveNum.setCreatedAt(new Date());
//							id.setReplyDate(DateUtil.parse(temp.getReplyDate(),
//									DateUtil.datePatternYYMMDD));
//							// 2013.3.3, 存在的日期放入map
//							dateToRunChatPeers.put(temp.getReplyDate()
//									.toString(), true);
//							receiveNum.setReplyNum(tempId.getReplyNum());
//							String staffId = TaoBaoUtil
//									.removeEServicePrefix(serviceStaffId);
//							id.setServiceStaffId(staffId);
//							receiveNum.setUpdatedAt(new Date());
//							receiveNum.setManagerId(groupMember.get(0).getId()
//									.getManagerId());
//							receiveNum.setId(id);
//							if (receiveNumMap.get(id.getServiceStaffId()
//									.toString() + id.getReplyDate().toString()) == null) {
//								receiveNumMap.put(id.getServiceStaffId()
//										.toString()
//										+ id.getReplyDate().toString(),
//										receiveNum);
//								log.info(staffId + " " + " from t "
//										+ id.getServiceStaffId().toString()
//										+ id.getReplyDate().toString() + "\n");
//							}
//						}
//					}
//				}
//			}
//			Long dayGap = DateUtil.getDateGap(startTime, endTime);
//			Date start = startTime;
//			// for test
//			// dateToRunChatPeers.clear();
//			for (int i = 0; i <= dayGap; i++) {
//				if (dateToRunChatPeers.get(start.toString()) == null) {
//					String staffId = TaoBaoUtil
//							.removeEServicePrefix(serviceStaffId);
//					List<Map> data = receiveNumDao.getReplyNumByChatPeers(
//							staffId, start);
//					if (data != null && !data.isEmpty()) {
//						for (Map tmp : data) {
//							ReplyStatOnDayEO receiveNum = new ReplyStatOnDayEO();
//							ReplyStatOnDayEOId id = new ReplyStatOnDayEOId();
//							receiveNum.setCreatedAt(new Date());
//							id.setReplyDate(start);
//							receiveNum.setReplyNum((Long) tmp.get("num"));
//							id.setServiceStaffId(staffId);
//							receiveNum.setUpdatedAt(new Date());
//							receiveNum.setManagerId(groupMember.get(0).getId()
//									.getManagerId());
//							receiveNum.setId(id);
//							if (receiveNumMap.get(id) == null) {
//								receiveNumMap.put(id.getServiceStaffId()
//										.toString()
//										+ id.getReplyDate().toString(),
//										receiveNum);
//								log.info(staffId + " " + " from s"
//										+ id.getServiceStaffId().toString()
//										+ id.getReplyDate().toString() + "\n");
//							}
//						}
//					}
//				}
//				start = DateUtil.getNextDay(start, 1);
//			}
//			if (!receiveNumMap.isEmpty()) {
//				receiveNumDao.saveOrUpdateBatch(receiveNumMap.values());
//				log.info(startTime + "-" + endTime + ":" + serviceStaffId
//						+ "接待客户数目保存成功");
//			}
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}


    @Override
    public void loadReceiveNum(String serviceStaffId, Date startTime, Date endTime, String sessionKey) throws Exception {
        try {
            Long dayGap = DateUtil.getDateGap(startTime, endTime);
            Map<String, ReplyStatOnDayEO> receiveNumMap = new HashMap();
            Date start = startTime;
            List<GroupMemberEO> groupMember = groupMemberDao
                    .findByServiceStaffId(serviceStaffId);
            //		// 判断组员信息
            if (groupMember == null || groupMember.isEmpty()) {
                throw new Exception("没有此组员!");
            } else if (groupMember.size() != 1) {
                throw new Exception("此客服id不唯一！");
            }
            for (int i = 0; i <= dayGap; i++) {
                String staffId = TaoBaoUtil
                        .removeEServicePrefix(serviceStaffId);
                List<Map> data = receiveNumDao.getReplyNumByChatPeers(
                        staffId, start);
                if (data != null && !data.isEmpty()) {
                    for (Map tmp : data) {
                        ReplyStatOnDayEO receiveNum = new ReplyStatOnDayEO();
                        ReplyStatOnDayEOId id = new ReplyStatOnDayEOId();
                        receiveNum.setCreatedAt(new Date());
                        id.setReplyDate(start);
                        receiveNum.setReplyNum((Long) tmp.get("num"));
                        id.setServiceStaffId(staffId);
                        receiveNum.setUpdatedAt(new Date());
                        receiveNum.setManagerId(groupMember.get(0).getId()
                                .getManagerId());
                        receiveNum.setId(id);
                        if (receiveNumMap.get(id) == null) {
                            receiveNumMap.put(id.getServiceStaffId()
                                            .toString()
                                            + id.getReplyDate().toString(),
                                    receiveNum);
                            log.info(staffId + " " + " from s"
                                    + id.getServiceStaffId().toString()
                                    + id.getReplyDate().toString() + "\n");
                        }
                    }
                }
                start = DateUtil.getNextDay(start, 1);
            }
            if (!receiveNumMap.isEmpty()) {
                receiveNumDao.saveOrUpdateBatch(receiveNumMap.values());
                log.info(startTime + "-" + endTime + ":" + serviceStaffId
                        + "接待客户数目保存成功");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public List<ReplyStatOnDayEO> findAllByStaffId(String serviceStaffId) {
        // TODO Auto-generated method stub
        return receiveNumDao.findByServiceStaffId(serviceStaffId);
    }

    public GroupMemberDAO getGroupMemberDao() {
        return groupMemberDao;
    }

    public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
        this.groupMemberDao = groupMemberDao;
    }

    public ReceiveNumDAO getReceiveNumDao() {
        return receiveNumDao;
    }

    public void setReceiveNumDao(ReceiveNumDAO receiveNumDao) {
        this.receiveNumDao = receiveNumDao;
    }

}
