package com.eagleeye.statistics.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.statistics.mo.rank.RankCustomerRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankCustomerSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleNumResultMO;

public interface IRankStatisticsBlh {
	/**
	 * 得到销售额前n位的商品排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public RankGoodsSaleNumResultMO getRankGoodsSaleNumMO(String managerId,
			Date startDate, Date endDate);

	/**
	 * 得到销售数量前n位的商品排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public RankGoodsSaleAmountResultMO getRankGoodsSaleAmountMO(
			String managerId, Date startDate, Date endDate);

	/**
	 * 得到退款额前n位的商品排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public RankGoodsRefundAmountResultMO getRankGoodsRefundMO(String managerId,
			Date startDate, Date endDate);

	/**
	 * 得到购买金额前n位的客户排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public RankCustomerSaleAmountResultMO getRankCustomerSaleAmountMO(
			String managerId, Date startDate, Date endDate);

	/**
	 * 得到退款金额前n位的客户排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public RankCustomerRefundAmountResultMO getRankCustomerRefundMO(
			String managerId, Date startDate, Date endDate);

	/**
	 * 得到销售额客服排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffAchievementByTimePeriod(String managerId,
			Date startDate, Date endDate);

	/**
	 * 得到客服退款排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffRefundByTimePeriod(String managerId,
			Date startDate, Date endDate);

	/**
	 * 得到客服平均回复时间排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffAvgWaitTimeByTimePeriod(String managerId,
			Date startDate, Date endDate);

	/**
	 * 得到客服工作量排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffWorkloadByTimePreiod(String managerId,
			Date startDate, Date endDate);

	/**
	 * 得到客服转换率排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffExchangeRateByTimePreiod(String managerId,
			Date startDate, Date endDate);

	/**
	 * 得到客单价排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffManBuyByTimePreiod(String managerId,
			Date startDate, Date endDate);
}
