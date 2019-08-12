package com.eagleeye.statistics.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.ReturnMo;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.bsh.IChatLogBsh;
import com.eagleeye.eservice.bsh.IChatPeersBsh;
import com.eagleeye.eservice.bsh.ITradeRateBsh;
import com.eagleeye.eservice.eo.TradeRateEO;
import com.eagleeye.eservice.mo.ChatPeerQueryParametersMO;
import com.eagleeye.eservice.mo.TradeRateQueryParametersMo;
import com.eagleeye.statistics.mo.staff.StatStaffTradeRateDetailMo;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.eo.GroupMemberEO;
import com.taobao.api.domain.MsgList;

@ManagedBean(name = "statStaffTradeRate")
@ViewScoped
public class StatTradeRateDetail {

	ReturnMo selectedChatDate;

	ReturnMo selectedStaffId;

	TradeRateEO selectedTradeRate;

	List<ReturnMo> chatDates = new ArrayList();

	List<ReturnMo> staffIds = new ArrayList();

	StatStaffTradeRateDetailMo statStaffTradeRateDetailMo;

	TradeRateQueryParametersMo queryMo = new TradeRateQueryParametersMo();

	ChatPeerQueryParametersMO mo = new ChatPeerQueryParametersMO();

	List<GroupMemberEO> members = new ArrayList();

	List<MsgList> chatMsgs = new ArrayList();

	String managerId;

	String sessionKey;

	IGroupMemberBsh groupMemberBsh;

	IChatPeersBsh chatPeersBsh;

	IChatLogBsh chatLogBsh;

	ITradeRateBsh tradeRateBsh;

	public StatTradeRateDetail() {
		super();
		// TODO Auto-generated constructor stub
		tradeRateBsh = (ITradeRateBsh) EagleEyeServiceLocator
				.getBean("tradeRateBsh");
		chatPeersBsh = (IChatPeersBsh) EagleEyeServiceLocator
				.getBean("chatPeersBsh");
		chatLogBsh = (IChatLogBsh) EagleEyeServiceLocator.getBean("chatLogBsh");
		groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator
				.getBean("groupMemberBsh");

		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		sessionKey = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		if (managerId != null) {
			queryMo.setManagerId(managerId);
		}
		Date endDate = DateUtil.getSimpleDate(new Date());
		Date startDate = DateUtil.getActualMinimumDate(endDate);
		queryMo.setStartCreated(startDate);
		queryMo.setEndCreated(endDate);
		statStaffTradeRateDetailMo = new StatStaffTradeRateDetailMo(queryMo);
		if (managerId != null) {
			members = groupMemberBsh.getGroupMemebersByManagerId(managerId);
		}
	}

	public void searchBadOrNeutralTradeRates() {
		if (queryMo.getStartCreated() != null
				&& queryMo.getEndCreated() != null) {
			if (!TaoBaoUtil.valideDateGap(queryMo.getStartCreated(),
					queryMo.getEndCreated())) {
				return;
			}
		}
		statStaffTradeRateDetailMo.setQueryMo(queryMo);
		statStaffTradeRateDetailMo.load(0, 10, null, null, null);
		chatDates = null;
		staffIds = null;
		chatMsgs = null;
		selectedTradeRate = null;
		selectedChatDate = null;
		selectedStaffId = null;
	}

	public void getChatPeerDates(SelectEvent ev) {
		selectedTradeRate = (TradeRateEO) ev.getObject();
		if (selectedTradeRate != null) {
			mo.setBuyNick(selectedTradeRate.getNick());
			mo.setManagerId(selectedTradeRate.getRatedNick());
			mo.setStart(DateUtil.getPreviousDay(selectedTradeRate.getCreated(),
					30));
			mo.setEnd(DateUtil.getNextDay(selectedTradeRate.getCreated(), 15));
			mo.setOrderBy(EagleConstants.ORDERBY_DESC);
			mo.setField("chat_date");
			chatDates = (List<ReturnMo>) chatPeersBsh
					.getDistinctRecordByBuyNickandTimePeriod(mo);
		}
		staffIds = null;
		chatMsgs = null;
		selectedChatDate = null;
		selectedStaffId = null;
	}

	public void getChatPeers(SelectEvent ev) {
		if (ev.getObject() != null) {
			selectedChatDate = (ReturnMo) ev.getObject();
			mo.setStart((Date) selectedChatDate.getData());
			mo.setEnd((Date) selectedChatDate.getData());
			mo.setField("service_staff_id");
			staffIds = (List<ReturnMo>) chatPeersBsh
					.getDistinctRecordByBuyNickandTimePeriod(mo);
		}
		selectedStaffId = null;
		chatMsgs = null;
	}

	public void getChatLogs(SelectEvent ev) {
		if (ev.getObject() != null) {
			selectedStaffId = (ReturnMo) ev.getObject();
			chatMsgs = chatLogBsh.getChatLogByStaffIdAndUidNew(
					(String) selectedStaffId.getData(),
					selectedTradeRate.getNick(),
					(Date) selectedChatDate.getData(),
					(Date) DateUtil.getDateRange(
							(Date) selectedChatDate.getData()).get("DATEEND"),
					sessionKey);
		}
	}

	public void updateTradeRateEO(RowEditEvent ev) {
		selectedTradeRate = (TradeRateEO) ev.getObject();
		if (selectedTradeRate != null) {
			tradeRateBsh.updateTradeRateEO(selectedTradeRate);
		}
	}

	public ReturnMo getSelectedChatDate() {
		return selectedChatDate;
	}

	public void setSelectedChatDate(ReturnMo selectedChatDate) {
		this.selectedChatDate = selectedChatDate;
	}

	public ReturnMo getSelectedStaffId() {
		return selectedStaffId;
	}

	public void setSelectedStaffId(ReturnMo selectedStaffId) {
		this.selectedStaffId = selectedStaffId;
	}

	public List<ReturnMo> getChatDates() {
		return chatDates;
	}

	public void setChatDates(List<ReturnMo> chatDates) {
		this.chatDates = chatDates;
	}

	public List<ReturnMo> getStaffIds() {
		return staffIds;
	}

	public void setStaffIds(List<ReturnMo> staffIds) {
		this.staffIds = staffIds;
	}

	public TradeRateEO getSelectedTradeRate() {
		return selectedTradeRate;
	}

	public void setSelectedTradeRate(TradeRateEO selectedTradeRate) {
		this.selectedTradeRate = selectedTradeRate;
	}

	public TradeRateQueryParametersMo getQueryMo() {
		return queryMo;
	}

	public void setQueryMo(TradeRateQueryParametersMo queryMo) {
		this.queryMo = queryMo;
	}

	public StatStaffTradeRateDetailMo getStatStaffTradeRateDetailMo() {
		return statStaffTradeRateDetailMo;
	}

	public void setStatStaffTradeRateDetailMo(
			StatStaffTradeRateDetailMo statStaffTradeRateDetailMo) {
		this.statStaffTradeRateDetailMo = statStaffTradeRateDetailMo;
	}

	public List<MsgList> getChatMsgs() {
		return chatMsgs;
	}

	public void setChatMsgs(List<MsgList> chatMsgs) {
		this.chatMsgs = chatMsgs;
	}

	public List<GroupMemberEO> getMembers() {
		return members;
	}

	public void setMembers(List<GroupMemberEO> members) {
		this.members = members;
	}

}
