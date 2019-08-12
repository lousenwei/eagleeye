package com.eagleeye.statistics.eo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * StatKpiId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class StatKpiEOId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2138598055520176837L;
	private Date resultDate;
	private String staffId;
	private String shopId;
	private String managerId;

	// Constructors

	/** default constructor */
	public StatKpiEOId() {
	}

	/** full constructor */
	public StatKpiEOId(Date resultDate, String staffId, String shopId,
			String managerId) {
		this.resultDate = resultDate;
		this.staffId = staffId;
		this.shopId = shopId;
		this.managerId = managerId;
	}

	// Property accessors
	@Temporal(TemporalType.DATE)
	@Column(name = "result_date", nullable = false, length = 0)
	public Date getResultDate() {
		return this.resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	@Column(name = "staff_id", nullable = false, length = 100)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "shop_id", nullable = false, length = 100)
	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
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
		if (!(other instanceof StatKpiEOId))
			return false;
		StatKpiEOId castOther = (StatKpiEOId) other;

		return ((this.getResultDate() == castOther.getResultDate()) || (this
				.getResultDate() != null
				&& castOther.getResultDate() != null && this.getResultDate()
				.equals(castOther.getResultDate())))
				&& ((this.getStaffId() == castOther.getStaffId()) || (this
						.getStaffId() != null
						&& castOther.getStaffId() != null && this.getStaffId()
						.equals(castOther.getStaffId())))
				&& ((this.getShopId() == castOther.getShopId()) || (this
						.getShopId() != null
						&& castOther.getShopId() != null && this.getShopId()
						.equals(castOther.getShopId())))
				&& ((this.getManagerId() == castOther.getManagerId()) || (this
						.getManagerId() != null
						&& castOther.getManagerId() != null && this
						.getManagerId().equals(castOther.getManagerId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getResultDate() == null ? 0 : this.getResultDate()
						.hashCode());
		result = 37 * result
				+ (getStaffId() == null ? 0 : this.getStaffId().hashCode());
		result = 37 * result
				+ (getShopId() == null ? 0 : this.getShopId().hashCode());
		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		return result;
	}

}