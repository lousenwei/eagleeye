package com.eagleeye.eservice.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.eservice.eo.OnlineTimesOnDayEO;

/**
 * A data access object (DAO) providing persistence and search support for
 * OnlineTimesOnDayEO entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.eagleeye.eo.OnlineTimesOnDayEO
 * @author MyEclipse Persistence Tools
 */

public class OnlineTimeDAO extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(OnlineTimeDAO.class);
	// property constants
	public static final String SERVICE_STAFF_ID = "serviceStaffId";
	public static final String ONLINE_TIMES = "OnlineTimesOnDayEOs";

	public void save(OnlineTimesOnDayEO transientInstance) {
		log.debug("saving OnlineTimesOnDayEO instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OnlineTimesOnDayEO persistentInstance) {
		log.debug("deleting OnlineTimesOnDayEO instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OnlineTimesOnDayEO findById(java.lang.Long id) {
		log.debug("getting OnlineTimesOnDayEO instance with id: " + id);
		try {
			OnlineTimesOnDayEO instance = (OnlineTimesOnDayEO) getSession().get(
					"com.eagleeye.eo.OnlineTimesOnDayEO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<OnlineTimesOnDayEO> findByExample(OnlineTimesOnDayEO instance) {
		log.debug("finding OnlineTimesOnDayEO instance by example");
		try {
			List<OnlineTimesOnDayEO> results = (List<OnlineTimesOnDayEO>) getSession()
					.createCriteria("com.eagleeye.eo.OnlineTimesOnDayEO").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding OnlineTimesOnDayEO instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from OnlineTimesOnDayEO as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<OnlineTimesOnDayEO> findByServiceStaffId(Object serviceStaffId) {
		String sql = "from OnlineTimesOnDayEO e where e.id.serviceStaffId=?";
		return super.getListData(sql, new Object[] { serviceStaffId });
	}

	public List<OnlineTimesOnDayEO> findByOnlineTimesOnDayEOs(Object OnlineTimesOnDayEOs) {
		return findByProperty(ONLINE_TIMES, OnlineTimesOnDayEOs);
	}

	public List findAll() {
		log.debug("finding all OnlineTimesOnDayEO instances");
		try {
			String queryString = "from OnlineTimesOnDayEO";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OnlineTimesOnDayEO merge(OnlineTimesOnDayEO detachedInstance) {
		log.debug("merging OnlineTimesOnDayEO instance");
		try {
			OnlineTimesOnDayEO result = (OnlineTimesOnDayEO) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OnlineTimesOnDayEO instance) {
		log.debug("attaching dirty OnlineTimesOnDayEO instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OnlineTimesOnDayEO instance) {
		log.debug("attaching clean OnlineTimesOnDayEO instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}