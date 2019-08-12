package com.eagleeye.salary.mo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.eagleeye.common.constant.EagleConstants;

public class SalaryMO {

	String managerId;

	String serviceStaffId;

	Date start = new Date();

	Date end = new Date();

	String template;
	
	/**
	 * 基础工资
	 */
	private BigDecimal baseSalary = BigDecimal.ZERO;

	/**
	 * 结算方式
	 */
	private String settlementType;

	/**
	 * 是否完成指标
	 */
	private Boolean achieveTarget = false;

	/**
	 * 基线下提成
	 */
	private BigDecimal baseRateResult = BigDecimal.ZERO;

	/**
	 * 基线上提成
	 */
	private BigDecimal extraRateResult = BigDecimal.ZERO;

	/**
	 * 奖励部分
	 */
	private BigDecimal plusAmountResult = BigDecimal.ZERO;

	/**
	 * 惩罚部分
	 */
	private BigDecimal subtractAmountResult = BigDecimal.ZERO;

	/**
	 * 最佳销售奖
	 */
	private BigDecimal topSaleAmountPlusResult = BigDecimal.ZERO;

	/**
	 * 最快回复奖
	 */
	private BigDecimal topAvgReplyPlusResult = BigDecimal.ZERO;

	/**
	 * 最大工作量奖
	 */
	private BigDecimal topTotalReplyPlusResult = BigDecimal.ZERO;

	/**
	 * 最高转换率奖
	 */
	private BigDecimal topExchangeRatePlusResult = BigDecimal.ZERO;

	/**
	 * 最高平均客单价奖
	 */
	private BigDecimal topManBuyPlusResult = BigDecimal.ZERO;

	/**
	 * 零退款奖
	 */
	private BigDecimal zeroRefundPlusResult = BigDecimal.ZERO;

	/**
	 * 最慢回复罚
	 */
	private BigDecimal lastAvgReplySubtractResult = BigDecimal.ZERO;

	/**
	 * 最少工作量罚
	 */
	private BigDecimal lastTotalReplySubtractResult = BigDecimal.ZERO;

	/**
	 * 最低销售罚
	 */
	private BigDecimal lastSaleAmountSubtractResult = BigDecimal.ZERO;

	/**
	 * 最低转换率罚
	 */
	private BigDecimal lastExchangeRateSubtractResult = BigDecimal.ZERO;

	/**
	 * 最高退款罚
	 */
	private BigDecimal topRefundAmountSubtractResult = BigDecimal.ZERO;

	/**
	 * 手工调整额
	 */
	private BigDecimal adjustAmount = BigDecimal.ZERO;

	/**
	 * 不包含手工调整额度
	 */
	private BigDecimal finalSalaryTemp = BigDecimal.ZERO;

	/**
	 * 最终工资结算
	 */
	private BigDecimal finalSalaryResult = BigDecimal.ZERO;

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getServiceStaffId() {
		return serviceStaffId;
	}

	public void setServiceStaffId(String serviceStaffId) {
		this.serviceStaffId = serviceStaffId;
	}

