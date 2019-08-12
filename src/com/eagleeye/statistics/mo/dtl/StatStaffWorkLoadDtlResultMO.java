package com.eagleeye.statistics.mo.dtl;

import java.math.BigDecimal;
import java.util.List;

public class StatStaffWorkLoadDtlResultMO {

	List<StatStaffWorkLoadDtlMO> workLoadMoList;

	BigDecimal staffTotalWorkLoad = BigDecimal.ZERO;

	BigDecimal staffAvgWorkLoad = BigDecimal.ZERO;

	public List<StatStaffWorkLoadDtlMO> getWorkLoadMoList() {
		return workLoadMoList;
	}

	public void setWorkLoadMoList(List<StatStaffWorkLoadDtlMO> workLoadMoList) {
		this.workLoadMoList = workLoadMoList;
	}

	public BigDecimal getStaffTotalWorkLoad() {
		return staffTotalWorkLoad;
	}

	public void setStaffTotalWorkLoad(BigDecimal staffTotalWorkLoad) {
		this.staffTotalWorkLoad = staffTotalWorkLoad;
	}

	public BigDecimal getStaffAvgWorkLoad() {
		return staffAvgWorkLoad;
	}

	public void setStaffAvgWorkLoad(BigDecimal staffAvgWorkLoad) {
		this.staffAvgWorkLoad = staffAvgWorkLoad;
	}

	public StatStaffWorkLoadDtlResultMO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
