package com.eagleeye.salary.blh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.salary.blh.ISalarySettlementBlh;
import com.eagleeye.salary.dao.impl.SalarySettlementDAO;
import com.eagleeye.salary.eo.SalarySettlementEO;
import com.eagleeye.salary.mo.SalarySettlementQueryMO;

public class SalarySettlementBlhImpl implements ISalarySettlementBlh {
	SalarySettlementDAO salarySettlementDao;

	@Override
	public List<SalarySettlementEO> getSalarySettlementByMo(
			SalarySettlementQueryMO queryMo) {
		// TODO Auto-generated method stub
		return salarySettlementDao.getSalarySettlementByMo(queryMo);
	}

	@Override
	public void saveSalarySettlement(SalarySettlementEO salarySettlement) {
		// TODO Auto-generated method stub
		salarySettlementDao.saveOrUpdate(salarySettlement);
	}

	public List<SalarySettlementEO> getSalarySettlementByManagerIdAndTime(
			String managerId, Date start, Date end) {
		return salarySettlementDao.getSalarySettlementByManagerIdAndTime(
				managerId, start, end);
	}

	public SalarySettlementDAO getSalarySettlementDao() {
		return salarySettlementDao;
	}

	public void setSalarySettlementDao(SalarySettlementDAO salarySettlementDao) {
		this.salarySettlementDao = salarySettlementDao;
	}

}
