package com.eagleeye.user.blh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.user.blh.ITotalUpdateBlh;
import com.eagleeye.user.dao.TotalUpdateDAO;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;
import com.eagleeye.user.eo.TotalUpdateDetailEO;

public class TotalUpdateBlhImpl implements ITotalUpdateBlh {

	TotalUpdateDAO totalUpdateDao;

	@Override
	public void saveTotalUpdate(TotalUpdateDetailEO totalUpdate) {
		// TODO Auto-generated method stub
		totalUpdateDao.save(totalUpdate);
	}

	@Override
	public void saveTotalUpdates(List<TotalUpdateDetailEO> totalUpdates) {
		// TODO Auto-generated method stub
		totalUpdateDao.saveOrUpdateBatch(totalUpdates);
	}

	public Boolean checkHasTotalUpdateByStaffIdAndManagerId(String staffId,
			String managerId) {
		return totalUpdateDao.hasData(staffId, managerId);
	}

	@Override
	public TotalUpdateDetailEO getTotalUpdate(String managerId, Date updateTime) {
		// TODO Auto-generated method stub
		return totalUpdateDao.getTotalUpdate(managerId, updateTime);
	}

	@Override
	public List<TotalUpdateDetailEO> getTotalUpdates(String managerId) {
		// TODO Auto-generated method stub

		return totalUpdateDao.getTotalUpdates(managerId);
	}

	public List<TotalUpdateDetailEO> getUnfinishedTotalUpdates(String managerId) {
		return totalUpdateDao.getUnfinishedTotalUpdates(managerId);
	}

	public Boolean hasTodayTotalUpdate(String managerId) {
		return totalUpdateDao.hasTodayTotalUpdate(managerId);
	}

	public List<TotalUpdateDetailEO> getTotalUpdatesForSchedule(String status,
			int num) throws Exception {
		return totalUpdateDao.getTotalUpdatesForSchedule(status, num);
	}

	public TotalUpdateDAO getTotalUpdateDao() {
		return totalUpdateDao;
	}

	public void setTotalUpdateDao(TotalUpdateDAO totalUpdateDao) {
		this.totalUpdateDao = totalUpdateDao;
	}

}
