package com.eagleeye.user.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;
import com.eagleeye.user.eo.TotalUpdateDetailEO;

public class TotalUpdateDAO extends BaseDao {
	private Logger log = Logger.getLogger(TotalUpdateDAO.class);

	public TotalUpdateDetailEO getTotalUpdate(String managerId, Date updateTime) {
		// TODO Auto-generated method stub
		String sql = "from TotalUpdateDetailEO e where e.id.managerId=? and e.id.updateTime=?";
		List<TotalUpdateDetailEO> result = super.getListData(sql, new Object[] {
				managerId, updateTime });
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	public Boolean hasTodayTotalUpdate(String managerId) {
		if (getTotalUpdate(managerId, new Date()) != null) {
			return true;
		}
		return false;
	}

	public List<TotalUpdateDetailEO> getTotalUpdates(String managerId) {
		// TODO Auto-generated method stub
		String sql = " from TotalUpdateDetailEO e where e.id.managerId=?";
		return super.getListData(sql, new Object[] { managerId });
	}

	public List<TotalUpdateDetailEO> getUnfinishedTotalUpdates(String managerId) {
		// TODO Auto-generated method stub
		String sql = " from TotalUpdateDetailEO e where e.id.managerId=? and e.status=?";
		return super.getListData(sql, new Object[] { managerId, "0" });
	}

	public Boolean hasData(String staffId, String managerId) {
		String sql = "select * from total_update_detail where staff_id=? and manager_id=?";
		try {
			List results = super.getListByJDBChHSql(sql, new Object[] {
					staffId, managerId });
			if (results != null && !results.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return false;
	}

	public List<TotalUpdateDetailEO> getTotalUpdatesForSchedule(String status,
			int num) throws Exception {
		String sql = "from TotalUpdateDetailEO t where t.status=? order by t.createAt asc";
		return super.getListByHqlLimit(sql, num, new Object[] { status });
	}
}