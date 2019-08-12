package com.eagleeye.statistics.blh.impl;

import java.util.List;

import com.eagleeye.statistics.blh.INoCalCulateItemBlh;
import com.eagleeye.statistics.dao.NoCalculateItemDAO;
import com.eagleeye.statistics.eo.NoCalculateItemsEO;

public class NoCalCulateItemBlhImpl implements INoCalCulateItemBlh {

	NoCalculateItemDAO noCalculateItemDao;

	@Override
	public void saveItem(NoCalculateItemsEO item) {
		// TODO Auto-generated method stub
		noCalculateItemDao.saveOrUpdate(item);
	}

	@Override
	public void saveItems(List<NoCalculateItemsEO> items) {
		// TODO Auto-generated method stub
		noCalculateItemDao.saveOrUpdateBatch(items);
	}

	@Override
	public NoCalculateItemsEO getNoCalculateItemById(Long itemId) {
		// TODO Auto-generated method stub
		return (NoCalculateItemsEO) noCalculateItemDao.getData(
				NoCalculateItemsEO.class, itemId);
	}

	@Override
	public List<NoCalculateItemsEO> getNoCalculateItemsByManagerId(
			String managerId) {
		// TODO Auto-generated method stub
		return noCalculateItemDao.getNoCalculateItemsByManagerId(managerId);
	}

	public void deleteByItemId(Long itemId) {
		noCalculateItemDao.deleteByItemId(itemId);
	}

	public NoCalculateItemDAO getNoCalculateItemDao() {
		return noCalculateItemDao;
	}

	public void setNoCalculateItemDao(NoCalculateItemDAO noCalculateItemDao) {
		this.noCalculateItemDao = noCalculateItemDao;
	}

}
