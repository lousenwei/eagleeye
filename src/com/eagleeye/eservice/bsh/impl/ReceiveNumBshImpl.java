package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.blh.IReceiveNumBlh;
import com.eagleeye.eservice.bsh.IReceiveNumBsh;
import com.eagleeye.eservice.eo.ReplyStatOnDayEO;

public class ReceiveNumBshImpl implements IReceiveNumBsh {
	IReceiveNumBlh receiveNumBlh;
	private Logger log = Logger.getLogger(ReceiveNumBshImpl.class);

	@Override
	public void loadReceiveNum(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) {
		// TODO Auto-generated method stub
		try {
			this.receiveNumBlh.loadReceiveNum(serviceStaffId, startTime,
					endTime, sessionKey);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public List<ReplyStatOnDayEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return receiveNumBlh.findAllByStaffId(serviceStaffId);
	}

	public IReceiveNumBlh getReceiveNumBlh() {
		return receiveNumBlh;
	}

	public void setReceiveNumBlh(IReceiveNumBlh receiveNumBlh) {
		this.receiveNumBlh = receiveNumBlh;
	}

}
