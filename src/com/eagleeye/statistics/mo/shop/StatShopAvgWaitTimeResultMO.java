package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.util.List;

public class StatShopAvgWaitTimeResultMO {
	List<StatShopAvgWaitTimeMO> avgWaitTimeMoList;

	BigDecimal avgShopWaitTime = BigDecimal.ZERO;

	public List<StatShopAvgWaitTimeMO> getAvgWaitTimeMoList() {
		return avgWaitTimeMoList;
	}

	public void setAvgWaitTimeMoList(List<StatShopAvgWaitTimeMO> avgWaitTimeMoList) {
		this.avgWaitTimeMoList = avgWaitTimeMoList;
	}

	public BigDecimal getAvgShopWaitTime() {
		return avgShopWaitTime;
	}

	public void setAvgShopWaitTime(BigDecimal avgShopWaitTime) {
		this.avgShopWaitTime = avgShopWaitTime;
	}
}
