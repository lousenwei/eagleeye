package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.util.Date;

public class StatShopOnlineTimeMO {

	BigDecimal onlineTime = BigDecimal.ZERO;

	String managerId;

	Date statDate;

	public BigDecimal getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(BigDecimal onlineTime) {
		this.onlineTime = onlineTime;
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
