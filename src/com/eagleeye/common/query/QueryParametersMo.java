package com.eagleeye.common.query;

import java.util.Date;

import com.eagleeye.common.util.DateUtil;

public class QueryParametersMo {
	public QueryParametersMo() {
		super();
		Date endDate = DateUtil.getSimpleDate(new Date());
		Date startDate = DateUtil.getActualMinimumDate(endDate);
		this.setStartTime(startDate);
		this.setEndTime(endDate);
	}
	String managerId;
	
	String staffId;

	Date startTime;

	Date endTime;

	Boolean isTotal;

	String shopId;

	String sessionKey;

	Date betweenTime;
	
	
	public Date getBetweenTime() {
		return betweenTime;
	}

	public void setBetweenTime(Date betweenTime) {
		this.betweenTime = betweenTime;
	}

	public Boolean getIsTotal() {
		return isTotal;
	}

	public void setIsTotal(Boolean isTotal) {
		this.isTotal = isTotal;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
