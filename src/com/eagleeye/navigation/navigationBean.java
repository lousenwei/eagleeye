package com.eagleeye.navigation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.web.SessionManager;

@ManagedBean(name = "naviBean")
@SessionScoped
public class navigationBean {
	public navigationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer menuNum = 0;

	public Integer getMenuNum() {
		Object menuNum = SessionManager.getSessionByKey(EagleConstants.TOPMENU);
		if (menuNum != null) {
			return (Integer) menuNum;
		}
		return 0;
	}

	public void setMenuNum(Integer menuNum) {
		this.menuNum = menuNum;
	}

	// 2012-8-15,v1.7,验证session读锁
	public Boolean getReadLock() {
		Object readLock = SessionManager
				.getSessionByKey(EagleConstants.READ_LOCK);
		if (readLock != null) {
			return (Boolean) readLock;
		}
		return true;
	}

	// 2012-8-15，v1.7，验证session写锁
	public Boolean getModifyLock() {
		Object modifyLock = SessionManager
				.getSessionByKey(EagleConstants.MODIFY_LOCK);
		if (modifyLock != null) {
			return (Boolean) modifyLock;
		}
		return true;
	}
}
