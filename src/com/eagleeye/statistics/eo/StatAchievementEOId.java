package com.eagleeye.statistics.eo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * StatAchievementId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class StatAchievementEOId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6837252687334368353L;
	private Date statDate;
	private String staffId;
	private Boolean isTotal;
	private String managerId;

	// Constructors

	/** default constructor */
	public StatAchievementEOId() {
	}

	/** full constructor */
	public StatAchievementEOId(Date statDate, String staffId, Boolean isTotal,
			String managerId) {
		this.statDate = statDate;
		this.staffId = staffId;
		this.isTotal = isTotal;
		this.managerId = managerId;
	}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "stat_date", nullable = false, length = 0)
	public Date getStatDate() {
		return this.statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	@Column(name = "staff_id", nullable = false, length = 100)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "is_total", nullable = false)
	public Boolean getIsTotal() {
		return this.isTotal;
	}

	public void setIsTotal(Boolean isTotal) {
		this.isTotal = isTotal;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StatAchievementEOId))
			return false;
		StatAchievementEOId castOther = (StatAchievementEOId) other;

		return ((this.getStatDate() == castOther.getStatDate()) || (this
				.getStatDate() != null && castOther.getStatDate() != null && this
				.getStatDate().equals(castOther.getStatDate())))
				&& ((this.getStaffId() == castOther.getStaffId()) || (this
						.getStaffId() != null && castOther.getStaffId() != null && this
						.getStaffId().equals(castOther.getStaffId())))
				&& ((this.getManagerId() == castOther.getManagerId()) || (this
						.getManagerId() != null
						&& castOther.getManagerId() != null && this
						.getManagerId().equals(castOther.getManagerId())))
				&& ((this.getIsTotal() == castOther.getIsTotal()) || (this
						.getIsTotal() != null && castOther.getIsTotal() != null && this
						.getIsTotal().equals(castOther.getIsTotal())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result
				+ (getStatDate() == null ? 0 : this.getStatDate().hashCode());
		result = 37 * result
				+ (getStaffId() == null ? 0 : this.getStaffId().hashCode());
		result = 37 * result
				+ (getIsTotal() == null ? 0 : this.getIsTotal().hashCode());
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