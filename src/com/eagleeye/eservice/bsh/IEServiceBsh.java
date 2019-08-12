package com.eagleeye.eservice.bsh;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.eo.UpdatelogEO;

public interface IEServiceBsh {

	/**
	 * 全量方式导入店铺数据，时间跨度不得超过7天，startDate不得早于84天前
	 * 
	 * @param serviceStaffId
	 *            设置null则跑全部客服
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllDataByManagerIdandTimePreiod(String serviceStaffId,
			String managerId, Date start, Date end, String sessionKey);

	/**
	 * 增量方式导入店铺数据，时间跨度不得超过7天，startDate不得早于84天前
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 * @param update
	 * @return
	 */
	public List<TradeEO> loadIncrementDataByManagerIdandTimePreiod(
			String managerId, Date start, Date end, String sessionKey,
			UpdatelogEO update);

	/**
	 * 跑业绩报表
	 * 
	 * @param managerId
	 * @param trades
	 * @param shopId
	 */
	public void runPersonalStatAchievementReport(String managerId,
			List<TradeEO> trades, String shopId);
	
	/**
	 * 跑业绩报表
	 * 
	 * @param managerId
	 * @param trades
	 * @param shopId
	 */
	public void runShopStatAchievementReport(String managerId,
			List<TradeEO> trades, String shopId);

	/**
	 * 业务归属界定
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param needAssignTrades
	 */
	public void runTradePersonalStatByDay(String managerId, String sessionKey,
			List<TradeEO> needAssignTrades);

	/**
	 * 业务归属界定
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param start
	 * @param end
	 * @param modifyStart
	 * @param modifyEnd
	 * @param type
	 */
	public void runTradePersonalStatByDay(String managerId, String sessionKey,
			Date start, Date end, Date modifyStart, Date modifyEnd);

	/**
	 * 跑全量业务报表，日期范围是84天前至今的数据
	 * 
	 * @param managerId
	 * @param shopId
	 */
	public void runPersonalStatAchievementReport(String managerId, String shopId);
	
	/**
	 * 跑全量业务报表，日期范围是84天前至今的数据
	 * 
	 * @param managerId
	 * @param shopId
	 */
	public void runShopStatAchievementReport(String managerId, String shopId);

	/**
	 * 跑全量个人业务报表
	 * 
	 * @param managerId
	 * @param shopId
	 * @param start
	 * @param end
	 */
	public void runPersonalStatAchievementReport(String managerId, String shopId, Date start, Date end);
	
	/**
	 * 跑全量店铺业务报表
	 * 
	 * @param managerId
	 * @param shopId
	 * @param start
	 * @param end
	 */
	public void runShopStatAchievementReport(String managerId, String shopId, Date start, Date end);

	/**
	 * 根据时间跑全量报表
	 * 
	 * @param managerId
	 * @param shopId
	 * @param start
	 * @param end
	 */
	public void runPersonalStatAchievementReportByTime(String managerId,
			String shopId, Date start, Date end);
	
	/**
	 * 根据时间跑全量报表
	 * 
	 * @param managerId
	 * @param shopId
	 * @param start
	 * @param end
	 */
	public void runShopStatAchievementReportByTime(String managerId,
			String shopId, Date start, Date end);

	/**
	 * 导入平均等待时间全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllAvgWaitTimeDataByManagerIdandTimePreiod(
			String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey);

	/**
	 * 导入聊天对象全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllChatPeersDataByManagerIdandTimePreiod(
			String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey);

	/**
	 * 导入未回复对象全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllNoReplyNumDataByManagerIdandTimePreiod(
			String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey);

	/**
	 * 导入在线时间全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllOnlineTimeDataByManagerIdandTimePreiod(
			String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey);

	/**
	 * 导入接待数目全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllReceiveNumDataByManagerIdandTimePreiod(
			String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey);

	/**
	 * 导入交易信息全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllTradeDataByManagerIdandTimePreiod(String serviceStaffId,
			String managerId, Date start, Date end, String sessionKey);

	/**
	 * 导入退款信息全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllRefundDataByManagerIdandTimePreiod(
			String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey);

	/**
	 * 导入交易评价信息全量数据
	 * 
	 * @param serviceStaffId
	 * @param managerId
	 * @param start
	 * @param end
	 * @param sessionKey
	 */
	public void loadAllTradeRateDataByManagerIdandTimePreiod(
			String serviceStaffId, String managerId, Date start, Date end,
			String sessionKey);

	/**
	 * 删除对应统计数据
	 * 
	 * @param managerId
	 * @param shopId
	 * @param statDay
	 * @throws Exception
	 */
	public void deleteTradeStatByDays(String managerId, String shopId,
			Set<Date> statDays);

	/**
	 * 将本月交易归属人设置为null
	 * 
	 * @param managerId
	 * @return
	 */
	public int setCurrentMonthTradeOwnerToNull(String managerId);

	/**
	 * 根据日期跑业绩报表
	 * 
	 * @param managerId
	 * @param sateDates
	 * @param shopId
	 */
	public void runPersonalStatAchievementReportByDate(String managerId,
			Set<Date> sateDates, String shopId);

	/**
	 * 根据日期跑业绩报表
	 * 
	 * @param managerId
	 * @param sateDates
	 * @param shopId
	 */
	public void runShopStatAchievementReportByDate(String managerId,
			Set<Date> sateDates, String shopId);

	/**
	 * 根据refundid更新支付日期
	 * 
	 * @param refundId
	 * @param payTime
	 * @throws Exception
	 */
	public void updateRefundPayTime(Map<Long, Date> refundDates);
}
