package com.eagleeye.statistics.mo.rank;

import java.math.BigDecimal;

public class RankGoodsSaleAmountMO {
	// 商品标题
	private String title;
	// 商品ID
	private Long numIid;
	// 销售总额
	private BigDecimal totalAmount;
	// 图片地址
	private String picPath;

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public RankGoodsSaleAmountMO(String title, Long numIid,
			BigDecimal totalAmount) {
		super();
		this.title = title;
		this.numIid = numIid;
		this.totalAmount = totalAmount;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public RankGoodsSaleAmountMO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
