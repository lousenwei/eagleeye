package com.eagleeye.salary.bsh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.salary.blh.ISalarySettlementBlh;
import com.eagleeye.salary.bsh.ISalarySettlementBsh;
import com.eagleeye.salary.eo.SalarySettlementEO;
import com.eagleeye.salary.mo.SalarySettlementQueryMO;

public class SalarySettlementBshImpl implements ISalarySettlementBsh {
	ISalarySettlementBlh salarySettlementBlh;

	@Override
	public List<SalarySettlementEO> getSalarySettlementByMo(
			SalarySettlementQueryMO queryMo) {
		// TODO Auto-generated method stub
		return salarySettlementBlh.getSalarySettlementByMo(queryMo);
	}

	@Override
	public void saveSalarySettlement(SalarySettlementEO salarySettlement) {
		// TODO Auto-generated method stub
		salarySettlementBlh.saveSalarySettlement(salarySettlement);
	}

	public List<SalarySettlementEO> getSalarySettlementByManagerIdAndTime(
			String managerId, Date start, Date end) {
		return salarySettlementBlh.getSalarySettlementByManagerIdAndTime(
				managerId, start, end);
	}

	public ISalarySettlementBlh getSalarySettlementBlh() {
		return salarySettlementBlh;
	}

	public void setSalarySettlementBlh(ISalarySettlementBlh salarySettlementBlh) {
		this.salarySettlementBlh = salarySettlementBlh;
	}

}
