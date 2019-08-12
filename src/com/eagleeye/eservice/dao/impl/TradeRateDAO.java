package com.eagleeye.eservice.dao.impl;

// default package
// Generated 2011-12-13 16:46:39 by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.common.query.PageResult;
import com.eagleeye.eservice.eo.TradeRateEO;
import com.eagleeye.eservice.eo.TradeRateEOId;
import com.eagleeye.eservice.mo.TradeRateQueryParametersMo;

/**
 * Home object for domain model class TradeRate.
 * 
 * @see .TradeRate
 * @author Hibernate Tools
 */

public class TradeRateDAO extends BaseDao {

	private static final Log log = LogFactory.getLog(TradeRateDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TradeRateEO transientInstance) {
		log.debug("persisting TradeRate instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TradeRateEO persistentInstance) {
		log.debug("removing TradeRate instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TradeRateEO merge(TradeRateEO detachedInstance) {
		log.debug("merging TradeRate instance");
		try {
			TradeRateEO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TradeRateEO findById(TradeRateEOId id) {
		log.debug("getting TradeRate instance with id: " + id);
		try {
			TradeRateEO instance = entityManager.find(TradeRateEO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 返回交易评价数据分页
	 * 
	 * @param queryMo
	 * @param startPage
	 * @param pageSize
	 * @return
	 */
	public PageResult getTradeRateEOsPagnationByManagerIdAndTimePreiod(
			TradeRateQueryParametersMo queryMo, int startNum, int pageSize) {
		String sql = "from TradeRateEO e where 1=1 ";
		ArrayList params = new ArrayList();
		if (queryMo.getManagerId() != null) {
			sql = sql + " and e.ratedNick=? ";
			params.add(queryMo.getManagerId());
		}
		if (queryMo.getNick() != null) {
			sql = sql + " and e.nick=? ";
			params.add(queryMo.getNick());
		}
		if (queryMo.getStartCreated() != null) {
			sql = sql + " and e.created>=? ";
			params.add(queryMo.getStartCreated());
		}
		if (queryMo.getEndCreated() != null) {
			sql = sql + " and e.created<=? ";
			params.add(queryMo.getEndCreated());
		}
		if (queryMo.getOwner() != null && !queryMo.getOwner().isEmpty()) {
			sql = sql + " and e.owner=? ";
			params.add(queryMo.getOwner());
		}
		if (queryMo.getType() != null && !queryMo.getType().isEmpty()) {
			sql = sql + " and e.result=? ";
			params.add(queryMo.getType());
		}
		if (queryMo.getResult() != null && !queryMo.getResult().isEmpty()) {
			String temp = "";
			int i = 0;
			for (String status : queryMo.getResult()) {
				if (++i != queryMo.getResult().size()) {
					temp = temp + "'" + status + "',";
				} else {
					temp = temp + "'" + status + "'";
				}
			}
			if (!"".equals(temp)) {
				sql = sql + " and e.result in (" + temp + ")";
			}
		}
		if (queryMo.getTid() != null && queryMo.getTid() != 0) {
			sql = sql + " and e.id.tid=?";
			params.add(queryMo.getTid());
		}
		return super.getListByPaginationRowNum(sql, startNum, pageSize,
				params.toArray());
	}
}
