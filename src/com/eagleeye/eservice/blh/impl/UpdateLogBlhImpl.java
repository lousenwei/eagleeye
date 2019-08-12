package com.eagleeye.eservice.blh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.blh.IUpdateLogBlh;
import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.eservice.dao.impl.UpdateLogDAO;
import com.eagleeye.eservice.eo.UpdatelogEO;
import com.eagleeye.eservice.eo.UpdatelogEOId;

public class UpdateLogBlhImpl implements IUpdateLogBlh {
	UpdateLogDAO updateLogDao;

	@Override
	public void saveUpdateLogs(List<UpdatelogEO> updateLogs) {
		// TODO Auto-generated method stub
		updateLogDao.saveOrUpdateBatch(updateLogs);
	}

	@Override
	public void saveUpdateLog(UpdatelogEO updateLog) {
		// TODO Auto-generated method stub
		updateLogDao.saveOrUpdate(updateLog);
	}

	@Override
	public List<UpdatelogEO> getUpdateLogs(String managerId) {
		// TODO Auto-generated method stub
		return updateLogDao.getUpdateLogs(managerId);
	}

	@Override
	public UpdatelogEO getUpdateLog(String managerId, String item) {
		// TODO Auto-generated method stub
		return updateLogDao.getUpdateLog(managerId, item);
	}

	public UpdatelogEO doUpdateLog(String managerId, String item,
			Date updateTime) {
		UpdatelogEOId id = new UpdatelogEOId(managerId, item);
		UpdatelogEO log = new UpdatelogEO(id, updateTime);
		//2017-3-17，添加优先级
		int pri=99;
		if(EServiceConstants.UPDATE_LOG_PRIORITY_MAP.get(item)!=null) {
			pri=EServiceConstants.UPDATE_LOG_PRIORITY_MAP.get(item);
		}
		log.setPriority(pri);
		return log;
	}



	public UpdateLogDAO getUpdateLogDao() {
		return updateLogDao;
	}

	public void setUpdateLogDao(UpdateLogDAO updateLogDao) {
		this.updateLogDao = updateLogDao;
	}

}
