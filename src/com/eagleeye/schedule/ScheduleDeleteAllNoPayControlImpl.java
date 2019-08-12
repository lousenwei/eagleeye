package com.eagleeye.schedule;

import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.nopayclean.bsh.INoPayCleanBsh;
import com.eagleeye.nopayclean.eo.NoPayCleanDetailEO;
import com.eagleeye.schedule.constant.ScheduleConstants;

public class ScheduleDeleteAllNoPayControlImpl implements
		IScheduleCommomControl {

	private Logger log = Logger
			.getLogger(ScheduleDeleteAllNoPayControlImpl.class);

	INoPayCleanBsh noPayCleanBsh = (INoPayCleanBsh) EagleEyeServiceLocator
			.getBean("noPayCleanBsh");

	@Override
	public void execute() {
		List<NoPayCleanDetailEO> undoList = noPayCleanBsh
				.getAllNoUndoPayCleanDetails();
		// TODO Auto-generated method stub
		if (undoList != null && !undoList.isEmpty()) {
			for (NoPayCleanDetailEO temp : undoList) {
				try {
					noPayCleanBsh.deleteAllDataBySchedule(temp);
					temp.setStatus(ScheduleConstants.SCHEDULE_SUCCESS_STATUS);
					log.info("delete all " + temp.getId().getManagerId()
							+ "success!");
				} catch (Exception e) {
					temp.setStatus(ScheduleConstants.SCHEDULE_FAIL_STATUS);
					log.error("delete all " + temp.getId().getManagerId()
							+ "error! \n" + e);

				}
			}
			// 处理完保存
			noPayCleanBsh.updateAll(undoList);
		}
	}

	public static void main(String[] args) {
		try {
			INoPayCleanBsh noPayCleanBsh = (INoPayCleanBsh) EagleEyeServiceLocator
					.getBean("noPayCleanBsh");
			List<NoPayCleanDetailEO> undoList = noPayCleanBsh
					.getAllNoUndoPayCleanDetails();
			// TODO Auto-generated method stub
			if (undoList != null && !undoList.isEmpty()) {
				for (NoPayCleanDetailEO temp : undoList) {
					try {
						noPayCleanBsh.deleteAllDataBySchedule(temp);
						temp.setStatus(ScheduleConstants.SCHEDULE_SUCCESS_STATUS);
					} catch (Exception e) {
						temp.setStatus(ScheduleConstants.SCHEDULE_FAIL_STATUS);
					}
				}
				// 处理完保存
				noPayCleanBsh.updateAll(undoList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
