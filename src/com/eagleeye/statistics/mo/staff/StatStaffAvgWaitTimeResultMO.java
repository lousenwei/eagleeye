package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.util.List;

public class StatStaffAvgWaitTimeResultMO {

	List<StatStaffAvgWaitTimeMO> avgWaitTimeMoList;

	BigDecimal avgShopWaitTime = BigDecimal.ZERO;

	public BigDecimal getAvgShopWaitTime() {
		return avgShopWaitTime;
	}

	public void setAvgShopWaitTime(BigDecimal avgShopWaitTime) {
		this.avgShopWaitTime = avgShopWaitTime;
	}

	public List<StatStaffAvgWaitTimeMO> getAvgWaitTimeMoList() {
		return avgWaitTimeMoList;
	}

	public void setAvgWaitTimeMoList(List<StatStaffAvgWaitTimeMO> avgWaitTimeMoList) {
		this.avgWaitTimeMoList = avgWaitTimeMoList;
	}
}
