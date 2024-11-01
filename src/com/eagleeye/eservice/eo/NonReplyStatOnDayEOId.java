package com.eagleeye.eservice.eo;
// default package
// Generated 2011-11-19 12:12:31 by Hibernate Tools 3.3.0.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NonReplyStatOnDayId generated by hbm2java
 */
@Embeddable
public class NonReplyStatOnDayEOId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3850906652494910712L;
	private String serviceStaffId;
	private Date nonreplyDate;

	public NonReplyStatOnDayEOId() {
	}

	public NonReplyStatOnDayEOId(String serviceStaffId, Date nonreplyDate) {
		this.serviceStaffId = serviceStaffId;
		this.nonreplyDate = nonreplyDate;
	}

	@Column(name = "service_staff_id", nullable = false, length = 100)
	public String getServiceStaffId() {
		return this.serviceStaffId;
	}

	public void setServiceStaffId(String serviceStaffId) {
		this.serviceStaffId = serviceStaffId;
	}

	@Column(name = "nonreply_date", nullable = false, length = 0)
	public Date getNonreplyDate() {
		return this.nonreplyDate;
	}

	public void setNonreplyDate(Date nonreplyDate) {
		this.nonreplyDate = nonreplyDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NonReplyStatOnDayEOId))
			return false;
		NonReplyStatOnDayEOId castOther = (NonReplyStatOnDayEOId) other;

		return ((this.getServiceStaffId() == castOther.getServiceStaffId()) || (this
				.getServiceStaffId() != null
				&& castOther.getServiceStaffId() != null && this
				.getServiceStaffId().equals(castOther.getServiceStaffId())))
				&& ((this.getNonreplyDate() == castOther.getNonreplyDate()) || (this
						.getNonreplyDate() != null
						&& castOther.getNonreplyDate() != null && this
						.getNonreplyDate().equals(castOther.getNonreplyDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getServiceStaffId() == null ? 0 : this.getServiceStaffId()
						.hashCode());
		result = 37
				* result
				+ (getNonreplyDate() == null ? 0 : this.getNonreplyDate()
						.hashCode());
		return result;
	}

}
