package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.blh.INoreplyNumBlh;
import com.eagleeye.eservice.bsh.INoreplyNumBsh;
import com.eagleeye.eservice.eo.NonReplyStatOnDayEO;

public class NoreplyNumBshImpl implements INoreplyNumBsh {
	INoreplyNumBlh noreplyNumBlh;
	private Logger log = Logger.getLogger(NoreplyNumBshImpl.class);

	@Override
	public void loadNoreplyNum(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) {
		// TODO Auto-generated method stub
		try {
			noreplyNumBlh.loadNoreplyNum(serviceStaffId, startTime, endTime,
					sessionKey);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public List<NonReplyStatOnDayEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return noreplyNumBlh.findAllByStaffId(serviceStaffId);
	}

	public INoreplyNumBlh getNoreplyNumBlh() {
		return noreplyNumBlh;
	}

	public void setNoreplyNumBlh(INoreplyNumBlh noreplyNumBlh) {
		this.noreplyNumBlh = noreplyNumBlh;
	}

}
