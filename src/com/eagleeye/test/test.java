package com.eagleeye.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.dao.impl.ReceiveNumDAO;
import com.eagleeye.eservice.eo.ReplyStatOnDayEO;
import com.eagleeye.eservice.eo.ReplyStatOnDayEOId;

public class test extends Thread {


	// public test(int id) {
	// super();
	// // TODO Auto-generated constructor stub
	// this.id = id;
	// }
	//
	// int id;
	//
	// public void run() {
	// List b = new ArrayList();
	// for (int i = 0; i < 1000; i++) {
	// ChatLogEO a = new ChatLogEO();
	// a.setContent(String.valueOf(i));
	// a.setCreatedAt(new Date());
	// a.setDirection(1);
	// a.setMessageTime(new Date());
	// a.setServiceStaffId(String.valueOf(id));
	// a.setToId(String.valueOf(i));
	// b.add(a);
	// }
	// long startTime = System.currentTimeMillis();
	// test.saveAllObject(b);
	// long endTime = System.currentTimeMillis();
	// System.out.println("id" + id + "增加200条记录,保存时间：" + (endTime - startTime)
	// / 1000.0 + "秒");
	// }

	public static void main(String[] args) {
		try {
			Map<Date, Boolean> dateToRunChatPeers = new HashMap();
			Map<ReplyStatOnDayEOId, ReplyStatOnDayEO> receiveNumMap = new HashMap();
			Date startTime = DateUtil.getDateByString("2013-03-01",
					DateUtil.datePatternYY_MM_DD);
			Date endTime = DateUtil.getDateByString("2013-03-02",
					DateUtil.datePatternYY_MM_DD);
			Long dayGap = DateUtil.getDateGap(startTime, endTime);
			Date start = startTime;
			for (int i = 0; i <= dayGap; i++) {
				if (dateToRunChatPeers.get(start) == null) {
					//ReceiveNumDAO receiveNumDao = new ReceiveNumDAO();
//					List<Map> data = receiveNumDao.getReplyNumByChatPeers(
//							"maxine_111", start);
//					if (data != null && !data.isEmpty()) {
//						for (Map tmp : data) {
//							ReplyStatOnDayEO receiveNum = new ReplyStatOnDayEO();
//							ReplyStatOnDayEOId id = new ReplyStatOnDayEOId();
//							receiveNum.setCreatedAt(new Date());
//							id.setReplyDate(start);
//							receiveNum.setReplyNum((Long) tmp.get("num"));
//							id.setServiceStaffId("maxine_111");
//							receiveNum.setUpdatedAt(new Date());
//
//							receiveNum.setId(id);
//							if (receiveNumMap.get(id) == null) {
//								receiveNumMap.put(id, receiveNum);
//							}
//						}
//					}
				}
				start = DateUtil.getNextDay(start, 1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
