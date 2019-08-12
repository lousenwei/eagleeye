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
import com.eagleeye.statistics.mo.shop.StatShopWorkLoadMO;
import com.eagleeye.statistics.mo.shop.StatShopWorkLoadResultMO;

@ManagedBean(name = "statShopWorkLoad")
@ViewScoped
public class StatShopWorkLoadController extends AbstractStatShopController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8466810221916164353L;

	CartesianChartModel shopStatChartModel;

	/******************** 店铺工作量数据结构 ********************/
	StatShopWorkLoadResultMO statWorkLoadResultMO = new StatShopWorkLoadResultMO();

	public StatShopWorkLoadController() {
		super();
		shopStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set(EagleConstants.CHART_YEAR, 0);
		shopStatChartModel.addSeries(boys);
	}

	/**
	 * 获得店铺工作量
	 */
	public void getStatShopWorkLoad() {

		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		statWorkLoadResultMO = statAchievementBsh.getStatShopWorkLoadResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺工作量及图表
	 */
	public void loadData() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopWorkLoad();
		if (statWorkLoadResultMO != null && statWorkLoadResultMO.getWorkLoadMoList() != null
				&& !statWorkLoadResultMO.getWorkLoadMoList().isEmpty()) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺客户平均等待时间");
			for (StatShopWorkLoadMO temp : statWorkLoadResultMO.getWorkLoadMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getStatDate(), DateUtil.datePatternMM_DD),
						temp.getWorkLoad());
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
			return "工作量（人次）";
		}
		return null;
	}

	public CartesianChartModel getShopStatChartModel() {
		return shopStatChartModel;
	}

	public void setShopStatChartModel(CartesianChartModel shopStatChartModel) {
		this.shopStatChartModel = shopStatChartModel;
	}

	public StatShopWorkLoadResultMO getStatWorkLoadResultMO() {
		return statWorkLoadResultMO;
	}

	public void setStatWorkLoadResultMO(StatShopWorkLoadResultMO statWorkLoadResultMO) {
		this.statWorkLoadResultMO = statWorkLoadResultMO;
	}

}
