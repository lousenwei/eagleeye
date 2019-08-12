package com.eagleeye.post.dao.impl;

// default package
// Generated 2011-12-25 16:07:19 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eagleeye.common.dao.BaseDao;
import com.eagleeye.post.eo.PostEO;

/**
 * Home object for domain model class Post.
 * 
 * @see .Post
 * @author Hibernate Tools
 */

public class PostDAO extends BaseDao {

	private static final Log log = LogFactory.getLog(PostDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(PostEO transientInstance) {
		log.debug("persisting Post instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(PostEO persistentInstance) {
		log.debug("removing Post instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public PostEO merge(PostEO detachedInstance) {
		log.debug("merging Post instance");
		try {
			PostEO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PostEO findById(Integer id) {
		log.debug("getting Post instance with id: " + id);
		try {
			PostEO instance = entityManager.find(PostEO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 获取前n条有效的公告
	 * 
	 * @return
	 */
	public List getTopNumPosts() {
		try {
			String sql = "from PostEO e where e.status=? order by e.id desc";
			return super.getListByHqlLimit(sql, 5, "1");
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
}
