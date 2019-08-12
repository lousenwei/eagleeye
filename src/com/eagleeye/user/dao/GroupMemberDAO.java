package com.eagleeye.user.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.user.eo.GroupMemberEO;

/**
 * A data access object (DAO) providing persistence and search support for
 * GroupMemberEO entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.eagleeye.user.eo.GroupMemberEO.GroupMemberEO
 * @author MyEclipse Persistence Tools
 */

public class GroupMemberDAO extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(GroupMemberDAO.class);
	// property constants
	public static final String MANAGER_ID = "managerId";
	public static final String GROUP_NAME = "groupName";
	public static final String SERVICE_STAFF_ID = "serviceStaffId";
	public static final String GROUP_ID = "groupId";

	public void save(GroupMemberEO transientInstance) {
		log.debug("saving GroupMemberEO instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GroupMemberEO persistentInstance) {
		log.debug("deleting GroupMemberEO instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GroupMemberEO findById(java.lang.Long id) {
		log.debug("getting GroupMemberEO instance with id: " + id);
		try {
			GroupMemberEO instance = (GroupMemberEO) getSession().get(
					"com.eagleeye.eo.GroupMemberEO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GroupMemberEO> findByExample(GroupMemberEO instance) {
		log.debug("finding GroupMemberEO instance by example");
		try {
			List<GroupMemberEO> results = (List<GroupMemberEO>) getSession()
					.createCriteria("com.eagleeye.eo.GroupMemberEO")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding GroupMemberEO instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from GroupMemberEO as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GroupMemberEO> findByManagerId(Object managerId) {
		String sql = "from GroupMemberEO e where e.id.managerId=?";
		return super.getListData(sql, new Object[] { managerId });
	}

	public List<GroupMemberEO> findByGroupName(Object groupName) {
		return findByProperty(GROUP_NAME, groupName);
	}

	public List<GroupMemberEO> findByServiceStaffId(Object serviceStaffId) {
		String sql = "from GroupMemberEO e where e.id.serviceStaffId=?";
		return super.getListData(sql, new Object[] { serviceStaffId });
	}

	public List findAll() {
		log.debug("finding all GroupMemberEO instances");
		try {
			String queryString = "from GroupMemberEO";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GroupMemberEO merge(GroupMemberEO detachedInstance) {
		log.debug("merging GroupMemberEO instance");
		try {
			GroupMemberEO result = (GroupMemberEO) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GroupMemberEO instance) {
		log.debug("attaching dirty GroupMemberEO instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GroupMemberEO instance) {
		log.debug("attaching clean GroupMemberEO instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public GroupMemberEO getFirstGroupMember(String managerId) {
		// TODO Auto-generated method stub
		List<GroupMemberEO> members = findByManagerId(managerId);
		if (members != null && !members.isEmpty()) {
			return members.get(0);
		}
		return null;
	}

	public void deleteGroupMemberByStaffId(String staffId) {
		String sql = "delete from group_member where service_staff_id=?";
		try {
			super.deleteByJDBCsqL(sql, new Object[] { staffId });
		} catch (Exception e) {
			log.error(e.toString());
		}
	}
}