package com.eagleeye.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.schedule.constant.ScheduleConstants;
import com.eagleeye.statistics.bsh.IBelongTypeBsh;
import com.eagleeye.statistics.bsh.INoCalCulateItemBsh;
import com.eagleeye.statistics.eo.BelongTypeEO;
import com.eagleeye.statistics.eo.NoCalculateItemsEO;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.bsh.ITotalUpdateBsh;
import com.eagleeye.user.bsh.IUserBsh;
import com.eagleeye.user.constant.UserConstant;
import com.eagleeye.user.eo.GroupMemberEO;
import com.eagleeye.user.eo.GroupMemberEOId;
import com.eagleeye.user.eo.TotalUpdateDetailEO;
import com.eagleeye.user.eo.TotalUpdateDetailEOId;
import com.eagleeye.user.mo.StaffMO;

@ManagedBean(name = "userConfig")
@ViewScoped
public class UserConfigController {
	private Logger log = Logger.getLogger(UserConfigController.class);

	IUserBsh userBsh;
	IGroupMemberBsh groupMemberBsh;
	INoCalCulateItemBsh noCalCulateItemBsh;
	ITotalUpdateBsh totalUpdateBsh;
	IBelongTypeBsh belongTypeBsh;

	private DualListModel<String> allStaffs;
	private List<Long> noCalculateItems = new ArrayList();
	private Long selectedItem;
	private Long addItem;
	private List<String> source;
	private List<String> target;
	private List<StaffMO> choosedStaff = new ArrayList();
	private String managerId;
	private String belongType;
	private String flag_red_owner;
	private String flag_yellow_owner;
	private String flag_green_owner;
	private String flag_blue_owner;
	private String flag_pupple_owner;
	private Map<String, Long> flagOwnerMap = new HashMap();

	/**
	 * 初始化
	 * 
	 * 导入所有子帐号
	 */
	public UserConfigController() {
		managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		userBsh = (IUserBsh) EagleEyeServiceLocator.getBean("userBsh");
		belongTypeBsh = (IBelongTypeBsh) EagleEyeServiceLocator.getBean("belongTypeBsh");
		if (managerId != null) {
			source = userBsh.loadUser(managerId);
			target = new ArrayList();
			allStaffs = new DualListModel<String>(source, target);
		}
		belongType = EagleConstants.BELONG_TYPE_PAY_MOST_TALK;
	}

	/**
	 * 初始化
	 * 
	 * 删除过滤商品事件
	 */
	public void deleteNoCalCulateItems() {
		noCalculateItems.remove(selectedItem);
	}

	/**
	 * 初始化
	 * 
	 * 添加过滤商品事件
	 */
	public void addNoCalCulateItems() {
		if (addItem != 0 && !noCalculateItems.contains(addItem)) {
			noCalculateItems.add(addItem);
		}
	}

