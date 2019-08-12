package com.eagleeye.salary.bsh.impl;

import java.util.Date;

import com.eagleeye.salary.blh.ISalaryCalculateBlh;
import com.eagleeye.salary.bsh.ISalaryCalculateBsh;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.mo.SalaryResultMO;

public class SalaryCalculateBshImpl implements ISalaryCalculateBsh {

	ISalaryCalculateBlh salaryCalculateBlh;

	@Override
	public SalaryResultMO calculateSalaryByTimeAndManagerId(String managerId,
			Date start, Date end, SalaryConfigEO salaryConfig) {
		// TODO Auto-generated method stub
		return salaryCalculateBlh.calculateSalaryByTimeAndManagerId(managerId,
				start, end, salaryConfig);
	}

	public ISalaryCalculateBlh getSalaryCalculateBlh() {
		return salaryCalculateBlh;
	}

	public void setSalaryCalculateBlh(ISalaryCalculateBlh salaryCalculateBlh) {
		this.salaryCalculateBlh = salaryCalculateBlh;
	}

}
