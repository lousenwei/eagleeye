package com.eagleeye.statistics.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.dom4j.DocumentException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.QueryParametersMo;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.statistics.bsh.IStatAchievementBsh;
import com.eagleeye.statistics.eo.StatAchievementEO;
import com.eagleeye.statistics.mo.shop.StatAchievementResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffAvgWaitTimeMO;
import com.eagleeye.statistics.mo.staff.StatStaffAvgWaitTimeResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffExchangeRateMO;
import com.eagleeye.statistics.mo.staff.StatStaffExchangeRateResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffManBuyMO;
import com.eagleeye.statistics.mo.staff.StatStaffManBuyResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffOnlineTimeMO;
import com.eagleeye.statistics.mo.staff.StatStaffOnlineTimeResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffRefundRateMO;
import com.eagleeye.statistics.mo.staff.StatStaffRefundRateResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumMO;
import com.eagleeye.statistics.mo.staff.StatStaffTradeNumResultMO;
import com.eagleeye.statistics.mo.staff.StatStaffWorkLoadMO;
import com.eagleeye.statistics.mo.staff.StatStaffWorkLoadResultMO;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.eo.GroupMemberEO;

@ManagedBean(name = "statOwners")
@ViewScoped
public class StatOwnersController {

	QueryParametersMo queryParametersMo = new QueryParametersMo();

	IStatAchievementBsh statAchievementBsh;

	private IGroupMemberBsh groupMemberBsh;

	PieChartModel statOwnersChartModel;

	CartesianChartModel statChartModel;

	List<StatAchievementEO> statOwnersList;

	List<StatAchievementResultMO> statResultMos = new ArrayList();

	List<GroupMemberEO> members = new ArrayList();

	/******************** 客服业绩指标数据结构 ********************/
	StatAchievementResultMO statResultMo = new StatAchievementResultMO();

	/**
	 * 选中的客服业绩数据
	 */
	StatAchievementResultMO selectStatResultMo = new StatAchievementResultMO();

	/******************** 客服首次响应时间率数据结构 ********************/
	StatStaffAvgWaitTimeResultMO statStaffAvgWaitTimeResultMo = new StatStaffAvgWaitTimeResultMO();

	/******************** 客服转换率数据结构 ********************/
	StatStaffExchangeRateResultMO statStaffExchangeRateResultMo = new StatStaffExchangeRateResultMO();

	/******************** 客服工作量数据结构 ********************/
	StatStaffWorkLoadResultMO statStaffWorkLoadResultMO = new StatStaffWorkLoadResultMO();

	/******************** 客服客单价数据结构 ********************/
	StatStaffManBuyResultMO statStaffManBuyResultMO = new StatStaffManBuyResultMO();

	/******************** 客服退款金率数据结构 ********************/
	StatStaffRefundRateResultMO statStaffRefundRateResultMO = new StatStaffRefundRateResultMO();

	/******************** 客服在线时间数据结构 ********************/
	StatStaffOnlineTimeResultMO statStaffOnlineTimeResultMo = new StatStaffOnlineTimeResultMO();

	/******************** 客服交易数量统计数据结构 ********************/
	StatStaffTradeNumResultMO statStaffTradeNumResultMo = new StatStaffTradeNumResultMO();

