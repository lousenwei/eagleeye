package com.eagleeye.eservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.common.query.PageResult;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.mo.TradeQueryParametersMo;

public class TradeSoldDAO extends BaseDao {
	public List<TradeEO> getTradeEOsByManagerIdAndTimePreiod(
			TradeQueryParametersMo queryMo) {
		String sql = "from TradeEO e where 1=1 ";
		ArrayList params = new ArrayList();
		if (queryMo.getStaffId() != null) {
			sql = sql + " and e.sellerNick=? ";
			params.add(queryMo.getStaffId());
		}
		if (!queryMo.getHasPayTime()) {
			sql = sql + " and e.payTime is null ";
		}
		if (queryMo.getHasPayTime() && queryMo.getStart() != null) {
			sql = sql + " and e.payTime>=? ";
			params.add(queryMo.getStart());
		}
		if (queryMo.getHasPayTime() && queryMo.getEnd() != null) {
			sql = sql + " and e.payTime<=? ";
			params.add(queryMo.getEnd());
		}
		if (queryMo.getModifyStart() != null) {
			sql = sql + " and e.modified>=? ";
			params.add(queryMo.getModifyStart());
		}
		if (queryMo.getModifyEnd() != null) {
			sql = sql + " and e.modified<=? ";
			params.add(queryMo.getModifyEnd());
		}

		if (queryMo.getStatus() != null && !queryMo.getStatus().isEmpty()) {
			String temp = "";
			int i = 0;
			for (String status : queryMo.getStatus()) {
				if (++i != queryMo.getStatus().size()) {
					temp = temp + "'" + status + "',";
				} else {
					temp = temp + "'" + status + "'";
				}
			}
			if (!"".equals(temp)) {
				sql = sql + " and e.status in (" + temp + ")";
			}
		}
		if (queryMo.getHasTradeOwner() != null
				&& queryMo.getHasTradeOwner() == false) {
			sql = sql + " and e.tradeOwner is null";
		}
		if (queryMo.getTradeOwner() != null) {
			sql = sql + " and e.tradeOwner=?";
			params.add(queryMo.getTradeOwner());
		}
		return super.getListData(sql, params.toArray());
	}

	public PageResult getTradeEOsPagnationByManagerIdAndTimePreiod(
			TradeQueryParametersMo queryMo, int startNum, int pageSize) {
		String sql = "from TradeEO e where 1=1 ";
		ArrayList params = new ArrayList();
		if (queryMo.getStaffId() != null) {
			sql = sql + " and e.sellerNick=? ";
			params.add(queryMo.getStaffId());
		}
		if (!queryMo.getHasPayTime()) {
			sql = sql + " and e.payTime is null ";
		}
		if (queryMo.getHasPayTime() && queryMo.getStart() != null) {
			sql = sql + " and e.payTime>=? ";
			params.add(queryMo.getStart());
		}
		if (queryMo.getHasPayTime() && queryMo.getEnd() != null) {
			sql = sql + " and e.payTime<=? ";
			params.add(queryMo.getEnd());
		}
		if (queryMo.getModifyStart() != null) {
			sql = sql + " and e.modified>=? ";
			params.add(queryMo.getModifyEnd());
		}
		if (queryMo.getModifyEnd() != null) {
			sql = sql + " and e.modified<=? ";
			params.add(queryMo.getModifyEnd());
		}

		if (queryMo.getStatus() != null && !queryMo.getStatus().isEmpty()) {
			String temp = "";
			int i = 0;
			for (String status : queryMo.getStatus()) {
				if (++i != queryMo.getStatus().size()) {
					temp = temp + "'" + status + "',";
				} else {
					temp = temp + "'" + status + "'";
				}
			}
			if (!"".equals(temp)) {
				sql = sql + " and e.status in (" + temp + ")";
			}
		}
		if (queryMo.getHasTradeOwner() != null
				&& queryMo.getHasTradeOwner() == false) {
			sql = sql + " and e.tradeOwner is null";
		}
		if (queryMo.getTradeOwner() != null
				&& !queryMo.getTradeOwner().isEmpty()) {
			sql = sql + " and e.tradeOwner=?";
			params.add(queryMo.getTradeOwner());
		}
		if (queryMo.getTid() != null && !queryMo.getTid().toString().isEmpty()
				&& queryMo.getTid().compareTo(0L) != 0) {
			sql = sql + " and e.tid=?";
			params.add(queryMo.getTid());
		}
		return super.getListByPaginationRowNum(sql, startNum, pageSize,
				params.toArray());
	}

	public List getSumTradeInfoByManagerId() {
		return null;
	}

}
