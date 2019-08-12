package com.eagleeye.statistics.controller.staffDtl;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.dtl.StatStaffWorkLoadDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffWorkLoadDtlResultMO;

@ManagedBean(name = "statStaffWorkLoadDtl")
@ViewScoped
public class StatStaffWorkLoadDtlController extends AbstractStatStaffDtlController implements Serializable {

	private static final long serialVersionUID = 8400159622374508106L;

	CartesianChartModel statStaffWorkLoadDtlChartModel;

	/******************** 店铺工作量数据结构 ********************/
	StatStaffWorkLoadDtlResultMO workLoadDtlMo = new StatStaffWorkLoadDtlResultMO();

	public StatStaffWorkLoadDtlController() {
		super();
		statStaffWorkLoadDtlChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statStaffWorkLoadDtlChartModel.addSeries(boys);
	}

	@Override
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.validQueryParams(queryParametersMo)) {
			return;
		}
		this.getStaffWorkLoadDtl();
		this.getStaffWorkLoadLineChart();
	}

	public void getStaffWorkLoadDtl() {
		workLoadDtlMo = statAchievementBsh.getStatStaffWorkLoadDtlResultMO(managerId, queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime(), queryParametersMo.getStaffId());
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStaffWorkLoadLineChart() {
		if (workLoadDtlMo != null && workLoadDtlMo.getWorkLoadMoList() != null
				&& !workLoadDtlMo.getWorkLoadMoList().isEmpty()) {
			statStaffWorkLoadDtlChartModel.clear();
			ChartSeries lineSeries = new ChartSeries();
			lineSeries.setLabel(queryParametersMo.getStaffId());
			for (StatStaffWorkLoadDtlMO temp : workLoadDtlMo.getWorkLoadMoList()) {
				lineSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getWorkLoad());
			}
			statStaffWorkLoadDtlChartModel.addSeries(lineSeries);
		}
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服帐号";
		case 1:
			return "统计日期";
		case 2:
			return "工作量（人次）";
		}
		return null;
	}

	public CartesianChartModel getStatStaffWorkLoadDtlChartModel() {
		return statStaffWorkLoadDtlChartModel;
	}

	public void setStatStaffWorkLoadDtlChartModel(CartesianChartModel statStaffWorkLoadDtlChartModel) {
		this.statStaffWorkLoadDtlChartModel = statStaffWorkLoadDtlChartModel;
	}

	public StatStaffWorkLoadDtlResultMO getWorkLoadDtlMo() {
		return workLoadDtlMo;
	}

	public void setWorkLoadDtlMo(StatStaffWorkLoadDtlResultMO workLoadDtlMo) {
		this.workLoadDtlMo = workLoadDtlMo;
	}

}
