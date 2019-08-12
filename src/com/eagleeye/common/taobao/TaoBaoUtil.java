package com.eagleeye.common.taobao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.Messages;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.QueryParametersMo;
import com.eagleeye.common.util.DateUtil;
import com.taobao.api.internal.util.json.ExceptionErrorListener;
import com.taobao.api.internal.util.json.JSONReader;
import com.taobao.api.internal.util.json.JSONValidatingReader;

public class TaoBaoUtil {
	/**
	 * 增加Eservice前缀cntaobao
	 * 
	 * @param id
	 * @return
	 */
	public static String addEServicePrefix(String id) {
		id = EagleConstants.TAOBAOESERVICEPREFIX + id;
		return id;
	}

	/**
	 * 去除Eservice前缀cntaobao
	 * 
	 * @param id
	 * @return
	 */
	public static String removeEServicePrefix(String id) {
		if (id.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
			id = id.substring(EagleConstants.TAOBAOESERVICEPREFIX.length());
		}
		return id;
	}

	public static String removeEServiceAllPrefix(String id) {
		id = id.replaceAll(EagleConstants.TAOBAOESERVICEPREFIX, "");
		return id;
	}

	public static String generateShopId() {
		UUID shopId = UUID.randomUUID();
		return shopId.toString();
	}

