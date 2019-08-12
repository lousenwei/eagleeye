package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.util.List;

public class StatShopOnlineTimeResultMO {

	List<StatShopOnlineTimeMO> avgOnlineTimeMoList;

	BigDecimal avgShopOnlineTime = BigDecimal.ZERO;


	public List<StatShopOnlineTimeMO> getAvgOnlineTimeMoList() {
		return avgOnlineTimeMoList;
	}

	public void setAvgOnlineTimeMoList(
			List<StatShopOnlineTimeMO> avgOnlineTimeMoList) {
		this.avgOnlineTimeMoList = avgOnlineTimeMoList;
	}

	public BigDecimal getAvgShopOnlineTime() {
		return avgShopOnlineTime;
	}

	public void setAvgShopOnlineTime(BigDecimal avgShopOnlineTime) {
		this.avgShopOnlineTime = avgShopOnlineTime;
	}



}
