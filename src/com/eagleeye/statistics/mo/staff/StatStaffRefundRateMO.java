package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.eagleeye.common.constant.EagleConstants;

public class StatStaffRefundRateMO {
	BigDecimal refundRate = BigDecimal.ZERO;

	String managerId;

	String staffId;
	
	Date statDate;

	public BigDecimal getRefundRate() {
		return refundRate;
	}

	public void setRefundRate(BigDecimal refundRate) {
		this.refundRate = (refundRate == null ? BigDecimal.ZERO : refundRate)
				.multiply(BigDecimal.valueOf(100)).setScale(
						EagleConstants.DEFAULT_NUMBERSCALE,
						RoundingMode.HALF_UP);
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
