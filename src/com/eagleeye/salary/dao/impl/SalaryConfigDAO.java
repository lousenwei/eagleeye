package com.eagleeye.salary.dao.impl;

import java.util.List;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.eo.SalaryConfigEOId;
import com.eagleeye.salary.eo.SalaryConfigOwnerEO;
import com.eagleeye.salary.eo.SalaryConfigOwnerEOId;

/**
 * A data access object (DAO) providing persistence and search support for
 * WaitingTimesOnDayEO entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.eagleeye.WaitingTimesOnDayEO.WaitingTimesOnDayEO
 * @author MyEclipse Persistence Tools
 */

public class SalaryConfigDAO extends BaseDao {

	public SalaryConfigEO getSalaryConfigByManagerIdAndStaffId(
			String managerId, String staffId) {
		if (managerId != null && staffId != null) {
			SalaryConfigOwnerEOId id = new SalaryConfigOwnerEOId(managerId,
					staffId);
			Object relationShip = super.get(SalaryConfigOwnerEO.class, id);
			if (relationShip != null) {
				return this.getSalaryConfigByManagerIdandTemplateName(
						managerId,
						((SalaryConfigOwnerEO) relationShip).getTemplateName());
			}
		}
		return null;
	}

	public SalaryConfigEO getSalaryConfigByManagerIdandTemplateName(
			String managerId, String templateName) {
		String sql = "from SalaryConfigEO e where e.id.managerId=? and e.id.templateName=?";
		List<SalaryConfigEO> results = super.getListData(sql, new Object[] {
				managerId, templateName });
		if (results.size() == 1) {
			return results.get(0);
		}
		return null;
	}

	public List<SalaryConfigEO> getSalaryConfigsByManagerId(String managerId) {
		if (managerId != null) {
			String sql = "from SalaryConfigEO e where e.id.managerId=?";
			return super.getListData(sql, new Object[] { managerId });
		}
		return null;
	}

	/**
	 * 根据Id删除工资配置
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSalaryConfigById(SalaryConfigEOId id) {
		if (id.getManagerId() != null & id.getTemplateName() != null) {
			String sql = "delete from SalaryConfigEO e where e.id.managerId=? and e.id.templateName=? and e.id.templateName<>?";
			return super.updateWithHql(sql, new Object[] { id.getManagerId(),
					id.getTemplateName(),
					EagleConstants.GENERAL_PAYMENT_TEMPLATE });
		}
		return 0;
	}
}