package com.eagleeye.statistics.mo.dtl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.eagleeye.common.constant.EagleConstants;

public class StatStaffRefundRateDtlResultMO {

	List<StatStaffRefundRateDtlMO> refundRateMoList;

	BigDecimal refundAmount = BigDecimal.ZERO;

	BigDecimal overallAmount = BigDecimal.ZERO;

	BigDecimal avgRefundRate = BigDecimal.ZERO;

	public BigDecimal getAvgRefundRate() {
		return avgRefundRate;
	}

	public void setAvgRefundRate(BigDecimal avgRefundRate) {
		this.avgRefundRate = (avgRefundRate == null ? BigDecimal.ZERO : avgRefundRate)
				.multiply(BigDecimal.valueOf(100)).setScale(EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public List<StatStaffRefundRateDtlMO> getRefundRateMoList() {
		return refundRateMoList;
	}

	public void setRefundRateMoList(List<StatStaffRefundRateDtlMO> refundRateMoList) {
		this.refundRateMoList = refundRateMoList;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public BigDecimal getOverallAmount() {
		return overallAmount;
	}

	public void setOverallAmount(BigDecimal overallAmount) {
		this.overallAmount = overallAmount;
	}

}
