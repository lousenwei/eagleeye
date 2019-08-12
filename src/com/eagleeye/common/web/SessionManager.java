package com.eagleeye.common.web;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author wilson.lou
 * 
 */
public class SessionManager {

	/***
	 * 得到jsf的session.
	 * 
	 * @return
	 */
	public static HttpSession getHttpSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		HttpSession httpSession = request.getSession(false);
		return httpSession;
	}

	/***
	 * 根据key得到对应的值.
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSessionByKey(String key) {
		return getHttpSession().getAttribute(key);
	}

	/***
	 * 设置key对应的值.
	 * 
	 * @param key
	 * @param values
	 */
	public static void setSessionKeyValue(String key, Object values) {
		getHttpSession().setAttribute(key, values);

	}

	/***
	 * 移除key对应的session.
	 * 
	 * @param key
	 */
	public static void removeAttribute(String key) {
		getHttpSession().removeAttribute(key);
	}

	/**
	 * 使当前Session失效
	 */
	public static void invalidateSession() {
		getHttpSession().invalidate();
		// getHttpSession().setMaxInactiveInterval(0);
	}

}
