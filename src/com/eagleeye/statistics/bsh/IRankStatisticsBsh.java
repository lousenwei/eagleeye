package com.eagleeye.statistics.bsh;

import java.util.Date;

import com.eagleeye.statistics.mo.rank.RankCustomerRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankCustomerSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleNumResultMO;

public interface IRankStatisticsBsh {
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
}
