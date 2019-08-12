package com.eagleeye.eservice.bsh;

import java.util.Date;
import java.util.List;

import com.eagleeye.eservice.eo.ChatLogEO;
import com.taobao.api.domain.MsgList;

public interface IChatLogBsh {
//	/**
//	 * 导入聊天记录
//	 * 
//	 * @param serviceStaffId
//	 *            客服id
//	 */
//	public void loadChatLog(String serviceStaffId, Date startTime,
//			Date endTime, String sessionKey);

	/**
	 * 取得staff聊天记录
	 * 
	 * @param serviceStaffId
	 */
	public List<ChatLogEO> findAllByStaffId(String serviceStaffId);

	/**
	 * 取得staff聊天记录
	 * 
	 * @param serviceStaffId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<ChatLogEO> findAllByStaffId(String serviceStaffId, Date start,
			Date end);

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
			String buyNick, Date startTime, Date endTime, String sessionKey);

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
			throws Exception;
}
