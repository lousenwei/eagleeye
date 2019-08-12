package com.eagleeye.statistics.controller.staff;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.statistics.mo.staff.StatStaffAvgWaitTimeMO;
import com.eagleeye.statistics.mo.staff.StatStaffAvgWaitTimeResultMO;

@ManagedBean(name = "statStaffAvgWait")
@ViewScoped
public class StatStaffAvgWaitTimeController extends AbstractStatStaffController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2628066564515061546L;

	CartesianChartModel statChartModel;
	/******************** 客服首次响应时间率数据结构 ********************/
	StatStaffAvgWaitTimeResultMO statStaffAvgWaitTimeResultMo = new StatStaffAvgWaitTimeResultMO();

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服ID";
		case 1:
			return "客户平均等待时间（秒）";
		}
		return null;
	}

	/**
	 * 初始化类和图表
	 */
	public StatStaffAvgWaitTimeController() {
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
	public void getStatStaffAvgWaitTime() {
		statStaffAvgWaitTimeResultMo = statAchievementBsh.getStatStaffAvgWaitTimeResultMO(managerId,
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
		this.getStatStaffAvgWaitTime();
		if (statStaffAvgWaitTimeResultMo != null && statStaffAvgWaitTimeResultMo.getAvgWaitTimeMoList() != null
				&& !statStaffAvgWaitTimeResultMo.getAvgWaitTimeMoList().isEmpty()) {
			ChartSeries staffSeries = new ChartSeries();
			statChartModel.clear();
			staffSeries.setLabel("首次响应时间");
			for (StatStaffAvgWaitTimeMO temp : statStaffAvgWaitTimeResultMo.getAvgWaitTimeMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getAvgWaitTime());
			}
			statChartModel.addSeries(staffSeries);
		}
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public StatStaffAvgWaitTimeResultMO getStatStaffAvgWaitTimeResultMo() {
		return statStaffAvgWaitTimeResultMo;
	}

	public void setStatStaffAvgWaitTimeResultMo(StatStaffAvgWaitTimeResultMO statStaffAvgWaitTimeResultMo) {
		this.statStaffAvgWaitTimeResultMo = statStaffAvgWaitTimeResultMo;
	}

}
