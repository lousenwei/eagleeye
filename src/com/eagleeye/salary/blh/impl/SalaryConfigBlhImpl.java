package com.eagleeye.salary.blh.impl;

import java.util.List;

import com.eagleeye.salary.blh.ISalaryConfigBlh;
import com.eagleeye.salary.dao.impl.SalaryConfigDAO;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.eo.SalaryConfigEOId;
import com.eagleeye.salary.eo.SalaryConfigOwnerEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEOId;

public class SalaryConfigBlhImpl implements ISalaryConfigBlh {

	SalaryConfigDAO salaryConfigDao;

	@Override
	public SalaryConfigEO getSalaryConfigByManagerIdAndStaffId(
			String managerId, String staffId) {
		// TODO Auto-generated method stub
		return salaryConfigDao.getSalaryConfigByManagerIdAndStaffId(managerId,
				staffId);
	}

	@Override
	public List<SalaryConfigEO> getSalaryConfigsByManagerId(String managerId) {
		// TODO Auto-generated method stub
		return salaryConfigDao.getSalaryConfigsByManagerId(managerId);
	}

	@Override
	public void saveSalaryConfig(SalaryConfigEO salaryConfig) {
		// TODO Auto-generated method stub
		salaryConfigDao.saveOrUpdate(salaryConfig);
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
		return salaryConfigDao.getSalaryConfigByManagerIdandTemplateName(
				managerId, templateName);
	}

	/**
	 * 根据Id删除工资配置
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSalaryConfigById(SalaryConfigEOId id) {
		return salaryConfigDao.deleteSalaryConfigById(id);
	}

	public SalaryConfigDAO getSalaryConfigDao() {
		return salaryConfigDao;
	}

	public void setSalaryConfigDao(SalaryConfigDAO salaryConfigDao) {
		this.salaryConfigDao = salaryConfigDao;
	}

}
