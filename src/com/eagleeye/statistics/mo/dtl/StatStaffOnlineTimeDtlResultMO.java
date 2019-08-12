package com.eagleeye.statistics.mo.dtl;

import java.math.BigDecimal;
import java.util.List;

public class StatStaffOnlineTimeDtlResultMO {

	List<StatStaffOnlineTimeDtlMO> onlineMoList;

	BigDecimal avgStaffOnlineTime = BigDecimal.ZERO;

	BigDecimal sumStaffOnlineTime = BigDecimal.ZERO;

	BigDecimal sumStaffOnlineTimeForEightHours = BigDecimal.ZERO;



	public BigDecimal getAvgStaffOnlineTime() {
		return avgStaffOnlineTime;
	}

	public void setAvgStaffOnlineTime(BigDecimal avgStaffOnlineTime) {
		this.avgStaffOnlineTime = avgStaffOnlineTime;
	}

	public BigDecimal getSumStaffOnlineTime() {
		return sumStaffOnlineTime;
	}

	public void setSumStaffOnlineTime(BigDecimal sumStaffOnlineTime) {
		this.sumStaffOnlineTime = sumStaffOnlineTime;
	}

	public BigDecimal getSumStaffOnlineTimeForEightHours() {
		return sumStaffOnlineTimeForEightHours;
	}

	public void setSumStaffOnlineTimeForEightHours(BigDecimal sumStaffOnlineTimeForEightHours) {
		this.sumStaffOnlineTimeForEightHours = sumStaffOnlineTimeForEightHours;
	}

	public List<StatStaffOnlineTimeDtlMO> getOnlineMoList() {
		return onlineMoList;
	}

	public void setOnlineMoList(List<StatStaffOnlineTimeDtlMO> onlineMoList) {
		this.onlineMoList = onlineMoList;
	}

	
}
