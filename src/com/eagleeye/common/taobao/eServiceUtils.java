package com.eagleeye.common.taobao;

import com.eagleeye.eservice.eo.OrderEO;
import com.eagleeye.eservice.eo.OrderEOId;
import com.eagleeye.eservice.eo.RefundEO;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.eo.TradeRateEO;
import com.eagleeye.eservice.eo.TradeRateEOId;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Refund;
import com.taobao.api.domain.Trade;
import com.taobao.api.domain.TradeRate;

public class eServiceUtils {
	/**
	 * 交易变量从接口对象转换为内部EO对象
	 * 
	 * @param trade
	 * @param tradeEO
	 */
	public static void copyTradeToTradeEO(Trade trade, TradeEO tradeEO) {
		tradeEO.setAdjustFee(Float.valueOf(trade.getAdjustFee() == null ? "0.0" : trade.getAdjustFee()));
		tradeEO.setBuyerNick(trade.getBuyerNick());
		tradeEO.setBuyerRate(trade.getBuyerRate());
		tradeEO.setCodFee(Float.valueOf(trade.getCodFee() == null ? "0.0" : trade.getCodFee()));
		tradeEO.setCommisionFee(Float.valueOf(trade.getCommissionFee() == null ? "0.0" : trade.getCommissionFee()));
		tradeEO.setCreated(trade.getCreated());
		tradeEO.setDiscountFee(Float.valueOf(trade.getDiscountFee() == null ? "0.0" : trade.getDiscountFee()));
		tradeEO.setEndTime(trade.getEndTime());
		tradeEO.setModified(trade.getModified());
		tradeEO.setNum(trade.getNum());
		tradeEO.setNumIid(trade.getNumIid());
		tradeEO.setPayment(Float.valueOf(trade.getPayment() == null ? "0.0" : trade.getPayment()));
		tradeEO.setPayTime(trade.getPayTime());
		tradeEO.setPayTimeAdd(trade.getPayTime());
		tradeEO.setPostFee(Float.valueOf(trade.getPostFee() == null ? "0.0" : trade.getPostFee()));
		tradeEO.setReceivedPayment(Float.valueOf(trade.getReceivedPayment() == null ? "0.0" : trade
				.getReceivedPayment()));
		tradeEO.setSellerNick(trade.getSellerNick());
		tradeEO.setSellerRate(trade.getSellerRate());
		tradeEO.setShippingType(trade.getShippingType());
		tradeEO.setStatus(trade.getStatus());
		tradeEO.setTid(trade.getTid());
		tradeEO.setTitle(trade.getTitle());
		tradeEO.setSellerFlag(trade.getSellerFlag());
		tradeEO.setTotalFee(Float.valueOf(trade.getTotalFee() == null ? "0.0" : trade.getTotalFee()));
	}

	/**
	 * 交易评价从接口对象转换为内部EO对象
	 * 
	 * @param tradeRate
	 * @param tradeRateEO
	 */
	public static void copyTradeRateToTradeRateEO(TradeRate tradeRate, TradeRateEO tradeRateEO) {
		if (tradeRate.getOid() != null && tradeRate.getTid() != null) {
			tradeRateEO.setContent(tradeRate.getContent());
			tradeRateEO.setCreated(tradeRate.getCreated());
			TradeRateEOId id = tradeRateEO.getId();
			id.setOid(tradeRate.getOid());
			id.setTid(tradeRate.getTid());
			tradeRateEO.setId(id);
			tradeRateEO.setItemTitle(tradeRate.getItemTitle());
			tradeRateEO.setNick(tradeRate.getNick());
			tradeRateEO.setRatedNick(tradeRate.getRatedNick());
			tradeRateEO.setReply(tradeRate.getReply());
			tradeRateEO.setResult(tradeRate.getResult());
			tradeRateEO.setValidScore(tradeRate.getValidScore());
		}
	}

	// /**
	// * 交易帐务变量从接口对象转换为内部EO对象
	// *
	// * @param tradeAmount
	// * @param tradeAmountEO
	// */
	// public static void copyTradeAmountTOTradeAmountEO(TradeAmount
	// tradeAmount,
	// TradeAmountEO tradeAmountEO) {
	// if (tradeAmountEO != null && tradeAmount != null) {
	// tradeAmountEO.setAlipayNo(tradeAmount.getAlipayNo());
	// tradeAmountEO
	// .setCodFee(Float.valueOf(tradeAmount.getCodFee() == null ? "0.0"
	// : tradeAmount.getCodFee()));
	// tradeAmountEO.setCommisionFee(Float.valueOf(tradeAmount
	// .getCommissionFee() == null ? "0.0" : tradeAmount
	// .getCommissionFee()));
	// tradeAmountEO.setCreated(tradeAmount.getCreated());
	// tradeAmountEO.setEndTime(tradeAmountEO.getEndTime());
	// tradeAmountEO
	// .setPayment(Float.valueOf(tradeAmount.getPayment() == null ? "0.0"
	// : tradeAmount.getPayment()));
	// tradeAmountEO.setPayTime(tradeAmount.getPayTime());
	// tradeAmountEO
	// .setPostFee(Float.valueOf(tradeAmount.getPostFee() == null ? "0.0"
	// : tradeAmount.getPostFee()));
	// tradeAmountEO.setTid(tradeAmount.getTid());
	// tradeAmountEO
	// .setTotalFee(Float
	// .valueOf(tradeAmount.getTotalFee() == null ? "0.0"
	// : tradeAmount.getTotalFee()));
	// if (tradeAmount.getOrderAmounts() != null) {
	// List<OrderAmountEO> orderAmountEOs = new ArrayList();
	// for (OrderAmount orderAmount : tradeAmount.getOrderAmounts()) {
	// OrderAmountEO orderAmountEO = new OrderAmountEO();
	// copyOrderAmountTOOrderAmountEO(orderAmount, orderAmountEO,
	// tradeAmount.getTid());
	// orderAmountEOs.add(orderAmountEO);
	// }
	// if (!orderAmountEOs.isEmpty()) {
	// tradeAmountEO.setOrderAmounts(new HashSet(orderAmountEOs));
	// }
	// }
	// }
	// }

