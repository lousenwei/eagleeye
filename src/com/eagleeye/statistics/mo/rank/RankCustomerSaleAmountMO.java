package com.eagleeye.statistics.mo.rank;

import java.math.BigDecimal;

public class RankCustomerSaleAmountMO {
	// 买家ID
	private String buyerNick;
	// 购买总额
	private BigDecimal totalAmount;

	public RankCustomerSaleAmountMO(String buyerNick, BigDecimal totalAmount) {
		super();
		this.buyerNick = buyerNick;
		this.totalAmount = totalAmount;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public RankCustomerSaleAmountMO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
