package com.eagleeye.statistics.controller.staff;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.statistics.mo.staff.StatStaffRefundRateMO;
import com.eagleeye.statistics.mo.staff.StatStaffRefundRateResultMO;

@ManagedBean(name = "statStaffRefundRate")
@ViewScoped
public class StatStaffRefundRateController extends AbstractStatStaffController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3043228059029769298L;

	CartesianChartModel statChartModel;

	/******************** 客服退款金率数据结构 ********************/
	StatStaffRefundRateResultMO statStaffRefundRateResultMO = new StatStaffRefundRateResultMO();

	public StatStaffRefundRateController() {
		super();
		statChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statChartModel.addSeries(boys);
	}

	/**
	 * 得到客服退款金额率数据结构
	 */
	public void getStatStaffRefundRate() {
		statStaffRefundRateResultMO = statAchievementBsh.getStatStaffRefundRateResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服退款金额率图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffRefundRate();
		if (statStaffRefundRateResultMO != null && statStaffRefundRateResultMO.getRefundRateMoList() != null
				&& !statStaffRefundRateResultMO.getRefundRateMoList().isEmpty()) {
			ChartSeries staffSeries = new ChartSeries();
			statChartModel.clear();
			staffSeries.setLabel("客服退款金率");
			for (StatStaffRefundRateMO temp : statStaffRefundRateResultMO.getRefundRateMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getRefundRate());
			}
			statChartModel.addSeries(staffSeries);
		}
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服ID";
		case 1:
			return "退款金额率（%）";
		}
		return null;
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public StatStaffRefundRateResultMO getStatStaffRefundRateResultMO() {
		return statStaffRefundRateResultMO;
	}

	public void setStatStaffRefundRateResultMO(StatStaffRefundRateResultMO statStaffRefundRateResultMO) {
		this.statStaffRefundRateResultMO = statStaffRefundRateResultMO;
	}

}
