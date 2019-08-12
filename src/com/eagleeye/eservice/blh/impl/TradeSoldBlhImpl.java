package com.eagleeye.eservice.blh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.PageResult;
import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.taobao.eServiceUtils;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.blh.ITradeSoldBlh;
import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.eservice.dao.impl.OrderDAO;
import com.eagleeye.eservice.dao.impl.TradeSoldDAO;
import com.eagleeye.eservice.eo.OrderEO;
import com.eagleeye.eservice.eo.OrderEOId;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.mo.TradeQueryParametersMo;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.TradesSoldIncrementGetRequest;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.TradesSoldIncrementGetResponse;

public class TradeSoldBlhImpl implements ITradeSoldBlh {
	private Logger log = Logger.getLogger(TradeSoldBlhImpl.class);
	TradeSoldDAO tradeSoldDao;
	OrderDAO orderDao;

	@Override
	public List<TradeEO> loadTradeIncrementInfo(String serviceStaffId, Date startTime, Date endTime, String sessionKey)
			throws Exception {
		// TODO Auto-generated method stub
		// 得到sessionkey
		if (sessionKey == null)
			sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		if (sessionKey == null)
			throw new Exception(serviceStaffId + " no session key!");
		// 封装taobaorequest
		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);
		TradesSoldIncrementGetRequest req = new TradesSoldIncrementGetRequest();
		// 判断淘宝客户id是否cntaobao为前缀，不是则添加
		if (!serviceStaffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
			serviceStaffId = TaoBaoUtil.addEServicePrefix(serviceStaffId);
		}
		req.setFields(EServiceConstants.INTERFACES_TRADE_INFO_FIELDS);
		req.setStartModified(startTime);
		req.setEndModified(endTime);
		req.setPageSize(40L);
		req.setUseHasNext(true);
		Boolean hasNext = true;
		Long pageNum = 1L;
		Map<Long, TradeEO> tradeEOs = new HashMap();
		while (hasNext) {
			req.setPageNo(pageNum);
			pageNum++;
			TradesSoldIncrementGetResponse response = client.execute(req, sessionKey);
			List<Trade> trades = new ArrayList();
			if (response != null) {
				trades = response.getTrades();
			}
			// 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
			// 最多重跑3次
			if ((response == null || response.getErrorCode() != null)
					&& TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
				int count = 0;
				Boolean needReRun = true;
				while (count < 3 && needReRun) {
					// 线程休眠半秒
					Thread.sleep(500);
					// count自增
					count++;
					response = client.execute(req, sessionKey);
					if (response != null && response.getTrades() == null) {
						trades = response.getTrades();
						needReRun = false;
					}
				}
				log.error(serviceStaffId + " " + sessionKey + " " + startTime + "-" + endTime + " Trade Rerun time:"
						+ count);
			}
			// end
			// 是否存在下一页
			if (response != null) {
				hasNext = response.getHasNext();
			}
			if (hasNext == null) {
				hasNext = false;
				if (response.getErrorCode() != null && !response.getErrorCode().isEmpty()) {
					// log.error(serviceStaffId + " " + response.getMsg());
					throw new Exception(serviceStaffId + " " + response.getMsg());
					// break;
				}
			}
			// 遍历存数据
			if (trades != null && !trades.isEmpty()) {
				for (Trade temp : trades) {
					if (Float.valueOf(temp.getPayment()) < 10000000.0) {
						// TODO:增加判断，如果存在，则无需重新指定归属人
						TradeEO tradeEO = (TradeEO) tradeSoldDao.getData(TradeEO.class, temp.getTid());
						if (tradeEO == null) {
							tradeEO = new TradeEO();
						}
						eServiceUtils.copyTradeToTradeEO(temp, tradeEO);
						this.copyOrders(temp, tradeEO);
						log.info("交易归属：" + tradeEO.getTradeOwner() + " TID:" + tradeEO.getTid() + "Payment"
								+ tradeEO.getPayment());
						// end
						tradeEOs.put(tradeEO.getTid(), tradeEO);
					}
				}
			}
		}
		if (!tradeEOs.isEmpty()) {
			// TODO:save tradeEOs
			tradeSoldDao.saveOrUpdateBatch(tradeEOs.values());
			log.info(serviceStaffId + "增量交易信息添加成功");
		}
		return new ArrayList(tradeEOs.values());
	}

	private void copyOrders(Trade temp, TradeEO tradeEO) {
		if (temp.getOrders() != null && !temp.getOrders().isEmpty()) {
			Map<Long, OrderEO> orderEOs = new HashMap();
			for (Order order : temp.getOrders()) {
				OrderEO orderEO = (OrderEO) orderDao.getData(OrderEO.class,
						new OrderEOId(order.getOid(), temp.getTid()));
				if (orderEO == null) {
					orderEO = new OrderEO();
					OrderEOId id = new OrderEOId();
					orderEO.setId(id);
				}
				eServiceUtils.copyOrderToOrderEO(order, orderEO, temp.getTid());
				orderEOs.put(orderEO.getId().getOid(), orderEO);
			}
			if (!orderEOs.isEmpty()) {
				tradeEO.setOrders(new HashSet(orderEOs.values()));
			}
		}
	}

	@Override
	public List<TradeEO> findAllTradeByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadTradeAmountInfo(String serviceStaffId, Date startTime, Date endTime, String sessionKey)
			throws Exception {

	}

	public TradeSoldDAO getTradeSoldDao() {
		return tradeSoldDao;
	}

	public void setTradeSoldDao(TradeSoldDAO tradeSoldDao) {
		this.tradeSoldDao = tradeSoldDao;
	}

	@Override
	public void loadTradeInfo(String serviceStaffId, Date startTime, Date endTime, String sessionKey) throws Exception {
		// TODO Auto-generated method stub
		// 得到sessionkey
		if (sessionKey == null)
			sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		if (sessionKey == null)
			throw new Exception("no sessionkey!!!");
		// 封装taobaorequest
		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);
		TradesSoldGetRequest req = new TradesSoldGetRequest();
		// 判断淘宝客户id是否cntaobao为前缀，不是则添加
		if (!serviceStaffId.startsWith(EagleConstants.TAOBAOESERVICEPREFIX)) {
			serviceStaffId = TaoBaoUtil.addEServicePrefix(serviceStaffId);
		}
		req.setFields(EServiceConstants.INTERFACES_TRADE_INFO_FIELDS);
		req.setStartCreated(startTime);
		req.setEndCreated(endTime);
		req.setPageSize(40L);
		Boolean hasNext = true;
		Long pageNum = 1L;
		List<TradeEO> tradeEOs = new ArrayList();
		while (hasNext) {
			req.setPageNo(pageNum);
			TradesSoldGetResponse response = client.execute(req, sessionKey);
			List<Trade> trades = new ArrayList();
			if (response != null) {
				trades = response.getTrades();
			}
			// 最多重跑3次
			if ((response == null || response.getErrorCode() != null)
					&& TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
				int count = 0;
				Boolean needReRun = true;
				while (count < 3 && needReRun) {
					// 线程休眠半秒
					Thread.sleep(500);
					// count自增
					count++;
					response = client.execute(req, sessionKey);
					if (response != null && response.getTrades() != null) {
						trades = response.getTrades();
						needReRun = false;
					}
				}
				log.error(serviceStaffId + " " + sessionKey + " " + startTime + "-" + endTime + " Trade Rerun time:"
						+ count);
			}
			// 是否存在下一页
			if (response == null || response.getErrorCode() != null || (response.getTotalResults() - pageNum * 40) <= 0) {
				hasNext = false;
				if (response == null) {
					log.error(serviceStaffId + " " + sessionKey + " " + startTime + "-" + endTime
							+ " full trade response null!");
					break;
				}
				if (response != null && response.getErrorCode() != null) {
					log.error(serviceStaffId + " " + sessionKey + " " + startTime + "-" + endTime + " "
							+ response.getMsg());
					break;
				}
			}
			// 遍历存数据
			if (trades != null && !trades.isEmpty()) {
				for (Trade temp : trades) {
					if (Float.valueOf(temp.getPayment()) < 10000000.0) {
						TradeEO tradeEO = new TradeEO();
						eServiceUtils.copyTradeToTradeEO(temp, tradeEO);
						this.copyOrders(temp, tradeEO);
						tradeEOs.add(tradeEO);
					}
				}
			}
			log.info(serviceStaffId + "交易信息PAGE" + pageNum);
			pageNum++;
		}
		if (tradeEOs != null && !tradeEOs.isEmpty()) {
			// TODO:save tradeEOs
			tradeSoldDao.saveOrUpdateBatch(tradeEOs);
			log.info(startTime + "-" + endTime + ":" + serviceStaffId + "全量交易信息添加成功");
		}
	}

	/**
	 * 返回交易数据分页
	 * 
	 * @param queryMo
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	public PageResult getTradeEOsPagnationByManagerIdAndTimePreiod(TradeQueryParametersMo queryMo, int startNum,
			int pageSize) {
		return tradeSoldDao.getTradeEOsPagnationByManagerIdAndTimePreiod(queryMo, startNum, pageSize);
	}

	/**
	 * 更新tradeeo
	 * 
	 * @param trade
	 */
	public void updateTradeEO(TradeEO trade) {
		tradeSoldDao.updateData(trade);
	}

	/**
	 * 将本月交易归属人设置为null
	 * 
	 * @param managerId
	 */
	public int setCurrentMonthTradeOwnerToNull(String managerId) {
		String sql = "update trade set trade_owner=null where seller_nick=?";
		try {
			int i = tradeSoldDao.updateByJDBCsql(sql, new Object[] { managerId });
			return i;
		} catch (Exception e) {
			log.error(managerId + " 更新交易归属人为空失败");
		}
		return 0;
	}

	/**
	 * 根据TID获取交易
	 * 
	 * @param tradeId
	 * @return
	 */
	public List<OrderEO> getOrdersByTradeId(Long tradeId) {
		return orderDao.getOrdersByTradeId(tradeId);
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
}
