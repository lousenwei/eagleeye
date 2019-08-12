package com.eagleeye.statistics.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.eo.StatAchievementNumEO;
import com.eagleeye.statistics.mo.dtl.StatStaffAvgWaitTimeDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffExchangeRateDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffManBuyDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffManBuyDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffOnlineTimeDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffRefundRateDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffWorkLoadDtlResultMO;
import com.eagleeye.statistics.mo.shop.StatAchievementNumResultMO;
import com.eagleeye.statistics.mo.shop.StatAchievementResultMO;
import com.eagleeye.statistics.mo.shop.StatShopAvgWaitTimeResultMO;
import com.eagleeye.statistics.mo.shop.StatShopExchangeRateResultMO;
import com.eagleeye.statistics.mo.shop.StatShopManBuyResultMO;
import com.eagleeye.statistics.mo.shop.StatShopOnlineTimeResultMO;
import com.eagleeye.statistics.mo.shop.StatShopRefundRateResultMO;
import com.eagleeye.statistics.mo.shop.StatShopWorkLoadResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffAvgWaitTimeResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffExchangeRateResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffManBuyResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffOnlineTimeResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffRefundRateResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumMO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffWorkLoadResultMO;

public interface IStatAchievementBlh {

	/**
	 * 获得店铺经营状况报表
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param isTotal
	 * @return
	 */
	public List<StatAchievementEO> getStatAchievementEOsByManagerIdAndTime(String managerId, Date start, Date end,
			Boolean isTotal);

	/**
	 * 获得店铺经营状况报表
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param isTotal
	 * @return
	 */
	public List<StatAchievementNumEO> getStatAchievementNumEOsByManagerIdAndTime(String managerId, Date start, Date end,
			Boolean isTotal);

	
	/**
	 * 查询特定客服的业绩明细
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param isTotal
	 *            =false
	 * @param staffId
	 * @return
	 */
	public List<StatAchievementNumEO> getStaffStatAchievementNumEOsById(String managerId, Date start, Date end,
			Boolean isTotal, String staffId);

	/**
	 * 查询客服一段时间数量统计总和
	 * 
	 * @param shopId
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffTradeNumMO getStaffNumSumResultMO(String staffId, String managerId, Date startDate, Date endDate);

	/**
	 * 获得店铺总计经营状况
	 * 
	 * @param shopId
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatAchievementResultMO getStatAchievementSumResultMO(String shopId, String managerId, Boolean isTotal,
			Date startDate, Date endDate);

	/**
	 * 获取客服总计经营状况
	 * 
	 * @param staffId
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatAchievementResultMO getStatAchievementResultMOByStaff(String staffId, String managerId, Boolean isTotal,
			Date startDate, Date endDate);

	/**
	 * 获得店铺客单价
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatShopManBuyResultMO getStatShopManBuy(String managerId, Date startDate, Date endDate);

	/**
	 * 获得店铺转换率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatShopExchangeRateResultMO getStatShopExchangeRateResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得店铺退款金额率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatShopRefundRateResultMO getStatShopRefundRateResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得店铺工作量
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatShopWorkLoadResultMO getStatShopWorkLoadResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得店铺客户平均等待时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatShopAvgWaitTimeResultMO getStatShopAvgWaitTimeResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得客服业务统计数据
	 * 
	 * @param shopId
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<StatAchievementResultMO> getStatOwnersSumResultMOs(String shopId, String managerId, Boolean isTotal,
			Date startDate, Date endDate);

	/**
	 * 获得客服一段时间内平均等待时间数据
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffAvgWaitTimeResultMO getStatStaffAvgWaitTimeResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得客服一段时间内的转换率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffExchangeRateResultMO getStatStaffExchangeRateResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得客服一段时间内的工作量
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffWorkLoadResultMO getStatStaffWorkLoadResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得客服一段时间内的客单价
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffManBuyResultMO getStatStaffManBuyResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得客服一段时间内的客单价明细 2014.2.10
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @param staffId
	 * @return
	 */
	public StatStaffManBuyDtlResultMO getStatStaffManBuyDtlResultMO(String managerId, Date startDate, Date endDate,
			String staffId);

	/**
	 * 获得客服一段时间内的退款金额率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffRefundRateResultMO getStatStaffRefundRateResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得客服一段时间内的退款金额率明细
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @param staffId
	 * @return
	 */
	public StatStaffRefundRateDtlResultMO getStatStaffRefundRateDtlResultMO(String managerId, Date startDate,
			Date endDate, String staffId);

	/**
	 * 获取店铺一段时间内的在线时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatShopOnlineTimeResultMO getStatShopOnlineTimeResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得客服一段时间内的平均工作时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffOnlineTimeResultMO getStatStaffOnlineTimeResultMO(String managerId, Date startDate, Date endDate);

	/**
	 * 获得客服一段时间内的工作时间明细 2014.2.18
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @param staffId
	 * @return
	 */
	public StatStaffOnlineTimeDtlResultMO getStatStaffOnlineTimeDtlResultMO(String managerId, Date startDate,
			Date endDate, String staffId);

	/**
	 * 删除对应统计数据
	 * 
	 * @param managerId
	 * @param shopId
	 * @param statDay
	 * @throws Exception
	 */
	public void deleteTradeStatByDays(String managerId, String shopId, Date statDay);

	/**
	 * 得到客服计件制统计数据模型
	 * 
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffTradeNumResultMO getStatStaffTradeNumResultMO(String managerId, Boolean isTotal, Date startDate,
			Date endDate);

	/**
	 * 获取店铺一段时间内的销售数量总计
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatAchievementNumResultMO getShopNumSumAllResultMO(String managerId, Date startDate, Date endDate);
	/**
	 * 查询特定客服的业绩明细
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param isTotal
	 *            =false
	 * @param staffId
	 * @return
	 */
	public List<StatAchievementEO> getStaffStatAchievementEOsById(String managerId, Date start, Date end,
			Boolean isTotal, String staffId);

	/**
	 * 获取客服工作量明细
	 * 
	 * 2013-12-23，v1.9
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @param staffId
	 * @return
	 */
	public StatStaffWorkLoadDtlResultMO getStatStaffWorkLoadDtlResultMO(String managerId, Date startDate, Date endDate,
			String staffId);

	/**
	 * 获取客服首次响应明细
	 * 
	 * 2014.1.13，v1.9
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @param staffId
	 * @return
	 */
	public StatStaffAvgWaitTimeDtlResultMO getStatStaffAvgWaitTimeResultDtlMO(String managerId, Date startDate,
			Date endDate, String staffId);

	/**
	 * 获取客服转换率明细
	 * 
	 * 2014.1.14，v1.9
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @param staffId
	 * @return
	 */
	public StatStaffExchangeRateDtlResultMO getStatStaffExchangeRateDtlResultMO(String managerId, Date startDate,
			Date endDate, String staffId);
}
