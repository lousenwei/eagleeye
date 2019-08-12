package com.eagleeye.statistics.mo.dtl;

import java.math.BigDecimal;
import java.util.List;

public class StatStaffAvgWaitTimeDtlResultMO {

	List<StatStaffAvgWaitTimeDtlMO> avgWaitTimeMoList;

	BigDecimal avgStaffWaitTime = BigDecimal.ZERO;

	public BigDecimal getAvgStaffWaitTime() {
		return avgStaffWaitTime;
	}

	public void setAvgStaffWaitTime(BigDecimal avgStaffWaitTime) {
		this.avgStaffWaitTime = avgStaffWaitTime;
	}

	public List<StatStaffAvgWaitTimeDtlMO> getAvgWaitTimeMoList() {
		return avgWaitTimeMoList;
	}

	public void setAvgWaitTimeMoList(List<StatStaffAvgWaitTimeDtlMO> avgWaitTimeMoList) {
		this.avgWaitTimeMoList = avgWaitTimeMoList;
	}
}