	/**
	 * 订单变量从接口对象转换为内部EO对象
	 * 
	 * @param order
	 * @param orderEO
	 * @param tid
	 */
	public static void copyOrderToOrderEO(Order order, OrderEO orderEO, Long tid) {
		if (order != null && orderEO != null) {
			orderEO.setAdjustFee(Float.valueOf(order.getAdjustFee() == null ? "0.0" : order.getAdjustFee()));
			orderEO.setBuyerRate(order.getBuyerRate());
			orderEO.setDiscountFee(Float.valueOf(order.getDiscountFee() == null ? "0.0" : order.getDiscountFee()));
			OrderEOId id = orderEO.getId();
			id.setOid(order.getOid());
			id.setTid(tid);
			orderEO.setId(id);
			orderEO.setItemMealName(order.getItemMealName());
			orderEO.setNum(order.getNum());
			orderEO.setNumIid(order.getNumIid());
			orderEO.setPayment(Float.valueOf(order.getPayment() == null ? "0.0" : order.getPayment()));
			orderEO.setPicPath(order.getPicPath());
			orderEO.setPrice(Float.valueOf(order.getPrice() == null ? "0.0" : order.getPrice()));
			orderEO.setRefundId(order.getRefundId());
			orderEO.setRefundStatus(order.getRefundStatus());
			orderEO.setSellerRate(order.getSellerRate());
			orderEO.setSellerType(order.getSellerType());
			orderEO.setStatus(order.getStatus());
			orderEO.setTitle(order.getTitle());
			orderEO.setTotalFee(Float.valueOf(order.getTotalFee() == null ? "0.0" : order.getTotalFee()));
		}
	}

	// /**
	// * 订单帐务变量从接口对象转换为内部EO对象
	// *
	// * @param orderAmount
	// * @param orderAmountEO
	// * @param tid
	// */
	// public static void copyOrderAmountTOOrderAmountEO(OrderAmount
	// orderAmount,
	// OrderAmountEO orderAmountEO, Long tid) {
	// if (orderAmount != null && orderAmountEO != null) {
	// orderAmountEO
	// .setAdjustFee(Float
	// .valueOf(orderAmount.getAdjustFee() == null ? "0.0"
	// : orderAmount.getAdjustFee()));
	// orderAmountEO.setDiscountFee(Float.valueOf(orderAmount
	// .getDiscountFee() == null ? "0.0" : orderAmount
	// .getDiscountFee()));
	// OrderAmountEOId id = new OrderAmountEOId();
	// id.setOid(orderAmount.getOid());
	// id.setTid(tid);
	// orderAmountEO.setId(id);
	// orderAmountEO.setNum(orderAmount.getNum());
	// orderAmountEO.setNumIid(orderAmount.getNumIid());
	// orderAmountEO
	// .setPayment(Float.valueOf(orderAmount.getPayment() == null ? "0.0"
	// : orderAmount.getPayment()));
	// orderAmountEO
	// .setPrice(Float.valueOf(orderAmount.getPrice() == null ? "0.0"
	// : orderAmount.getPrice()));
	// orderAmountEO.setPromotionName(orderAmount.getPromotionName());
	// orderAmountEO.setSkuId(orderAmount.getSkuId());
	// orderAmountEO.setSkuPropertiesName(orderAmount
	// .getSkuPropertiesName());
	// orderAmountEO.setTitle(orderAmount.getTitle());
	// }
	// }

	/**
	 * 退款变量从接口对象转换为内部EO对象
	 * 
	 * @param refund
	 * @param refundEO
	 */
	public static void copyRefundTORefundEO(Refund refund, RefundEO refundEO) {
		if (refund != null || refundEO != null) {
			refundEO.setBuyerNick(refund.getBuyerNick());
			refundEO.setCreated(refund.getCreated());
			refundEO.setDescription(refund.getDesc());
			refundEO.setGoodStatus(refund.getGoodStatus());
			refundEO.setHasGoodReturn(refund.getHasGoodReturn());
			refundEO.setModified(refund.getModified());
			refundEO.setOid(refund.getOid());
			refundEO.setOrderStatus(refund.getOrderStatus());
			refundEO.setPayment(Float.valueOf(refund.getPayment() == null ? "0.0" : refund.getPayment()));
			refundEO.setReason(refund.getReason());
			refundEO.setRefundFee(Float.valueOf(refund.getRefundFee() == null ? "0.0" : refund.getRefundFee()));
			refundEO.setRefundId(refund.getRefundId());
			refundEO.setSellerNick(refund.getSellerNick());
			refundEO.setStatus(refund.getStatus());
			refundEO.setTid(refund.getTid());
			refundEO.setTotalFee(Float.valueOf(refund.getTotalFee() == null ? "0.0" : refund.getTotalFee()));
		}
	}
}
