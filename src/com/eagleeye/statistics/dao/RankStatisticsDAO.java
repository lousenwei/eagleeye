package com.eagleeye.statistics.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.statistics.constant.StatisticsConstants;

public class RankStatisticsDAO extends BaseDao {
	private Logger log = Logger.getLogger(RankStatisticsDAO.class);

	/**
	 * 得到销售额前n位的商品排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankGoodsSaleNumMO(String managerId, Date startDate,
			Date endDate) {
		String call = "call prop_goodsSaleNumRank(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), StatisticsConstants.TOP_RANK_NUM };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到销售数量前n位的商品排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankGoodsSaleAmountMO(String managerId, Date startDate,
			Date endDate) {
		String call = "call prop_goodsSaleAmountRank(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), StatisticsConstants.TOP_RANK_NUM };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到退款额前n位的商品排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankGoodsRefundMO(String managerId, Date startDate,
			Date endDate) {
		String call = "call prop_goodsRefundAmountRank(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), StatisticsConstants.TOP_RANK_NUM };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到购买金额前n位的客户排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankCustomerSaleAmountMO(String managerId, Date startDate,
			Date endDate) {
		String call = "call prop_customerSaleAmountRank(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), StatisticsConstants.TOP_RANK_NUM };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到退款金额前n位的客户排行榜
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankCustomerRefundMO(String managerId, Date startDate,
			Date endDate) {
		String call = "call prop_customerRefundAmountRank(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), StatisticsConstants.TOP_RANK_NUM };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到销售额客服排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffAchievementByTimePeriod(String managerId,
			Date startDate, Date endDate, Boolean noManager) {
		String call = "call proc_rankStaffAchievementByTimePeriod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), noManager };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到客服退款排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffRefundByTimePeriod(String managerId,
			Date startDate, Date endDate, Boolean noManager) {
		String call = "call proc_rankStaffRefundByTimePeriod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), noManager };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到客服平均回复时间排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffAvgWaitTimeByTimePeriod(String managerId,
			Date startDate, Date endDate, Boolean noManager) {
		String call = "call proc_avgWaitTimeStaffCalculateByTimePeriod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), noManager };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到客服工作量排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffWorkloadByTimePreiod(String managerId,
			Date startDate, Date endDate, Boolean noManager) {
		String call = "call proc_workloadStaffCalculateByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), noManager };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到客服转换率排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffExchangeRateByTimePreiod(String managerId,
			Date startDate, Date endDate, Boolean noManager) {
		String call = "call proc_rankStaffExchangeRateByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), noManager };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到客单价排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffManBuyByTimePreiod(String managerId,
			Date startDate, Date endDate, Boolean noManager) {
		String call = "call proc_manBuyStaffCalculateByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), noManager };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 得到客服销售数量排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffNumByTimePreiod(String managerId, Date startDate,
			Date endDate, Boolean noManager) {
		String call = "call proc_rankStaffNumByTimePeriod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate), noManager };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}
}
