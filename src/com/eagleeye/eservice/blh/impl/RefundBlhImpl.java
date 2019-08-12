package com.eagleeye.eservice.blh.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.taobao.eServiceUtils;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.blh.IRefundBlh;
import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.eservice.dao.impl.RefundDAO;
import com.eagleeye.eservice.dao.impl.TradeSoldDAO;
import com.eagleeye.eservice.eo.RefundEO;
import com.eagleeye.eservice.eo.TradeEO;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Refund;
import com.taobao.api.request.RefundsReceiveGetRequest;
import com.taobao.api.response.RefundsReceiveGetResponse;

public class RefundBlhImpl implements IRefundBlh {
	private Logger log = Logger.getLogger(RefundBlhImpl.class);
	RefundDAO refundDao;
	// 2013.3.2 增加refund的paytime与trade的paytime同步功能
	TradeSoldDAO tradeSoldDao;

	@Override
	public List<RefundEO> loadRefund(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) throws Exception {
		// TODO Auto-generated method stub
		// 得到sessionkey
		if (sessionKey == null)
			sessionKey = (String) SessionManager
					.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		if (sessionKey == null)
			throw new Exception(serviceStaffId + " no session key!");
		// 封装taobaorequest
		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);
		RefundsReceiveGetRequest req = new RefundsReceiveGetRequest();
		req.setFields(EServiceConstants.INTERFACES_REFUND_INFO_FIELDS);
		req.setStartModified(startTime);
		req.setEndModified(endTime);
		req.setPageSize(40L);
		Boolean hasNext = true;
		Long pageNum = 1L;
		List<RefundEO> refundEOs = new ArrayList();
		while (hasNext) {
			req.setPageNo(pageNum);
			RefundsReceiveGetResponse response = client
					.execute(req, sessionKey);
			List<Refund> refunds = response.getRefunds();
			// 2011-10-29如果存在错误码为已定义需要重跑的，则重跑；
			// 最多重跑3次
			if (refunds == null && response.getErrorCode() != null
					&& TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
				int count = 0;
				Boolean needReRun = true;
				while (count < 3 && needReRun) {
					// 线程休眠半秒
					Thread.sleep(500);
					// count自增
					count++;
					response = client.execute(req, sessionKey);
					refunds = response.getRefunds();
					if (refunds != null || response.getErrorCode() == null) {
						needReRun = false;
					}
				}
				log.error(serviceStaffId + " " + sessionKey + " " + startTime
						+ "-" + endTime + " Refund Rerun time:" + count);
			}
			// end
			// 是否存在下一页
			if (response.getErrorCode() != null
					|| (response.getTotalResults() - pageNum * 40) <= 0) {
				hasNext = false;
				if (response.getErrorCode() != null) {
					log.error(serviceStaffId + " " + response.getMsg());
					break;
				}
			}

			if (refunds != null) {
				for (Refund temp : refunds) {
					RefundEO refundEO = new RefundEO();
					eServiceUtils.copyRefundTORefundEO(temp, refundEO);
					// 2013.3.2 增加refund的paytime与trade的paytime同步功能
					TradeEO tradeEO = (TradeEO) tradeSoldDao.getData(
							TradeEO.class, temp.getTid());
					if (tradeEO != null && tradeEO.getPayTime() != null) {
						refundEO.setPayTime(tradeEO.getPayTime());
					}
					// 2013.3.2 end
					refundEOs.add(refundEO);
				}
			} else if (response.getErrorCode() != null
					&& !response.getErrorCode().isEmpty()) {
				throw new Exception(response.getMsg());
			}
			log.info(serviceStaffId + "退款信息PAGE" + pageNum);
			pageNum++;
		}
		if (!refundEOs.isEmpty()) {
			refundDao.saveOrUpdateBatch(refundEOs);
			log.info(startTime + "-" + endTime + ":" + serviceStaffId
					+ "退款信息添加成功");
			return refundEOs;
		}
		return null;
	}

	/**
	 * 根据refundid更新支付日期
	 * 
	 * @param refundId
	 * @param payTime
	 * @throws Exception
	 */
	public void updateRefundPayTime(Long refundId, Date payTime) {
		String call = "call proc_updateRefundPayTime(?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = { sdf.format(payTime), refundId };
		try {
			refundDao.callProcedure(call, params);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public List<RefundEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefundEO> findALlByTidorOid(String tid, String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	public RefundDAO getRefundDao() {
		return refundDao;
	}

	public void setRefundDao(RefundDAO refundDao) {
		this.refundDao = refundDao;
	}

	public TradeSoldDAO getTradeSoldDao() {
		return tradeSoldDao;
	}

	public void setTradeSoldDao(TradeSoldDAO tradeSoldDao) {
		this.tradeSoldDao = tradeSoldDao;
	}

}
