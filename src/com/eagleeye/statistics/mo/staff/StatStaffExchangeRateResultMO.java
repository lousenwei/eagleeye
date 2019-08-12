package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.eagleeye.common.constant.EagleConstants;

public class StatStaffExchangeRateResultMO {

	List<StatStaffExchangeRateMO> exchangeRateMoList;

	BigDecimal avgExchangeRate = BigDecimal.ZERO;

	public BigDecimal getAvgExchangeRate() {
		return avgExchangeRate;
	}

	public void setAvgExchangeRate(BigDecimal avgExchangeRate) {
		this.avgExchangeRate = (avgExchangeRate == null ? BigDecimal.ZERO : avgExchangeRate).multiply(
				BigDecimal.valueOf(100)).setScale(EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public List<StatStaffExchangeRateMO> getExchangeRateMoList() {
		return exchangeRateMoList;
	}

	public void setExchangeRateMoList(List<StatStaffExchangeRateMO> exchangeRateMoList) {
		this.exchangeRateMoList = exchangeRateMoList;
	}

}
