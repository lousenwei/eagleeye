package com.eagleeye.salary.bsh.impl;

import java.util.ArrayList;
import java.util.List;

import com.eagleeye.salary.blh.ISalaryConfigBlh;
import com.eagleeye.salary.blh.ISalaryConfigOwnerBlh;
import com.eagleeye.salary.bsh.ISalaryConfigOwnerBsh;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEOId;
import com.eagleeye.salary.mo.SalaryConfigOwnerResultMO;

public class SalaryConfigOwnerBshImpl implements ISalaryConfigOwnerBsh {
	ISalaryConfigOwnerBlh salaryConfigOwnerBlh;
	ISalaryConfigBlh salaryConfigBlh;

	@Override
	public List getNoSalaryConfigStaffs(String managerId) {
		// TODO Auto-generated method stub
		return salaryConfigOwnerBlh.getNoSalaryConfigStaffs(managerId);
	}

	@Override
	public List getSalaryConfigOwner(String managerId) {
		// TODO Auto-generated method stub
		return salaryConfigOwnerBlh.getSalaryConfigOwner(managerId);
	}

	/**
	 * 根据Id获取已分配客服和模板关系对象
	 * 
	 * @param id
	 * @return
	 */
	public SalaryConfigOwnerEO getSalaryConfigOwnerById(SalaryConfigOwnerEOId id) {
		return salaryConfigOwnerBlh.getSalaryConfigOwnerById(id);
	}

	/**
	 * 保存所有
	 * 
	 * @param salaryConfigOwners
	 */
	public void saveAll(List<SalaryConfigOwnerEO> salaryConfigOwners) {
		salaryConfigOwnerBlh.saveAll(salaryConfigOwners);
	}

	public void deleteSalaryConfigOwner(SalaryConfigOwnerEO salaryConfigOwner) {
		salaryConfigOwnerBlh.deleteSalaryConfigOwner(salaryConfigOwner);
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
		return salaryConfigOwnerBlh.deleteSalaryConfigOwnersByTemplateName(
				managerId, templateName);
	}

	@Override
	public List<SalaryConfigOwnerResultMO> getSalaryConfigMO(String managerId) {
		// TODO Auto-generated method stub
		// 获取所有的模板
		List<SalaryConfigEO> salaryConfigs = salaryConfigBlh
				.getSalaryConfigsByManagerId(managerId);
		if (salaryConfigs != null && !salaryConfigs.isEmpty()) {
			// 根据模板组装已分配对象
			List<SalaryConfigOwnerEO> salaryConfigOwners = salaryConfigOwnerBlh
					.getSalaryConfigOwner(managerId);

			List<SalaryConfigOwnerResultMO> salaryConfigMos = new ArrayList();
			for (SalaryConfigEO tmpConfig : salaryConfigs) {
				SalaryConfigOwnerResultMO salaryConfigMo = new SalaryConfigOwnerResultMO();
				List<String> ownerList = new ArrayList();
				if (salaryConfigOwners != null && !salaryConfigOwners.isEmpty()) {
					for (SalaryConfigOwnerEO tmpOwner : salaryConfigOwners) {
						// 如果managerId和模板名称相同，则放入这个list
						if (tmpOwner.getId().getManagerId()
								.equals(tmpConfig.getId().getManagerId())
								&& tmpOwner.getTemplateName().equals(
										tmpConfig.getId().getTemplateName())) {
							ownerList.add(tmpOwner.getId().getStaffId());
						}
					}
				}
				salaryConfigMo.setTemplateName(tmpConfig.getId()
						.getTemplateName());
				salaryConfigMo.setSalaryConfigOwners(ownerList);
				salaryConfigMos.add(salaryConfigMo);
			}
			return salaryConfigMos;
		}

		return null;
	}

	public ISalaryConfigOwnerBlh getSalaryConfigOwnerBlh() {
		return salaryConfigOwnerBlh;
	}

	public void setSalaryConfigOwnerBlh(
			ISalaryConfigOwnerBlh salaryConfigOwnerBlh) {
		this.salaryConfigOwnerBlh = salaryConfigOwnerBlh;
	}

	public ISalaryConfigBlh getSalaryConfigBlh() {
		return salaryConfigBlh;
	}

	public void setSalaryConfigBlh(ISalaryConfigBlh salaryConfigBlh) {
		this.salaryConfigBlh = salaryConfigBlh;
	}

}
