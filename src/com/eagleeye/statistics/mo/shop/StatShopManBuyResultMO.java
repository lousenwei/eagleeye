package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;
import java.util.List;

public class StatShopManBuyResultMO {

	List<StatShopManBuyMO> statShopManBuyMoList;

	BigDecimal avgManBuyAmount = BigDecimal.ZERO;

	public List<StatShopManBuyMO> getStatShopManBuyMoList() {
		return statShopManBuyMoList;
	}

	public void setStatShopManBuyMoList(List<StatShopManBuyMO> statShopManBuyMoList) {
		this.statShopManBuyMoList = statShopManBuyMoList;
	}

	public BigDecimal getAvgManBuyAmount() {
		return avgManBuyAmount;
	}

	public void setAvgManBuyAmount(BigDecimal avgManBuyAmount) {
		this.avgManBuyAmount = avgManBuyAmount;
	}
}
