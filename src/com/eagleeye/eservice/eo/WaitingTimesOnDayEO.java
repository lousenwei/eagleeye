package com.eagleeye.eservice.eo;
// default package
// Generated 2011-11-19 12:12:31 by Hibernate Tools 3.3.0.GA

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WaitingTimesOnDay generated by hbm2java
 */
@Entity
@Table(name = "waiting_times_on_day", catalog = "eagleeye")
public class WaitingTimesOnDayEO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1046458120208428168L;
	private WaitingTimesOnDayEOId id;
	private long avgWaitingTimes;
	private Date createdAt;
	private Date updatedAt;
	private String managerId;

	public WaitingTimesOnDayEO() {
	}

	public WaitingTimesOnDayEO(WaitingTimesOnDayEOId id, long avgWaitingTimes,
			Date createdAt, Date updatedAt, String managerId) {
		this.id = id;
		this.avgWaitingTimes = avgWaitingTimes;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.managerId = managerId;
	}

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "serviceStaffId", column = @Column(name = "service_staff_id", nullable = false, length = 100)),
			@AttributeOverride(name = "waitingDate", column = @Column(name = "waiting_date", nullable = false, length = 0)) })
	public WaitingTimesOnDayEOId getId() {
		return this.id;
	}

	public void setId(WaitingTimesOnDayEOId id) {
		this.id = id;
	}

	@Column(name = "avg_waiting_times", nullable = false)
	public long getAvgWaitingTimes() {
		return this.avgWaitingTimes;
	}

	public void setAvgWaitingTimes(long avgWaitingTimes) {
		this.avgWaitingTimes = avgWaitingTimes;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 0)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 0)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
