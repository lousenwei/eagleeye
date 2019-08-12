package com.eagleeye.eservice.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.RefundEO;

public interface IRefundBlh {

	/**
	 * 导入退款信息
	 * 
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 * @throws Exception
	 */
	public List<RefundEO> loadRefund(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) throws Exception;

	/**
	 * 取得所有退款信息
	 * 
	 * @param serviceStaffId
	 */
	public List<RefundEO> findAllByStaffId(String serviceStaffId);

	/**
	 * 根据交易号，订单号查询对应退款信息
	 * 
	 * @param tid
	 * @param oid
	 * @return
	 */
	public List<RefundEO> findALlByTidorOid(String tid, String oid);

	/**
	 * 根据refundid更新支付日期
	 * 
	 * @param refundId
	 * @param payTime
	 * @throws Exception
	 */
	public void updateRefundPayTime(Long refundId, Date payTime);
}