	/**
	 * 得到客服交易数目数据结构
	 */
	public void getStatOwnersNumResultMOs() {
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		statStaffTradeNumResultMo = statAchievementBsh.getStatStaffTradeNumResultMO(managerId,
				(queryParametersMo.getIsTotal() == null ? false : queryParametersMo.getIsTotal()),
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服业绩数据结构
	 */
	public void getStatOwnersResultMOs() {
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		statResultMos = statAchievementBsh.getStatOwnersSumResultMOs(null, managerId,
				(queryParametersMo.getIsTotal() == null ? false : queryParametersMo.getIsTotal()),
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
		statResultMo = statAchievementBsh.getStatAchievementSumResultMO(null, managerId,
				(queryParametersMo.getIsTotal() == null ? true : queryParametersMo.getIsTotal()),
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
		selectStatResultMo = null;
	}

	/**
	 * 选择客服业绩指标数据联动
	 * 
	 * @param ev
	 */
	public void onRowSelect(SelectEvent ev) {

		selectStatResultMo = (StatAchievementResultMO) ev.getObject();
		// 选择交易下订单
		if (selectStatResultMo != null) {
			statOwnersList = statAchievementBsh.getStaffStatAchievementEOsById(selectStatResultMo.getManagerId(),
					queryParametersMo.getStartTime(), queryParametersMo.getEndTime(), false,
					selectStatResultMo.getStaffId());
		}
	}

	/**
	 * 初始化类和图表
	 */
	public StatOwnersController() {
		statOwnersChartModel = new PieChartModel();
		statOwnersChartModel.set(EagleConstants.CHART_TITLE, 100);
		statChartModel = new CartesianChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel(EagleConstants.CHART_TITLE);
		boys.set("2011", 0);
		statChartModel.addSeries(boys);
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator.getBean("groupMemberBsh");
		if (managerId != null) {
			members = groupMemberBsh.getGroupMemebersByManagerId(managerId);
		}
		// this.getStatOwnersAll();
	}

	/**
	 * 得到客服业绩饼图
	 */
	public void getStatStaffPieChart() {
		if (statResultMos != null) {
			statOwnersChartModel.clear();
			for (StatAchievementResultMO temp : statResultMos) {
				statOwnersChartModel.set(temp.getStaffId(), temp.getTotalAmount());
			}
		}
	}

	/**
	 * 得到客服交易数目饼图
	 */
	public void getStatStaffNumPieChart() {
		if (statStaffTradeNumResultMo != null && statStaffTradeNumResultMo.getStatStaffTradeNumMoList() != null) {
			statOwnersChartModel.clear();
			for (StatStaffTradeNumMO temp : statStaffTradeNumResultMo.getStatStaffTradeNumMoList()) {
				statOwnersChartModel.set(temp.getStaffId(), temp.getTotalNumNoRefund());
			}
		}
	}

	/**
	 * 得到客服业绩柱状图
	 */
	public void getStatStaffBarChart() {
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		if (statResultMos != null) {
			statChartModel.clear();
			for (StatAchievementResultMO temp : statResultMos) {
				ChartSeries barSeries = new ChartSeries();
				barSeries.setLabel(temp.getStaffId());
				barSeries.set("全部退款", temp.getAmountAllRefund());
				barSeries.set("部分退款", temp.getAmountPartRefund());
				barSeries.set("确认付款", temp.getAmountSuccess());
				barSeries.set("等待确认", temp.getAmountWaitConfirm());
				barSeries.set("等待发货", temp.getAmountWaitSend());
				barSeries.set("运费", temp.getPostFee());
				barSeries.set("实际盈亏", temp.getTotalAmount());
				statChartModel.addSeries(barSeries);
			}
		}
	}

	/**
	 * 得到客服交易数目柱状图
	 */
	public void getStatStaffNumBarChart() {
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		if (statStaffTradeNumResultMo != null && statStaffTradeNumResultMo.getStatStaffTradeNumMoList() != null) {
			statChartModel.clear();
			for (StatStaffTradeNumMO temp : statStaffTradeNumResultMo.getStatStaffTradeNumMoList()) {
				ChartSeries barSeries = new ChartSeries();
				barSeries.setLabel(temp.getStaffId());
				barSeries.set("等待确认", temp.getNumWaitConfirm());
				barSeries.set("等待发货", temp.getNumWaitSend());
				barSeries.set("确认付款", temp.getNumSuccess());
				barSeries.set("退款处理中", temp.getNumRefunding());
				barSeries.set("全部退款", temp.getNumAllRefund());
				barSeries.set("部分退款", temp.getNumPartRefund());
				barSeries.set("实际数目", temp.getTotalNumNoRefund());
				barSeries.set("所有数目", temp.getTotalNumHasRefund());
				statChartModel.addSeries(barSeries);
			}
		}
	}

	/**
	 * 得到客服业绩图表
	 */
	public void getStatOwnersAll() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatOwnersResultMOs();
		this.getStatStaffPieChart();
		this.getStatStaffBarChart();
	}

	/**
	 * 得到客服交易数目图表
	 */
	public void getStatOwnersNumAll() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatOwnersNumResultMOs();
		this.getStatStaffNumPieChart();
		this.getStatStaffNumBarChart();
	}

	/**
	 * 得到客服首次响应时间数据结构
	 */
	public void getStatStaffAvgWaitTime() {
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);

		statStaffAvgWaitTimeResultMo = statAchievementBsh.getStatStaffAvgWaitTimeResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服首次响应时间图表
	 */
	public void getStatStaffAvgWaitTimeChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffAvgWaitTime();
		if (statStaffAvgWaitTimeResultMo != null && statStaffAvgWaitTimeResultMo.getAvgWaitTimeMoList() != null) {
			ChartSeries staffSeries = new ChartSeries();
			statChartModel.clear();
			staffSeries.setLabel("首次响应时间");
			for (StatStaffAvgWaitTimeMO temp : statStaffAvgWaitTimeResultMo.getAvgWaitTimeMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getAvgWaitTime());
			}
			statChartModel.addSeries(staffSeries);
		}
	}

