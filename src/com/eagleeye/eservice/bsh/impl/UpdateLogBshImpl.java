package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.blh.IUpdateLogBlh;
import com.eagleeye.eservice.bsh.IUpdateLogBsh;
import com.eagleeye.eservice.eo.UpdatelogEO;

public class UpdateLogBshImpl implements IUpdateLogBsh {

	IUpdateLogBlh updateLogBlh;

	@Override
	public void saveUpdateLogs(List<UpdatelogEO> updateLogs) {
		// TODO Auto-generated method stub
		updateLogBlh.saveUpdateLogs(updateLogs);
	}

	@Override
	public void saveUpdateLog(UpdatelogEO updateLog) {
		// TODO Auto-generated method stub
		updateLogBlh.saveUpdateLog(updateLog);
	}

	@Override
	public List<UpdatelogEO> getUpdateLogs(String managerId) {
		// TODO Auto-generated method stub
		return updateLogBlh.getUpdateLogs(managerId);
	}

	@Override
	public UpdatelogEO getUpdateLog(String managerId, String item) {
		// TODO Auto-generated method stub
		return updateLogBlh.getUpdateLog(managerId, item);
	}

	@Override
	public UpdatelogEO doUpdateLog(String managerId, String item,
			Date updateTime) {
		// TODO Auto-generated method stub
		return updateLogBlh.doUpdateLog(managerId, item, updateTime);
	}

	public IUpdateLogBlh getUpdateLogBlh() {
		return updateLogBlh;
	}

	public void setUpdateLogBlh(IUpdateLogBlh updateLogBlh) {
		this.updateLogBlh = updateLogBlh;
	}

}
