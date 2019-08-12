package com.eagleeye.user.eo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IncrementUpdateDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "increment_update_detail", catalog = "eagleeye")
public class IncrementUpdateDetailEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3054470007162833456L;
	private IncrementUpdateDetailEOId id;
	private String status;
	private String sessionKey;
	private String shopId;
	private Date createAt = new Date();
	private Date startAt;
	private Date endAt;

	// Constructors

	/** default constructor */
	public IncrementUpdateDetailEO() {
	}

	/** minimal constructor */
	public IncrementUpdateDetailEO(IncrementUpdateDetailEOId id) {
		this.id = id;
	}

	/** full constructor */
	public IncrementUpdateDetailEO(IncrementUpdateDetailEOId id, String status,
			String sessionKey) {
		this.id = id;
		this.status = status;
		this.sessionKey = sessionKey;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "managerId", column = @Column(name = "manager_id", nullable = false, length = 100)),
			@AttributeOverride(name = "updateTime", column = @Column(name = "update_time", nullable = false, length = 0)) })
	public IncrementUpdateDetailEOId getId() {
		return this.id;
	}

	public void setId(IncrementUpdateDetailEOId id) {
		this.id = id;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "session_key", length = 100)
	public String getSessionKey() {
		return this.sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	@Column(name = "create_at", nullable = false, length = 0)
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Column(name = "shop_id", nullable = false, length = 36)
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Column(name = "start_at", length = 0)
	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	@Column(name = "end_at", length = 0)
	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

}