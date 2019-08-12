package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.eagleeye.common.constant.EagleConstants;

public class StatShopRefundRateResultMO {

	List<StatShopRefundRateMO> refundRateMoList;

	BigDecimal avgRefundRate = BigDecimal.ZERO;

	public List<StatShopRefundRateMO> getRefundRateMoList() {
		return refundRateMoList;
	}

	public void setRefundRateMoList(List<StatShopRefundRateMO> refundRateMoList) {
		this.refundRateMoList = refundRateMoList;
	}

	public BigDecimal getAvgRefundRate() {
		return avgRefundRate;
	}

	public void setAvgRefundRate(BigDecimal avgRefundRate) {
		this.avgRefundRate = (avgRefundRate == null ? BigDecimal.ZERO : avgRefundRate)
				.multiply(BigDecimal.valueOf(100)).setScale(EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}
}
