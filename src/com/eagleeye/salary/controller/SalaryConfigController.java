package com.eagleeye.salary.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.Messages;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.salary.bsh.ISalaryConfigBsh;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.eo.SalaryConfigEOId;

@ManagedBean(name = "salaryConfig")
@RequestScoped
public class SalaryConfigController {

	ISalaryConfigBsh salaryConfigBsh;

	/******************** 通用工资配置数据结构 ********************/
	SalaryConfigEO salaryConfig;

	public SalaryConfigController() {
		super();
		// TODO Auto-generated constructor stub
		salaryConfigBsh = (ISalaryConfigBsh) EagleEyeServiceLocator
				.getBean("salaryConfigBsh");
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		salaryConfig = salaryConfigBsh.getSalaryConfigByManagerIdandTemplateName(
				managerId, EagleConstants.GENERAL_PAYMENT_TEMPLATE);
		if (salaryConfig == null) {
			SalaryConfigEOId id = new SalaryConfigEOId(managerId,
					EagleConstants.GENERAL_PAYMENT_TEMPLATE);
			salaryConfig = new SalaryConfigEO(id);
		}
	}

	public void saveSalaryConfig() {
		salaryConfigBsh.saveSalaryConfig(salaryConfig);
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								Messages.getString("salary_config_save_success_brief"),
								Messages.getString("salary_config_save_success_detail")));
	}

	public SalaryConfigEO getSalaryConfig() {
		return salaryConfig;
	}

	public void setSalaryConfig(SalaryConfigEO salaryConfig) {
		this.salaryConfig = salaryConfig;
	}

}
