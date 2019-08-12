package com.eagleeye.common.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.eagleeye.cache.CacheUtil;
import com.eagleeye.common.spring.EagleEyeServiceLocator;

/**
 * 当启动时进行cash的封.
 * 
 * @author wilson.lou
 * 
 */
@SuppressWarnings("serial")
public class StartUpCacheServlet extends HttpServlet {
	private Logger log = Logger.getLogger(this.getClass());

	// private static XManager xManager = null;
	/***
	 * cash信息.
	 */
	public void init() throws ServletException {
		// 获得WebApplicationContext对象，以便获得对应业务Bean
		ServletContext sc = getServletConfig().getServletContext();

		// 设置对应的webContext以用来得到spring的Bean.
		EagleEyeServiceLocator.setWebContext(sc);
		//this.refreshAllCache();

		// ---------------上线需要打开
		// 启动计划任务
//		this.startXScheduler();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 刷新所有
		if (request.getParameter("cacheKey") != null && ((String) request.getParameter("cacheKey")).trim().equals("ALL")) {
			// 刷新所有
			this.refreshAllCache();
		}// End of if
		else if (request.getParameter("cacheKey") != null && ((String) request.getParameter("cacheKey")).trim().equals("CODEVALUE")) {
			this.refreshCache(null);
		}// End of else if
		else if (request.getParameter("cacheKey") != null && !((String) request.getParameter("cacheKey")).trim().equals("")) {
			this.refreshCache(((String) request.getParameter("cacheKey")).trim());
		}// End of else if
	}

	

	// 刷新所有缓存
	private void refreshAllCache() {
		try {
			log.info("**************开始刷新cache*****************");
			// 将code信息放入缓存.
			CacheUtil cashCode = (CacheUtil) EagleEyeServiceLocator.getBean("codeUtilCache");
			cashCode.refreshCache();
			log.info("**************成功初始化cache*****************");
			// 将对应的resource放入到properties设置中.
			// ConstantProperties.setPropertiesFile(ConstantFilePath.FILE_PATH_CONTEXT_BASE);
		} catch (Exception e) {
			log.error("**************刷新缓存错误*****************", e);
		}
	}

	// 刷新某一个缓存
	private void refreshCache(String cacheKey) {
		try {
			// 将code信息放入缓存.
			CacheUtil cashCode = (CacheUtil) EagleEyeServiceLocator.getBean("codeUtilCache");
			if (cacheKey != null)
				cashCode.refreshCache(cacheKey);
			else
				cashCode.refreshCache();
		} catch (Exception e) {
			log.error("**************refresh cache 错误*****************", e);
		}
	}
}
