package com.eagleeye.user.bsh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.user.blh.ITotalUpdateBlh;
import com.eagleeye.user.bsh.ITotalUpdateBsh;
import com.eagleeye.user.eo.TotalUpdateDetailEO;

public class TotalUpdateBshImpl implements ITotalUpdateBsh {
	ITotalUpdateBlh totalUpdateBlh;

	@Override
	public void saveTotalUpdate(TotalUpdateDetailEO totalUpdate) {
		// TODO Auto-generated method stub
		totalUpdateBlh.saveTotalUpdate(totalUpdate);
	}

	@Override
	public void saveTotalUpdates(List<TotalUpdateDetailEO> totalUpdates) {
		// TODO Auto-generated method stub
		totalUpdateBlh.saveTotalUpdates(totalUpdates);
	}

	public Boolean checkHasTotalUpdateByStaffIdAndManagerId(String staffId,
			String managerId) {
		return totalUpdateBlh.checkHasTotalUpdateByStaffIdAndManagerId(staffId,
				managerId);
	}

	@Override
	public TotalUpdateDetailEO getTotalUpdate(String managerId, Date updateTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TotalUpdateDetailEO> getTotalUpdates(String managerId) {
		// TODO Auto-generated method stub
		return totalUpdateBlh.getTotalUpdates(managerId);
	}

	public ITotalUpdateBlh getTotalUpdateBlh() {
		return totalUpdateBlh;
	}

	public void setTotalUpdateBlh(ITotalUpdateBlh totalUpdateBlh) {
		this.totalUpdateBlh = totalUpdateBlh;
	}

	@Override
	public Boolean hasTodayTotalUpdate(String managerId) {
		// TODO Auto-generated method stub
		return totalUpdateBlh.hasTodayTotalUpdate(managerId);
	}

	public List<TotalUpdateDetailEO> getUnfinishedTotalUpdates(String managerId) {
		return totalUpdateBlh.getUnfinishedTotalUpdates(managerId);
	}

}
