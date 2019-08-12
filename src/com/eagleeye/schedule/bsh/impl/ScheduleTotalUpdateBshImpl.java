package com.eagleeye.schedule.bsh.impl;

import java.util.List;

import com.eagleeye.schedule.bsh.IScheduleBsh;
import com.eagleeye.schedule.constant.ScheduleConstants;
import com.eagleeye.user.blh.ITotalUpdateBlh;
import com.eagleeye.user.eo.TotalUpdateDetailEO;

public class ScheduleTotalUpdateBshImpl implements IScheduleBsh {

	ITotalUpdateBlh totalUpdateBlh;

	@Override
	public List<TotalUpdateDetailEO> selectedTasks() throws Exception {
		// TODO Auto-generated method stub
		return totalUpdateBlh.getTotalUpdatesForSchedule(
				ScheduleConstants.SCHEDULE_INIT_STATUS,
				ScheduleConstants.SCHEDULE_TOTALUPDATE_NUM);
	}

	@Override
	public void modifyTaskStatus(List<?> totalUpdates, String status) {
		// TODO Auto-generated method stub
		if (totalUpdates != null && !totalUpdates.isEmpty()) {
			for (int i = 0; i < totalUpdates.size(); i++) {
				if (totalUpdates.get(i) instanceof TotalUpdateDetailEO)
					((TotalUpdateDetailEO) totalUpdates.get(i))
							.setStatus(status);
			}
			totalUpdateBlh
					.saveTotalUpdates((List<TotalUpdateDetailEO>) totalUpdates);
		}
	}

	public ITotalUpdateBlh getTotalUpdateBlh() {
		return totalUpdateBlh;
	}

	public void setTotalUpdateBlh(ITotalUpdateBlh totalUpdateBlh) {
		this.totalUpdateBlh = totalUpdateBlh;
	}

}
