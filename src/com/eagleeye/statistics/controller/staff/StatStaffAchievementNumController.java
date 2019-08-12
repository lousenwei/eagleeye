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
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.statistics.eo.StatAchievementNumEO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumMO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumResultMO;

@ManagedBean(name = "statStaffAchNum")
@ViewScoped
public class StatStaffAchievementNumController extends AbstractStatStaffController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1263170610649707700L;

	PieChartModel statOwnersChartModel;

	CartesianChartModel statChartModel;

	/******************** 客服交易数量统计数据结构 ********************/
	StatStaffTradeNumResultMO statStaffTradeNumResultMo = new StatStaffTradeNumResultMO();

	StatStaffTradeNumMO selectStatResultMo = new StatStaffTradeNumMO();

	List<StatAchievementNumEO> statStaffDtlList = new ArrayList();

	/**
	 * 初始化类和图表
	 */
	public StatStaffAchievementNumController() {
		super();
		statOwnersChartModel = new PieChartModel();
		statOwnersChartModel.set(EagleConstants.CHART_TITLE, 100);
		statChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statChartModel.addSeries(boys);
	}

	/**
	 * 选择客服业绩指标数据联动
	 * 
	 * @param ev
	 */
	public void onRowSelect(SelectEvent ev) {

		selectStatResultMo = (StatStaffTradeNumMO) ev.getObject();
		// 选择交易下订单
		if (selectStatResultMo != null) {
			statStaffDtlList = statAchievementBsh.getStaffStatAchievementNumEOsById(managerId,
					queryParametersMo.getStartTime(), queryParametersMo.getEndTime(), false,
					selectStatResultMo.getStaffId());
		}
	}

	/**
	 * 得到客服交易数目数据结构
	 */
	public void getStatOwnersNumResultMOs() {
		statStaffTradeNumResultMo = statAchievementBsh.getStatStaffTradeNumResultMO(managerId, false,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服交易数目柱状图
	 */
	public void getStatStaffNumBarChart() {
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		if (statStaffTradeNumResultMo != null && statStaffTradeNumResultMo.getStatStaffTradeNumMoList() != null
				&& !statStaffTradeNumResultMo.getStatStaffTradeNumMoList().isEmpty()) {
			statChartModel.clear();
			for (StatStaffTradeNumMO temp : statStaffTradeNumResultMo.getStatStaffTradeNumMoList()) {
				ChartSeries barSeries = new ChartSeries();
				barSeries.setLabel(temp.getStaffId());
				barSeries.set("等待确认", temp.getNumWaitConfirm());
				barSeries.set("等待发货", temp.getNumWaitSend());
				barSeries.set("确认付款", temp.getNumSuccess());
				barSeries.set("退款处理中", temp.getNumRefunding());
				barSeries.set("全部退款", temp.getNumAllRefund());
				barSeries.set("部分退款", temp.getNumPartRefund());
				barSeries.set("实际数目", temp.getTotalNumNoRefund());
				barSeries.set("所有数目", temp.getTotalNumHasRefund());
				statChartModel.addSeries(barSeries);
			}
		}
	}

	/**
	 * 得到客服交易数目图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatOwnersNumResultMOs();
		this.getStatStaffNumPieChart();
		this.getStatStaffNumBarChart();
	}

	/**
	 * 得到客服交易数目饼图
	 */
	public void getStatStaffNumPieChart() {
		if (statStaffTradeNumResultMo != null && statStaffTradeNumResultMo.getStatStaffTradeNumMoList() != null) {
			statOwnersChartModel.clear();
			for (StatStaffTradeNumMO temp : statStaffTradeNumResultMo.getStatStaffTradeNumMoList()) {
				statOwnersChartModel.set(temp.getStaffId(), temp.getTotalNumNoRefund());
			}
		}
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服帐号";
		case 1:
			return "等待发货";
		case 2:
			return "等待确认";
		case 3:
			return "交易完成";
		case 4:
			return "退款中";
		case 5:
			return "全额退款";
		case 6:
			return "部分退款";
		case 7:
			return "实际数量";
		case 8:
			return "所有数量(含退款)";
		}
		return null;
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

	public StatStaffTradeNumResultMO getStatStaffTradeNumResultMo() {
		return statStaffTradeNumResultMo;
	}

	public void setStatStaffTradeNumResultMo(StatStaffTradeNumResultMO statStaffTradeNumResultMo) {
		this.statStaffTradeNumResultMo = statStaffTradeNumResultMo;
	}

	public StatStaffTradeNumMO getSelectStatResultMo() {
		return selectStatResultMo;
	}

	public void setSelectStatResultMo(StatStaffTradeNumMO selectStatResultMo) {
		this.selectStatResultMo = selectStatResultMo;
	}

	public List<StatAchievementNumEO> getStatStaffDtlList() {
		return statStaffDtlList;
	}

	public void setStatStaffDtlList(List<StatAchievementNumEO> statStaffDtlList) {
		this.statStaffDtlList = statStaffDtlList;
	}

}
