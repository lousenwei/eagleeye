package com.eagleeye.salary.blh.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.salary.blh.ISalaryCalculateBlh;
import com.eagleeye.salary.dao.impl.SalaryConfigDAO;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.mo.SalaryMO;
import com.eagleeye.salary.mo.SalaryRankMO;
import com.eagleeye.salary.mo.SalaryResultMO;
import com.eagleeye.statistics.dao.RankStatisticsDAO;
import com.eagleeye.user.dao.GroupMemberDAO;
import com.eagleeye.user.eo.GroupMemberEO;

public class SalaryCalculateBlhImpl implements ISalaryCalculateBlh {

	RankStatisticsDAO rankStatisticsDao;
	GroupMemberDAO groupMemberDao;
	SalaryConfigDAO salaryConfigDao;

	@Override
	public SalaryResultMO calculateSalaryByTimeAndManagerId(String managerId,
			Date start, Date end, SalaryConfigEO salaryConfig) {
		// TODO Auto-generated method stub
		SalaryConfigEO calculateSalaryConfig = salaryConfig;
		// step 1. 获取排名mo
		SalaryRankMO salaryRankMo = this.getSalaryRankMO(managerId, start, end,
				salaryConfig.getSettlementType(),
				calculateSalaryConfig.getNoManagerFlg());
		// step 2. 计算工资
		List<GroupMemberEO> members = groupMemberDao.findByManagerId(managerId);
		if (members != null && !members.isEmpty()) {
			SalaryResultMO salaryResultMo = new SalaryResultMO();
			salaryResultMo.setEnd(new SimpleDateFormat("yyyy-MM-dd")
					.format(end));
			salaryResultMo.setStart(new SimpleDateFormat("yyyy-MM-dd")
					.format(start));
			salaryResultMo.setSettlementType(salaryConfig.getSettlementType());
			for (GroupMemberEO member : members) {
				if (calculateSalaryConfig.getNoManagerFlg()
						&& member.getId().getServiceStaffId().equals(managerId)) {
					continue;
				}
				String staffId = member.getId().getServiceStaffId();
				// 2011-12-3，lou，逻辑修改
				// 1.获取个性化模版
				SalaryConfigEO customSalaryConfig = salaryConfigDao
						.getSalaryConfigByManagerIdAndStaffId(managerId,
								staffId);
				if (customSalaryConfig != null) {
					calculateSalaryConfig = customSalaryConfig;
				}
				// 2.如果取不到，获取通用模版

				SalaryMO salaryMo = new SalaryMO();
				salaryMo.setManagerId(managerId);
				salaryMo.setStart(start);
				salaryMo.setEnd(end);
				salaryMo.setServiceStaffId(staffId);
				// 使用的模版
				salaryMo.setTemplate(calculateSalaryConfig.getId()
						.getTemplateName());
				// 最终结果
				BigDecimal finalSalary = BigDecimal.ZERO;
				// 奖励部分
				BigDecimal plusAmount = BigDecimal.ZERO;
				// 惩罚部分
				BigDecimal subtractAmount = BigDecimal.ZERO;
				// 1.基础工资
				salaryMo.setBaseSalary(calculateSalaryConfig.getBaseSalary());
				// 2.选择结算方式
				String type = calculateSalaryConfig.getSettlementType();
				salaryMo.setSettlementType(type);

				if (EagleConstants.SALARY_CALCULATE_BY_NUM.equals(type)) {
					// 按数量结算
					// 3.计算基线下和基线上提成
					BigDecimal saleNum = salaryRankMo.getStaffNum()
							.get(staffId);
					if (saleNum != null) {
						if (saleNum.compareTo(calculateSalaryConfig
								.getAchievementNumTarget()) <= 0) {
							// 基础部分
							BigDecimal baseAmount = saleNum
									.multiply(calculateSalaryConfig
											.getNumBaseRate());
							salaryMo.setBaseRateResult(baseAmount);
						} else {
							// 基础部分
							BigDecimal baseAmount = calculateSalaryConfig
									.getAchievementNumTarget().multiply(
											calculateSalaryConfig
													.getNumBaseRate());
							// 超出部分
							BigDecimal extraNum = saleNum
									.subtract(calculateSalaryConfig
											.getAchievementNumTarget());
							BigDecimal extraAmount = extraNum
									.multiply(calculateSalaryConfig
											.getNumExtraRate());
							salaryMo.setBaseRateResult(baseAmount);
							salaryMo.setExtraRateResult(extraAmount);
							salaryMo.setAchieveTarget(true);
						}
					}
				} else {
					// 按金额结算
					// 3.计算基线下和基线上提成
					BigDecimal saleAmount = salaryRankMo.getStaffSale().get(
							staffId);
					if (saleAmount != null) {
						if (saleAmount.compareTo(calculateSalaryConfig
								.getAchievementAmountTarget()) <= 0) {
							// 基础部分
							BigDecimal baseAmount = saleAmount.multiply(
									calculateSalaryConfig.getAmountBaseRate())
									.divide(BigDecimal.valueOf(100));
							salaryMo.setBaseRateResult(baseAmount);
						} else {
							// 基础部分
							BigDecimal baseAmount = calculateSalaryConfig
									.getAchievementAmountTarget()
									.multiply(
											calculateSalaryConfig
													.getAmountBaseRate())
									.divide(BigDecimal.valueOf(100));
							// 超出部分
							BigDecimal extraSaleAmount = saleAmount
									.subtract(calculateSalaryConfig
											.getAchievementAmountTarget());
							BigDecimal extraAmountResult = extraSaleAmount
									.multiply(
											calculateSalaryConfig
													.getAmountExtraRate())
									.divide(BigDecimal.valueOf(100));
							salaryMo.setBaseRateResult(baseAmount);
							salaryMo.setExtraRateResult(extraAmountResult);
							salaryMo.setAchieveTarget(true);
						}
					}
				}
				// 4.计算奖励制度
				// 4.1.销售冠军
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getTopSaleAmountPlus()) != 0
						&& salaryRankMo.getStaffSaleRank()
								.get(salaryRankMo.TOP_RANK).equals(staffId)) {
					salaryMo.setTopSaleAmountPlusResult(calculateSalaryConfig
							.getTopSaleAmountPlus());
				}
				// 4.2.最快回复
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getTopAvgReplyPlus()) != 0
						&& salaryRankMo.getStaffReplyTimeRank()
								.get(salaryRankMo.TOP_RANK).equals(staffId)) {
					salaryMo.setTopAvgReplyPlusResult(calculateSalaryConfig
							.getTopAvgReplyPlus());
				}
				// 4.3.最高工作量
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getTopTotalReplyPlus()) != 0
						&& salaryRankMo.getStaffReplyNumRank()
								.get(salaryRankMo.TOP_RANK).equals(staffId)) {
					salaryMo.setTopTotalReplyPlusResult(calculateSalaryConfig
							.getTopTotalReplyPlus());
				}
				// 4.4.最高转换率
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getTopExchangeRatePlus()) != 0
						&& salaryRankMo.getStaffExchangeRank()
								.get(salaryRankMo.TOP_RANK).equals(staffId)) {
					salaryMo.setTopExchangeRatePlusResult(calculateSalaryConfig
							.getTopExchangeRatePlus());
				}
				// 4.5.最高客单价
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getTopManBuyPlus()) != 0
						&& salaryRankMo.getStaffManBuyRank()
								.get(salaryRankMo.TOP_RANK).equals(staffId)) {
					salaryMo.setTopManBuyPlusResult(calculateSalaryConfig
							.getTopManBuyPlus());
				}
				// 4.6.零退款
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getZeroRefundPlus()) != 0
						&& salaryRankMo.getStaffRefundRank().get(staffId) == null) {
					salaryMo.setZeroRefundPlusResult(calculateSalaryConfig
							.getZeroRefundPlus());
				}
				// 5.计算惩罚制度
				// 5.1.销售垫底
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getLastSaleAmountSubtract()) != 0
						&& salaryRankMo.getStaffSaleRank()
								.get(salaryRankMo.LAST_RANK).equals(staffId)) {
					salaryMo.setLastSaleAmountSubtractResult(calculateSalaryConfig
							.getLastSaleAmountSubtract());
				}
				// 5.2.最慢回复
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getLastAvgReplySubtract()) != 0
						&& salaryRankMo.getStaffReplyTimeRank()
								.get(salaryRankMo.LAST_RANK).equals(staffId)) {
					salaryMo.setLastAvgReplySubtractResult(calculateSalaryConfig
							.getLastAvgReplySubtract());
				}
				// 5.3.最少工作量
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getLastTotalReplySubtract()) != 0
						&& salaryRankMo.getStaffReplyNumRank()
								.get(salaryRankMo.LAST_RANK).equals(staffId)) {
					salaryMo.setLastTotalReplySubtractResult(calculateSalaryConfig
							.getLastTotalReplySubtract());
				}
				// 5.4.最低转换率
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getLastExchangeRateSubtract()) != 0
						&& salaryRankMo.getStaffExchangeRank()
								.get(salaryRankMo.LAST_RANK).equals(staffId)) {
					salaryMo.setLastExchangeRateSubtractResult(calculateSalaryConfig
							.getLastExchangeRateSubtract());
				}
				// 5.5.最高退款率
				if (BigDecimal.ZERO.compareTo(calculateSalaryConfig
						.getTopRefundAmountSubtract()) != 0
						&& salaryRankMo.getStaffRefundRank().get(staffId) != null
						&& salaryRankMo.getStaffRefundRank().get(staffId) == 1) {
					salaryMo.setTopRefundAmountSubtractResult(calculateSalaryConfig
							.getTopRefundAmountSubtract());
				}
				// 工资基础部分
				finalSalary = salaryMo.getBaseSalary()
						.add(salaryMo.getBaseRateResult())
						.add(salaryMo.getExtraRateResult());
				// 工资奖励部分
				plusAmount = plusAmount
						.add(salaryMo.getTopAvgReplyPlusResult())
						.add(salaryMo.getTopExchangeRatePlusResult())
						.add(salaryMo.getTopManBuyPlusResult())
						.add(salaryMo.getTopSaleAmountPlusResult())
						.add(salaryMo.getTopTotalReplyPlusResult())
						.add(salaryMo.getZeroRefundPlusResult());
				finalSalary = finalSalary.add(plusAmount);
				salaryMo.setPlusAmountResult(plusAmount);
				// 工资惩罚部分
				subtractAmount = subtractAmount
						.add(salaryMo.getLastAvgReplySubtractResult())
						.add(salaryMo.getLastExchangeRateSubtractResult())
						.add(salaryMo.getLastSaleAmountSubtractResult())
						.add(salaryMo.getLastTotalReplySubtractResult())
						.add(salaryMo.getTopRefundAmountSubtractResult());
				finalSalary = finalSalary.subtract(subtractAmount);
				salaryMo.setSubtractAmountResult(subtractAmount);
				// 设置最终工资
				salaryMo.setFinalSalaryResult(finalSalary);
				// 设置最终工资
				salaryMo.setFinalSalaryTemp(finalSalary);
				// 加入每个客服数据
				salaryResultMo.getSalaryMoList().add(salaryMo);
			}
			if (salaryResultMo.getSalaryMoList() != null
					&& !salaryResultMo.getSalaryMoList().isEmpty()) {
				return salaryResultMo;
			}
		}
		return null;
	}

	public SalaryRankMO getSalaryRankMO(String managerId, Date start, Date end,
			String type, Boolean noManager) {

		SalaryRankMO salaryRankMo = new SalaryRankMO();
		// sale rank
		List<Map> rankStaffAchievements = (List<Map>) rankStatisticsDao
				.getRankStaffAchievementByTimePeriod(managerId, start, end,
						noManager);
		if (rankStaffAchievements != null && !rankStaffAchievements.isEmpty()) {
			int i = 1;
			for (Map temp : rankStaffAchievements) {
				String staffId = (String) temp.get("staff_id");
				BigDecimal num = (temp.get("total") == null ? BigDecimal.ZERO
						: (BigDecimal) temp.get("total"));
				salaryRankMo.getStaffSale().put(staffId, num);
				if (i == 1) {
					salaryRankMo.getStaffSaleRank().put(salaryRankMo.TOP_RANK,
							staffId);
				} 
				if (i == rankStaffAchievements.size()) {
					salaryRankMo.getStaffSaleRank().put(salaryRankMo.LAST_RANK,
							staffId);
				}
				i++;
			}
		}

		// num rank
		{
			List<Map> rankStaffNums = (List<Map>) rankStatisticsDao
					.getRankStaffNumByTimePreiod(managerId, start, end,
							noManager);
			if (rankStaffNums != null && !rankStaffNums.isEmpty()) {
				for (Map temp : rankStaffNums) {
					String staffId = (String) temp.get("staff_id");
					BigDecimal achievement = (temp.get("total") == null ? BigDecimal.ZERO
							: (BigDecimal) temp.get("total"));
					salaryRankMo.getStaffNum().put(staffId, achievement);
				}
			}
		}

		// refund rank
		List<Map> rankStaffRefunds = (List<Map>) rankStatisticsDao
				.getRankStaffRefundByTimePeriod(managerId, start, end,
						noManager);
		if (rankStaffRefunds != null && !rankStaffRefunds.isEmpty()) {
			int i = 1;
			for (Map temp : rankStaffRefunds) {
				String staffId = (String) temp.get("staff_id");
				salaryRankMo.getStaffRefundRank().put(staffId, i);
				i++;
			}
		}
		// avg wait time rank
		List<Map> rankStaffAvgWaitTime = (List<Map>) rankStatisticsDao
				.getRankStaffAvgWaitTimeByTimePeriod(managerId, start, end,
						noManager);
		if (rankStaffAvgWaitTime != null && !rankStaffAvgWaitTime.isEmpty()) {
			String topStaffId = (String) rankStaffAvgWaitTime.get(0).get(
					"service_staff_id");
			String lastStaffId = (String) rankStaffAvgWaitTime.get(
					rankStaffAvgWaitTime.size() - 1).get("service_staff_id");
			salaryRankMo.getStaffReplyTimeRank().put(salaryRankMo.TOP_RANK,
					topStaffId);
			salaryRankMo.getStaffReplyTimeRank().put(salaryRankMo.LAST_RANK,
					lastStaffId);
		}
		// work load rank
		List<Map> rankStaffWorkLoad = (List<Map>) rankStatisticsDao
				.getRankStaffWorkloadByTimePreiod(managerId, start, end,
						noManager);
		if (rankStaffWorkLoad != null && !rankStaffWorkLoad.isEmpty()) {
			String topStaffId = (String) rankStaffWorkLoad.get(0).get(
					"service_staff_id");
			String lastStaffId = (String) rankStaffWorkLoad.get(
					rankStaffWorkLoad.size() - 1).get("service_staff_id");
			salaryRankMo.getStaffReplyNumRank().put(salaryRankMo.TOP_RANK,
					topStaffId);
			salaryRankMo.getStaffReplyNumRank().put(salaryRankMo.LAST_RANK,
					lastStaffId);
		}
		// exchange rate rank
		List<Map> rankStaffExchangeRate = (List<Map>) rankStatisticsDao
				.getRankStaffExchangeRateByTimePreiod(managerId, start, end,
						noManager);
		if (rankStaffExchangeRate != null && !rankStaffExchangeRate.isEmpty()) {
			String topStaffId = (String) rankStaffExchangeRate.get(0).get(
					"service_staff_id");
			String lastStaffId = (String) rankStaffExchangeRate.get(
					rankStaffExchangeRate.size() - 1).get("service_staff_id");
			salaryRankMo.getStaffExchangeRank().put(salaryRankMo.TOP_RANK,
					topStaffId);
			salaryRankMo.getStaffExchangeRank().put(salaryRankMo.LAST_RANK,
					lastStaffId);
		}
		// man buy
		List<Map> rankStaffManBuy = (List<Map>) rankStatisticsDao
				.getRankStaffManBuyByTimePreiod(managerId, start, end,
						noManager);
		if (rankStaffManBuy != null && !rankStaffManBuy.isEmpty()) {
			String topStaffId = (String) rankStaffManBuy.get(0).get(
					"trade_owner");
			String lastStaffId = (String) rankStaffManBuy.get(
					rankStaffManBuy.size() - 1).get("trade_owner");
			salaryRankMo.getStaffManBuyRank().put(salaryRankMo.TOP_RANK,
					topStaffId);
			salaryRankMo.getStaffManBuyRank().put(salaryRankMo.LAST_RANK,
					lastStaffId);
		}
		return salaryRankMo;

	}

	public RankStatisticsDAO getRankStatisticsDao() {
		return rankStatisticsDao;
	}

	public void setRankStatisticsDao(RankStatisticsDAO rankStatisticsDao) {
		this.rankStatisticsDao = rankStatisticsDao;
	}

	public GroupMemberDAO getGroupMemberDao() {
		return groupMemberDao;
	}

	public void setGroupMemberDao(GroupMemberDAO groupMemberDao) {
		this.groupMemberDao = groupMemberDao;
	}

	public SalaryConfigDAO getSalaryConfigDao() {
		return salaryConfigDao;
	}

	public void setSalaryConfigDao(SalaryConfigDAO salaryConfigDao) {
		this.salaryConfigDao = salaryConfigDao;
	}

}
