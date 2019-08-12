package com.eagleeye.eservice.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.eo.ChatLogEO;

/**
 * A data access object (DAO) providing persistence and search support for
 * ChatLogEO entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.eagleeye.ChatLogEO.ChatLogEO
 * @author MyEclipse Persistence Tools
 */

public class ChatLogDAO extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(ChatLogDAO.class);
	// property constants
	public static final String SERVICE_STAFF_ID = "serviceStaffId";
	public static final String TO_ID = "toId";
	public static final String COUNT = "count";
	public static final String DIRECTION = "direction";
	public static final String CONTENT = "content";

	public void save(ChatLogEO transientInstance) {
		log.debug("saving ChatLogEO instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ChatLogEO persistentInstance) {
		log.debug("deleting ChatLogEO instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List<ChatLogEO> findAllByStaffId(String staffId, Date start, Date end) {
		String sql = "from ChatLogEO e where e.serviceStaffId=? and e.messageTime>=? and e.messageTime<?";
		return (List<ChatLogEO>) super.getListData(sql, new Object[] { staffId,
				start, DateUtil.getNextDay(end, 1) });
	}

	public List<ChatLogEO> findAllByStaffIdAndTo(String staffId, String to,
			Date start, Date end) {
		String sql = "from ChatLogEO e where e.serviceStaffId=? and e.messageTime>=? and e.messageTime<?";
		return (List<ChatLogEO>) super.getListData(sql, new Object[] { staffId,
				start, DateUtil.getNextDay(end, 1) });
	}

	public void deleteByStaffIdandTimePeriod(String staffId, Date start,
			Date end) {
		List<ChatLogEO> lists = this.findAllByStaffId(staffId, start, end);
		super.deleteAllObject(lists);
	}

	public ChatLogEO findById(java.lang.Long id) {
		log.debug("getting ChatLogEO instance with id: " + id);
		try {
			ChatLogEO instance = (ChatLogEO) getSession().get(
					"com.eagleeye.eo.ChatLogEO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ChatLogEO> findByExample(ChatLogEO instance) {
		log.debug("finding ChatLogEO instance by example");
		try {
			List<ChatLogEO> results = (List<ChatLogEO>) getSession()
					.createCriteria("com.eagleeye.eo.ChatLogEO")
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
		log.debug("finding ChatLogEO instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ChatLogEO as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ChatLogEO instances");
		try {
			String queryString = "from ChatLogEO";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ChatLogEO merge(ChatLogEO detachedInstance) {
		log.debug("merging ChatLogEO instance");
		try {
			ChatLogEO result = (ChatLogEO) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ChatLogEO instance) {
		log.debug("attaching dirty ChatLogEO instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ChatLogEO instance) {
		log.debug("attaching clean ChatLogEO instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}