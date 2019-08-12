package com.eagleeye.eservice.bsh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.ReplyStatOnDayEO;

public interface IReceiveNumBsh {
	/**
	 * 导入对应客服接待人数
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 */
	public void loadReceiveNum(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey);

	/**
	 * 找到对应客服接待对象List
	 * 
	 * @param serviceStaffId
	 * @return
	 */
	public List<ReplyStatOnDayEO> findAllByStaffId(String serviceStaffId);
}
