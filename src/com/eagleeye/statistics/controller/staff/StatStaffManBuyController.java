package com.eagleeye.statistics.controller.staff;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.statistics.mo.staff.StatStaffManBuyMO;
import com.eagleeye.statistics.mo.staff.StatStaffManBuyResultMO;

@ManagedBean(name = "statStaffManBuy")
@ViewScoped
public class StatStaffManBuyController extends AbstractStatStaffController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8200567092604629474L;

	CartesianChartModel statChartModel;

	/******************** 客服客单价数据结构 ********************/
	StatStaffManBuyResultMO statStaffManBuyResultMO = new StatStaffManBuyResultMO();

	public StatStaffManBuyController() {
		super();
		statChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statChartModel.addSeries(boys);
	}

	/**
	 * 得到客服的客单价数据结构
	 */
	public void getStatStaffManBuy() {
		statStaffManBuyResultMO = statAchievementBsh.getStatStaffManBuyResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服的客单价图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffManBuy();
		if (statStaffManBuyResultMO != null && statStaffManBuyResultMO.getStatStaffManBuyMoList() != null
				&& !statStaffManBuyResultMO.getStatStaffManBuyMoList().isEmpty()) {
			ChartSeries staffSeries = new ChartSeries();
			statChartModel.clear();
			staffSeries.setLabel("客服客单价");
			for (StatStaffManBuyMO temp : statStaffManBuyResultMO.getStatStaffManBuyMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getManBuyAmount());
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
			return "客单价（元）";
		}
		return null;
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public StatStaffManBuyResultMO getStatStaffManBuyResultMO() {
		return statStaffManBuyResultMO;
	}

	public void setStatStaffManBuyResultMO(StatStaffManBuyResultMO statStaffManBuyResultMO) {
		this.statStaffManBuyResultMO = statStaffManBuyResultMO;
	}

}
