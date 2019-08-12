package com.eagleeye.user.bsh.impl;

import java.util.Date;
import java.util.List;

import com.eagleeye.user.blh.IIncrementUpdateBlh;
import com.eagleeye.user.bsh.IIncrementUpdateBsh;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;

public class IncrementUpdateBshImpl implements IIncrementUpdateBsh {
	IIncrementUpdateBlh incrementUpdateBlh;

	@Override
	public void saveIncrementUpdate(IncrementUpdateDetailEO incrementUpdate) {
		// TODO Auto-generated method stub
		incrementUpdateBlh.saveIncrementUpdate(incrementUpdate);
	}

	@Override
	public void saveIncrementUpdates(
			List<IncrementUpdateDetailEO> incrementUpdates) {
		// TODO Auto-generated method stub
		incrementUpdateBlh.saveIncrementUpdates(incrementUpdates);
	}

	@Override
	public IncrementUpdateDetailEO getIncrementUpdate(String managerId,
			Date updateTime) {
		// TODO Auto-generated method stub
		return incrementUpdateBlh.getIncrementUpdate(managerId, updateTime);
	}

	@Override
	public List<IncrementUpdateDetailEO> getIncrementUpdates(String managerId) {
		// TODO Auto-generated method stub
		return incrementUpdateBlh.getIncrementUpdates(managerId);
	}

	@Override
	public Boolean hasTodayUpdate(String managerId) {
		// TODO Auto-generated method stub
		return incrementUpdateBlh.hasTodayUpdate(managerId);
	}

	public List<IncrementUpdateDetailEO> getTopIncrementUpdatesByManagerid(
			String managerId, int num) {
		return incrementUpdateBlh.getTopIncrementUpdatesByManagerid(managerId,
				num);
	}

	public List<IncrementUpdateDetailEO> getIncrementUpdatesForSchedule(
			String status, int num) throws Exception {
		return incrementUpdateBlh.getIncrementUpdatesForSchedule(status, num);
	}

	public IIncrementUpdateBlh getIncrementUpdateBlh() {
		return incrementUpdateBlh;
	}

	public void setIncrementUpdateBlh(IIncrementUpdateBlh incrementUpdateBlh) {
		this.incrementUpdateBlh = incrementUpdateBlh;
	}

}
