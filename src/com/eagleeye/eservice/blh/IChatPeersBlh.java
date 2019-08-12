package com.eagleeye.eservice.blh;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.eagleeye.eservice.eo.ChatPeerEO;
import com.eagleeye.eservice.mo.ChatPeerQueryParametersMO;

public interface IChatPeersBlh {

	/**
	 * 导入对应客服聊天对象
	 * 
	 * @param serviceStaffId
	 * @param startTime
	 * @param endTime
	 */
	public void loadChatPeers(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) throws Exception;

	/**
	 * 找到对应客服聊天对象List
	 * 
	 * @param serviceStaffId
	 * @return
	 */
	public List<ChatPeerEO> findAllByStaffId(String serviceStaffId);

	/**
	 * 获取distinct对象
	 * 
	 * @param mo
	 * @return
	 */
	public List getDistinctRecordByBuyNickandTimePeriod(
			ChatPeerQueryParametersMO mo);

}
