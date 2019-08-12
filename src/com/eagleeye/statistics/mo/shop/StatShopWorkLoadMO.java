package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.util.Date;

public class StatShopWorkLoadMO {

	BigDecimal workLoad = BigDecimal.ZERO;

	String managerId;

	Date statDate;

	public BigDecimal getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(BigDecimal workLoad) {
		this.workLoad = workLoad;
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
