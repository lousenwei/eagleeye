package com.eagleeye.user.eo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TotalUpdateDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "total_update_detail", catalog = "eagleeye")
public class TotalUpdateDetailEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 326468764324855015L;
	private TotalUpdateDetailEOId id;

	private String sessionKey;
	private String status;
	private Date createAt = new Date();
	private String shopId;
	private Date startAt;
	private Date endAt;

	// Constructors

	/** default constructor */
	public TotalUpdateDetailEO() {
	}

	/** minimal constructor */
	public TotalUpdateDetailEO(TotalUpdateDetailEOId id) {
		this.id = id;
	}

	/** full constructor */
	public TotalUpdateDetailEO(TotalUpdateDetailEOId id, String sessionKey,
			String status) {
		this.id = id;
		this.sessionKey = sessionKey;
		this.status = status;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "staffId", column = @Column(name = "staff_id", nullable = false, length = 100)),
			@AttributeOverride(name = "updateTime", column = @Column(name = "update_time", nullable = false, length = 0)),
			@AttributeOverride(name = "managerId", column = @Column(name = "manager_id", nullable = false, length = 100)) })
	public TotalUpdateDetailEOId getId() {
		return this.id;
	}

	public void setId(TotalUpdateDetailEOId id) {
		this.id = id;
	}

	@Column(name = "session_key", nullable = false, length = 100)
	public String getSessionKey() {
		return this.sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	@Column(name = "status", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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