package com.eagleeye.schedule;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.bsh.IEServiceBsh;
import com.eagleeye.schedule.bsh.IScheduleBsh;
import com.eagleeye.schedule.constant.ScheduleConstants;
import com.eagleeye.user.eo.TotalUpdateDetailEO;

public class ScheduleTotalUpdateControlImpl implements IScheduleCommomControl {

	private Logger log = Logger.getLogger(ScheduleTotalUpdateControlImpl.class);

	IScheduleBsh scheduleTotalUpdateBsh = (IScheduleBsh) EagleEyeServiceLocator
			.getBean("scheduleTotalUpdateBsh");
	IEServiceBsh eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator
			.getBean("eServiceBsh");

	@Override
	public void execute() {
		// // // TODO Auto-generated method stub
		List<TotalUpdateDetailEO> results = null;
		try {

			// 选择需要处理的任务
			results = (List<TotalUpdateDetailEO>) scheduleTotalUpdateBsh
					.selectedTasks();
			// 处理任务
			if (results != null && !results.isEmpty()) {
				for (TotalUpdateDetailEO temp : results) {
					temp.setStartAt(new Date());
					// 客服号
					String staffId = ScheduleConstants.SCHEDULE_UPDATE_ALLSTAFF
							.equalsIgnoreCase(temp.getId().getStaffId()) ? null
							: temp.getId().getStaffId();
					// 经理号
					String managerId = temp.getId().getManagerId();
					// 开始时间
					//2014.3.10，修改全量更新，从上月1号开始更新，最多更新60天。
					Date start = DateUtil.getSimpleDate(DateUtil.getLastMonth(new Date()));
//					Date start = DateUtil.getPreviousDay(
//							DateUtil.getSimpleDate(new Date()),
//							EagleConstants.UPDATE_GAP);
					// 结束时间
					Date end = temp.getId().getUpdateTime();

					if (start.after(new Date()) || end.after(new Date())
							|| end.getYear() > EagleConstants.LIMITED_YEAR) {
						return;
					}
					// session key
					String sessionKey = temp.getSessionKey();
					log.info("----------" + staffId
							+ "全量导入业务数据开始--------------");
					long a = System.currentTimeMillis();
					long b = System.currentTimeMillis();
					// 如果第一次初始化，更新此类数据
					// 以14天为周期更新
					if (staffId == null) {
						Date tempStart = start;
						Date tempEnd = DateUtil.getNextDay(tempStart,
								(EagleConstants.ONE_TRANS_GAP - 1));
						while (!tempEnd.after(end)) {
							eServiceBsh
									.loadAllAvgWaitTimeDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart,
											tempEnd, sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllChatPeersDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart,
											tempEnd, sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllNoReplyNumDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart,
											tempEnd, sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllOnlineTimeDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart,
											tempEnd, sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllReceiveNumDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart,
											tempEnd, sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllTradeDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart,
											tempEnd, sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllTradeRateDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart,
											tempEnd, sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllRefundDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart,
											tempEnd, sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							// 设置业务归属
							eServiceBsh.runTradePersonalStatByDay(temp.getId()
									.getManagerId(), temp.getSessionKey(),
									tempStart, tempEnd, null, null);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							tempStart = tempEnd;
							tempEnd = DateUtil.getNextDay(tempEnd,
									EagleConstants.ONE_TRANS_GAP - 1);
						}
						if (!tempStart.after(end)) {
							eServiceBsh
									.loadAllAvgWaitTimeDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart, end,
											sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllChatPeersDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart, end,
											sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllNoReplyNumDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart, end,
											sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllOnlineTimeDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart, end,
											sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllReceiveNumDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart, end,
											sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllTradeDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart, end,
											sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllTradeRateDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart, end,
											sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							eServiceBsh
									.loadAllRefundDataByManagerIdandTimePreiod(
											staffId, managerId, tempStart, end,
											sessionKey);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							// 设置业务归属
							eServiceBsh.runTradePersonalStatByDay(temp.getId()
									.getManagerId(), temp.getSessionKey(),
									tempStart, end, null, null);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();

						}
					}
					if (staffId == null) {
						Date tempStart = start;
						Date tempEnd = DateUtil.getNextDay(tempStart,
								(EagleConstants.ONE_TRANS_GAP));
						while (!tempEnd.after(end)) {
							// 设置报表
							eServiceBsh.runPersonalStatAchievementReport(temp
									.getId().getManagerId(), temp.getShopId(),
									tempStart, tempEnd);
							eServiceBsh.runShopStatAchievementReport(temp
									.getId().getManagerId(), temp.getShopId(),
									tempStart, tempEnd);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
							tempStart = tempEnd;
							tempEnd = DateUtil.getNextDay(tempEnd,
									EagleConstants.ONE_TRANS_GAP);
						}
						if (!tempStart.after(end)) {
							// 设置报表
							eServiceBsh.runPersonalStatAchievementReport(temp
									.getId().getManagerId(), temp.getShopId(),
									tempStart, end);
							eServiceBsh.runShopStatAchievementReport(temp
									.getId().getManagerId(), temp.getShopId(),
									tempStart, end);
							log.info("执行耗时 : "
									+ (System.currentTimeMillis() - a) / 1000f
									+ " 秒 \n");
							a = System.currentTimeMillis();
						}
					}
					log.error("----------" + managerId
							+ "全量导入业务数据结束--------------");
					log.error("执行耗时 : " + (System.currentTimeMillis() - b)
							/ 1000f + " 秒 \n");
					temp.setEndAt(new Date());
				}
				// 修改任务状态为成功
				scheduleTotalUpdateBsh.modifyTaskStatus(results,
						ScheduleConstants.SCHEDULE_SUCCESS_STATUS);
			}
			log.info("total schedule success run");
		} catch (Exception e) {
			if (results != null && !results.isEmpty()) {
				// 修改任务状态为失败
				scheduleTotalUpdateBsh.modifyTaskStatus(results,
						ScheduleConstants.SCHEDULE_FAIL_STATUS);
			}
			log.error(e);
		}
	}
}
