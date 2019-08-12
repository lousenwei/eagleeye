package com.eagleeye.statistics.mo.shop;

import java.math.BigDecimal;

public class StatAchievementNumResultMO {

	private String managerId;
	private String staffId;
	private BigDecimal numWaitSend = BigDecimal.ZERO;
	private BigDecimal numWaitConfirm = BigDecimal.ZERO;
	private BigDecimal numSuccess = BigDecimal.ZERO;
	private BigDecimal numAllRefund = BigDecimal.ZERO;
	private BigDecimal numPartRefund = BigDecimal.ZERO;
	private BigDecimal numRefunding = BigDecimal.ZERO;
	private BigDecimal numTotalNoRefund = BigDecimal.ZERO;
	private BigDecimal numTotalHasRefund = BigDecimal.ZERO;

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

	public BigDecimal getNumRefunding() {
		return numRefunding;
	}

	public void setNumRefunding(BigDecimal numRefunding) {
		this.numRefunding = numRefunding;
	}

	public BigDecimal getNumTotalNoRefund() {
		return numTotalNoRefund;
	}

	public void setNumTotalNoRefund(BigDecimal numTotalNoRefund) {
		this.numTotalNoRefund = numTotalNoRefund;
	}

	public BigDecimal getNumTotalHasRefund() {
		return numTotalHasRefund;
	}

	public void setNumTotalHasRefund(BigDecimal numTotalHasRefund) {
		this.numTotalHasRefund = numTotalHasRefund;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
