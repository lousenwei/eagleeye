package com.eagleeye.statistics.controller.shop;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.shop.StatShopAvgWaitTimeMO;
import com.eagleeye.statistics.mo.shop.StatShopAvgWaitTimeResultMO;

@ManagedBean(name = "statShopAvgWaitTime")
@ViewScoped
public class StatShopAvgWaitTimeController extends AbstractStatShopController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 117926810715614194L;

	CartesianChartModel shopStatChartModel;

	/******************** 店铺首次响应时间数据结构 ********************/
	StatShopAvgWaitTimeResultMO statAvgWaitTimeResultMo = new StatShopAvgWaitTimeResultMO();

	public StatShopAvgWaitTimeController() {
		super();
		shopStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		shopStatChartModel.addSeries(boys);
	}

	/**
	 * 获得店铺平均等待时间
	 */
	public void getStatShopAvgWaitTime() {
		statAvgWaitTimeResultMo = statAchievementBsh.getStatShopAvgWaitTimeResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺平均等待时间数据及图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopAvgWaitTime();
		if (statAvgWaitTimeResultMo != null && statAvgWaitTimeResultMo.getAvgWaitTimeMoList() != null
				&& !statAvgWaitTimeResultMo.getAvgWaitTimeMoList().isEmpty()) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺客户平均等待时间");
			for (StatShopAvgWaitTimeMO temp : statAvgWaitTimeResultMo.getAvgWaitTimeMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getAvgWaitTime());
			}
			shopStatChartModel.addSeries(shopSeries);
		}
	}

	@Override
	protected String getHeadString(int i) {
		switch (i) {
		case 0:
			return "日期";
		case 1:
			return "店主ID";
		case 2:
			return "客户平均等待时间（秒）";
		}
		return null;
	}

	public CartesianChartModel getShopStatChartModel() {
		return shopStatChartModel;
	}

	public void setShopStatChartModel(CartesianChartModel shopStatChartModel) {
		this.shopStatChartModel = shopStatChartModel;
	}

	public StatShopAvgWaitTimeResultMO getStatAvgWaitTimeResultMo() {
		return statAvgWaitTimeResultMo;
	}

	public void setStatAvgWaitTimeResultMo(StatShopAvgWaitTimeResultMO statAvgWaitTimeResultMo) {
		this.statAvgWaitTimeResultMo = statAvgWaitTimeResultMo;
	}

}
