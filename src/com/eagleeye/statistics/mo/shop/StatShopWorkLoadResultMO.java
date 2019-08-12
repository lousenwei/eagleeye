package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.util.List;

public class StatShopWorkLoadResultMO {
	List<StatShopWorkLoadMO> workLoadMoList;

	BigDecimal shopTotalWorkLoad = BigDecimal.ZERO;
	
	BigDecimal shopAvgWorkLoad=BigDecimal.ZERO;

	public List<StatShopWorkLoadMO> getWorkLoadMoList() {
		return workLoadMoList;
	}

	public void setWorkLoadMoList(List<StatShopWorkLoadMO> workLoadMoList) {
		this.workLoadMoList = workLoadMoList;
	}


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


}
