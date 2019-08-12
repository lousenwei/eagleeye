package com.eagleeye.statistics.controller.shop;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.eo.StatAchievementNumEO;
import com.eagleeye.statistics.mo.shop.StatAchievementNumResultMO;

@ManagedBean(name = "statShopAchieveNum")
@ViewScoped
public class StatShopAchievementNumController extends AbstractStatShopController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4049854919800092948L;

	List<StatAchievementNumEO> statAchievementList;

	CartesianChartModel shopStatChartModel;

	/******************** 店铺业绩指标数据结构 ********************/
	StatAchievementNumResultMO statResultMo = new StatAchievementNumResultMO();

	public StatShopAchievementNumController() {
		super();
		shopStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		shopStatChartModel.addSeries(boys);
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
		statAchievementList = statAchievementBsh.getStatAchievementNumEOsByManagerIdAndTime(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime(), true);
		statResultMo = statAchievementBsh.getShopNumSumAllResultMO(managerId, queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺统计及图表
	 */
	public void getStatAchievementChart() {
		this.loadData();
		if (statAchievementList != null && !statAchievementList.isEmpty()) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺净件数业绩");
			for (StatAchievementNumEO temp : statAchievementList) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getId().getStatDate(), DateUtil.datePatternMM_DD),
						temp.getTotalNumNoRefund());
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
			return "净件数业绩（不含退款）";
		case 9:
			return "全部件数业绩（包含退款）";
		}
		return null;
	}

	public List<StatAchievementNumEO> getStatAchievementList() {
		return statAchievementList;
	}

	public void setStatAchievementList(List<StatAchievementNumEO> statAchievementList) {
		this.statAchievementList = statAchievementList;
	}

	public CartesianChartModel getShopStatChartModel() {
		return shopStatChartModel;
	}

	public void setShopStatChartModel(CartesianChartModel shopStatChartModel) {
		this.shopStatChartModel = shopStatChartModel;
	}

	public StatAchievementNumResultMO getStatResultMo() {
		return statResultMo;
	}

	public void setStatResultMo(StatAchievementNumResultMO statResultMo) {
		this.statResultMo = statResultMo;
	}

}
