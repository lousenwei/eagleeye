package com.eagleeye.salary.eo;

// default package
// Generated 2011-12-3 9:17:01 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SalaryConfigId generated by hbm2java
 */
@Embeddable
public class SalaryConfigEOId implements java.io.Serializable {

	private static final long serialVersionUID = -3058433630952774869L;

	private String managerId;
	private String templateName;

	public SalaryConfigEOId() {
	}

	public SalaryConfigEOId(String managerId, String templateName) {
		this.managerId = managerId;
		this.templateName = templateName;
	}

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "template_name", nullable = false, length = 100)
	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SalaryConfigEOId))
			return false;
		SalaryConfigEOId castOther = (SalaryConfigEOId) other;

		return ((this.getManagerId() == castOther.getManagerId()) || (this
				.getManagerId() != null && castOther.getManagerId() != null && this
				.getManagerId().equals(castOther.getManagerId())))
				&& ((this.getTemplateName() == castOther.getTemplateName()) || (this
						.getTemplateName() != null
						&& castOther.getTemplateName() != null && this
						.getTemplateName().equals(castOther.getTemplateName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		result = 37
				* result
				+ (getTemplateName() == null ? 0 : this.getTemplateName()
						.hashCode());
		return result;
	}
}
