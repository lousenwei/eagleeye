package com.eagleeye.statistics.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.statistics.blh.impl.TradeStatBlhImpl;
import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.eo.StatAchievementNumEO;

public class StatAchievementDAO extends BaseDao {
	private Logger log = Logger.getLogger(TradeStatBlhImpl.class);

	/**
	 * 查询总店业务统计数据
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param isTotal
	 * @return
	 */
	public List<StatAchievementEO> getStatAchievementEOsByManagerIdAndTime(String managerId, Date start, Date end,
			Boolean isTotal) {
		String sql = "from StatAchievementEO where id.managerId=? and id.statDate>=? and id.statDate<=? and id.isTotal=? order by id.statDate asc";
		return super.getListData(sql, new Object[] { managerId, start, end, isTotal });
	}

	/**
	 * 查询总店业务统计数据
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @param isTotal
	 * @return
	 */
	public List<StatAchievementNumEO> getStatAchievementNumEOsByManagerIdAndTime(String managerId, Date start,
			Date end, Boolean isTotal) {
		String sql = "from StatAchievementNumEO where id.managerId=? and id.statDate>=? and id.statDate<=? and id.isTotal=? order by id.statDate asc";
		return super.getListData(sql, new Object[] { managerId, start, end, isTotal });
	}

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
			Boolean isTotal, String staffId) {
		String sql = "from StatAchievementEO where id.managerId=? and id.statDate>=? and id.statDate<=? and id.isTotal=? and id.staffId=? order by id.statDate asc";
		return super.getListData(sql, new Object[] { managerId, start, end, isTotal, staffId });
	}

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
			Boolean isTotal, String staffId) {
		String sql = "from StatAchievementNumEO where id.managerId=? and id.statDate>=? and id.statDate<=? and id.isTotal=? and id.staffId=? order by id.statDate asc";
		return super.getListData(sql, new Object[] { managerId, start, end, isTotal, staffId });
	}

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
	public List getStaffNumSumResultMO(String staffId, String managerId, Date startDate, Date endDate) {
		String call = "call proc_statStaffNumSumCalculate(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { staffId, managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

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
	public List getShopNumSumAllResultMO(String managerId, Date startDate, Date endDate) {
		String call = "call proc_statShopNumSumAll(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 查询总店业务统计总和数据
	 * 
	 * @param shopId
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatAchievementResultMO(String shopId, String managerId, Boolean isTotal, Date startDate,
			Date endDate) {
		String call = "call proc_tradeAllShopSumCalculate(?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { shopId, managerId, isTotal, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 查询总店业务统计总和数据
	 * 
	 * @param shopId
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatAchievementResultMOByStaff(String staffId, String managerId, Boolean isTotal, Date startDate,
			Date endDate) {
		String call = "call proc_statStaffSumCalculate(?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { staffId, managerId, isTotal, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 查询客服业务统计总额数据
	 * 
	 * @param shopId
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatOwnersResultMOs(String shopId, String managerId, Boolean isTotal, Date startDate, Date endDate) {
		String call = "call proc_tradeAllStaffSumCalculate(?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { shopId, managerId, isTotal, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 查询客服交易数量统计总额数据
	 * 
	 * @param shopId
	 * @param managerId
	 * @param isTotal
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffTradeNumResultMOs(String managerId, Boolean isTotal, Date startDate, Date endDate) {
		String call = "call proc_tradeAllStaffNumSumCalculate(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, isTotal, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取总店客单价
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatShopManBuy(String managerId, Date startDate, Date endDate) {
		String call = "call proc_manBuyShopCalculateByTimePreiod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取总店退款金率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatShopRefundRate(String managerId, Date startDate, Date endDate) {
		String call = "call proc_refundRateShopCalculateByTimePreiod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取总店转换率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatShopExchangeRate(String managerId, Date startDate, Date endDate) {
		String call = "call proc_exchangeRateShopCalculateByTimePreiod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得总店总工作量
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatShopWorkLoad(String managerId, Date startDate, Date endDate) {
		String call = "call proc_workloadShopCalculateByTimePreiod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得总店客户平均等待时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatShopAvgWaitTime(String managerId, Date startDate, Date endDate) {
		String call = "call proc_avgWaitTimeShopCalculateByTimePeriod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得客服接待客户平均等待时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffAvgWaitTime(String managerId, Date startDate, Date endDate) {
		String call = "call proc_avgWaitTimeStaffCalculateByTimePeriod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), false };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得客服接待客户平均等待时间明细 2014.1.13，v1.9
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffAvgWaitTimeDtl(String managerId, Date startDate, Date endDate, String staffId) {
		String call = "call proc_avgWaitTimeStaffDtlByTimePeriod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), staffId };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得客服一段时间内的工作总量
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffWorkLoad(String managerId, Date startDate, Date endDate) {
		String call = "call proc_workloadStaffCalculateByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), false };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取一段时间内客服工作量明细 2013-12-23，v1.9
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @param staffId
	 * @return
	 */
	public List getStatStaffWorkLoadDtl(String managerId, Date startDate, Date endDate, String staffId) {
		String call = "call proc_workloadStaffDtlByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), staffId };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取客服对应转换率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffExchangeRate(String managerId, Date startDate, Date endDate) {
		String call = "call proc_exchangeRateStaffCalculateByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), false };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取客服对应转换率明细
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffExchangeRateDtl(String managerId, Date startDate, Date endDate, String staffId) {
		String call = "call proc_exchangeRateStaffDtlByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), staffId };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取客服一段时间内退款金率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffRefundRate(String managerId, Date startDate, Date endDate) {
		String call = "call proc_refundRateStaffCalculateByTimePreiod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取客服一段时间内退款金率
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffRefundRateDtl(String managerId, Date startDate, Date endDate, String staffId) {
		String call = "call proc_refundRateStaffDtlByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), staffId };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取客服一段时间内客单价
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffManBuy(String managerId, Date startDate, Date endDate) {
		String call = "call proc_manBuyStaffCalculateByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), false };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获取客服一段时间内客单价明细
	 * 
	 * 2014.2.10
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffManBuyDtl(String managerId, Date startDate, Date endDate, String staffId) {
		String call = "call proc_manBuyStaffDtlByTimePreiod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), staffId };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得店铺一段时间内的在线时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatShopOnlineTime(String managerId, Date startDate, Date endDate) {
		String call = "call proc_onlineTimeShopCalculateByTimePeriod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得客服一段时间内的在线时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffOnlineTime(String managerId, Date startDate, Date endDate) {
		String call = "call proc_onlineTimeStaffCalculateByTimePeriod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 获得客服一段时间内的在线时间明细 2014.2.11
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getStatStaffOnlineTimeDtl(String managerId, Date startDate, Date endDate, String staffId) {
		String call = "call proc_onlineTimeStaffDtlByTimePeriod(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate), sdf.format(endDate), staffId };
		List result = new ArrayList();
		try {
			result = super.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	public void deleteTradeStatByDays(String managerId, String shopId, Date statDay) throws Exception {
		if (statDay != null && managerId != null && shopId != null) {
			String sql = "delete from stat_achievement where manager_id=? and shop_id=? and stat_date=?";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int i = super.deleteByJDBCsqL(sql, new Object[] { managerId, shopId, sdf.format(statDay) });
			log.info("delete stat-achievement nums: " + i);
		}
	}

	public void deleteTradeNumStatByDays(String managerId, Date statDay) throws Exception {
		if (statDay != null && managerId != null) {
			String sql = "delete from stat_achievement_num where manager_id=? and stat_date=?";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int i = super.deleteByJDBCsqL(sql, new Object[] { managerId, sdf.format(statDay) });
			log.info("delete stat-achievement-num nums: " + i);
		}
	}
}
