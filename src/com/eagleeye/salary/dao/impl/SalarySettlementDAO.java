package com.eagleeye.salary.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.salary.eo.SalarySettlementEO;
import com.eagleeye.salary.mo.SalarySettlementQueryMO;

public class SalarySettlementDAO extends BaseDao {
	public SalarySettlementEO getSalarySettlementByManagerIdAndTimePreiod(
			String managerId, Date betweenDate) {
		if (managerId != null) {
			String sql = "from SalarySettlementEO e where e.id.managerId=? and e.id.start<=? and e.id.end>=? order by e.settlementDate desc";
			List<SalarySettlementEO> results = super.getListData(sql,
					new Object[] { managerId, betweenDate, betweenDate });
			if (results.size() == 1) {
				return results.get(0);
			}
		}
		return null;
	}

	public List<SalarySettlementEO> getSalarySettlementByManagerId(
			String managerId, Date betweenDate) {
		if (managerId != null) {
			String sql = "from SalarySettlementEO e where e.id.managerId=? and e.id.start<=? and e.id.end>=? order by e.settlementDate desc";
			return super.getListData(sql, new Object[] { managerId,
					betweenDate, betweenDate });
		}
		return null;
	}

	public List<SalarySettlementEO> getSalarySettlementByManagerIdAndTime(
			String managerId, Date start, Date end) {
		if (managerId != null && start != null && end != null) {
			String sql = "from SalarySettlementEO e where e.id.managerId=? and e.id.start<=? and e.id.end>=? order by e.settlementDate desc";
			return super.getListData(sql,
					new Object[] { managerId, start, end });
		}
		return null;
	}

	public List<SalarySettlementEO> getSalarySettlementByMo(
			SalarySettlementQueryMO queryMo) {
		String sql = "from SalarySettlementEO e where 1=1 ";
		ArrayList params = new ArrayList();
		if (queryMo.getManagerId() != null) {
			sql = sql + " and e.id.managerId=? ";
			params.add(queryMo.getManagerId());
		} else {
			return null;
		}
		if (queryMo.getStaffId() != null) {
			sql = sql + " and e.id.staffId ";
			params.add(queryMo.getStaffId());
		}
		if (queryMo.getBetweenDate() != null) {
			sql = sql + " and e.id.start<=? and e.id.end>=?";
			params.add(queryMo.getBetweenDate());
			params.add(queryMo.getBetweenDate());
		}
		return super.getListData(sql, params.toArray());
	}
}
