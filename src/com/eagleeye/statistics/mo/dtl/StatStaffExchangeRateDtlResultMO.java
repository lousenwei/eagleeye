package com.eagleeye.statistics.mo.dtl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.eagleeye.common.constant.EagleConstants;

public class StatStaffExchangeRateDtlResultMO {

	List<StatStaffExchangeRateDtlMO> exchangeRateMoList = new ArrayList();

	BigDecimal avgExchangeRate = BigDecimal.ZERO;

	BigDecimal sumReplyNum = BigDecimal.ZERO;

	BigDecimal sumPayNum = BigDecimal.ZERO;

	public List<StatStaffExchangeRateDtlMO> getExchangeRateMoList() {
		return exchangeRateMoList;
	}

	public void setExchangeRateMoList(List<StatStaffExchangeRateDtlMO> exchangeRateMoList) {
		this.exchangeRateMoList = exchangeRateMoList;
	}

	public BigDecimal getAvgExchangeRate() {
		return avgExchangeRate;
	}

	public void setAvgExchangeRate(BigDecimal avgExchangeRate) {
		this.avgExchangeRate = (avgExchangeRate == null ? BigDecimal.ZERO : avgExchangeRate).multiply(
				BigDecimal.valueOf(100)).setScale(EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public BigDecimal getSumReplyNum() {
		return sumReplyNum;
	}

	public void setSumReplyNum(BigDecimal sumReplyNum) {
		this.sumReplyNum = sumReplyNum;
	}

	public BigDecimal getSumPayNum() {
		return sumPayNum;
	}

	public void setSumPayNum(BigDecimal sumPayNum) {
		this.sumPayNum = sumPayNum;
	}

}
