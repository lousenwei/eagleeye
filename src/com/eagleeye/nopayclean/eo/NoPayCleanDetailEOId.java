package com.eagleeye.nopayclean.eo;
// default package
// Generated 2011-12-23 17:46:39 by Hibernate Tools 3.3.0.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NoPayCleanDetailId generated by hbm2java
 */
@Embeddable
public class NoPayCleanDetailEOId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1523237207013820837L;
	private String managerId;
	private Date createDate;

	public NoPayCleanDetailEOId() {
	}

	public NoPayCleanDetailEOId(String managerId, Date createDate) {
		this.managerId = managerId;
		this.createDate = createDate;
	}

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "create_date", nullable = false, length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NoPayCleanDetailEOId))
			return false;
		NoPayCleanDetailEOId castOther = (NoPayCleanDetailEOId) other;

		return ((this.getManagerId() == castOther.getManagerId()) || (this
				.getManagerId() != null
				&& castOther.getManagerId() != null && this.getManagerId()
				.equals(castOther.getManagerId())))
				&& ((this.getCreateDate() == castOther.getCreateDate()) || (this
						.getCreateDate() != null
						&& castOther.getCreateDate() != null && this
						.getCreateDate().equals(castOther.getCreateDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		result = 37
				* result
				+ (getCreateDate() == null ? 0 : this.getCreateDate()
						.hashCode());
		return result;
	}

}
