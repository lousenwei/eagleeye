package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.blh.IAvgwaitTimeBlh;
import com.eagleeye.eservice.bsh.IAvgwaitTimeBsh;
import com.eagleeye.eservice.eo.WaitingTimesOnDayEO;

public class AvgwaitTimeBshImpl implements IAvgwaitTimeBsh {
	private Logger log = Logger.getLogger(AvgwaitTimeBshImpl.class);
	IAvgwaitTimeBlh avgwaitTimeBlh;

	@Override
	public void loadAvgwaitTime(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) {
		// TODO Auto-generated method stub
		try {
			avgwaitTimeBlh.loadAvgwaitTime(serviceStaffId, startTime, endTime,
					sessionKey);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public List<WaitingTimesOnDayEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return avgwaitTimeBlh.findAllByStaffId(serviceStaffId);
	}

	public IAvgwaitTimeBlh getAvgwaitTimeBlh() {
		return avgwaitTimeBlh;
	}

	public void setAvgwaitTimeBlh(IAvgwaitTimeBlh avgwaitTimeBlh) {
		this.avgwaitTimeBlh = avgwaitTimeBlh;
	}

}
