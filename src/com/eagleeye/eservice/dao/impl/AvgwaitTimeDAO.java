package com.eagleeye.eservice.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.eservice.eo.WaitingTimesOnDayEO;

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

public class AvgwaitTimeDAO extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(AvgwaitTimeDAO.class);
	// property constants
	public static final String SERVICE_STAFF_ID = "serviceStaffId";
	public static final String AVG_WAITING_TIMES = "avgWaitingTimes";

	public void save(WaitingTimesOnDayEO transientInstance) {
		log.debug("saving WaitingTimesOnDayEO instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(WaitingTimesOnDayEO persistentInstance) {
		log.debug("deleting WaitingTimesOnDayEO instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WaitingTimesOnDayEO findById(java.lang.Long id) {
		log.debug("getting WaitingTimesOnDayEO instance with id: " + id);
		try {
			WaitingTimesOnDayEO instance = (WaitingTimesOnDayEO) getSession()
					.get("com.eagleeye.eo.WaitingTimesOnDayEO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<WaitingTimesOnDayEO> findByExample(WaitingTimesOnDayEO instance) {
		log.debug("finding WaitingTimesOnDayEO instance by example");
		try {
			List<WaitingTimesOnDayEO> results = (List<WaitingTimesOnDayEO>) getSession()
					.createCriteria("com.eagleeye.eo.WaitingTimesOnDayEO")
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
		log.debug("finding WaitingTimesOnDayEO instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from WaitingTimesOnDayEO as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<WaitingTimesOnDayEO> findByServiceStaffId(Object serviceStaffId) {
		return findByProperty(SERVICE_STAFF_ID, serviceStaffId);
	}

	public List<WaitingTimesOnDayEO> findByAvgWaitingTimes(
			Object avgWaitingTimes) {
		return findByProperty(AVG_WAITING_TIMES, avgWaitingTimes);
	}

	public List findAll() {
		log.debug("finding all WaitingTimesOnDayEO instances");
		try {
			String queryString = "from WaitingTimesOnDayEO";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public WaitingTimesOnDayEO merge(WaitingTimesOnDayEO detachedInstance) {
		log.debug("merging WaitingTimesOnDayEO instance");
		try {
			WaitingTimesOnDayEO result = (WaitingTimesOnDayEO) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(WaitingTimesOnDayEO instance) {
		log.debug("attaching dirty WaitingTimesOnDayEO instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WaitingTimesOnDayEO instance) {
		log.debug("attaching clean WaitingTimesOnDayEO instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}