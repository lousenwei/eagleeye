package com.eagleeye.salary.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.salary.eo.SalaryConfigOwnerEO;
import com.eagleeye.salary.mo.SalaryConfigOwnerMO;

public class SalaryConfigOwnerDAO extends BaseDao {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 返回未分配工资模板的客服
	 * 
	 * @param managerId
	 * @return
	 */
	public List<SalaryConfigOwnerMO> getNoSalaryConfigStaffs(String managerId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select service_staff_id from group_member t ");
		sql.append(" where ");
		sql.append(" t.manager_id=? and ");
		sql.append(" not exists ");
		sql.append(" (select 1 from salary_config_owner e ");
		sql.append(" where t.manager_id=e.manager_id and t.service_staff_id=e.staff_id) ");
		try {
			List<Map> results = super.getListByJDBCSqL(sql.toString(), null,
					new Object[] { managerId });
			List<SalaryConfigOwnerMO> returns;
			if (results != null && !results.isEmpty()) {
				returns = new ArrayList();
				int rowKey = 0;
				for (Map tmp : results) {
					SalaryConfigOwnerMO mo = new SalaryConfigOwnerMO();
					mo.setRowKey(rowKey++);
					mo.setStaffId((String) tmp.get("service_staff_id"));
					returns.add(mo);
				}
				return returns;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 返回已分配工资模板的客服
	 * 
	 * @param managerId
	 * @return
	 */
	public List getSalaryConfigOwner(String managerId) {
		String sql = "from SalaryConfigOwnerEO e where e.id.managerId=?";
		return super.getListData(sql, new Object[] { managerId });
	}

	/**
	 * 删除已分配工资模板的客服
	 * 
	 * @param salaryConfigOwner
	 * @return
	 */
	public void deleteSalaryConfigOwner(SalaryConfigOwnerEO salaryConfigOwner) {
		super.deleteData(salaryConfigOwner);
	}

	/**
	 * 删除模板下的对应关系
	 * 
	 * @param managerId
	 * @param templateName
	 * @return
	 */
	public int deleteSalaryConfigOwnersByTemplateName(String managerId,
			String templateName) {
		String sql = "delete from salary_config_owner where manager_id=? and template_name=?";
		try {
			int i = super.deleteByJDBCsqL(sql, new Object[] { managerId,
					templateName });
			return i;
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}
}
