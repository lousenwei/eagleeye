package com.eagleeye.user.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manager_info", catalog = "eagleeye")
public class ManagerInfoEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8519988522324427561L;
	private String shopId;
	private String managerId;
	private String status;
	//2012-8-14,v1.7,增加两个字段，分别用于修改和查询密码
	private String readPsd;
	private String modifyPsd;
	//2014.6.13,业绩补零开关
	private Boolean statZeroSwitch=false;

	// Constructors

	/** default constructor */
	public ManagerInfoEO() {
	}

	/** full constructor */
	public ManagerInfoEO(String status, String managerId, String shopId) {
		this.managerId = managerId;
		this.shopId = shopId;
		this.status = status;
	}

	@Column(name = "shop_id", unique = true, nullable = false)
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	// Property accessors
	@Id
	@Column(name = "manager_id", unique = true, nullable = false)
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "status", unique = true, nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//2012-8-14,v1.7,增加两个字段，分别用于修改和查询密码,begin
	@Column(name = "read_psd", nullable = true, length = 50)
	public String getReadPsd() {
		return readPsd;
	}

	public void setReadPsd(String readPsd) {
		this.readPsd = readPsd;
	}

	@Column(name = "modify_psd", nullable = true, length = 50)
	public String getModifyPsd() {
		return modifyPsd;
	}

	public void setModifyPsd(String modifyPsd) {
		this.modifyPsd = modifyPsd;
	}
	//2012-8-14,v1.7,增加两个字段，分别用于修改和查询密码,end
	
	@Column(name = "stat_zero_switch", nullable = false)
	public Boolean getStatZeroSwitch() {
		return statZeroSwitch;
	}

	public void setStatZeroSwitch(Boolean statZeroSwitch) {
		this.statZeroSwitch = statZeroSwitch;
	}
}