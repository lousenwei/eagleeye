package com.eagleeye.statistics.blh.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.statistics.blh.IStatAchievementBlh;
import com.eagleeye.statistics.dao.StatAchievementDAO;
import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.eo.StatAchievementNumEO;
import com.eagleeye.statistics.mo.dtl.StatStaffAvgWaitTimeDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffAvgWaitTimeDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffExchangeRateDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffExchangeRateDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffManBuyDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffManBuyDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffOnlineTimeDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffOnlineTimeDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffRefundRateDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffRefundRateDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffWorkLoadDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffWorkLoadDtlResultMO;
import com.eagleeye.statistics.mo.shop.StatAchievementNumResultMO;
import com.eagleeye.statistics.mo.shop.StatAchievementResultMO;
import com.eagleeye.statistics.mo.shop.StatShopAvgWaitTimeMO;
import com.eagleeye.statistics.mo.shop.StatShopAvgWaitTimeResultMO;
import com.eagleeye.statistics.mo.shop.StatShopExchangeRateMO;
import com.eagleeye.statistics.mo.shop.StatShopExchangeRateResultMO;
import com.eagleeye.statistics.mo.shop.StatShopManBuyMO;
import com.eagleeye.statistics.mo.shop.StatShopManBuyResultMO;
import com.eagleeye.statistics.mo.shop.StatShopOnlineTimeMO;
import com.eagleeye.statistics.mo.shop.StatShopOnlineTimeResultMO;
import com.eagleeye.statistics.mo.shop.StatShopRefundRateMO;
import com.eagleeye.statistics.mo.shop.StatShopRefundRateResultMO;
import com.eagleeye.statistics.mo.shop.StatShopWorkLoadMO;
import com.eagleeye.statistics.mo.shop.StatShopWorkLoadResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffAvgWaitTimeMO;
import com.eagleeye.statistics.mo.staff.StatStaffAvgWaitTimeResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffExchangeRateMO;
import com.eagleeye.statistics.mo.staff.StatStaffExchangeRateResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffManBuyMO;
import com.eagleeye.statistics.mo.staff.StatStaffManBuyResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffOnlineTimeMO;
import com.eagleeye.statistics.mo.staff.StatStaffOnlineTimeResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffRefundRateMO;
import com.eagleeye.statistics.mo.staff.StatStaffRefundRateResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumMO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffWorkLoadMO;
import com.eagleeye.statistics.mo.staff.StatStaffWorkLoadResultMO;
import com.eagleeye.user.dao.GroupMemberDAO;
import com.eagleeye.user.eo.GroupMemberEO;

public class StatAchievementBlhImpl implements IStatAchievementBlh {
	private Logger log = Logger.getLogger(StatAchievementBlhImpl.class);

	StatAchievementDAO statAchievementDao;
	GroupMemberDAO groupMemberDao;

	@Override
	public List<StatAchievementEO> getStatAchievementEOsByManagerIdAndTime(String managerId, Date start, Date end,
			Boolean isTotal) {
		// TODO Auto-generated method stub
		return statAchievementDao.getStatAchievementEOsByManagerIdAndTime(managerId, start, end, isTotal);
	}

	@Override
	public List<StatAchievementNumEO> getStatAchievementNumEOsByManagerIdAndTime(String managerId, Date start,
			Date end, Boolean isTotal) {
		// TODO Auto-generated method stub
		return statAchievementDao.getStatAchievementNumEOsByManagerIdAndTime(managerId, start, end, isTotal);
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
		return statAchievementDao.getStaffStatAchievementEOsById(managerId, start, end, isTotal, staffId);
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
		return statAchievementDao.getStaffStatAchievementNumEOsById(managerId, start, end, isTotal, staffId);
	}

	@Override
	public StatShopManBuyResultMO getStatShopManBuy(String managerId, Date startDate, Date endDate) {
		StatShopManBuyResultMO result = new StatShopManBuyResultMO();
		List<StatShopManBuyMO> manBuyMoList = new ArrayList();
		BigDecimal avgManBuyAmount = BigDecimal.ZERO;
		List<Map> manBuyList = (List<Map>) statAchievementDao.getStatShopManBuy(managerId, startDate, endDate);
		if (manBuyList != null && !manBuyList.isEmpty()) {
			for (Map temp : manBuyList) {
				StatShopManBuyMO manBuyMo = new StatShopManBuyMO();
				manBuyMo.setManagerId(managerId);
				manBuyMo.setStatDate((Date) temp.get("pay_time"));
				manBuyMo.setManBuyAmount((BigDecimal) (temp.get("man_buy") == null ? BigDecimal.ZERO : temp
						.get("man_buy")));
				manBuyMoList.add(manBuyMo);
				// 加总manbuy
				avgManBuyAmount = avgManBuyAmount.add(manBuyMo.getManBuyAmount());
			}
			// 算区间内平均客单价
			avgManBuyAmount = avgManBuyAmount.divide(BigDecimal.valueOf(manBuyMoList.size()), 2, RoundingMode.HALF_UP);

			result.setStatShopManBuyMoList(manBuyMoList);
			result.setAvgManBuyAmount(avgManBuyAmount);
		}
		return result;
	}

