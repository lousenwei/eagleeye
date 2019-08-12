package com.eagleeye.statistics.dao;

import org.apache.log4j.Logger;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.statistics.eo.BelongTypeEO;

public class BelongTypeDAO extends BaseDao {
	private Logger log = Logger.getLogger(BelongTypeDAO.class);

	public BelongTypeEO getBelongTypeEOByManagerId(String managerId) {
		Object belongType = super.getData(BelongTypeEO.class, managerId);
		if (belongType != null) {
			return (BelongTypeEO) belongType;
		}
		return null;
	}

	public void deleteByItemId(String managerId) {
		String sql = "delete from belong_type where manager_id=?";
		try {
			super.deleteByJDBCsqL(sql, new Object[] { managerId });
		} catch (Exception e) {
			log.error(e);
		}
	}
}
