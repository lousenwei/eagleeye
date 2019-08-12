package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;

public class StatAchievementResultMO {

	private int rowKey = 0;
	private String managerId;
	private String shopId;
	private String staffId;
	private BigDecimal amountWaitSend = BigDecimal.ZERO;
	private BigDecimal amountWaitConfirm = BigDecimal.ZERO;
	private BigDecimal amountSuccess = BigDecimal.ZERO;
	private BigDecimal amountAllRefund = BigDecimal.ZERO;
	private BigDecimal amountPartRefund = BigDecimal.ZERO;
	private BigDecimal amountRefunding = BigDecimal.ZERO;
	// 2011-10-28,增加运费统计项
	private BigDecimal postFee = BigDecimal.ZERO;
	private BigDecimal totalAmount = BigDecimal.ZERO;

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getAmountWaitSend() {
		return amountWaitSend;
	}

	public void setAmountWaitSend(BigDecimal amountWaitSend) {
		this.amountWaitSend = amountWaitSend;
	}

	public BigDecimal getAmountWaitConfirm() {
		return amountWaitConfirm;
	}

	public void setAmountWaitConfirm(BigDecimal amountWaitConfirm) {
		this.amountWaitConfirm = amountWaitConfirm;
	}

	public BigDecimal getAmountSuccess() {
		return amountSuccess;
	}

	public void setAmountSuccess(BigDecimal amountSuccess) {
		this.amountSuccess = amountSuccess;
	}

	public BigDecimal getAmountAllRefund() {
		return amountAllRefund;
	}

	public void setAmountAllRefund(BigDecimal amountAllRefund) {
		this.amountAllRefund = amountAllRefund;
	}

	public BigDecimal getAmountPartRefund() {
		return amountPartRefund;
	}

	public void setAmountPartRefund(BigDecimal amountPartRefund) {
		this.amountPartRefund = amountPartRefund;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPostFee() {
		return postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	public int getRowKey() {
		return rowKey;
	}

	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}

	public BigDecimal getAmountRefunding() {
		return amountRefunding;
	}

	public void setAmountRefunding(BigDecimal amountRefunding) {
		this.amountRefunding = amountRefunding;
	}

}
