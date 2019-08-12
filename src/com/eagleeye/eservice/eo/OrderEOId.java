package com.eagleeye.eservice.eo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OrderId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class OrderEOId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3463745544569061463L;
	private Long oid;
	private Long tid;

	// Constructors

	/** default constructor */
	public OrderEOId() {
	}

	/** full constructor */
	public OrderEOId(Long oid, Long tid) {
		this.oid = oid;
		this.tid = tid;
	}

	// Property accessors

	@Column(name = "oid", nullable = false)
	public Long getOid() {
		return this.oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	@Column(name = "tid", nullable = false)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderEOId))
			return false;
		OrderEOId castOther = (OrderEOId) other;

		return ((this.getOid() == castOther.getOid()) || (this.getOid() != null
				&& castOther.getOid() != null && this.getOid().equals(
				castOther.getOid())))
				&& ((this.getTid() == castOther.getTid()) || (this.getTid() != null
						&& castOther.getTid() != null && this.getTid().equals(
						castOther.getTid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOid() == null ? 0 : this.getOid().hashCode());
		result = 37 * result
				+ (getTid() == null ? 0 : this.getTid().hashCode());
		return result;
	}

}