package com.eagleeye.salary.mo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SalaryRankMO {

	public String TOP_RANK = "top";

	public String LAST_RANK = "last";

	/**
	 * 销售金额，最多至最少
	 */
	Map<String, BigDecimal> staffSale = new HashMap();

	/**
	 * 销售金额排行，最多and最少
	 */
	Map<String, String> staffSaleRank = new HashMap();

	/**
	 * 销售数量列表
	 */
	Map<String, BigDecimal> staffNum = new HashMap();
	/**
	 * 退款排行，最少到最多，不包含零退款
	 */
	Map<String, Integer> staffRefundRank = new HashMap();

	/**
	 * 回复速度排行，最快and最慢
	 */
	Map<String, String> staffReplyTimeRank = new HashMap();

	/**
	 * 回复数量排行，最多and最少
	 */
	Map<String, String> staffReplyNumRank = new HashMap();

	/**
	 * 转换率排行，最高and最低
	 */
	Map<String, String> staffExchangeRank = new HashMap();

	/**
	 * 客单价排行，最高and最低
	 */
	Map<String, String> staffManBuyRank = new HashMap();


	public Map<String, Integer> getStaffRefundRank() {
		return staffRefundRank;
	}

	public void setStaffRefundRank(Map<String, Integer> staffRefundRank) {
		this.staffRefundRank = staffRefundRank;
	}

	public Map<String, String> getStaffReplyTimeRank() {
		return staffReplyTimeRank;
	}

	public void setStaffReplyTimeRank(Map<String, String> staffReplyTimeRank) {
		this.staffReplyTimeRank = staffReplyTimeRank;
	}

	public Map<String, String> getStaffReplyNumRank() {
		return staffReplyNumRank;
	}

	public void setStaffReplyNumRank(Map<String, String> staffReplyNumRank) {
		this.staffReplyNumRank = staffReplyNumRank;
	}

	public Map<String, String> getStaffExchangeRank() {
		return staffExchangeRank;
	}

	public void setStaffExchangeRank(Map<String, String> staffExchangeRank) {
		this.staffExchangeRank = staffExchangeRank;
	}

	public Map<String, String> getStaffManBuyRank() {
		return staffManBuyRank;
	}

	public void setStaffManBuyRank(Map<String, String> staffManBuyRank) {
		this.staffManBuyRank = staffManBuyRank;
	}

	public Map<String, BigDecimal> getStaffSale() {
		return staffSale;
	}

	public void setStaffSale(Map<String, BigDecimal> staffSale) {
		this.staffSale = staffSale;
	}

	public Map<String, String> getStaffSaleRank() {
		return staffSaleRank;
	}

	public void setStaffSaleRank(Map<String, String> staffSaleRank) {
		this.staffSaleRank = staffSaleRank;
	}

	public Map<String, BigDecimal> getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(Map<String, BigDecimal> staffNum) {
		this.staffNum = staffNum;
	}

}
