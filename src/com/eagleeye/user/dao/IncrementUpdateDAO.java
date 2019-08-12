package com.eagleeye.user.dao;

import java.util.Date;
import java.util.List;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.schedule.constant.ScheduleConstants;
import com.eagleeye.user.constant.UserConstant;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;

public class IncrementUpdateDAO extends BaseDao {

	public IncrementUpdateDetailEO getIncrementUpdate(String managerId,
			Date updateTime) {
		// TODO Auto-generated method stub
		String sql = "from IncrementUpdateDetailEO e where e.id.managerId=? and e.id.updateTime=?";
		List<IncrementUpdateDetailEO> result = super.getListData(sql,
				new Object[] { managerId, updateTime });
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	public List<IncrementUpdateDetailEO> getIncrementUpdates(String managerId) {
		// TODO Auto-generated method stub
		String sql = "from IncrementUpdateDetailEO e where e.id.managerId=?";
		return super.getListData(sql, new Object[] { managerId });
	}

	public List<IncrementUpdateDetailEO> getIncrementUpdatesByManagerIdAndToday(
			String managerId) {
		// TODO Auto-generated method stub
		String sql = "from IncrementUpdateDetailEO e where e.id.managerId=? and e.id.updateTime=?";
		return super
				.getListData(
						sql,
						new Object[] {
								managerId,
								DateUtil.getSimpleDate(DateUtil.getPreviousDay(
										new Date(),
										UserConstant.INCREMENT_UPDATE_GAP)) });
	}

	public Boolean hasTodayUpdate(String managerId) {
		List<IncrementUpdateDetailEO> result = this
				.getIncrementUpdatesByManagerIdAndToday(managerId);
		if (result != null && !result.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<IncrementUpdateDetailEO> getIncrementUpdatesForSchedule(
			String status, int num) throws Exception {
		String sql = "from IncrementUpdateDetailEO t where t.status=? order by t.createAt asc ";
		return super.getListByHqlLimit(sql, num, new Object[] { status });
	}

	public List<IncrementUpdateDetailEO> getTopIncrementUpdatesByManagerid(
			String managerId, int num) {
		String sql = "from IncrementUpdateDetailEO t where t.id.managerId=? order by t.createAt desc ";
		return super.getListByHqlLimit(sql, num, new Object[] { managerId });
	}
}
