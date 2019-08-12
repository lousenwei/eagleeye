package com.eagleeye.user.bsh;

import java.util.Date;
import java.util.List;

import com.eagleeye.user.eo.TotalUpdateDetailEO;

public interface ITotalUpdateBsh {

	public void saveTotalUpdate(TotalUpdateDetailEO totalUpdate);

	public void saveTotalUpdates(List<TotalUpdateDetailEO> totalUpdates);

	public TotalUpdateDetailEO getTotalUpdate(String managerId, Date updateTime);

	public List<TotalUpdateDetailEO> getTotalUpdates(String managerId);

	public Boolean checkHasTotalUpdateByStaffIdAndManagerId(String staffId,
			String managerId);

	public Boolean hasTodayTotalUpdate(String managerId);

	public List<TotalUpdateDetailEO> getUnfinishedTotalUpdates(String managerId);
}
