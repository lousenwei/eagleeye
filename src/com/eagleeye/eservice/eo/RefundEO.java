package com.eagleeye.eservice.eo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Refund entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "refund", catalog = "eagleeye")
public class RefundEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 804633480080595886L;
	private Long refundId;
	private Long tid;
	private Long oid;
	private Float totalFee;
	private String buyerNick;
	private String sellerNick;
	private Date created;
	private Date modified;
	private String orderStatus;
	private String status;
	private String goodStatus;
	private Boolean hasGoodReturn;
	private Float refundFee;
	private Float payment;
	private String reason;
	private String description;
	private Date payTime;

	// Constructors

	/** default constructor */
	public RefundEO() {
	}

	/** full constructor */
	public RefundEO(Long refundId, Long tid, Long oid, Float totalFee,
			String buyerNick, String sellerNick, Date created, Date modified,
			String orderStatus, String status, String goodStatus,
			Boolean hasGoodReturn, Float refundFee, Float payment,
			String reason, String description) {
		this.refundId = refundId;
		this.tid = tid;
		this.oid = oid;
		this.totalFee = totalFee;
		this.buyerNick = buyerNick;
		this.sellerNick = sellerNick;
		this.created = created;
		this.modified = modified;
		this.orderStatus = orderStatus;
		this.status = status;
		this.goodStatus = goodStatus;
		this.hasGoodReturn = hasGoodReturn;
		this.refundFee = refundFee;
		this.payment = payment;
		this.reason = reason;
		this.description = description;
	}

	// Property accessors
	@Id
	@Column(name = "refund_id", unique = true, nullable = false)
	public Long getRefundId() {
		return this.refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	@Column(name = "tid", nullable = true)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "oid", nullable = true)
	public Long getOid() {
		return this.oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	@Column(name = "total_fee", nullable = true, precision = 12, scale = 0)
	public Float getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	@Column(name = "buyer_nick", nullable = true)
	public String getBuyerNick() {
		return this.buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	@Column(name = "seller_nick", nullable = true, length = 100)
	public String getSellerNick() {
		return this.sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created", nullable = true, length = 0)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "modified", nullable = true, length = 0)
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Column(name = "order_status", nullable = true, length = 50)
	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "refund_status", nullable = true, length = 50)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "good_status", nullable = true, length = 50)
	public String getGoodStatus() {
		return this.goodStatus;
	}

	public void setGoodStatus(String goodStatus) {
		this.goodStatus = goodStatus;
	}

	@Column(name = "has_good_return", nullable = true)
	public Boolean getHasGoodReturn() {
		return this.hasGoodReturn;
	}

	public void setHasGoodReturn(Boolean hasGoodReturn) {
		this.hasGoodReturn = hasGoodReturn;
	}

	@Column(name = "refund_fee", nullable = true, precision = 12, scale = 0)
	public Float getRefundFee() {
		return this.refundFee;
	}

	public void setRefundFee(Float refundFee) {
		this.refundFee = refundFee;
	}

	@Column(name = "payment", nullable = true, precision = 12, scale = 0)
	public Float getPayment() {
		return this.payment;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
	}

	@Column(name = "reason", nullable = true)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pay_time", nullable = true, length = 0)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
}