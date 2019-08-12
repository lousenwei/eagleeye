package com.eagleeye.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.bsh.IEServiceBsh;
import com.eagleeye.eservice.bsh.IUpdateLogBsh;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.eo.UpdatelogEO;
import com.eagleeye.post.bsh.IPostBsh;
import com.eagleeye.post.eo.PostEO;
import com.eagleeye.salary.bsh.ISalaryConfigBsh;
import com.eagleeye.statistics.bsh.INoCalCulateItemBsh;
import com.eagleeye.statistics.bsh.ITradeStatBsh;
import com.eagleeye.statistics.eo.NoCalculateItemsEO;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.eo.GroupMemberEO;

@ManagedBean(name = "indexBean")
@RequestScoped
public class IndexController {

	IUpdateLogBsh updateLogBsh;
	IGroupMemberBsh groupMemberBsh;
	INoCalCulateItemBsh noCalCulateItemBsh;
	ISalaryConfigBsh salaryConfigBsh;
	ITradeStatBsh tradeStatBsh;
	IEServiceBsh eServiceBsh;
	IPostBsh postBsh;

	private String managerId;
	private String sessionKey;
	private String shopId;
	private List<UpdatelogEO> updateLogs = new ArrayList();
	//private Boolean hasUpdate = false;
	private List<GroupMemberEO> members = new ArrayList();
	private List<NoCalculateItemsEO> noCalculateItems = new ArrayList();
	private Boolean hasSalaryConfiged = false;
	private List<TradeEO> pendingTrades = new ArrayList();
	private Boolean hasPending = false;
	private int pendingNum = 0;
	private Date end = DateUtil.getSimpleDate(new Date());
	private Date start = DateUtil.getSimpleDate(DateUtil.getLastMonth(new Date()));
	private List<PostEO> posts = new ArrayList();

	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		sessionKey = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		shopId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPSHOPID);
		if (managerId == null || sessionKey == null || shopId == null) {
			return;
		}
		// 获取公告
		postBsh = (IPostBsh) EagleEyeServiceLocator.getBean("postBsh");
		posts = postBsh.getTopNumPosts();
		// 获取数据更新状态
		updateLogBsh = (IUpdateLogBsh) EagleEyeServiceLocator
				.getBean("updateLogBsh");
		updateLogs = updateLogBsh.getUpdateLogs(managerId);
		// if (updateLogs == null
		// || updateLogs.isEmpty()
		// || DateUtil.getDateGap(updateLogs.get(0).getUpdateTime(),
		// DateUtil.getPreviousDay(
		// DateUtil.getSimpleDate(new Date()),
		// EagleConstants.UPDATE_TIME_END_GAP)) != 0) {
		// hasUpdate = true;
		// }
		// 获取交易pending
		tradeStatBsh = (ITradeStatBsh) EagleEyeServiceLocator
				.getBean("tradeStatBsh");
		pendingTrades = tradeStatBsh.getPendingTradeByManagerId(managerId,
				start, end);
		if (pendingTrades != null && !pendingTrades.isEmpty()) {
			hasPending = true;
			pendingNum = pendingTrades.size();
		}
		// 获取客服数据
		groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator
				.getBean("groupMemberBsh");
		members = groupMemberBsh.getGroupMemebersByManagerId(managerId);
		// 获取过滤商品
		noCalCulateItemBsh = (INoCalCulateItemBsh) EagleEyeServiceLocator
				.getBean("noCalCulateItemBsh");
		noCalculateItems = noCalCulateItemBsh
				.getNoCalculateItemsByManagerId(managerId);
		// 获取工资参数是否配置
		salaryConfigBsh = (ISalaryConfigBsh) EagleEyeServiceLocator
				.getBean("salaryConfigBsh");
		if (salaryConfigBsh.getSalaryConfigByManagerIdandTemplateName(
				managerId, EagleConstants.GENERAL_PAYMENT_TEMPLATE) != null) {
			hasSalaryConfiged = true;
		}
	}

	public void refreshPendingTrade() {
		if (managerId == null || sessionKey == null || shopId == null) {
			return;
		}
		pendingTrades = tradeStatBsh.getPendingTradeByManagerId(managerId,
				start, end);
		if (pendingTrades != null && !pendingTrades.isEmpty()) {
			eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator
					.getBean("eServiceBsh");
			// 设置业务归属
			eServiceBsh.runTradePersonalStatByDay(managerId, sessionKey,
					pendingTrades);
			// 删除对应日期的统计数据
			Map<Date, Boolean> statDays = new HashMap();
			for (TradeEO trade : pendingTrades) {
				if (trade.getPayTime() != null) {
					Date statDay = DateUtil.getSimpleDate(trade.getPayTime());
					if (statDays.get(statDay) == null) {
						statDays.put(statDay, true);
					}
				}
			}
			if (statDays != null && !statDays.isEmpty()) {
				eServiceBsh.deleteTradeStatByDays(managerId, shopId,
						statDays.keySet());
				// 设置报表
				eServiceBsh.runPersonalStatAchievementReportByDate(managerId,
						statDays.keySet(), shopId);
				// 设置报表
				eServiceBsh.runShopStatAchievementReportByDate(managerId,
						statDays.keySet(), shopId);
			}
			// 刷新界面
			pendingTrades = tradeStatBsh.getPendingTradeByManagerId(managerId,
					start, end);
			if (pendingTrades == null || pendingTrades.isEmpty()) {
				hasPending = false;
				pendingNum = 0;
			} else if (pendingTrades != null && !pendingTrades.isEmpty()) {
				hasPending = true;
				pendingNum = pendingTrades.size();
			}
		}
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public List<UpdatelogEO> getUpdateLogs() {
		return updateLogs;
	}

	public void setUpdateLogs(List<UpdatelogEO> updateLogs) {
		this.updateLogs = updateLogs;
	}

	// public Boolean getHasUpdate() {
	// return hasUpdate;
	// }
	//
	// public void setHasUpdate(Boolean hasUpdate) {
	// this.hasUpdate = hasUpdate;
	// }

	public List<GroupMemberEO> getMembers() {
		return members;
	}

	public void setMembers(List<GroupMemberEO> members) {
		this.members = members;
	}

	public List<NoCalculateItemsEO> getNoCalculateItems() {
		return noCalculateItems;
	}

	public void setNoCalculateItems(List<NoCalculateItemsEO> noCalculateItems) {
		this.noCalculateItems = noCalculateItems;
	}

	public Boolean getHasSalaryConfiged() {
		return hasSalaryConfiged;
	}

	public void setHasSalaryConfiged(Boolean hasSalaryConfiged) {
		this.hasSalaryConfiged = hasSalaryConfiged;
	}

	public List<TradeEO> getPendingTrades() {
		return pendingTrades;
	}

	public void setPendingTrades(List<TradeEO> pendingTrades) {
		this.pendingTrades = pendingTrades;
	}

	public Boolean getHasPending() {
		return hasPending;
	}

	public void setHasPending(Boolean hasPending) {
		this.hasPending = hasPending;
	}

	public int getPendingNum() {
		return pendingNum;
	}

	public void setPendingNum(int pendingNum) {
		this.pendingNum = pendingNum;
	}

	public List<PostEO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostEO> posts) {
		this.posts = posts;
	}

}
