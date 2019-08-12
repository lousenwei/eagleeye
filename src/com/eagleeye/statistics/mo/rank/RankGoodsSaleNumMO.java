package com.eagleeye.statistics.mo.rank;

import java.math.BigDecimal;

public class RankGoodsSaleNumMO {
	// 商品标题
	private String title;
	// 商品代码
	private Long numIid;
	// 销售总量
	private BigDecimal totalNum;
	// 图片地址
	private String picPath;

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public RankGoodsSaleNumMO(String title, Long numIid, BigDecimal totalNum) {
		super();
		this.title = title;
		this.numIid = numIid;
		this.totalNum = totalNum;
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

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	public RankGoodsSaleNumMO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
