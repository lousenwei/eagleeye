package com.eagleeye.schedule.listener;

import org.quartz.JobExecutionContext;
import org.quartz.listeners.JobListenerSupport;

public class CommonJobListener extends JobListenerSupport {

	public String getName() {
		return "commonJobListener";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		super.jobToBeExecuted(context);

		// System.out.println(context.getJobDetail().getName());
	}

}
