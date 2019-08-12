package com.eagleeye.schedule;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.eagleeye.common.spring.EagleEyeServiceLocator;

public class SchedulerFactory extends SchedulerFactoryBean {

	private final Logger logger = Logger.getLogger(SchedulerFactory.class);

	public SchedulerFactory() {
		super();
		// TODO Auto-generated constructor stub
		setTriggers();
	}

	/**
	 * 设置触发器列表
	 * 
	 * @throws IOException
	 */
	private void setTriggers() {
		Properties scheduleProperties = getScheduleProperties();
		List<Trigger> triggerList = new ArrayList<Trigger>();
		logger.info("Starting schedule:----------------------------");
		for (Object triggerName : scheduleProperties.keySet()) {
			logger.info((String) triggerName);
			String status = scheduleProperties
					.getProperty((String) triggerName);
			if (status.equalsIgnoreCase("on")) {
				Trigger trigger = (Trigger) EagleEyeServiceLocator
						.getBean((String) triggerName);
				triggerList.add(trigger);
			}
		}
		setTriggers(triggerList.toArray(new Trigger[triggerList.size()]));
	}

	/**
	 * 读取触发器配置文件
	 * 
	 * @return
	 * @throws IOException
	 */
	public Properties getScheduleProperties() {
		String propertiesFileName = "configs/schedule.properties";
		Properties scheduleProperties = new Properties();
		try {
			InputStream in = SchedulerFactory.class.getClassLoader()
					.getResourceAsStream(propertiesFileName);
			scheduleProperties.load(in);
		} catch (IOException e) {
			logger.error("触发器配置文件:" + propertiesFileName + "没找到！");
		}
		return scheduleProperties;
	}

}
