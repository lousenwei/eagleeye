package com.eagleeye.eservice.eo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orders", catalog = "eagleeye")
public class OrderEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1447560067791350920L;
	private OrderEOId id;
	private TradeEO trade;
	private Float totalFee;
	private Float discountFee;
	private Float adjustFee;
	private Float payment;
	private String status;
	private Long refundId;
	private String itemMealName;
	private Long num;
	private String title;
	private Float price;
	private String picPath;
	private String refundStatus;
	private Boolean buyerRate;
	private Boolean sellerRate;
	private String sellerType;
	private Long numIid;

	// Constructors

	/** default constructor */
	public OrderEO() {
	}

	/** full constructor */
	public OrderEO(OrderEOId id, TradeEO trade, Float totalFee,
			Float discountFee, Float adjustFee, Float payment, String status,
			Long refundId, String itemMealName, Long num, String title,
			Float price, String picPath, String refundStatus,
			Boolean buyerRate, Boolean sellerRate, String sellerType,
			Long numIid) {
		this.id = id;
		this.trade = trade;
		this.totalFee = totalFee;
		this.discountFee = discountFee;
		this.adjustFee = adjustFee;
		this.payment = payment;
		this.status = status;
		this.refundId = refundId;
		this.itemMealName = itemMealName;
		this.num = num;
		this.title = title;
		this.price = price;
		this.picPath = picPath;
		this.refundStatus = refundStatus;
		this.buyerRate = buyerRate;
		this.sellerRate = sellerRate;
		this.sellerType = sellerType;
		this.numIid = numIid;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "oid", column = @Column(name = "oid", nullable = false)),
			@AttributeOverride(name = "tid", column = @Column(name = "tid", nullable = false)) })
	public OrderEOId getId() {
		return this.id;
	}

	public void setId(OrderEOId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tid", nullable = false, insertable = false, updatable = false)
	public TradeEO getTrade() {
		return this.trade;
	}

	public void setTrade(TradeEO trade) {
		this.trade = trade;
	}

	@Column(name = "total_fee", nullable = true, precision = 12, scale = 0)
	public Float getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	@Column(name = "discount_fee", nullable = true, precision = 12, scale = 0)
	public Float getDiscountFee() {
		return this.discountFee;
	}

	public void setDiscountFee(Float discountFee) {
		this.discountFee = discountFee;
	}

	@Column(name = "adjust_fee", nullable = true, precision = 12, scale = 0)
	public Float getAdjustFee() {
		return this.adjustFee;
	}

	public void setAdjustFee(Float adjustFee) {
		this.adjustFee = adjustFee;
	}

	@Column(name = "payment", nullable = true, precision = 12, scale = 0)
	public Float getPayment() {
		return this.payment;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
	}

	@Column(name = "order_status", nullable = true, length = 50)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "refund_id", nullable = true)
	public Long getRefundId() {
		return this.refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	@Column(name = "item_meal_name", nullable = true)
	public String getItemMealName() {
		return this.itemMealName;
	}

	public void setItemMealName(String itemMealName) {
		this.itemMealName = itemMealName;
	}

	@Column(name = "num", nullable = true)
	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	@Column(name = "title", nullable = true)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "price", nullable = true, precision = 12, scale = 0)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "pic_path", nullable = true)
	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Column(name = "refund_status", nullable = true, length = 50)
	public String getRefundStatus() {
		return this.refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	@Column(name = "buyer_rate", nullable = true)
	public Boolean getBuyerRate() {
		return this.buyerRate;
	}

	public void setBuyerRate(Boolean buyerRate) {
		this.buyerRate = buyerRate;
	}

	@Column(name = "seller_rate", nullable = true)
	public Boolean getSellerRate() {
		return this.sellerRate;
	}

	public void setSellerRate(Boolean sellerRate) {
		this.sellerRate = sellerRate;
	}

	@Column(name = "seller_type", nullable = true, length = 2)
	public String getSellerType() {
		return this.sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	@Column(name = "num_iid", nullable = true)
	public Long getNumIid() {
		return this.numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}
}