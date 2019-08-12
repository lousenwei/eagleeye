package com.eagleeye.statistics.controller.staffDtl;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.dtl.StatStaffExchangeRateDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffExchangeRateDtlResultMO;

@ManagedBean(name = "statStaffExchangeRateDtl")
@ViewScoped
public class StatStaffExchangeRateDtlController extends AbstractStatStaffDtlController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8665163220107105920L;

	CartesianChartModel statStaffExchangeRateDtlChartModel;

	StatStaffExchangeRateDtlResultMO exchangeRateDtlMo = new StatStaffExchangeRateDtlResultMO();

	public StatStaffExchangeRateDtlController() {
		super();
		statStaffExchangeRateDtlChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statStaffExchangeRateDtlChartModel.addSeries(boys);
	}

	@Override
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.validQueryParams(queryParametersMo)) {
			return;
		}
		this.getStaffExchangeRateDtl();
		this.getStaffExchangeRateLineChart();
	}

	public void getStaffExchangeRateDtl() {
		exchangeRateDtlMo = statAchievementBsh.getStatStaffExchangeRateDtlResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime(), queryParametersMo.getStaffId());
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStaffExchangeRateLineChart() {
		if (exchangeRateDtlMo != null && exchangeRateDtlMo.getExchangeRateMoList() != null
				&& !exchangeRateDtlMo.getExchangeRateMoList().isEmpty()) {
			statStaffExchangeRateDtlChartModel.clear();
			ChartSeries lineSeries = new ChartSeries();
			lineSeries.setLabel(queryParametersMo.getStaffId());
			for (StatStaffExchangeRateDtlMO temp : exchangeRateDtlMo.getExchangeRateMoList()) {
				lineSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getExchangeRate());
			}
			statStaffExchangeRateDtlChartModel.addSeries(lineSeries);
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
			return "付费人数（人次）";
		case 3:
			return "接待人数（人次）";
		case 4:
			return "客服转换率（%）";
		}
		return null;
	}

	public CartesianChartModel getStatStaffExchangeRateDtlChartModel() {
		return statStaffExchangeRateDtlChartModel;
	}

	public void setStatStaffExchangeRateDtlChartModel(CartesianChartModel statStaffExchangeRateDtlChartModel) {
		this.statStaffExchangeRateDtlChartModel = statStaffExchangeRateDtlChartModel;
	}

	public StatStaffExchangeRateDtlResultMO getExchangeRateDtlMo() {
		return exchangeRateDtlMo;
	}

	public void setExchangeRateDtlMo(StatStaffExchangeRateDtlResultMO exchangeRateDtlMo) {
		this.exchangeRateDtlMo = exchangeRateDtlMo;
	}

}
