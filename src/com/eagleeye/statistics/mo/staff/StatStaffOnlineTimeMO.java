package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.util.Date;

public class StatStaffOnlineTimeMO {

	BigDecimal avgOnlineTime = BigDecimal.ZERO;

	BigDecimal sumOnlineTime = BigDecimal.ZERO;

	BigDecimal sumOnlineTimeForEightHours = BigDecimal.ZERO;

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

	public BigDecimal getAvgOnlineTime() {
		return avgOnlineTime;
	}

	public void setAvgOnlineTime(BigDecimal avgOnlineTime) {
		this.avgOnlineTime = avgOnlineTime;
	}

	public BigDecimal getSumOnlineTime() {
		return sumOnlineTime;
	}

	public void setSumOnlineTime(BigDecimal sumOnlineTime) {
		this.sumOnlineTime = sumOnlineTime;
	}

	public BigDecimal getSumOnlineTimeForEightHours() {
		return sumOnlineTimeForEightHours;
	}

	public void setSumOnlineTimeForEightHours(BigDecimal sumOnlineTimeForEightHours) {
		this.sumOnlineTimeForEightHours = sumOnlineTimeForEightHours;
	}

}
