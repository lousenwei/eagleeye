package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.util.List;

public class StatStaffOnlineTimeResultMO {

	List<StatStaffOnlineTimeMO> avgOnlineMoList;

	BigDecimal avgShopOnlineTime = BigDecimal.ZERO;

	BigDecimal sumShopOnlineTime = BigDecimal.ZERO;

	BigDecimal sumShopOnlineTimeForEightHours = BigDecimal.ZERO;

	public List<StatStaffOnlineTimeMO> getAvgOnlineMoList() {
		return avgOnlineMoList;
	}

	public void setAvgOnlineMoList(List<StatStaffOnlineTimeMO> avgOnlineMoList) {
		this.avgOnlineMoList = avgOnlineMoList;
	}

	public BigDecimal getAvgShopOnlineTime() {
		return avgShopOnlineTime;
	}

	public void setAvgShopOnlineTime(BigDecimal avgShopOnlineTime) {
		this.avgShopOnlineTime = avgShopOnlineTime;
	}

	public BigDecimal getSumShopOnlineTime() {
		return sumShopOnlineTime;
	}

	public void setSumShopOnlineTime(BigDecimal sumShopOnlineTime) {
		this.sumShopOnlineTime = sumShopOnlineTime;
	}

	public BigDecimal getSumShopOnlineTimeForEightHours() {
		return sumShopOnlineTimeForEightHours;
	}

	public void setSumShopOnlineTimeForEightHours(
			BigDecimal sumShopOnlineTimeForEightHours) {
		this.sumShopOnlineTimeForEightHours = sumShopOnlineTimeForEightHours;
	}


}
