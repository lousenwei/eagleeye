package com.eagleeye.salary.mo;

import java.util.Date;

public class SalarySettlementQueryMO {

	String managerId;	

	String staffId;
	
	Date betweenDate;
	
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getBetweenDate() {
		return betweenDate;
	}

	public void setBetweenDate(Date betweenDate) {
		this.betweenDate = betweenDate;
	}

}
