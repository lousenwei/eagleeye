package com.eagleeye.nopayclean.constant;

import java.util.HashMap;
import java.util.Map;

public class NoPayCleanConstants {

	public static void loadNoPayCleanConstants() {
		TOTALDELETESQLMAP.put("DELETE_SQL_WAITINGTIMESONDAY",
				DELETE_SQL_WAITINGTIMESONDAY);
		TOTALDELETESQLMAP.put("DELETE_SQL_UPDATELOG", DELETE_SQL_UPDATELOG);
		TOTALDELETESQLMAP.put("DELETE_SQL_TOTALUPDATEDETAIL",
				DELETE_SQL_TOTALUPDATEDETAIL);
		TOTALDELETESQLMAP.put("DELETE_SQL_STATACHIEVEMENT",
				DELETE_SQL_STATACHIEVEMENT);
		TOTALDELETESQLMAP.put("DELETE_SQL_STATACHIEVEMENTNUM",
				DELETE_SQL_STATACHIEVEMENTNUM);
		TOTALDELETESQLMAP.put("DELETE_SQL_SALARYCONFIG",
				DELETE_SQL_SALARYCONFIG);
		TOTALDELETESQLMAP.put("DELETE_SQL_REPLAYSTATONDAY",
				DELETE_SQL_REPLAYSTATONDAY);
		TOTALDELETESQLMAP.put("DELETE_SQL_REFUND", DELETE_SQL_REFUND);
		TOTALDELETESQLMAP.put("DELETE_SQL_ORDERS", DELETE_SQL_ORDERS);
		// TOTALDELETESQLMAP.put("DELETE_SQL_TRADE", DELETE_SQL_TRADE);
		TOTALDELETESQLMAP.put("DELETE_SQL_ONLINETIMESONDAY",
				DELETE_SQL_ONLINETIMESONDAY);
		TOTALDELETESQLMAP.put("DELETE_SQL_NOREPLAYSTATONDAY",
				DELETE_SQL_NOREPLAYSTATONDAY);
		TOTALDELETESQLMAP.put("DELETE_SQL_NOCALCULATEITEMS",
				DELETE_SQL_NOCALCULATEITEMS);
		TOTALDELETESQLMAP.put("DELETE_SQL_MANAGERINFO", DELETE_SQL_MANAGERINFO);
		TOTALDELETESQLMAP.put("DELETE_SQL_INCREMENTUPDATEDETAIL",
				DELETE_SQL_INCREMENTUPDATEDETAIL);
		TOTALDELETESQLMAP.put("DELETE_SQL_GROUPMEMBER", DELETE_SQL_GROUPMEMBER);
		// TOTALDELETESQLMAP.put("DELETE_SQL_CHATPEER", DELETE_SQL_CHATPEER);
		TOTALDELETESQLMAP.put("DELETE_SQL_BELONGTYPE", DELETE_SQL_BELONGTYPE);
		TOTALDELETESQLMAP.put("DELETE_SQL_SALARYSETTLEMENT",
				DELETE_SQL_SALARYSETTLEMENT);
		TOTALDELETESQLMAP.put("DELETE_SQL_SALARYCONFIGOWNER",
				DELETE_SQL_SALARYCONFIGOWNER);
		TOTALDELETESQLMAP.put("DELETE_SQL_READLOCKMAP", DELETE_SQL_READLOCKMAP);
		TOTALDELETESQLMAP.put("DELETE_SQL_TRADERATE", DELETE_SQL_TRADERATE);
	}

	public final static String DELETE_SQL_WAITINGTIMESONDAY = "DELETE FROM `eagleeye`.`waiting_times_on_day` where manager_id=?";
	public final static String DELETE_SQL_UPDATELOG = "DELETE FROM `eagleeye`.`updatelog` where manager_id=?";
	public final static String DELETE_SQL_TOTALUPDATEDETAIL = "DELETE FROM `eagleeye`.`total_update_detail` where manager_id=?";
	public final static String DELETE_SQL_STATACHIEVEMENT = "DELETE FROM `eagleeye`.`stat_achievement` where manager_id=?";
	public final static String DELETE_SQL_STATACHIEVEMENTNUM = "DELETE FROM `eagleeye`.`stat_achievement_num` where manager_id=?";
	public final static String DELETE_SQL_SALARYCONFIG = "DELETE FROM `eagleeye`.`salary_config` where manager_id=?";
	public final static String DELETE_SQL_REPLAYSTATONDAY = "DELETE FROM `eagleeye`.`reply_stat_on_day` where manager_id=?";
	public final static String DELETE_SQL_REFUND = "DELETE FROM `eagleeye`.`refund` where seller_nick=?";
	public final static String DELETE_SQL_ORDERS = "DELETE orders FROM orders,trade where trade.seller_nick=? and trade.tid=orders.tid";
	public final static String DELETE_SQL_TRADE = "DELETE FROM TradeEO where sellerNick=?";
	public final static String DELETE_SQL_ONLINETIMESONDAY = "DELETE FROM `eagleeye`.`online_times_on_day` where manager_id=?";
	public final static String DELETE_SQL_NOREPLAYSTATONDAY = "DELETE FROM `eagleeye`.`non_reply_stat_on_day` where manager_id=?";
	public final static String DELETE_SQL_NOCALCULATEITEMS = "DELETE FROM `eagleeye`.`no_calculate_items` where manager_id=?";
	public final static String DELETE_SQL_MANAGERINFO = "DELETE FROM `eagleeye`.`manager_info` where manager_id=?";
	public final static String DELETE_SQL_INCREMENTUPDATEDETAIL = "DELETE FROM `eagleeye`.`increment_update_detail` where manager_id=?";
	public final static String DELETE_SQL_GROUPMEMBER = "DELETE FROM `eagleeye`.`group_member` where manager_id=?";
	public final static String DELETE_SQL_CHATPEER = "DELETE FROM `eagleeye`.`chat_peer` where service_staff_id like ?";
	public final static String DELETE_SQL_BELONGTYPE = "DELETE FROM `eagleeye`.`belong_type` where manager_id=?";
	public final static String DELETE_SQL_SALARYSETTLEMENT = "DELETE FROM `eagleeye`.`salary_settlement` where manager_id=?";
	public final static String DELETE_SQL_SALARYCONFIGOWNER = "DELETE FROM `eagleeye`.`salary_config_owner` where manager_id=?";
	public final static String DELETE_SQL_TRADERATE = "DELETE FROM `eagleeye`.`trade_rate` where rated_nick=?";
	public final static String DELETE_SQL_READLOCKMAP = "DELETE FROM `eagleeye`.`read_lock_mapping` where manager_id=?";

	public final static Map<String, String> TOTALDELETESQLMAP = new HashMap();

}