	public BigDecimal getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(BigDecimal baseSalary) {
		this.baseSalary = baseSalary.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public BigDecimal getTopSaleAmountPlusResult() {
		return topSaleAmountPlusResult;
	}

	public void setTopSaleAmountPlusResult(BigDecimal topSaleAmountPlusResult) {
		this.topSaleAmountPlusResult = topSaleAmountPlusResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getTopAvgReplyPlusResult() {
		return topAvgReplyPlusResult;
	}

	public void setTopAvgReplyPlusResult(BigDecimal topAvgReplyPlusResult) {
		this.topAvgReplyPlusResult = topAvgReplyPlusResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getTopTotalReplyPlusResult() {
		return topTotalReplyPlusResult;
	}

	public void setTopTotalReplyPlusResult(BigDecimal topTotalReplyPlusResult) {
		this.topTotalReplyPlusResult = topTotalReplyPlusResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getTopExchangeRatePlusResult() {
		return topExchangeRatePlusResult;
	}

	public void setTopExchangeRatePlusResult(
			BigDecimal topExchangeRatePlusResult) {
		this.topExchangeRatePlusResult = topExchangeRatePlusResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getTopManBuyPlusResult() {
		return topManBuyPlusResult;
	}

	public void setTopManBuyPlusResult(BigDecimal topManBuyPlusResult) {
		this.topManBuyPlusResult = topManBuyPlusResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getZeroRefundPlusResult() {
		return zeroRefundPlusResult;
	}

	public void setZeroRefundPlusResult(BigDecimal zeroRefundPlusResult) {
		this.zeroRefundPlusResult = zeroRefundPlusResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getLastAvgReplySubtractResult() {
		return lastAvgReplySubtractResult;
	}

	public void setLastAvgReplySubtractResult(
			BigDecimal lastAvgReplySubtractResult) {
		this.lastAvgReplySubtractResult = lastAvgReplySubtractResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getLastTotalReplySubtractResult() {
		return lastTotalReplySubtractResult;
	}

	public void setLastTotalReplySubtractResult(
			BigDecimal lastTotalReplySubtractResult) {
		this.lastTotalReplySubtractResult = lastTotalReplySubtractResult
				.setScale(EagleConstants.DEFAULT_NUMBERSCALE,
						RoundingMode.HALF_UP);
	}

	public BigDecimal getLastSaleAmountSubtractResult() {
		return lastSaleAmountSubtractResult;
	}

	public void setLastSaleAmountSubtractResult(
			BigDecimal lastSaleAmountSubtractResult) {
		this.lastSaleAmountSubtractResult = lastSaleAmountSubtractResult
				.setScale(EagleConstants.DEFAULT_NUMBERSCALE,
						RoundingMode.HALF_UP);
	}

	public BigDecimal getLastExchangeRateSubtractResult() {
		return lastExchangeRateSubtractResult;
	}

	public void setLastExchangeRateSubtractResult(
			BigDecimal lastExchangeRateSubtractResult) {
		this.lastExchangeRateSubtractResult = lastExchangeRateSubtractResult
				.setScale(EagleConstants.DEFAULT_NUMBERSCALE,
						RoundingMode.HALF_UP);
	}

	public BigDecimal getTopRefundAmountSubtractResult() {
		return topRefundAmountSubtractResult;
	}

	public void setTopRefundAmountSubtractResult(
			BigDecimal topRefundAmountSubtractResult) {
		this.topRefundAmountSubtractResult = topRefundAmountSubtractResult
				.setScale(EagleConstants.DEFAULT_NUMBERSCALE,
						RoundingMode.HALF_UP);
	}

	public Boolean getAchieveTarget() {
		return achieveTarget;
	}

	public void setAchieveTarget(Boolean achieveTarget) {
		this.achieveTarget = achieveTarget;
	}

	public BigDecimal getBaseRateResult() {
		return baseRateResult;
	}

	public void setBaseRateResult(BigDecimal baseRateResult) {
		this.baseRateResult = baseRateResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getExtraRateResult() {
		return extraRateResult;
	}

	public void setExtraRateResult(BigDecimal extraRateResult) {
		this.extraRateResult = extraRateResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public BigDecimal getFinalSalaryResult() {
		return finalSalaryResult;
	}

	public void setFinalSalaryResult(BigDecimal finalSalaryResult) {
		this.finalSalaryResult = finalSalaryResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getPlusAmountResult() {
		return plusAmountResult;
	}

	public void setPlusAmountResult(BigDecimal plusAmountResult) {
		this.plusAmountResult = plusAmountResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getSubtractAmountResult() {
		return subtractAmountResult;
	}

	public void setSubtractAmountResult(BigDecimal subtractAmountResult) {
		this.subtractAmountResult = subtractAmountResult.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getAdjustAmount() {
		return adjustAmount;
	}

	public void setAdjustAmount(BigDecimal adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	public BigDecimal getFinalSalaryTemp() {
		return finalSalaryTemp;
	}

	public void setFinalSalaryTemp(BigDecimal finalSalaryTemp) {
		this.finalSalaryTemp = finalSalaryTemp;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
}
