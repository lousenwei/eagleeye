package com.eagleeye.user.eo;

// Generated 2012-8-18 21:57:46 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ReadLockMappingId generated by hbm2java
 */
@Embeddable
public class ReadLockMappingEOId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8224493885458823896L;
	private String managerId;
	private String readLockUrl;

	public ReadLockMappingEOId() {
	}

	public ReadLockMappingEOId(String managerId, String readLockUrl) {
		this.managerId = managerId;
		this.readLockUrl = readLockUrl;
	}

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "read_lock_url", nullable = false, length = 100)
	public String getReadLockUrl() {
		return this.readLockUrl;
	}

	public void setReadLockUrl(String readLockUrl) {
		this.readLockUrl = readLockUrl;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReadLockMappingEOId))
			return false;
		ReadLockMappingEOId castOther = (ReadLockMappingEOId) other;

		return ((this.getManagerId() == castOther.getManagerId()) || (this
				.getManagerId() != null && castOther.getManagerId() != null && this
				.getManagerId().equals(castOther.getManagerId())))
				&& ((this.getReadLockUrl() == castOther.getReadLockUrl()) || (this
						.getReadLockUrl() != null
						&& castOther.getReadLockUrl() != null && this
						.getReadLockUrl().equals(castOther.getReadLockUrl())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		result = 37
				* result
				+ (getReadLockUrl() == null ? 0 : this.getReadLockUrl()
						.hashCode());
		return result;
	}

}