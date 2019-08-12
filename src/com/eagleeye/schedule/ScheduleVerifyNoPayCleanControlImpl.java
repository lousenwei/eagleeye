package com.eagleeye.schedule;

import org.apache.log4j.Logger;

import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.nopayclean.bsh.INoPayCleanBsh;

public class ScheduleVerifyNoPayCleanControlImpl implements IScheduleCommomControl {

	private Logger log = Logger.getLogger(ScheduleVerifyNoPayCleanControlImpl.class);

	INoPayCleanBsh noPayCleanBsh = (INoPayCleanBsh) EagleEyeServiceLocator
			.getBean("noPayCleanBsh");

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			noPayCleanBsh.loadAllNoPayCleanPersonsByNewDate();
		} catch (Exception e) {
			log.error(e);
		}
	}

}
