package com.eagleeye.statistics.bsh;

import java.util.List;

import com.eagleeye.statistics.eo.NoCalculateItemsEO;

public interface INoCalCulateItemBsh {
	public void saveItem(NoCalculateItemsEO item);

	public void saveItems(List<NoCalculateItemsEO> items);

	public NoCalculateItemsEO getNoCalculateItemById(Long itemId);

	public List<NoCalculateItemsEO> getNoCalculateItemsByManagerId(
			String managerId);

	public void deleteByItemId(Long itemId);
}
