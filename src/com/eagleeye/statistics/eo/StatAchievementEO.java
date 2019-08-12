package com.eagleeye.statistics.eo;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * StatAchievement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stat_achievement", catalog = "eagleeye")
public class StatAchievementEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6110707103464522929L;
	private StatAchievementEOId id;
	private String shopId;
	private BigDecimal amountWaitSend = BigDecimal.ZERO;
	private BigDecimal amountWaitConfirm = BigDecimal.ZERO;
	private BigDecimal amountSuccess = BigDecimal.ZERO;
	private BigDecimal amountAllRefund = BigDecimal.ZERO;
	private BigDecimal amountPartRefund = BigDecimal.ZERO;
	private BigDecimal amountRefunding = BigDecimal.ZERO;
	private BigDecimal postFee = BigDecimal.ZERO;
	private BigDecimal totalAmount = BigDecimal.ZERO;

	// Constructors

	/** default constructor */
	public StatAchievementEO() {
	}

	/** full constructor */
	public StatAchievementEO(StatAchievementEOId id, BigDecimal amountWaitSend,
			BigDecimal amountWaitConfirm, BigDecimal amountSuccess,
			BigDecimal amountAllRefund, BigDecimal amountPartRefund,
			BigDecimal amountRefunding, BigDecimal totalAmount) {
		this.id = id;
		this.amountWaitSend = amountWaitSend;
		this.amountWaitConfirm = amountWaitConfirm;
		this.amountSuccess = amountSuccess;
		this.amountAllRefund = amountAllRefund;
		this.amountPartRefund = amountPartRefund;
		this.amountRefunding = amountRefunding;
		this.totalAmount = totalAmount;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "statDate", column = @Column(name = "stat_date", nullable = false, length = 0)),
			@AttributeOverride(name = "staffId", column = @Column(name = "staff_id", nullable = false, length = 100)),
			@AttributeOverride(name = "managerId", column = @Column(name = "manager_id", nullable = false, length = 100)),
			@AttributeOverride(name = "isTotal", column = @Column(name = "is_total", nullable = false)) })
	public StatAchievementEOId getId() {
		return this.id;
	}

	public void setId(StatAchievementEOId id) {
		this.id = id;
	}

	@Column(name = "amount_wait_send", nullable = false, precision = 12, scale = 0)
	public BigDecimal getAmountWaitSend() {
		return this.amountWaitSend;
	}

	public void setAmountWaitSend(BigDecimal amountWaitSend) {
		this.amountWaitSend = amountWaitSend;
	}

	@Column(name = "amount_wait_confirm", nullable = false, precision = 12, scale = 0)
	public BigDecimal getAmountWaitConfirm() {
		return this.amountWaitConfirm;
	}

	public void setAmountWaitConfirm(BigDecimal amountWaitConfirm) {
		this.amountWaitConfirm = amountWaitConfirm;
	}

	@Column(name = "amount_success", nullable = false, precision = 12, scale = 0)
	public BigDecimal getAmountSuccess() {
		return this.amountSuccess;
	}

	public void setAmountSuccess(BigDecimal amountSuccess) {
		this.amountSuccess = amountSuccess;
	}

	@Column(name = "amount_all_refund", nullable = false, precision = 12, scale = 0)
	public BigDecimal getAmountAllRefund() {
		return this.amountAllRefund;
	}

	public void setAmountAllRefund(BigDecimal amountAllRefund) {
		this.amountAllRefund = amountAllRefund;
	}

	@Column(name = "amount_part_refund", nullable = false, precision = 12, scale = 0)
	public BigDecimal getAmountPartRefund() {
		return this.amountPartRefund;
	}

	public void setAmountPartRefund(BigDecimal amountPartRefund) {
		this.amountPartRefund = amountPartRefund;
	}

	@Column(name = "total_amount", nullable = false, precision = 12, scale = 0)
	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "post_fee", nullable = false, precision = 12, scale = 0)
	public BigDecimal getPostFee() {
		return postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	@Column(name = "shop_id", nullable = false, length = 50)
	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Column(name = "amount_refunding", nullable = false, precision = 12, scale = 0)
	public BigDecimal getAmountRefunding() {
		return amountRefunding;
	}

	public void setAmountRefunding(BigDecimal amountRefunding) {
		this.amountRefunding = amountRefunding;
	}
}