package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.blh.IChatPeersBlh;
import com.eagleeye.eservice.bsh.IChatPeersBsh;
import com.eagleeye.eservice.eo.ChatPeerEO;
import com.eagleeye.eservice.mo.ChatPeerQueryParametersMO;

public class ChatPeersBshImpl implements IChatPeersBsh {
	IChatPeersBlh chatPeersBlh;
	private Logger log = Logger.getLogger(ChatPeersBshImpl.class);

	@Override
	public void loadChatPeers(String serviceStaffId, Date startTime,
			Date endTime, String sessionKey) {
		// TODO Auto-generated method stub
		try {
			chatPeersBlh.loadChatPeers(serviceStaffId, startTime, endTime,
					sessionKey);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 获取distinct对象
	 * 
	 * @param mo
	 * @return
	 */
	public List getDistinctRecordByBuyNickandTimePeriod(
			ChatPeerQueryParametersMO mo) {
		return chatPeersBlh.getDistinctRecordByBuyNickandTimePeriod(mo);
	}

	@Override
	public List<ChatPeerEO> findAllByStaffId(String serviceStaffId) {
		// TODO Auto-generated method stub
		return chatPeersBlh.findAllByStaffId(serviceStaffId);
	}

	public IChatPeersBlh getChatPeersBlh() {
		return chatPeersBlh;
	}

	public void setChatPeersBlh(IChatPeersBlh chatPeersBlh) {
		this.chatPeersBlh = chatPeersBlh;
	}

}
