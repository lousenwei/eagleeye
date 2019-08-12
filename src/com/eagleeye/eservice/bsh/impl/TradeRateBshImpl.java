package com.eagleeye.eservice.bsh.impl;

import java.util.Date;

import com.eagleeye.common.query.PageResult;
import com.eagleeye.eservice.blh.ITradeRateBlh;
import com.eagleeye.eservice.bsh.ITradeRateBsh;
import com.eagleeye.eservice.eo.TradeRateEO;
import com.eagleeye.eservice.mo.TradeRateQueryParametersMo;

public class TradeRateBshImpl implements ITradeRateBsh {

	ITradeRateBlh tradeRateBlh;

	@Override
	public void loadTradeRateIncrementInfo(String managerId, Date startTime,
			Date endTime, String sessionKey, String rateType) throws Exception {
		// TODO Auto-generated method stub
		tradeRateBlh.loadTradeRateIncrementInfo(managerId, startTime, endTime,
				sessionKey, rateType);
	}

	@Override
	public PageResult getTradeRateEOsPagnationByManagerIdAndTimePreiod(
			TradeRateQueryParametersMo queryMo, int startNum, int pageSize) {
		// TODO Auto-generated method stub
		return tradeRateBlh.getTradeRateEOsPagnationByManagerIdAndTimePreiod(
				queryMo, startNum, pageSize);
	}

	/**
	 * 保存tradeRate
	 * 
	 * @param tradeRate
	 */
	public void updateTradeRateEO(TradeRateEO tradeRate) {
		tradeRateBlh.updateTradeRateEO(tradeRate);
	}

	public ITradeRateBlh getTradeRateBlh() {
		return tradeRateBlh;
	}

	public void setTradeRateBlh(ITradeRateBlh tradeRateBlh) {
		this.tradeRateBlh = tradeRateBlh;
	}

}
