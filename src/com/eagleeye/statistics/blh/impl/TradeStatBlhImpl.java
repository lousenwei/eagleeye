package com.eagleeye.statistics.blh.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.eservice.dao.impl.ChatPeersDAO;
import com.eagleeye.eservice.dao.impl.TradeSoldDAO;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.mo.TradeQueryParametersMo;
import com.eagleeye.statistics.blh.IBelongTypeBlh;
import com.eagleeye.statistics.blh.ITradeStatBlh;
import com.eagleeye.statistics.dao.NoCalculateItemDAO;
import com.eagleeye.statistics.eo.NoCalculateItemsEO;
import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.eo.StatAchievementEOId;
import com.eagleeye.statistics.eo.StatAchievementNumEO;
import com.eagleeye.statistics.eo.StatAchievementNumEOId;
import com.eagleeye.user.dao.GroupMemberDAO;
import com.eagleeye.user.dao.ManagerInfoDAO;
import com.eagleeye.user.eo.GroupMemberEO;

public class TradeStatBlhImpl implements ITradeStatBlh {
	TradeSoldDAO tradeSoldDao;
	GroupMemberDAO groupMemberDao;
	ChatPeersDAO chatPeersDao;
	NoCalculateItemDAO noCalculateItemDao;
	IBelongTypeBlh belongTypeBlh;
	ManagerInfoDAO managerInfoDao;

	private Logger log = Logger.getLogger(TradeStatBlhImpl.class);

