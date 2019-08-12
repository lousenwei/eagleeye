package com.eagleeye.statistics.controller.shop;

import java.io.Serializable;

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
import com.eagleeye.statistics.mo.shop.StatShopRefundRateMO;
import com.eagleeye.statistics.mo.shop.StatShopRefundRateResultMO;

@ManagedBean(name = "statShopRefundRate")
@ViewScoped
public class StatShopRefundRateController extends AbstractStatShopController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6660683913998579676L;

	CartesianChartModel shopStatChartModel;

	public CartesianChartModel getShopStatChartModel() {
		return shopStatChartModel;
	}

	public void setShopStatChartModel(CartesianChartModel shopStatChartModel) {
		this.shopStatChartModel = shopStatChartModel;
	}

	public StatShopRefundRateResultMO getStatRefundRateResultMo() {
		return statRefundRateResultMo;
	}

	public void setStatRefundRateResultMo(StatShopRefundRateResultMO statRefundRateResultMo) {
		this.statRefundRateResultMo = statRefundRateResultMo;
	}

	/******************** 店铺退款金率数据结构 ********************/
	StatShopRefundRateResultMO statRefundRateResultMo = new StatShopRefundRateResultMO();

	public StatShopRefundRateController() {
		super();
		shopStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		shopStatChartModel.addSeries(boys);
	}

	/**
	 * 获得店铺退款金率
	 */
	public void getStatShopRefundRate() {

		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		statRefundRateResultMo = statAchievementBsh.getStatShopRefundRateResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺退款金率数据及图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopRefundRate();
		if (statRefundRateResultMo != null && statRefundRateResultMo.getRefundRateMoList() != null
				&& !statRefundRateResultMo.getRefundRateMoList().isEmpty()) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺退款金额率");
			for (StatShopRefundRateMO temp : statRefundRateResultMo.getRefundRateMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getRefundRate());
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
			return "退款金额率（%）";
		}
		return null;
	}

}