	public static Map<String, String> convertBase64StringtoMap(String str) {
		if (str == null)
			return null;
		String keyvalues = null;
		try {
			str = URLEncoder.encode(str, "GBK");
			keyvalues = new String(Base64.decodeBase64(URLDecoder.decode(str, "GBK").getBytes("GBK")), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] keyvalueArray = keyvalues.split("\\&");
		Map<String, String> map = new HashMap<String, String>();
		for (String keyvalue : keyvalueArray) {
			String[] s = keyvalue.split("\\=");
			if (s == null || s.length != 2)
				return null;
			map.put(s[0], s[1]);
		}
		return map;
	}

	public static Map<String, String> convertBase64StringtoMap(Map<String, String> returnParams, String str) {
		if (str == null)
			return null;
		String keyvalues = null;
		try {
			str = URLEncoder.encode(str, "GBK");
			keyvalues = new String(Base64.decodeBase64(URLDecoder.decode(str, "GBK").getBytes("GBK")), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] keyvalueArray = keyvalues.split("\\&");
		for (String keyvalue : keyvalueArray) {
			String[] s = keyvalue.split("\\=");
			if (s == null || s.length != 2)
				return null;
			returnParams.put(s[0], s[1]);
		}
		return returnParams;
	}

	public static Map<String, String> convertStringtoMapOAuth(Map<String, String> returnParams, String str) {
		if (str == null)
			return null;
		// str = str.replaceAll("\"", "");
		JSONReader reader = new JSONValidatingReader(new ExceptionErrorListener());
		Map rootObj = (Map) reader.read(str);
		for (Object key : rootObj.keySet()) {
			if (EagleConstants.OAUTH_PARAM_MAP.get(key) != null) {
				returnParams.put(EagleConstants.OAUTH_PARAM_MAP.get(key), String.valueOf(rootObj.get(key)));
			}
		}
		return returnParams;
	}

	public static String ParametersName(String top_parameters) {
		String nick = null;
		Map<String, String> map = convertBase64StringtoMap(top_parameters);
		Iterator keyValuePairs = map.entrySet().iterator();
		for (int i = 0; i < map.size(); i++) {
			Map.Entry entry = (Map.Entry) keyValuePairs.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (key.equals("visitor_nick")) {
				nick = (String) value;
				break;
			}
		}
		return nick;
	}

	public static HttpServletResponse getHttpResponse() {
		ExternalContext ec = getHttpContext();
		return (HttpServletResponse) ec.getResponse();
	}

	public static ExternalContext getHttpContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		return ec;
	}

	public static boolean validQueryParams(QueryParametersMo param) {
		if (!valideDateGap(param.getStartTime(), param.getEndTime())) {
			return false;
		}
		if (param.getStaffId() == null || param.getStaffId().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getString("NoStaffId_brief"), Messages
							.getString("NoStaffId_detail")));
			return false;
		}
		return true;
	}

	public static boolean valideDateGap(Date start, Date end) {
		if (start == null || end == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getString("taobao_util_warning_brief.0"),
							Messages.getString("taobao_util_warning_detail.0")));
			return false;
		}
		if (start.after(end)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getString("taobao_util_warning_brief.1"),
							Messages.getString("taobao_util_warning_detail.1")));
			return false;
		}
		if (start != null && end != null && DateUtil.getDateGap(start, end) > EagleConstants.SELECT_DATE_GAP) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getString("taobao_util_warning_brief.2"),
							Messages.getString("taobao_util_warning_detail.2")));
			return false;
		}
		return true;
	}

	/**
	 * 根据错误码，检查是否重跑
	 * 
	 * @param errorCode
	 * @return
	 */
	public static Boolean checkReRunErrorCode(String errorCode) {
		for (String reRunErrorCode : EagleConstants.RERUN_ERROR_CODES) {
			if (reRunErrorCode.equalsIgnoreCase(errorCode)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取一月前的查询条件
	 */
	public static void getPreviousMonthQueryMo(QueryParametersMo queryParametersMo) {
		Date startDate = DateUtil.getSimpleDate(DateUtil.getPriorMonthDate(new Date()));
		Date endDate = DateUtil.getActualMaximumDate(startDate);
		queryParametersMo.setEndTime(endDate);
		queryParametersMo.setStartTime(startDate);
	}

	/**
	 * 获取一周前的查询条件
	 */
	public static void getPreviousWeekQueryMo(QueryParametersMo queryParametersMo) {
		Date endDate = DateUtil.getPreviousDay(DateUtil.getSimpleDate(new Date()), 1);
		Date startDate = DateUtil.getPreviousDay(endDate, 7);
		queryParametersMo.setEndTime(endDate);
		queryParametersMo.setStartTime(startDate);
	}

	/**
	 * 获取前一天的查询条件
	 */
	public static void getPreviousDayQueryMo(QueryParametersMo queryParametersMo) {
		Date startDate = DateUtil.getPreviousDay(DateUtil.getSimpleDate(new Date()), 1);
		queryParametersMo.setEndTime(startDate);
		queryParametersMo.setStartTime(startDate);
	}

	/**
	 * 根据类型获取查询条件
	 * 
	 * @param queryParametersMo
	 * @param type
	 */
	public static void getPreviousQueryMoByType(QueryParametersMo queryParametersMo) {
		String type = (String) getHttpContext().getRequestParameterMap().get("type");
		if (type != null) {
			if (EagleConstants.QUERY_TYPE_LAST_DAY.equals(type)) {
				TaoBaoUtil.getPreviousDayQueryMo(queryParametersMo);
			} else if (EagleConstants.QUERY_TYPE_LAST_WEEK.equals(type)) {
				TaoBaoUtil.getPreviousWeekQueryMo(queryParametersMo);
			} else if (EagleConstants.QUERY_TYPE_LAST_MONTH.equals(type)) {
				TaoBaoUtil.getPreviousMonthQueryMo(queryParametersMo);
			}
		}
	}

	private static Map<String, String> menuUrlMap = null;

	private static void initMenuUrlMap() {
		menuUrlMap = new HashMap<String, String>();
		// 店铺
		menuUrlMap.put("店铺经营状况", "StatAchievement.xhtml");
		menuUrlMap.put("店铺业绩图表", "StatAchievementShopChart.xhtml");
		menuUrlMap.put("店铺业绩件数图表", "StatAchievementNumShopChart.xhtml");
		menuUrlMap.put("店铺客单价", "StatShopManBuyChart.xhtml");
		menuUrlMap.put("店铺转换率", "StatShopExchangeRateChart.xhtml");
		menuUrlMap.put("店铺工作量", "StatShopWorkLoadChart.xhtml");
		menuUrlMap.put("店铺平均响应", "StatShopAvgWaitTimeChart.xhtml");
		menuUrlMap.put("店铺退款率", "StatShopRefundRateChart.xhtml");
		// 客服明细
		menuUrlMap.put("客服业绩金额明细", "StaffAchievementDtl.xhtml");
		menuUrlMap.put("客服业绩件数明细", "StaffAchievementNumDtl.xhtml");
		menuUrlMap.put("客服首次响应时间明细", "StaffAvgWaitTimeDtl.xhtml");
		menuUrlMap.put("客服工作量明细", "StaffWorkLoadDtl.xhtml");
		menuUrlMap.put("客服转换率明细", "StaffExchangeRateDtl.xhtml");
		menuUrlMap.put("客服客单价明细", "StaffManBuyDtl.xhtml");
		menuUrlMap.put("客服退款金额率明细", "StaffRefundRateDtl.xhtml");
		menuUrlMap.put("客服在线时间明细", "StaffOnlineTimeDtl.xhtml");
		// 客服比较
		menuUrlMap.put("客服业绩状况", "StatOwnersAchievement.xhtml");
		menuUrlMap.put("客服交易数目状况", "StatOwnersNumAchievement.xhtml");
		menuUrlMap.put("客服平均响应时间", "StatStaffAvgWaitTimeChart.xhtml");
		menuUrlMap.put("客服工作量", "StatStaffWorkLoadChart.xhtml");
		menuUrlMap.put("客服转换率", "StatStaffExchangeRateChart.xhtml");
		menuUrlMap.put("客服客单价", "StatStaffManBuyChart.xhtml");
		menuUrlMap.put("客服退款率", "StatStaffRefundRateChart.xhtml");
		menuUrlMap.put("客服在线时间", "StatStaffOnlineTimeChart.xhtml");
		// 排行榜
		menuUrlMap.put("商品销售金额排行榜", "GoodsSaleAmountRank.xhtml");
		menuUrlMap.put("商品销售数量排行榜", "GoodsSaleNumRank.xhtml");
		menuUrlMap.put("商品退款金额排行榜", "GoodsRefundRank.xhtml");
		menuUrlMap.put("客户购买金额排行榜", "CustomerSaleAmountRank.xhtml");
		menuUrlMap.put("客户退款金额排行榜", "CustomerRefundRank.xhtml");
		// 交易管理
		menuUrlMap.put("已付款待发货交易", "PayedTradeDetail.xhtml");
		menuUrlMap.put("已发货待确认交易", "SentTradeDetail.xhtml");
		menuUrlMap.put("已完成交易", "FinishedTradeDetail.xhtml");
		// 中差评
		menuUrlMap.put("中差评管理", "TradeRateDetail.xhtml");
		// 工资管理
		menuUrlMap.put("通用工资模版设置", "SalaryConfig.xhtml");
		menuUrlMap.put("个性化工资模板设置", "SpecialSalaryConfig.xhtml");
		menuUrlMap.put("客服工资模板指定", "SalaryConfigOwner.xhtml");
		menuUrlMap.put("工资结算", "SalaryCalculate.xhtml");
		menuUrlMap.put("工资已结算历史查询", "SalarySettlementHistory.xhtml");
		// 系统设置
		menuUrlMap.put("客服管理", "StaffSetting.xhtml");
		menuUrlMap.put("商品过滤管理", "ItemSetting.xhtml");
		menuUrlMap.put("业绩归属方式管理", "BelongTypeSetting.xhtml");
		menuUrlMap.put("密码解锁及重置", "ReadModifySetting.xhtml");
		menuUrlMap.put("查看权限设置", "ReadLockSetting.xhtml");
	}

	public static Map getMenuUrlMap() {
		if (menuUrlMap != null) {
			return menuUrlMap;
		} else {
			initMenuUrlMap();
			return menuUrlMap;
		}
	}

	public static String getOAuthURL(String code) {
		StringBuilder url = new StringBuilder();
		url.append("https://oauth.taobao.com/token?grant_type=authorization_code&amp;client_id=");
		url.append(EagleConstants.APPKEY + "&amp;client_secret=" + EagleConstants.APPSECRET + "&ampcode=" + code
				+ "&redirect_uri=" + EagleConstants.BASEURL);
		return url.toString();
	}

	// private static void initReadLockMenu() {
	// root = new DefaultTreeNode("root", null);
	// // 店铺绩效
	// TreeNode statShopTopNode = new DefaultTreeNode("店铺绩效", root);
	// new DefaultTreeNode("店铺经营状况", statShopTopNode);
	// new DefaultTreeNode("店铺业绩图表", statShopTopNode);
	// new DefaultTreeNode("店铺客单价", statShopTopNode);
	// new DefaultTreeNode("店铺转换率", statShopTopNode);
	// new DefaultTreeNode("店铺工作量", statShopTopNode);
	//
	// }

	// public static TreeNode getReadLockMenu() {
	// if (root != null) {
	// return root;
	// } else {
	// initReadLockMenu();
	// return root;
	// }
	// }

	public static void main(String[] args) {
		try {
			String str = "森林里的木楼";
			//str = new String(Base64.decodeBase64(URLDecoder.decode(str, "UTF8").getBytes("UTF8")), "UTF8");
			String a="";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
