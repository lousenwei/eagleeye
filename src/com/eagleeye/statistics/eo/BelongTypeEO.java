package com.eagleeye.statistics.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.eagleeye.common.constant.EagleConstants;

/**
 * NoCalculateItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "belong_type", catalog = "eagleeye")
public class BelongTypeEO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2303809198177984426L;
	private String type = EagleConstants.BELONG_TYPE_PAY_FIRST_TALK;
	private String managerId;

	// Constructors

	/** default constructor */
	public BelongTypeEO() {
	}

	/** full constructor */
	public BelongTypeEO(String type, String managerId) {
		this.type = type;
		this.managerId = managerId;
	}

	// Property accessors

	@Id
	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "type", nullable = false, length = 2)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}