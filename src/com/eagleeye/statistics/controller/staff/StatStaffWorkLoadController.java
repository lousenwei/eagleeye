package com.eagleeye.statistics.controller.staff;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.statistics.mo.staff.StatStaffWorkLoadMO;
import com.eagleeye.statistics.mo.staff.StatStaffWorkLoadResultMO;

@ManagedBean(name = "statStaffWorkLoad")
@ViewScoped
public class StatStaffWorkLoadController extends AbstractStatStaffController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4093983779298511354L;

	CartesianChartModel statChartModel;

	PieChartModel statOwnersChartModel;

	/******************** 客服工作量数据结构 ********************/
	StatStaffWorkLoadResultMO statStaffWorkLoadResultMO = new StatStaffWorkLoadResultMO();

	public StatStaffWorkLoadController() {
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
	 * 得到客服工作量数据结构
	 */
	public void getStatStaffWorkLoad() {
		statStaffWorkLoadResultMO = statAchievementBsh.getStatStaffWorkLoadResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStatStaffWorkLoadPieChart() {

		if (statStaffWorkLoadResultMO != null && statStaffWorkLoadResultMO.getWorkLoadMoList() != null
				&& !statStaffWorkLoadResultMO.getWorkLoadMoList().isEmpty()) {
			statOwnersChartModel.clear();
			for (StatStaffWorkLoadMO temp : statStaffWorkLoadResultMO.getWorkLoadMoList()) {
				statOwnersChartModel.set(temp.getStaffId(), temp.getSumWorkLoad());
			}
		}
	}

	/**
	 * 得到客服工作量柱状图
	 */
	public void getStatStaffWorkLoadBarChart() {
		if (statStaffWorkLoadResultMO != null && statStaffWorkLoadResultMO.getWorkLoadMoList() != null
				&& !statStaffWorkLoadResultMO.getWorkLoadMoList().isEmpty()) {
			statChartModel.clear();
			ChartSeries staffSeries = new ChartSeries();
			staffSeries.setLabel("平均工作量");
			for (StatStaffWorkLoadMO temp : statStaffWorkLoadResultMO.getWorkLoadMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getAvgWorkLoad());
			}
			statChartModel.addSeries(staffSeries);
		}
	}

	/**
	 * 得到客服工作量图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffWorkLoad();
		this.getStatStaffWorkLoadPieChart();
		this.getStatStaffWorkLoadBarChart();
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服ID";
		case 1:
			return "总工作量（人次）";
		case 2:
			return "平均工作量（人次）";
		}
		return null;
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public PieChartModel getStatOwnersChartModel() {
		return statOwnersChartModel;
	}

	public void setStatOwnersChartModel(PieChartModel statOwnersChartModel) {
		this.statOwnersChartModel = statOwnersChartModel;
	}

	public StatStaffWorkLoadResultMO getStatStaffWorkLoadResultMO() {
		return statStaffWorkLoadResultMO;
	}

	public void setStatStaffWorkLoadResultMO(StatStaffWorkLoadResultMO statStaffWorkLoadResultMO) {
		this.statStaffWorkLoadResultMO = statStaffWorkLoadResultMO;
	}

}
