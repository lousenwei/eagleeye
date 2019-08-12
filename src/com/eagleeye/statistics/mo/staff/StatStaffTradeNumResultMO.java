package com.eagleeye.statistics.mo.staff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatStaffTradeNumResultMO {

	BigDecimal sumNumWaitSend = BigDecimal.ZERO;

	BigDecimal sumNumWaitConfirm = BigDecimal.ZERO;

	BigDecimal sumNumSuccess = BigDecimal.ZERO;

	BigDecimal sumNumRefunding = BigDecimal.ZERO;

	BigDecimal sumNumAllRefund = BigDecimal.ZERO;

	BigDecimal sumNumPartRefund = BigDecimal.ZERO;

	BigDecimal sumTotalNumNoRefund = BigDecimal.ZERO;

	BigDecimal sumTotalNumHasRefund = BigDecimal.ZERO;

	List<StatStaffTradeNumMO> statStaffTradeNumMoList = new ArrayList();

	public BigDecimal getSumNumWaitSend() {
		return sumNumWaitSend;
	}

	public void setSumNumWaitSend(BigDecimal sumNumWaitSend) {
		this.sumNumWaitSend = sumNumWaitSend;
	}

	public BigDecimal getSumNumWaitConfirm() {
		return sumNumWaitConfirm;
	}

	public void setSumNumWaitConfirm(BigDecimal sumNumWaitConfirm) {
		this.sumNumWaitConfirm = sumNumWaitConfirm;
	}

	public BigDecimal getSumNumSuccess() {
		return sumNumSuccess;
	}

	public void setSumNumSuccess(BigDecimal sumNumSuccess) {
		this.sumNumSuccess = sumNumSuccess;
	}

	public BigDecimal getSumNumRefunding() {
		return sumNumRefunding;
	}

	public void setSumNumRefunding(BigDecimal sumNumRefunding) {
		this.sumNumRefunding = sumNumRefunding;
	}

	public BigDecimal getSumNumAllRefund() {
		return sumNumAllRefund;
	}

	public void setSumNumAllRefund(BigDecimal sumNumAllRefund) {
		this.sumNumAllRefund = sumNumAllRefund;
	}

	public BigDecimal getSumNumPartRefund() {
		return sumNumPartRefund;
	}

	public void setSumNumPartRefund(BigDecimal sumNumPartRefund) {
		this.sumNumPartRefund = sumNumPartRefund;
	}

	public BigDecimal getSumTotalNumNoRefund() {
		return sumTotalNumNoRefund;
	}

	public void setSumTotalNumNoRefund(BigDecimal sumTotalNumNoRefund) {
		this.sumTotalNumNoRefund = sumTotalNumNoRefund;
	}

	public BigDecimal getSumTotalNumHasRefund() {
		return sumTotalNumHasRefund;
	}

	public void setSumTotalNumHasRefund(BigDecimal sumTotalNumHasRefund) {
		this.sumTotalNumHasRefund = sumTotalNumHasRefund;
	}

	public List<StatStaffTradeNumMO> getStatStaffTradeNumMoList() {
		return statStaffTradeNumMoList;
	}

	public void setStatStaffTradeNumMoList(
			List<StatStaffTradeNumMO> statStaffTradeNumMoList) {
		this.statStaffTradeNumMoList = statStaffTradeNumMoList;
	}
}
