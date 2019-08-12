package com.eagleeye.statistics.mo.rank;

import java.math.BigDecimal;

public class RankGoodsRefundAmountMO {
	// 商品标题
	private String title;
	// 商品ID
	private Long numIid;
	// 退款总量
	private BigDecimal totalRefundAmount;
	// 图片地址
	private String picPath;

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public RankGoodsRefundAmountMO(String title, Long numIid,
			BigDecimal totalRefundAmount) {
		super();
		this.title = title;
		this.numIid = numIid;
		this.totalRefundAmount = totalRefundAmount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public BigDecimal getTotalRefundAmount() {
		return totalRefundAmount;
	}

	public void setTotalRefundAmount(BigDecimal totalRefundAmount) {
		this.totalRefundAmount = totalRefundAmount;
	}

	public RankGoodsRefundAmountMO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
