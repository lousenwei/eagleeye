package com.eagleeye.eservice.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.UpdatelogEO;

public interface IUpdateLogBlh {

	/**
	 * 保存更新日志list
	 * 
	 * @param updateLogs
	 */
	public void saveUpdateLogs(List<UpdatelogEO> updateLogs);

	/**
	 * 保存单条更新日志
	 * 
	 * @param updateLog
	 */
	public void saveUpdateLog(UpdatelogEO updateLog);

	/**
	 * 取得manager下所有更新日志
	 * 
	 * @param managerId
	 * @return
	 */
	public List<UpdatelogEO> getUpdateLogs(String managerId);

	/**
	 * 取得manager下单项更新日志
	 * 
	 * @param managerId
	 * @param item
	 * @return
	 */
	public UpdatelogEO getUpdateLog(String managerId, String item);

	/**
	 * 生成updatelog对象
	 * 
	 * @param managerId
	 * @param item
	 * @param updateTime
	 * @return
	 */
	public UpdatelogEO doUpdateLog(String managerId, String item,
			Date updateTime);
}
