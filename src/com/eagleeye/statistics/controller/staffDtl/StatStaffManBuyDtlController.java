package com.eagleeye.statistics.controller.staffDtl;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.dtl.StatStaffManBuyDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffManBuyDtlResultMO;

@ManagedBean(name = "statStaffManBuyDtl")
@ViewScoped
public class StatStaffManBuyDtlController extends AbstractStatStaffDtlController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3715620858191733866L;

	CartesianChartModel statStaffManBuyDtlChartModel;

	/******************** 店铺工作量数据结构 ********************/
	StatStaffManBuyDtlResultMO manBuyDtlMo = new StatStaffManBuyDtlResultMO();

	public StatStaffManBuyDtlController() {
		super();
		statStaffManBuyDtlChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statStaffManBuyDtlChartModel.addSeries(boys);
	}

	@Override
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.validQueryParams(queryParametersMo)) {
			return;
		}
		this.getStaffManBuyDtl();
		this.getStaffManBuyLineChart();
	}

	public void getStaffManBuyDtl() {
		manBuyDtlMo = statAchievementBsh.getStatStaffManBuyDtlResultMO(managerId, queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime(), queryParametersMo.getStaffId());
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStaffManBuyLineChart() {
		if (manBuyDtlMo != null && manBuyDtlMo.getStatStaffManBuyMoList() != null
				&& !manBuyDtlMo.getStatStaffManBuyMoList().isEmpty()) {
			statStaffManBuyDtlChartModel.clear();
			ChartSeries lineSeries = new ChartSeries();
			lineSeries.setLabel(queryParametersMo.getStaffId());
			for (StatStaffManBuyDtlMO temp : manBuyDtlMo.getStatStaffManBuyMoList()) {
				lineSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getManBuyAmount());
			}
			statStaffManBuyDtlChartModel.addSeries(lineSeries);
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
			return "客单价（元）";
		}
		return null;
	}

	public CartesianChartModel getStatStaffManBuyDtlChartModel() {
		return statStaffManBuyDtlChartModel;
	}

	public void setStatStaffManBuyDtlChartModel(CartesianChartModel statStaffManBuyDtlChartModel) {
		this.statStaffManBuyDtlChartModel = statStaffManBuyDtlChartModel;
	}

	public StatStaffManBuyDtlResultMO getManBuyDtlMo() {
		return manBuyDtlMo;
	}

	public void setManBuyDtlMo(StatStaffManBuyDtlResultMO manBuyDtlMo) {
		this.manBuyDtlMo = manBuyDtlMo;
	}

}
