package com.eagleeye.statistics.eo;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * StatKpi entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stat_kpi", catalog = "eagleeye")
public class StatKpiEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7860966862740403500L;
	private StatKpiEOId id;
	private BigDecimal manBuy;
	private BigDecimal targetSaleAmount;
	private BigDecimal realSaleAmount;
	private Integer replyPerson;
	private Integer noReplyPerson;
	private Integer payPerson;
	private BigDecimal exchangeRate;
	private BigDecimal barganAbility;
	private Long responseTime;
	private BigDecimal refundRate;

	// Constructors

	/** default constructor */
	public StatKpiEO() {
	}

	/** full constructor */
	public StatKpiEO(StatKpiEOId id, BigDecimal manBuy, BigDecimal targetSaleAmount,
			BigDecimal realSaleAmount, Integer replyPerson, Integer noReplyPerson,
			Integer payPerson, BigDecimal exchangeRate, BigDecimal barganAbility,
			Long responseTime, BigDecimal refundRate) {
		this.id = id;
		this.manBuy = manBuy;
		this.targetSaleAmount = targetSaleAmount;
		this.realSaleAmount = realSaleAmount;
		this.replyPerson = replyPerson;
		this.noReplyPerson = noReplyPerson;
		this.payPerson = payPerson;
		this.exchangeRate = exchangeRate;
		this.barganAbility = barganAbility;
		this.responseTime = responseTime;
		this.refundRate = refundRate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "resultDate", column = @Column(name = "result_date", nullable = false, length = 0)),
			@AttributeOverride(name = "staffId", column = @Column(name = "staff_id", nullable = false, length = 100)),
			@AttributeOverride(name = "shopId", column = @Column(name = "shop_id", nullable = false, length = 100)),
			@AttributeOverride(name = "managerId", column = @Column(name = "manager_id", nullable = false, length = 100)) })
	public StatKpiEOId getId() {
		return this.id;
	}

	public void setId(StatKpiEOId id) {
		this.id = id;
	}

	@Column(name = "man_buy", nullable = false, precision = 12, scale = 0)
	public BigDecimal getManBuy() {
		return this.manBuy;
	}

	public void setManBuy(BigDecimal manBuy) {
		this.manBuy = manBuy;
	}

	@Column(name = "target_sale_amount", nullable = false, precision = 12, scale = 0)
	public BigDecimal getTargetSaleAmount() {
		return this.targetSaleAmount;
	}

	public void setTargetSaleAmount(BigDecimal targetSaleAmount) {
		this.targetSaleAmount = targetSaleAmount;
	}

	@Column(name = "real_sale_amount", nullable = false, precision = 12, scale = 0)
	public BigDecimal getRealSaleAmount() {
		return this.realSaleAmount;
	}

	public void setRealSaleAmount(BigDecimal realSaleAmount) {
		this.realSaleAmount = realSaleAmount;
	}

	@Column(name = "reply_person", nullable = false)
	public Integer getReplyPerson() {
		return this.replyPerson;
	}

	public void setReplyPerson(Integer replyPerson) {
		this.replyPerson = replyPerson;
	}

	@Column(name = "no_reply_person", nullable = false)
	public Integer getNoReplyPerson() {
		return this.noReplyPerson;
	}

	public void setNoReplyPerson(Integer noReplyPerson) {
		this.noReplyPerson = noReplyPerson;
	}

	@Column(name = "pay_person", nullable = false)
	public Integer getPayPerson() {
		return this.payPerson;
	}

	public void setPayPerson(Integer payPerson) {
		this.payPerson = payPerson;
	}

	@Column(name = "exchange_rate", nullable = false, precision = 12, scale = 0)
	public BigDecimal getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name = "bargan_ability", nullable = false, precision = 12, scale = 0)
	public BigDecimal getBarganAbility() {
		return this.barganAbility;
	}

	public void setBarganAbility(BigDecimal barganAbility) {
		this.barganAbility = barganAbility;
	}

	@Column(name = "response_time", nullable = false)
	public Long getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}

	@Column(name = "refund_rate", nullable = false, precision = 12, scale = 0)
	public BigDecimal getRefundRate() {
		return this.refundRate;
	}

	public void setRefundRate(BigDecimal refundRate) {
		this.refundRate = refundRate;
	}

}