	@Override
	public StatAchievementResultMO getStatAchievementSumResultMO(String shopId, String managerId, Boolean isTotal,
			Date startDate, Date endDate) {
		if (shopId == null) {
			shopId = (String) SessionManager.getSessionByKey(EagleConstants.TOPSHOPID);
		}
		if (managerId == null) {
			managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		}
		if (shopId == null && managerId != null) {
			GroupMemberEO member = groupMemberDao.getFirstGroupMember(managerId);
			shopId = member.getShopId();
		}

		List result = statAchievementDao.getStatAchievementResultMO(shopId, managerId, isTotal, startDate, endDate);
		StatAchievementResultMO resultMo = new StatAchievementResultMO();
		if (result != null && !result.isEmpty()) {
			Map temp = (Map) result.get(0);
			resultMo.setAmountWaitSend((BigDecimal) (temp.get("sum_wait_send") == null ? BigDecimal.ZERO : temp
					.get("sum_wait_send")));
			resultMo.setAmountWaitConfirm((BigDecimal) (temp.get("sum_wait_confirm") == null ? BigDecimal.ZERO : temp
					.get("sum_wait_confirm")));
			resultMo.setAmountSuccess((BigDecimal) (temp.get("sum_success") == null ? BigDecimal.ZERO : temp
					.get("sum_success")));
			resultMo.setAmountAllRefund((BigDecimal) (temp.get("sum_all_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_all_refund")));
			resultMo.setAmountPartRefund((BigDecimal) (temp.get("sum_part_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_part_refund")));
			resultMo.setAmountRefunding((BigDecimal) (temp.get("refunding") == null ? BigDecimal.ZERO : temp
					.get("refunding")));
			resultMo.setPostFee((BigDecimal) (temp.get("post_fee") == null ? BigDecimal.ZERO : temp.get("post_fee")));
			resultMo.setTotalAmount((BigDecimal) (temp.get("sum_total") == null ? BigDecimal.ZERO : temp
					.get("sum_total")));
			resultMo.setShopId(shopId);
			resultMo.setManagerId(managerId);
			return resultMo;
		}
		return null;
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
		StatStaffTradeNumMO resultMo = new StatStaffTradeNumMO();
		List result = statAchievementDao.getStaffNumSumResultMO(staffId, managerId, startDate, endDate);
		if (result != null && !result.isEmpty()) {
			Map temp = (Map) result.get(0);
			resultMo.setNumWaitSend((BigDecimal) (temp.get("sum_wait_send") == null ? BigDecimal.ZERO : temp
					.get("sum_wait_send")));
			resultMo.setNumWaitConfirm((BigDecimal) (temp.get("sum_wait_confirm") == null ? BigDecimal.ZERO : temp
					.get("sum_wait_confirm")));
			resultMo.setNumSuccess((BigDecimal) (temp.get("sum_success") == null ? BigDecimal.ZERO : temp
					.get("sum_success")));
			resultMo.setNumAllRefund((BigDecimal) (temp.get("sum_all_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_all_refund")));
			resultMo.setNumPartRefund((BigDecimal) (temp.get("sum_part_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_part_refund")));
			resultMo.setNumRefunding((BigDecimal) (temp.get("refunding") == null ? BigDecimal.ZERO : temp
					.get("refunding")));
			resultMo.setTotalNumNoRefund((BigDecimal) (temp.get("sum_num_no_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_num_no_refund")));
			resultMo.setTotalNumHasRefund((BigDecimal) (temp.get("sum_num_has_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_num_has_refund")));
			resultMo.setStaffId(staffId);
			return resultMo;
		}
		return null;
	}

	@Override
	public StatAchievementResultMO getStatAchievementResultMOByStaff(String staffId, String managerId, Boolean isTotal,
			Date startDate, Date endDate) {
		List result = statAchievementDao.getStatAchievementResultMOByStaff(staffId, managerId, isTotal, startDate,
				endDate);
		StatAchievementResultMO resultMo = new StatAchievementResultMO();
		if (result != null && !result.isEmpty()) {
			Map temp = (Map) result.get(0);
			resultMo.setAmountWaitSend((BigDecimal) (temp.get("sum_wait_send") == null ? BigDecimal.ZERO : temp
					.get("sum_wait_send")));
			resultMo.setAmountWaitConfirm((BigDecimal) (temp.get("sum_wait_confirm") == null ? BigDecimal.ZERO : temp
					.get("sum_wait_confirm")));
			resultMo.setAmountSuccess((BigDecimal) (temp.get("sum_success") == null ? BigDecimal.ZERO : temp
					.get("sum_success")));
			resultMo.setAmountAllRefund((BigDecimal) (temp.get("sum_all_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_all_refund")));
			resultMo.setAmountPartRefund((BigDecimal) (temp.get("sum_part_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_part_refund")));
			resultMo.setAmountRefunding((BigDecimal) (temp.get("refunding") == null ? BigDecimal.ZERO : temp
					.get("refunding")));
			resultMo.setPostFee((BigDecimal) (temp.get("post_fee") == null ? BigDecimal.ZERO : temp.get("post_fee")));
			resultMo.setTotalAmount((BigDecimal) (temp.get("sum_total") == null ? BigDecimal.ZERO : temp
					.get("sum_total")));
			resultMo.setStaffId(staffId);
			resultMo.setManagerId(managerId);
			return resultMo;
		}
		return null;
	}

	@Override
	public StatAchievementNumResultMO getShopNumSumAllResultMO(String managerId, Date startDate, Date endDate) {
		List result = statAchievementDao.getShopNumSumAllResultMO(managerId, startDate, endDate);
		StatAchievementNumResultMO resultMo = new StatAchievementNumResultMO();
		if (result != null && !result.isEmpty()) {
			Map temp = (Map) result.get(0);
			resultMo.setNumWaitSend((BigDecimal) (temp.get("sum_wait_send") == null ? BigDecimal.ZERO : temp
					.get("sum_wait_send")));
			resultMo.setNumWaitConfirm((BigDecimal) (temp.get("sum_wait_confirm") == null ? BigDecimal.ZERO : temp
					.get("sum_wait_confirm")));
			resultMo.setNumSuccess((BigDecimal) (temp.get("sum_success") == null ? BigDecimal.ZERO : temp
					.get("sum_success")));
			resultMo.setNumAllRefund((BigDecimal) (temp.get("sum_all_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_all_refund")));
			resultMo.setNumPartRefund((BigDecimal) (temp.get("sum_part_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_part_refund")));
			resultMo.setNumRefunding((BigDecimal) (temp.get("sum_refunding") == null ? BigDecimal.ZERO : temp
					.get("sum_refunding")));
			resultMo.setNumTotalNoRefund((BigDecimal) (temp.get("sum_total_no_refund") == null ? BigDecimal.ZERO : temp
					.get("sum_total_no_refund")));
			resultMo.setNumTotalHasRefund((BigDecimal) (temp.get("sum_total_has_refund") == null ? BigDecimal.ZERO
					: temp.get("sum_total_has_refund")));
			return resultMo;
		}
		return null;
	}

	public List<StatAchievementResultMO> getStatOwnersSumResultMOs(String shopId, String managerId, Boolean isTotal,
			Date startDate, Date endDate) {
		if (shopId == null) {
			shopId = (String) SessionManager.getSessionByKey(EagleConstants.TOPSHOPID);
		}
		if (managerId == null) {
			managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		}
		if (shopId == null && managerId != null) {
			GroupMemberEO member = groupMemberDao.getFirstGroupMember(managerId);
			shopId = member.getShopId();
		}

		List<Map> result = (List<Map>) statAchievementDao.getStatOwnersResultMOs(shopId, managerId, isTotal, startDate,
				endDate);
		List<StatAchievementResultMO> resultMos = new ArrayList();
		int rowKey = 1;
		if (result != null && !result.isEmpty()) {
			for (Map temp : result) {
				StatAchievementResultMO resultMo = new StatAchievementResultMO();
				resultMo.setRowKey(rowKey);
				resultMo.setStaffId((String) temp.get("staff_id"));
				resultMo.setAmountWaitSend((BigDecimal) (temp.get("sum_wait_send") == null ? BigDecimal.ZERO : temp
						.get("sum_wait_send")));
				resultMo.setAmountWaitConfirm((BigDecimal) (temp.get("sum_wait_confirm") == null ? BigDecimal.ZERO
						: temp.get("sum_wait_confirm")));
				resultMo.setAmountSuccess((BigDecimal) (temp.get("sum_success") == null ? BigDecimal.ZERO : temp
						.get("sum_success")));
				resultMo.setAmountAllRefund((BigDecimal) (temp.get("sum_all_refund") == null ? BigDecimal.ZERO : temp
						.get("sum_all_refund")));
				resultMo.setAmountPartRefund((BigDecimal) (temp.get("sum_part_refund") == null ? BigDecimal.ZERO : temp
						.get("sum_part_refund")));
				resultMo.setAmountRefunding((BigDecimal) (temp.get("refunding") == null ? BigDecimal.ZERO : temp
						.get("refunding")));
				resultMo.setPostFee((BigDecimal) (temp.get("post_fee") == null ? BigDecimal.ZERO : temp.get("post_fee")));
				resultMo.setTotalAmount((BigDecimal) (temp.get("sum_total") == null ? BigDecimal.ZERO : temp
						.get("sum_total")));
				resultMo.setShopId(shopId);
				resultMo.setManagerId(managerId);
				resultMos.add(resultMo);
				rowKey++;
			}
			return resultMos;
		}
		return null;
	}

