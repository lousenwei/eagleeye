package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.util.List;

public class StatStaffWorkLoadResultMO {
	
	List<StatStaffWorkLoadMO> workLoadMoList;

	BigDecimal shopTotalWorkLoad = BigDecimal.ZERO;
	
	BigDecimal shopAvgWorkLoad=BigDecimal.ZERO;

	public BigDecimal getShopTotalWorkLoad() {
		return shopTotalWorkLoad;
	}

	public void setShopTotalWorkLoad(BigDecimal shopTotalWorkLoad) {
		this.shopTotalWorkLoad = shopTotalWorkLoad;
	}

	public BigDecimal getShopAvgWorkLoad() {
		return shopAvgWorkLoad;
	}

	public void setShopAvgWorkLoad(BigDecimal shopAvgWorkLoad) {
		this.shopAvgWorkLoad = shopAvgWorkLoad;
	}

	public List<StatStaffWorkLoadMO> getWorkLoadMoList() {
		return workLoadMoList;
	}

	public void setWorkLoadMoList(List<StatStaffWorkLoadMO> workLoadMoList) {
		this.workLoadMoList = workLoadMoList;
	}


}
