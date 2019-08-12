package com.eagleeye.common.spring;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/***
 * 根据beanId得到bean信息.
 * 
 * @author wilson.lou.
 * 
 */
public class EagleEyeServiceLocator {

	private static String configPath = "classpath:" + "configs/applicationContext.xml";
	// web的context得到bean
	private static WebApplicationContext webContext = null;
	// 用于测试的文件系统得到bean.
	private static ApplicationContext aplication = null;

	/***
	 * 当sevlet 启动时设置值.
	 * 
	 * @param sc
	 */
	public static void setWebContext(ServletContext sc) {
		webContext = WebApplicationContextUtils.getWebApplicationContext(sc);
	}

	/***
	 * 得到bean的信息.
	 * 
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		if (webContext == null) {
			if (aplication == null) {
				aplication = new FileSystemXmlApplicationContext(configPath);
			}
			return aplication.getBean(beanId);
		} else {
			return webContext.getBean(beanId);
		}
	}
}
