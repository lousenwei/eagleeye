package com.eagleeye.statistics.mo.dtl;

import java.math.BigDecimal;
import java.util.Date;

public class StatStaffAvgWaitTimeDtlMO {

	BigDecimal avgWaitTime = BigDecimal.ZERO;

	String managerId;

	Date statDate;

	String staffId;

	public BigDecimal getAvgWaitTime() {
		return avgWaitTime;
	}

	public void setAvgWaitTime(BigDecimal avgWaitTime) {
		this.avgWaitTime = avgWaitTime;
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
