package com.eagleeye.statistics.mo.rank;

import java.math.BigDecimal;

public class RankCustomerRefundAmountMO {
	// 买家ID
	private String buyerNick;
	// 退款总额
	private BigDecimal totalRefundAmount;

	public RankCustomerRefundAmountMO(String buyerNick,
			BigDecimal totalRefundAmount) {
		super();
		this.buyerNick = buyerNick;
		this.totalRefundAmount = totalRefundAmount;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public BigDecimal getTotalRefundAmount() {
		return totalRefundAmount;
	}

	public void setTotalRefundAmount(BigDecimal totalRefundAmount) {
		this.totalRefundAmount = totalRefundAmount;
	}

	public RankCustomerRefundAmountMO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
