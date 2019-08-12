package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.util.Date;

public class StatStaffWorkLoadMO {

	BigDecimal sumWorkLoad = BigDecimal.ZERO;

	BigDecimal avgWorkLoad = BigDecimal.ZERO;

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

	public BigDecimal getSumWorkLoad() {
		return sumWorkLoad;
	}

	public void setSumWorkLoad(BigDecimal sumWorkLoad) {
		this.sumWorkLoad = sumWorkLoad;
	}

	public BigDecimal getAvgWorkLoad() {
		return avgWorkLoad;
	}

	public void setAvgWorkLoad(BigDecimal avgWorkLoad) {
		this.avgWorkLoad = avgWorkLoad;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
}
