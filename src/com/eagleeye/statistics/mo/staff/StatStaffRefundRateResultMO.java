package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.eagleeye.common.constant.EagleConstants;

public class StatStaffRefundRateResultMO {

	List<StatStaffRefundRateMO> refundRateMoList;

	BigDecimal avgRefundRate = BigDecimal.ZERO;

	public BigDecimal getAvgRefundRate() {
		return avgRefundRate;
	}

	public void setAvgRefundRate(BigDecimal avgRefundRate) {
		this.avgRefundRate = (avgRefundRate == null ? BigDecimal.ZERO : avgRefundRate)
				.multiply(BigDecimal.valueOf(100)).setScale(EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public List<StatStaffRefundRateMO> getRefundRateMoList() {
		return refundRateMoList;
	}

	public void setRefundRateMoList(List<StatStaffRefundRateMO> refundRateMoList) {
		this.refundRateMoList = refundRateMoList;
	}

}
