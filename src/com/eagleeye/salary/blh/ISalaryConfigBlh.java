package com.eagleeye.salary.blh;

import java.util.List;

import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.eo.SalaryConfigEOId;
import com.eagleeye.salary.eo.SalaryConfigOwnerEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEOId;

public interface ISalaryConfigBlh {

	/**
	 * 根据经理ID获取工资配置
	 * 
	 * @param managerId
	 * @return
	 */
	public SalaryConfigEO getSalaryConfigByManagerIdAndStaffId(
			String managerId, String staffId);

	/**
	 * 根据经理ID获取工资配置
	 * 
	 * @param managerId
	 * @return
	 */
	public List<SalaryConfigEO> getSalaryConfigsByManagerId(String managerId);

	/**
	 * 保存工资配置
	 * 
	 * @param salaryConfig
	 */
	public void saveSalaryConfig(SalaryConfigEO salaryConfig);

	/**
	 * 根据Id删除工资配置
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSalaryConfigById(SalaryConfigEOId id);

	/**
	 * 根据经理Id和模板名称获取模板
	 * 
	 * @param managerId
	 * @param templateName
	 * @return
	 */
	public SalaryConfigEO getSalaryConfigByManagerIdandTemplateName(
			String managerId, String templateName);
}