	/**
	 * 得到客服转换率数据结构
	 */
	public void getStatStaffExchangeRate() {
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);

		statStaffExchangeRateResultMo = statAchievementBsh.getStatStaffExchangeRateResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服转换率图表
	 */
	public void getStatStaffExchangeRateChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffExchangeRate();
		if (statStaffExchangeRateResultMo != null && statStaffExchangeRateResultMo.getExchangeRateMoList() != null) {
			ChartSeries staffSeries = new ChartSeries();
			statChartModel.clear();
			staffSeries.setLabel("客服转换率");
			for (StatStaffExchangeRateMO temp : statStaffExchangeRateResultMo.getExchangeRateMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getExchangeRate());
			}
			statChartModel.addSeries(staffSeries);
		}
	}

	/**
	 * 得到客服工作量数据结构
	 */
	public void getStatStaffWorkLoad() {
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);

		statStaffWorkLoadResultMO = statAchievementBsh.getStatStaffWorkLoadResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服工作量饼图
	 */
	public void getStatStaffWorkLoadPieChart() {

		if (statStaffWorkLoadResultMO != null && statStaffWorkLoadResultMO.getWorkLoadMoList() != null) {
			statOwnersChartModel.clear();
			for (StatStaffWorkLoadMO temp : statStaffWorkLoadResultMO.getWorkLoadMoList()) {
				statOwnersChartModel.set(temp.getStaffId(), temp.getSumWorkLoad());
			}
		}
	}

	/**
	 * 得到客服工作量柱状图
	 */
	public void getStatStaffWorkLoadBarChart() {
		if (statStaffWorkLoadResultMO != null && statStaffWorkLoadResultMO.getWorkLoadMoList() != null) {
			statChartModel.clear();
			ChartSeries staffSeries = new ChartSeries();
			staffSeries.setLabel("平均工作量");
			for (StatStaffWorkLoadMO temp : statStaffWorkLoadResultMO.getWorkLoadMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getAvgWorkLoad());
			}
			statChartModel.addSeries(staffSeries);
		}
	}

	/**
	 * 得到客服工作量图表
	 */
	public void getStatStaffWorkLoadChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffWorkLoad();
		this.getStatStaffWorkLoadPieChart();
		this.getStatStaffWorkLoadBarChart();
	}

	/**
	 * 得到客服的客单价数据结构
	 */
	public void getStatStaffManBuy() {
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);

		statStaffManBuyResultMO = statAchievementBsh.getStatStaffManBuyResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());

	}

	/**
	 * 得到客服的客单价图表
	 */
	public void getStatStaffManBuyChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffManBuy();
		if (statStaffManBuyResultMO != null && statStaffManBuyResultMO.getStatStaffManBuyMoList() != null) {
			ChartSeries staffSeries = new ChartSeries();
			statChartModel.clear();
			staffSeries.setLabel("客服客单价");
			for (StatStaffManBuyMO temp : statStaffManBuyResultMO.getStatStaffManBuyMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getManBuyAmount());
			}
			statChartModel.addSeries(staffSeries);
		}
	}

	/**
	 * 得到客服退款金额率数据结构
	 */
	public void getStatStaffRefundRate() {
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);

		statStaffRefundRateResultMO = statAchievementBsh.getStatStaffRefundRateResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服退款金额率图表
	 */
	public void getStatStaffRefundRateChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffRefundRate();
		if (statStaffRefundRateResultMO != null && statStaffRefundRateResultMO.getRefundRateMoList() != null) {
			ChartSeries staffSeries = new ChartSeries();
			statChartModel.clear();
			staffSeries.setLabel("客服退款金率");
			for (StatStaffRefundRateMO temp : statStaffRefundRateResultMO.getRefundRateMoList()) {
				staffSeries.set(temp.getStaffId(), temp.getRefundRate());
			}
			statChartModel.addSeries(staffSeries);
		}
	}

	/**
	 * 得到客服首次响应时间数据结构
	 */
	public void getStatStaffOnlineTime() {
		statAchievementBsh = (IStatAchievementBsh) EagleEyeServiceLocator.getBean("statAchievementBsh");
		String managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);

		statStaffOnlineTimeResultMo = statAchievementBsh.getStatStaffOnlineTimeResultMO(managerId,
				queryParametersMo.getStartTime(), queryParametersMo.getEndTime());
	}

	/**
	 * 得到客服首次响应时间图表
	 */
	public void getStatStaffOnlineTimeChart() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(), queryParametersMo.getEndTime())) {
			return;
		}
		this.getStatStaffOnlineTime();
		if (statStaffOnlineTimeResultMo != null && statStaffOnlineTimeResultMo.getAvgOnlineMoList() != null) {
			ChartSeries staffAvgOnlineSeries = new ChartSeries();
			ChartSeries staffSumOnlineSeries = new ChartSeries();
			ChartSeries staffSumOnlineTimeForEightHoursSeries = new ChartSeries();
			statChartModel.clear();
			staffAvgOnlineSeries.setLabel("平均在线时间");
			staffSumOnlineSeries.setLabel("总在线时间");
			staffSumOnlineTimeForEightHoursSeries.setLabel("总在线天数");

			for (StatStaffOnlineTimeMO temp : statStaffOnlineTimeResultMo.getAvgOnlineMoList()) {
				staffAvgOnlineSeries.set(temp.getStaffId(), temp.getAvgOnlineTime());
				staffSumOnlineSeries.set(temp.getStaffId(), temp.getSumOnlineTime());
				staffSumOnlineTimeForEightHoursSeries.set(temp.getStaffId(), temp.getSumOnlineTimeForEightHours());
			}
			statChartModel.addSeries(staffAvgOnlineSeries);
			statChartModel.addSeries(staffSumOnlineSeries);
			statChartModel.addSeries(staffSumOnlineTimeForEightHoursSeries);
		}
	}

	/**
	 * 导出EXCEL功能
	 * 
	 * @param document
	 */
	public void postProcessXLS(Object document) throws IOException, DocumentException {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);
			cell.setCellValue(this.getHeadString(i));
			cell.setCellStyle(cellStyle);
			sheet.autoSizeColumn((short) i);
		}
	}

	private String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服帐号";
		case 1:
			return "等待发货（件）";
		case 2:
			return "等待确认（件）";
		case 3:
			return "交易完成（件）";
		case 4:
			return "退款中（件）";
		case 5:
			return "全额退款（件）";
		case 6:
			return "部分退款（件）";
		case 7:
			return "实际数量（件）";
		case 8:
			return "所有数量（含退款）（件）";
		}
		return null;
	}

	public QueryParametersMo getQueryParametersMo() {
		return queryParametersMo;
	}

	public void setQueryParametersMo(QueryParametersMo queryParametersMo) {
		this.queryParametersMo = queryParametersMo;
	}

	public List<StatAchievementEO> getStatOwnersList() {
		return statOwnersList;
	}

	public void setStatOwnersList(List<StatAchievementEO> statOwnersList) {
		this.statOwnersList = statOwnersList;
	}

	public List<StatAchievementResultMO> getStatResultMos() {
		return statResultMos;
	}

	public void setStatResultMos(List<StatAchievementResultMO> statResultMos) {
		this.statResultMos = statResultMos;
	}

	public StatAchievementResultMO getStatResultMo() {
		return statResultMo;
	}

	public void setStatResultMo(StatAchievementResultMO statResultMo) {
		this.statResultMo = statResultMo;
	}

	public PieChartModel getStatOwnersChartModel() {
		return statOwnersChartModel;
	}

	public void setStatOwnersChartModel(PieChartModel statOwnersChartModel) {
		this.statOwnersChartModel = statOwnersChartModel;
	}

	public CartesianChartModel getStatChartModel() {
		return statChartModel;
	}

	public void setStatChartModel(CartesianChartModel statChartModel) {
		this.statChartModel = statChartModel;
	}

	public StatStaffAvgWaitTimeResultMO getStatStaffAvgWaitTimeResultMo() {
		return statStaffAvgWaitTimeResultMo;
	}

	public void setStatStaffAvgWaitTimeResultMo(StatStaffAvgWaitTimeResultMO statStaffAvgWaitTimeResultMo) {
		this.statStaffAvgWaitTimeResultMo = statStaffAvgWaitTimeResultMo;
	}

	public StatStaffExchangeRateResultMO getStatStaffExchangeRateResultMo() {
		return statStaffExchangeRateResultMo;
	}

	public void setStatStaffExchangeRateResultMo(StatStaffExchangeRateResultMO statStaffExchangeRateResultMo) {
		this.statStaffExchangeRateResultMo = statStaffExchangeRateResultMo;
	}

	public StatStaffWorkLoadResultMO getStatStaffWorkLoadResultMO() {
		return statStaffWorkLoadResultMO;
	}

	public void setStatStaffWorkLoadResultMO(StatStaffWorkLoadResultMO statStaffWorkLoadResultMO) {
		this.statStaffWorkLoadResultMO = statStaffWorkLoadResultMO;
	}

	public StatStaffManBuyResultMO getStatStaffManBuyResultMO() {
		return statStaffManBuyResultMO;
	}

	public void setStatStaffManBuyResultMO(StatStaffManBuyResultMO statStaffManBuyResultMO) {
		this.statStaffManBuyResultMO = statStaffManBuyResultMO;
	}

	public StatStaffRefundRateResultMO getStatStaffRefundRateResultMO() {
		return statStaffRefundRateResultMO;
	}

	public void setStatStaffRefundRateResultMO(StatStaffRefundRateResultMO statStaffRefundRateResultMO) {
		this.statStaffRefundRateResultMO = statStaffRefundRateResultMO;
	}

	public StatStaffOnlineTimeResultMO getStatStaffOnlineTimeResultMo() {
		return statStaffOnlineTimeResultMo;
	}

	public void setStatStaffOnlineTimeResultMo(StatStaffOnlineTimeResultMO statStaffOnlineTimeResultMo) {
		this.statStaffOnlineTimeResultMo = statStaffOnlineTimeResultMo;
	}

	public List<GroupMemberEO> getMembers() {
		return members;
	}

	public void setMembers(List<GroupMemberEO> members) {
		this.members = members;
	}

	public StatStaffTradeNumResultMO getStatStaffTradeNumResultMo() {
		return statStaffTradeNumResultMo;
	}

	public void setStatStaffTradeNumResultMo(StatStaffTradeNumResultMO statStaffTradeNumResultMo) {
		this.statStaffTradeNumResultMo = statStaffTradeNumResultMo;
	}

	public StatAchievementResultMO getSelectStatResultMo() {
		return selectStatResultMo;
	}

	public void setSelectStatResultMo(StatAchievementResultMO selectStatResultMo) {
		this.selectStatResultMo = selectStatResultMo;
	}

}
