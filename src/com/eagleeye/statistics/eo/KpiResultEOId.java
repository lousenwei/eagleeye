package com.eagleeye.statistics.eo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * KpiResultId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class KpiResultEOId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8208575239757927983L;
	private String managerId;
	private String shopId;
	private String staffId;
	private Date resultDate;

	// Constructors

	/** default constructor */
	public KpiResultEOId() {
	}

	/** full constructor */
	public KpiResultEOId(String managerId, String shopId, String staffId,
			Date resultDate) {
		this.managerId = managerId;
		this.shopId = shopId;
		this.staffId = staffId;
		this.resultDate = resultDate;
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

	@Column(name = "staff_id", nullable = false, length = 100)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "result_date", nullable = false, length = 0)
	public Date getResultDate() {
		return this.resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KpiResultEOId))
			return false;
		KpiResultEOId castOther = (KpiResultEOId) other;

		return ((this.getManagerId() == castOther.getManagerId()) || (this
				.getManagerId() != null
				&& castOther.getManagerId() != null && this.getManagerId()
				.equals(castOther.getManagerId())))
				&& ((this.getShopId() == castOther.getShopId()) || (this
						.getShopId() != null
						&& castOther.getShopId() != null && this.getShopId()
						.equals(castOther.getShopId())))
				&& ((this.getStaffId() == castOther.getStaffId()) || (this
						.getStaffId() != null
						&& castOther.getStaffId() != null && this.getStaffId()
						.equals(castOther.getStaffId())))
				&& ((this.getResultDate() == castOther.getResultDate()) || (this
						.getResultDate() != null
						&& castOther.getResultDate() != null && this
						.getResultDate().equals(castOther.getResultDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		result = 37 * result
				+ (getShopId() == null ? 0 : this.getShopId().hashCode());
		result = 37 * result
				+ (getStaffId() == null ? 0 : this.getStaffId().hashCode());
		result = 37
				* result
				+ (getResultDate() == null ? 0 : this.getResultDate()
						.hashCode());
		return result;
	}

}