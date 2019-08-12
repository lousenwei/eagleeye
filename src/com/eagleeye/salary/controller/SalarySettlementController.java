package com.eagleeye.salary.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.salary.bsh.ISalarySettlementBsh;
import com.eagleeye.salary.eo.SalarySettlementEO;
import com.eagleeye.salary.mo.SalarySettlementQueryMO;

@ManagedBean(name = "salarySettlement")
@ViewScoped
public class SalarySettlementController {

	ISalarySettlementBsh salarySettlementBsh;

	SalarySettlementQueryMO salarySettlementQueryMo = new SalarySettlementQueryMO();
	List<SalarySettlementEO> salarySettlementHistory = new ArrayList();
	String managerId;
	SalarySettlementEO selectedStaff = new SalarySettlementEO();

	public SalarySettlementController() {
		super();
		salarySettlementBsh = (ISalarySettlementBsh) EagleEyeServiceLocator
				.getBean("salarySettlementBsh");
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		salarySettlementQueryMo.setManagerId(managerId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 得到历史数据
	 */
	public void getSalarySettlementsHistory() {
		if (managerId != null
				&& salarySettlementQueryMo.getBetweenDate() != null) {
			salarySettlementHistory = salarySettlementBsh
					.getSalarySettlementByMo(salarySettlementQueryMo);
		}
	}

	public List<SalarySettlementEO> getSalarySettlementHistory() {
		return salarySettlementHistory;
	}

	public void setSalarySettlementHistory(
			List<SalarySettlementEO> salarySettlementHistory) {
		this.salarySettlementHistory = salarySettlementHistory;
	}

	public SalarySettlementQueryMO getSalarySettlementQueryMo() {
		return salarySettlementQueryMo;
	}

	public void setSalarySettlementQueryMo(
			SalarySettlementQueryMO salarySettlementQueryMo) {
		this.salarySettlementQueryMo = salarySettlementQueryMo;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public SalarySettlementEO getSelectedStaff() {
		return selectedStaff;
	}

	public void setSelectedStaff(SalarySettlementEO selectedStaff) {
		this.selectedStaff = selectedStaff;
	}

}
