package com.eagleeye.test;

import com.eagleeye.common.dao.BaseDao;

public class TestBsh extends BaseDao {

	public String getId(String name) {
		String sql = "select nextval(?);";
		return (String) super.getValueByJDBCsql(sql, new Object[] { name });
	}
}
