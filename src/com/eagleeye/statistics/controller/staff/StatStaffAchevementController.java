package com.eagleeye.statistics.controller.staff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.QueryParametersMo;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.mo.shop.StatAchievementResultMO;
import com.eagleeye.user.eo.GroupMemberEO;

@ManagedBean(name = "statStaffAcheve")
@ViewScoped
public class StatStaffAchevementController extends AbstractStatStaffController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4545822945961312505L;

	PieChartModel statOwnersChartModel;

	CartesianChartModel statChartModel;

	List<StatAchievementEO> statOwnersList;

	List<StatAchievementResultMO> statResultMos = new ArrayList();

	/******************** 客服业绩指标数据结构 ********************/
	StatAchievementResultMO statResultMo = new StatAchievementResultMO();

	/**
	 * 选中的客服业绩数据
	 */
	StatAchievementResultMO selectStatResultMo = new StatAchievementResultMO();

	/**
	 * 得到客服业绩数据结构
	 */
	public void getStatOwnersResultMOs() {
		statResultMos = statAchievementBsh.getStatOwnersSumResultMOs(null, managerId,
				(queryParametersMo.getIsTotal() == null ? false : queryParametersMo.getIsTotal()),
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
		statResultMo = statAchievementBsh.getStatAchievementSumResultMO(null, managerId,
				(queryParametersMo.getIsTotal() == null ? false : queryParametersMo.getIsTotal()),
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 选择客服业绩指标数据联动
	 * 
	 * @param ev
	 */
	public void onRowSelect(SelectEvent ev) {

		selectStatResultMo = (StatAchievementResultMO) ev.getObject();
		// 选择交易下订单
		if (selectStatResultMo != null) {
			statOwnersList = statAchievementBsh.getStaffStatAchievementEOsById(selectStatResultMo.getManagerId(),
					queryParametersMo.getStartTime(), queryParametersMo.getEndTime(), false,
					selectStatResultMo.getStaffId());
		}
	}

	/**
	 * 初始化类和图表
	 */
	public StatStaffAchevementController() {
		super();
		statOwnersChartModel = new PieChartModel();
		statOwnersChartModel.set(EagleConstants.CHART_TITLE, 100);
		statChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statChartModel.addSeries(boys);
		this.loadData();
	}

	/**
	 * 得到客服业绩饼图
	 */
	public void getStatStaffPieChart() {
		if (statResultMos != null && !statResultMos.isEmpty()) {
			statOwnersChartModel.clear();
			for (StatAchievementResultMO temp : statResultMos) {
				statOwnersChartModel.set(temp.getStaffId(), temp.getTotalAmount());
			}
		}
	}

	/**
	 * 得到客服业绩柱状图
	 */
	public void getStatStaffBarChart() {
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		if (statResultMos != null && !statResultMos.isEmpty()) {
			statChartModel.clear();
			for (StatAchievementResultMO temp : statResultMos) {
				ChartSeries barSeries = new ChartSeries();
				barSeries.setLabel(temp.getStaffId());
				barSeries.set("全部退款", temp.getAmountAllRefund());
				barSeries.set("部分退款", temp.getAmountPartRefund());
				barSeries.set("确认付款", temp.getAmountSuccess());
				barSeries.set("等待确认", temp.getAmountWaitConfirm());
				barSeries.set("等待发货", temp.getAmountWaitSend());
				barSeries.set("运费", temp.getPostFee());
				barSeries.set("实际盈亏", temp.getTotalAmount());
				statChartModel.addSeries(barSeries);
			}
		}
	}

	/**
	 * 得到客服业绩图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatOwnersResultMOs();
		this.getStatStaffPieChart();
		this.getStatStaffBarChart();
		selectStatResultMo = null;
		statOwnersList = null;
	}

	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服帐号";
		case 1:
			return "等待发货（元）";
		case 2:
			return "等待确认（元）";
		case 3:
			return "交易完成（元）";
		case 4:
			return "退款中（元）";
		case 5:
			return "全额退款（元）";
		case 6:
			return "部分退款（元）";
		case 7:
			return "运费（元）";
		case 8:
			return "实际盈亏（不含退款）（元）";
		}
		return null;
	}

	public QueryParametersMo getQueryParametersMo() {
		return queryParametersMo;
	}

	public void setQueryParametersMo(QueryParametersMo queryParametersMo) {
		this.queryParametersMo = queryParametersMo;
	}

	public List<StatAchievementEO> getStatOwnersList() {
		return statOwnersList;
	}

	public void setStatOwnersList(List<StatAchievementEO> statOwnersList) {
		this.statOwnersList = statOwnersList;
	}

	public List<StatAchievementResultMO> getStatResultMos() {
		return statResultMos;
	}

	public void setStatResultMos(List<StatAchievementResultMO> statResultMos) {
		this.statResultMos = statResultMos;
	}

	public StatAchievementResultMO getStatResultMo() {
		return statResultMo;
	}

	public void setStatResultMo(StatAchievementResultMO statResultMo) {
		this.statResultMo = statResultMo;
	}

	public PieChartModel getStatOwnersChartModel() {
		return statOwnersChartModel;
	}

	public void setStatOwnersChartModel(PieChartModel statOwnersChartModel) {
		this.statOwnersChartModel = statOwnersChartModel;
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public List<GroupMemberEO> getMembers() {
		return members;
	}

	public void setMembers(List<GroupMemberEO> members) {
		this.members = members;
	}

	public StatAchievementResultMO getSelectStatResultMo() {
		return selectStatResultMo;
	}

	public void setSelectStatResultMo(StatAchievementResultMO selectStatResultMo) {
		this.selectStatResultMo = selectStatResultMo;
	}

}
