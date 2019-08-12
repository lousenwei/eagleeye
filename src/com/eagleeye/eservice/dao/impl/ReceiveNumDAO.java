package com.eagleeye.eservice.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.eservice.eo.ChatPeerEO;
import com.eagleeye.eservice.eo.ChatPeerEOId;
import com.eagleeye.eservice.eo.ReplyStatOnDayEO;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReplyStatOnDay entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.eagleeye.ReplyStatOnDayEO.ReplyStatOnDay
 * @author MyEclipse Persistence Tools
 */

public class ReceiveNumDAO extends BaseDao {
	private static final Logger log = LoggerFactory
			.getLogger(ReceiveNumDAO.class);
	// property constants
	public static final String SERVICE_STAFF_ID = "serviceStaffId";
	public static final String USER_ID = "userId";
	public static final String REPLY_NUM = "replyNum";

	public void save(ReplyStatOnDayEO transientInstance) {
		log.debug("saving ReplyStatOnDay instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReplyStatOnDayEO persistentInstance) {
		log.debug("deleting ReplyStatOnDay instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReplyStatOnDayEO findById(java.lang.Long id) {
		log.debug("getting ReplyStatOnDay instance with id: " + id);
		try {
			ReplyStatOnDayEO instance = (ReplyStatOnDayEO) getSession().get(
					"com.eagleeye.eo.ReplyStatOnDay", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ReplyStatOnDayEO> findByExample(ReplyStatOnDayEO instance) {
		log.debug("finding ReplyStatOnDay instance by example");
		try {
			List<ReplyStatOnDayEO> results = (List<ReplyStatOnDayEO>) getSession()
					.createCriteria("com.eagleeye.eo.ReplyStatOnDay")
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
		log.debug("finding ReplyStatOnDay instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReplyStatOnDay as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ReplyStatOnDayEO> findByServiceStaffId(Object serviceStaffId) {
		String sql = "from ReplyStatOnDayEO e where e.id.serviceStaffId=?";
		return super.getListData(sql, new Object[] { serviceStaffId });
	}

	public List<ReplyStatOnDayEO> findByReplyNum(Object replyNum) {
		return findByProperty(REPLY_NUM, replyNum);
	}

	public List findAll() {
		log.debug("finding all ReplyStatOnDay instances");
		try {
			String queryString = "from ReplyStatOnDay";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 根据chatpeers计算接待数量
	 * 
	 * @param managerId
	 * @param chatDate
	 * @return
	 */
	public List getReplyNumByChatPeers(String service_staff_id, Date chatDate) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select service_staff_id,chat_date, count(1) num ");
			sql.append("from chat_peer where service_staff_id = ? ");
			sql.append("and chat_date=? ");
			sql.append("group by service_staff_id ");
			List<Map> results = super.getListByJDBCSqL(sql.toString(), null,
					new Object[] { service_staff_id, chatDate });
			return results;
		} catch (Exception re) {
			log.error(re.toString());
		}
		return null;
	}

	public ReplyStatOnDayEO merge(ReplyStatOnDayEO detachedInstance) {
		log.debug("merging ReplyStatOnDay instance");
		try {
			ReplyStatOnDayEO result = (ReplyStatOnDayEO) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReplyStatOnDayEO instance) {
		log.debug("attaching dirty ReplyStatOnDay instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReplyStatOnDayEO instance) {
		log.debug("attaching clean ReplyStatOnDay instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}