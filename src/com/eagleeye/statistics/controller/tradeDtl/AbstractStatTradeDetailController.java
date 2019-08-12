package com.eagleeye.statistics.controller.tradeDtl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.Messages;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.bsh.IEServiceBsh;
import com.eagleeye.eservice.bsh.ITradeSoldBsh;
import com.eagleeye.eservice.eo.OrderEO;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.mo.TradeQueryParametersMo;
import com.eagleeye.statistics.mo.staff.StatStaffTradeDetailMo;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.eo.GroupMemberEO;

public abstract class AbstractStatTradeDetailController {

	private IGroupMemberBsh groupMemberBsh;

	private ITradeSoldBsh tradeSoldBsh;

	TradeQueryParametersMo queryParametersMo = new TradeQueryParametersMo();

	List<GroupMemberEO> members = new ArrayList();

	StatStaffTradeDetailMo statStaffTradeDetailMo;

	List<OrderEO> relatedOrders = new ArrayList();

	TradeEO selectedTrade;

	IEServiceBsh eServiceBsh;

	String managerId;

	String shopId;

	List<TradeEO> refreshTradeReport = new ArrayList();

	public AbstractStatTradeDetailController() {
		super();
		// TODO Auto-generated constructor stub
		groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator.getBean("groupMemberBsh");
		tradeSoldBsh = (ITradeSoldBsh) EagleEyeServiceLocator.getBean("tradeSoldBsh");
		eServiceBsh = (IEServiceBsh) EagleEyeServiceLocator.getBean("eServiceBsh");
		managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		shopId = (String) SessionManager.getSessionByKey(EagleConstants.TOPSHOPID);
		if (managerId != null) {
			queryParametersMo.setStaffId(managerId);
			members = groupMemberBsh.getGroupMemebersByManagerId(managerId);
		} else {
			queryParametersMo.setStaffId("test");
		}
		Date endDate = DateUtil.getSimpleDate(new Date());
		Date startDate = DateUtil.getActualMinimumDate(endDate);
		queryParametersMo.setStart(startDate);
		queryParametersMo.setEnd(endDate);
		queryParametersMo.setHasPayTime(true);

	}

	public void updateTradeEO(RowEditEvent ev) {
		selectedTrade = (TradeEO) ev.getObject();
		if (selectedTrade != null) {
			tradeSoldBsh.updateTradeEO(selectedTrade);
			refreshTradeReport.add(selectedTrade);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.getString("保存成功"), Messages
							.getString("亲，保存成功，如果要反应到报表，请刷新报表哦～")));
		}
	}

	protected void load(String status) {
		if (queryParametersMo.getStart() != null && queryParametersMo.getEnd() != null) {
			if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStart(), queryParametersMo.getStart())) {
				return;
			}
		}
		List tradeStatus = new ArrayList();
		tradeStatus.add(status);
		queryParametersMo.setStatus(tradeStatus);
		statStaffTradeDetailMo.setQueryMo(queryParametersMo);
		statStaffTradeDetailMo.load(0, 5, null, null, null);
	}

	public void runAllStatAchievementReport() {
		if (managerId != null && shopId != null && !refreshTradeReport.isEmpty()) {
			// 删除对应日期的统计数据
			Map<Date, Boolean> statDays = new HashMap();
			for (TradeEO trade : refreshTradeReport) {
				if (trade.getPayTime() != null) {
					Date statDay = DateUtil.getSimpleDate(trade.getPayTime());
					if (statDays.get(statDay) == null) {
						statDays.put(statDay, true);
					}
				}
			}
			if (statDays != null && !statDays.isEmpty()) {
				eServiceBsh.deleteTradeStatByDays(managerId, shopId, statDays.keySet());

				eServiceBsh.runPersonalStatAchievementReportByDate(managerId, statDays.keySet(), shopId);
				eServiceBsh.runShopStatAchievementReportByDate(managerId, statDays.keySet(), shopId);
			}
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.getString("good_setting_success_brief.0"),
							Messages.getString("good_setting_success_detail.0")));
		} else if (managerId != null && shopId != null && refreshTradeReport.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, Messages.getString("good_setting_warning_brief.0"),
							Messages.getString("good_setting_warning_detail.0")));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getString("good_setting_warning_brief.1"),
							Messages.getString("good_setting_warning_detail.1")));
		}
		refreshTradeReport.clear();
	}

	public TradeQueryParametersMo getQueryParametersMo() {
		return queryParametersMo;
	}

	public void setQueryParametersMo(TradeQueryParametersMo queryParametersMo) {
		this.queryParametersMo = queryParametersMo;
	}

	public StatStaffTradeDetailMo getStatStaffTradeDetailMo() {
		return statStaffTradeDetailMo;
	}

	public void setStatStaffTradeDetailMo(StatStaffTradeDetailMo statStaffTradeDetailMo) {
		this.statStaffTradeDetailMo = statStaffTradeDetailMo;
	}

	/**
	 * 选择联动
	 * 
	 * @param ev
	 */
	public void onRowSelect(SelectEvent ev) {

		selectedTrade = (TradeEO) ev.getObject();
		// 选择交易下订单
		if (selectedTrade != null) {
			relatedOrders = tradeSoldBsh.getOrdersByTradeId(selectedTrade.getTid());
		}
	}

	public TradeEO getSelectedTrade() {
		return selectedTrade;
	}

	public void setSelectedTrade(TradeEO selectedTrade) {
		this.selectedTrade = selectedTrade;
	}

	public List<GroupMemberEO> getMembers() {
		return members;
	}

	public void setMembers(List<GroupMemberEO> members) {
		this.members = members;
	}

	public List<OrderEO> getRelatedOrders() {
		return relatedOrders;
	}

	public void setRelatedOrders(List<OrderEO> relatedOrders) {
		this.relatedOrders = relatedOrders;
	}

}
