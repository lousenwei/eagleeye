package com.eagleeye.salary.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.Messages;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.salary.bsh.ISalaryConfigBsh;
import com.eagleeye.salary.bsh.ISalaryConfigOwnerBsh;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEOId;
import com.eagleeye.salary.mo.SalaryConfigOwnerMO;
import com.eagleeye.salary.mo.SalaryConfigOwnerResultMO;

@ManagedBean(name = "salaryConfigOwner")
@ViewScoped
public class SalaryConfigOwnerController {

	ISalaryConfigOwnerBsh salaryConfigOwnerBsh = (ISalaryConfigOwnerBsh) EagleEyeServiceLocator
			.getBean("salaryConfigOwnerBsh");
	ISalaryConfigBsh salaryConfigBsh = (ISalaryConfigBsh) EagleEyeServiceLocator
			.getBean("salaryConfigBsh");
	// 已分配情况
	List<SalaryConfigOwnerResultMO> salaryConfigMos;
	// 未分配客服
	List<SalaryConfigOwnerMO> noSalaryConfigOwners;
	// 是否存在模板
	Boolean hasTemplate = true;
	// 选择的客服
	SalaryConfigOwnerMO[] selectOwners;
	// 删除时选择的客服
	String selectOwner;
	// 工资模板
	List<SalaryConfigEO> salaryConfigs;
	// 选择的工资模板
	String selectSalaryConfig;
	// 经理ID
	String managerId;

	public SalaryConfigOwnerController() {
		super();
		// TODO Auto-generated constructor stub
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		if (managerId != null) {
			salaryConfigs = salaryConfigBsh
					.getSalaryConfigsByManagerId(managerId);
			this.initStaffs(managerId);
			if (salaryConfigMos == null || salaryConfigMos.isEmpty()) {
				hasTemplate = false;
			}
		}
	}

	/**
	 * 设置客服合适的工资模板
	 */
	public void assignStaffs() {
		if (selectOwners != null && selectOwners.length != 0
				&& selectSalaryConfig != null && !selectSalaryConfig.isEmpty()) {
			List<SalaryConfigOwnerEO> salaryConfigOwners = new ArrayList();
			for (int i = 0; i < selectOwners.length; i++) {
				SalaryConfigOwnerEO salaryConfigOwner = new SalaryConfigOwnerEO();
				SalaryConfigOwnerEOId id = new SalaryConfigOwnerEOId(managerId,
						selectOwners[i].getStaffId());
				salaryConfigOwner.setId(id);
				salaryConfigOwner.setTemplateName(selectSalaryConfig);
				salaryConfigOwners.add(salaryConfigOwner);
			}
			// 存储
			if (!salaryConfigOwners.isEmpty()) {
				salaryConfigOwnerBsh.saveAll(salaryConfigOwners);
			}
			// 重新获取对象
			selectOwners = null;
			this.initStaffs(managerId);
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									Messages.getString("salary_config_owner_assign_success_brief"),
									Messages.getString("salary_config_owner_assign_success_detail")));
		} else if (selectSalaryConfig == null || selectSalaryConfig.isEmpty()) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Messages.getString("salary_config_owner_assign_warning_brief.0"),
									Messages.getString("salary_config_owner_assign_warning_detail.0")));
		}
	}

	/**
	 * 删除对应的工资模板客服
	 */
	public void deleteSalaryConfigOwner() {
		if (selectOwner != null && !selectOwner.isEmpty()) {
			SalaryConfigOwnerEOId id = new SalaryConfigOwnerEOId(managerId,
					selectOwner);
			SalaryConfigOwnerEO deleteObject = salaryConfigOwnerBsh
					.getSalaryConfigOwnerById(id);
			if (deleteObject != null) {
				salaryConfigOwnerBsh.deleteSalaryConfigOwner(deleteObject);
				selectOwner = null;
				this.initStaffs(managerId);
			}
		}
	}

	/**
	 * 初始化未分配客服及已分配客服列表
	 * 
	 * @param managerId
	 */
	private void initStaffs(String managerId) {
		noSalaryConfigOwners = salaryConfigOwnerBsh
				.getNoSalaryConfigStaffs(managerId);
		salaryConfigMos = salaryConfigOwnerBsh.getSalaryConfigMO(managerId);
	}

	public List<SalaryConfigOwnerResultMO> getSalaryConfigMos() {
		return salaryConfigMos;
	}

	public void setSalaryConfigMos(
			List<SalaryConfigOwnerResultMO> salaryConfigMos) {
		this.salaryConfigMos = salaryConfigMos;
	}

	public List<SalaryConfigOwnerMO> getNoSalaryConfigOwners() {
		return noSalaryConfigOwners;
	}

	public void setNoSalaryConfigOwners(
			List<SalaryConfigOwnerMO> noSalaryConfigOwners) {
		this.noSalaryConfigOwners = noSalaryConfigOwners;
	}

	public Boolean getHasTemplate() {
		return hasTemplate;
	}

	public void setHasTemplate(Boolean hasTemplate) {
		this.hasTemplate = hasTemplate;
	}

	public String getSelectOwner() {
		return selectOwner;
	}

	public void setSelectOwner(String selectOwner) {
		this.selectOwner = selectOwner;
	}

	public SalaryConfigOwnerMO[] getSelectOwners() {
		return selectOwners;
	}

	public void setSelectOwners(SalaryConfigOwnerMO[] selectOwners) {
		this.selectOwners = selectOwners;
	}

	public List<SalaryConfigEO> getSalaryConfigs() {
		return salaryConfigs;
	}

	public void setSalaryConfigs(List<SalaryConfigEO> salaryConfigs) {
		this.salaryConfigs = salaryConfigs;
	}

	public String getSelectSalaryConfig() {
		return selectSalaryConfig;
	}

	public void setSelectSalaryConfig(String selectSalaryConfig) {
		this.selectSalaryConfig = selectSalaryConfig;
	}

}
