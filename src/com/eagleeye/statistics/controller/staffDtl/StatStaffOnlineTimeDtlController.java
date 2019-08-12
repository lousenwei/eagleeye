package com.eagleeye.statistics.controller.staffDtl;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.dtl.StatStaffOnlineTimeDtlMO;
import com.eagleeye.statistics.mo.dtl.StatStaffOnlineTimeDtlResultMO;

@ManagedBean(name = "statStaffOnlineTimeDtl")
@ViewScoped
public class StatStaffOnlineTimeDtlController extends AbstractStatStaffDtlController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1005193992224223616L;

	CartesianChartModel statStaffOnlineTimeDtlChartModel;

	/******************** 店铺工作量数据结构 ********************/
	StatStaffOnlineTimeDtlResultMO onlineTimeDtlMo = new StatStaffOnlineTimeDtlResultMO();

	public StatStaffOnlineTimeDtlController() {
		super();
		statStaffOnlineTimeDtlChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statStaffOnlineTimeDtlChartModel.addSeries(boys);
	}

	@Override
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.validQueryParams(queryParametersMo)) {
			return;
		}
		this.getStaffOnlineTimeDtl();
		this.getStaffOnlineTimeLineChart();
	}

	public void getStaffOnlineTimeDtl() {
		onlineTimeDtlMo = statAchievementBsh.getStatStaffOnlineTimeDtlResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime(), queryParametersMo.getStaffId());
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStaffOnlineTimeLineChart() {
		if (onlineTimeDtlMo != null && onlineTimeDtlMo.getOnlineMoList() != null
				&& !onlineTimeDtlMo.getOnlineMoList().isEmpty()) {
			statStaffOnlineTimeDtlChartModel.clear();
			ChartSeries lineSeries = new ChartSeries();
			lineSeries.setLabel(queryParametersMo.getStaffId());
			for (StatStaffOnlineTimeDtlMO temp : onlineTimeDtlMo.getOnlineMoList()) {
				lineSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getOnlineTimeForEightHours());
			}
			statStaffOnlineTimeDtlChartModel.addSeries(lineSeries);
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
			return "在线时间（小时）";
		case 3:
			return "在线时间（工），8小时为1工";
		}
		return null;
	}

	public CartesianChartModel getStatStaffOnlineTimeDtlChartModel() {
		return statStaffOnlineTimeDtlChartModel;
	}

	public void setStatStaffOnlineTimeDtlChartModel(CartesianChartModel statStaffOnlineTimeDtlChartModel) {
		this.statStaffOnlineTimeDtlChartModel = statStaffOnlineTimeDtlChartModel;
	}

	public StatStaffOnlineTimeDtlResultMO getOnlineTimeDtlMo() {
		return onlineTimeDtlMo;
	}

	public void setOnlineTimeDtlMo(StatStaffOnlineTimeDtlResultMO onlineTimeDtlMo) {
		this.onlineTimeDtlMo = onlineTimeDtlMo;
	}

}
