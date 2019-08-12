package com.eagleeye.schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.bsh.IEServiceBsh;
import com.eagleeye.eservice.bsh.IUpdateLogBsh;
import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.eservice.eo.OrderEO;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.eo.UpdatelogEO;
import com.eagleeye.schedule.bsh.IScheduleBsh;
import com.eagleeye.schedule.constant.ScheduleConstants;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;

public class ScheduleIncrementUpdateControlImpl implements
		IScheduleCommomControl {

	private Logger log = Logger
			.getLogger(ScheduleIncrementUpdateControlImpl.class);

	IScheduleBsh scheduleIncrementUpdateBsh = (IScheduleBsh) EagleEyeServiceLocator
			.getBean("scheduleIncrementUpdateBsh");
	IEServiceBsh eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator
			.getBean("eServiceBsh");
	IUpdateLogBsh updateLogBsh = (IUpdateLogBsh) EagleEyeServiceLocator
			.getBean("updateLogBsh");

	@Override
	public void execute() {
		// // // TODO Auto-generated method stub
		List<IncrementUpdateDetailEO> results = null;
		try {

			// 选择需要处理的任务
			results = (List<IncrementUpdateDetailEO>) scheduleIncrementUpdateBsh
					.selectedTasks();
			// 处理任务
			if (results != null && !results.isEmpty()) {
				for (IncrementUpdateDetailEO temp : results) {
					// 增量更新
					temp.setStartAt(new Date());
					List<UpdatelogEO> oldUpdateLogs = updateLogBsh
							.getUpdateLogs(temp.getId().getManagerId());
					if (oldUpdateLogs != null && !oldUpdateLogs.isEmpty()) {
						Date end = temp.getId().getUpdateTime();
						if (end.after(new Date())
								|| end.getYear() > EagleConstants.LIMITED_YEAR) {
							log.error(temp.getId().getManagerId() + " " + end
									+ " 结束日期过长！");
							return;
						}
						for (UpdatelogEO updateTmp : oldUpdateLogs) {
							// 得到更新周期14天后
							Date tempStart = updateTmp.getUpdateTime();
							Date tempEnd = DateUtil.getNextDay(tempStart,
									(EagleConstants.ONE_TRANS_GAP - 1));
							Date updateEnd = end;
							// 如果是交易更新则更新到当前修改日期
							if (EServiceConstants.UPDATE_LOG_ITEM_TRADEINFO
									.equals(updateTmp.getId().getItem())
									|| EServiceConstants.UPDATE_LOG_ITEM_TRADERATE
											.equals(updateTmp.getId().getItem())
									|| EServiceConstants.UPDATE_LOG_ITEM_REFUND
											.equals(updateTmp.getId().getItem())) {
								updateEnd = new Date();
							}
							while (!tempEnd.after(updateEnd)) {
								List<TradeEO> trades = eServiceBsh
										.loadIncrementDataByManagerIdandTimePreiod(
												temp.getId().getManagerId(),
												null, tempEnd,
												temp.getSessionKey(), updateTmp);
								log.info(temp.getId().getManagerId() + " "
										+ tempEnd + " "
										+ updateTmp.getId().getItem()
										+ " 增量更新成功！");
								// 如果此次是增量交易更新，则更新归属和报表
								if (trades != null && !trades.isEmpty()) {
									// 设置业务归属
									eServiceBsh.runTradePersonalStatByDay(temp
											.getId().getManagerId(), temp
											.getSessionKey(), null, null,
											tempStart, tempEnd);
									log.info(temp.getId().getManagerId() + " "
											+ tempEnd + " " + " 业务归属更新成功！");
									// 删除对应日期的统计数据
									Map<Date, Boolean> statDays = new HashMap();
									Map<Long, Date> refundDays = new HashMap();
									for (TradeEO trade : trades) {
										// TODO:如果关闭和待付款交易，则无需重跑报表
										if (trade.getPayTime() != null) {
											Date statDay = DateUtil
													.getSimpleDate(trade
															.getPayTime());
											if (statDays.get(statDay) == null) {
												statDays.put(statDay, true);
												log.info("StatDays:" + statDay);
											}
											// 2013.3.3 增加退款支付日期更新
											for (OrderEO order : trade
													.getOrders()) {
												if (order.getRefundId() != null) {
													refundDays
															.put(order
																	.getRefundId(),
																	statDay);
												}
											}
										}
									}
									trades.clear();
									// 2013.3.3 增加退款支付日期更新
									if (refundDays != null
											&& !refundDays.isEmpty()) {
										eServiceBsh
												.updateRefundPayTime(refundDays);
									}
									if (statDays != null && !statDays.isEmpty()) {
										eServiceBsh
												.deleteTradeStatByDays(
														temp.getId()
																.getManagerId(),
														temp.getShopId(),
														statDays.keySet());
										log.info(temp.getId().getManagerId()
												+ " " + tempEnd + " "
												+ " 报表删除成功！");

										// 设置报表
										eServiceBsh
												.runPersonalStatAchievementReportByDate(
														temp.getId()
																.getManagerId(),
														statDays.keySet(), temp
																.getShopId());
										// 设置报表
										eServiceBsh
												.runShopStatAchievementReportByDate(
														temp.getId()
																.getManagerId(),
														statDays.keySet(), temp
																.getShopId());
										log.info(temp.getId().getManagerId()
												+ " " + tempEnd + " "
												+ " 业务归属更新成功！");
									}
								}
								tempStart = tempEnd;
								tempEnd = DateUtil.getNextDay(tempEnd,
										EagleConstants.ONE_TRANS_GAP - 1);
							}
							if (!tempStart.after(updateEnd)) {
								List<TradeEO> trades = eServiceBsh
										.loadIncrementDataByManagerIdandTimePreiod(
												temp.getId().getManagerId(),
												null, updateEnd,
												temp.getSessionKey(), updateTmp);
								log.info(temp.getId().getManagerId() + " "
										+ updateEnd + " "
										+ updateTmp.getId().getItem()
										+ " 增量更新成功！");
								// 如果此次是增量交易更新，则更新归属和报表
								if (trades != null && !trades.isEmpty()) {
									// 设置业务归属
									eServiceBsh.runTradePersonalStatByDay(temp
											.getId().getManagerId(), temp
											.getSessionKey(), null, end,
											tempStart, DateUtil.getNextDay(end,
													1));
									log.info(temp.getId().getManagerId() + " "
											+ tempStart + "-" + end + " "
											+ " 业务归属更新成功！");
									// 删除对应日期的统计数据
									Map<Date, Boolean> statDays = new HashMap();
									Map<Long, Date> refundDays = new HashMap();
									for (TradeEO trade : trades) {
										if (trade.getPayTime() != null) {
											Date statDay = DateUtil
													.getSimpleDate(trade
															.getPayTime());
											if (statDays.get(statDay) == null) {
												statDays.put(statDay, true);
											}
											// 2013.3.3 增加退款支付日期更新
											for (OrderEO order : trade
													.getOrders()) {
												if (order.getRefundId() != null) {
													refundDays
															.put(order
																	.getRefundId(),
																	statDay);
												}
											}
										}
									}
									trades.clear();
									// 2013.3.3 增加退款支付日期更新
									if (refundDays != null
											&& !refundDays.isEmpty()) {
										eServiceBsh
												.updateRefundPayTime(refundDays);
									}
									if (statDays != null && !statDays.isEmpty()) {
										eServiceBsh
												.deleteTradeStatByDays(
														temp.getId()
																.getManagerId(),
														temp.getShopId(),
														statDays.keySet());
										log.info(temp.getId().getManagerId()
												+ " " + end + " " + " 报表删除成功！");

										// 设置报表
										eServiceBsh
												.runPersonalStatAchievementReportByDate(
														temp.getId()
																.getManagerId(),
														statDays.keySet(), temp
																.getShopId());
										// 设置报表
										eServiceBsh
												.runShopStatAchievementReportByDate(
														temp.getId()
																.getManagerId(),
														statDays.keySet(), temp
																.getShopId());
										log.info(temp.getId().getManagerId()
												+ " " + end + " "
												+ " 业务归属更新成功！");
									}
								}
							}
						}
					}
					temp.setEndAt(new Date());
				}
				// 修改任务状态为成功
				scheduleIncrementUpdateBsh.modifyTaskStatus(results,
						ScheduleConstants.SCHEDULE_SUCCESS_STATUS);
			}
			log.info("increment schedule success run");
		} catch (Exception e) {
			if (results != null && !results.isEmpty()) {
				// 修改任务状态为失败
				scheduleIncrementUpdateBsh.modifyTaskStatus(results,
						ScheduleConstants.SCHEDULE_FAIL_STATUS);
			}
			log.error(e);
		}
	}
}
