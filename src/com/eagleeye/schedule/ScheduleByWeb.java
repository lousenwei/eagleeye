package com.eagleeye.schedule;

import com.eagleeye.common.spring.EagleEyeServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class for Servlet: ScheduleTest
 * 
 */
public class ScheduleByWeb extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ScheduleByWeb() {
		super();
	}

	public void init(ServletConfig servletconfig) throws ServletException {
		super.init(servletconfig);
		initScheduler("start");
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		initScheduler(type);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void initScheduler(String type) {
		if (type == null || "".equals(type)) {
			return;
		}
		log.info("----->initScheduler run : " + type);
		try {
			Scheduler scheduler = (Scheduler) EagleEyeServiceLocator.getBean("schedulerBean");
			if ("stop".equals(type)) {
				if (!scheduler.isShutdown()) {
					scheduler.shutdown();
				}

			}

		} catch (Exception e) {
			log.error("Quartz   Init   Servlet   failed ", e);
		}
	}
	
}