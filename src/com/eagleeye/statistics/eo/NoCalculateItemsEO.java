package com.eagleeye.statistics.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NoCalculateItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "no_calculate_items", catalog = "eagleeye")
public class NoCalculateItemsEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8553744374174689179L;
	private Long numIid;
	private String managerId;

	// Constructors

	/** default constructor */
	public NoCalculateItemsEO() {
	}

	/** full constructor */
	public NoCalculateItemsEO(Long numIid, String managerId) {
		this.numIid = numIid;
		this.managerId = managerId;
	}

	// Property accessors
	@Id
	@Column(name = "num_iid", nullable = false, length = 100)
	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}