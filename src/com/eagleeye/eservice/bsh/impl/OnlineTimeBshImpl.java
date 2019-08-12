package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.blh.IOnlineTimeBlh;
import com.eagleeye.eservice.bsh.IOnlineTimeBsh;
import com.eagleeye.eservice.eo.OnlineTimesOnDayEO;

public class OnlineTimeBshImpl implements IOnlineTimeBsh {
	IOnlineTimeBlh onlineTimeBlh;
	private Logger log = Logger.getLogger(OnlineTimeBshImpl.class);

	@Override
	public void loadOnlineTime(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) {
		// TODO Auto-generated method stub
		try {
			onlineTimeBlh.loadOnlineTime(serviceStaffId, startTime, endTime,
					sessionKey);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public List<OnlineTimesOnDayEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return onlineTimeBlh.findAllByStaffId(serviceStaffId);
	}

	public IOnlineTimeBlh getOnlineTimeBlh() {
		return onlineTimeBlh;
	}

	public void setOnlineTimeBlh(IOnlineTimeBlh onlineTimeBlh) {
		this.onlineTimeBlh = onlineTimeBlh;
	}

}
