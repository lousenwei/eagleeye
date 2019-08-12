package com.eagleeye.statistics.controller.staff;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.statistics.mo.staff.StatStaffOnlineTimeMO;
import com.eagleeye.statistics.mo.staff.StatStaffOnlineTimeResultMO;

@ManagedBean(name = "statStaffOnlineTime")
@ViewScoped
public class StatStaffOnlineTimeController extends AbstractStatStaffController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5139969881898997223L;

	CartesianChartModel statChartModel;

	/******************** 客服在线时间数据结构 ********************/
	StatStaffOnlineTimeResultMO statStaffOnlineTimeResultMo = new StatStaffOnlineTimeResultMO();

	public StatStaffOnlineTimeController() {
		super();
		statChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statChartModel.addSeries(boys);
	}

	/**
	 * 得到客服首次响应时间数据结构
	 */
	public void getStatStaffOnlineTime() {
		statStaffOnlineTimeResultMo = statAchievementBsh.getStatStaffOnlineTimeResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服首次响应时间图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffOnlineTime();
		if (statStaffOnlineTimeResultMo != null && statStaffOnlineTimeResultMo.getAvgOnlineMoList() != null
				&& !statStaffOnlineTimeResultMo.getAvgOnlineMoList().isEmpty()) {
			ChartSeries staffAvgOnlineSeries = new ChartSeries();
			ChartSeries staffSumOnlineSeries = new ChartSeries();
			ChartSeries staffSumOnlineTimeForEightHoursSeries = new ChartSeries();
			statChartModel.clear();
			staffAvgOnlineSeries.setLabel("平均在线时间");
			staffSumOnlineSeries.setLabel("总在线时间");
			staffSumOnlineTimeForEightHoursSeries.setLabel("总在线天数");

			for (StatStaffOnlineTimeMO temp : statStaffOnlineTimeResultMo.getAvgOnlineMoList()) {
				staffAvgOnlineSeries.set(temp.getStaffId(), temp.getAvgOnlineTime());
				staffSumOnlineSeries.set(temp.getStaffId(), temp.getSumOnlineTime());
				staffSumOnlineTimeForEightHoursSeries.set(temp.getStaffId(), temp.getSumOnlineTimeForEightHours());
			}
			statChartModel.addSeries(staffAvgOnlineSeries);
			statChartModel.addSeries(staffSumOnlineSeries);
			statChartModel.addSeries(staffSumOnlineTimeForEightHoursSeries);
		}
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服ID";
		case 1:
			return "客服日均在线时间（小时）";
		case 2:
			return "客服总在线时间（小时）";
		case 3:
			return "客服总在线工数（工）";
		}
		return null;
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public StatStaffOnlineTimeResultMO getStatStaffOnlineTimeResultMo() {
		return statStaffOnlineTimeResultMo;
	}

	public void setStatStaffOnlineTimeResultMo(StatStaffOnlineTimeResultMO statStaffOnlineTimeResultMo) {
		this.statStaffOnlineTimeResultMo = statStaffOnlineTimeResultMo;
	}

}
