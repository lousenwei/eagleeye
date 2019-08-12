package com.eagleeye.eservice.blh;

import java.util.Date;

import com.eagleeye.common.query.PageResult;
import com.eagleeye.eservice.eo.TradeRateEO;
import com.eagleeye.eservice.mo.TradeRateQueryParametersMo;

public interface ITradeRateBlh {

	/**
	 * 导入交易评价
	 * 
	 * @param managerId
	 * @param startTime
	 * @param endTime
	 * @param sessionKey
	 * @param rateType
	 * @throws Exception
	 */
	public void loadTradeRateIncrementInfo(String managerId, Date startTime,
			Date endTime, String sessionKey, String rateType) throws Exception;

	/**
	 * 返回交易评价数据分页
	 * 
	 * @param queryMo
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	public PageResult getTradeRateEOsPagnationByManagerIdAndTimePreiod(
			TradeRateQueryParametersMo queryMo, int startNum, int pageSize);

	/**
	 * 更新tradeRate
	 * 
	 * @param tradeRate
	 */
	public void updateTradeRateEO(TradeRateEO tradeRate);
}
