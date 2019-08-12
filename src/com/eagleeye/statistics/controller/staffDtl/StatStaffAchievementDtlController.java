package com.eagleeye.statistics.controller.staffDtl;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.statistics.bsh.IStatAchievementBsh;
import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.mo.shop.StatAchievementResultMO;

@ManagedBean(name = "statStaffAchieveDtl")
@ViewScoped
public class StatStaffAchievementDtlController extends AbstractStatStaffDtlController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2872673200408327953L;

	List<StatAchievementEO> statAchievementList;

	CartesianChartModel staffStatChartModel;

	/******************** 店铺业绩指标数据结构 ********************/
	StatAchievementResultMO statResultMo = new StatAchievementResultMO();

	public StatStaffAchievementDtlController() {
		super();
		staffStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		staffStatChartModel.addSeries(boys);
	}

	/**
	 * 获得店铺统计
	 * 
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);

		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		statAchievementList = statAchievementBsh.getStaffStatAchievementEOsById(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime(),
				(queryParametersMo.getIsTotal() == null ? false : queryParametersMo.getIsTotal()),
				queryParametersMo.getStaffId());
		statResultMo = statAchievementBsh.getStatAchievementResultMOByStaff(queryParametersMo.getStaffId(), managerId,
				false, queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
		if (statAchievementList != null && statResultMo != null && !statAchievementList.isEmpty()) {
			ChartSeries staffSeries = new ChartSeries();
			staffStatChartModel.clear();
			staffSeries.setLabel(queryParametersMo.getStaffId());
			for (StatAchievementEO temp : statAchievementList) {
				staffSeries.set(DateUtil.getTimeByCustomPattern(temp.getId().getStatDate(), DateUtil.datePatternMM_DD),
						temp.getTotalAmount());
			}
			staffStatChartModel.addSeries(staffSeries);
		}
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服ID";
		case 1:
			return "日期";
		case 2:
			return "等待发货";
		case 3:
			return "等待确认";
		case 4:
			return "交易完成";
		case 5:
			return "退款中";
		case 6:
			return "全额退款";
		case 7:
			return "部分退款";
		case 8:
			return "运费";
		case 9:
			return "当天盈亏（不含退款）";
		}
		return null;
	}

	public List<StatAchievementEO> getStatAchievementList() {
		return statAchievementList;
	}

	public void setStatAchievementList(List<StatAchievementEO> statAchievementList) {
		this.statAchievementList = statAchievementList;
	}

	public StatAchievementResultMO getStatResultMo() {
		return statResultMo;
	}

	public void setStatResultMo(StatAchievementResultMO statResultMo) {
		this.statResultMo = statResultMo;
	}

	public CartesianChartModel getStaffStatChartModel() {
		return staffStatChartModel;
	}

	public void setStaffStatChartModel(CartesianChartModel staffStatChartModel) {
		this.staffStatChartModel = staffStatChartModel;
	}

}
