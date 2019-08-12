package com.eagleeye.common.query;

import java.util.List;

public class PageResult {
	/***
	 * 总的录条数.
	 */
	private int countTotal;

	/**
	 * 用于保存结果集.
	 */
	@SuppressWarnings("unchecked")
	private List listResult;

	public PageResult() {

	}

	@SuppressWarnings("unchecked")
	public PageResult(int countTotal, List listResult) {
		this.countTotal = countTotal;
		this.listResult = listResult;
	}

	/**
	 * 得到总的记录条数.
	 * 
	 * @return countTotal.
	 */
	public int getCountTotal() {
		return countTotal;
	}

	/**
	 * 设置总的记录条数.
	 * 
	 * @param countTotal
	 */
	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
	}

	/**
	 * 得到结果集.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListResult() {
		return listResult;
	}

	/**
	 * 设置查询的结果集.
	 * 
	 * @param listResult
	 */
	@SuppressWarnings("unchecked")
	public void setListResult(List listResult) {
		this.listResult = listResult;
	}
}
