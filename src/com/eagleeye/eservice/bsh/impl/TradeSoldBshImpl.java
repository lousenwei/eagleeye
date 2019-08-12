package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.query.PageResult;
import com.eagleeye.eservice.blh.ITradeSoldBlh;
import com.eagleeye.eservice.bsh.ITradeSoldBsh;
import com.eagleeye.eservice.eo.OrderEO;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.mo.TradeQueryParametersMo;

public class TradeSoldBshImpl implements ITradeSoldBsh {
	ITradeSoldBlh tradeSoldBlh;
	private Logger log = Logger.getLogger(TradeSoldBshImpl.class);

	@Override
	public void loadTradeInfo(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) {
		// TODO Auto-generated method stub
		try {
			tradeSoldBlh.loadTradeInfo(serviceStaffId, startTime, endTime,
					sessionKey);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public void loadTradeAmountInfo(String serviceStaffId, Date startTime,
			Date endTime) {
		// TODO Auto-generated method stub

	}

	/**
	 * 更新tradeeo
	 * 
	 * @param trade
	 */
	public void updateTradeEO(TradeEO trade) {
		tradeSoldBlh.updateTradeEO(trade);
	}

	@Override
	public List<TradeEO> findAllTradeByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ITradeSoldBlh getTradeSoldBlh() {
		return tradeSoldBlh;
	}

	public void setTradeSoldBlh(ITradeSoldBlh tradeSoldBlh) {
		this.tradeSoldBlh = tradeSoldBlh;
	}

	@Override
	public List<TradeEO> loadTradeIncrementInfo(String serviceStaffId,
			Date startTime, Date endTime, String sessionKey) throws Exception {
		// TODO Auto-generated method stub
		return tradeSoldBlh.loadTradeIncrementInfo(serviceStaffId, startTime,
				endTime, sessionKey);
	}

	/**
	 * 返回交易数据分页
	 * 
	 * @param queryMo
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	public PageResult getTradeEOsPagnationByManagerIdAndTimePreiod(
			TradeQueryParametersMo queryMo, int startNum, int pageSize) {
		return tradeSoldBlh.getTradeEOsPagnationByManagerIdAndTimePreiod(
				queryMo, startNum, pageSize);
	}

	/**
	 * 根据TID获取交易
	 * 
	 * @param tradeId
	 * @return
	 */
	public List<OrderEO> getOrdersByTradeId(Long tradeId) {
		return tradeSoldBlh.getOrdersByTradeId(tradeId);
	}
}
