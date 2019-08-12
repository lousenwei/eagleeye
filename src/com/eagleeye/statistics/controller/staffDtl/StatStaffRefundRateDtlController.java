package com.eagleeye.statistics.controller.staffDtl;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.dtl.StatStaffExchangeRateDtlResultMO;
import com.eagleeye.statistics.mo.dtl.StatStaffRefundRateDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffRefundRateDtlResultMO;

@ManagedBean(name = "statStaffRefundRateDtl")
@ViewScoped
public class StatStaffRefundRateDtlController extends AbstractStatStaffDtlController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8918132116911316422L;

	CartesianChartModel statStaffRefundRateDtlChartModel;

	StatStaffRefundRateDtlResultMO refundRateDtlMo = new StatStaffRefundRateDtlResultMO();

	public StatStaffRefundRateDtlController() {
		super();
		statStaffRefundRateDtlChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statStaffRefundRateDtlChartModel.addSeries(boys);
	}

	@Override
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.validQueryParams(queryParametersMo)) {
			return;
		}
		this.getStaffRefundRateDtl();
		this.getStaffRefundRateLineChart();
	}

	public void getStaffRefundRateDtl() {
		refundRateDtlMo = statAchievementBsh.getStatStaffRefundRateDtlResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime(), queryParametersMo.getStaffId());
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStaffRefundRateLineChart() {
		if (refundRateDtlMo != null && refundRateDtlMo.getRefundRateMoList() != null
				&& !refundRateDtlMo.getRefundRateMoList().isEmpty()) {
			statStaffRefundRateDtlChartModel.clear();
			ChartSeries lineSeries = new ChartSeries();
			lineSeries.setLabel(queryParametersMo.getStaffId());
			for (StatStaffRefundRateDtlMO temp : refundRateDtlMo.getRefundRateMoList()) {
				lineSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getRefundRate());
			}
			statStaffRefundRateDtlChartModel.addSeries(lineSeries);
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
			return "退款金额（元）";
		case 3:
			return "总金额（元）";
		case 4:
			return "退款金额率（%）";
		}
		return null;
	}

	public CartesianChartModel getStatStaffRefundRateDtlChartModel() {
		return statStaffRefundRateDtlChartModel;
	}

	public void setStatStaffRefundRateDtlChartModel(CartesianChartModel statStaffRefundRateDtlChartModel) {
		this.statStaffRefundRateDtlChartModel = statStaffRefundRateDtlChartModel;
	}

	public StatStaffRefundRateDtlResultMO getRefundRateDtlMo() {
		return refundRateDtlMo;
	}

	public void setRefundRateDtlMo(StatStaffRefundRateDtlResultMO refundRateDtlMo) {
		this.refundRateDtlMo = refundRateDtlMo;
	}

}
