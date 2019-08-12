package com.eagleeye.eservice.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.eservice.eo.NonReplyStatOnDayEO;

/**
 * A data access object (DAO) providing persistence and search support for
 * NonReplyStatOnDayEO entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.eagleeye.eo.NonReplyStatOnDayEO
 * @author MyEclipse Persistence Tools
 */

public class NoreplyNumDAO extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(NoreplyNumDAO.class);
	// property constants
	public static final String SERVICE_STAFF_ID = "serviceStaffId";
	public static final String NON_REPLY_NUM = "nonReplyNum";
	public static final String NON_REPLY_CUSTOMID = "nonReplyCustomid";

	public void save(NonReplyStatOnDayEO transientInstance) {
		log.debug("saving NonReplyStatOnDayEO instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NonReplyStatOnDayEO persistentInstance) {
		log.debug("deleting NonReplyStatOnDayEO instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NonReplyStatOnDayEO findById(java.lang.Long id) {
		log.debug("getting NonReplyStatOnDayEO instance with id: " + id);
		try {
			NonReplyStatOnDayEO instance = (NonReplyStatOnDayEO) getSession()
					.get("com.eagleeye.eo.NonReplyStatOnDayEO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<NonReplyStatOnDayEO> findByExample(NonReplyStatOnDayEO instance) {
		log.debug("finding NonReplyStatOnDayEO instance by example");
		try {
			List<NonReplyStatOnDayEO> results = (List<NonReplyStatOnDayEO>) getSession()
					.createCriteria("com.eagleeye.eo.NonReplyStatOnDayEO")
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
		log.debug("finding NonReplyStatOnDayEO instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NonReplyStatOnDayEO as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<NonReplyStatOnDayEO> findByServiceStaffId(Object serviceStaffId) {
		String sql = "from NonReplyStatOnDayEO e where e.id.serviceStaffId=?";
		return super.getListData(sql, new Object[] { serviceStaffId });
	}

	public List<NonReplyStatOnDayEO> findByNonReplyNum(Object nonReplyNum) {
		return findByProperty(NON_REPLY_NUM, nonReplyNum);
	}

	public List<NonReplyStatOnDayEO> findByNonReplyCustomid(
			Object nonReplyCustomid) {
		return findByProperty(NON_REPLY_CUSTOMID, nonReplyCustomid);
	}

	public List findAll() {
		log.debug("finding all NonReplyStatOnDayEO instances");
		try {
			String queryString = "from NonReplyStatOnDayEO";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NonReplyStatOnDayEO merge(NonReplyStatOnDayEO detachedInstance) {
		log.debug("merging NonReplyStatOnDayEO instance");
		try {
			NonReplyStatOnDayEO result = (NonReplyStatOnDayEO) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NonReplyStatOnDayEO instance) {
		log.debug("attaching dirty NonReplyStatOnDayEO instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NonReplyStatOnDayEO instance) {
		log.debug("attaching clean NonReplyStatOnDayEO instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}