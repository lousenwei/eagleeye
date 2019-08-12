package com.eagleeye.eservice.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.NonReplyStatOnDayEO;

public interface INoreplyNumBlh {
	/**
	 * 导入对应客服未回复人数
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 */
	public void loadNoreplyNum(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) throws Exception;

	/**
	 * 找到对应客服未回复对象List
	 * 
	 * @param serviceStaffId
	 * @return
	 */
	public List<NonReplyStatOnDayEO> findAllByStaffId(String serviceStaffId);
}
