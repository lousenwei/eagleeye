package com.eagleeye.eservice.eo;

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
 * Updatelog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "updatelog", catalog = "eagleeye")
public class UpdatelogEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6874474075705387206L;
	private UpdatelogEOId id;
	private Date updateTime;
	private Integer priority;

	// Constructors

	/** default constructor */
	public UpdatelogEO() {
	}

	/** full constructor */
	public UpdatelogEO(UpdatelogEOId id, Date updateTime) {
		this.id = id;
		this.updateTime = updateTime;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "managerId", column = @Column(name = "manager_id", nullable = false, length = 0)),
			@AttributeOverride(name = "item", column = @Column(name = "item", nullable = false, length = 45)) })
	public UpdatelogEOId getId() {
		return this.id;
	}

	public void setId(UpdatelogEOId id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}