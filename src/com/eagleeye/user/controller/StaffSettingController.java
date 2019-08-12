package com.eagleeye.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.bsh.ITotalUpdateBsh;
import com.eagleeye.user.bsh.IUserBsh;
import com.eagleeye.user.constant.UserConstant;
import com.eagleeye.user.eo.GroupMemberEO;
import com.eagleeye.user.eo.GroupMemberEOId;
import com.eagleeye.user.eo.TotalUpdateDetailEO;
import com.eagleeye.user.eo.TotalUpdateDetailEOId;

@ManagedBean(name = "staffConfig")
@ViewScoped
public class StaffSettingController {

	private Logger log = Logger.getLogger(StaffSettingController.class);
	IGroupMemberBsh groupMemberBsh;
	private List<GroupMemberEO> allStaffs = new ArrayList();
	private String newStaff = new String();
	private List<String> newStaffs = new ArrayList();
	private String selectedStaff = new String();
	private String managerId;
	private String shopId;
	ITotalUpdateBsh totalUpdateBsh;
	IUserBsh userBsh;
	List<String> source = new ArrayList();

	/**
	 * 初始化
	 */
	public StaffSettingController() {
		super();
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		shopId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPSHOPID);
		groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator
				.getBean("groupMemberBsh");
		allStaffs = groupMemberBsh.getGroupMemebersByManagerId(managerId);
		loadAllMembers();
	}

	private void loadAllMembers() {
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		userBsh = (IUserBsh) EagleEyeServiceLocator.getBean("userBsh");
		if (managerId != null) {
			source = userBsh.loadUser(managerId);
		}
	}

	/**
	 * 删除客服事件
	 */
	public void deleteStaff() {
		if (selectedStaff != null && !selectedStaff.isEmpty()) {
			groupMemberBsh.deleteGroupMemberByStaffId(selectedStaff);
			selectedStaff = null;
			allStaffs = groupMemberBsh.getGroupMemebersByManagerId(managerId);
		}
	}

	/**
	 * 添加客服事件
	 */
	public void addStaff() {
		if (newStaffs != null && !newStaffs.isEmpty()) {
			List<GroupMemberEO> results = new ArrayList();
			for (String temp : newStaffs) {
				newStaff = temp;
				if (newStaff != null && !newStaff.isEmpty()
						&& !checkExists(newStaff)
						&&  !"undefine".startsWith(temp)) {
					GroupMemberEO newStaffEo = new GroupMemberEO();
					GroupMemberEOId id = new GroupMemberEOId();
					id.setManagerId(managerId);
					id.setServiceStaffId(newStaff);
					newStaffEo.setId(id);
					newStaffEo.setGroupName(managerId);
					newStaffEo.setShopId(shopId);
					newStaffEo.setUpdatedAt(new Date());
					newStaffEo.setCreatedAt(new Date());
					results.add(newStaffEo);
				}
			}
			if (results != null && !results.isEmpty()) {
				groupMemberBsh.saveGroupMembers(results);
				// 放弃更新
				// saveTotalUpdate(results);
				allStaffs = groupMemberBsh
						.getGroupMemebersByManagerId(managerId);
			}
		}
	}

	/**
	 * 检查客服是否在列表中
	 * 
	 * @param staffId
	 * @return
	 */
	private Boolean checkExists(String staffId) {
		if (allStaffs != null) {
			for (GroupMemberEO temp : allStaffs) {
				if (temp.getId().getServiceStaffId().equals(staffId)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 添加全量更新记录
	 */
	public void saveTotalUpdate(List<GroupMemberEO> staffIds) {
		List<TotalUpdateDetailEO> totalUpdates = new ArrayList();
		for (GroupMemberEO temp : staffIds) {
			String staffId = temp.getId().getServiceStaffId();
			if (staffId != null && !staffId.isEmpty()) {
				totalUpdateBsh = (ITotalUpdateBsh) EagleEyeServiceLocator
						.getBean("totalUpdateBsh");
				// 做check，该客服是否做过全量更新
				if (!totalUpdateBsh.checkHasTotalUpdateByStaffIdAndManagerId(
						staffId, managerId)) {
					// 否，则做一次全量更新
					TotalUpdateDetailEO totalUpdate = new TotalUpdateDetailEO();
					TotalUpdateDetailEOId id = new TotalUpdateDetailEOId();
					id.setStaffId(staffId);
					id.setUpdateTime(DateUtil.getPreviousDay(new Date(),
							EagleConstants.UPDATE_TIME_END_GAP));
					String sessionKey = (String) SessionManager
							.getSessionByKey(EagleConstants.TOPSESSIONKEY);
					String shopId = (String) SessionManager
							.getSessionByKey(EagleConstants.TOPSHOPID);
					id.setManagerId(managerId);
					totalUpdate.setId(id);
					totalUpdate.setSessionKey(sessionKey);
					totalUpdate.setStatus(UserConstant.INIT);
					totalUpdate.setShopId(shopId);
					totalUpdates.add(totalUpdate);
				}
			}
		}
		if (totalUpdates != null && !totalUpdates.isEmpty()) {
			totalUpdateBsh.saveTotalUpdates(totalUpdates);
		}
	}

	public List<GroupMemberEO> getAllStaffs() {
		return allStaffs;
	}

	public void setAllStaffs(List<GroupMemberEO> allStaffs) {
		this.allStaffs = allStaffs;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getNewStaff() {
		return newStaff;
	}

	public void setNewStaff(String newStaff) {
		this.newStaff = newStaff;
	}

	public String getSelectedStaff() {
		return selectedStaff;
	}

	public void setSelectedStaff(String selectedStaff) {
		this.selectedStaff = selectedStaff;
	}

	public List<String> getSource() {
		return source;
	}

	public void setSource(List<String> source) {
		this.source = source;
	}

	public List<String> getNewStaffs() {
		return newStaffs;
	}

	public void setNewStaffs(List<String> newStaffs) {
		this.newStaffs = newStaffs;
	}

}
