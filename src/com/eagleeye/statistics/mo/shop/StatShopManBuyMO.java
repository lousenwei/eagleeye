package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.eagleeye.common.constant.EagleConstants;

public class StatShopManBuyMO {

	BigDecimal manBuyAmount = BigDecimal.ZERO;

	String managerId;

	Date statDate;

	public BigDecimal getManBuyAmount() {
		return manBuyAmount;
	}

	public void setManBuyAmount(BigDecimal manBuyAmount) {
		this.manBuyAmount = manBuyAmount.setScale(
				EagleConstants.DEFAULT_NUMBERSCALE, RoundingMode.HALF_UP);
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

}
