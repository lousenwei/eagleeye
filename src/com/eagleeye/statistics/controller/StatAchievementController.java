package com.eagleeye.statistics.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.QueryParametersMo;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.statistics.bsh.IStatAchievementBsh;
import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.mo.shop.StatAchievementResultMO;
import com.eagleeye.statistics.mo.shop.StatShopAvgWaitTimeMO;
import com.eagleeye.statistics.mo.shop.StatShopAvgWaitTimeResultMO;
import com.eagleeye.statistics.mo.shop.StatShopExchangeRateMO;
import com.eagleeye.statistics.mo.shop.StatShopExchangeRateResultMO;
import com.eagleeye.statistics.mo.shop.StatShopManBuyMO;
import com.eagleeye.statistics.mo.shop.StatShopManBuyResultMO;
import com.eagleeye.statistics.mo.shop.StatShopOnlineTimeMO;
import com.eagleeye.statistics.mo.shop.StatShopOnlineTimeResultMO;
import com.eagleeye.statistics.mo.shop.StatShopRefundRateMO;
import com.eagleeye.statistics.mo.shop.StatShopRefundRateResultMO;
import com.eagleeye.statistics.mo.shop.StatShopWorkLoadMO;
import com.eagleeye.statistics.mo.shop.StatShopWorkLoadResultMO;

@ManagedBean(name = "statAchievement")
@ViewScoped
public class StatAchievementController {

	QueryParametersMo queryParametersMo = new QueryParametersMo();

	List<StatAchievementEO> statAchievementList;

	IStatAchievementBsh statAchievementBsh;

	CartesianChartModel shopStatChartModel;

	/******************** 店铺业绩指标数据结构 ********************/
	StatAchievementResultMO statResultMo = new StatAchievementResultMO();

	/******************** 店铺客单价数据结构 ********************/
	StatShopManBuyResultMO statManBuyResultMo = new StatShopManBuyResultMO();

	/******************** 店铺转换率数据结构 ********************/
	StatShopExchangeRateResultMO statExchangeRateResultMo = new StatShopExchangeRateResultMO();

	/******************** 店铺退款金率数据结构 ********************/
	StatShopRefundRateResultMO statRefundRateResultMo = new StatShopRefundRateResultMO();

	/******************** 店铺首次响应时间数据结构 ********************/
	StatShopAvgWaitTimeResultMO statAvgWaitTimeResultMo = new StatShopAvgWaitTimeResultMO();

	/******************** 店铺工作量数据结构 ********************/
	StatShopWorkLoadResultMO statWorkLoadResultMO = new StatShopWorkLoadResultMO();

	/******************** 店铺在线时间数据结构 ********************/
	StatShopOnlineTimeResultMO statOnlineTimeResultMo = new StatShopOnlineTimeResultMO();

	public StatAchievementController() {
		shopStatChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set("2011", 0);
		shopStatChartModel.addSeries(boys);
	}

