package com.eagleeye.statistics.controller.shop;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.shop.StatShopManBuyMO;
import com.eagleeye.statistics.mo.shop.StatShopManBuyResultMO;

@ManagedBean(name = "statShopManBuy")
@ViewScoped
public class StatShopManBuyController extends AbstractStatShopController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3489640322954098346L;

	CartesianChartModel shopStatChartModel;

	/******************** 店铺客单价数据结构 ********************/
	StatShopManBuyResultMO statManBuyResultMo = new StatShopManBuyResultMO();

	public StatShopManBuyController() {
		super();
		shopStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		shopStatChartModel.addSeries(boys);
	}

	/**
	 * 获得店铺客单价
	 */
	public void getStatShopManBuy() {
		statManBuyResultMo = statAchievementBsh.getStatShopManBuy(managerId, queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺客单价数据及图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopManBuy();
		if (statManBuyResultMo != null && statManBuyResultMo.getStatShopManBuyMoList() != null
				&& !statManBuyResultMo.getStatShopManBuyMoList().isEmpty()) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺客单价");
			for (StatShopManBuyMO temp : statManBuyResultMo.getStatShopManBuyMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getManBuyAmount());
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
			return "客单价（元）";
		}
		return null;
	}

	public CartesianChartModel getShopStatChartModel() {
		return shopStatChartModel;
	}

	public void setShopStatChartModel(CartesianChartModel shopStatChartModel) {
		this.shopStatChartModel = shopStatChartModel;
	}

	public StatShopManBuyResultMO getStatManBuyResultMo() {
		return statManBuyResultMo;
	}

	public void setStatManBuyResultMo(StatShopManBuyResultMO statManBuyResultMo) {
		this.statManBuyResultMo = statManBuyResultMo;
	}

}
