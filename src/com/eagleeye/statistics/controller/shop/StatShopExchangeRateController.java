package com.eagleeye.statistics.controller.shop;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.statistics.mo.shop.StatShopExchangeRateMO;
import com.eagleeye.statistics.mo.shop.StatShopExchangeRateResultMO;

@ManagedBean(name = "statShopExchangeRate")
@ViewScoped
public class StatShopExchangeRateController extends AbstractStatShopController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6898246332693949617L;

	CartesianChartModel shopStatChartModel;

	/******************** 店铺转换率数据结构 ********************/
	StatShopExchangeRateResultMO statExchangeRateResultMo = new StatShopExchangeRateResultMO();

	public StatShopExchangeRateController() {
		super();
		shopStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		shopStatChartModel.addSeries(boys);
	}

	/**
	 * 获得店铺转换率
	 */
	public void getStatExchangeRate() {
		statExchangeRateResultMo = statAchievementBsh.getStatShopExchangeRateResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺转换率数据及图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatExchangeRate();
		if (statExchangeRateResultMo != null && statExchangeRateResultMo.getExchangeRateMoList() != null
				&& !statExchangeRateResultMo.getExchangeRateMoList().isEmpty()) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺转换率");
			for (StatShopExchangeRateMO temp : statExchangeRateResultMo.getExchangeRateMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getExchangeRate());
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
			return "转换率（%）";
		}
		return null;
	}

	public CartesianChartModel getShopStatChartModel() {
		return shopStatChartModel;
	}

	public void setShopStatChartModel(CartesianChartModel shopStatChartModel) {
		this.shopStatChartModel = shopStatChartModel;
	}

	public StatShopExchangeRateResultMO getStatExchangeRateResultMo() {
		return statExchangeRateResultMo;
	}

	public void setStatExchangeRateResultMo(StatShopExchangeRateResultMO statExchangeRateResultMo) {
		this.statExchangeRateResultMo = statExchangeRateResultMo;
	}

}
