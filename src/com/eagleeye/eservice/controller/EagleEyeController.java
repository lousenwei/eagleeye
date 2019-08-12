package com.eagleeye.eservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.RowEditEvent;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.QueryParametersMo;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.bsh.IAvgwaitTimeBsh;
import com.eagleeye.eservice.bsh.IChatLogBsh;
import com.eagleeye.eservice.bsh.IChatPeersBsh;
import com.eagleeye.eservice.bsh.IEServiceBsh;
import com.eagleeye.eservice.bsh.INoreplyNumBsh;
import com.eagleeye.eservice.bsh.IOnlineTimeBsh;
import com.eagleeye.eservice.bsh.IReceiveNumBsh;
import com.eagleeye.eservice.bsh.IRefundBsh;
import com.eagleeye.eservice.bsh.ITradeSoldBsh;
import com.eagleeye.eservice.bsh.IUpdateLogBsh;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.statistics.bsh.ITradeStatBsh;
import com.eagleeye.user.bsh.IIncrementUpdateBsh;
import com.eagleeye.user.bsh.ITotalUpdateBsh;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;
import com.eagleeye.user.eo.TotalUpdateDetailEO;

@ManagedBean(name = "eagleEye")
@RequestScoped
public class EagleEyeController {
	IAvgwaitTimeBsh avgwaitTimeBsh;
	IChatPeersBsh chatPeersBsh;
	INoreplyNumBsh noreplyNumBsh;
	IOnlineTimeBsh onlineTimeBsh;
	IReceiveNumBsh receiveNumBsh;
	IChatLogBsh chatLogBsh;
	ITradeSoldBsh tradeSoldBsh;
	IRefundBsh refundBsh;
	ITradeStatBsh tradeStatBsh;
	IEServiceBsh eServiceBsh;
	IUpdateLogBsh updateLogBsh;
	ITotalUpdateBsh totalUpdateBsh;
	IIncrementUpdateBsh incrementUpdateBsh;

	QueryParametersMo queryParametersMo = new QueryParametersMo();

	private List<TradeEO> noPaymentTimeTrades = new ArrayList();
	private List<TradeEO> pendingTrades = new ArrayList();
	private int noOwnerTrades = 0;
	private List<TotalUpdateDetailEO> totalUpdateDetails = new ArrayList();
	private List<IncrementUpdateDetailEO> incrementUpdateDetails = new ArrayList();
	private String managerId;

	public void queryAll() {
		if (managerId != null) {
			// updatelog
			updateLogBsh = (IUpdateLogBsh) EagleEyeServiceLocator.getBean("updateLogBsh");
			// totalupdate
			totalUpdateBsh = (ITotalUpdateBsh) EagleEyeServiceLocator.getBean("totalUpdateBsh");
			totalUpdateDetails = totalUpdateBsh.getTotalUpdates(managerId);
			if (totalUpdateDetails != null && !totalUpdateDetails.isEmpty()) {
				TotalUpdateDetailEO temp = totalUpdateDetails.get(0);
				queryParametersMo.setSessionKey(temp.getSessionKey());
				queryParametersMo.setShopId(temp.getShopId());
			}
			// incrementupdate
			incrementUpdateBsh = (IIncrementUpdateBsh) EagleEyeServiceLocator.getBean("incrementUpdateBsh");
			incrementUpdateDetails = incrementUpdateBsh.getTopIncrementUpdatesByManagerid(managerId, 5);
			if (incrementUpdateDetails != null && !incrementUpdateDetails.isEmpty()) {
				IncrementUpdateDetailEO temp = incrementUpdateDetails.get(0);
				queryParametersMo.setSessionKey(temp.getSessionKey());
				queryParametersMo.setShopId(temp.getShopId());
			}
			tradeStatBsh = (ITradeStatBsh) EagleEyeServiceLocator.getBean("tradeStatBsh");
			// noPaymentTimeTrades = tradeStatBsh.getNoPaymentTradeByManagerId(
			// managerId, queryParametersMo.getStartTime(),
			// queryParametersMo.getEndTime());
			// 获取trade_owner=null的交易数
			noOwnerTrades = tradeStatBsh.getNoOwnerTradeNumByManagerId(managerId, queryParametersMo.getStartTime(),
					queryParametersMo.getEndTime());
			// // 获取Pending交易
			// pendingTrades =
			// tradeStatBsh.getPendingTradeByManagerId(managerId,
			// queryParametersMo.getStartTime(),
			// queryParametersMo.getEndTime());
			queryParametersMo.setStaffId(managerId);

		}
	}

