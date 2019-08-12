package com.eagleeye.statistics.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.statistics.eo.NoCalculateItemsEO;

public class NoCalculateItemDAO extends BaseDao {
	private Logger log = Logger.getLogger(NoCalculateItemDAO.class);

	public List<NoCalculateItemsEO> getNoCalculateItemsByManagerId(
			String managerId) {
		String sql = "from NoCalculateItemsEO e where e.managerId=?";
		return super.getListData(sql, new Object[] { managerId });
	}

	public void deleteByItemId(Long itemId) {
		String sql = "delete from no_calculate_items where num_iid=?";
		try {
			super.deleteByJDBCsqL(sql, new Object[] { itemId });
		} catch (Exception e) {
			log.error(e);
		}
	}
}
