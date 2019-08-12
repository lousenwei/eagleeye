package com.eagleeye.statistics.mo.dtl;

import java.math.BigDecimal;
import java.util.Date;

public class StatStaffWorkLoadDtlMO {

	BigDecimal workLoad = BigDecimal.ZERO;

	String managerId;

	Date statDate;

	String staffId;

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

	public BigDecimal getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(BigDecimal workLoad) {
		this.workLoad = workLoad;
	}
}
