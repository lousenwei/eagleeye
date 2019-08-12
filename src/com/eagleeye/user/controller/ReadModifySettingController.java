package com.eagleeye.user.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.Messages;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.user.bsh.IManagerInfoBsh;
import com.eagleeye.user.eo.ManagerInfoEO;

/**
 * @author wilson
 * 
 */
@ManagedBean(name = "RMSetting")
@RequestScoped
public class ReadModifySettingController {

	private IManagerInfoBsh managerInfoBsh;
	private String managerId;
	private String oldReadPsd;
	private String oldModifyPsd;
	private String newReadPsd;
	private String newModifyPsd;
	private String readPsd;
	private String modifyPsd;

	public ReadModifySettingController() {
		super();
		// TODO Auto-generated constructor stub
		managerInfoBsh = (IManagerInfoBsh) EagleEyeServiceLocator
				.getBean("managerInfoBsh");
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
	}

	private boolean preCheckUnNull(String psd) {
		if (psd == null || psd.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages
							.getString("psd_setting_null_brief"), null));
			return false;
		}
		return true;
	}

	/**
	 * 保存查看密码
	 */
	public void saveReadPsd() {
//		if (!this.preCheckUnNull(newReadPsd)) {
//			return;
//		}
		ManagerInfoEO manager = managerInfoBsh
				.getDistinctManagerInfoEO(managerId);
		if (manager != null && manager.getReadPsd() != null
				&& manager.getReadPsd().equals(oldReadPsd)) {
			managerInfoBsh.saveReadPsd(managerId, newReadPsd);
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									Messages.getString("read_psd_setting_success_brief"),
									Messages.getString("read_psd_setting_success_detail")));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages
							.getString("read_psd_setting_fail_brief"), Messages
							.getString("read_psd_setting_fail_detail")));
		}
	}

	/**
	 * 保存修改密码
	 */
	public void saveModifyPsd() {
//		if (!this.preCheckUnNull(newModifyPsd)) {
//			return;
//		}
		ManagerInfoEO manager = managerInfoBsh
				.getDistinctManagerInfoEO(managerId);
		if (manager != null && manager.getReadPsd() != null
				&& manager.getModifyPsd().equals(oldModifyPsd)) {
			managerInfoBsh.saveModifyPsd(managerId, newModifyPsd);
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									Messages.getString("modify_psd_setting_success_brief"),
									Messages.getString("modify_psd_setting_success_detail")));
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Messages.getString("modify_psd_setting_fail_brief"),
									Messages.getString("modify_psd_setting_fail_detail")));
		}
	}

	public void unlockReadLock() {
		ManagerInfoEO manager = managerInfoBsh
				.getDistinctManagerInfoEO(managerId);
		if (manager.getReadPsd().equals(readPsd)) {
			SessionManager.setSessionKeyValue(EagleConstants.READ_LOCK, false);
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages
							.getString("psd_wrong_brief"), Messages
							.getString("read_psd_wrong_detail")));
		}
	}

	public void unlockModifyLock() {
		ManagerInfoEO manager = managerInfoBsh
				.getDistinctManagerInfoEO(managerId);
		if (manager.getModifyPsd().equals(modifyPsd)) {
			SessionManager
					.setSessionKeyValue(EagleConstants.MODIFY_LOCK, false);
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages
							.getString("psd_wrong_brief"), Messages
							.getString("modify_psd_wrong_detail")));
		}
	}

	public String getOldReadPsd() {
		return oldReadPsd;
	}

	public void setOldReadPsd(String oldReadPsd) {
		this.oldReadPsd = oldReadPsd;
	}

	public String getOldModifyPsd() {
		return oldModifyPsd;
	}

	public void setOldModifyPsd(String oldModifyPsd) {
		this.oldModifyPsd = oldModifyPsd;
	}

	public String getNewReadPsd() {
		return newReadPsd;
	}

	public void setNewReadPsd(String newReadPsd) {
		this.newReadPsd = newReadPsd;
	}

	public String getNewModifyPsd() {
		return newModifyPsd;
	}

	public void setNewModifyPsd(String newModifyPsd) {
		this.newModifyPsd = newModifyPsd;
	}

	public String getReadPsd() {
		return readPsd;
	}

	public void setReadPsd(String readPsd) {
		this.readPsd = readPsd;
	}

	public String getModifyPsd() {
		return modifyPsd;
	}

	public void setModifyPsd(String modifyPsd) {
		this.modifyPsd = modifyPsd;
	}

}
