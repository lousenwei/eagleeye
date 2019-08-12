package com.eagleeye.eservice.mo;

import java.util.Date;

public class ChatPeerQueryParametersMO {

	String field;

	Date start;

	Date end;

	String managerId;

	String buyNick;

	String orderBy;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getBuyNick() {
		return buyNick;
	}

	public void setBuyNick(String buyNick) {
		this.buyNick = buyNick;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