	/**
	 * 检查是否需要初始化更新
	 */
	public void checkValidation() {
		if ((Boolean) SessionManager.getSessionByKey(EagleConstants.NEED_CONFIG) == null
				|| !(Boolean) SessionManager.getSessionByKey(EagleConstants.NEED_CONFIG)) {
			try {
				HttpServletResponse httpResponse = TaoBaoUtil.getHttpResponse();
				httpResponse.sendRedirect(EagleConstants.URI_INDEX_PAGE);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	/**
	 * 保存过滤商品
	 */
	public void saveNoCalCulateItems() {
		if (noCalculateItems != null && !noCalculateItems.isEmpty()) {
			List<NoCalculateItemsEO> results = new ArrayList();
			for (Long temp : noCalculateItems) {
				NoCalculateItemsEO item = new NoCalculateItemsEO();
				item.setManagerId(managerId);
				item.setNumIid(temp);
				results.add(item);
			}
			if (results != null && !results.isEmpty()) {
				noCalCulateItemBsh = (INoCalCulateItemBsh) EagleEyeServiceLocator.getBean("noCalCulateItemBsh");
				noCalCulateItemBsh.saveItems(results);
			}
		}
	}

	/**
	 * 保存业绩归属方式
	 */
	public void saveBelongType() {
		belongType =String.valueOf(SessionManager.getSessionByKey("BelongType"));
		if (belongType == null || belongType.isEmpty()) {
			belongType = EagleConstants.BELONG_TYPE_PAY_MOST_TALK;
		}
		BelongTypeEO temp = new BelongTypeEO(belongType, managerId);
		belongTypeBsh.saveItem(temp);
	}

	/**
	 * 保存客服
	 */
	public void saveGroupMembers() {
		if (choosedStaff != null && !choosedStaff.isEmpty()) {
			List<GroupMemberEO> groupMemebers = new ArrayList();
			for (StaffMO temp : choosedStaff) {
				if (temp != null && !"undefine".startsWith(temp.getStaffId())) {
					String shopId = (String) SessionManager.getSessionByKey(EagleConstants.TOPSHOPID);
					GroupMemberEO staff = new GroupMemberEO();
					GroupMemberEOId id = new GroupMemberEOId();
					id.setManagerId(managerId);
					id.setServiceStaffId(temp.getStaffId());
					staff.setId(id);
					staff.setShopId(shopId);
					staff.setUpdatedAt(new Date());
					staff.setCreatedAt(new Date());
					staff.setGroupName(managerId);
					staff.setFlag(temp.getFlag());
					groupMemebers.add(staff);
				}
			}
			groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator.getBean("groupMemberBsh");
			try {
				groupMemberBsh.saveGroupMembers(groupMemebers);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	/**
	 * 添加全量更新记录
	 */
	public void saveTotalUpdate() {
		if (allStaffs.getTarget() != null && !allStaffs.getTarget().isEmpty()) {
			totalUpdateBsh = (ITotalUpdateBsh) EagleEyeServiceLocator.getBean("totalUpdateBsh");
			List<TotalUpdateDetailEO> results = new ArrayList();
			// 做check，做全量更新
			if (!totalUpdateBsh.checkHasTotalUpdateByStaffIdAndManagerId(ScheduleConstants.SCHEDULE_UPDATE_ALLSTAFF,
					managerId)) {
				// 否，则做一次全量更新
				TotalUpdateDetailEO totalUpdate = new TotalUpdateDetailEO();
				TotalUpdateDetailEOId id = new TotalUpdateDetailEOId();
				id.setStaffId(ScheduleConstants.SCHEDULE_UPDATE_ALLSTAFF);
				Date updateTime = DateUtil.getSimpleDate(DateUtil.getPreviousDay(new Date(),
						UserConstant.INCREMENT_UPDATE_GAP));
				id.setUpdateTime(updateTime);
				String sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
				String shopId = (String) SessionManager.getSessionByKey(EagleConstants.TOPSHOPID);
				id.setManagerId(managerId);
				totalUpdate.setId(id);
				totalUpdate.setSessionKey(sessionKey);
				totalUpdate.setStatus(UserConstant.INIT);
				totalUpdate.setShopId(shopId);
				results.add(totalUpdate);
			}
			totalUpdateBsh.saveTotalUpdates(results);
		}
	}

	/**
	 * 保存按钮事件
	 */
	public void save() {
		String sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		log.error(managerId + " " + sessionKey + " 初始化界面");
		saveNoCalCulateItems();
		saveBelongType();
		saveGroupMembers();
		saveTotalUpdate();
		try {
			HttpServletResponse httpResponse = TaoBaoUtil.getHttpResponse();
			httpResponse.sendRedirect(EagleConstants.URI_INDEX_PAGE);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public String onFlowProcess(FlowEvent event) {
		if ("nocalculateitem".equals(event.getNewStep())) {
			choosedStaff.clear();
			if (allStaffs != null && !allStaffs.getTarget().isEmpty()) {
				for (String staffId : allStaffs.getTarget()) {
					StaffMO temp = new StaffMO();
					temp.setStaffId(staffId);
					choosedStaff.add(temp);
				}
			}
		} else if ("confirm".equals(event.getNewStep())) {
			SessionManager.setSessionKeyValue("BelongType", belongType);
			if (EagleConstants.BELONG_TYPE_FLAG.equals(belongType)) {
				flagOwnerMap.put(flag_red_owner, EagleConstants.FLAG_RED);
				flagOwnerMap.put(flag_yellow_owner, EagleConstants.FLAG_YELLOW);
				flagOwnerMap.put(flag_green_owner, EagleConstants.FLAG_GREEN);
				flagOwnerMap.put(flag_blue_owner, EagleConstants.FLAG_BLUE);
				flagOwnerMap.put(flag_pupple_owner, EagleConstants.FLAG_PUPPLE);
				for (StaffMO temp : choosedStaff) {
					if (flagOwnerMap.get(temp.getStaffId()) != null) {
						temp.setFlag(flagOwnerMap.get(temp.getStaffId()));
					}
				}
			}
		}
		return event.getNewStep();
	}

	public DualListModel<String> getAllStaffs() {
		return allStaffs;
	}

	public void setAllStaffs(DualListModel<String> allStaffs) {
		this.allStaffs = allStaffs;
	}

	public List<Long> getNoCalculateItems() {
		return noCalculateItems;
	}

	public void setNoCalculateItems(List<Long> noCalculateItems) {
		this.noCalculateItems = noCalculateItems;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
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

	public String getBelongType() {
		return belongType;
	}

	public void setBelongType(String belongType) {
		this.belongType = belongType;
	}

	public List<StaffMO> getChoosedStaff() {
		return choosedStaff;
	}

	public void setChoosedStaff(List<StaffMO> choosedStaff) {
		this.choosedStaff = choosedStaff;
	}

	public String getFlag_red_owner() {
		return flag_red_owner;
	}

	public void setFlag_red_owner(String flag_red_owner) {
		this.flag_red_owner = flag_red_owner;
	}

	public String getFlag_yellow_owner() {
		return flag_yellow_owner;
	}

	public void setFlag_yellow_owner(String flag_yellow_owner) {
		this.flag_yellow_owner = flag_yellow_owner;
	}

	public String getFlag_green_owner() {
		return flag_green_owner;
	}

	public void setFlag_green_owner(String flag_green_owner) {
		this.flag_green_owner = flag_green_owner;
	}

	public String getFlag_blue_owner() {
		return flag_blue_owner;
	}

	public void setFlag_blue_owner(String flag_blue_owner) {
		this.flag_blue_owner = flag_blue_owner;
	}

	public String getFlag_pupple_owner() {
		return flag_pupple_owner;
	}

	public void setFlag_pupple_owner(String flag_pupple_owner) {
		this.flag_pupple_owner = flag_pupple_owner;
	}

}
