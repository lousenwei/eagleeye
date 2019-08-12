package com.eagleeye.eservice.bsh.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.blh.IChatLogBlh;
import com.eagleeye.eservice.bsh.IChatLogBsh;
import com.eagleeye.eservice.eo.ChatLogEO;
import com.taobao.api.domain.MsgList;

public class ChatLogBshImpl implements IChatLogBsh {
    private Logger log = Logger.getLogger(ChatLogBshImpl.class);
    IChatLogBlh chatLogBlh;

//	@Override
//	public void loadChatLog(String serviceStaffId, Date startTime,
//			Date endTime, String sessionKey) {
//		// TODO Auto-generated method stub
//		try {
//			chatLogBlh.loadChatLog(serviceStaffId, startTime, endTime,
//					sessionKey);
//		} catch (Exception e) {
//			log.error(e);
//		}
//	}

    @Override
    public List<ChatLogEO> findAllByStaffId(String serviceStaffId) {
        // TODO Auto-generated method stub
        return null;
    }

    public IChatLogBlh getChatLogBlh() {
        return chatLogBlh;
    }

    public void setChatLogBlh(IChatLogBlh chatLogBlh) {
        this.chatLogBlh = chatLogBlh;
    }

    @Override
    public List<ChatLogEO> findAllByStaffId(String serviceStaffId, Date start,
                                            Date end) {
        // TODO Auto-generated method stub
        if (serviceStaffId == null || start == null || end == null) {
            return null;
        } else {
            return chatLogBlh.findAllByStaffId(serviceStaffId, start, end);
        }
    }

    /**
     * 实时得到聊天记录对象 2013-5-22
     *
     * @param staffId
     * @param buyNick
     * @param startTime
     * @param endTime
     * @param sessionKey
     * @return
     */
    public List<MsgList> getChatLogByStaffIdAndUidNew(String staffId,
                                                      String buyNick, Date startTime, Date endTime, String sessionKey) {
        return chatLogBlh.getChatLogByStaffIdAndUidNew(staffId, buyNick,
                startTime, endTime, sessionKey);
    }

    /**
     * 得到聊天记录 2013-12-13
     *
     * @param staffId
     * @param buyNick
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public List<ChatLogEO> loadChatLogs(String staffId, String buyNick, Date startTime, Date endTime, String sessionKey)
            throws Exception {
        return chatLogBlh.loadChatLogs(staffId, buyNick, startTime, endTime, sessionKey);
    }
}
