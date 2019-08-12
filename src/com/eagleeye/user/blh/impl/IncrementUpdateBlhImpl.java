package com.eagleeye.user.blh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.user.blh.IIncrementUpdateBlh;
import com.eagleeye.user.dao.IncrementUpdateDAO;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;

public class IncrementUpdateBlhImpl implements IIncrementUpdateBlh {

	IncrementUpdateDAO incrementUpdateDao;

	@Override
	public void saveIncrementUpdate(IncrementUpdateDetailEO incrementUpdate) {
		// TODO Auto-generated method stub
		incrementUpdateDao.saveOrUpdate(incrementUpdate);
	}

	@Override
	public void saveIncrementUpdates(
			List<IncrementUpdateDetailEO> incrementUpdates) {
		// TODO Auto-generated method stub
		incrementUpdateDao.saveAllObject(incrementUpdates);
	}

	public void saveOrUpdateIncrementUpdates(
			List<IncrementUpdateDetailEO> incrementUpdates) {
		incrementUpdateDao.saveOrUpdateBatch(incrementUpdates);
	}

	@Override
	public IncrementUpdateDetailEO getIncrementUpdate(String managerId,
			Date updateTime) {
		// TODO Auto-generated method stub
		return incrementUpdateDao.getIncrementUpdate(managerId, updateTime);
	}

	@Override
	public List<IncrementUpdateDetailEO> getIncrementUpdates(String managerId) {
		// TODO Auto-generated method stub
		return incrementUpdateDao.getIncrementUpdates(managerId);
	}

	public Boolean hasTodayUpdate(String managerId) {
		return incrementUpdateDao.hasTodayUpdate(managerId);
	}

	public List<IncrementUpdateDetailEO> getIncrementUpdatesForSchedule(
			String status, int num) throws Exception {
		return incrementUpdateDao.getIncrementUpdatesForSchedule(status, num);
	}

	public List<IncrementUpdateDetailEO> getTopIncrementUpdatesByManagerid(
			String managerId, int num) {
		return incrementUpdateDao.getTopIncrementUpdatesByManagerid(managerId,
				num);
	}

	public IncrementUpdateDAO getIncrementUpdateDao() {
		return incrementUpdateDao;
	}

	public void setIncrementUpdateDao(IncrementUpdateDAO incrementUpdateDao) {
		this.incrementUpdateDao = incrementUpdateDao;
	}

}
