package com.eagleeye.eservice.mo;

import java.util.Date;
import java.util.List;

public class TradeQueryParametersMo {

	String staffId;
	Date start;
	Date end;
	Date modifyStart;
	Date modifyEnd;
	List<String> status;
	Boolean hasTradeOwner;
	String tradeOwner;
	Boolean hasPayTime = true;
	Long tid;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public Boolean getHasTradeOwner() {
		return hasTradeOwner;
	}

	public void setHasTradeOwner(Boolean hasTradeOwner) {
		this.hasTradeOwner = hasTradeOwner;
	}

	public String getTradeOwner() {
		return tradeOwner;
	}

	public void setTradeOwner(String tradeOwner) {
		this.tradeOwner = tradeOwner;
	}

	public Boolean getHasPayTime() {
		return hasPayTime;
	}

	public void setHasPayTime(Boolean hasPayTime) {
		this.hasPayTime = hasPayTime;
	}

	public Date getModifyStart() {
		return modifyStart;
	}

	public void setModifyStart(Date modifyStart) {
		this.modifyStart = modifyStart;
	}

	public Date getModifyEnd() {
		return modifyEnd;
	}

	public void setModifyEnd(Date modifyEnd) {
		this.modifyEnd = modifyEnd;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}
}
