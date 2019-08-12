package com.eagleeye.eservice.dao.impl;

import java.util.List;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.eservice.eo.OrderEO;

public class OrderDAO extends BaseDao {

	public List<OrderEO> getOrdersByTradeId(Long tradeId) {
		if (tradeId != null) {
			String sql = "from OrderEO where id.tid=? order by id.oid desc";
			return super.getListData(sql, new Object[] { tradeId });
		}
		return null;
	}
}
