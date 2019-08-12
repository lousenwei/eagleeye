package com.eagleeye.statistics.controller.staffDtl;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.dtl.StatStaffAvgWaitTimeDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffAvgWaitTimeDtlResultMO;

@ManagedBean(name = "statStaffAvgWaitTimeDtl")
@ViewScoped
public class StatStaffAvgWaitTimeDtlController extends AbstractStatStaffDtlController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 25214054301552836L;

	CartesianChartModel statStaffAvgWaitTimeDtlChartModel;

	/******************** 店铺工作量数据结构 ********************/
	StatStaffAvgWaitTimeDtlResultMO avgWaitTimeDtlMo = new StatStaffAvgWaitTimeDtlResultMO();

	public StatStaffAvgWaitTimeDtlController() {
		super();
		statStaffAvgWaitTimeDtlChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statStaffAvgWaitTimeDtlChartModel.addSeries(boys);
	}

	@Override
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.validQueryParams(queryParametersMo)) {
			return;
		}
		this.getStaffavgWaitTimeDtl();
		this.getStaffAvgWaitTimeLineChart();
	}

	public void getStaffavgWaitTimeDtl() {
		avgWaitTimeDtlMo = statAchievementBsh.getStatStaffAvgWaitTimeResultDtlMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime(), queryParametersMo.getStaffId());
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStaffAvgWaitTimeLineChart() {
		if (avgWaitTimeDtlMo != null && avgWaitTimeDtlMo.getAvgWaitTimeMoList() != null
				&& !avgWaitTimeDtlMo.getAvgWaitTimeMoList().isEmpty()) {
			statStaffAvgWaitTimeDtlChartModel.clear();
			ChartSeries lineSeries = new ChartSeries();
			lineSeries.setLabel(queryParametersMo.getStaffId());
			for (StatStaffAvgWaitTimeDtlMO temp : avgWaitTimeDtlMo.getAvgWaitTimeMoList()) {
				lineSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getAvgWaitTime());
			}
			statStaffAvgWaitTimeDtlChartModel.addSeries(lineSeries);
		}
	}

	public CartesianChartModel getStatStaffAvgWaitTimeDtlChartModel() {
		return statStaffAvgWaitTimeDtlChartModel;
	}

	public void setStatStaffAvgWaitTimeDtlChartModel(CartesianChartModel statStaffAvgWaitTimeDtlChartModel) {
		this.statStaffAvgWaitTimeDtlChartModel = statStaffAvgWaitTimeDtlChartModel;
	}

	public StatStaffAvgWaitTimeDtlResultMO getAvgWaitTimeDtlMo() {
		return avgWaitTimeDtlMo;
	}

	public void setAvgWaitTimeDtlMo(StatStaffAvgWaitTimeDtlResultMO avgWaitTimeDtlMo) {
		this.avgWaitTimeDtlMo = avgWaitTimeDtlMo;
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服帐号";
		case 1:
			return "统计日期";
		case 2:
			return "首次响应时间（秒）";
		}
		return null;
	}
}
