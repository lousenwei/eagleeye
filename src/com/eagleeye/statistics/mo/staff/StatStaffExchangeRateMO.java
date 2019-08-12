package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.eagleeye.common.constant.EagleConstants;

public class StatStaffExchangeRateMO {

	BigDecimal exchangeRate = BigDecimal.ZERO;
	
	String staffId;

	String managerId;

	Date statDate;

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = (exchangeRate == null ? BigDecimal.ZERO
				: exchangeRate).multiply(BigDecimal.valueOf(100)).setScale(
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

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

}
