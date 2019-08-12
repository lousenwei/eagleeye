package com.eagleeye.eservice.dao.impl;

import java.util.List;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.eservice.eo.UpdatelogEO;

public class UpdateLogDAO extends BaseDao {

	public List<UpdatelogEO> getUpdateLogs(String managerId) {
		if (managerId != null) {
			String sql = "from UpdatelogEO e where e.id.managerId=? order by priority asc";
			return super.getListData(sql, new Object[] { managerId });
		}
		return null;
	}

	public UpdatelogEO getUpdateLog(String managerId, String item) {
		if (managerId != null && item != null) {
			String sql = "from UpdatelogEO e where e.id.managerId=? and e.id.item=?";
			List<UpdatelogEO> results = super.getListData(sql, new Object[] {
					managerId, item });
			if (results.size() == 1) {
				return results.get(0);
			}
		}
		return null;
	}
}
