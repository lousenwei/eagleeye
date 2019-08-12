package com.eagleeye.statistics.mo.dtl;

import java.math.BigDecimal;
import java.util.List;

public class StatStaffManBuyDtlResultMO {

	List<StatStaffManBuyDtlMO> statStaffManBuyMoList;

	BigDecimal avgManBuyAmount = BigDecimal.ZERO;


	public BigDecimal getAvgManBuyAmount() {
		return avgManBuyAmount;
	}

	public void setAvgManBuyAmount(BigDecimal avgManBuyAmount) {
		this.avgManBuyAmount = avgManBuyAmount;
	}

	public List<StatStaffManBuyDtlMO> getStatStaffManBuyMoList() {
		return statStaffManBuyMoList;
	}

	public void setStatStaffManBuyMoList(
			List<StatStaffManBuyDtlMO> statStaffManBuyMoList) {
		this.statStaffManBuyMoList = statStaffManBuyMoList;
	}
}
