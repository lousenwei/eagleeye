package com.eagleeye.eservice.bsh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.blh.IAvgwaitTimeBlh;
import com.eagleeye.eservice.blh.IChatPeersBlh;
import com.eagleeye.eservice.blh.INoreplyNumBlh;
import com.eagleeye.eservice.blh.IOnlineTimeBlh;
import com.eagleeye.eservice.blh.IReceiveNumBlh;
import com.eagleeye.eservice.blh.IRefundBlh;
import com.eagleeye.eservice.blh.ITradeRateBlh;
import com.eagleeye.eservice.blh.ITradeSoldBlh;
import com.eagleeye.eservice.blh.IUpdateLogBlh;
import com.eagleeye.eservice.bsh.IEServiceBsh;
import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.eo.UpdatelogEO;
import com.eagleeye.eservice.eo.UpdatelogEOId;
import com.eagleeye.statistics.blh.IBelongTypeBlh;
import com.eagleeye.statistics.blh.IStatAchievementBlh;
import com.eagleeye.statistics.blh.ITradeStatBlh;
import com.eagleeye.user.blh.IGroupMemberBlh;
import com.eagleeye.user.blh.IManagerInfoBlh;
import com.eagleeye.user.eo.GroupMemberEO;

public class EServiceBshImpl implements IEServiceBsh {
	private Logger log = Logger.getLogger(EServiceBshImpl.class);
	IAvgwaitTimeBlh avgwaitTimeBlh;
	IChatPeersBlh chatPeersBlh;
	INoreplyNumBlh noreplyNumBlh;
	IOnlineTimeBlh onlineTimeBlh;
	IReceiveNumBlh receiveNumBlh;
	ITradeSoldBlh tradeSoldBlh;
	IRefundBlh refundBlh;
	ITradeStatBlh tradeStatBlh;
	IManagerInfoBlh managerInfoBlh;
	IUpdateLogBlh updateLogBlh;
	IGroupMemberBlh groupMemberBlh;
	IStatAchievementBlh statAchievementBlh;
	IBelongTypeBlh belongTypeBlh;
	ITradeRateBlh tradeRateBlh;

