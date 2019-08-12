package com.eagleeye.eservice.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lousenwei
 * 
 */
public class EServiceConstants {

	/********************** 接口字段 **********************/
	// 交易接口和交易增量接口字段
	public static final String INTERFACES_TRADE_INFO_FIELDS = "seller_nick, buyer_nick, title, created, tid, seller_rate, buyer_rate, status, payment, discount_fee, adjust_fee, post_fee, total_fee, pay_time, end_time, modified, received_payment, commission_fee, num_iid, num, cod_fee, shipping_type,seller_flag, orders.title, orders.price, orders.num, orders.num_iid, orders.refund_status, orders.status, orders.oid, orders.total_fee, orders.payment, orders.discount_fee, orders.adjust_fee, orders.item_meal_name, orders.buyer_rate, orders.seller_rate, orders.refund_id, orders.seller_type, orders.pic_path";

	// 退款信息接口字段
	public static final String INTERFACES_REFUND_INFO_FIELDS = "refund_id, tid, title, buyer_nick, seller_nick, total_fee, status, created, refund_fee, oid, good_status, company_name, sid, payment, reason, desc, has_good_return, modified, order_status";

	// 交易评价接口
	public static final String INTERFACES_TRADE_RATE_FIELDS = "tid,oid,role,rated_nick,nick,result,created,item_title,content,reply,valid_score";
	/********************** 交易状态 **********************/
	/**
	 * * TRADE_NO_CREATE_PAY(没有创建支付宝交易)
	 */
	public static final String TRADE_STATUS_TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";

	/**
	 * * WAIT_BUYER_PAY(等待买家付款)
	 */
	public static final String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

	/**
	 * * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款)
	 */
	public static final String TRADE_STATUS_WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";

	/**
	 * * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货)
	 */
	public static final String TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";

	/**
	 * * TRADE_BUYER_SIGNED(买家已签收,货到付款专用)
	 */
	public static final String TRADE_STATUS_TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";

	/**
	 * * TRADE_FINISHED(交易成功)
	 */
	public static final String TRADE_STATUS_TRADE_FINISHED = "TRADE_FINISHED";

	/**
	 * * TRADE_CLOSED(付款以后用户退款成功，交易自动关闭)
	 */
	public static final String TRADE_STATUS_TRADE_CLOSED = "TRADE_CLOSED";

	/**
	 * * TRADE_CLOSED_BY_TAOBAO(付款以前，卖家或买家主动关闭交易)
	 */
	public static final String TRADE_STATUS_TRADE_CLOSED_BY_TAOBAO = "TRADE_CLOSED_BY_TAOBAO";

	/********************** 退款状态 **********************/
	/**
	 * CLOSED(退款关闭)
	 */
	public static final String REFUND_STATUS_CLOSED = "CLOSED";

	/**
	 * SELLER_REFUSE_BUYER（卖家拒绝退款）
	 */
	public static final String REFUND_STATUS_SELLER_REFUSE_BUYER = "SELLER_REFUSE_BUYER";

	/**
	 * SUCCESS(退款成功)
	 */
	public static final String REFUND_STATUS_SUCCESS = "SUCCESS";

	/**
	 * WAIT_SELLER_CONFIRM_GOODS(等待卖家确认收到货物)
	 */
	public static final String REFUND_STATUS_WAIT_SELLER_CONFIRM_GOODS = "WAIT_SELLER_CONFIRM_GOODS";

	/**
	 * WAIT_BUYER_RETURN_GOODS（等待买家返回货物）
	 */
	public static final String REFUND_STATUS_WAIT_BUYER_RETURN_GOODS = "WAIT_BUYER_RETURN_GOODS";

	/**
	 * WAIT_SELLER_AGREE（等待卖家同意）
	 */
	public static final String REFUND_STATUS_WAIT_SELLER_AGREE = "WAIT_SELLER_AGREE";

	/**
	 * NO_REFUND(无退款)
	 */
	public static final String REFUND_STATUS_NO_REFUND = "NO_REFUND";

	/********************* start 更新日志item常量 *************************/
	public static final String UPDATE_LOG_ITEM_AVGWAITTIME = "AVGWAITTIME";

	public static final String UPDATE_LOG_ITEM_REFUND = "REFUND";

	public static final String UPDATE_LOG_ITEM_CHATPEERS = "CHATPEERS";

	public static final String UPDATE_LOG_ITEM_NONREPLYNUM = "NONREPLYNUM";

	public static final String UPDATE_LOG_ITEM_ONLINETIME = "ONLINETIME";

	public static final String UPDATE_LOG_ITEM_RECEIVENUM = "RECEIVENUM";

	public static final String UPDATE_LOG_ITEM_TRADEINFO = "TRADEINFO";

	public static final String UPDATE_LOG_ITEM_TRADERATE = "TRADERATE";

	public static final String UPDATE_LOG_ITEM_LOGINLOGS="LOGINLOGS";

	public static final String UPDATE_LOG_ITEM_CHATLOGS="CHATLOGS";

	public static final Map<String,Integer> UPDATE_LOG_PRIORITY_MAP=new HashMap();
	//2017-3-17，添加loginlogs，chatlogs，初始化优先级Map
	static{
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_LOGINLOGS,3);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_ONLINETIME,4);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_CHATPEERS,1);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_CHATLOGS,2);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_RECEIVENUM,5);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_RECEIVENUM,5);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_AVGWAITTIME,6);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_NONREPLYNUM,6);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_REFUND,7);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_TRADEINFO,8);
		UPDATE_LOG_PRIORITY_MAP.put(UPDATE_LOG_ITEM_TRADERATE,9);
	}
	/********************* end 更新日志item常量 *************************/

	/********************* start 数据类型 *************************/
	public static final String FEE_TYPE_ALL_REFUND = "ALL_REFUND";

	public static final String FEE_TYPE_PART_REFUND = "PART_REFUND";

	public static final String FEE_TYPE_REFUNDING = "REFUNDING";

	/********************* end 数据类型 *************************/

	/**
	 * 数据导入时间间隔
	 */
	public static final int UPDATE_TIME_START_GAP = 1;

	public static final int UPDATE_TIME_END_GAP = 7;

	/*********************** 中差评常量 *******************************/
	public static final String TRADE_RATE_GOOD = "good";

	public static final String TRADE_RATE_NEUTRAL = "neutral";

	public static final String TRADE_RATE_BAD = "bad";
}
