package com.eagleeye.user.eo;

// default package
// Generated 2011-11-19 12:12:31 by Hibernate Tools 3.3.0.GA

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GroupMemberId generated by hbm2java
 */
@Embeddable
public class GroupMemberEOId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3969321591709635259L;
	private String serviceStaffId;
	private String managerId;

	public GroupMemberEOId() {
	}

	public GroupMemberEOId(String serviceStaffId, String managerId) {
		this.serviceStaffId = serviceStaffId;
		this.managerId = managerId;
	}

	@Column(name = "service_staff_id", nullable = false, length = 100)
	public String getServiceStaffId() {
		return this.serviceStaffId;
	}

	public void setServiceStaffId(String serviceStaffId) {
		this.serviceStaffId = serviceStaffId;
	}

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GroupMemberEOId))
			return false;
		GroupMemberEOId castOther = (GroupMemberEOId) other;

		return ((this.getServiceStaffId() == castOther.getServiceStaffId()) || (this
				.getServiceStaffId() != null
				&& castOther.getServiceStaffId() != null && this
				.getServiceStaffId().equals(castOther.getServiceStaffId())))
				&& ((this.getManagerId() == castOther.getManagerId()) || (this
						.getManagerId() != null
						&& castOther.getManagerId() != null && this
						.getManagerId().equals(castOther.getManagerId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getServiceStaffId() == null ? 0 : this.getServiceStaffId()
						.hashCode());
		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		return result;
	}

}
