package com.eagleeye.salary.blh.impl;

import java.util.List;

import com.eagleeye.salary.blh.ISalaryConfigOwnerBlh;
import com.eagleeye.salary.dao.impl.SalaryConfigOwnerDAO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEOId;

public class SalaryConfigOwnerBlhImpl implements ISalaryConfigOwnerBlh {
	SalaryConfigOwnerDAO salaryConfigOwnerDao;

	@Override
	public List getNoSalaryConfigStaffs(String managerId) {
		// TODO Auto-generated method stub
		return salaryConfigOwnerDao.getNoSalaryConfigStaffs(managerId);
	}

	@Override
	public List getSalaryConfigOwner(String managerId) {
		// TODO Auto-generated method stub
		return salaryConfigOwnerDao.getSalaryConfigOwner(managerId);
	}

	public SalaryConfigOwnerDAO getSalaryConfigOwnerDao() {
		return salaryConfigOwnerDao;
	}

	public void setSalaryConfigOwnerDao(
			SalaryConfigOwnerDAO salaryConfigOwnerDao) {
		this.salaryConfigOwnerDao = salaryConfigOwnerDao;
	}

	@Override
	public void deleteSalaryConfigOwner(SalaryConfigOwnerEO salaryConfigOwner) {
		// TODO Auto-generated method stub
		salaryConfigOwnerDao.deleteSalaryConfigOwner(salaryConfigOwner);
	}

	@Override
	public void saveAll(List<SalaryConfigOwnerEO> salaryConfigOwners) {
		// TODO Auto-generated method stub
		salaryConfigOwnerDao.saveAllObject(salaryConfigOwners);
	}

	/**
	 * 根据Id获取已分配客服和模板关系对象
	 * 
	 * @param id
	 * @return
	 */
	public SalaryConfigOwnerEO getSalaryConfigOwnerById(SalaryConfigOwnerEOId id) {
		Object result = salaryConfigOwnerDao.getData(SalaryConfigOwnerEO.class,
				id);
		if (result != null) {
			return (SalaryConfigOwnerEO) result;
		}
		return null;
	}

	/**
	 * 删除模板下的对应关系
	 * 
	 * @param managerId
	 * @param templateName
	 * @return
	 */
	public int deleteSalaryConfigOwnersByTemplateName(String managerId,
			String templateName) {
		return salaryConfigOwnerDao.deleteSalaryConfigOwnersByTemplateName(
				managerId, templateName);
	}
}
