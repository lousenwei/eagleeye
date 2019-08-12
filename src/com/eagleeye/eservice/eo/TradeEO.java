package com.eagleeye.eservice.eo;

import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.taobao.api.security.SecurityClient;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Trade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trade", catalog = "eagleeye")
public class TradeEO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7921105111741987271L;
	private Long tid;
	private Date endTime;
	private String shippingType;
	private String status;
	private Date modified;
	private String sellerNick;
	private String buyerNick;
	private String title;
	private Date created;
	private Long num;
	private Float payment;
	private Float discountFee;
	private Float adjustFee;
	private Boolean sellerRate;
	private Boolean buyerRate;
	private Date payTime;
	private Float totalFee;
	private Float postFee;
	private Float commisionFee;
	private Float receivedPayment;
	private Float codFee;
	private Long numIid;
	private Set<OrderEO> orders = new HashSet<OrderEO>(0);
	private String tradeOwner;
	private Date payTimeAdd;
	//2014-4-30, flag
	private Long sellerFlag;

	// Constructors

	/** default constructor */
	public TradeEO() {
	}

	/** minimal constructor */
	public TradeEO(Long tid, Date endTime, String shippingType, String status,
			Date modified, String sellerNick, String buyerNick, String title,
			Date created, Long num, Float payment, Float discountFee,
			Float adjustFee, Boolean sellerRate, Boolean buyerRate,
			Date payTime, Float totalFee, Float postFee, Float commisionFee,
			Float receivedPayment, Float codFee, Long numIid) {
		this.tid = tid;
		this.endTime = endTime;
		this.shippingType = shippingType;
		this.status = status;
		this.modified = modified;
		this.sellerNick = sellerNick;
		this.buyerNick = buyerNick;
		this.title = title;
		this.created = created;
		this.num = num;
		this.payment = payment;
		this.discountFee = discountFee;
		this.adjustFee = adjustFee;
		this.sellerRate = sellerRate;
		this.buyerRate = buyerRate;
		this.payTime = payTime;
		this.totalFee = totalFee;
		this.postFee = postFee;
		this.commisionFee = commisionFee;
		this.receivedPayment = receivedPayment;
		this.codFee = codFee;
		this.numIid = numIid;
	}

	/** full constructor */
	public TradeEO(Long tid, Date endTime, String shippingType, String status,
			Date modified, String sellerNick, String buyerNick, String title,
			Date created, Long num, Float payment, Float discountFee,
			Float adjustFee, Boolean sellerRate, Boolean buyerRate,
			Date payTime, Float totalFee, Float postFee, Float commisionFee,
			Float receivedPayment, Float codFee, Long numIid,
			Set<OrderEO> orders) {
		this.tid = tid;
		this.endTime = endTime;
		this.shippingType = shippingType;
		this.status = status;
		this.modified = modified;
		this.sellerNick = sellerNick;
		this.buyerNick = buyerNick;
		this.title = title;
		this.created = created;
		this.num = num;
		this.payment = payment;
		this.discountFee = discountFee;
		this.adjustFee = adjustFee;
		this.sellerRate = sellerRate;
		this.buyerRate = buyerRate;
		this.payTime = payTime;
		this.totalFee = totalFee;
		this.postFee = postFee;
		this.commisionFee = commisionFee;
		this.receivedPayment = receivedPayment;
		this.codFee = codFee;
		this.numIid = numIid;
		this.orders = orders;
	}

	// Property accessors
	@Id
	@Column(name = "tid", unique = true, nullable = false)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_time", nullable = true, length = 0)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "shipping_type", nullable = true, length = 10)
	public String getShippingType() {
		return this.shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	@Column(name = "trade_status", nullable = true, length = 50)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "modified", nullable = true, length = 0)
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Column(name = "seller_nick", nullable = true, length = 100)
	public String getSellerNick() {
		return this.sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	@Column(name = "buyer_nick", nullable = true)
	public String getBuyerNick() {
		return this.buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	@Column(name = "title", nullable = true)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = true, length = 0)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "num", nullable = true)
	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	@Column(name = "payment", nullable = true, precision = 12, scale = 0)
	public Float getPayment() {
		return this.payment;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
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

	@Column(name = "seller_rate", nullable = true)
	public Boolean getSellerRate() {
		return this.sellerRate;
	}

	public void setSellerRate(Boolean sellerRate) {
		this.sellerRate = sellerRate;
	}

	@Column(name = "buyer_rate", nullable = true)
	public Boolean getBuyerRate() {
		return this.buyerRate;
	}

	public void setBuyerRate(Boolean buyerRate) {
		this.buyerRate = buyerRate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pay_time", nullable = true, length = 0)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "total_fee", nullable = true, precision = 12, scale = 0)
	public Float getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	@Column(name = "post_fee", nullable = true, precision = 12, scale = 0)
	public Float getPostFee() {
		return this.postFee;
	}

	public void setPostFee(Float postFee) {
		this.postFee = postFee;
	}

	@Column(name = "commision_fee", nullable = true, precision = 12, scale = 0)
	public Float getCommisionFee() {
		return this.commisionFee;
	}

	public void setCommisionFee(Float commisionFee) {
		this.commisionFee = commisionFee;
	}

	@Column(name = "received_payment", nullable = true, precision = 12, scale = 0)
	public Float getReceivedPayment() {
		return this.receivedPayment;
	}

	public void setReceivedPayment(Float receivedPayment) {
		this.receivedPayment = receivedPayment;
	}

	@Column(name = "cod_fee", nullable = true, precision = 12, scale = 0)
	public Float getCodFee() {
		return this.codFee;
	}

	public void setCodFee(Float codFee) {
		this.codFee = codFee;
	}

	@Column(name = "num_iid", nullable = true)
	public Long getNumIid() {
		return this.numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trade")
	public Set<OrderEO> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<OrderEO> orders) {
		this.orders = orders;
	}

	@Column(name = "trade_owner", nullable = true)
	public String getTradeOwner() {
		return tradeOwner;
	}

	public void setTradeOwner(String tradeOwner) {
		this.tradeOwner = tradeOwner;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pay_time_add", nullable = true, length = 0)
	public Date getPayTimeAdd() {
		return payTimeAdd;
	}

	public void setPayTimeAdd(Date payTimeAdd) {
		this.payTimeAdd = payTimeAdd;
	}

	@Column(name = "seller_flag", nullable = true)
	public Long getSellerFlag() {
		return sellerFlag;
	}

	public void setSellerFlag(Long sellerFlag) {
		this.sellerFlag = sellerFlag;
	}
}