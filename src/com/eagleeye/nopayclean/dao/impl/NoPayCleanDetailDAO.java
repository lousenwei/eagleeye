package com.eagleeye.nopayclean.dao.impl;

// default package
// Generated 2011-11-23 16:17:15 by Hibernate Tools 3.3.0.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.nopayclean.constant.NoPayCleanConstants;
import com.eagleeye.nopayclean.eo.NoPayCleanDetailEO;
import com.eagleeye.nopayclean.eo.NoPayCleanDetailEOId;
import com.eagleeye.schedule.constant.ScheduleConstants;

/**
 * Home object for domain model class NoPayCleanDetailEO.
 * 
 * @see .NoPayCleanDetailEO
 * @author Hibernate Tools
 */
public class NoPayCleanDetailDAO extends BaseDao {

	private static final Log log = LogFactory.getLog(NoPayCleanDetailDAO.class);

	public void persist(NoPayCleanDetailEO transientInstance) {
		log.debug("persisting NoPayCleanDetail instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(NoPayCleanDetailEO instance) {
		log.debug("attaching dirty NoPayCleanDetailEO instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NoPayCleanDetailEO instance) {
		log.debug("attaching clean NoPayCleanDetailEO instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(NoPayCleanDetailEO persistentInstance) {
		log.debug("deleting NoPayCleanDetailEO instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NoPayCleanDetailEO merge(NoPayCleanDetailEO detachedInstance) {
		log.debug("merging NoPayCleanDetailEO instance");
		try {
			NoPayCleanDetailEO result = (NoPayCleanDetailEO) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public NoPayCleanDetailEO findById(NoPayCleanDetailEOId id) {
		log.debug("getting NoPayCleanDetailEO instance with id: " + id);
		try {
			NoPayCleanDetailEO instance = (NoPayCleanDetailEO) getSession()
					.get("NoPayCleanDetailEO", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<NoPayCleanDetailEO> findByExample(NoPayCleanDetailEO instance) {
		log.debug("finding NoPayCleanDetailEO instance by example");
		try {
			List<NoPayCleanDetailEO> results = (List<NoPayCleanDetailEO>) getSession()
					.createCriteria("NoPayCleanDetailEO").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/**
	 * 全量清除一个用户信息
	 * 
	 * @param noPayCleanDetail
	 */
	public void deleteAllDataBySchedule(NoPayCleanDetailEO noPayCleanDetail) {
		try {
			NoPayCleanConstants.loadNoPayCleanConstants();
			for (String key : NoPayCleanConstants.TOTALDELETESQLMAP.keySet()) {
				String sql = NoPayCleanConstants.TOTALDELETESQLMAP.get(key);
				int i = super
						.deleteByJDBCsqL(sql, new Object[] { noPayCleanDetail
								.getId().getManagerId() });
				log.info("delete " + key + " nums: " + i);
			}
			int i = super.updateWithHql(NoPayCleanConstants.DELETE_SQL_TRADE,
					new Object[] { noPayCleanDetail.getId().getManagerId() });
			log.info("delete trade and orders nums: " + i);
			i = super.deleteByJDBCsqL(NoPayCleanConstants.DELETE_SQL_CHATPEER,
					new Object[] { noPayCleanDetail.getId().getManagerId()
							+ "%" });
			log.info("delete chatpeers nums: " + i);
		} catch (Exception e) {
			log.error("Delete error " + noPayCleanDetail.getId().getManagerId()
					+ "\n" + e);
		}
	}

	/**
	 * 获取所有未处理的过期人员列表
	 * 
	 * @return
	 */
	public List<NoPayCleanDetailEO> getAllNoUndoPayCleanDetails() {
		String sql = "from NoPayCleanDetailEO e where e.status=? order by e.id.createDate asc";
		return super.getListData(sql,
				new Object[] { ScheduleConstants.SCHEDULE_INIT_STATUS });
	}
}
