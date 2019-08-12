package com.eagleeye.statistics.controller.shop;

import java.io.Serializable;
import java.math.BigDecimal;

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
import com.eagleeye.statistics.mo.shop.StatShopOnlineTimeMO;
import com.eagleeye.statistics.mo.shop.StatShopOnlineTimeResultMO;

@ManagedBean(name = "statShopOnlineTime")
@ViewScoped
public class StatShopOnlineTimeController extends AbstractStatShopController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7167501534128289016L;

	CartesianChartModel shopStatChartModel;

	/******************** 店铺在线时间数据结构 ********************/
	StatShopOnlineTimeResultMO statOnlineTimeResultMo = new StatShopOnlineTimeResultMO();

	public StatShopOnlineTimeController() {
		super();
		shopStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		shopStatChartModel.addSeries(boys);
	}

	public void getStatShopOnlineTime() {

		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		statOnlineTimeResultMo = statAchievementBsh.getStatShopOnlineTimeResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopOnlineTime();
		if (statOnlineTimeResultMo != null && statOnlineTimeResultMo.getAvgOnlineTimeMoList() != null
				&& !statOnlineTimeResultMo.getAvgOnlineTimeMoList().isEmpty()) {
			ChartSeries shopSeries = new ChartSeries();
			ChartSeries standSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺客服在线时间");
			standSeries.setLabel("8小时工作线");
			for (StatShopOnlineTimeMO temp : statOnlineTimeResultMo.getAvgOnlineTimeMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getOnlineTime());
				standSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						BigDecimal.valueOf(8));
			}
			shopStatChartModel.addSeries(shopSeries);
			shopStatChartModel.addSeries(standSeries);
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
			return "客服日平均在线时间（小时）";
		}
		return null;
	}

	public CartesianChartModel getShopStatChartModel() {
		return shopStatChartModel;
	}

	public void setShopStatChartModel(CartesianChartModel shopStatChartModel) {
		this.shopStatChartModel = shopStatChartModel;
	}

	public StatShopOnlineTimeResultMO getStatOnlineTimeResultMo() {
		return statOnlineTimeResultMo;
	}

	public void setStatOnlineTimeResultMo(StatShopOnlineTimeResultMO statOnlineTimeResultMo) {
		this.statOnlineTimeResultMo = statOnlineTimeResultMo;
	}

}
