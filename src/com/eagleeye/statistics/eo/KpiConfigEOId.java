package com.eagleeye.statistics.eo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * KpiConfigId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class KpiConfigEOId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6687569221017374833L;
	private String managerId;
	private String shopId;

	// Constructors

	/** default constructor */
	public KpiConfigEOId() {
	}

	/** full constructor */
	public KpiConfigEOId(String managerId, String shopId) {
		this.managerId = managerId;
		this.shopId = shopId;
	}

	// Property accessors

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "shop_id", nullable = false, length = 100)
	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KpiConfigEOId))
			return false;
		KpiConfigEOId castOther = (KpiConfigEOId) other;

		return ((this.getManagerId() == castOther.getManagerId()) || (this
				.getManagerId() != null
				&& castOther.getManagerId() != null && this.getManagerId()
				.equals(castOther.getManagerId())))
				&& ((this.getShopId() == castOther.getShopId()) || (this
						.getShopId() != null
						&& castOther.getShopId() != null && this.getShopId()
						.equals(castOther.getShopId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		result = 37 * result
				+ (getShopId() == null ? 0 : this.getShopId().hashCode());
		return result;
	}

}