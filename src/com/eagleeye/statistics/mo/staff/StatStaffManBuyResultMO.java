package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.util.List;

public class StatStaffManBuyResultMO {

	List<StatStaffManBuyMO> statStaffManBuyMoList;

	BigDecimal avgManBuyAmount = BigDecimal.ZERO;


	public BigDecimal getAvgManBuyAmount() {
		return avgManBuyAmount;
	}

	public void setAvgManBuyAmount(BigDecimal avgManBuyAmount) {
		this.avgManBuyAmount = avgManBuyAmount;
	}

	public List<StatStaffManBuyMO> getStatStaffManBuyMoList() {
		return statStaffManBuyMoList;
	}

	public void setStatStaffManBuyMoList(
			List<StatStaffManBuyMO> statStaffManBuyMoList) {
		this.statStaffManBuyMoList = statStaffManBuyMoList;
	}
}
