package com.eagleeye.eservice.bsh;

import java.util.Date;
import java.util.List;

import com.eagleeye.common.query.PageResult;
import com.eagleeye.eservice.eo.OrderEO;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.mo.TradeQueryParametersMo;

public interface ITradeSoldBsh {

	/**
	 * 导入增量交易信息
	 * 
	 * 时间间隔不能超过1天
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 */
	public List<TradeEO> loadTradeIncrementInfo(String serviceStaffId,
			Date startTime, Date endTime, String sessionKey) throws Exception;

	/**
	 * 导入交易信息
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 */
	public void loadTradeInfo(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey);

	/**
	 * 导入交易帐务信息
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 */
	public void loadTradeAmountInfo(String serviceStaffId, Date startTime,
			Date endTime);

	/**
	 * 找到对应客服交易信息
	 * 
	 * @param serviceStaffId
	 * @return
	 */
	public List<TradeEO> findAllTradeByStaffId(String serviceStaffId);

	/**
	 * 返回交易数据分页
	 * 
	 * @param queryMo
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	public PageResult getTradeEOsPagnationByManagerIdAndTimePreiod(
			TradeQueryParametersMo queryMo, int startPage, int pageSize);

	/**
	 * 根据TID获取交易
	 * 
	 * @param tradeId
	 * @return
	 */
	public List<OrderEO> getOrdersByTradeId(Long tradeId);

	/**
	 * 更新tradeEO
	 * 
	 * @param trade
	 */
	public void updateTradeEO(TradeEO trade);
}
