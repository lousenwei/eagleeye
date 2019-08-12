package com.eagleeye.user.eo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IncrementUpdateDetailId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class IncrementUpdateDetailEOId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8979069274451205015L;
	// Fields

	private String managerId;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public IncrementUpdateDetailEOId() {
	}

	/** full constructor */
	public IncrementUpdateDetailEOId(String managerId, Date updateTime) {
		this.managerId = managerId;
		this.updateTime = updateTime;
	}

	// Property accessors

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
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
		if (!(other instanceof IncrementUpdateDetailEOId))
			return false;
		IncrementUpdateDetailEOId castOther = (IncrementUpdateDetailEOId) other;

		return ((this.getManagerId() == castOther.getManagerId()) || (this
				.getManagerId() != null
				&& castOther.getManagerId() != null && this.getManagerId()
				.equals(castOther.getManagerId())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		return result;
	}

}