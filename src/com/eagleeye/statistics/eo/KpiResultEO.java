package com.eagleeye.statistics.eo;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * KpiResult entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "kpi_result", catalog = "eagleeye")
public class KpiResultEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5572343472639405466L;
	private KpiResultEOId id;
	private BigDecimal kpiSaleAmount;
	private BigDecimal kpiExchange;
	private BigDecimal kpiManBuy;
	private BigDecimal kpiFirstResponse;
	private BigDecimal kpiRefundRate;
	private BigDecimal kpiGoodEval;
	private BigDecimal kpiTotal;

	// Constructors

	/** default constructor */
	public KpiResultEO() {
	}

	/** full constructor */
	public KpiResultEO(KpiResultEOId id, BigDecimal kpiSaleAmount, BigDecimal kpiExchange,
			BigDecimal kpiManBuy, BigDecimal kpiFirstResponse, BigDecimal kpiRefundRate,
			BigDecimal kpiGoodEval, BigDecimal kpiTotal) {
		this.id = id;
		this.kpiSaleAmount = kpiSaleAmount;
		this.kpiExchange = kpiExchange;
		this.kpiManBuy = kpiManBuy;
		this.kpiFirstResponse = kpiFirstResponse;
		this.kpiRefundRate = kpiRefundRate;
		this.kpiGoodEval = kpiGoodEval;
		this.kpiTotal = kpiTotal;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "managerId", column = @Column(name = "manager_id", nullable = false, length = 100)),
			@AttributeOverride(name = "shopId", column = @Column(name = "shop_id", nullable = false, length = 100)),
			@AttributeOverride(name = "staffId", column = @Column(name = "staff_id", nullable = false, length = 100)),
			@AttributeOverride(name = "resultDate", column = @Column(name = "result_date", nullable = false, length = 0)) })
	public KpiResultEOId getId() {
		return this.id;
	}

	public void setId(KpiResultEOId id) {
		this.id = id;
	}

	@Column(name = "kpi_sale_amount", nullable = false, precision = 12, scale = 0)
	public BigDecimal getKpiSaleAmount() {
		return this.kpiSaleAmount;
	}

	public void setKpiSaleAmount(BigDecimal kpiSaleAmount) {
		this.kpiSaleAmount = kpiSaleAmount;
	}

	@Column(name = "kpi_exchange", nullable = false, precision = 12, scale = 0)
	public BigDecimal getKpiExchange() {
		return this.kpiExchange;
	}

	public void setKpiExchange(BigDecimal kpiExchange) {
		this.kpiExchange = kpiExchange;
	}

	@Column(name = "kpi_man_buy", nullable = false, precision = 12, scale = 0)
	public BigDecimal getKpiManBuy() {
		return this.kpiManBuy;
	}

	public void setKpiManBuy(BigDecimal kpiManBuy) {
		this.kpiManBuy = kpiManBuy;
	}

	@Column(name = "kpi_first_response", nullable = false, precision = 12, scale = 0)
	public BigDecimal getKpiFirstResponse() {
		return this.kpiFirstResponse;
	}

	public void setKpiFirstResponse(BigDecimal kpiFirstResponse) {
		this.kpiFirstResponse = kpiFirstResponse;
	}

	@Column(name = "kpi_refund_rate", nullable = false, precision = 12, scale = 0)
	public BigDecimal getKpiRefundRate() {
		return this.kpiRefundRate;
	}

	public void setKpiRefundRate(BigDecimal kpiRefundRate) {
		this.kpiRefundRate = kpiRefundRate;
	}

	@Column(name = "kpi_good_eval", nullable = false, precision = 12, scale = 0)
	public BigDecimal getKpiGoodEval() {
		return this.kpiGoodEval;
	}

	public void setKpiGoodEval(BigDecimal kpiGoodEval) {
		this.kpiGoodEval = kpiGoodEval;
	}

	@Column(name = "kpi_total", nullable = false, precision = 12, scale = 0)
	public BigDecimal getKpiTotal() {
		return this.kpiTotal;
	}

	public void setKpiTotal(BigDecimal kpiTotal) {
		this.kpiTotal = kpiTotal;
	}

}