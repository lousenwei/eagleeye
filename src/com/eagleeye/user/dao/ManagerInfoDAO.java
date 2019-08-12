package com.eagleeye.user.dao;

import java.util.List;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.user.constant.UserConstant;
import com.eagleeye.user.eo.ManagerInfoEO;

public class ManagerInfoDAO extends BaseDao {

	public Boolean checkHasValidManager(String managerId) {
		String sql = "from ManagerInfoEO e where e.managerId=? and e.status=?";
		List<ManagerInfoEO> result = super.getListData(sql, new Object[] {
				managerId, UserConstant.IS_VALID });
		if (result != null && !result.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<ManagerInfoEO> getManagerInfoEO(String managerId) {
		String sql = "from ManagerInfoEO e where e.managerId=? and e.status=?";
		List<ManagerInfoEO> result = super.getListData(sql, new Object[] {
				managerId, UserConstant.IS_VALID });
		if (result != null && !result.isEmpty()) {
			return result;
		}
		return null;
	}

	public ManagerInfoEO getDistinctManagerInfoEO(String managerId) {
		List<ManagerInfoEO> result = getManagerInfoEO(managerId);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
}
