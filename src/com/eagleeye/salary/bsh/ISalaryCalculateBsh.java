package com.eagleeye.salary.bsh;

import java.util.Date;

import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.mo.SalaryResultMO;

public interface ISalaryCalculateBsh {

	public SalaryResultMO calculateSalaryByTimeAndManagerId(String managerId,
			Date start, Date end, SalaryConfigEO salaryConfig);

}
