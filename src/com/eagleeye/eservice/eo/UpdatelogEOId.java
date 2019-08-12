package com.eagleeye.eservice.eo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UpdatelogId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UpdatelogEOId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7236593401582851169L;
	private String managerId;

	private String item;

	// Constructors

	/** default constructor */
	public UpdatelogEOId() {
	}

	/** full constructor */
	public UpdatelogEOId(String managerId, String item) {
		this.managerId = managerId;
		this.item = item;
	}

	// Property accessors

	@Column(name = "manager_id", nullable = false, length = 100)
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "item", nullable = false, length = 45)
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}