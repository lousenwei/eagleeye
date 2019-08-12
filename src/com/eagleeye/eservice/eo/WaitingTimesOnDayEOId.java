package com.eagleeye.eservice.eo;
// default package
// Generated 2011-11-19 12:12:31 by Hibernate Tools 3.3.0.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WaitingTimesOnDayId generated by hbm2java
 */
@Embeddable
public class WaitingTimesOnDayEOId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7032732474948053607L;
	private String serviceStaffId;
	private Date waitingDate;

	public WaitingTimesOnDayEOId() {
	}

	public WaitingTimesOnDayEOId(String serviceStaffId, Date waitingDate) {
		this.serviceStaffId = serviceStaffId;
		this.waitingDate = waitingDate;
	}

	@Column(name = "service_staff_id", nullable = false, length = 100)
	public String getServiceStaffId() {
		return this.serviceStaffId;
	}

	public void setServiceStaffId(String serviceStaffId) {
		this.serviceStaffId = serviceStaffId;
	}

	@Column(name = "waiting_date", nullable = false, length = 0)
	public Date getWaitingDate() {
		return this.waitingDate;
	}

	public void setWaitingDate(Date waitingDate) {
		this.waitingDate = waitingDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WaitingTimesOnDayEOId))
			return false;
		WaitingTimesOnDayEOId castOther = (WaitingTimesOnDayEOId) other;

		return ((this.getServiceStaffId() == castOther.getServiceStaffId()) || (this
				.getServiceStaffId() != null
				&& castOther.getServiceStaffId() != null && this
				.getServiceStaffId().equals(castOther.getServiceStaffId())))
				&& ((this.getWaitingDate() == castOther.getWaitingDate()) || (this
						.getWaitingDate() != null
						&& castOther.getWaitingDate() != null && this
						.getWaitingDate().equals(castOther.getWaitingDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getServiceStaffId() == null ? 0 : this.getServiceStaffId()
						.hashCode());
		result = 37
				* result
				+ (getWaitingDate() == null ? 0 : this.getWaitingDate()
						.hashCode());
		return result;
	}

}
