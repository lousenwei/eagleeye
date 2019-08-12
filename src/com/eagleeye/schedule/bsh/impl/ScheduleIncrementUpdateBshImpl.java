package com.eagleeye.schedule.bsh.impl;

import java.util.List;

import com.eagleeye.schedule.bsh.IScheduleBsh;
import com.eagleeye.schedule.constant.ScheduleConstants;
import com.eagleeye.user.blh.IIncrementUpdateBlh;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;

public class ScheduleIncrementUpdateBshImpl implements IScheduleBsh {

	IIncrementUpdateBlh incrementUpdateBlh;

	@Override
	public List<IncrementUpdateDetailEO> selectedTasks() throws Exception {
		// TODO Auto-generated method stub
		return incrementUpdateBlh.getIncrementUpdatesForSchedule(
				ScheduleConstants.SCHEDULE_INIT_STATUS,
				ScheduleConstants.SCHEDULE_INCREMENTUPDATE_NUM);
	}

	@Override
	public void modifyTaskStatus(List<?> incrementUpdates, String status) {
		// TODO Auto-generated method stub
		if (incrementUpdates != null && !incrementUpdates.isEmpty()) {
			for (int i = 0; i < incrementUpdates.size(); i++) {
				if (incrementUpdates.get(i) instanceof IncrementUpdateDetailEO)
					((IncrementUpdateDetailEO) incrementUpdates.get(i))
							.setStatus(status);
			}
			incrementUpdateBlh
					.saveOrUpdateIncrementUpdates((List<IncrementUpdateDetailEO>) incrementUpdates);
		}
	}

	public IIncrementUpdateBlh getIncrementUpdateBlh() {
		return incrementUpdateBlh;
	}

	public void setIncrementUpdateBlh(IIncrementUpdateBlh incrementUpdateBlh) {
		this.incrementUpdateBlh = incrementUpdateBlh;
	}

}
