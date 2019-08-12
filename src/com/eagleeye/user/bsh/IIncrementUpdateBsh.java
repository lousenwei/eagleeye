package com.eagleeye.user.bsh;

import java.util.Date;
import java.util.List;

import com.eagleeye.user.eo.IncrementUpdateDetailEO;

public interface IIncrementUpdateBsh {

	public void saveIncrementUpdate(IncrementUpdateDetailEO incrementUpdate);

	public void saveIncrementUpdates(
			List<IncrementUpdateDetailEO> incrementUpdates);

	public IncrementUpdateDetailEO getIncrementUpdate(String managerId,
			Date updateTime);

	public List<IncrementUpdateDetailEO> getIncrementUpdates(String managerId);

	public Boolean hasTodayUpdate(String managerId);

	public List<IncrementUpdateDetailEO> getTopIncrementUpdatesByManagerid(
			String managerId, int num);
}
