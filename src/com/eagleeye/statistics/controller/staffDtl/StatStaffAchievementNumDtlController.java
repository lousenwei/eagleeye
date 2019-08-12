package com.eagleeye.statistics.controller.staffDtl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.eo.StatAchievementNumEO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumMO;

@ManagedBean(name = "statStaffAchNumDtl")
@ViewScoped
public class StatStaffAchievementNumDtlController extends AbstractStatStaffDtlController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7586182402811419263L;

	CartesianChartModel statChartModel;

	/******************** 客服交易数量统计数据结构 ********************/
	StatStaffTradeNumMO statStaffTradeNumResultMo = new StatStaffTradeNumMO();

	List<StatAchievementNumEO> statStaffDtlList = new ArrayList();

	/**
	 * 初始化类和图表
	 */
	public StatStaffAchievementNumDtlController() {
		super();
		statChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		statChartModel.addSeries(boys);
	}

	/**
	 * 得到客服交易数目数据结构
	 */
	public void getStaffAchievementNumDtlResultMOs() {
		statStaffDtlList = statAchievementBsh
				.getStaffStatAchievementNumEOsById(managerId, queryParametersMo.getStartTime(),
						queryParametersMo.getEndTime(), false, queryParametersMo.getStaffId());
		// 添加小记
		statStaffTradeNumResultMo = statAchievementBsh.getStaffNumSumResultMO(queryParametersMo.getStaffId(),
				managerId, queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服交易数目图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStaffAchievementNumDtlResultMOs();
		this.getStaffAchievementNumDtlLineChart();
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStaffAchievementNumDtlLineChart() {
		if (statStaffDtlList != null && !statStaffDtlList.isEmpty()) {
			statChartModel.clear();
			ChartSeries lineSeries = new ChartSeries();
			lineSeries.setLabel(queryParametersMo.getStaffId());
			for (StatAchievementNumEO temp : statStaffDtlList) {
				lineSeries.set(DateUtil.getTimeByCustomPattern(temp.getId().getStatDate(), DateUtil.datePatternMM_DD),
						temp.getTotalNumNoRefund());
			}
			statChartModel.addSeries(lineSeries);
		}
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服帐号";
		case 1:
			return "等待发货";
		case 2:
			return "等待确认";
		case 3:
			return "交易完成";
		case 4:
			return "退款中";
		case 5:
			return "全额退款";
		case 6:
			return "部分退款";
		case 7:
			return "实际数量";
		case 8:
			return "所有数量(含退款)";
		}
		return null;
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public List<StatAchievementNumEO> getStatStaffDtlList() {
		return statStaffDtlList;
	}

	public void setStatStaffDtlList(List<StatAchievementNumEO> statStaffDtlList) {
		this.statStaffDtlList = statStaffDtlList;
	}

	public StatStaffTradeNumMO getStatStaffTradeNumResultMo() {
		return statStaffTradeNumResultMo;
	}

	public void setStatStaffTradeNumResultMo(StatStaffTradeNumMO statStaffTradeNumResultMo) {
		this.statStaffTradeNumResultMo = statStaffTradeNumResultMo;
	}

}
