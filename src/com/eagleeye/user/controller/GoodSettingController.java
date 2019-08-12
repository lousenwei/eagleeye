package com.eagleeye.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.bsh.IEServiceBsh;
import com.eagleeye.statistics.bsh.INoCalCulateItemBsh;
import com.eagleeye.statistics.eo.NoCalculateItemsEO;
import com.Messages;

@ManagedBean(name = "itemConfig")
@ViewScoped
public class GoodSettingController {

	private List<NoCalculateItemsEO> noCalculateItems = new ArrayList();
	private Long selectedItem;
	private Long addItem;
	private String managerId;
	private String shopId;
	private boolean hasNewAdd = false;

	INoCalCulateItemBsh noCalCulateItemBsh;
	IEServiceBsh eServiceBsh;

	public GoodSettingController() {
		super();
		// TODO Auto-generated constructor stub
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		shopId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPSHOPID);
		noCalCulateItemBsh = (INoCalCulateItemBsh) EagleEyeServiceLocator
				.getBean("noCalCulateItemBsh");
		eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator
				.getBean("eServiceBsh");
		loadNoCalCulateItems(managerId);
	}

	public void loadNoCalCulateItems(String managerId) {
		noCalculateItems = noCalCulateItemBsh
				.getNoCalculateItemsByManagerId(managerId);
	}

	public void runAllStatAchievementReport() {
		if (managerId != null && shopId != null && hasNewAdd) {
			eServiceBsh.runPersonalStatAchievementReport(managerId, shopId);
			eServiceBsh.runShopStatAchievementReport(managerId, shopId);
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_INFO,
									Messages.getString("good_setting_success_brief.0"),
									Messages.getString("good_setting_success_detail.0")));
		} else if (managerId != null && shopId != null && !hasNewAdd) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									Messages.getString("good_setting_warning_brief.0"),
									Messages.getString("good_setting_warning_detail.0")));
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Messages.getString("good_setting_warning_brief.1"),
									Messages.getString("good_setting_warning_detail.1")));
		}
	}

	/**
	 * 
	 * 删除过滤商品事件
	 */
	public void deleteNoCalCulateItem() {
		noCalCulateItemBsh.deleteByItemId(selectedItem);
		selectedItem = 0L;
		loadNoCalCulateItems(managerId);
		hasNewAdd = true;
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, Messages
						.getString("good_setting_success_brief.1"), Messages
						.getString("good_setting_success_detail.1")));
	}

	/**
	 * 
	 * 添加过滤商品事件
	 */
	public void addNoCalCulateItem() {
		if (addItem != 0 && !checkExists(addItem)) {
			NoCalculateItemsEO newItem = new NoCalculateItemsEO();
			newItem.setManagerId(managerId);
			newItem.setNumIid(addItem);
			noCalCulateItemBsh.saveItem(newItem);
			loadNoCalCulateItems(managerId);
			hasNewAdd = true;
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									Messages.getString("good_setting_success_brief.2"),
									Messages.getString("good_setting_success_detail.2")));
		}
	}

	private Boolean checkExists(Long itemId) {
		if (noCalculateItems != null) {
			for (NoCalculateItemsEO temp : noCalculateItems) {
				if (temp.getNumIid().equals(itemId)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<NoCalculateItemsEO> getNoCalculateItems() {
		return noCalculateItems;
	}

	public void setNoCalculateItems(List<NoCalculateItemsEO> noCalculateItems) {
		this.noCalculateItems = noCalculateItems;
	}

	public Long getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Long selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Long getAddItem() {
		return addItem;
	}

	public void setAddItem(Long addItem) {
		this.addItem = addItem;
	}
}
