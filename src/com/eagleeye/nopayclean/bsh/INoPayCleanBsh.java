package com.eagleeye.nopayclean.bsh;

import java.util.List;

import com.eagleeye.nopayclean.eo.NoPayCleanDetailEO;
import com.taobao.api.domain.ArticleSub;

public interface INoPayCleanBsh {
	/**
	 * 获取订购对象，取当前日期前20天过期的用户
	 */
	public List<ArticleSub> loadNoPayCleanPersons();

	/**
	 * 获取订购关系，确定是否确实未续订
	 */
	public void verifyNoPayCleanPersons(List<ArticleSub> articleSubs);

	/**
	 * 取当前日期前20天过期的用户,并放入队列
	 */
	public void loadAllNoPayCleanPersonsByNewDate();

	/**
	 * 全量清除一个用户信息
	 * 
	 * @param noPayCleanDetail
	 */
	public void deleteAllDataBySchedule(NoPayCleanDetailEO noPayCleanDetail);

	/**
	 * 获取所有未处理的过期人员列表
	 * 
	 * @return
	 */
	public List<NoPayCleanDetailEO> getAllNoUndoPayCleanDetails();

	/**
	 * 更新待处理过期人员列表并保存
	 * 
	 * @param noPayCleanDetailList
	 */
	public void updateAll(List<NoPayCleanDetailEO> noPayCleanDetailList);
}
