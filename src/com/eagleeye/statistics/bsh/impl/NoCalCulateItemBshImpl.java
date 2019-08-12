package com.eagleeye.statistics.bsh.impl;

import java.util.List;

import com.eagleeye.statistics.blh.INoCalCulateItemBlh;
import com.eagleeye.statistics.bsh.INoCalCulateItemBsh;
import com.eagleeye.statistics.eo.NoCalculateItemsEO;

public class NoCalCulateItemBshImpl implements INoCalCulateItemBsh {
	INoCalCulateItemBlh noCalCulateItemBlh;

	@Override
	public void saveItem(NoCalculateItemsEO item) {
		// TODO Auto-generated method stub
		noCalCulateItemBlh.saveItem(item);
	}

	@Override
	public void saveItems(List<NoCalculateItemsEO> items) {
		// TODO Auto-generated method stub
		noCalCulateItemBlh.saveItems(items);
	}

	@Override
	public NoCalculateItemsEO getNoCalculateItemById(Long itemId) {
		// TODO Auto-generated method stub
		return noCalCulateItemBlh.getNoCalculateItemById(itemId);
	}

	@Override
	public List<NoCalculateItemsEO> getNoCalculateItemsByManagerId(
			String managerId) {
		// TODO Auto-generated method stub
		return noCalCulateItemBlh.getNoCalculateItemsByManagerId(managerId);
	}

	public void deleteByItemId(Long itemId) {
		noCalCulateItemBlh.deleteByItemId(itemId);
	}

	public INoCalCulateItemBlh getNoCalCulateItemBlh() {
		return noCalCulateItemBlh;
	}

	public void setNoCalCulateItemBlh(INoCalCulateItemBlh noCalCulateItemBlh) {
		this.noCalCulateItemBlh = noCalCulateItemBlh;
	}

}
