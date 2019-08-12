package com.eagleeye.eservice.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.OnlineTimesOnDayEO;

public interface IOnlineTimeBlh {
	/**
	 * 导入对应客服在线时间
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 */
	public void loadOnlineTime(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) throws Exception;

	/**
	 * 找到对应客服在线时间List
	 * 
	 * @param serviceStaffId
	 * @return
	 */
	public List<OnlineTimesOnDayEO> findAllByStaffId(String serviceStaffId);
}
