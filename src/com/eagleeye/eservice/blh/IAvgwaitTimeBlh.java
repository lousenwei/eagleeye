package com.eagleeye.eservice.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.WaitingTimesOnDayEO;

public interface IAvgwaitTimeBlh {
	/**
	 * 导入平均等待时间
	 * 
	 * @param serviceStaffId
	 *            客服id
	 */
	public void loadAvgwaitTime(String serviceStaffId, Date startTime,
			Date endTime,String sessionKey) throws Exception;

	/**
	 * 取得所有平均等待时间
	 * 
	 * @param serviceStaffId
	 */
	public List<WaitingTimesOnDayEO> findAllByStaffId(String serviceStaffId);
}
