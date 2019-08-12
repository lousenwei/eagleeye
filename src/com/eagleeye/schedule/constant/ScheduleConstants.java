package com.eagleeye.schedule.constant;

import com.taobao.api.Constants;

public class ScheduleConstants extends Constants {

	/**
	 * 单次增量更新数据数目
	 */
	public final static int SCHEDULE_INCREMENTUPDATE_NUM = 1;

	/**
	 * 单次总量更新数据数目
	 */
	public final static int SCHEDULE_TOTALUPDATE_NUM = 1;

	/**
	 * 数据更新初始状态
	 */
	public final static String SCHEDULE_INIT_STATUS = "0";

	/**
	 * 数据更新成功状态
	 */
	public final static String SCHEDULE_SUCCESS_STATUS = "1";

	/**
	 * 数据更新失败状态
	 */
	public final static String SCHEDULE_FAIL_STATUS = "2";

	/**
	 * 更新所有客服
	 */
	public final static String SCHEDULE_UPDATE_ALLSTAFF = "ALL";
}
