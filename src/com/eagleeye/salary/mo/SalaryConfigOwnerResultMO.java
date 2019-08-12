package com.eagleeye.salary.mo;

import java.util.ArrayList;
import java.util.List;

public class SalaryConfigOwnerResultMO {

	String templateName;
	
	List<String> salaryConfigOwners = new ArrayList();

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public List<String> getSalaryConfigOwners() {
		return salaryConfigOwners;
	}

	public void setSalaryConfigOwners(List<String> salaryConfigOwners) {
		this.salaryConfigOwners = salaryConfigOwners;
	}


}