	@Override
	public StatShopExchangeRateResultMO getStatShopExchangeRateResultMO(String managerId, Date startDate, Date endDate) {
		StatShopExchangeRateResultMO result = new StatShopExchangeRateResultMO();
		List<StatShopExchangeRateMO> exchangeRateMoList = new ArrayList();
		List<Map> exchangeRateList = (List<Map>) statAchievementDao.getStatShopExchangeRate(managerId, startDate,
				endDate);
		if (exchangeRateList != null && !exchangeRateList.isEmpty()) {
			BigDecimal pay_num = BigDecimal.ZERO;
			BigDecimal reply_num = BigDecimal.ZERO;
			for (Map temp : exchangeRateList) {
				BigDecimal tempPay = BigDecimal.valueOf((Long) temp.get("pay_num") == null ? 0L : (Long) temp
						.get("pay_num"));
				BigDecimal tempReply = (BigDecimal) (temp.get("reply_num") == null ? BigDecimal.ZERO : temp
						.get("reply_num"));
				StatShopExchangeRateMO exchangeRateMo = new StatShopExchangeRateMO();
				exchangeRateMo.setManagerId(managerId);
				exchangeRateMo.setStatDate((Date) temp.get("reply_date"));
				if (tempReply.compareTo(BigDecimal.ZERO) != 0) {
					exchangeRateMo.setExchangeRate(tempPay.divide(tempReply, EagleConstants.DEFAULT_PERCENTSCALE,
							RoundingMode.HALF_UP));
				} else {
					exchangeRateMo.setExchangeRate(BigDecimal.ZERO);
				}
				exchangeRateMoList.add(exchangeRateMo);
				pay_num = pay_num.add(tempPay);
				reply_num = reply_num.add(tempReply);
			}
			if (reply_num.compareTo(BigDecimal.ZERO) != 0) {
				result.setAvgExchangeRate(pay_num.divide(reply_num, EagleConstants.DEFAULT_PERCENTSCALE,
						RoundingMode.HALF_UP));
			} else {
				result.setAvgExchangeRate(BigDecimal.ZERO);
			}
			result.setExchangeRateMoList(exchangeRateMoList);
		}
		return result;
	}

	@Override
	public StatShopRefundRateResultMO getStatShopRefundRateResultMO(String managerId, Date startDate, Date endDate) {
		StatShopRefundRateResultMO result = new StatShopRefundRateResultMO();
		List<StatShopRefundRateMO> refundRateMoList = new ArrayList();
		List<Map> exchangeRateList = (List<Map>) statAchievementDao
				.getStatShopRefundRate(managerId, startDate, endDate);
		if (exchangeRateList != null && !exchangeRateList.isEmpty()) {
			BigDecimal sum_refund = BigDecimal.ZERO;
			BigDecimal sum_amount = BigDecimal.ZERO;
			for (Map temp : exchangeRateList) {
				BigDecimal tempRefund = (BigDecimal) (temp.get("sum_refund") == null ? BigDecimal.ZERO : temp
						.get("sum_refund"));
				BigDecimal tempOverall = (BigDecimal) (temp.get("sum_amount") == null ? BigDecimal.ZERO : temp
						.get("sum_amount"));
				StatShopRefundRateMO refundRateMo = new StatShopRefundRateMO();
				refundRateMo.setManagerId(managerId);
				refundRateMo.setStatDate((Date) temp.get("pay_time"));
				if (tempOverall.compareTo(BigDecimal.ZERO) != 0) {
					refundRateMo.setRefundRate(tempRefund.divide(tempOverall, EagleConstants.DEFAULT_PERCENTSCALE,
							RoundingMode.HALF_UP));
				} else {
					refundRateMo.setRefundRate(BigDecimal.ZERO);
				}
				refundRateMoList.add(refundRateMo);
				sum_refund = sum_refund.add(tempRefund);
				sum_amount = sum_amount.add(tempOverall);
			}
			if (sum_amount.compareTo(BigDecimal.ZERO) != 0) {
				result.setAvgRefundRate(sum_refund.divide(sum_amount, EagleConstants.DEFAULT_PERCENTSCALE,
						RoundingMode.HALF_UP));
			} else {
				result.setAvgRefundRate(BigDecimal.ZERO);
			}
			result.setRefundRateMoList(refundRateMoList);
		}
		return result;
	}

