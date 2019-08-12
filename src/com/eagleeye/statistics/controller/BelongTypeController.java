package com.eagleeye.statistics.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.Messages;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.bsh.IEServiceBsh;
import com.eagleeye.statistics.bsh.IBelongTypeBsh;
import com.eagleeye.statistics.bsh.ITradeStatBsh;
import com.eagleeye.statistics.eo.BelongTypeEO;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.eo.GroupMemberEO;

@ManagedBean(name = "belongTypeBean")
@ViewScoped
public class BelongTypeController {

	IBelongTypeBsh belongTypeBsh;
	IEServiceBsh eServiceBsh;
	ITradeStatBsh tradeStatBsh;
	IGroupMemberBsh groupMemberBsh;

	private String managerId;
	private boolean hasChanged = false;
	private String belongType;
	private String sessionKey;
	private String shopId;
	private int count = 0;
	private long progress = 0;
	private String flag_red_owner;
	private String flag_yellow_owner;
	private String flag_green_owner;
	private String flag_blue_owner;
	private String flag_pupple_owner;
	private Map<String, Long> flagOwnerMap = new HashMap();
	private List<GroupMemberEO> allStaffs = new ArrayList();

	public BelongTypeController() {
		super();
		// TODO Auto-generated constructor stub
		managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		shopId = (String) SessionManager.getSessionByKey(EagleConstants.TOPSHOPID);
		belongTypeBsh = (IBelongTypeBsh) EagleEyeServiceLocator.getBean("belongTypeBsh");
		eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator.getBean("eServiceBsh");
		tradeStatBsh = (ITradeStatBsh) EagleEyeServiceLocator.getBean("tradeStatBsh");
		groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator.getBean("groupMemberBsh");
		if (managerId != null) {
			belongType = belongTypeBsh.getBelongTypeByManagerId(managerId);
			allStaffs = groupMemberBsh.getGroupMemebersByManagerId(managerId);
			if (belongType == null || belongType.isEmpty()) {
				belongType = EagleConstants.BELONG_TYPE_PAY_MOST_TALK;
				hasChanged = true;
			}
			if (allStaffs != null && !allStaffs.isEmpty()) {
				
				for (GroupMemberEO temp : allStaffs) {
					if (EagleConstants.FLAG_RED.equals(temp.getFlag())) {
						flag_red_owner = temp.getId().getServiceStaffId();
					} else if (EagleConstants.FLAG_YELLOW.equals(temp.getFlag())) {
						flag_yellow_owner = temp.getId().getServiceStaffId();
					} else if (EagleConstants.FLAG_GREEN.equals(temp.getFlag())) {
						flag_green_owner = temp.getId().getServiceStaffId();
					} else if (EagleConstants.FLAG_BLUE.equals(temp.getFlag())) {
						flag_blue_owner = temp.getId().getServiceStaffId();
					} else if (EagleConstants.FLAG_PUPPLE.equals(temp.getFlag())) {
						flag_pupple_owner = temp.getId().getServiceStaffId();
					}
				}
			}
		}
	}

	public void saveBelongType() {
		if (belongType != null && !belongType.isEmpty() && allStaffs != null) {
			BelongTypeEO type = new BelongTypeEO(belongType, managerId);
			belongTypeBsh.saveItem(type);
			if (EagleConstants.BELONG_TYPE_FLAG.equals(belongType)) {
				flagOwnerMap.clear();
				flagOwnerMap.put(flag_red_owner, EagleConstants.FLAG_RED);
				flagOwnerMap.put(flag_yellow_owner, EagleConstants.FLAG_YELLOW);
				flagOwnerMap.put(flag_green_owner, EagleConstants.FLAG_GREEN);
				flagOwnerMap.put(flag_blue_owner, EagleConstants.FLAG_BLUE);
				flagOwnerMap.put(flag_pupple_owner, EagleConstants.FLAG_PUPPLE);
				for (GroupMemberEO temp : allStaffs) {
					if (flagOwnerMap.get(temp.getId().getServiceStaffId()) != null) {
						temp.setFlag(flagOwnerMap.get(temp.getId().getServiceStaffId()));
					}
				}
			}
			groupMemberBsh.saveGroupMembers(allStaffs);
			hasChanged = true;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							Messages.getString("belongtype_setting_success_brief"), Messages
									.getString("belongtype_setting_success_detail")));
		}
	}

	public void reCalculate() {
		if (hasChanged) {
			Date end = DateUtil.getSimpleDate(new Date());
			Date tempStart = DateUtil.getActualMinimumDate(end);
			Date tempEnd = DateUtil.getNextDay(tempStart, (EagleConstants.ONE_TRANS_GAP - 1));
			if (end.after(new Date()) || tempStart.after(new Date()) || end.getYear() > EagleConstants.LIMITED_YEAR) {
				return;
			}
			// TODO:更新本月交易归属为null;
			count = eServiceBsh.setCurrentMonthTradeOwnerToNull(managerId);
			long round = DateUtil.getDateGap(tempStart, end) / 7;
			long i = 1;
			// 开始更新归属人
			while (!tempEnd.after(end)) {
				// 记录进度
				progress = i * 100 / round;
				if (progress > 100) {
					progress = 100;
				}
				eServiceBsh.runTradePersonalStatByDay(managerId, sessionKey, tempStart, tempEnd, null, null);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, Messages
								.getString("belongtype_setting_success_brief.2"), tempStart + "至" + tempEnd
								+ Messages.getString("belongtype_setting_success_detail.2")));
				tradeStatBsh.calulateTradeOwnersStatByDay(managerId, tempStart, tempEnd, shopId);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, Messages
								.getString("belongtype_setting_success_brief.3"), tempStart + "至" + tempEnd
								+ Messages.getString("belongtype_setting_success_detail.3")));
				tempStart = tempEnd;
				tempEnd = DateUtil.getNextDay(tempStart, (EagleConstants.ONE_TRANS_GAP - 1));
				i++;
			}
			if (!tempStart.after(end)) {
				// 结束
				progress = 100;
				eServiceBsh.runTradePersonalStatByDay(managerId, sessionKey, tempStart, end, null, null);
				tradeStatBsh.calulateTradeOwnersStatByDay(managerId, tempStart, end, shopId);
			}
			// end
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, Messages
							.getString("belongtype_setting_success_brief.1"), Messages
							.getString("belongtype_setting_success_detail.1")));
			hasChanged = false;
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, Messages
							.getString("belongtype_setting_warning_brief.0"), Messages
							.getString("belongtype_setting_warning_detail.0")));
		}
	}

	public String getBelongType() {
		return belongType;
	}

	public void setBelongType(String belongType) {
		this.belongType = belongType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getProgress() {
		return progress;
	}

	public void setProgress(long progress) {
		this.progress = progress;
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

	public List<GroupMemberEO> getAllStaffs() {
		return allStaffs;
	}

	public void setAllStaffs(List<GroupMemberEO> allStaffs) {
		this.allStaffs = allStaffs;
	}

}
