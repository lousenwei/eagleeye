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
import com.eagleeye.salary.eo.SalaryConfigEOId;

@ManagedBean(name = "salarySpecialConfig")
@ViewScoped
public class SalarySpecialConfigController {

	ISalaryConfigBsh salaryConfigBsh;
	ISalaryConfigOwnerBsh salaryConfigOwnerBsh;

	/******************** 工资配置数据结构 ********************/
	SalaryConfigEO selectedSalaryConfig;

	String templateName;

	String managerId;

	List<SalaryConfigEO> salaryConfigs = new ArrayList();

	public SalarySpecialConfigController() {
		super();
		// TODO Auto-generated constructor stub
		salaryConfigBsh = (ISalaryConfigBsh) EagleEyeServiceLocator
				.getBean("salaryConfigBsh");
		salaryConfigOwnerBsh = (ISalaryConfigOwnerBsh) EagleEyeServiceLocator
				.getBean("salaryConfigOwnerBsh");
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		salaryConfigs = salaryConfigBsh.getSalaryConfigsByManagerId(managerId);
		SalaryConfigEOId id = new SalaryConfigEOId();
		id.setManagerId(managerId);
		id.setTemplateName(EagleConstants.GENERAL_PAYMENT_TEMPLATE);
		selectedSalaryConfig = new SalaryConfigEO(id);
	}

	public void saveSalaryConfig() {
		salaryConfigBsh.saveSalaryConfig(selectedSalaryConfig);
		salaryConfigs = salaryConfigBsh.getSalaryConfigsByManagerId(managerId);
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								Messages.getString("salary_config_save_success_brief"),
								Messages.getString("salary_config_save_success_detail")));
	}

	/**
	 * 点击新建按钮
	 */
	public void addSalaryConfig() {
		if (managerId != null && templateName != null
				&& !templateName.isEmpty()) {
			if (salaryConfigs != null) {
				for (SalaryConfigEO tmp : salaryConfigs) {
					if (templateName.equals(tmp.getId().getTemplateName())) {
						templateName = templateName + ":副本";
					}
				}
			}
			SalaryConfigEOId id = new SalaryConfigEOId();
			id.setManagerId(managerId);
			id.setTemplateName(templateName);
			selectedSalaryConfig = new SalaryConfigEO(id);
		}
	}

	/**
	 * 删除按钮
	 */
	public void deleteSalaryConfig() {
		int i = 0;
		int j = 0;
		if (selectedSalaryConfig != null
				&& selectedSalaryConfig.getId() != null
				&& !EagleConstants.GENERAL_PAYMENT_TEMPLATE
						.equals(selectedSalaryConfig.getId().getTemplateName())) {
			i = salaryConfigBsh.deleteSalaryConfigById(selectedSalaryConfig
					.getId());
			j = salaryConfigOwnerBsh.deleteSalaryConfigOwnersByTemplateName(
					selectedSalaryConfig.getId().getManagerId(),
					selectedSalaryConfig.getId().getTemplateName());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "删除成功", "共删除"
							+ i + "条模板数据！共有" + j + "个客服使用此模板，请重新指派模板给他们！"));
		} else if (selectedSalaryConfig != null
				&& selectedSalaryConfig.getId() != null
				&& EagleConstants.GENERAL_PAYMENT_TEMPLATE
						.equals(selectedSalaryConfig.getId().getTemplateName())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "删除失败",
							"亲，您至少得有一个通用模版，删除失败！"));
		}
		if (i != 0) {
			salaryConfigs = salaryConfigBsh
					.getSalaryConfigsByManagerId(managerId);
		}
	}

	public SalaryConfigEO getSelectedSalaryConfig() {
		return selectedSalaryConfig;
	}

	public void setSelectedSalaryConfig(SalaryConfigEO selectedSalaryConfig) {
		this.selectedSalaryConfig = selectedSalaryConfig;
	}

	public List<SalaryConfigEO> getSalaryConfigs() {
		return salaryConfigs;
	}

	public void setSalaryConfigs(List<SalaryConfigEO> salaryConfigs) {
		this.salaryConfigs = salaryConfigs;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}