	@Override
	public StatShopWorkLoadResultMO getStatShopWorkLoadResultMO(String managerId, Date startDate, Date endDate) {
		StatShopWorkLoadResultMO result = new StatShopWorkLoadResultMO();
		List<StatShopWorkLoadMO> workLoadMoList = new ArrayList();
		BigDecimal shopTotalWorkLoad = BigDecimal.ZERO;
		BigDecimal shopAvgWorkLoad = BigDecimal.ZERO;
		List<Map> workLoadList = (List<Map>) statAchievementDao.getStatShopWorkLoad(managerId, startDate, endDate);
		if (workLoadList != null && !workLoadList.isEmpty()) {
			for (Map temp : workLoadList) {
				StatShopWorkLoadMO workLoadMo = new StatShopWorkLoadMO();
				workLoadMo.setManagerId(managerId);
				workLoadMo.setStatDate((Date) temp.get("reply_date"));
				workLoadMo.setWorkLoad((BigDecimal) temp.get("workload"));
				workLoadMoList.add(workLoadMo);
				shopTotalWorkLoad = shopTotalWorkLoad.add(workLoadMo.getWorkLoad());
			}
			shopAvgWorkLoad = shopTotalWorkLoad.divide(BigDecimal.valueOf(workLoadList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			result.setWorkLoadMoList(workLoadMoList);
			result.setShopAvgWorkLoad(shopAvgWorkLoad);
			result.setShopTotalWorkLoad(shopTotalWorkLoad);
		}
		return result;
	}

	@Override
	public StatShopAvgWaitTimeResultMO getStatShopAvgWaitTimeResultMO(String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		StatShopAvgWaitTimeResultMO result = new StatShopAvgWaitTimeResultMO();
		List<StatShopAvgWaitTimeMO> avgWaitTimeMoList = new ArrayList();
		BigDecimal avgWaitTime = BigDecimal.ZERO;
		List<Map> avgWaitTimeList = (List<Map>) statAchievementDao
				.getStatShopAvgWaitTime(managerId, startDate, endDate);
		if (avgWaitTimeList != null && !avgWaitTimeList.isEmpty()) {
			for (Map temp : avgWaitTimeList) {
				StatShopAvgWaitTimeMO avgWaitTimeMo = new StatShopAvgWaitTimeMO();
				avgWaitTimeMo.setManagerId(managerId);
				avgWaitTimeMo.setStatDate((Date) temp.get("waiting_date"));
				avgWaitTimeMo.setAvgWaitTime((BigDecimal) temp.get("avg_waiting_time"));
				avgWaitTimeMoList.add(avgWaitTimeMo);
				avgWaitTime = avgWaitTime.add(avgWaitTimeMo.getAvgWaitTime());
			}
			avgWaitTime = avgWaitTime.divide(BigDecimal.valueOf(avgWaitTimeMoList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			result.setAvgWaitTimeMoList(avgWaitTimeMoList);
			result.setAvgShopWaitTime(avgWaitTime);
		}
		return result;
	}

	public StatStaffAvgWaitTimeResultMO getStatStaffAvgWaitTimeResultMO(String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		StatStaffAvgWaitTimeResultMO result = new StatStaffAvgWaitTimeResultMO();
		List<StatStaffAvgWaitTimeMO> avgWaitTimeMoList = new ArrayList();
		BigDecimal avgWaitTime = BigDecimal.ZERO;
		List<Map> avgWaitTimeList = (List<Map>) statAchievementDao.getStatStaffAvgWaitTime(managerId, startDate,
				endDate);
		if (avgWaitTimeList != null && !avgWaitTimeList.isEmpty()) {
			for (Map temp : avgWaitTimeList) {
				StatStaffAvgWaitTimeMO avgWaitTimeMo = new StatStaffAvgWaitTimeMO();
				avgWaitTimeMo.setManagerId(managerId);
				avgWaitTimeMo.setStaffId((String) temp.get("service_staff_id"));
				avgWaitTimeMo.setAvgWaitTime((BigDecimal) temp.get("avg_waiting_time"));
				avgWaitTimeMoList.add(avgWaitTimeMo);
				avgWaitTime = avgWaitTime.add(avgWaitTimeMo.getAvgWaitTime());
			}
			avgWaitTime = avgWaitTime.divide(BigDecimal.valueOf(avgWaitTimeMoList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			result.setAvgWaitTimeMoList(avgWaitTimeMoList);
			result.setAvgShopWaitTime(avgWaitTime);
		}
		return result;
	}

	public StatStaffAvgWaitTimeDtlResultMO getStatStaffAvgWaitTimeResultDtlMO(String managerId, Date startDate,
			Date endDate, String staffId) {
		// TODO Auto-generated method stub
		StatStaffAvgWaitTimeDtlResultMO result = new StatStaffAvgWaitTimeDtlResultMO();
		List<StatStaffAvgWaitTimeDtlMO> avgWaitTimeMoList = new ArrayList();
		BigDecimal avgWaitTime = BigDecimal.ZERO;
		List<Map> avgWaitTimeList = (List<Map>) statAchievementDao.getStatStaffAvgWaitTimeDtl(managerId, startDate,
				endDate, staffId);
		if (avgWaitTimeList != null && !avgWaitTimeList.isEmpty()) {
			for (Map temp : avgWaitTimeList) {
				StatStaffAvgWaitTimeDtlMO avgWaitTimeMo = new StatStaffAvgWaitTimeDtlMO();
				avgWaitTimeMo.setManagerId(managerId);
				avgWaitTimeMo.setStaffId((String) temp.get("service_staff_id"));
				avgWaitTimeMo
						.setAvgWaitTime((BigDecimal.valueOf((Long) (temp.get("avg_waiting_times") == null ? BigDecimal.ZERO
								: temp.get("avg_waiting_times")))));
				avgWaitTimeMo.setStatDate((Date) (temp.get("waiting_date")));
				avgWaitTimeMoList.add(avgWaitTimeMo);
				avgWaitTime = avgWaitTime.add(avgWaitTimeMo.getAvgWaitTime());
			}
			avgWaitTime = avgWaitTime.divide(BigDecimal.valueOf(avgWaitTimeMoList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			result.setAvgWaitTimeMoList(avgWaitTimeMoList);
			result.setAvgStaffWaitTime(avgWaitTime);
		}
		return result;
	}

	public StatStaffExchangeRateDtlResultMO getStatStaffExchangeRateDtlResultMO(String managerId, Date startDate,
			Date endDate, String staffId) {
		StatStaffExchangeRateDtlResultMO result = new StatStaffExchangeRateDtlResultMO();
		List<StatStaffExchangeRateDtlMO> exchangeRateMoList = new ArrayList();
		BigDecimal avgExchangeRate = BigDecimal.ZERO;
		List<Map> exchangeRateList = (List<Map>) statAchievementDao.getStatStaffExchangeRateDtl(managerId, startDate,
				endDate, staffId);
		if (exchangeRateList != null && !exchangeRateList.isEmpty()) {
			BigDecimal replyNum = BigDecimal.ZERO;
			BigDecimal payNum = BigDecimal.ZERO;
			for (Map temp : exchangeRateList) {
				BigDecimal tempReply = (BigDecimal.valueOf((Long) (temp.get("reply_num") == null ? 0L : temp
						.get("reply_num"))));
				BigDecimal tempPay = (BigDecimal
						.valueOf((Long) (temp.get("pay_num") == null ? 0L : temp.get("pay_num"))));
				replyNum = replyNum.add(tempReply);
				payNum = payNum.add(tempPay);
				StatStaffExchangeRateDtlMO exchangeRateMo = new StatStaffExchangeRateDtlMO();
				exchangeRateMo.setStatDate((Date) temp.get("reply_date"));
				exchangeRateMo.setManagerId(managerId);
				exchangeRateMo.setStaffId((String) temp.get("service_staff_id"));
				if (tempReply.compareTo(BigDecimal.ZERO) != 0) {
					exchangeRateMo.setExchangeRate(tempPay.divide(tempReply, EagleConstants.DEFAULT_PERCENTSCALE,
							RoundingMode.HALF_UP));
				} else {
					exchangeRateMo.setExchangeRate(BigDecimal.ONE);
				}
				exchangeRateMo.setPayNum(tempPay);
				exchangeRateMo.setReplyNum(tempReply);
				exchangeRateMoList.add(exchangeRateMo);
			}
			result.setSumPayNum(payNum);
			result.setSumReplyNum(replyNum);
			if (replyNum.compareTo(BigDecimal.ZERO) != 0) {
				result.setAvgExchangeRate(payNum.divide(replyNum, EagleConstants.DEFAULT_PERCENTSCALE,
						RoundingMode.HALF_UP));
			} else {
				result.setAvgExchangeRate(BigDecimal.ONE);
			}
			result.setExchangeRateMoList(exchangeRateMoList);
		}
		return result;
	}

	public StatStaffExchangeRateResultMO getStatStaffExchangeRateResultMO(String managerId, Date startDate, Date endDate) {
		StatStaffExchangeRateResultMO result = new StatStaffExchangeRateResultMO();
		List<StatStaffExchangeRateMO> exchangeRateMoList = new ArrayList();
		List<Map> exchangeRateList = (List<Map>) statAchievementDao.getStatStaffExchangeRate(managerId, startDate,
				endDate);
		if (exchangeRateList != null && !exchangeRateList.isEmpty()) {
			BigDecimal pay_num = BigDecimal.ZERO;
			BigDecimal reply_num = BigDecimal.ZERO;
			for (Map temp : exchangeRateList) {
				BigDecimal tempPay = BigDecimal.valueOf((Long) temp.get("pay_num") == null ? 0L : (Long) temp
						.get("pay_num"));
				BigDecimal tempReply = (BigDecimal) (temp.get("reply_num") == null ? BigDecimal.ZERO : temp
						.get("reply_num"));
				StatStaffExchangeRateMO exchangeRateMo = new StatStaffExchangeRateMO();
				exchangeRateMo.setManagerId(managerId);
				exchangeRateMo.setStaffId((String) temp.get("service_staff_id"));
				if (tempReply.compareTo(BigDecimal.ZERO) != 0) {
					exchangeRateMo.setExchangeRate(tempPay.divide(tempReply, EagleConstants.DEFAULT_PERCENTSCALE,
							RoundingMode.HALF_UP));
				} else {
					exchangeRateMo.setExchangeRate(BigDecimal.ZERO);
				}
				exchangeRateMoList.add(exchangeRateMo);
				pay_num = pay_num.add(tempPay);
				reply_num = reply_num.add(tempReply);
			}
			if (reply_num.compareTo(BigDecimal.ZERO) != 0) {
				result.setAvgExchangeRate(pay_num.divide(reply_num, EagleConstants.DEFAULT_PERCENTSCALE,
						RoundingMode.HALF_UP));
			} else {
				result.setAvgExchangeRate(BigDecimal.ZERO);
			}
			result.setExchangeRateMoList(exchangeRateMoList);
		}
		return result;
	}

	public StatStaffWorkLoadResultMO getStatStaffWorkLoadResultMO(String managerId, Date startDate, Date endDate) {
		StatStaffWorkLoadResultMO result = new StatStaffWorkLoadResultMO();
		List<StatStaffWorkLoadMO> workLoadMoList = new ArrayList();
		BigDecimal shopTotalWorkLoad = BigDecimal.ZERO;
		BigDecimal shopAvgWorkLoad = BigDecimal.ZERO;
		List<Map> workLoadList = (List<Map>) statAchievementDao.getStatStaffWorkLoad(managerId, startDate, endDate);
		if (workLoadList != null && !workLoadList.isEmpty()) {
			for (Map temp : workLoadList) {
				StatStaffWorkLoadMO workLoadMo = new StatStaffWorkLoadMO();
				workLoadMo.setManagerId(managerId);
				workLoadMo.setStaffId((String) temp.get("service_staff_id"));
				workLoadMo.setSumWorkLoad((BigDecimal) (temp.get("sum_work_load") == null ? BigDecimal.ZERO : temp
						.get("sum_work_load")));
				workLoadMo.setAvgWorkLoad((BigDecimal) (temp.get("avg_work_load") == null ? BigDecimal.ZERO : temp
						.get("avg_work_load")));
				workLoadMoList.add(workLoadMo);
				shopAvgWorkLoad = shopAvgWorkLoad.add(workLoadMo.getAvgWorkLoad() == null ? BigDecimal.ZERO
						: workLoadMo.getAvgWorkLoad());
				shopTotalWorkLoad = shopTotalWorkLoad.add(workLoadMo.getSumWorkLoad() == null ? BigDecimal.ZERO
						: workLoadMo.getSumWorkLoad());
			}
			shopAvgWorkLoad = shopAvgWorkLoad.divide(BigDecimal.valueOf(workLoadList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			result.setWorkLoadMoList(workLoadMoList);
			result.setShopAvgWorkLoad(shopAvgWorkLoad);
			result.setShopTotalWorkLoad(shopTotalWorkLoad);
		}
		return result;
	}

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
			String staffId) {
		StatStaffWorkLoadDtlResultMO result = new StatStaffWorkLoadDtlResultMO();
		List<StatStaffWorkLoadDtlMO> workLoadMoList = new ArrayList();
		BigDecimal staffTotalWorkLoad = BigDecimal.ZERO;
		BigDecimal staffAvgWorkLoad = BigDecimal.ZERO;
		List<Map> workLoadList = (List<Map>) statAchievementDao.getStatStaffWorkLoadDtl(managerId, startDate, endDate,
				staffId);
		if (workLoadList != null && !workLoadList.isEmpty()) {
			for (Map temp : workLoadList) {
				StatStaffWorkLoadDtlMO workLoadMo = new StatStaffWorkLoadDtlMO();
				workLoadMo.setManagerId(managerId);
				workLoadMo.setStaffId((String) temp.get("service_staff_id"));
				workLoadMo.setWorkLoad((BigDecimal.valueOf((Long) (temp.get("reply_num") == null ? BigDecimal.ZERO
						: temp.get("reply_num")))));
				workLoadMo.setStatDate((Date) (temp.get("reply_date")));
				workLoadMoList.add(workLoadMo);
				staffAvgWorkLoad = staffAvgWorkLoad.add(workLoadMo.getWorkLoad() == null ? BigDecimal.ZERO : workLoadMo
						.getWorkLoad());
				staffTotalWorkLoad = staffTotalWorkLoad.add(workLoadMo.getWorkLoad() == null ? BigDecimal.ZERO
						: workLoadMo.getWorkLoad());
			}
			staffAvgWorkLoad = staffAvgWorkLoad.divide(BigDecimal.valueOf(workLoadList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			result.setWorkLoadMoList(workLoadMoList);
			result.setStaffAvgWorkLoad(staffAvgWorkLoad);
			result.setStaffTotalWorkLoad(staffTotalWorkLoad);
		}
		return result;
	}

	public StatStaffManBuyResultMO getStatStaffManBuyResultMO(String managerId, Date startDate, Date endDate) {
		StatStaffManBuyResultMO result = new StatStaffManBuyResultMO();
		List<StatStaffManBuyMO> manBuyMoList = new ArrayList();
		BigDecimal avgManBuyAmount = BigDecimal.ZERO;
		List<Map> manBuyList = (List<Map>) statAchievementDao.getStatStaffManBuy(managerId, startDate, endDate);
		if (manBuyList != null && !manBuyList.isEmpty()) {
			for (Map temp : manBuyList) {
				StatStaffManBuyMO manBuyMo = new StatStaffManBuyMO();
				manBuyMo.setManagerId(managerId);
				manBuyMo.setStaffId((String) temp.get("trade_owner"));
				manBuyMo.setManBuyAmount((BigDecimal) (temp.get("man_buy") == null ? BigDecimal.ZERO : temp
						.get("man_buy")));
				manBuyMoList.add(manBuyMo);
				// 加总manbuy
				avgManBuyAmount = avgManBuyAmount.add(manBuyMo.getManBuyAmount());
			}
			// 算区间内平均客单价
			avgManBuyAmount = avgManBuyAmount.divide(BigDecimal.valueOf(manBuyMoList.size()), 2, RoundingMode.HALF_UP);

			result.setStatStaffManBuyMoList(manBuyMoList);
			result.setAvgManBuyAmount(avgManBuyAmount);
		}
		return result;
	}

	public StatStaffManBuyDtlResultMO getStatStaffManBuyDtlResultMO(String managerId, Date startDate, Date endDate,
			String staffId) {
		StatStaffManBuyDtlResultMO result = new StatStaffManBuyDtlResultMO();
		List<StatStaffManBuyDtlMO> manBuyMoList = new ArrayList();
		BigDecimal sumPayment = BigDecimal.ZERO;
		BigDecimal sumNum = BigDecimal.ZERO;
		List<Map> manBuyList = (List<Map>) statAchievementDao.getStatStaffManBuyDtl(managerId, startDate, endDate,
				staffId);
		if (manBuyList != null && !manBuyList.isEmpty()) {
			for (Map temp : manBuyList) {
				BigDecimal payment = BigDecimal.ZERO;
				BigDecimal num = BigDecimal.ZERO;
				StatStaffManBuyDtlMO manBuyMo = new StatStaffManBuyDtlMO();
				manBuyMo.setManagerId(managerId);
				manBuyMo.setStaffId((String) temp.get("trade_owner"));
				payment = temp.get("payment") == null ? BigDecimal.ZERO : (BigDecimal) temp.get("payment");
				num = BigDecimal.valueOf((Long) (temp.get("num") == null ? BigDecimal.ZERO : temp.get("num")));
				manBuyMo.setManBuyAmount(payment.divide(num, 2, RoundingMode.HALF_UP));
				manBuyMo.setStatDate((Date) (temp.get("pay_time")));
				manBuyMoList.add(manBuyMo);
				// 加总manbuy
				sumPayment = sumPayment.add(payment);
				sumNum = sumNum.add(num);
			}
			// 算区间内平均客单价
			result.setStatStaffManBuyMoList(manBuyMoList);
			result.setAvgManBuyAmount(sumPayment.divide(sumNum, 2, RoundingMode.HALF_UP));
		}
		return result;
	}

	public StatStaffRefundRateDtlResultMO getStatStaffRefundRateDtlResultMO(String managerId, Date startDate,
			Date endDate, String staffId) {
		StatStaffRefundRateDtlResultMO result = new StatStaffRefundRateDtlResultMO();
		List<StatStaffRefundRateDtlMO> refundRateMoList = new ArrayList();
		BigDecimal refundAmount = BigDecimal.ZERO;
		BigDecimal overallAmount = BigDecimal.ZERO;
		List<Map> exchangeRateList = (List<Map>) statAchievementDao.getStatStaffRefundRateDtl(managerId, startDate,
				endDate, staffId);
		if (exchangeRateList != null && !exchangeRateList.isEmpty()) {
			for (Map temp : exchangeRateList) {
				StatStaffRefundRateDtlMO refundRateMo = new StatStaffRefundRateDtlMO();
				refundRateMo.setManagerId(managerId);
				refundRateMo.setStaffId((String) temp.get("staff_id"));
				refundRateMo.setStatDate((Date) temp.get("stat_date"));
				BigDecimal tempRefund = (BigDecimal) (temp.get("refund_amount") == null ? BigDecimal.ZERO : temp
						.get("refund_amount"));
				BigDecimal tempOverall = (BigDecimal) (temp.get("overall_amount") == null ? BigDecimal.ZERO : temp
						.get("overall_amount"));
				refundRateMo.setRefundAmount(tempRefund);
				refundRateMo.setOverallAmount(tempOverall);
				if (tempOverall.compareTo(BigDecimal.ZERO) != 0) {
					refundRateMo.setRefundRate(tempRefund.divide(tempOverall, EagleConstants.DEFAULT_PERCENTSCALE,
							RoundingMode.HALF_UP));
				} else {
					refundRateMo.setRefundRate(BigDecimal.ZERO);
				}
				refundRateMoList.add(refundRateMo);
				refundAmount = refundAmount.add(tempRefund);
				overallAmount = overallAmount.add(tempOverall);
			}
			result.setRefundAmount(refundAmount);
			result.setOverallAmount(overallAmount);
			if (overallAmount.compareTo(BigDecimal.ZERO) != 0) {
				result.setAvgRefundRate(refundAmount.divide(overallAmount, EagleConstants.DEFAULT_PERCENTSCALE,
						RoundingMode.HALF_UP));
			} else {
				result.setAvgRefundRate(BigDecimal.ZERO);
			}
			result.setRefundRateMoList(refundRateMoList);
		}
		return result;
	}

	public StatStaffRefundRateResultMO getStatStaffRefundRateResultMO(String managerId, Date startDate, Date endDate) {
		StatStaffRefundRateResultMO result = new StatStaffRefundRateResultMO();
		List<StatStaffRefundRateMO> refundRateMoList = new ArrayList();
		List<Map> exchangeRateList = (List<Map>) statAchievementDao.getStatStaffRefundRate(managerId, startDate,
				endDate);
		if (exchangeRateList != null && !exchangeRateList.isEmpty()) {
			BigDecimal sum_refund = BigDecimal.ZERO;
			BigDecimal sum_amount = BigDecimal.ZERO;
			for (Map temp : exchangeRateList) {
				BigDecimal tempRefund = (BigDecimal) (temp.get("sum_refund") == null ? BigDecimal.ZERO : temp
						.get("sum_refund"));
				BigDecimal tempOverall = (BigDecimal) (temp.get("sum_amount") == null ? BigDecimal.ZERO : temp
						.get("sum_amount"));
				StatStaffRefundRateMO refundRateMo = new StatStaffRefundRateMO();
				refundRateMo.setManagerId(managerId);
				refundRateMo.setStaffId((String) temp.get("trade_owner"));
				if (tempOverall.compareTo(BigDecimal.ZERO) != 0) {
					refundRateMo.setRefundRate(tempRefund.divide(tempOverall, EagleConstants.DEFAULT_PERCENTSCALE,
							RoundingMode.HALF_UP));
				} else {
					refundRateMo.setRefundRate(BigDecimal.ZERO);
				}
				refundRateMoList.add(refundRateMo);
				sum_refund = sum_refund.add(tempRefund);
				sum_amount = sum_amount.add(tempOverall);

			}
			if (sum_amount.compareTo(BigDecimal.ZERO) != 0) {
				result.setAvgRefundRate(sum_refund.divide(sum_amount, EagleConstants.DEFAULT_PERCENTSCALE,
						RoundingMode.HALF_UP));
			} else {
				result.setAvgRefundRate(BigDecimal.ZERO);
			}
			result.setRefundRateMoList(refundRateMoList);
		}
		return result;
	}

	public StatShopOnlineTimeResultMO getStatShopOnlineTimeResultMO(String managerId, Date startDate, Date endDate) {
		StatShopOnlineTimeResultMO result = new StatShopOnlineTimeResultMO();
		List<StatShopOnlineTimeMO> onlineTimeMoList = new ArrayList();
		BigDecimal avgShopOnlineTime = BigDecimal.ZERO;
		List<Map> onlineTimeList = (List<Map>) statAchievementDao.getStatShopOnlineTime(managerId, startDate, endDate);
		if (onlineTimeList != null && !onlineTimeList.isEmpty()) {
			for (Map temp : onlineTimeList) {
				StatShopOnlineTimeMO onlineTimeMo = new StatShopOnlineTimeMO();
				onlineTimeMo.setManagerId(managerId);
				onlineTimeMo.setStatDate((Date) temp.get("online_date"));
				// 转换成小时
				BigDecimal onlineTime = (BigDecimal) temp.get("avg_online_time");
				onlineTime = DateUtil.getHoursBySeconds(onlineTime);
				// end
				onlineTimeMo.setOnlineTime(onlineTime);
				onlineTimeMoList.add(onlineTimeMo);
				avgShopOnlineTime = avgShopOnlineTime.add(onlineTimeMo.getOnlineTime());
			}
			avgShopOnlineTime = avgShopOnlineTime.divide(BigDecimal.valueOf(onlineTimeList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			result.setAvgOnlineTimeMoList(onlineTimeMoList);
			result.setAvgShopOnlineTime(avgShopOnlineTime);
		}
		return result;
	}

	public StatStaffOnlineTimeResultMO getStatStaffOnlineTimeResultMO(String managerId, Date startDate, Date endDate) {
		StatStaffOnlineTimeResultMO result = new StatStaffOnlineTimeResultMO();
		List<StatStaffOnlineTimeMO> onlineTimeMoList = new ArrayList();

		BigDecimal avgShopOnlineTime = BigDecimal.ZERO;

		BigDecimal sumShopOnlineTime = BigDecimal.ZERO;

		BigDecimal sumShopOnlineTimeForEightHours = BigDecimal.ZERO;

		List<Map> onlineTimeList = (List<Map>) statAchievementDao.getStatStaffOnlineTime(managerId, startDate, endDate);
		if (onlineTimeList != null && !onlineTimeList.isEmpty()) {
			for (Map temp : onlineTimeList) {
				StatStaffOnlineTimeMO onlineTimeMo = new StatStaffOnlineTimeMO();
				onlineTimeMo.setManagerId(managerId);
				onlineTimeMo.setStaffId((String) temp.get("service_staff_id"));
				// 转换成小时
				BigDecimal avgOnlineTime = (BigDecimal) temp.get("avg_online_time");
				BigDecimal sumOnlineTime = (BigDecimal) temp.get("sum_online_time");
				avgOnlineTime = DateUtil.getHoursBySeconds(avgOnlineTime);
				sumOnlineTime = DateUtil.getHoursBySeconds(sumOnlineTime);
				BigDecimal sumOnlineTimeForEightHours = sumOnlineTime.divide(BigDecimal.valueOf(8), 2,
						RoundingMode.HALF_UP);
				// end
				onlineTimeMo.setAvgOnlineTime(avgOnlineTime);
				onlineTimeMo.setSumOnlineTime(sumOnlineTime);
				onlineTimeMo.setSumOnlineTimeForEightHours(sumOnlineTimeForEightHours);
				onlineTimeMoList.add(onlineTimeMo);
				avgShopOnlineTime = avgShopOnlineTime.add(onlineTimeMo.getAvgOnlineTime());
				sumShopOnlineTime = sumShopOnlineTime.add(onlineTimeMo.getSumOnlineTime());
				sumShopOnlineTimeForEightHours = sumShopOnlineTimeForEightHours.add(onlineTimeMo
						.getSumOnlineTimeForEightHours());
			}
			avgShopOnlineTime = avgShopOnlineTime.divide(BigDecimal.valueOf(onlineTimeList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			sumShopOnlineTime = sumShopOnlineTime.divide(BigDecimal.valueOf(onlineTimeList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			sumShopOnlineTimeForEightHours = sumShopOnlineTimeForEightHours
					.divide(BigDecimal.valueOf(onlineTimeList.size()), EagleConstants.DEFAULT_NUMBERSCALE,
							RoundingMode.HALF_UP);
			result.setAvgOnlineMoList(onlineTimeMoList);
			result.setAvgShopOnlineTime(avgShopOnlineTime);
			result.setSumShopOnlineTime(sumShopOnlineTime);
			result.setSumShopOnlineTimeForEightHours(sumShopOnlineTimeForEightHours);
		}
		return result;
	}

	public StatStaffOnlineTimeDtlResultMO getStatStaffOnlineTimeDtlResultMO(String managerId, Date startDate,
			Date endDate, String staffId) {
		StatStaffOnlineTimeDtlResultMO result = new StatStaffOnlineTimeDtlResultMO();
		List<StatStaffOnlineTimeDtlMO> onlineTimeMoList = new ArrayList();

		BigDecimal avgStaffOnlineTime = BigDecimal.ZERO;

		BigDecimal sumStaffOnlineTime = BigDecimal.ZERO;

		BigDecimal sumStaffOnlineTimeForEightHours = BigDecimal.ZERO;

		List<Map> onlineTimeList = (List<Map>) statAchievementDao.getStatStaffOnlineTimeDtl(managerId, startDate,
				endDate, staffId);
		if (onlineTimeList != null && !onlineTimeList.isEmpty()) {
			for (Map temp : onlineTimeList) {
				StatStaffOnlineTimeDtlMO onlineTimeMo = new StatStaffOnlineTimeDtlMO();
				onlineTimeMo.setManagerId(managerId);
				onlineTimeMo.setStaffId((String) temp.get("service_staff_id"));
				onlineTimeMo.setStatDate((Date) (temp.get("online_date")));
				// 转换成小时
				BigDecimal onlineTime = (BigDecimal.valueOf((Long) (temp.get("online_times") == null ? BigDecimal.ZERO
						: temp.get("online_times"))));
				onlineTime = DateUtil.getHoursBySeconds(onlineTime);
				BigDecimal sumOnlineTimeForEightHours = onlineTime.divide(BigDecimal.valueOf(8),
						EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
				// end
				onlineTimeMo.setOnlineTime(onlineTime);
				onlineTimeMo.setOnlineTimeForEightHours(sumOnlineTimeForEightHours);
				onlineTimeMoList.add(onlineTimeMo);
				sumStaffOnlineTime = sumStaffOnlineTime.add(onlineTime);
				sumStaffOnlineTimeForEightHours = sumStaffOnlineTimeForEightHours.add(sumOnlineTimeForEightHours);
			}
			avgStaffOnlineTime = sumStaffOnlineTime.divide(BigDecimal.valueOf(onlineTimeList.size()),
					EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
			sumStaffOnlineTimeForEightHours = sumStaffOnlineTimeForEightHours
					.divide(BigDecimal.valueOf(onlineTimeList.size()), EagleConstants.DEFAULT_NUMBERSCALE,
							RoundingMode.HALF_UP);
			result.setOnlineMoList(onlineTimeMoList);
			result.setAvgStaffOnlineTime(avgStaffOnlineTime);
			result.setSumStaffOnlineTime(sumStaffOnlineTime);
			result.setSumStaffOnlineTimeForEightHours(sumStaffOnlineTimeForEightHours);
		}
		return result;
	}

	/**
	 * 删除对应统计数据
	 * 
	 * @param managerId
	 * @param shopId
	 * @param statDay
	 * @throws Exception
	 */
	public void deleteTradeStatByDays(String managerId, String shopId, Date statDay) {
		try {
			statAchievementDao.deleteTradeStatByDays(managerId, shopId, statDay);
			statAchievementDao.deleteTradeNumStatByDays(managerId, statDay);
			log.info(managerId + " " + statDay + "\n");
		} catch (Exception e) {
			log.error(managerId + " " + e);
		}

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
		if (managerId == null) {
			managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		}
		StatStaffTradeNumResultMO statStaffTradeNumResultMo = new StatStaffTradeNumResultMO();
		List<StatStaffTradeNumMO> statStaffTradeNumMoList = new ArrayList();
		List<Map> result = (List<Map>) statAchievementDao.getStatStaffTradeNumResultMOs(managerId, isTotal, startDate,
				endDate);
		if (result != null && !result.isEmpty()) {
			int rowKey = 1;
			for (Map temp : result) {
				StatStaffTradeNumMO statStaffTradeNumMo = new StatStaffTradeNumMO();
				// staff_id,
				// sum(num_wait_send) num_wait_send,
				// sum(num_wait_confirm) num_wait_confirm,
				// sum(num_success) num_success,
				// sum(num_refunding) num_refunding,
				// sum(num_all_refund) num_all_refund,
				// sum(num_part_refund)num_part_refund,
				// sum(total_num_no_refund) total_num_no_refund,
				// sum(total_num_has_refund)total_num_has_refund
				statStaffTradeNumMo.setRowKey(rowKey);
				statStaffTradeNumMo.setStaffId((String) temp.get("staff_id"));
				statStaffTradeNumMo.setNumWaitSend((BigDecimal) (temp.get("num_wait_send") == null ? BigDecimal.ZERO
						: temp.get("num_wait_send")));
				statStaffTradeNumMo
						.setNumWaitConfirm((BigDecimal) (temp.get("num_wait_confirm") == null ? BigDecimal.ZERO : temp
								.get("num_wait_confirm")));
				statStaffTradeNumMo.setNumSuccess((BigDecimal) (temp.get("num_success") == null ? BigDecimal.ZERO
						: temp.get("num_success")));
				statStaffTradeNumMo.setNumRefunding((BigDecimal) (temp.get("num_refunding") == null ? BigDecimal.ZERO
						: temp.get("num_refunding")));
				statStaffTradeNumMo.setNumAllRefund((BigDecimal) (temp.get("num_all_refund") == null ? BigDecimal.ZERO
						: temp.get("num_all_refund")));
				statStaffTradeNumMo
						.setNumPartRefund((BigDecimal) (temp.get("num_part_refund") == null ? BigDecimal.ZERO : temp
								.get("num_part_refund")));
				statStaffTradeNumMo
						.setTotalNumNoRefund((BigDecimal) (temp.get("total_num_no_refund") == null ? BigDecimal.ZERO
								: temp.get("total_num_no_refund")));
				statStaffTradeNumMo
						.setTotalNumHasRefund((BigDecimal) (temp.get("total_num_has_refund") == null ? BigDecimal.ZERO
								: temp.get("total_num_has_refund")));
				// 总计
				statStaffTradeNumResultMo.setSumNumAllRefund(statStaffTradeNumResultMo.getSumNumAllRefund().add(
						statStaffTradeNumMo.getNumAllRefund()));
				statStaffTradeNumResultMo.setSumNumPartRefund(statStaffTradeNumResultMo.getSumNumPartRefund().add(
						statStaffTradeNumMo.getNumPartRefund()));
				statStaffTradeNumResultMo.setSumNumRefunding(statStaffTradeNumResultMo.getSumNumRefunding().add(
						statStaffTradeNumMo.getNumRefunding()));
				statStaffTradeNumResultMo.setSumNumSuccess(statStaffTradeNumResultMo.getSumNumSuccess().add(
						statStaffTradeNumMo.getNumSuccess()));
				statStaffTradeNumResultMo.setSumNumWaitConfirm(statStaffTradeNumResultMo.getSumNumWaitConfirm().add(
						statStaffTradeNumMo.getNumWaitConfirm()));
				statStaffTradeNumResultMo.setSumNumWaitSend(statStaffTradeNumResultMo.getSumNumWaitSend().add(
						statStaffTradeNumMo.getNumWaitSend()));
				statStaffTradeNumResultMo.setSumTotalNumHasRefund(statStaffTradeNumResultMo.getSumTotalNumHasRefund()
						.add(statStaffTradeNumMo.getTotalNumHasRefund()));
				statStaffTradeNumResultMo.setSumTotalNumNoRefund(statStaffTradeNumResultMo.getSumTotalNumNoRefund()
						.add(statStaffTradeNumMo.getTotalNumNoRefund()));
				// 放入list
				statStaffTradeNumMoList.add(statStaffTradeNumMo);
				rowKey++;
			}
			statStaffTradeNumResultMo.setStatStaffTradeNumMoList(statStaffTradeNumMoList);
		}
		return statStaffTradeNumResultMo;
	}

	public StatAchievementDAO getStatAchievementDao() {
		return statAchievementDao;
	}

	public void setStatAchievementDao(StatAchievementDAO statAchievementDao) {
		this.statAchievementDao = statAchievementDao;
	}

	public GroupMemberDAO getGroupMemberDao() {
		return groupMemberDao;
	}

	public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
		this.groupMemberDao = groupMemberDao;
	}

}
