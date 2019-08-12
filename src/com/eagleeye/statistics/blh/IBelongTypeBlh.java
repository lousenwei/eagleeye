package com.eagleeye.statistics.blh;

import java.util.List;

import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.statistics.eo.BelongTypeEO;

public interface IBelongTypeBlh {
	public void saveItem(BelongTypeEO type);

	public BelongTypeEO getBelongTypeById(String managerId);

	public String getBelongTypeByManagerId(String managerId);

	public void deleteByItemId(String managerId);

	/**
	 * type 1付款时间X小时内最早与客户沟通的客服
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 * @param type
	 * @throws Exception
	 */
	public void runTradePersonalStatFirstContactByPayDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades) throws Exception;

	/**
	 * type 2为付款时间X小时内最后与客户沟通的客服
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 * @throws Exception
	 */
	public void runTradePersonalStatLastContactByPayDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades) throws Exception;

	/**
	 * type 3创建时间X小时内最早与客户沟通的客服
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 * @param type
	 * @throws Exception
	 */
	public void runTradePersonalStatFirstContactByCreateDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades) throws Exception;

	/**
	 * type 4创建时间X小时内最后与客户沟通的客服
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 * @param type
	 * @throws Exception
	 */
	public void runTradePersonalStatLastContactByCreateDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades) throws Exception;

	/**
	 * type 3创建时间X小时内最早与客户沟通的客服
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 * @param type
	 * @throws Exception
	 */
	public void runTradePersonalStatMostContactByCreateDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades) throws Exception;

	/**
	 * type 4创建时间X小时内最后与客户沟通的客服
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 * @param type
	 * @throws Exception
	 */
	public void runTradePersonalStatMostContactByPayDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades) throws Exception;
	
	/**
	 * type 5 根据插旗归属
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 * @param type
	 * @throws Exception
	 */
	public void runTradePersonalStatFlag(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades) throws Exception;
}
