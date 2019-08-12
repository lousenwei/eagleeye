package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.util.Date;

public class StatShopAvgWaitTimeMO {

	BigDecimal avgWaitTime = BigDecimal.ZERO;

	String managerId;

	Date statDate;

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
	
}
