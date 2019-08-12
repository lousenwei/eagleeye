package com.eagleeye.user.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.user.eo.TotalUpdateDetailEO;

public interface ITotalUpdateBlh {

	public void saveTotalUpdate(TotalUpdateDetailEO totalUpdate);

	public void saveTotalUpdates(List<TotalUpdateDetailEO> totalUpdates);

	public TotalUpdateDetailEO getTotalUpdate(String managerId, Date updateTime);

	public List<TotalUpdateDetailEO> getTotalUpdates(String managerId);

	public Boolean checkHasTotalUpdateByStaffIdAndManagerId(String staffId,
			String managerId);

	public Boolean hasTodayTotalUpdate(String managerId);

	public List<TotalUpdateDetailEO> getTotalUpdatesForSchedule(String status,
			int num) throws Exception;

	public List<TotalUpdateDetailEO> getUnfinishedTotalUpdates(String managerId);
}
