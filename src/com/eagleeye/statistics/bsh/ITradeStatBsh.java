package com.eagleeye.statistics.bsh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.TradeEO;

public interface ITradeStatBsh {

	/**
	 * 生成店铺业务业绩表数据
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 */
	public void calulateTradeTotalStatByDay(String managerId, Date start,
			Date end, String shopId);

	/**
	 * 生成交易客服业绩归属
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 * @param modifyStart
	 * @param modifyEnd
	 * @param type
	 */
	public void runTradePersonalStatByDay(String managerId, Date start,
			Date end, String sessionKey, Date modifyStart, Date modifyEnd,
			String type);

	/**
	 * 生成交易客服业绩归属
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 */
	public void runTradePersonalStatByDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades, String type);

	/**
	 * 生成客服业绩表数据
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param shopId
	 */
	public void calulateTradeOwnersStatByDay(String managerId, Date start,
			Date end, String shopId);

	/**
	 * 获得待定交易
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TradeEO> getPendingTradeByManagerId(String managerId,
			Date start, Date end);

	/**
	 * 获得没有支付日期的交易
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TradeEO> getNoPaymentTradeByManagerId(String managerId,
			Date start, Date end);

	/**
	 * 获得无主交易数量
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public int getNoOwnerTradeNumByManagerId(String managerId, Date start,
			Date end);
}
