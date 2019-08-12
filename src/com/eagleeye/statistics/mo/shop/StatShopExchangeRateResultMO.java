package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.eagleeye.common.constant.EagleConstants;

public class StatShopExchangeRateResultMO {

	List<StatShopExchangeRateMO> exchangeRateMoList;

	BigDecimal avgExchangeRate = BigDecimal.ZERO;

	public List<StatShopExchangeRateMO> getExchangeRateMoList() {
		return exchangeRateMoList;
	}

	public void setExchangeRateMoList(List<StatShopExchangeRateMO> exchangeRateMoList) {
		this.exchangeRateMoList = exchangeRateMoList;
	}

	public BigDecimal getAvgExchangeRate() {
		return avgExchangeRate;
	}

	public void setAvgExchangeRate(BigDecimal avgExchangeRate) {
		this.avgExchangeRate = (avgExchangeRate == null ? BigDecimal.ZERO : avgExchangeRate).multiply(
				BigDecimal.valueOf(100)).setScale(EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}
}
