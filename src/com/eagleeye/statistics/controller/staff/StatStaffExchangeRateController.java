package com.eagleeye.statistics.controller.staff;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.statistics.mo.staff.StatStaffExchangeRateMO;
import com.eagleeye.statistics.mo.staff.StatStaffExchangeRateResultMO;

@ManagedBean(name = "statStaffExchangeRate")
@ViewScoped
public class StatStaffExchangeRateController extends AbstractStatStaffController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6227984883784840985L;

	CartesianChartModel statChartModel;

	/******************** 客服转换率数据结构 ********************/
	StatStaffExchangeRateResultMO statStaffExchangeRateResultMo = new StatStaffExchangeRateResultMO();

	public StatStaffExchangeRateController() {
		super();
		statChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statChartModel.addSeries(boys);
	}

	/**
	 * 得到客服转换率数据结构
	 */
	public void getStatStaffExchangeRate() {
		statStaffExchangeRateResultMo = statAchievementBsh.getStatStaffExchangeRateResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服转换率图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffExchangeRate();
		if (statStaffExchangeRateResultMo != null && statStaffExchangeRateResultMo.getExchangeRateMoList() != null
				&& !statStaffExchangeRateResultMo.getExchangeRateMoList().isEmpty()) {
			ChartSeries staffSeries = new ChartSeries();
			statChartModel.clear();
			staffSeries.setLabel("客服转换率");
			for (StatStaffExchangeRateMO temp : statStaffExchangeRateResultMo.getExchangeRateMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getExchangeRate());
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
			return "转换率（%）";
		}
		return null;
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public StatStaffExchangeRateResultMO getStatStaffExchangeRateResultMo() {
		return statStaffExchangeRateResultMo;
	}

	public void setStatStaffExchangeRateResultMo(StatStaffExchangeRateResultMO statStaffExchangeRateResultMo) {
		this.statStaffExchangeRateResultMo = statStaffExchangeRateResultMo;
	}

}