	public void rowTotalUpdateEdit(RowEditEvent ev) {
		TotalUpdateDetailEO updateItem = (TotalUpdateDetailEO) ev.getObject();
		if (updateItem != null) {
			totalUpdateBsh.saveTotalUpdate(updateItem);
		}
	}

	public void rowIncrementUpdateEdit(RowEditEvent ev) {
		IncrementUpdateDetailEO updateItem = (IncrementUpdateDetailEO) ev.getObject();
		if (updateItem != null) {
			incrementUpdateBsh.saveIncrementUpdate(updateItem);
		}
	}

	/**
	 * 全量方式导入数据,日期跨度不得超过84天
	 */
	public void loadAllData() {
		eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator.getBean("eServiceBsh");
		// 7天为1个周期进行数据更新
		{
			Date tempStart = queryParametersMo.getStartTime();
			Date end = DateUtil.getPreviousDay(queryParametersMo.getEndTime(), EagleConstants.UPDATE_TIME_END_GAP);
			Date tempEnd = DateUtil.getNextDay(tempStart, (EagleConstants.ONE_TRANS_GAP - 1));
			if (tempStart.after(new Date()) || end.after(new Date()) || end.getYear() < EagleConstants.LIMITED_YEAR) {
				return;
			}

			while (!tempEnd.after(end)) {
				eServiceBsh.loadAllAvgWaitTimeDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, tempEnd, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllChatPeersDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, tempEnd, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllNoReplyNumDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, tempEnd, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllOnlineTimeDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, tempEnd, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllReceiveNumDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, tempEnd, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllTradeDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, tempEnd, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllRefundDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, tempEnd, queryParametersMo.getSessionKey());
				// 设置业务归属
				eServiceBsh.runTradePersonalStatByDay(managerId, queryParametersMo.getSessionKey(), null, null,
						tempStart, tempEnd);

				tempStart = tempEnd;
				tempEnd = DateUtil.getNextDay(tempEnd, EagleConstants.ONE_TRANS_GAP - 1);
			}
			if (!tempStart.after(end)) {
				eServiceBsh.loadAllAvgWaitTimeDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, end, queryParametersMo.getStaffId());
				eServiceBsh.loadAllChatPeersDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, end, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllNoReplyNumDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, end, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllOnlineTimeDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, end, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllReceiveNumDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, end, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllTradeDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, end, queryParametersMo.getSessionKey());
				eServiceBsh.loadAllRefundDataByManagerIdandTimePreiod(queryParametersMo.getStaffId(), managerId,
						tempStart, end, queryParametersMo.getSessionKey());
				// 设置业务归属
				eServiceBsh.runTradePersonalStatByDay(managerId, queryParametersMo.getSessionKey(), null, null,
						tempStart, DateUtil.getNextDay(end, 1));

			}
		}
		{
			Date tempStart = queryParametersMo.getStartTime();
			Date end = DateUtil.getPreviousDay(queryParametersMo.getEndTime(), EagleConstants.UPDATE_TIME_END_GAP);
			Date tempEnd = DateUtil.getNextDay(tempStart, (EagleConstants.ONE_TRANS_GAP - 1));
			while (!tempEnd.after(end)) {
				// 设置报表
				eServiceBsh.runPersonalStatAchievementReport(managerId, queryParametersMo.getShopId(), tempStart, tempEnd);
				eServiceBsh.runShopStatAchievementReport(managerId, queryParametersMo.getShopId(), tempStart, tempEnd);
				tempStart = tempEnd;
				tempEnd = DateUtil.getNextDay(tempEnd, EagleConstants.ONE_TRANS_GAP);
			}
			if (!tempStart.after(end)) {
				// 设置报表
				eServiceBsh.runPersonalStatAchievementReport(managerId, queryParametersMo.getShopId(), tempStart, end);
				eServiceBsh.runShopStatAchievementReport(managerId, queryParametersMo.getShopId(), tempStart, end);
			}
		}
	}

	/**
	 * 增量方式导入数据
	 */
	public void loadIncrementData() {
		eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator.getBean("eServiceBsh");
		// 15天为1个周期进行数据更新
		Date tempStart = queryParametersMo.getStartTime();
		Date end = DateUtil.getPreviousDay(queryParametersMo.getEndTime(), EagleConstants.UPDATE_TIME_END_GAP);
		Date tempEnd = DateUtil.getNextDay(tempStart, (EagleConstants.ONE_TRANS_GAP - 1));
		if (tempStart.after(new Date()) || end.after(new Date()) || end.getYear() > EagleConstants.LIMITED_YEAR) {
			return;
		}
		while (!tempEnd.after(end)) {
			List<TradeEO> trades = eServiceBsh.loadIncrementDataByManagerIdandTimePreiod(
					queryParametersMo.getStaffId(), tempStart, tempEnd, queryParametersMo.getSessionKey(), null);
			if (trades != null && !trades.isEmpty()) {
				eServiceBsh.runTradePersonalStatByDay(queryParametersMo.getStaffId(),
						queryParametersMo.getSessionKey(), null, null, tempStart, tempEnd);
				// 删除对应日期的统计数据
				Map<Date, Boolean> statDays = new HashMap();
				for (TradeEO trade : trades) {
					if (trade.getPayTime() != null) {
						Date statDay = DateUtil.getSimpleDate(trade.getPayTime());
						if (statDays.get(statDay) == null) {
							statDays.put(statDay, true);
						}
					}
				}
				trades.clear();
				if (statDays != null && !statDays.isEmpty()) {
					eServiceBsh.deleteTradeStatByDays(managerId, queryParametersMo.getShopId(), statDays.keySet());

					eServiceBsh.runPersonalStatAchievementReportByDate(queryParametersMo.getStaffId(), statDays.keySet(),
							queryParametersMo.getShopId());
					eServiceBsh.runShopStatAchievementReportByDate(queryParametersMo.getStaffId(), statDays.keySet(),
							queryParametersMo.getShopId());
				}
			}
			tempStart = tempEnd;
			tempEnd = DateUtil.getNextDay(tempStart, (EagleConstants.ONE_TRANS_GAP - 1));
		}
		if (!tempStart.after(end)) {
			List<TradeEO> trades = eServiceBsh.loadIncrementDataByManagerIdandTimePreiod(
					queryParametersMo.getStaffId(), tempStart, end, queryParametersMo.getSessionKey(), null);
			if (trades != null && !trades.isEmpty()) {
				eServiceBsh.runTradePersonalStatByDay(queryParametersMo.getStaffId(),
						queryParametersMo.getSessionKey(), null, null, tempStart, end);
				// 删除对应日期的统计数据
				Map<Date, Boolean> statDays = new HashMap();
				for (TradeEO trade : trades) {
					Date statDay = DateUtil.getSimpleDate(trade.getPayTime());
					if (statDays.get(statDay) == null) {
						statDays.put(statDay, true);
					}
				}
				trades.clear();
				if (statDays != null && !statDays.isEmpty()) {
					eServiceBsh.deleteTradeStatByDays(managerId, queryParametersMo.getShopId(), statDays.keySet());

					eServiceBsh.runPersonalStatAchievementReportByDate(queryParametersMo.getStaffId(), statDays.keySet(),
							queryParametersMo.getShopId());
					eServiceBsh.runShopStatAchievementReportByDate(queryParametersMo.getStaffId(), statDays.keySet(),
							queryParametersMo.getShopId());
				}
			}
		}
	}

	/**
	 * 跑全量报表
	 */
	public void runAllStatAchievementReport() {
		eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator.getBean("eServiceBsh");
		if (queryParametersMo.getStartTime() != null && queryParametersMo.getEndTime() != null) {
			Date statDay = queryParametersMo.getStartTime();
			Set<Date> statDays = new HashSet();
			Date end = DateUtil.getPreviousDay(queryParametersMo.getEndTime(), EagleConstants.UPDATE_TIME_END_GAP);
			while (!statDay.after(end)) {
				Date tmp = statDay;
				statDays.add(tmp);
				statDay = DateUtil.getNextDay(statDay, 1);
			}
			if (statDays != null && !statDays.isEmpty()) {
				eServiceBsh.deleteTradeStatByDays(queryParametersMo.getStaffId(), queryParametersMo.getShopId(),
						statDays);
			}
			eServiceBsh.runPersonalStatAchievementReportByTime(queryParametersMo.getStaffId(),
					queryParametersMo.getShopId(), queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
			eServiceBsh.runShopStatAchievementReportByTime(queryParametersMo.getStaffId(),
					queryParametersMo.getShopId(), queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
		}
	}

	/**
	 * 设置业务归属
	 */
	public void runTradePersonalStatByDay() {
		eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator.getBean("eServiceBsh");
		Date tempStart = queryParametersMo.getStartTime();
		Date end = DateUtil.getPreviousDay(queryParametersMo.getEndTime(), EagleConstants.UPDATE_TIME_END_GAP);
		Date tempEnd = DateUtil.getNextDay(tempStart, (EagleConstants.ONE_TRANS_GAP - 1));
		if (end.after(new Date()) || tempStart.after(new Date()) || end.getYear() > EagleConstants.LIMITED_YEAR) {
			return;
		}
		while (!tempEnd.after(end)) {
			eServiceBsh.runTradePersonalStatByDay(queryParametersMo.getStaffId(), queryParametersMo.getSessionKey(),
					tempStart, tempEnd, null, null);
			tempStart = tempEnd;
			tempEnd = DateUtil.getNextDay(tempStart, (EagleConstants.ONE_TRANS_GAP - 1));
		}
		if (!tempStart.after(end)) {
			eServiceBsh.runTradePersonalStatByDay(queryParametersMo.getStaffId(), queryParametersMo.getSessionKey(),
					tempStart, end, null, null);
		}
	}

	public QueryParametersMo getQueryParametersMo() {
		return queryParametersMo;
	}

	public void setQueryParametersMo(QueryParametersMo queryParametersMo) {
		this.queryParametersMo = queryParametersMo;
	}

	public List<TradeEO> getNoPaymentTimeTrades() {
		return noPaymentTimeTrades;
	}

	public void setNoPaymentTimeTrades(List<TradeEO> noPaymentTimeTrades) {
		this.noPaymentTimeTrades = noPaymentTimeTrades;
	}

	public List<TradeEO> getPendingTrades() {
		return pendingTrades;
	}

	public void setPendingTrades(List<TradeEO> pendingTrades) {
		this.pendingTrades = pendingTrades;
	}

	public int getNoOwnerTrades() {
		return noOwnerTrades;
	}

	public void setNoOwnerTrades(int noOwnerTrades) {
		this.noOwnerTrades = noOwnerTrades;
	}

	public List<IncrementUpdateDetailEO> getIncrementUpdateDetails() {
		return incrementUpdateDetails;
	}

	public void setIncrementUpdateDetails(List<IncrementUpdateDetailEO> incrementUpdateDetails) {
		this.incrementUpdateDetails = incrementUpdateDetails;
	}

	public List<TotalUpdateDetailEO> getTotalUpdateDetails() {
		return totalUpdateDetails;
	}

	public void setTotalUpdateDetails(List<TotalUpdateDetailEO> totalUpdateDetails) {
		this.totalUpdateDetails = totalUpdateDetails;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
}
