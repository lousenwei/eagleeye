package com.eagleeye.salary.bsh.impl;

import java.util.List;

import com.eagleeye.salary.blh.ISalaryConfigBlh;
import com.eagleeye.salary.bsh.ISalaryConfigBsh;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.eo.SalaryConfigEOId;

public class SalaryConfigBshImpl implements ISalaryConfigBsh {

	ISalaryConfigBlh salaryConfigBlh;

	@Override
	public SalaryConfigEO getSalaryConfigByManagerIdAndStaffId(
			String managerId, String staffId) {
		// TODO Auto-generated method stub
		return salaryConfigBlh.getSalaryConfigByManagerIdAndStaffId(managerId,
				staffId);
	}

	/**
	 * 根据经理Id和模板名称获取模板
	 * 
	 * @param managerId
	 * @param templateName
	 * @return
	 */
	public SalaryConfigEO getSalaryConfigByManagerIdandTemplateName(
			String managerId, String templateName) {
		return salaryConfigBlh.getSalaryConfigByManagerIdandTemplateName(
				managerId, templateName);
	}

	@Override
	public List<SalaryConfigEO> getSalaryConfigsByManagerId(String managerId) {
		// TODO Auto-generated method stub
		return salaryConfigBlh.getSalaryConfigsByManagerId(managerId);
	}

	@Override
	public void saveSalaryConfig(SalaryConfigEO salaryConfig) {
		// TODO Auto-generated method stub
		salaryConfigBlh.saveSalaryConfig(salaryConfig);
	}

	/**
	 * 根据Id删除工资配置
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSalaryConfigById(SalaryConfigEOId id) {
		return salaryConfigBlh.deleteSalaryConfigById(id);
	}

	public ISalaryConfigBlh getSalaryConfigBlh() {
		return salaryConfigBlh;
	}

	public void setSalaryConfigBlh(ISalaryConfigBlh salaryConfigBlh) {
		this.salaryConfigBlh = salaryConfigBlh;
	}

}