	/**
	 * 导入平均等待时间全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllAvgWaitTimeDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start,
			Date end, String sessionKey) {
		// TODO Auto-generated method stub
		try {
			List<GroupMemberEO> members = groupMemberBlh.getGroupMemebersByManagerId(managerId);
			Boolean needDoLog = false;
			if (serviceStaffId != null) {
				for (int i = 0; i < members.size(); i++) {
					if (!members.get(i).getId().getServiceStaffId().equalsIgnoreCase(serviceStaffId)) {
						members.remove(i);
					}
				}
			}
			// 旺旺数据
			for (GroupMemberEO tempMember : members) {
				Date tempStart = start;
				Date tempEnd = DateUtil.getNextDay(tempStart, (EServiceConstants.UPDATE_TIME_END_GAP - 1));
				while (!tempEnd.after(end)) {
					// avgwaitTimeBlh.findAllByStaffId("maxine_111");
					avgwaitTimeBlh.loadAvgwaitTime(tempMember.getId().getServiceStaffId(), tempStart, tempEnd,
							sessionKey);
					tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
					tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
					needDoLog = true;
				}
				if (!tempStart.after(end)) {
					avgwaitTimeBlh.loadAvgwaitTime(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					needDoLog = true;
				}
			}
			if (needDoLog) {
				// 首次响应时间日志
				UpdatelogEO avgWaitTimeLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_AVGWAITTIME, end);
				updateLogBlh.saveUpdateLog(avgWaitTimeLog);
			}
		} catch (Exception e) {
			// UpdatelogEO avgWaitTimeLog = updateLogBlh.doUpdateLog(managerId,
			// EServiceConstants.UPDATE_LOG_ITEM_AVGWAITTIME, start);
			// updateLogBlh.saveUpdateLog(avgWaitTimeLog);
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}
	}

	/**
	 * 导入聊天对象全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllChatPeersDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start,
			Date end, String sessionKey) {

		// TODO Auto-generated method stub
		try {
			List<GroupMemberEO> members = groupMemberBlh.getGroupMemebersByManagerId(managerId);
			Boolean needDoLog = false;
			if (serviceStaffId != null) {
				for (int i = 0; i < members.size(); i++) {
					if (!members.get(i).getId().getServiceStaffId().equalsIgnoreCase(serviceStaffId)) {
						members.remove(i);
					}
				}
			}
			// 旺旺数据
			for (GroupMemberEO tempMember : members) {
				Date tempStart = start;
				Date tempEnd = DateUtil.getNextDay(tempStart, (EServiceConstants.UPDATE_TIME_END_GAP - 1));
				while (!tempEnd.after(end)) {
					// avgwaitTimeBlh.findAllByStaffId("maxine_111");
					chatPeersBlh.loadChatPeers(tempMember.getId().getServiceStaffId(), tempStart, tempEnd, sessionKey);
					tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
					tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
					needDoLog = true;
				}
				if (!tempStart.after(end)) {
					chatPeersBlh.loadChatPeers(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					needDoLog = true;
				}
			}
			if (needDoLog) {
				// 聊天对象日志
				UpdatelogEO chatPeersLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_CHATPEERS, end);
				// 记录更新日志表
				updateLogBlh.saveUpdateLog(chatPeersLog);
			}
		} catch (Exception e) {
			// UpdatelogEO chatPeersLog = updateLogBlh.doUpdateLog(managerId,
			// EServiceConstants.UPDATE_LOG_ITEM_CHATPEERS, start);
			// // 记录更新日志表
			// updateLogBlh.saveUpdateLog(chatPeersLog);
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}

	}

	/**
	 * 导入未回复对象全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllNoReplyNumDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start,
			Date end, String sessionKey) {

		// TODO Auto-generated method stub
		try {
			List<GroupMemberEO> members = groupMemberBlh.getGroupMemebersByManagerId(managerId);
			Boolean needDoLog = false;
			if (serviceStaffId != null) {
				for (int i = 0; i < members.size(); i++) {
					if (!members.get(i).getId().getServiceStaffId().equalsIgnoreCase(serviceStaffId)) {
						members.remove(i);
					}
				}
			}
			// 旺旺数据
			for (GroupMemberEO tempMember : members) {
				Date tempStart = start;
				Date tempEnd = DateUtil.getNextDay(tempStart, (EServiceConstants.UPDATE_TIME_END_GAP - 1));
				while (!tempEnd.after(end)) {
					noreplyNumBlh
							.loadNoreplyNum(tempMember.getId().getServiceStaffId(), tempStart, tempEnd, sessionKey);
					tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
					tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
					needDoLog = true;
				}
				if (!tempStart.after(end)) {
					noreplyNumBlh.loadNoreplyNum(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					needDoLog = true;
				}
			}
			if (needDoLog) {
				// 未回复对象日志
				UpdatelogEO NoreplyNumLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_NONREPLYNUM, end);
				updateLogBlh.saveUpdateLog(NoreplyNumLog);

			}
		} catch (Exception e) {
			// // 未回复对象日志
			// UpdatelogEO NoreplyNumLog = updateLogBlh.doUpdateLog(managerId,
			// EServiceConstants.UPDATE_LOG_ITEM_NONREPLYNUM, start);
			// updateLogBlh.saveUpdateLog(NoreplyNumLog);
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}

	}

	/**
	 * 导入在线时间全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllOnlineTimeDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start,
			Date end, String sessionKey) {

		// TODO Auto-generated method stub
		try {
			List<GroupMemberEO> members = groupMemberBlh.getGroupMemebersByManagerId(managerId);
			Boolean needDoLog = false;
			if (serviceStaffId != null) {
				for (int i = 0; i < members.size(); i++) {
					if (!members.get(i).getId().getServiceStaffId().equalsIgnoreCase(serviceStaffId)) {
						members.remove(i);
					}
				}
			}
			// 旺旺数据
			for (GroupMemberEO tempMember : members) {
				Date tempStart = start;
				Date tempEnd = DateUtil.getNextDay(tempStart, (EServiceConstants.UPDATE_TIME_END_GAP - 1));
				while (!tempEnd.after(end)) {
					onlineTimeBlh
							.loadOnlineTime(tempMember.getId().getServiceStaffId(), tempStart, tempEnd, sessionKey);
					tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
					tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
					needDoLog = true;
				}
				if (!tempStart.after(end)) {
					onlineTimeBlh.loadOnlineTime(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					needDoLog = true;
				}
			}
			if (needDoLog) {
				// 在线时间日志
				UpdatelogEO onlineTimeLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_ONLINETIME, end);
				updateLogBlh.saveUpdateLog(onlineTimeLog);
			}
		} catch (Exception e) {
			// UpdatelogEO onlineTimeLog = updateLogBlh.doUpdateLog(managerId,
			// EServiceConstants.UPDATE_LOG_ITEM_ONLINETIME, start);
			// updateLogBlh.saveUpdateLog(onlineTimeLog);
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}

	}

	/**
	 * 导入接待数目全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllReceiveNumDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start,
			Date end, String sessionKey) {

		// TODO Auto-generated method stub
		try {
			List<GroupMemberEO> members = groupMemberBlh.getGroupMemebersByManagerId(managerId);
			Boolean needDoLog = false;
			if (serviceStaffId != null) {
				for (int i = 0; i < members.size(); i++) {
					if (!members.get(i).getId().getServiceStaffId().equalsIgnoreCase(serviceStaffId)) {
						members.remove(i);
					}
				}
			}
			// 旺旺数据
			for (GroupMemberEO tempMember : members) {
				Date tempStart = start;
				Date tempEnd = DateUtil.getNextDay(tempStart, (EServiceConstants.UPDATE_TIME_END_GAP - 1));
				while (!tempEnd.after(end)) {
					receiveNumBlh
							.loadReceiveNum(tempMember.getId().getServiceStaffId(), tempStart, tempEnd, sessionKey);
					tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
					tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
					needDoLog = true;
				}
				if (!tempStart.after(end)) {
					receiveNumBlh.loadReceiveNum(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					needDoLog = true;
				}
			}
			if (needDoLog) {
				// 接待数目日志
				UpdatelogEO receiveNumLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_RECEIVENUM, end);
				updateLogBlh.saveUpdateLog(receiveNumLog);
			}
		} catch (Exception e) {
			// 接待数目日志
			// UpdatelogEO receiveNumLog = updateLogBlh.doUpdateLog(managerId,
			// EServiceConstants.UPDATE_LOG_ITEM_RECEIVENUM, start);
			// updateLogBlh.saveUpdateLog(receiveNumLog);
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}
	}

	/**
	 * 导入交易信息全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllTradeDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey) {
		// TODO Auto-generated method stub
		try {
			// TODO：如果serviceStaffId!=ALL，则代表是后面添加，所以无需更新交易数据
			if (serviceStaffId == null) {
				// 导入交易数据
				tradeSoldBlh.loadTradeInfo(managerId, start, (Date) DateUtil.getDateRange(end).get("DATEEND"),
						sessionKey);

				// 导入交易数据日志
				UpdatelogEO tradeInfoLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_TRADEINFO, end);
				updateLogBlh.saveUpdateLog(tradeInfoLog);
			}
		} catch (Exception e) {
			// UpdatelogEO tradeInfoLog = updateLogBlh.doUpdateLog(managerId,
			// EServiceConstants.UPDATE_LOG_ITEM_TRADEINFO, start);
			// updateLogBlh.saveUpdateLog(tradeInfoLog);
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}

	}

	/**
	 * 导入交易评价信息全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllTradeRateDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start,
			Date end, String sessionKey) {
		// TODO Auto-generated method stub
		try {
			// TODO:如果serviceStaffId!=null，则代表是添加客服，无需更新
			if (serviceStaffId == null) {
				// 导入交易评价数据,差评
				tradeRateBlh.loadTradeRateIncrementInfo(managerId, start,
						(Date) DateUtil.getDateRange(end).get("DATEEND"), sessionKey, EServiceConstants.TRADE_RATE_BAD);
				// 导入交易评价数据，中评
				tradeRateBlh.loadTradeRateIncrementInfo(managerId, start,
						(Date) DateUtil.getDateRange(end).get("DATEEND"), sessionKey,
						EServiceConstants.TRADE_RATE_NEUTRAL);
				// 导入交易评价数据日志
				UpdatelogEO tradeRateLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_TRADERATE, end);
				updateLogBlh.saveUpdateLog(tradeRateLog);
			}
		} catch (Exception e) {
			// // 导入退款数据日志
			// UpdatelogEO refundLog = updateLogBlh.doUpdateLog(managerId,
			// EServiceConstants.UPDATE_LOG_ITEM_REFUND, start);
			// updateLogBlh.saveUpdateLog(refundLog);
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}
	}

	/**
	 * 导入退款信息全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllRefundDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start,
			Date end, String sessionKey) {

		// TODO Auto-generated method stub
		try {
			// TODO:如果serviceStaffId!=null，则代表是添加客服，无需更新
			if (serviceStaffId == null) {
				// 导入退款数据
				refundBlh.loadRefund(managerId, start, (Date) DateUtil.getDateRange(end).get("DATEEND"), sessionKey);

				// 导入退款数据日志
				UpdatelogEO refundLog = updateLogBlh.doUpdateLog(managerId, EServiceConstants.UPDATE_LOG_ITEM_REFUND,
						end);
				updateLogBlh.saveUpdateLog(refundLog);
			}
		} catch (Exception e) {
			// // 导入退款数据日志
			// UpdatelogEO refundLog = updateLogBlh.doUpdateLog(managerId,
			// EServiceConstants.UPDATE_LOG_ITEM_REFUND, start);
			// updateLogBlh.saveUpdateLog(refundLog);
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}

	}

	@Override
	public void loadAllDataByManagerIdandTimePreiod(String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey) {
		// TODO Auto-generated method stub
		try {
			// 更新日志对象
			List<UpdatelogEO> updateLogs = new ArrayList();
			List<GroupMemberEO> members = groupMemberBlh.getGroupMemebersByManagerId(managerId);
			Boolean needDoLog = false;
			if (serviceStaffId != null) {
				for (int i = 0; i < members.size(); i++) {
					if (!members.get(i).getId().getServiceStaffId().equalsIgnoreCase(serviceStaffId)) {
						members.remove(i);
					}
				}
			}
			// 旺旺数据
			for (GroupMemberEO tempMember : members) {
				Date tempStart = start;
				Date tempEnd = DateUtil.getNextDay(tempStart, (EServiceConstants.UPDATE_TIME_END_GAP - 1));
				while (!tempEnd.after(end)) {
					// avgwaitTimeBlh.findAllByStaffId("maxine_111");
					avgwaitTimeBlh.loadAvgwaitTime(tempMember.getId().getServiceStaffId(), tempStart, tempEnd,
							sessionKey);
					chatPeersBlh.loadChatPeers(tempMember.getId().getServiceStaffId(), tempStart, tempEnd, sessionKey);
					noreplyNumBlh
							.loadNoreplyNum(tempMember.getId().getServiceStaffId(), tempStart, tempEnd, sessionKey);
					onlineTimeBlh
							.loadOnlineTime(tempMember.getId().getServiceStaffId(), tempStart, tempEnd, sessionKey);
					receiveNumBlh
							.loadReceiveNum(tempMember.getId().getServiceStaffId(), tempStart, tempEnd, sessionKey);
					tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
					tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
					needDoLog = true;
				}
				if (!tempStart.after(end)) {
					avgwaitTimeBlh.loadAvgwaitTime(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					chatPeersBlh.loadChatPeers(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					noreplyNumBlh.loadNoreplyNum(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					onlineTimeBlh.loadOnlineTime(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					receiveNumBlh.loadReceiveNum(tempMember.getId().getServiceStaffId(), tempStart, end, sessionKey);
					needDoLog = true;
				}
			}
			if (needDoLog) {
				// 首次响应时间日志
				UpdatelogEO avgWaitTimeLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_AVGWAITTIME, end);
				updateLogs.add(avgWaitTimeLog);

				// 聊天对象日志
				UpdatelogEO chatPeersLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_CHATPEERS, end);
				updateLogs.add(chatPeersLog);

				// 未回复对象日志
				UpdatelogEO NoreplyNumLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_NONREPLYNUM, end);
				updateLogs.add(NoreplyNumLog);

				// 在线时间日志
				UpdatelogEO onlineTimeLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_ONLINETIME, end);
				updateLogs.add(onlineTimeLog);

				// 接待数目日志
				UpdatelogEO receiveNumLog = updateLogBlh.doUpdateLog(managerId,
						EServiceConstants.UPDATE_LOG_ITEM_RECEIVENUM, end);
				updateLogs.add(receiveNumLog);
			}
			// 导入交易数据
			tradeSoldBlh.loadTradeInfo(managerId, start, (Date) DateUtil.getDateRange(end).get("DATEEND"), sessionKey);

			// 导入退款数据
			refundBlh.loadRefund(managerId, start, (Date) DateUtil.getDateRange(end).get("DATEEND"), sessionKey);

			// 导入交易数据日志
			UpdatelogEO tradeInfoLog = updateLogBlh.doUpdateLog(managerId, EServiceConstants.UPDATE_LOG_ITEM_TRADEINFO,
					end);
			updateLogs.add(tradeInfoLog);

			// 导入退款数据日志
			//UpdatelogEOId refundLogId = new UpdatelogEOId(managerId, EServiceConstants.UPDATE_LOG_ITEM_REFUND);
			UpdatelogEO refundLog = updateLogBlh.doUpdateLog(managerId, EServiceConstants.UPDATE_LOG_ITEM_REFUND,
					end);

			updateLogs.add(refundLog);
			// 记录更新日志表
			updateLogBlh.saveUpdateLogs(updateLogs);
		} catch (Exception e) {
			log.error(managerId + " " + start + "-" + end + "\n" + e);
		}
	}

	public List<TradeEO> loadIncrementDataByManagerIdandTimePreiod(String managerId, Date start, Date end,
			String sessionKey, UpdatelogEO update) {
		// TODO Auto-generated method stub
		List<UpdatelogEO> oldUpdateLogs = new ArrayList();
		if (update == null) {
			oldUpdateLogs = updateLogBlh.getUpdateLogs(managerId);
		} else {
			oldUpdateLogs.add(update);
		}
		List<TradeEO> trades = new ArrayList();
		List<GroupMemberEO> members = groupMemberBlh.getGroupMemebersByManagerId(managerId);
		if (oldUpdateLogs != null && !oldUpdateLogs.isEmpty()) {
			for (UpdatelogEO temp : oldUpdateLogs) {
				try {
					if (EServiceConstants.UPDATE_LOG_ITEM_AVGWAITTIME.equals(temp.getId().getItem())) {
						for (GroupMemberEO tempMember : members) {
							Date tempStart = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_START_GAP);
							Date tempEnd = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_END_GAP);
							// 循环取数据
							while (!tempEnd.after(end)) {
								avgwaitTimeBlh.loadAvgwaitTime(tempMember.getId().getServiceStaffId(), tempStart,
										tempEnd, sessionKey);
								tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
								tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
							}
							// 跳出循环后，最后一次更新数据
							if (!tempStart.after(end)) {
								avgwaitTimeBlh.loadAvgwaitTime(tempMember.getId().getServiceStaffId(), tempStart, end,
										sessionKey);

							}
						}
						temp.setUpdateTime(end);
					} else if (EServiceConstants.UPDATE_LOG_ITEM_CHATPEERS.equals(temp.getId().getItem())) {
						for (GroupMemberEO tempMember : members) {
							Date tempStart = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_START_GAP);
							Date tempEnd = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_END_GAP);
							// 循环取数据
							while (!tempEnd.after(end)) {
								chatPeersBlh.loadChatPeers(tempMember.getId().getServiceStaffId(), tempStart, tempEnd,
										sessionKey);
								tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
								tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
							}
							// 跳出循环后，最后一次更新数据
							if (!tempStart.after(end)) {
								chatPeersBlh.loadChatPeers(tempMember.getId().getServiceStaffId(), tempStart, end,
										sessionKey);
							}
						}
						temp.setUpdateTime(end);
					} else if (EServiceConstants.UPDATE_LOG_ITEM_NONREPLYNUM.equals(temp.getId().getItem())) {
						for (GroupMemberEO tempMember : members) {
							Date tempStart = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_START_GAP);
							Date tempEnd = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_END_GAP);
							// 循环取数据
							while (!tempEnd.after(end)) {
								noreplyNumBlh.loadNoreplyNum(tempMember.getId().getServiceStaffId(), tempStart,
										tempEnd, sessionKey);
								tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
								tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
							}
							// 跳出循环后，最后一次更新数据
							if (!tempStart.after(end)) {
								noreplyNumBlh.loadNoreplyNum(tempMember.getId().getServiceStaffId(), tempStart, end,
										sessionKey);
							}
						}
						temp.setUpdateTime(end);
					} else if (EServiceConstants.UPDATE_LOG_ITEM_ONLINETIME.equals(temp.getId().getItem())) {
						for (GroupMemberEO tempMember : members) {
							Date tempStart = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_START_GAP);
							Date tempEnd = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_END_GAP);
							// 循环取数据
							while (!tempEnd.after(end)) {
								onlineTimeBlh.loadOnlineTime(tempMember.getId().getServiceStaffId(), tempStart,
										tempEnd, sessionKey);
								tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
								tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
							}
							// 跳出循环后，最后一次更新数据
							if (!tempStart.after(end)) {
								onlineTimeBlh.loadOnlineTime(tempMember.getId().getServiceStaffId(), tempStart, end,
										sessionKey);
							}
						}
						temp.setUpdateTime(end);
					} else if (EServiceConstants.UPDATE_LOG_ITEM_RECEIVENUM.equals(temp.getId().getItem())) {
						for (GroupMemberEO tempMember : members) {
							Date tempStart = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_START_GAP);
							Date tempEnd = DateUtil.getNextDay(temp.getUpdateTime(),
									EServiceConstants.UPDATE_TIME_END_GAP);
							// 循环取数据
							while (!tempEnd.after(end)) {
								receiveNumBlh.loadReceiveNum(tempMember.getId().getServiceStaffId(), tempStart,
										tempEnd, sessionKey);
								tempStart = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
								tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_END_GAP);
							}
							// 跳出循环后，最后一次更新数据
							if (!tempStart.after(end)) {
								receiveNumBlh.loadReceiveNum(tempMember.getId().getServiceStaffId(), tempStart, end,
										sessionKey);
							}
						}
						temp.setUpdateTime(end);
					} else if (EServiceConstants.UPDATE_LOG_ITEM_REFUND.equals(temp.getId().getItem())) {
						Date tempStart = DateUtil.getNextSecond(temp.getUpdateTime());
						Date tempEnd = DateUtil.getNextDay(tempStart, EServiceConstants.UPDATE_TIME_START_GAP);
						Date realEnd = end;
						// 循环取数据
						while (!tempEnd.after(realEnd)) {
							refundBlh.loadRefund(managerId, tempStart, tempEnd, sessionKey);
							tempStart = tempEnd;
							tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
						}
						// 跳出循环后，最后一次更新数据
						if (!tempStart.after(realEnd)) {
							refundBlh.loadRefund(managerId, tempStart, realEnd, sessionKey);
							temp.setUpdateTime(end);
						}
					} else if (EServiceConstants.UPDATE_LOG_ITEM_TRADEINFO.equals(temp.getId().getItem())) {
						Date tempStart = DateUtil.getNextSecond(temp.getUpdateTime());
						Date tempEnd = DateUtil.getNextDay(tempStart, EServiceConstants.UPDATE_TIME_START_GAP);
						Date realEnd = end;
						// 循环取数据
						while (!tempEnd.after(realEnd)) {
							List<TradeEO> newTrades = tradeSoldBlh.loadTradeIncrementInfo(managerId, tempStart,
									tempEnd, sessionKey);
							tempStart = tempEnd;
							tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
							if (newTrades != null && !newTrades.isEmpty()) {
								trades.addAll(newTrades);
							}
						}
						// 跳出循环后，最后一次更新数据
						if (!tempStart.after(realEnd)) {
							List<TradeEO> newTrades = tradeSoldBlh.loadTradeIncrementInfo(managerId, tempStart,
									realEnd, sessionKey);
							if (newTrades != null && !newTrades.isEmpty()) {
								trades.addAll(newTrades);
							}
							temp.setUpdateTime(end);
						}
					} else if (EServiceConstants.UPDATE_LOG_ITEM_TRADERATE.equals(temp.getId().getItem())) {
						// TODO:增加评价管理
						Date tempStart = DateUtil.getNextSecond(temp.getUpdateTime());
						Date tempEnd = DateUtil.getNextDay(tempStart, EServiceConstants.UPDATE_TIME_START_GAP);
						Date realEnd = end;
						// 循环取数据
						while (!tempEnd.after(realEnd)) {
							// 中评
							tradeRateBlh.loadTradeRateIncrementInfo(managerId, tempStart, tempEnd, sessionKey,
									EServiceConstants.TRADE_RATE_NEUTRAL);
							// 差评
							tradeRateBlh.loadTradeRateIncrementInfo(managerId, tempStart, tempEnd, sessionKey,
									EServiceConstants.TRADE_RATE_BAD);
							tempStart = tempEnd;
							tempEnd = DateUtil.getNextDay(tempEnd, EServiceConstants.UPDATE_TIME_START_GAP);
						}
						// 跳出循环后，最后一次更新数据
						if (!tempStart.after(realEnd)) {
							// // 好评
							// tradeRateBlh.loadTradeRateIncrementInfo(managerId,
							// tempStart, realEnd, sessionKey,
							// EServiceConstants.TRADE_RATE_GOOD);
							// 中评
							tradeRateBlh.loadTradeRateIncrementInfo(managerId, tempStart, realEnd, sessionKey,
									EServiceConstants.TRADE_RATE_NEUTRAL);
							// 差评
							tradeRateBlh.loadTradeRateIncrementInfo(managerId, tempStart, realEnd, sessionKey,
									EServiceConstants.TRADE_RATE_BAD);
							temp.setUpdateTime(end);
						}
					}
				} catch (Exception e) {
					log.error(managerId + " " + start + "-" + end + "\n" + e);
				}
			}
			if (oldUpdateLogs != null & !oldUpdateLogs.isEmpty()) {
				updateLogBlh.saveUpdateLogs(oldUpdateLogs);
				if (trades != null && !trades.isEmpty()) {
					return trades;
				}
			}
		}
		return null;
	}

	/**
	 * 生成交易客服业绩归属
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 */
	public void runTradePersonalStatByDay(String managerId, String sessionKey, List<TradeEO> needAssignTrades) {
		try {
			// 得到业务归属类型；默认值为，付款前48小时内第一个接触的
			String type = belongTypeBlh.getBelongTypeByManagerId(managerId);
			log.info("业绩归属方式：" + type);
			tradeStatBlh.runTradePersonalStatByDay(managerId, sessionKey, needAssignTrades, type);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void runTradePersonalStatByDay(String managerId, String sessionKey, Date start, Date end, Date modifyStart,
			Date modifyEnd) {
		// 得到业务归属类型；默认值为，付款前48小时内第一个接触的
		String type = belongTypeBlh.getBelongTypeByManagerId(managerId);
		log.info("业绩归属方式：" + type);
		tradeStatBlh.runTradePersonalStatByDay(managerId, start, end, sessionKey, modifyStart, modifyEnd, type);
	}

	public void runPersonalStatAchievementReportByTime(String managerId, String shopId, Date start, Date end) {
		// 生成客服业绩指标报表
		tradeStatBlh.calulateTradeOwnersStatByDay(managerId, start, end, shopId);
		// 生成客服销售数量报表
		tradeStatBlh.calulateTradeOwnersStatNumByDay(managerId, start, end);
	}

	public void runShopStatAchievementReportByTime(String managerId, String shopId, Date start, Date end) {
		// 根据客服数据，生成店铺业绩指标报表
		// tradeStatBlh.calulateTradeTotalStatByDay(managerId,
		// start, end, shopId);
		tradeStatBlh.calulateTradeSumStatByDay(managerId, start, end, shopId);
		
		tradeStatBlh.calulateTradeNumSumStatByDay(managerId, start, end, shopId);
	}

	public void runPersonalStatAchievementReport(String managerId, String shopId) {
		Date tempStart = DateUtil.getPreviousDay(new Date(), EagleConstants.UPDATE_GAP);
		Date tempEnd = new Date();
		// 生成客服业绩指标报表
		tradeStatBlh.calulateTradeOwnersStatByDay(managerId, tempStart, tempEnd, shopId);
		// 生成客服销售数量报表
		tradeStatBlh.calulateTradeOwnersStatNumByDay(managerId, tempStart, tempEnd);
	}

	public void runShopStatAchievementReport(String managerId, String shopId) {
		Date tempStart = DateUtil.getSimpleDate(DateUtil.getLastMonth(new Date()));
		Date tempEnd = new Date();

		// 根据客服数据，生成店铺业绩指标报表
		// tradeStatBlh.calulateTradeTotalStatByDay(managerId,
		// start, end, shopId);
		tradeStatBlh.calulateTradeSumStatByDay(managerId, tempStart, tempEnd, shopId);
		
		tradeStatBlh.calulateTradeNumSumStatByDay(managerId, tempStart, tempEnd, shopId);
	}

	public void runPersonalStatAchievementReport(String managerId, String shopId, Date start, Date end) {
		// 生成客服业绩指标报表
		tradeStatBlh.calulateTradeOwnersStatByDay(managerId, start, end, shopId);
		// 生成客服销售数量报表
		tradeStatBlh.calulateTradeOwnersStatNumByDay(managerId, start, end);

	}

	public void runShopStatAchievementReport(String managerId, String shopId, Date start, Date end) {
		// 根据客服数据，生成店铺业绩指标报表
		// tradeStatBlh.calulateTradeTotalStatByDay(managerId,
		// start, end, shopId);
		tradeStatBlh.calulateTradeSumStatByDay(managerId, start, end, shopId);

		tradeStatBlh.calulateTradeNumSumStatByDay(managerId, start, end, shopId);
	}

	/**
	 * 根据日期跑业绩报表
	 * 
	 * @param managerId
	 * @param sateDates
	 * @param shopId
	 */
	public void runPersonalStatAchievementReportByDate(String managerId, Set<Date> sateDates, String shopId) {
		if (sateDates != null && !sateDates.isEmpty()) {
			// TODO: run报表
			// String managerId = (String) SessionManager
			// .getSessionByKey(EagleConstants.TOPMANAGERID);
			// Map<Date, Boolean> hasUpdate = new HashMap();
			for (Date temp : sateDates) {
				if (temp != null)
				// && temp.getPayTime().before(
				// DateUtil.getSimpleDate(new Date())))
				{
					log.info(temp + " " + managerId);
					Date tempStart = (Date) DateUtil.getDateRange(temp).get("DATEBEGIN");
					Date tempEnd = (Date) DateUtil.getDateRange(temp).get("DATEEND");
					// 生成客服业绩指标报表
					tradeStatBlh.calulateTradeOwnersStatByDay(managerId, tempStart, tempEnd, shopId);
					// 生成客服销售数量报表
					tradeStatBlh.calulateTradeOwnersStatNumByDay(managerId, tempStart, tempEnd);
				}
			}
		}
	}

	/**
	 * 根据日期跑业绩报表
	 * 
	 * @param managerId
	 * @param sateDates
	 * @param shopId
	 */
	public void runShopStatAchievementReportByDate(String managerId, Set<Date> sateDates, String shopId) {
		if (sateDates != null && !sateDates.isEmpty()) {
			// TODO: run报表
			// String managerId = (String) SessionManager
			// .getSessionByKey(EagleConstants.TOPMANAGERID);
			// Map<Date, Boolean> hasUpdate = new HashMap();
			for (Date temp : sateDates) {
				if (temp != null)
				// && temp.getPayTime().before(
				// DateUtil.getSimpleDate(new Date())))
				{
					log.info(temp + " " + managerId);
					Date tempStart = (Date) DateUtil.getDateRange(temp).get("DATEBEGIN");
					Date tempEnd = (Date) DateUtil.getDateRange(temp).get("DATEEND");
					// 根据客服数据，生成店铺业绩指标报表
					// tradeStatBlh.calulateTradeTotalStatByDay(managerId,
					// start, end, shopId);
					tradeStatBlh.calulateTradeSumStatByDay(managerId, tempStart, tempEnd, shopId);
					
					tradeStatBlh.calulateTradeNumSumStatByDay(managerId, tempStart, tempEnd, shopId);
				}
			}
		}
	}

	public void runPersonalStatAchievementReport(String managerId, List<TradeEO> trades, String shopId) {
		if (trades != null && !trades.isEmpty()) {
			// TODO: run报表
			// String managerId = (String) SessionManager
			// .getSessionByKey(EagleConstants.TOPMANAGERID);
			Map<Date, Boolean> hasUpdate = new HashMap();
			for (TradeEO temp : trades) {
				if (temp.getPayTime() != null)
				// && temp.getPayTime().before(
				// DateUtil.getSimpleDate(new Date())))
				{
					log.info(temp.getTid().toString() + " " + temp.getPayTime() + " " + temp.getBuyerNick());
					Date tempStart = (Date) DateUtil.getDateRange(temp.getPayTime()).get("DATEBEGIN");
					Date tempEnd = (Date) DateUtil.getDateRange(temp.getPayTime()).get("DATEEND");
					if (hasUpdate.get(tempStart) == null) {

						// 生成客服业绩指标报表
						tradeStatBlh.calulateTradeOwnersStatByDay(managerId, tempStart, tempEnd, shopId);
						// 生成客服销售数量报表
						tradeStatBlh.calulateTradeOwnersStatNumByDay(managerId, tempStart, tempEnd);

						hasUpdate.put(tempStart, true);
					}
				}
			}
		}
	}

	public void runShopStatAchievementReport(String managerId, List<TradeEO> trades, String shopId) {
		if (trades != null && !trades.isEmpty()) {
			// TODO: run报表
			// String managerId = (String) SessionManager
			// .getSessionByKey(EagleConstants.TOPMANAGERID);
			Map<Date, Boolean> hasUpdate = new HashMap();
			for (TradeEO temp : trades) {
				if (temp.getPayTime() != null)
				// && temp.getPayTime().before(
				// DateUtil.getSimpleDate(new Date())))
				{
					log.info(temp.getTid().toString() + " " + temp.getPayTime() + " " + temp.getBuyerNick());
					Date tempStart = (Date) DateUtil.getDateRange(temp.getPayTime()).get("DATEBEGIN");
					Date tempEnd = (Date) DateUtil.getDateRange(temp.getPayTime()).get("DATEEND");
					if (hasUpdate.get(tempStart) == null) {
						// 根据客服数据，生成店铺业绩指标报表
						// tradeStatBlh.calulateTradeTotalStatByDay(managerId,
						// start, end, shopId);
						tradeStatBlh.calulateTradeSumStatByDay(managerId, tempStart, tempEnd, shopId);

						tradeStatBlh.calulateTradeNumSumStatByDay(managerId, tempStart, tempEnd, shopId);
						hasUpdate.put(tempStart, true);
					}
				}
			}
		}
	}

	/**
	 * 将本月交易归属人设置为null
	 * 
	 * @param managerId
	 */
	public int setCurrentMonthTradeOwnerToNull(String managerId) {
		int i = tradeSoldBlh.setCurrentMonthTradeOwnerToNull(managerId);
		return i;
	}

	/**
	 * 删除对应统计数据
	 * 
	 * @param managerId
	 * @param shopId
	 * @param statDay
	 * @throws Exception
	 */
	public void deleteTradeStatByDays(String managerId, String shopId, Set<Date> statDays) {
		for (Date statDay : statDays) {
			statAchievementBlh.deleteTradeStatByDays(managerId, shopId, statDay);
		}
	}

	/**
	 * 根据refundid更新支付日期
	 * 
	 * @param refundId
	 * @param payTime
	 * @throws Exception
	 */
	public void updateRefundPayTime(Map<Long, Date> refundDates) {
		for (Long id : refundDates.keySet()) {
			refundBlh.updateRefundPayTime(id, refundDates.get(id));
			log.info("RefundId:" + id + " PayTime:" + refundDates.get(id));
		}
	}

	public IAvgwaitTimeBlh getAvgwaitTimeBlh() {
		return avgwaitTimeBlh;
	}

	public void setAvgwaitTimeBlh(IAvgwaitTimeBlh avgwaitTimeBlh) {
		this.avgwaitTimeBlh = avgwaitTimeBlh;
	}

	public IChatPeersBlh getChatPeersBlh() {
		return chatPeersBlh;
	}

	public void setChatPeersBlh(IChatPeersBlh chatPeersBlh) {
		this.chatPeersBlh = chatPeersBlh;
	}

	public INoreplyNumBlh getNoreplyNumBlh() {
		return noreplyNumBlh;
	}

	public void setNoreplyNumBlh(INoreplyNumBlh noreplyNumBlh) {
		this.noreplyNumBlh = noreplyNumBlh;
	}

	public IOnlineTimeBlh getOnlineTimeBlh() {
		return onlineTimeBlh;
	}

	public void setOnlineTimeBlh(IOnlineTimeBlh onlineTimeBlh) {
		this.onlineTimeBlh = onlineTimeBlh;
	}

	public IReceiveNumBlh getReceiveNumBlh() {
		return receiveNumBlh;
	}

	public void setReceiveNumBlh(IReceiveNumBlh receiveNumBlh) {
		this.receiveNumBlh = receiveNumBlh;
	}

	public ITradeSoldBlh getTradeSoldBlh() {
		return tradeSoldBlh;
	}

	public void setTradeSoldBlh(ITradeSoldBlh tradeSoldBlh) {
		this.tradeSoldBlh = tradeSoldBlh;
	}

	public IRefundBlh getRefundBlh() {
		return refundBlh;
	}

	public void setRefundBlh(IRefundBlh refundBlh) {
		this.refundBlh = refundBlh;
	}

	public ITradeStatBlh getTradeStatBlh() {
		return tradeStatBlh;
	}

	public void setTradeStatBlh(ITradeStatBlh tradeStatBlh) {
		this.tradeStatBlh = tradeStatBlh;
	}

	public IManagerInfoBlh getManagerInfoBlh() {
		return managerInfoBlh;
	}

	public void setManagerInfoBlh(IManagerInfoBlh managerInfoBlh) {
		this.managerInfoBlh = managerInfoBlh;
	}

	public IUpdateLogBlh getUpdateLogBlh() {
		return updateLogBlh;
	}

	public void setUpdateLogBlh(IUpdateLogBlh updateLogBlh) {
		this.updateLogBlh = updateLogBlh;
	}

	public IGroupMemberBlh getGroupMemberBlh() {
		return groupMemberBlh;
	}

	public void setGroupMemberBlh(IGroupMemberBlh groupMemberBlh) {
		this.groupMemberBlh = groupMemberBlh;
	}

	public IStatAchievementBlh getStatAchievementBlh() {
		return statAchievementBlh;
	}

	public void setStatAchievementBlh(IStatAchievementBlh statAchievementBlh) {
		this.statAchievementBlh = statAchievementBlh;
	}

	public IBelongTypeBlh getBelongTypeBlh() {
		return belongTypeBlh;
	}

	public void setBelongTypeBlh(IBelongTypeBlh belongTypeBlh) {
		this.belongTypeBlh = belongTypeBlh;
	}

	public ITradeRateBlh getTradeRateBlh() {
		return tradeRateBlh;
	}

	public void setTradeRateBlh(ITradeRateBlh tradeRateBlh) {
		this.tradeRateBlh = tradeRateBlh;
	}

	public static void main(String[] args) {
		System.out.println((Date) DateUtil.getDateRange(new Date()).get("DATEEND"));
	}
}
