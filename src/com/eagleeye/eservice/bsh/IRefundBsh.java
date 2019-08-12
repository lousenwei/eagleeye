package com.eagleeye.eservice.bsh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.RefundEO;

public interface IRefundBsh {
	/**
	 * 导入退款信息
	 * 
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 * @throws Exception
	 */
	public void loadRefund(String serviceStaffId, Date startTime, Date endTime,
			String sessionKey);

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
}
