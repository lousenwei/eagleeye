package com.eagleeye.statistics.bsh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.statistics.blh.IStatAchievementBlh;
import com.eagleeye.statistics.bsh.IStatAchievementBsh;
import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.eo.StatAchievementNumEO;
import com.eagleeye.statistics.mo.dtl.StatStaffAvgWaitTimeDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffExchangeRateDtlResultMO;
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

public class StatAchievementBshImpl implements IStatAchievementBsh {
	IStatAchievementBlh statAchievementBlh;

	@Override
	public List<StatAchievementEO> getStatAchievementEOsByManagerIdAndTime(String managerId, Date start, Date end,
			Boolean isTotal) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatAchievementEOsByManagerIdAndTime(managerId, start, end, isTotal);
	}

	public List<StatAchievementNumEO> getStatAchievementNumEOsByManagerIdAndTime(String managerId, Date start, Date end,
			Boolean isTotal) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatAchievementNumEOsByManagerIdAndTime(managerId, start, end, isTotal);
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
		return statAchievementBlh.getStaffStatAchievementEOsById(managerId, start, end, isTotal, staffId);
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
		return statAchievementBlh.getStaffStatAchievementNumEOsById(managerId, start, end, isTotal, staffId);
	}

	public StatAchievementResultMO getStatAchievementSumResultMO(String shopId, String managerId, Boolean isTotal,
			Date startDate, Date endDate) {
		return statAchievementBlh.getStatAchievementSumResultMO(shopId, managerId, isTotal, startDate, endDate);
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
	public StatStaffTradeNumMO getStaffNumSumResultMO(String staffId, String managerId, Date startDate, Date endDate) {
		return statAchievementBlh.getStaffNumSumResultMO(staffId, managerId, startDate, endDate);
	}

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
			Date startDate, Date endDate) {
		return statAchievementBlh.getStatAchievementResultMOByStaff(staffId, managerId, isTotal, startDate, endDate);
	}

	public StatShopManBuyResultMO getStatShopManBuy(String managerId, Date startDate, Date endDate) {
		return statAchievementBlh.getStatShopManBuy(managerId, startDate, endDate);
	}

	public StatShopExchangeRateResultMO getStatShopExchangeRateResultMO(String managerId, Date startDate, Date endDate) {
		return statAchievementBlh.getStatShopExchangeRateResultMO(managerId, startDate, endDate);
	}

	public StatShopRefundRateResultMO getStatShopRefundRateResultMO(String managerId, Date startDate, Date endDate) {
		return statAchievementBlh.getStatShopRefundRateResultMO(managerId, startDate, endDate);
	}

	@Override
	public StatShopWorkLoadResultMO getStatShopWorkLoadResultMO(String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatShopWorkLoadResultMO(managerId, startDate, endDate);
	}

	@Override
	public StatShopAvgWaitTimeResultMO getStatShopAvgWaitTimeResultMO(String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatShopAvgWaitTimeResultMO(managerId, startDate, endDate);
	}

	public List<StatAchievementResultMO> getStatOwnersSumResultMOs(String shopId, String managerId, Boolean isTotal,
			Date startDate, Date endDate) {
		return statAchievementBlh.getStatOwnersSumResultMOs(shopId, managerId, isTotal, startDate, endDate);
	}

	public IStatAchievementBlh getStatAchievementBlh() {
		return statAchievementBlh;
	}

	public void setStatAchievementBlh(IStatAchievementBlh statAchievementBlh) {
		this.statAchievementBlh = statAchievementBlh;
	}

	@Override
	public StatStaffAvgWaitTimeResultMO getStatStaffAvgWaitTimeResultMO(String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatStaffAvgWaitTimeResultMO(managerId, startDate, endDate);
	}

	@Override
	public StatStaffExchangeRateResultMO getStatStaffExchangeRateResultMO(String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatStaffExchangeRateResultMO(managerId, startDate, endDate);
	}

	@Override
	public StatStaffWorkLoadResultMO getStatStaffWorkLoadResultMO(String managerId, Date startDate, Date endDate) {
		return statAchievementBlh.getStatStaffWorkLoadResultMO(managerId, startDate, endDate);
	}

	@Override
	public StatStaffManBuyResultMO getStatStaffManBuyResultMO(String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatStaffManBuyResultMO(managerId, startDate, endDate);
	}

	@Override
	public StatStaffManBuyDtlResultMO getStatStaffManBuyDtlResultMO(String managerId, Date startDate, Date endDate,
			String staffId) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatStaffManBuyDtlResultMO(managerId, startDate, endDate, staffId);
	}

	@Override
	public StatStaffRefundRateResultMO getStatStaffRefundRateResultMO(String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return statAchievementBlh.getStatStaffRefundRateResultMO(managerId, startDate, endDate);
	}

	@Override
	public StatStaffRefundRateDtlResultMO getStatStaffRefundRateDtlResultMO(String managerId, Date startDate,
			Date endDate, String staffId) {
		return statAchievementBlh.getStatStaffRefundRateDtlResultMO(managerId, startDate, endDate, staffId);
	}

	/**
	 * 获取店铺一段时间内的在线时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatShopOnlineTimeResultMO getStatShopOnlineTimeResultMO(String managerId, Date startDate, Date endDate) {
		return statAchievementBlh.getStatShopOnlineTimeResultMO(managerId, startDate, endDate);
	}

	/**
	 * 获得客服一段时间内的平均工作时间
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public StatStaffOnlineTimeResultMO getStatStaffOnlineTimeResultMO(String managerId, Date startDate, Date endDate) {
		return statAchievementBlh.getStatStaffOnlineTimeResultMO(managerId, startDate, endDate);
	}

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
			Date endDate, String staffId) {
		return statAchievementBlh.getStatStaffOnlineTimeDtlResultMO(managerId, startDate, endDate, staffId);
	}

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
			Date endDate) {
		return statAchievementBlh.getStatStaffTradeNumResultMO(managerId, isTotal, startDate, endDate);
	}

	public StatAchievementNumResultMO getShopNumSumAllResultMO(String managerId, Date startDate, Date endDate){
		return statAchievementBlh.getShopNumSumAllResultMO(managerId, startDate, endDate);
	}
	
	@Override
	public StatStaffWorkLoadDtlResultMO getStatStaffWorkLoadDtlResultMO(String managerId, Date startDate, Date endDate,
			String staffId) {
		return statAchievementBlh.getStatStaffWorkLoadDtlResultMO(managerId, startDate, endDate, staffId);
	}

	@Override
	public StatStaffAvgWaitTimeDtlResultMO getStatStaffAvgWaitTimeResultDtlMO(String managerId, Date startDate,
			Date endDate, String staffId) {
		return statAchievementBlh.getStatStaffAvgWaitTimeResultDtlMO(managerId, startDate, endDate, staffId);
	}

	@Override
	public StatStaffExchangeRateDtlResultMO getStatStaffExchangeRateDtlResultMO(String managerId, Date startDate,
			Date endDate, String staffId) {
		return statAchievementBlh.getStatStaffExchangeRateDtlResultMO(managerId, startDate, endDate, staffId);
	}
}
