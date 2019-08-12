package com.eagleeye.nopayclean.bsh.impl;

import java.util.List;

import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.nopayclean.blh.INoPayCleanBlh;
import com.eagleeye.nopayclean.bsh.INoPayCleanBsh;
import com.eagleeye.nopayclean.eo.NoPayCleanDetailEO;
import com.taobao.api.domain.ArticleSub;

public class NoPayCleanBshImpl implements INoPayCleanBsh {

	INoPayCleanBlh noPayCleanBlh;

	@Override
	public List<ArticleSub> loadNoPayCleanPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyNoPayCleanPersons(List<ArticleSub> articleSubs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadAllNoPayCleanPersonsByNewDate() {
		List<ArticleSub> articleSubs = noPayCleanBlh.loadNoPayCleanPersons();
		if (articleSubs != null && !articleSubs.isEmpty()) {
			noPayCleanBlh.verifyNoPayCleanPersons(articleSubs);
		}
	}

	/**
	 * 使用schedule删除此manager所有数据
	 * 
	 * @param noPayCleanDetail
	 */
	public void deleteAllDataBySchedule(NoPayCleanDetailEO noPayCleanDetail) {
		noPayCleanBlh.deleteAllDataBySchedule(noPayCleanDetail);
	}

	/**
	 * 获取所有未处理的过期人员列表
	 * 
	 * @return
	 */
	public List<NoPayCleanDetailEO> getAllNoUndoPayCleanDetails() {
		return noPayCleanBlh.getAllNoUndoPayCleanDetails();
	}

	/**
	 * 更新待处理过期人员列表并保存
	 * 
	 * @param noPayCleanDetailList
	 */
	public void updateAll(List<NoPayCleanDetailEO> noPayCleanDetailList) {
		noPayCleanBlh.updateAll(noPayCleanDetailList);
	}

	public INoPayCleanBlh getNoPayCleanBlh() {
		return noPayCleanBlh;
	}

	public void setNoPayCleanBlh(INoPayCleanBlh noPayCleanBlh) {
		this.noPayCleanBlh = noPayCleanBlh;
	}

	public static void main(String[] args) {
		try {
			INoPayCleanBlh noPayCleanBlh = (INoPayCleanBlh) EagleEyeServiceLocator
					.getBean("noPayCleanBlh");
			List<ArticleSub> articleSubs = noPayCleanBlh
					.loadNoPayCleanPersons();
			if (articleSubs != null && !articleSubs.isEmpty()) {
				System.out.println(articleSubs.size());
				noPayCleanBlh.verifyNoPayCleanPersons(articleSubs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
