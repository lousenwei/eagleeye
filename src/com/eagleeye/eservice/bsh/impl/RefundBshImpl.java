package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.blh.IRefundBlh;
import com.eagleeye.eservice.bsh.IRefundBsh;
import com.eagleeye.eservice.eo.RefundEO;

public class RefundBshImpl implements IRefundBsh {
	IRefundBlh refundBlh;
	private Logger log = Logger.getLogger(RefundBshImpl.class);

	@Override
	public void loadRefund(String serviceStaffId, Date startTime, Date endTime,
			String sessionKey) {
		// TODO Auto-generated method stub
		try {
			refundBlh
					.loadRefund(serviceStaffId, startTime, endTime, sessionKey);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public List<RefundEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefundEO> findALlByTidorOid(String tid, String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRefundBlh getRefundBlh() {
		return refundBlh;
	}

	public void setRefundBlh(IRefundBlh refundBlh) {
		this.refundBlh = refundBlh;
	}

}
