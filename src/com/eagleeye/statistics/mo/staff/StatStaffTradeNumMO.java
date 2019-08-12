package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;

public class StatStaffTradeNumMO {

	private int rowKey = 0;
	
	String staffId;

	BigDecimal numWaitSend = BigDecimal.ZERO;

	BigDecimal numWaitConfirm = BigDecimal.ZERO;

	BigDecimal numSuccess = BigDecimal.ZERO;

	BigDecimal numRefunding = BigDecimal.ZERO;

	BigDecimal numAllRefund = BigDecimal.ZERO;

	BigDecimal numPartRefund = BigDecimal.ZERO;

	BigDecimal totalNumNoRefund = BigDecimal.ZERO;

	BigDecimal totalNumHasRefund = BigDecimal.ZERO;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getNumWaitSend() {
		return numWaitSend;
	}

	public void setNumWaitSend(BigDecimal numWaitSend) {
		this.numWaitSend = numWaitSend;
	}

	public BigDecimal getNumWaitConfirm() {
		return numWaitConfirm;
	}

	public void setNumWaitConfirm(BigDecimal numWaitConfirm) {
		this.numWaitConfirm = numWaitConfirm;
	}

	public BigDecimal getNumSuccess() {
		return numSuccess;
	}

	public void setNumSuccess(BigDecimal numSuccess) {
		this.numSuccess = numSuccess;
	}

	public BigDecimal getNumRefunding() {
		return numRefunding;
	}

	public void setNumRefunding(BigDecimal numRefunding) {
		this.numRefunding = numRefunding;
	}

	public BigDecimal getNumAllRefund() {
		return numAllRefund;
	}

	public void setNumAllRefund(BigDecimal numAllRefund) {
		this.numAllRefund = numAllRefund;
	}

	public BigDecimal getNumPartRefund() {
		return numPartRefund;
	}

	public void setNumPartRefund(BigDecimal numPartRefund) {
		this.numPartRefund = numPartRefund;
	}

	public BigDecimal getTotalNumNoRefund() {
		return totalNumNoRefund;
	}

	public void setTotalNumNoRefund(BigDecimal totalNumNoRefund) {
		this.totalNumNoRefund = totalNumNoRefund;
	}

	public BigDecimal getTotalNumHasRefund() {
		return totalNumHasRefund;
	}

	public void setTotalNumHasRefund(BigDecimal totalNumHasRefund) {
		this.totalNumHasRefund = totalNumHasRefund;
	}

	public int getRowKey() {
		return rowKey;
	}

	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}
}
