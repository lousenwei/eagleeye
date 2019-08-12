package com.eagleeye.eservice.bsh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.NonReplyStatOnDayEO;

public interface INoreplyNumBsh {
	/**
	 * 导入客服未回复对象信息
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 */
	public void loadNoreplyNum(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey);

	/**
	 * 找到对应客服未回复对象List
	 * 
	 * @param serviceStaffId
	 * @return
	 */
	public List<NonReplyStatOnDayEO> findAllByStaffId(String serviceStaffId);
}
