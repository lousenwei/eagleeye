package com.eagleeye.statistics.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.TradeEO;

public interface ITradeStatBlh {

	/**
	 * 根据原始数据生成总店业绩报表, sum trade
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 */
	public void calulateTradeTotalStatByDay(String managerId, Date start,
			Date end, String shopId);

	/**
	 * 根据客服业绩计算店铺业绩，sum stat_achievement
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param shopId
	 */
	public void calulateTradeSumStatByDay(String managerId, Date start, Date end, String shopId);
	
	/**
	 * 设置业绩归属
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 * @param modifyStart
	 * @param modifyEnd
	 * @param type
	 *            定义： 1为付款时间72小时内最早与客户沟通的客服；2为付款时间72小时内最后与客户沟通的客服
	 */
	public void runTradePersonalStatByDay(String managerId, Date start,
			Date end, String sessionKey, Date modifyStart, Date modifyEnd,
			String type);

	/**
	 * 业务归属集中分配type方法
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param noOwnerTrades
	 * @param type
	 */
	public void runTradePersonalStatByDay(String managerId, String sessionKey,
			List<TradeEO> noOwnerTrades, String type);

	/**
	 * 根据原始数据计算店铺业绩，sum trade
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List runTradeTotalStatProcedureByDay(String managerId, Date start,
			Date end);

	
	/**
	 * 根据客服业绩计算店铺业绩，sum stat_achievement
	 * 
	 * @param shopId
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List runTradeSumStatProcedureByDay(String shopId,String managerId, Date start, Date end);
	
	/**
	 * 生成客服业绩报表
	 * 
	 * @param managerId
	 * @param start
	 * @param end
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

	/**
	 * 计算客服销售数量业绩报表
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 */
	public void calulateTradeOwnersStatNumByDay(String managerId, Date start,
			Date end);


	/**
	 * 计算店铺件数报表，通过sum客服stat_achievement_num
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param shopId
	 */
	public void calulateTradeNumSumStatByDay(String managerId, Date start, Date end, String shopId) ;
	/**
	 * 根据客服业绩计算店铺件数业绩，sum stat_achievement_num
	 * 
	 * @param shopId
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List runTradeNumSumStatProcedureByDay(String shopId, String managerId, Date start, Date end);
}
