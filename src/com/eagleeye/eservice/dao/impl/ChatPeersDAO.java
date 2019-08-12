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
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.eo.ChatPeerEO;
import com.eagleeye.eservice.eo.ChatPeerEOId;
import com.eagleeye.eservice.mo.ChatPeerQueryParametersMO;

/**
 * A data access object (DAO) providing persistence and search support for
 * ChatPeerEO entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.eagleeye.ChatPeerEO.ChatPeerEO
 * @author MyEclipse Persistence Tools
 */

public class ChatPeersDAO extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(ChatPeersDAO.class);
	// property constants
	public static final String SERVICE_STAFF_ID = "serviceStaffId";
	public static final String COUNT = "count";
	public static final String UID = "uid";

	public void save(ChatPeerEO transientInstance) {
		log.debug("saving ChatPeerEO instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ChatPeerEO persistentInstance) {
		log.debug("deleting ChatPeerEO instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ChatPeerEO findById(java.lang.Long id) {
		log.debug("getting ChatPeerEO instance with id: " + id);
		try {
			ChatPeerEO instance = (ChatPeerEO) getSession().get("com.eagleeye.eo.ChatPeerEO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ChatPeerEO> findByExample(ChatPeerEO instance) {
		log.debug("finding ChatPeerEO instance by example");
		try {
			List<ChatPeerEO> results = (List<ChatPeerEO>) getSession().createCriteria("com.eagleeye.eo.ChatPeerEO")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ChatPeerEO instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ChatPeerEO as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProperties(Map<String, Object> params) {
		if (params == null || params.isEmpty())
			return null;
		try {
			String queryString = "from ChatPeerEO as model where 1=1 ";
			Query queryObject = getSession().createQuery(queryString);
			int count = 0;
			for (String key : params.keySet()) {
				queryString = queryString + " model." + key + "= ? ";
				queryObject.setParameter(count, params.get(key));
				count++;
				if (count != params.size()) {
					queryString = queryString + " and ";
				}
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ChatPeerEO> findByServiceStaffId(Object serviceStaffId) {
		String sql = "from ChatPeerEO e where e.id.serviceStaffId=?";
		return super.getListData(sql, new Object[] { serviceStaffId });
	}

	public List<ChatPeerEO> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List<ChatPeerEO> findByUid(Object uid) {
		String sql = "from ChatPeerEO e where e.id.uid=?";
		return super.getListData(sql, new Object[] { uid });
	}

	public List findAll() {
		log.debug("finding all ChatPeerEO instances");
		try {
			String queryString = "from ChatPeerEO";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ChatPeerEO merge(ChatPeerEO detachedInstance) {
		log.debug("merging ChatPeerEO instance");
		try {
			ChatPeerEO result = (ChatPeerEO) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ChatPeerEO instance) {
		log.debug("attaching dirty ChatPeerEO instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ChatPeerEO instance) {
		log.debug("attaching clean ChatPeerEO instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStaffidandTimePeriod(String staffId, Date start, Date end) {
		if (staffId == null || "".equals(staffId) || start == null || end == null) {
			return null;
		}
		try {
			String sql = "from ChatPeerEO e where e.id.serviceStaffId=? and t.id.chatDate>=? and t.id.chatDate<=?";
			return super.getListData(sql, new Object[] { staffId, start, end });
		} catch (RuntimeException re) {
			log.error(re.toString());
			throw re;
		}
	}

	public List getDistinctUidByStaffIdandTimePeriod(String staffId, Date start, Date end) {
		if (staffId == null || "".equals(staffId) || start == null || end == null) {
			return null;
		}
		try {
			String sql = "select distinct uid from chat_peer t where t.service_staff_id=? and t.id.chatDate>=? and t.id.chatDate<=?";
			return super.getListByJDBCSqL(sql, null, new Object[] { staffId, start, end });
		} catch (Exception re) {
			log.error(re.toString());
		}
		return null;
	}

	public List getChatPeerEOsByBuyNickandTimePeriod(String managerId, String buyNick, Date start, Date end,
			String orderBy) throws Exception {
		if (buyNick == null || "".equals(buyNick) || start == null || end == null) {
			throw new Exception("买家昵称或者起始终止日期为空");
		}
		try {
			// String sql =
			// "from ChatPeerEO t where t.id.uid=? and t.id.chatDate>=? and t.id.chatDate<=? and t.id.serviceStaffId like '"
			// + managerId + "%' order by t.id.chatDate " + orderBy;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c.* FROM chat_peer c,group_member g where c.uid=? ");
			sql.append(" and (c.chat_date between ? and ?) ");
			sql.append(" and c.service_staff_id=g.service_staff_id and g.manager_id=? ");
			sql.append(" order by c.chat_date " + orderBy);
			List<Map> results = super.getListByJDBCSqL(sql.toString(), null, new Object[] { buyNick, start, end,
					managerId });
			if (results != null && !results.isEmpty()) {
				List<ChatPeerEO> returns = new ArrayList();
				for (Map tmp : results) {
					ChatPeerEO chatPeer = new ChatPeerEO();
					ChatPeerEOId id = new ChatPeerEOId();
					id.setUid((String) tmp.get("uid"));
					id.setServiceStaffId((String) tmp.get("service_staff_id"));
					id.setChatDate((Date) tmp.get("chat_date"));
					chatPeer.setId(id);
					chatPeer.setCreatedAt((Date) tmp.get("create_at"));
					returns.add(chatPeer);
				}
				return returns;
			}
		} catch (Exception re) {
			log.error(re.toString());
		}
		return null;
	}

	public List<ChatPeerEO> getDistinctRecordByBuyNickandTimePeriod(String managerId, String buyNick, Date start,
			Date end, String orderBy) throws Exception {
		if (buyNick == null || "".equals(buyNick) || start == null || end == null) {
			throw new Exception("买家昵称或者起始终止日期为空");
		}
		try {
			// String sql =
			// "from ChatPeerEO t where t.id.uid=? and t.id.chatDate>=? and t.id.chatDate<=? and t.id.serviceStaffId like '"
			// + managerId + "%' order by t.id.chatDate " + orderBy;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct c.service_staff_id FROM chat_peer c,group_member g where c.uid=? ");
			sql.append(" and (c.chat_date between ? and ?) ");
			sql.append(" and c.service_staff_id=g.service_staff_id and g.manager_id=? ");
			sql.append(" order by c.chat_date " + orderBy);
			List<Map> results = super.getListByJDBCSqL(sql.toString(), null, new Object[] { buyNick, start, end,
					managerId });
			if (results != null && !results.isEmpty()) {
				List<ChatPeerEO> returns = new ArrayList();
				for (Map tmp : results) {
					ChatPeerEO chatPeer = new ChatPeerEO();
					ChatPeerEOId id = new ChatPeerEOId();
					id.setServiceStaffId((String) tmp.get("service_staff_id"));
					chatPeer.setId(id);
					returns.add(chatPeer);
				}
				return returns;
			}
		} catch (Exception re) {
			log.error(re.toString());
		}
		return null;
	}

	public List<Map> getDistinctRecordByBuyNickandTimePeriod(ChatPeerQueryParametersMO mo) throws Exception {
		if (mo.getBuyNick() == null || "".equals(mo.getBuyNick()) || mo.getStart() == null || mo.getEnd() == null) {
			throw new Exception("买家昵称或者起始终止日期为空");
		}
		try {
			// String sql =
			// "from ChatPeerEO t where t.id.uid=? and t.id.chatDate>=? and t.id.chatDate<=? and t.id.serviceStaffId like '"
			// + managerId + "%' order by t.id.chatDate " + orderBy;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct c." + mo.getField());
			sql.append(" FROM chat_peer c,group_member g where c.uid=? ");
			sql.append(" and (c.chat_date between ? and ?) ");
			sql.append(" and c.service_staff_id=g.service_staff_id and g.manager_id=? ");
			sql.append(" order by c.chat_date " + mo.getOrderBy());
			List<Map> results = super.getListByJDBCSqL(sql.toString(), null,
					new Object[] { mo.getBuyNick(), mo.getStart(), mo.getEnd(), mo.getManagerId() });
			if (results != null && !results.isEmpty()) {
				return results;
			}
		} catch (Exception re) {
			log.error(re.toString());
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			ChatPeersDAO chatPeersDao = (ChatPeersDAO) EagleEyeServiceLocator.getBean("chatPeersDao");
			ChatPeerQueryParametersMO mo = new ChatPeerQueryParametersMO();
			mo.setBuyNick("小丫丫爱美丽");
			mo.setEnd(DateUtil.getDateByString("2011-09-22", DateUtil.datePatternYY_MM_DD));
			mo.setStart(DateUtil.getDateByString("2011-09-22", DateUtil.datePatternYY_MM_DD));
			mo.setField("uid");
			mo.setManagerId("bao317045589");
			mo.setOrderBy("desc");
			List<Map> a = chatPeersDao.getDistinctRecordByBuyNickandTimePeriod(mo);
			System.out.println(a.get(0).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}