package com.eagleeye.eservice.mo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.ChatPeerEO;

public class ChatPeerMO {
	Date chatDate;
	
	List<ChatPeerEO> chatPeers=new ArrayList();

	public Date getChatDate() {
		return chatDate;
	}

	public void setChatDate(Date chatDate) {
		this.chatDate = chatDate;
	}

	public List<ChatPeerEO> getChatPeers() {
		return chatPeers;
	}

	public void setChatPeers(List<ChatPeerEO> chatPeers) {
		this.chatPeers = chatPeers;
	}
}