	/**
	 * 获得店铺统计
	 * 
	 */
	public void getStatAchievementEOs() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);

		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator
				.getBean("statAchievementBsh");
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		statAchievementList = statAchievementBsh
				.getStatAchievementEOsByManagerIdAndTime(managerId,
						queryParametersMo.getStartTime(), queryParametersMo
								.getEndTime(),
						(queryParametersMo.getIsTotal() == null ? true
								: queryParametersMo.getIsTotal()));
		statResultMo = statAchievementBsh.getStatAchievementSumResultMO(null,
				managerId, (queryParametersMo.getIsTotal() == null ? true
						: queryParametersMo.getIsTotal()), queryParametersMo
						.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺统计及图表
	 */
	public void getStatAchievementChart() {
		this.getStatAchievementEOs();
		if (statAchievementList != null) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺业绩");
			for (StatAchievementEO temp : statAchievementList) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(temp.getId()
						.getStatDate(), DateUtil.datePatternMM_DD), temp
						.getTotalAmount());
			}
			shopStatChartModel.addSeries(shopSeries);
		}
	}

	/**
	 * 获得店铺客单价
	 */
	public void getStatShopManBuy() {
		
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator
				.getBean("statAchievementBsh");
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		statManBuyResultMo = statAchievementBsh.getStatShopManBuy(managerId,
				queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺转换率
	 */
	public void getStatExchangeRate() {
		
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator
				.getBean("statAchievementBsh");
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		statExchangeRateResultMo = statAchievementBsh
				.getStatShopExchangeRateResultMO(managerId,
						queryParametersMo.getStartTime(),
						queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺客单价数据及图表
	 */
	public void getStatShopManBuyChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopManBuy();
		if (statManBuyResultMo != null
				&& statManBuyResultMo.getStatShopManBuyMoList() != null) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺客单价");
			for (StatShopManBuyMO temp : statManBuyResultMo
					.getStatShopManBuyMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(
						temp.getStatDate(), DateUtil.datePatternMM_DD), temp
						.getManBuyAmount());
			}
			shopStatChartModel.addSeries(shopSeries);
		}

	}

	/**
	 * 获得店铺转换率数据及图表
	 */
	public void getStatShopExchangeRateChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatExchangeRate();
		if (statExchangeRateResultMo != null
				&& statExchangeRateResultMo.getExchangeRateMoList() != null) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺转换率");
			for (StatShopExchangeRateMO temp : statExchangeRateResultMo
					.getExchangeRateMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(
						temp.getStatDate(), DateUtil.datePatternMM_DD), temp
						.getExchangeRate());
			}
			shopStatChartModel.addSeries(shopSeries);
		}
	}

	/**
	 * 获得店铺退款金率
	 */
	public void getStatShopRefundRate() {
		
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator
				.getBean("statAchievementBsh");
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		statRefundRateResultMo = statAchievementBsh
				.getStatShopRefundRateResultMO(managerId,
						queryParametersMo.getStartTime(),
						queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺退款金率数据及图表
	 */
	public void getStatShopRefundRateChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopRefundRate();
		if (statRefundRateResultMo != null
				&& statRefundRateResultMo.getRefundRateMoList() != null) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺退款金额率");
			for (StatShopRefundRateMO temp : statRefundRateResultMo
					.getRefundRateMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(
						temp.getStatDate(), DateUtil.datePatternMM_DD), temp
						.getRefundRate());
			}
			shopStatChartModel.addSeries(shopSeries);
		}
	}

	/**
	 * 获得店铺平均等待时间
	 */
	public void getStatShopAvgWaitTime() {
		
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator
				.getBean("statAchievementBsh");
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		statAvgWaitTimeResultMo = statAchievementBsh
				.getStatShopAvgWaitTimeResultMO(managerId,
						queryParametersMo.getStartTime(),
						queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺平均等待时间数据及图表
	 */
	public void getStatShopAvgWaitTimeChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopAvgWaitTime();
		if (statAvgWaitTimeResultMo != null
				&& statAvgWaitTimeResultMo.getAvgWaitTimeMoList() != null) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺客户平均等待时间");
			for (StatShopAvgWaitTimeMO temp : statAvgWaitTimeResultMo
					.getAvgWaitTimeMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(
						temp.getStatDate(), DateUtil.datePatternMM_DD), temp
						.getAvgWaitTime());
			}
			shopStatChartModel.addSeries(shopSeries);
		}
	}

	/**
	 * 获得店铺工作量
	 */
	public void getStatShopWorkLoad() {
		
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator
				.getBean("statAchievementBsh");
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		statWorkLoadResultMO = statAchievementBsh.getStatShopWorkLoadResultMO(
				managerId, queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime());
	}

	/**
	 * 获得店铺工作量及图表
	 */
	public void getStatShopWorkLoadChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopWorkLoad();
		if (statWorkLoadResultMO != null
				&& statWorkLoadResultMO.getWorkLoadMoList() != null) {
			ChartSeries shopSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺客户平均等待时间");
			for (StatShopWorkLoadMO temp : statWorkLoadResultMO
					.getWorkLoadMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(
						temp.getStatDate(), DateUtil.datePatternMM_DD), temp
						.getWorkLoad());
			}
			shopStatChartModel.addSeries(shopSeries);
		}
	}

	public void getStatShopOnlineTime() {
		
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator
				.getBean("statAchievementBsh");
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		statOnlineTimeResultMo = statAchievementBsh
				.getStatShopOnlineTimeResultMO(managerId,
						queryParametersMo.getStartTime(),
						queryParametersMo.getEndTime());
	}

	public void getStatShopOnlineTimeChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatShopOnlineTime();
		if (statOnlineTimeResultMo != null
				&& statOnlineTimeResultMo.getAvgOnlineTimeMoList() != null) {
			ChartSeries shopSeries = new ChartSeries();
			ChartSeries standSeries = new ChartSeries();
			shopStatChartModel.clear();
			shopSeries.setLabel("店铺客服在线时间");
			standSeries.setLabel("8小时工作线");
			for (StatShopOnlineTimeMO temp : statOnlineTimeResultMo
					.getAvgOnlineTimeMoList()) {
				shopSeries.set(DateUtil.getTimeByCustomPattern(
						temp.getStatDate(), DateUtil.datePatternMM_DD), temp
						.getOnlineTime());
				standSeries.set(DateUtil.getTimeByCustomPattern(
						temp.getStatDate(), DateUtil.datePatternMM_DD),
						BigDecimal.valueOf(8));
			}
			shopStatChartModel.addSeries(shopSeries);
			shopStatChartModel.addSeries(standSeries);
		}
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

	public StatShopManBuyResultMO getStatManBuyResultMo() {
		return statManBuyResultMo;
	}

	public void setStatManBuyResultMo(StatShopManBuyResultMO statManBuyResultMo) {
		this.statManBuyResultMo = statManBuyResultMo;
	}

	public List<StatAchievementEO> getStatAchievementList() {
		return statAchievementList;
	}

	public void setStatAchievementList(
			List<StatAchievementEO> statAchievementList) {
		this.statAchievementList = statAchievementList;
	}

	public QueryParametersMo getQueryParametersMo() {
		return queryParametersMo;
	}

	public void setQueryParametersMo(QueryParametersMo queryParametersMo) {
		this.queryParametersMo = queryParametersMo;
	}

	public StatShopExchangeRateResultMO getStatExchangeRateResultMo() {
		return statExchangeRateResultMo;
	}

	public void setStatExchangeRateResultMo(
			StatShopExchangeRateResultMO statExchangeRateResultMo) {
		this.statExchangeRateResultMo = statExchangeRateResultMo;
	}

	public StatShopRefundRateResultMO getStatRefundRateResultMo() {
		return statRefundRateResultMo;
	}

	public void setStatRefundRateResultMo(
			StatShopRefundRateResultMO statRefundRateResultMo) {
		this.statRefundRateResultMo = statRefundRateResultMo;
	}

	public StatShopAvgWaitTimeResultMO getStatAvgWaitTimeResultMo() {
		return statAvgWaitTimeResultMo;
	}

	public void setStatAvgWaitTimeResultMo(
			StatShopAvgWaitTimeResultMO statAvgWaitTimeResultMo) {
		this.statAvgWaitTimeResultMo = statAvgWaitTimeResultMo;
	}

	public StatShopWorkLoadResultMO getStatWorkLoadResultMO() {
		return statWorkLoadResultMO;
	}

	public void setStatWorkLoadResultMO(
			StatShopWorkLoadResultMO statWorkLoadResultMO) {
		this.statWorkLoadResultMO = statWorkLoadResultMO;
	}

	public StatShopOnlineTimeResultMO getStatOnlineTimeResultMo() {
		return statOnlineTimeResultMo;
	}

	public void setStatOnlineTimeResultMo(
			StatShopOnlineTimeResultMO statOnlineTimeResultMo) {
		this.statOnlineTimeResultMo = statOnlineTimeResultMo;
	}

}