	/**
	 * 获得客服一段时间内的销售数量（件）, 已经剔除不计入商品
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List runTradeStaffNumStatProcedureByDay(String managerId,
			Date startDate, Date endDate) {
		String call = "call proc_calStaffNumByTimePeriod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(startDate),
				sdf.format(endDate) };
		List result = new ArrayList();
		try {
			result = tradeSoldDao.callProcedureHasReturn(call, params);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	@Override
	public List runTradeTotalStatProcedureByDay(String managerId, Date start,
			Date end) {
		// TODO Auto-generated method stub
		/****
		 * 日期 等待发货 等待确认 交易完成 全额退款 部分退款 合计交易 店铺 manager_id
		 ***/
		String call = "call proc_tradeAllShopCalculateByTimePreiod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(start), sdf.format(end) };
		try {
			List tradeTotalStat = tradeSoldDao.callProcedureHasReturn(call,
					params);
			return tradeTotalStat;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public List runTradeSumStatProcedureByDay(String shopId, String managerId,
			Date start, Date end) {
		// TODO Auto-generated method stub
		/****
		 * 日期 等待发货 等待确认 交易完成 全额退款 部分退款 合计交易 店铺 manager_id
		 ***/
		String call = "call proc_statShopSumCalculate(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { shopId, managerId, sdf.format(start),
				sdf.format(end) };
		try {
			List tradeTotalStat = tradeSoldDao.callProcedureHasReturn(call,
					params);
			return tradeTotalStat;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public List runTradeNumSumStatProcedureByDay(String shopId,
			String managerId, Date start, Date end) {
		// TODO Auto-generated method stub
		/****
		 * 日期 等待发货 等待确认 交易完成 全额退款 部分退款 合计交易 店铺 manager_id
		 ***/
		String call = "call proc_statShopNumSumCalculate(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(start), sdf.format(end) };
		try {
			List tradeTotalStat = tradeSoldDao.callProcedureHasReturn(call,
					params);
			return tradeTotalStat;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 跑店铺剔除不计算商品的除退款的金额
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List runNoCalTradeAllShopWithoutRefundByDay(String managerId,
			Date start, Date end) {
		String call = "call proc_noCalTradeAllShopWithoutRefund(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(start), sdf.format(end) };
		try {
			List noCalTradeTotalStatWithoutRefund = tradeSoldDao
					.callProcedureHasReturn(call, params);
			return noCalTradeTotalStatWithoutRefund;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 跑店铺剔除不计算商品的退款金额
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List runNoCalTradeAllShopRefundByDay(String managerId, Date start,
			Date end) {
		String call = "call proc_noCalTradeAllShopRefund(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(start), sdf.format(end) };
		try {
			List noCalTradeTotalStatRefund = tradeSoldDao
					.callProcedureHasReturn(call, params);
			return noCalTradeTotalStatRefund;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 跑个人剔除不计算商品的除退款的金额
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List runNoCalTradeAllStaffWithoutRefundByDay(String managerId,
			Date start, Date end) {
		String call = "call proc_noCalTradeAllStaffWithoutRefund(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(start), sdf.format(end) };
		try {
			List noCalTradeTotalStatRefund = tradeSoldDao
					.callProcedureHasReturn(call, params);
			return noCalTradeTotalStatRefund;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 跑个人剔除不计算商品的退款金额
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List runNoCalTradeAllStaffRefundByDay(String managerId, Date start,
			Date end) {
		String call = "call proc_noCalTradeAllStaffRefund(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(start), sdf.format(end) };
		try {
			List noCalTradeTotalStatRefund = tradeSoldDao
					.callProcedureHasReturn(call, params);
			return noCalTradeTotalStatRefund;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public List runTradeOwnersStatProcedureByDay(String managerId, Date start,
			Date end) {
		String call = "call proc_tradeOwnersCalculateByTimePreiod(?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { managerId, sdf.format(start), sdf.format(end) };
		try {
			List tradeOwnersStat = tradeSoldDao.callProcedureHasReturn(call,
					params);
			return tradeOwnersStat;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public void runTradePersonalStatByDay(String managerId, Date start,
			Date end, String sessionKey, Date modifyStart, Date modifyEnd,
			String type) {
		// TODO Auto-generated method stub
		TradeQueryParametersMo params = new TradeQueryParametersMo();
		params.setStaffId(managerId);
		if (start != null) {
			params.setStart(start);
		}
		if (end != null) {
			params.setEnd(end);
		}
		if (modifyStart != null) {
			params.setModifyStart(modifyStart);
		}
		if (modifyEnd != null) {
			params.setModifyEnd(modifyEnd);
		}
		List<String> status = new ArrayList();
		status.add(EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED);
		status.add(EServiceConstants.TRADE_STATUS_TRADE_FINISHED);
		status.add(EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS);
		status.add(EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS);
		status.add(EServiceConstants.TRADE_STATUS_TRADE_CLOSED);
		params.setStatus(status);
		params.setHasTradeOwner(false);
		try {
			//20160627,wilson,如果是flag更新，则取出全部交易重新更新，防止之后插旗的形式出现
			if (EagleConstants.BELONG_TYPE_FLAG.equalsIgnoreCase(type)) {
				params.setHasTradeOwner(true);
			}
			List<TradeEO> noOwnerTrades = tradeSoldDao
					.getTradeEOsByManagerIdAndTimePreiod(params);
			this.runTradePersonalStatByDay(managerId, sessionKey,
					noOwnerTrades, type);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 业务归属集中分配type方法
	 * 
	 * @param managerId
	 * @param sessionKey
	 * @param noOwnerTrades
	 * @param type
	 */
	public void runTradePersonalStatByDay(String managerId, String sessionKey,
			List<TradeEO> noOwnerTrades, String type) {
		try {
			// TODO:判断业务归属类型
			if (EagleConstants.BELONG_TYPE_PAY_FIRST_TALK
					.equalsIgnoreCase(type)) {
				belongTypeBlh.runTradePersonalStatFirstContactByPayDay(
						managerId, sessionKey, noOwnerTrades);
			} else if (EagleConstants.BELONG_TYPE_CREATE_FIRST_TALK
					.equalsIgnoreCase(type)) {
				belongTypeBlh.runTradePersonalStatFirstContactByCreateDay(
						managerId, sessionKey, noOwnerTrades);
			} else if (EagleConstants.BELONG_TYPE_PAY_LAST_TALK
					.equalsIgnoreCase(type)) {
				belongTypeBlh.runTradePersonalStatLastContactByPayDay(
						managerId, sessionKey, noOwnerTrades);
			} else if (EagleConstants.BELONG_TYPE_CREATE_LAST_TALK
					.equalsIgnoreCase(type)) {
				belongTypeBlh.runTradePersonalStatLastContactByCreateDay(
						managerId, sessionKey, noOwnerTrades);
			} else if (EagleConstants.BELONG_TYPE_CREATE_MOST_TALK
					.equalsIgnoreCase(type)) {
				belongTypeBlh.runTradePersonalStatMostContactByCreateDay(
						managerId, sessionKey, noOwnerTrades);
			} else if (EagleConstants.BELONG_TYPE_PAY_MOST_TALK
					.equalsIgnoreCase(type)) {
				belongTypeBlh.runTradePersonalStatMostContactByPayDay(
						managerId, sessionKey, noOwnerTrades);
			} else if (EagleConstants.BELONG_TYPE_FLAG.equalsIgnoreCase(type)) {
				belongTypeBlh.runTradePersonalStatFlag(managerId, sessionKey,
						noOwnerTrades);
			} else {
				belongTypeBlh.runTradePersonalStatFirstContactByPayDay(
						managerId, sessionKey, noOwnerTrades);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	public TradeSoldDAO getTradeSoldDao() {
		return tradeSoldDao;
	}

	public void setTradeSoldDao(TradeSoldDAO tradeSoldDao) {
		this.tradeSoldDao = tradeSoldDao;
	}

	private Map<Date, StatAchievementEO> initialStatAchieveEOMap(
			String managerId, Date start, Date end, String shopId,
			String staffId, Boolean isTotal) {
		Map<Date, StatAchievementEO> statAchievementEOsMap = new HashMap();
		long gap = DateUtil.getDateGap(start, end);
		for (int i = 0; i <= gap; i++) {
			if (DateUtil.getDateGap(DateUtil.getNextDay(start, i), new Date()) != 0) {
				StatAchievementEO statAchievementEO = new StatAchievementEO();
				StatAchievementEOId id = new StatAchievementEOId();
				id.setIsTotal(isTotal);
				id.setManagerId(managerId);
				id.setStaffId(staffId);
				statAchievementEO.setShopId(shopId);
				id.setStatDate(DateUtil.getNextDay(start, i));
				statAchievementEO.setId(id);
				statAchievementEOsMap.put(DateUtil.getNextDay(start, i),
						statAchievementEO);
			}
		}
		return statAchievementEOsMap;
	}

	@Override
	public void calulateTradeTotalStatByDay(String managerId, Date start,
			Date end, String shopId) {
		// TODO Auto-generated method stub
		// long a = System.currentTimeMillis();
		List<Map> tradeTotal = (List<Map>) this
				.runTradeTotalStatProcedureByDay(managerId, start, end);
		Boolean statZeroSwitch = managerInfoDao.getManagerInfoEO(managerId)
				.get(0).getStatZeroSwitch();
		Map<Date, StatAchievementEO> statAchievementEOsMap = new HashMap();
		if (statZeroSwitch) {
			statAchievementEOsMap = this.initialStatAchieveEOMap(managerId,
					start, end, shopId, managerId, true);
		}
		if (tradeTotal != null) {
			for (Map temp : tradeTotal) {
				Date payTime = (Date) temp.get("pay_time");
				if (statAchievementEOsMap.get(payTime) == null) {
					log.info("新增公司业绩 " + payTime);
					StatAchievementEO statAchievementEO = new StatAchievementEO();
					StatAchievementEOId id = new StatAchievementEOId();
					id.setIsTotal(true);
					id.setManagerId(managerId);
					id.setStaffId(managerId);
					if (shopId == null)
						shopId = (String) SessionManager
								.getSessionByKey(EagleConstants.TOPSHOPID);
					if (shopId == null) {
						GroupMemberEO member = groupMemberDao
								.getFirstGroupMember(managerId);
						shopId = member.getShopId();
					}
					statAchievementEO.setShopId(shopId);
					id.setStatDate(payTime);
					statAchievementEO.setId(id);
					String status = (String) temp.get("trade_status");
					BigDecimal postFee = (BigDecimal) (temp.get("post_fee") == null ? BigDecimal.ZERO
							: temp.get("post_fee"));
					// 设置等待发货和等待确认款项
					if (EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS
							.equals(status)) {
						BigDecimal paymentContainPostFee = (BigDecimal) (temp
								.get("payment") == null ? BigDecimal.ZERO
								: temp.get("payment"));
						statAchievementEO
								.setAmountWaitSend(paymentContainPostFee
										.subtract(postFee));
					} else if (EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS
							.equals(status)
							|| EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED
									.equals(status)) {
						BigDecimal paymentContainPostFee = (BigDecimal) (temp
								.get("payment") == null ? BigDecimal.ZERO
								: temp.get("payment"));
						statAchievementEO
								.setAmountWaitConfirm(paymentContainPostFee
										.subtract(postFee));
					} else {
						BigDecimal paymentContainPostFee = (BigDecimal) (temp
								.get("received_payment") == null ? BigDecimal.ZERO
								: temp.get("received_payment"));
						// 如果全额退款，则不剔除邮费
						// 部分退款，首先退商品金额
						if (EServiceConstants.TRADE_STATUS_TRADE_CLOSED
								.equals(status)
								&& BigDecimal.ZERO
										.compareTo(paymentContainPostFee) == 0) {
							statAchievementEO
									.setAmountSuccess(paymentContainPostFee);
						} else {
							// 设置已收到款项
							statAchievementEO
									.setAmountSuccess(paymentContainPostFee
											.subtract(postFee));
						}
					}
					// 设置全额退款
					statAchievementEO.setAmountAllRefund(((BigDecimal) (temp
							.get("all_refund_fee") == null ? BigDecimal.ZERO
							: temp.get("all_refund_fee"))));
					// 设置部分退款
					statAchievementEO.setAmountPartRefund(((BigDecimal) (temp
							.get("part_refund_fee") == null ? BigDecimal.ZERO
							: temp.get("part_refund_fee"))));
					// 设置退款中
					statAchievementEO.setAmountRefunding(((BigDecimal) (temp
							.get("refunding") == null ? BigDecimal.ZERO : temp
							.get("refunding"))));
					// 设置邮费
					statAchievementEO.setPostFee(postFee);
					// 设置总款项
					BigDecimal totalAmount = statAchievementEO
							.getAmountSuccess()
							.add(statAchievementEO.getAmountWaitConfirm())
							.add(statAchievementEO.getAmountWaitSend())
							.subtract(statAchievementEO.getAmountRefunding());
					statAchievementEO.setTotalAmount(totalAmount);
					statAchievementEOsMap.put(payTime, statAchievementEO);
				} else {
					log.info("修改公司业绩 " + payTime);
					StatAchievementEO statAchievementEO = statAchievementEOsMap
							.get(payTime);
					BigDecimal amountSuccess = BigDecimal.ZERO;
					String status = (String) temp.get("trade_status");
					BigDecimal amountWaitSend = BigDecimal.ZERO;
					BigDecimal amountWaitConfirm = BigDecimal.ZERO;
					BigDecimal postFee = (BigDecimal) (temp.get("post_fee") == null ? BigDecimal.ZERO
							: temp.get("post_fee"));
					if (EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS
							.equals(status)) {
						BigDecimal paymentContainPostFee = (BigDecimal) (temp
								.get("payment") == null ? BigDecimal.ZERO
								: temp.get("payment"));
						amountWaitSend = paymentContainPostFee
								.subtract(postFee);
					} else if (EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS
							.equals(status)
							|| EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED
									.equals(status)) {
						BigDecimal paymentContainPostFee = (BigDecimal) (temp
								.get("payment") == null ? BigDecimal.ZERO
								: temp.get("payment"));
						amountWaitConfirm = paymentContainPostFee
								.subtract(postFee);
					} else {
						BigDecimal paymentContainPostFee = (BigDecimal) (temp
								.get("received_payment") == null ? BigDecimal.ZERO
								: temp.get("received_payment"));
						// 如果全额退款，则不剔除邮费
						// 部分退款，首先退商品金额
						if (EServiceConstants.TRADE_STATUS_TRADE_CLOSED
								.equals(status)
								&& BigDecimal.ZERO
										.compareTo(paymentContainPostFee) == 0) {
							// 全额退款，设置已收到款项
							amountSuccess = paymentContainPostFee;
						} else {
							// 部分退款，设置已收到款项
							amountSuccess = paymentContainPostFee
									.subtract(postFee);
						}
					}
					BigDecimal allRefund = ((BigDecimal) (temp
							.get("all_refund_fee") == null ? BigDecimal.ZERO
							: temp.get("all_refund_fee")));
					BigDecimal partRefund = ((BigDecimal) (temp
							.get("part_refund_fee") == null ? BigDecimal.ZERO
							: temp.get("part_refund_fee")));
					BigDecimal refunding = ((BigDecimal) (temp.get("refunding") == null ? BigDecimal.ZERO
							: temp.get("refunding")));
					// 设置全额退款
					statAchievementEO.setAmountAllRefund(statAchievementEO
							.getAmountAllRefund().add(allRefund));
					// 设置部分退款
					statAchievementEO.setAmountPartRefund(statAchievementEO
							.getAmountPartRefund().add(partRefund));
					// 设置退款中
					statAchievementEO.setAmountRefunding(statAchievementEO
							.getAmountRefunding().add(refunding));
					// 设置已付款
					statAchievementEO.setAmountSuccess(statAchievementEO
							.getAmountSuccess().add(amountSuccess));
					// 设置待确认付款
					statAchievementEO.setAmountWaitConfirm(statAchievementEO
							.getAmountWaitConfirm().add(amountWaitConfirm));
					// 设置待发货付款
					statAchievementEO.setAmountWaitSend(statAchievementEO
							.getAmountWaitSend().add(amountWaitSend));
					// 设置邮费
					statAchievementEO.setPostFee(statAchievementEO.getPostFee()
							.add(postFee));
					// 设置总款项
					BigDecimal totalAmount = statAchievementEO
							.getAmountSuccess()
							.add(statAchievementEO.getAmountWaitConfirm())
							.add(statAchievementEO.getAmountWaitSend())
							.subtract(statAchievementEO.getAmountRefunding());
					statAchievementEO.setTotalAmount(totalAmount);
					statAchievementEOsMap.put(payTime, statAchievementEO);
				}
			}
		}
		// System.out.println("\n" + managerId + "业绩报表执行结束耗时 : "
		// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
		if (!statAchievementEOsMap.isEmpty()) {
			// 剔除不计算商品金额

			this.subtractShopNoCalAmount(managerId, start, end,
					statAchievementEOsMap);
			// 保存最终结果
			List<StatAchievementEO> list = new ArrayList<StatAchievementEO>(
					statAchievementEOsMap.values());
			tradeSoldDao.saveOrUpdateBatch(list);
		}
		// System.out.println("\n" + managerId + "业绩报表存储耗时 : "
		// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
	}

	@Override
	public void calulateTradeSumStatByDay(String managerId, Date start,
			Date end, String shopId) {
		// TODO Auto-generated method stub
		// long a = System.currentTimeMillis();
		if (shopId == null)
			shopId = (String) SessionManager
					.getSessionByKey(EagleConstants.TOPSHOPID);
		if (shopId == null) {
			GroupMemberEO member = groupMemberDao
					.getFirstGroupMember(managerId);
			shopId = member.getShopId();
		}
		List<Map> tradeTotal = (List<Map>) this.runTradeSumStatProcedureByDay(
				shopId, managerId, start, end);
		List<StatAchievementEO> shopStatList = new ArrayList();
		if (tradeTotal != null) {
			for (Map temp : tradeTotal) {
				Date payTime = (Date) temp.get("stat_date");
				log.info("新增公司业绩 " + payTime);
				StatAchievementEO statAchievementEO = new StatAchievementEO();
				StatAchievementEOId id = new StatAchievementEOId();
				id.setIsTotal(true);
				id.setManagerId(managerId);
				id.setStaffId(managerId);
				statAchievementEO.setShopId(shopId);
				id.setStatDate(payTime);
				statAchievementEO.setId(id);
				statAchievementEO.setAmountWaitSend((BigDecimal) (temp
						.get("sum_wait_send") == null ? BigDecimal.ZERO : temp
						.get("sum_wait_send")));
				statAchievementEO.setAmountWaitConfirm((BigDecimal) (temp
						.get("sum_wait_confirm") == null ? BigDecimal.ZERO
						: temp.get("sum_wait_confirm")));
				statAchievementEO.setAmountSuccess((BigDecimal) (temp
						.get("sum_success") == null ? BigDecimal.ZERO : temp
						.get("sum_success")));
				statAchievementEO.setAmountAllRefund((BigDecimal) (temp
						.get("sum_all_refund") == null ? BigDecimal.ZERO : temp
						.get("sum_all_refund")));
				statAchievementEO.setAmountPartRefund((BigDecimal) (temp
						.get("sum_part_refund") == null ? BigDecimal.ZERO
						: temp.get("sum_part_refund")));
				statAchievementEO.setAmountRefunding((BigDecimal) (temp
						.get("sum_refunding") == null ? BigDecimal.ZERO : temp
						.get("sum_refunding")));
				statAchievementEO.setPostFee((BigDecimal) (temp
						.get("sum_post_fee") == null ? BigDecimal.ZERO : temp
						.get("sum_post_fee")));
				statAchievementEO.setTotalAmount((BigDecimal) (temp
						.get("sum_total") == null ? BigDecimal.ZERO : temp
						.get("sum_total")));
				shopStatList.add(statAchievementEO);
			}
		}
		// System.out.println("\n" + managerId + "业绩报表执行结束耗时 : "
		// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
		if (!shopStatList.isEmpty()) {
			// 保存最终结果
			tradeSoldDao.saveOrUpdateBatch(shopStatList);
		}
		// System.out.println("\n" + managerId + "业绩报表存储耗时 : "
		// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
	}

	private Map<String, Map<Date, StatAchievementEO>> initialStatOwnerAchieveEOMap(
			String managerId, Date start, Date end, String shopId) {
		Map<String, Map<Date, StatAchievementEO>> statAchievementEOsMap = new HashMap();
		List<GroupMemberEO> members = groupMemberDao.findByManagerId(managerId);
		for (GroupMemberEO member : members) {
			Map<Date, StatAchievementEO> tempMap = this
					.initialStatAchieveEOMap(managerId, start, end, shopId,
							member.getId().getServiceStaffId(), false);
			statAchievementEOsMap.put(member.getId().getServiceStaffId(),
					tempMap);
		}
		return statAchievementEOsMap;
	}

	public void calulateTradeOwnersStatByDay(String managerId, Date start,
			Date end, String shopId) {
		// TODO Auto-generated method stub
		List<Map> tradeOwners = (List<Map>) this
				.runTradeOwnersStatProcedureByDay(managerId, start, end);
		Boolean statZeroSwitch = managerInfoDao.getManagerInfoEO(managerId)
				.get(0).getStatZeroSwitch();
		Map<String, Map<Date, StatAchievementEO>> statAchievementEOsMap = new HashMap();
		if (statZeroSwitch) {
			statAchievementEOsMap = this.initialStatOwnerAchieveEOMap(
					managerId, start, end, shopId);
		}
		if (tradeOwners != null) {
			if (shopId == null)
				shopId = (String) SessionManager
						.getSessionByKey(EagleConstants.TOPSHOPID);
			if (shopId == null) {
				GroupMemberEO member = groupMemberDao
						.getFirstGroupMember(managerId);
				shopId = member.getShopId();
			}
			for (Map temp : tradeOwners) {
				Date payTime = (Date) temp.get("pay_time");
				String tradeOwner = (String) temp.get("trade_owner");
				if (tradeOwner != null) {
					Map<Date, StatAchievementEO> tempMap = statAchievementEOsMap
							.get(tradeOwner);
					if (tempMap == null && statZeroSwitch) {
						tempMap = this.initialStatAchieveEOMap(managerId,
								start, end, shopId, tradeOwner, false);
					} else if (tempMap == null && !statZeroSwitch) {
						tempMap = new HashMap();
					}
					if (tempMap.get(payTime) == null) {
						log.info("新增个人业绩 " + tradeOwner + payTime);
						StatAchievementEO statAchievementEO = new StatAchievementEO();
						StatAchievementEOId id = new StatAchievementEOId();
						id.setIsTotal(false);
						id.setManagerId(managerId);
						id.setStaffId(tradeOwner);
						statAchievementEO.setShopId(shopId);
						id.setStatDate(payTime);
						statAchievementEO.setId(id);
						String status = (String) temp.get("trade_status");
						BigDecimal postFee = (BigDecimal) (temp.get("post_fee") == null ? BigDecimal.ZERO
								: temp.get("post_fee"));
						// 设置等待发货和等待确认款项
						if (EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS
								.equals(status)) {
							BigDecimal paymentContainPostFee = (BigDecimal) (temp
									.get("payment") == null ? BigDecimal.ZERO
									: temp.get("payment"));
							statAchievementEO
									.setAmountWaitSend(paymentContainPostFee
											.subtract(postFee));
						} else if (EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS
								.equals(status)
								|| EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED
										.equals(status)) {
							BigDecimal paymentContainPostFee = (BigDecimal) (temp
									.get("payment") == null ? BigDecimal.ZERO
									: temp.get("payment"));
							statAchievementEO
									.setAmountWaitConfirm(paymentContainPostFee
											.subtract(postFee));
						} else {
							BigDecimal paymentContainPostFee = (BigDecimal) (temp
									.get("received_payment") == null ? BigDecimal.ZERO
									: temp.get("received_payment"));
							// 如果全额退款，则不剔除邮费
							// 部分退款，首先退商品金额
							if (EServiceConstants.TRADE_STATUS_TRADE_CLOSED
									.equals(status)
									&& BigDecimal.ZERO
											.compareTo(paymentContainPostFee) == 0) {
								// 全额退款，设置已收到款项
								statAchievementEO
										.setAmountSuccess(paymentContainPostFee);
							} else {
								// 部分退款，设置已收到款项
								statAchievementEO
										.setAmountSuccess(paymentContainPostFee
												.subtract(postFee));
							}
						}
						// 设置全额退款
						statAchievementEO
								.setAmountAllRefund(((BigDecimal) (temp
										.get("all_refund_fee") == null ? BigDecimal.ZERO
										: temp.get("all_refund_fee"))));
						// 设置部分退款
						statAchievementEO
								.setAmountPartRefund(((BigDecimal) (temp
										.get("part_refund_fee") == null ? BigDecimal.ZERO
										: temp.get("part_refund_fee"))));
						// 设置退款中
						statAchievementEO
								.setAmountRefunding(((BigDecimal) (temp
										.get("refunding") == null ? BigDecimal.ZERO
										: temp.get("refunding"))));
						// 设置邮费
						statAchievementEO.setPostFee(postFee);
						// 设置总款项
						BigDecimal totalAmount = statAchievementEO
								.getAmountSuccess()
								.add(statAchievementEO.getAmountWaitConfirm())
								.add(statAchievementEO.getAmountWaitSend())
								.subtract(
										statAchievementEO.getAmountRefunding());
						statAchievementEO.setTotalAmount(totalAmount);
						tempMap.put(payTime, statAchievementEO);
						statAchievementEOsMap.put(tradeOwner, tempMap);
					} else {
						log.info("修改个人业绩 " + tradeOwner + payTime);
						StatAchievementEO statAchievementEO = tempMap
								.get(payTime);
						BigDecimal amountSuccess = BigDecimal.ZERO;
						String status = (String) temp.get("trade_status");
						BigDecimal amountWaitSend = BigDecimal.ZERO;
						BigDecimal amountWaitConfirm = BigDecimal.ZERO;
						BigDecimal postFee = (BigDecimal) (temp.get("post_fee") == null ? BigDecimal.ZERO
								: temp.get("post_fee"));
						if (EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS
								.equals(status)) {
							BigDecimal paymentContainPostFee = (BigDecimal) (temp
									.get("payment") == null ? BigDecimal.ZERO
									: temp.get("payment"));
							amountWaitSend = paymentContainPostFee
									.subtract(postFee);
						} else if (EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS
								.equals(status)
								|| EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED
										.equals(status)) {
							BigDecimal paymentContainPostFee = (BigDecimal) (temp
									.get("payment") == null ? BigDecimal.ZERO
									: temp.get("payment"));
							amountWaitConfirm = paymentContainPostFee
									.subtract(postFee);
						} else {
							BigDecimal paymentContainPostFee = (BigDecimal) (temp
									.get("received_payment") == null ? BigDecimal.ZERO
									: temp.get("received_payment"));
							// 如果全额退款，则不剔除邮费
							// 部分退款，首先退商品金额
							if (EServiceConstants.TRADE_STATUS_TRADE_CLOSED
									.equals(status)
									&& BigDecimal.ZERO
											.compareTo(paymentContainPostFee) == 0) {
								// 全额退款，设置已收到款项
								amountSuccess = paymentContainPostFee;
							} else {
								// 部分退款，设置已收到款项
								amountSuccess = paymentContainPostFee
										.subtract(postFee);
							}
						}
						BigDecimal allRefund = ((BigDecimal) (temp
								.get("all_refund_fee") == null ? BigDecimal.ZERO
								: temp.get("all_refund_fee")));
						BigDecimal partRefund = ((BigDecimal) (temp
								.get("part_refund_fee") == null ? BigDecimal.ZERO
								: temp.get("part_refund_fee")));
						;
						// 设置退款中
						BigDecimal refunding = ((BigDecimal) (temp
								.get("refunding") == null ? BigDecimal.ZERO
								: temp.get("refunding")));
						// 设置全额退款
						statAchievementEO.setAmountAllRefund(statAchievementEO
								.getAmountAllRefund().add(allRefund));
						// 设置部分退款
						statAchievementEO.setAmountPartRefund(statAchievementEO
								.getAmountPartRefund().add(partRefund));
						// 设置退款中
						statAchievementEO.setAmountRefunding(statAchievementEO
								.getAmountRefunding().add(refunding));
						// 设置已付款
						statAchievementEO.setAmountSuccess(statAchievementEO
								.getAmountSuccess().add(amountSuccess));
						// 设置待确认付款
						statAchievementEO
								.setAmountWaitConfirm(statAchievementEO
										.getAmountWaitConfirm().add(
												amountWaitConfirm));
						// 设置待发货付款
						statAchievementEO.setAmountWaitSend(statAchievementEO
								.getAmountWaitSend().add(amountWaitSend));
						// 设置邮费
						statAchievementEO.setPostFee(statAchievementEO
								.getPostFee().add(postFee));
						// 设置总款项
						BigDecimal totalAmount = statAchievementEO
								.getAmountSuccess()
								.add(statAchievementEO.getAmountWaitConfirm())
								.add(statAchievementEO.getAmountWaitSend())
								.subtract(
										statAchievementEO.getAmountRefunding());
						statAchievementEO.setTotalAmount(totalAmount);
						tempMap.put(payTime, statAchievementEO);
						statAchievementEOsMap.put(tradeOwner, tempMap);
					}
				}
			}
		}
		if (!statAchievementEOsMap.isEmpty()) {
			this.subtractStaffNoCalAmount(managerId, start, end,
					statAchievementEOsMap);
			for (String key : statAchievementEOsMap.keySet()) {
				tradeSoldDao.saveOrUpdateBatch(statAchievementEOsMap.get(key)
						.values());
			}
		}
	}

	private void subtractShopNoCalAmount(String managerId, Date start,
			Date end, Map<Date, StatAchievementEO> statAchievementEOsMap) {
		// 获取剔除商品
		List<NoCalculateItemsEO> items = noCalculateItemDao
				.getNoCalculateItemsByManagerId(managerId);
		if (items == null || items.isEmpty()) {
			return;
		}
		// 剔除不计算商品的非退款金额
		List<Map> noCalTradeTotalWithoutRefund = (List<Map>) this
				.runNoCalTradeAllShopWithoutRefundByDay(managerId, start, end);
		if (noCalTradeTotalWithoutRefund != null) {
			for (Map noCalTmp : noCalTradeTotalWithoutRefund) {
				Date payTime = (Date) noCalTmp.get("pay_time");
				StatAchievementEO statAchievementEO = statAchievementEOsMap
						.get(payTime);
				if (statAchievementEO != null) {
					log.info("剔除店铺非退款的不计入商品销售额 " + payTime);
					BigDecimal noCalPayment = (BigDecimal) (noCalTmp
							.get("payment") == null ? BigDecimal.ZERO
							: noCalTmp.get("payment"));
					String type = (String) noCalTmp.get("order_status");
					if (EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS
							.equals(type)) {
						statAchievementEO.setAmountWaitSend(statAchievementEO
								.getAmountWaitSend().subtract(noCalPayment));
					} else if (EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS
							.equals(type)
							|| EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED
									.equals(type)) {
						statAchievementEO
								.setAmountWaitConfirm(statAchievementEO
										.getAmountWaitConfirm().subtract(
												noCalPayment));
					} else {
						statAchievementEO.setAmountSuccess(statAchievementEO
								.getAmountSuccess().subtract(noCalPayment));
					}
					// 设置总款项
					BigDecimal totalAmount = statAchievementEO
							.getAmountSuccess()
							.add(statAchievementEO.getAmountWaitConfirm())
							.add(statAchievementEO.getAmountWaitSend())
							.subtract(statAchievementEO.getAmountRefunding());
					statAchievementEO.setTotalAmount(totalAmount);
					statAchievementEOsMap.put(payTime, statAchievementEO);
				}
			}
		}
		// 剔除不计算商品的退款金额
		List<Map> noCalTradeTotalRefund = (List<Map>) this
				.runNoCalTradeAllShopRefundByDay(managerId, start, end);
		if (noCalTradeTotalRefund != null) {
			for (Map noCalTmp : noCalTradeTotalRefund) {
				Date payTime = (Date) noCalTmp.get("pay_time");
				StatAchievementEO statAchievementEO = statAchievementEOsMap
						.get(payTime);
				if (statAchievementEO != null) {
					log.info("剔除店铺的不计入商品退款 " + payTime);
					BigDecimal noCalRefund = (BigDecimal) (noCalTmp
							.get("refund_fee") == null ? BigDecimal.ZERO
							: noCalTmp.get("refund_fee"));
					BigDecimal noCalPartPayment = (BigDecimal) (noCalTmp
							.get("payment") == null ? BigDecimal.ZERO
							: noCalTmp.get("payment"));
					String feeType = (String) noCalTmp.get("fee_type");
					if (EServiceConstants.FEE_TYPE_ALL_REFUND.equals(feeType)) {
						statAchievementEO.setAmountAllRefund(statAchievementEO
								.getAmountAllRefund().subtract(noCalRefund));
					} else if (EServiceConstants.FEE_TYPE_PART_REFUND
							.equals(feeType)) {
						statAchievementEO.setAmountPartRefund(statAchievementEO
								.getAmountPartRefund().subtract(noCalRefund));
						statAchievementEO.setAmountSuccess(statAchievementEO
								.getAmountSuccess().subtract(noCalPartPayment));
					} else if (EServiceConstants.FEE_TYPE_REFUNDING
							.equals(feeType)) {
						statAchievementEO.setAmountRefunding(statAchievementEO
								.getAmountRefunding().subtract(noCalRefund));
					}
					// 设置总款项
					BigDecimal totalAmount = statAchievementEO
							.getAmountSuccess()
							.add(statAchievementEO.getAmountWaitConfirm())
							.add(statAchievementEO.getAmountWaitSend())
							.subtract(statAchievementEO.getAmountRefunding());
					statAchievementEO.setTotalAmount(totalAmount);
					statAchievementEOsMap.put(payTime, statAchievementEO);
				}
			}
		}
	}

	private void subtractStaffNoCalAmount(String managerId, Date start,
			Date end,
			Map<String, Map<Date, StatAchievementEO>> statAchievementEOsMap) {
		// 获取剔除商品
		List<NoCalculateItemsEO> items = noCalculateItemDao
				.getNoCalculateItemsByManagerId(managerId);
		if (items == null || items.isEmpty()) {
			return;
		}
		// 剔除不计算商品的非退款金额
		List<Map> noCalTradeTotalWithoutRefund = (List<Map>) this
				.runNoCalTradeAllStaffWithoutRefundByDay(managerId, start, end);
		if (noCalTradeTotalWithoutRefund != null) {
			for (Map noCalTmp : noCalTradeTotalWithoutRefund) {
				Date payTime = (Date) noCalTmp.get("pay_time");
				String tradeOwner = (String) noCalTmp.get("trade_owner");
				if (statAchievementEOsMap.get(tradeOwner) == null)
					continue;
				StatAchievementEO statAchievementEO = statAchievementEOsMap
						.get(tradeOwner).get(payTime);
				if (statAchievementEO != null) {
					log.info("剔除个人非退款的不计入商品销售额 " + tradeOwner + payTime);
					BigDecimal noCalPayment = (BigDecimal) (noCalTmp
							.get("payment") == null ? BigDecimal.ZERO
							: noCalTmp.get("payment"));
					String type = (String) noCalTmp.get("order_status");
					if (EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS
							.equals(type)) {
						statAchievementEO.setAmountWaitSend(statAchievementEO
								.getAmountWaitSend().subtract(noCalPayment));
					} else if (EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS
							.equals(type)
							|| EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED
									.equals(type)) {
						statAchievementEO
								.setAmountWaitConfirm(statAchievementEO
										.getAmountWaitConfirm().subtract(
												noCalPayment));
					} else {
						statAchievementEO.setAmountSuccess(statAchievementEO
								.getAmountSuccess().subtract(noCalPayment));
					}
					// 设置总款项
					BigDecimal totalAmount = statAchievementEO
							.getAmountSuccess()
							.add(statAchievementEO.getAmountWaitConfirm())
							.add(statAchievementEO.getAmountWaitSend())
							.subtract(statAchievementEO.getAmountRefunding());
					statAchievementEO.setTotalAmount(totalAmount);
					Map<Date, StatAchievementEO> tempMap = statAchievementEOsMap
							.get(tradeOwner);
					tempMap.put(payTime, statAchievementEO);
					statAchievementEOsMap.put(tradeOwner, tempMap);
				}
			}
		}
		// 剔除不计算商品的退款金额
		List<Map> noCalTradeTotalRefund = (List<Map>) this
				.runNoCalTradeAllStaffRefundByDay(managerId, start, end);
		if (noCalTradeTotalRefund != null) {
			for (Map noCalTmp : noCalTradeTotalRefund) {
				Date payTime = (Date) noCalTmp.get("pay_time");
				String tradeOwner = (String) noCalTmp.get("trade_owner");
				if (statAchievementEOsMap.get(tradeOwner) == null)
					continue;
				StatAchievementEO statAchievementEO = statAchievementEOsMap
						.get(tradeOwner).get(payTime);
				if (statAchievementEO != null) {
					log.info("剔除个人的不计入商品退款 " + tradeOwner + payTime);
					BigDecimal noCalRefund = (BigDecimal) (noCalTmp
							.get("refund_fee") == null ? BigDecimal.ZERO
							: noCalTmp.get("refund_fee"));
					BigDecimal noCalPartPayment = (BigDecimal) (noCalTmp
							.get("payment") == null ? BigDecimal.ZERO
							: noCalTmp.get("payment"));
					String feeType = (String) noCalTmp.get("fee_type");
					if (EServiceConstants.FEE_TYPE_ALL_REFUND.equals(feeType)) {
						statAchievementEO.setAmountAllRefund(statAchievementEO
								.getAmountAllRefund().subtract(noCalRefund));
					} else if (EServiceConstants.FEE_TYPE_PART_REFUND
							.equals(feeType)) {
						statAchievementEO.setAmountPartRefund(statAchievementEO
								.getAmountPartRefund().subtract(noCalRefund));
						statAchievementEO.setAmountSuccess(statAchievementEO
								.getAmountSuccess().subtract(noCalPartPayment));
					} else if (EServiceConstants.FEE_TYPE_REFUNDING
							.equals(feeType)) {
						statAchievementEO.setAmountRefunding(statAchievementEO
								.getAmountRefunding().subtract(noCalRefund));
					}
					// 设置总款项
					BigDecimal totalAmount = statAchievementEO
							.getAmountSuccess()
							.add(statAchievementEO.getAmountWaitConfirm())
							.add(statAchievementEO.getAmountWaitSend())
							.subtract(statAchievementEO.getAmountRefunding());
					statAchievementEO.setTotalAmount(totalAmount);
					Map<Date, StatAchievementEO> tempMap = statAchievementEOsMap
							.get(tradeOwner);
					tempMap.put(payTime, statAchievementEO);
					statAchievementEOsMap.put(tradeOwner, tempMap);
				}
			}
		}
	}

	/**
	 * 获得待定交易
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TradeEO> getPendingTradeByManagerId(String managerId,
			Date start, Date end) {
		TradeQueryParametersMo queryMo = new TradeQueryParametersMo();
		queryMo.setTradeOwner(EagleConstants.PENDING_OWNER);
		queryMo.setStaffId(managerId);
		queryMo.setStart(start);
		queryMo.setEnd(end);
		return tradeSoldDao.getTradeEOsByManagerIdAndTimePreiod(queryMo);
	}

	/**
	 * 获得没有支付日期的交易
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TradeEO> getNoPaymentTradeByManagerId(String managerId,
			Date start, Date end) {
		TradeQueryParametersMo queryMo = new TradeQueryParametersMo();
		queryMo.setStaffId(managerId);
		queryMo.setStart(start);
		queryMo.setEnd(end);
		List<String> status = new ArrayList();
		status.add(EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED);
		status.add(EServiceConstants.TRADE_STATUS_TRADE_FINISHED);
		status.add(EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS);
		status.add(EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS);
		status.add(EServiceConstants.TRADE_STATUS_TRADE_CLOSED);
		queryMo.setStatus(status);
		queryMo.setHasPayTime(false);
		return tradeSoldDao.getTradeEOsByManagerIdAndTimePreiod(queryMo);
	}

	/**
	 * 获得无主交易数量
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 * @return
	 */
	public int getNoOwnerTradeNumByManagerId(String managerId, Date start,
			Date end) {
		TradeQueryParametersMo queryMo = new TradeQueryParametersMo();
		queryMo.setStaffId(managerId);
		queryMo.setStart(start);
		queryMo.setEnd(end);
		List<String> status = new ArrayList();
		status.add(EServiceConstants.TRADE_STATUS_TRADE_BUYER_SIGNED);
		status.add(EServiceConstants.TRADE_STATUS_TRADE_FINISHED);
		status.add(EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS);
		status.add(EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS);
		status.add(EServiceConstants.TRADE_STATUS_TRADE_CLOSED);
		queryMo.setStatus(status);
		queryMo.setHasTradeOwner(false);
		List<TradeEO> temp = tradeSoldDao
				.getTradeEOsByManagerIdAndTimePreiod(queryMo);
		if (temp != null) {
			return temp.size();
		}
		return 0;
	}

	private Map<String, Map<Date, StatAchievementNumEO>> initialStatOwnerAchieveNumEOMap(
			String managerId, Date start, Date end) {
		Map<String, Map<Date, StatAchievementNumEO>> statAchievementEOsMap = new HashMap();
		List<GroupMemberEO> members = groupMemberDao.findByManagerId(managerId);
		for (GroupMemberEO member : members) {
			Map<Date, StatAchievementNumEO> tempMap = this
					.initialStatAchieveNumEOMap(managerId, start, end, member
							.getId().getServiceStaffId(), false);
			statAchievementEOsMap.put(member.getId().getServiceStaffId(),
					tempMap);
		}
		return statAchievementEOsMap;
	}

	/**
	 * 计算客服销售数量业绩报表
	 * 
	 * @param managerId
	 * @param start
	 * @param end
	 */
	public void calulateTradeOwnersStatNumByDay(String managerId, Date start,
			Date end) {
		// TODO Auto-generated method stub
		List<Map> tradeOwnerNums = (List<Map>) this
				.runTradeStaffNumStatProcedureByDay(managerId, start, end);
		Boolean statZeroSwitch = managerInfoDao.getManagerInfoEO(managerId)
				.get(0).getStatZeroSwitch();
		Map<String, Map<Date, StatAchievementNumEO>> statAchievementNumEOsMap = new HashMap();
		if (statZeroSwitch) {
			statAchievementNumEOsMap = this.initialStatOwnerAchieveNumEOMap(
					managerId, start, end);
		}
		if (tradeOwnerNums != null) {
			for (Map temp : tradeOwnerNums) {
				Date payTime = (Date) temp.get("pay_time");
				String tradeOwner = (String) temp.get("staff_id");
				if (tradeOwner != null && payTime != null) {
					Map<Date, StatAchievementNumEO> tempMap = statAchievementNumEOsMap
							.get(tradeOwner);
					if (tempMap == null && statZeroSwitch) {
						tempMap = this.initialStatAchieveNumEOMap(managerId,
								start, end, tradeOwner, false);
					} else if (tempMap == null && !statZeroSwitch) {
						tempMap = new HashMap();
					}
					if (tempMap.get(payTime) == null) {
						log.info("新增个人数量业绩 " + tradeOwner + payTime);
						StatAchievementNumEO statAchievementNumEO = new StatAchievementNumEO();
						StatAchievementNumEOId id = new StatAchievementNumEOId();
						id.setIsTotal(false);
						id.setManagerId(managerId);
						id.setStaffId(tradeOwner);
						id.setStatDate(payTime);
						statAchievementNumEO.setId(id);
						// 订单状态
						String orderStatus = (String) temp.get("order_status");
						// 保单状态
						String refundStatus = (String) temp
								.get("refund_status");
						// 数量
						BigDecimal total = (BigDecimal) (temp.get("total") == null ? BigDecimal.ZERO
								: temp.get("total"));
						// 首先判断是否退款
						// 是，refundStatus<>CLOSED,NO_REFUND
						if (!EServiceConstants.REFUND_STATUS_CLOSED
								.equals(refundStatus)
								&& !EServiceConstants.REFUND_STATUS_NO_REFUND
										.equals(refundStatus)) {
							if (!EServiceConstants.REFUND_STATUS_SUCCESS
									.equals(refundStatus)) {
								// 判断refundStatus<>SUCCESS,计入退款中
								statAchievementNumEO.setNumRefunding(total);
							} else if (EServiceConstants.REFUND_STATUS_SUCCESS
									.equals(refundStatus)
									&& EServiceConstants.TRADE_STATUS_TRADE_CLOSED
											.equals(orderStatus)) {
								// refundStatus=SUCCESS，order_status=TRADE_CLOSED,计入全额退款
								statAchievementNumEO.setNumAllRefund(total);
							} else {
								// 其他，计入部分退款
								statAchievementNumEO.setNumPartRefund(total);
							}
						} else {
							// else，否
							if (EServiceConstants.TRADE_STATUS_TRADE_FINISHED
									.equals(orderStatus)
									|| EServiceConstants.TRADE_STATUS_TRADE_CLOSED
											.equals(orderStatus)) {
								// order_status=TRADE_FINISHED，TRADE_CLOSED，计入success
								statAchievementNumEO.setNumSuccess(total);
							} else if (EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS
									.equals(orderStatus)) {
								// order_status=WAIT_BUYER_CONFIRM_GOODS，计入waiting
								statAchievementNumEO.setNumWaitConfirm(total);
							} else if (EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS
									.equals(orderStatus)) {
								// order_status=WAIT_SELLER_SEND_GOODS，计入send
								statAchievementNumEO.setNumWaitSend(total);
							}
						}
						// 计算不包含退款交易数量
						BigDecimal totalNumNoRefund = statAchievementNumEO
								.getNumSuccess()
								.add(statAchievementNumEO.getNumWaitConfirm())
								.add(statAchievementNumEO.getNumWaitSend());
						statAchievementNumEO
								.setTotalNumNoRefund(totalNumNoRefund);
						// 计算包含退款交易数量
						BigDecimal totalNumHasRefund = statAchievementNumEO
								.getNumRefunding()
								.add(statAchievementNumEO.getNumPartRefund())
								.add(statAchievementNumEO.getNumAllRefund())
								.add(totalNumNoRefund);
						statAchievementNumEO
								.setTotalNumHasRefund(totalNumHasRefund);
						// 放入map
						tempMap.put(payTime, statAchievementNumEO);
						statAchievementNumEOsMap.put(tradeOwner, tempMap);

					} else {
						log.info("修改个人数量业绩 " + tradeOwner + payTime);
						StatAchievementNumEO statAchievementNumEO = tempMap
								.get(payTime);
						// 订单状态
						String orderStatus = (String) temp.get("order_status");
						// 保单状态
						String refundStatus = (String) temp
								.get("refund_status");
						// 数量
						BigDecimal total = (BigDecimal) (temp.get("total") == null ? BigDecimal.ZERO
								: temp.get("total"));
						// 首先判断是否退款
						// 是，refundStatus<>CLOSED,NO_REFUND
						if (!EServiceConstants.REFUND_STATUS_CLOSED
								.equals(refundStatus)
								&& !EServiceConstants.REFUND_STATUS_NO_REFUND
										.equals(refundStatus)) {
							if (!EServiceConstants.REFUND_STATUS_SUCCESS
									.equals(refundStatus)) {
								// 判断refundStatus<>SUCCESS,计入退款中
								statAchievementNumEO
										.setNumRefunding(statAchievementNumEO
												.getNumRefunding().add(total));
							} else if (EServiceConstants.REFUND_STATUS_SUCCESS
									.equals(refundStatus)
									&& EServiceConstants.TRADE_STATUS_TRADE_CLOSED
											.equals(orderStatus)) {
								// refundStatus=SUCCESS，order_status=TRADE_CLOSED,计入全额退款
								statAchievementNumEO
										.setNumAllRefund(statAchievementNumEO
												.getNumAllRefund().add(total));
							} else {
								// 其他，计入部分退款
								statAchievementNumEO
										.setNumPartRefund(statAchievementNumEO
												.getNumPartRefund().add(total));
							}
						} else {
							// else，否
							if (EServiceConstants.TRADE_STATUS_TRADE_FINISHED
									.equals(orderStatus)
									|| EServiceConstants.TRADE_STATUS_TRADE_CLOSED
											.equals(orderStatus)) {
								// order_status=TRADE_FINISHED，TRADE_CLOSED，计入success
								statAchievementNumEO
										.setNumSuccess(statAchievementNumEO
												.getNumSuccess().add(total));
							} else if (EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS
									.equals(orderStatus)) {
								// order_status=WAIT_BUYER_CONFIRM_GOODS，计入waiting
								statAchievementNumEO
										.setNumWaitConfirm(statAchievementNumEO
												.getNumWaitConfirm().add(total));
							} else if (EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS
									.equals(orderStatus)) {
								// order_status=WAIT_SELLER_SEND_GOODS，计入send
								statAchievementNumEO
										.setNumWaitSend(statAchievementNumEO
												.getNumWaitSend().add(total));
							}
						}
						// 计算不包含退款交易数量
						BigDecimal totalNumNoRefund = statAchievementNumEO
								.getNumSuccess()
								.add(statAchievementNumEO.getNumWaitConfirm())
								.add(statAchievementNumEO.getNumWaitSend());
						statAchievementNumEO
								.setTotalNumNoRefund(totalNumNoRefund);
						// 计算包含退款交易数量
						BigDecimal totalNumHasRefund = statAchievementNumEO
								.getNumRefunding()
								.add(statAchievementNumEO.getNumPartRefund())
								.add(statAchievementNumEO.getNumAllRefund())
								.add(totalNumNoRefund);
						statAchievementNumEO
								.setTotalNumHasRefund(totalNumHasRefund);
						// 放入map
						tempMap.put(payTime, statAchievementNumEO);
						statAchievementNumEOsMap.put(tradeOwner, tempMap);
					}
				}
			}
		}
		if (!statAchievementNumEOsMap.isEmpty()) {
			for (String key : statAchievementNumEOsMap.keySet()) {
				tradeSoldDao.saveOrUpdateBatch(statAchievementNumEOsMap
						.get(key).values());
			}
		}
	}

	private Map<Date, StatAchievementNumEO> initialStatAchieveNumEOMap(
			String managerId, Date start, Date end, String staffId,
			Boolean isTotal) {
		Map<Date, StatAchievementNumEO> statAchievementEOsMap = new HashMap();
		long gap = DateUtil.getDateGap(start, end);
		for (int i = 0; i <= gap; i++) {
			if (DateUtil.getDateGap(DateUtil.getNextDay(start, i), new Date()) != 0) {
				StatAchievementNumEO statAchievementNumEO = new StatAchievementNumEO();
				StatAchievementNumEOId id = new StatAchievementNumEOId();
				id.setIsTotal(isTotal);
				id.setManagerId(managerId);
				id.setStaffId(staffId);
				id.setStatDate(DateUtil.getNextDay(start, i));
				statAchievementNumEO.setId(id);
				statAchievementEOsMap.put(DateUtil.getNextDay(start, i),
						statAchievementNumEO);
			}
		}
		return statAchievementEOsMap;
	}

	@Override
	public void calulateTradeNumSumStatByDay(String managerId, Date start,
			Date end, String shopId) {
		// TODO Auto-generated method stub
		// long a = System.currentTimeMillis();
		if (shopId == null)
			shopId = (String) SessionManager
					.getSessionByKey(EagleConstants.TOPSHOPID);
		if (shopId == null) {
			GroupMemberEO member = groupMemberDao
					.getFirstGroupMember(managerId);
			shopId = member.getShopId();
		}
		List<Map> tradeTotal = (List<Map>) this
				.runTradeNumSumStatProcedureByDay(shopId, managerId, start, end);
		List<StatAchievementNumEO> shopStatList = new ArrayList();
		if (tradeTotal != null) {
			for (Map temp : tradeTotal) {
				Date payTime = (Date) temp.get("stat_date");
				log.info("新增公司业绩 " + payTime);
				StatAchievementNumEO statAchievementEO = new StatAchievementNumEO();
				StatAchievementNumEOId id = new StatAchievementNumEOId();
				id.setIsTotal(true);
				id.setManagerId(managerId);
				id.setStaffId(managerId);
				id.setStatDate(payTime);
				statAchievementEO.setId(id);
				statAchievementEO.setNumWaitSend((BigDecimal) (temp
						.get("sum_wait_send") == null ? BigDecimal.ZERO : temp
						.get("sum_wait_send")));
				statAchievementEO.setNumWaitConfirm((BigDecimal) (temp
						.get("sum_wait_confirm") == null ? BigDecimal.ZERO
						: temp.get("sum_wait_confirm")));
				statAchievementEO.setNumSuccess((BigDecimal) (temp
						.get("sum_success") == null ? BigDecimal.ZERO : temp
						.get("sum_success")));
				statAchievementEO.setNumAllRefund((BigDecimal) (temp
						.get("sum_all_refund") == null ? BigDecimal.ZERO : temp
						.get("sum_all_refund")));
				statAchievementEO.setNumPartRefund((BigDecimal) (temp
						.get("sum_part_refund") == null ? BigDecimal.ZERO
						: temp.get("sum_part_refund")));
				statAchievementEO.setNumRefunding((BigDecimal) (temp
						.get("sum_refunding") == null ? BigDecimal.ZERO : temp
						.get("sum_refunding")));
				statAchievementEO.setTotalNumNoRefund((BigDecimal) (temp
						.get("sum_total_no_refund") == null ? BigDecimal.ZERO
						: temp.get("sum_total_no_refund")));
				statAchievementEO.setTotalNumHasRefund((BigDecimal) (temp
						.get("sum_total_has_refund") == null ? BigDecimal.ZERO
						: temp.get("sum_total_has_refund")));
				shopStatList.add(statAchievementEO);
			}
		}
		// System.out.println("\n" + managerId + "业绩报表执行结束耗时 : "
		// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
		if (!shopStatList.isEmpty()) {
			// 保存最终结果
			tradeSoldDao.saveOrUpdateBatch(shopStatList);
		}
		// System.out.println("\n" + managerId + "业绩报表存储耗时 : "
		// + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
	}

	public GroupMemberDAO getGroupMemberDao() {
		return groupMemberDao;
	}

	public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
		this.groupMemberDao = groupMemberDao;
	}

	public ChatPeersDAO getChatPeersDao() {
		return chatPeersDao;
	}

	public void setChatPeersDao(ChatPeersDAO chatPeersDao) {
		this.chatPeersDao = chatPeersDao;
	}

	public IBelongTypeBlh getBelongTypeBlh() {
		return belongTypeBlh;
	}

	public void setBelongTypeBlh(IBelongTypeBlh belongTypeBlh) {
		this.belongTypeBlh = belongTypeBlh;
	}

	public NoCalculateItemDAO getNoCalculateItemDao() {
		return noCalculateItemDao;
	}

	public void setNoCalculateItemDao(NoCalculateItemDAO noCalculateItemDao) {
		this.noCalculateItemDao = noCalculateItemDao;
	}

	public ManagerInfoDAO getManagerInfoDao() {
		return managerInfoDao;
	}

	public void setManagerInfoDao(ManagerInfoDAO managerInfoDao) {
		this.managerInfoDao = managerInfoDao;
	}
}
