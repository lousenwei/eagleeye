package com.eagleeye.statistics.controller.shop;

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

@ManagedBean(name = "statShopAchieve")
@ViewScoped
public class StatShopAchievementController extends AbstractStatShopController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4049854919800092948L;

	List<StatAchievementEO> statAchievementList;

	CartesianChartModel shopStatChartModel;

	/******************** 店铺业绩指标数据结构 ********************/
	StatAchievementResultMO statResultMo = new StatAchievementResultMO();

	public StatShopAchievementController() {
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
		statAchievementList = statAchievementBsh.getStatAchievementEOsByManagerIdAndTime(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime(),
				(queryParametersMo.getIsTotal() == null ? true : queryParametersMo.getIsTotal()));
		statResultMo = statAchievementBsh.getStatAchievementSumResultMO(null, managerId,
				(queryParametersMo.getIsTotal() == null ? true : queryParametersMo.getIsTotal()),
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺统计及图表
	 */
	public void getStatAchievementChart() {
		this.loadData();
		if (statAchievementList != null && !statAchievementList.isEmpty()) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺业绩");
			for (StatAchievementEO temp : statAchievementList) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getId().getStatDate(), DateUtil.datePatternMM_DD),
						temp.getTotalAmount());
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

	public CartesianChartModel getShopStatChartModel() {
		return shopStatChartModel;
	}

	public void setShopStatChartModel(CartesianChartModel shopStatChartModel) {
		this.shopStatChartModel = shopStatChartModel;
	}

	public StatAchievementResultMO getStatResultMo() {
		return statResultMo;
	}

	public void setStatResultMo(StatAchievementResultMO statResultMo) {
		this.statResultMo = statResultMo;
	}

}
