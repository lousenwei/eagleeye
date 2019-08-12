package com.eagleeye.salary.mo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalaryResultMO {

	List<SalaryMO> salaryMoList = new ArrayList();

	String start = new  SimpleDateFormat("yyyy-MM-dd").format(new Date());

	String end = new  SimpleDateFormat("yyyy-MM-dd").format(new Date());

	String settlementType;

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public List<SalaryMO> getSalaryMoList() {
		return salaryMoList;
	}

	public void setSalaryMoList(List<SalaryMO> salaryMoList) {
		this.salaryMoList = salaryMoList;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
