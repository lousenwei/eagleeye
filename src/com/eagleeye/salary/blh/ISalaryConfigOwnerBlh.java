package com.eagleeye.salary.blh;

import java.util.List;

import com.eagleeye.salary.eo.SalaryConfigOwnerEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEOId;

public interface ISalaryConfigOwnerBlh {
	/**
	 * 返回未分配工资模板的客服
	 * 
	 * @param managerId
	 * @return
	 */
	public List getNoSalaryConfigStaffs(String managerId);

	/**
	 * 返回已分配工资模板的客服
	 * 
	 * @param managerId
	 * @return
	 */
	public List getSalaryConfigOwner(String managerId);

	/**
	 * 删除已分配工资模板的客服
	 * 
	 * @param salaryConfigOwner
	 * @return
	 */
	public void deleteSalaryConfigOwner(SalaryConfigOwnerEO salaryConfigOwner);

	/**
	 * 保存已分配工资模板的客服
	 * 
	 * @param salaryConfigOwners
	 */
	public void saveAll(List<SalaryConfigOwnerEO> salaryConfigOwners);

	/**
	 * 根据Id获取已分配客服和模板关系对象
	 * 
	 * @param id
	 * @return
	 */
	public SalaryConfigOwnerEO getSalaryConfigOwnerById(SalaryConfigOwnerEOId id);

	/**
	 * 删除模板下的对应关系
	 * 
	 * @param managerId
	 * @param templateName
	 * @return
	 */
	public int deleteSalaryConfigOwnersByTemplateName(String managerId,
			String templateName);
}
