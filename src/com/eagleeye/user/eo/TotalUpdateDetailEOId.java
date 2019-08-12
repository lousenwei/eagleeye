package com.eagleeye.user.eo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TotalUpdateDetailId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TotalUpdateDetailEOId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2141917584897470725L;
	private String staffId;
	private Date updateTime;
	private String managerId;

	// Constructors

	/** default constructor */
	public TotalUpdateDetailEOId() {
	}

	/** full constructor */
	public TotalUpdateDetailEOId(String staffId, Date updateTime,
			String managerId) {
		this.staffId = staffId;
		this.updateTime = updateTime;
		this.managerId = managerId;
	}

	// Property accessors

	@Column(name = "staff_id", nullable = false, length = 100)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "update_time", nullable = false, length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TotalUpdateDetailEOId))
			return false;
		TotalUpdateDetailEOId castOther = (TotalUpdateDetailEOId) other;

		return ((this.getStaffId() == castOther.getStaffId()) || (this
				.getStaffId() != null && castOther.getStaffId() != null && this
				.getStaffId().equals(castOther.getStaffId())))
				&& ((this.getManagerId() == castOther.getManagerId()) || (this
						.getManagerId() != null
						&& castOther.getManagerId() != null && this
						.getManagerId().equals(castOther.getManagerId())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStaffId() == null ? 0 : this.getStaffId().hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		return result;
	}

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}