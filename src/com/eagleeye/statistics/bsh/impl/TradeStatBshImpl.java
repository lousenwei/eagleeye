package com.eagleeye.statistics.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.statistics.blh.ITradeStatBlh;
import com.eagleeye.statistics.bsh.ITradeStatBsh;

public class TradeStatBshImpl implements ITradeStatBsh {
	ITradeStatBlh tradeStatBlh;
	private Logger log = Logger.getLogger(TradeStatBshImpl.class);

	@Override
	public void calulateTradeTotalStatByDay(String managerId, Date start,
			Date end, String shopId) {
		tradeStatBlh.calulateTradeTotalStatByDay(managerId, start, end, shopId);
	}

	public void getPendingTrade(String managerId, Date start, Date end) {

	}

	@Override
	public void runTradePersonalStatByDay(String managerId, Date start,
			Date end, String sessionKey, Date modifyStart, Date modifyEnd,
			String type) {
		// TODO Auto-generated method stub
		tradeStatBlh.runTradePersonalStatByDay(managerId, start, end,
				sessionKey, modifyStart, modifyEnd, type);
	}

	public void runTradePersonalStatByDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades, String type) {
		try {
			tradeStatBlh.runTradePersonalStatByDay(managerId, sessionKey,
					needAssignTrades, type);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public ITradeStatBlh getTradeStatBlh() {
		return tradeStatBlh;
	}

	public void setTradeStatBlh(ITradeStatBlh tradeStatBlh) {
		this.tradeStatBlh = tradeStatBlh;
	}

	public void calulateTradeOwnersStatByDay(String managerId, Date start,
			Date end, String shopId) {
		this.tradeStatBlh.calulateTradeOwnersStatByDay(managerId, start, end,
				shopId);
	}

	/**
	 * 获得待定交易
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TradeEO> getPendingTradeByManagerId(String managerId,
			Date start, Date end) {
		return tradeStatBlh.getPendingTradeByManagerId(managerId, start, end);
	}

	/**
	 * 获得没有支付日期的交易
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TradeEO> getNoPaymentTradeByManagerId(String managerId,
			Date start, Date end) {
		return tradeStatBlh.getNoPaymentTradeByManagerId(managerId, start, end);
	}

	/**
	 * 获得无主交易数量
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public int getNoOwnerTradeNumByManagerId(String managerId, Date start,
			Date end) {
		return tradeStatBlh
				.getNoOwnerTradeNumByManagerId(managerId, start, end);
	}
}
