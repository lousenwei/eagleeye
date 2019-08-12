package com.eagleeye.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eagleeye.common.query.PageResult;
import com.eagleeye.common.util.PublicBean;

public class BaseUserDao extends HibernateDaoSupport {
	/** log4j instance. */
	private Logger log = Logger.getLogger(this.getClass());

	// private Connection con;
	// private SimpleJdbcTemplate jdbcTemplate ;
	/**
	 * 获取多条数据
	 * 
	 * @param sql
	 *            查询语句
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	List find(String sql) {
		this.logParams(sql, null);
		return getHibernateTemplate().find(sql);
	}

	/**
	 * 带有参数查询，获取多条数据
	 * 
	 * @param sql
	 *            查询语句
	 * @param parms
	 *            查询参数
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	List find(String sql, Object[] parms) {
		logParams(sql, parms);
		return getHibernateTemplate().find(sql, parms);
	}

	/**
	 * 带有参数查询，获取总的记录数
	 * 
	 * @param hql
	 *            查询语句
	 * @param parms
	 *            查询参数值
	 * @return 记录数
	 */
	long getRecordsCount(String hql, Object... parms) {
		logParams(hql, parms);
		Query queryCount = getSession().createQuery("select count(*) " + hql);
		if (parms != null) {
			for (int i = 0; i < parms.length; i++) {
				queryCount.setParameter(i, parms[i]);
			}
		}
		long totalRecords = -1;
		Object count = queryCount.iterate().next();
		if (count != null) {
			totalRecords = ((Long) count).longValue();
		}
		return totalRecords;
	}

	/**
	 * 根据jdbcSql得到记录总数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	long getCountsWithSql(String sql, Object... params) {
		logParams(sql, params);
		Query queryCount = getSession().createQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				queryCount.setParameter(i, params[i]);
			}
		}
		long totalRecords = -1;
		Object count = queryCount.iterate().next();
		if (count != null) {
			totalRecords = ((Long) count).longValue();
		}
		return totalRecords;
	}

	/**
	 * 查询结果是一个字符串.
	 * 
	 * @param hsql
	 *            查询语句
	 * @return 单个字符串
	 */
	@SuppressWarnings("unchecked")
	String getSingleHSQLString(String hsql) {
		Iterator it = getHibernateTemplate().iterate(hsql);
		if (it.hasNext()) {
			Object l = (Object) it.next();
			if (l != null) {
				return l.toString();
			}
		}
		return null;
	}

	/**
	 * 获取单条数据
	 * 
	 * @param clazz
	 *            Class
	 * @param s
	 *            Serializable
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	Object getSingleObject(Class clazz, Serializable s) {
		return getHibernateTemplate().get(clazz, s);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param clazz
	 *            Class
	 * @param s
	 *            Serializable
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	Object loadSingleObject(Class clazz, Serializable s) {
		return getHibernateTemplate().load(clazz, s);
	}

	/**
	 * 增加记录
	 * 
	 * @param obj
	 *            待保存对象
	 */
	void saveSingleObject(Object obj) {
		getHibernateTemplate().save(obj);
	}

	/**
	 * 增加或者修改记录
	 * 
	 * @param obj
	 *            待保存或修改对象
	 */
	void saveOrUpdateSingleObject(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * 批量增加或者修改记录
	 * 
	 * @param obj
	 *            待保存或修改对象
	 */
	@SuppressWarnings("unchecked")
	void saveOrUpdateAll(Collection obj) {
		getHibernateTemplate().saveOrUpdateAll(obj);
	}

	/**
	 * 修改记录
	 * 
	 * @param obj
	 *            待修改对象
	 */
	void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	/**
	 * 删除记录
	 * 
	 * @param obj
	 *            待删除对象
	 */
	void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	/**
	 * 删除所选记录
	 * 
	 * @param obj
	 *            待删除对象集合
	 */
	@SuppressWarnings("unchecked")
	void deleteAll(Collection obj) {
		getHibernateTemplate().deleteAll(obj);
	}

	/**
	 * 根据JDBC得到查询出来的一个值.
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	Object getCountByJDBCsql(String sql, Object... params) {
		Object totalRecords = null;
		this.logParams(sql, params);
		Query query = getSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		List lst = query.list();
		if (lst.size() > 0) {
			totalRecords = (Object) lst.get(0);
		}
		return totalRecords;
	}

	/**
	 * 根据JDBC得到查询出来的一个值.
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	List getListByJDBChSql(String sql, Object... params) throws Exception {
		this.logParams(sql, params);
		Query query = getSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		List lst = query.list();
		return lst;
	}

	/***
	 * 进行多表操作时 根据JDBCSQL得到分面数据.
	 * 
	 * @param sql
	 * @param startPage
	 * @param pageTotal
	 * @param params参数
	 *            .
	 * @return list<Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	PageResult getPageination(String sql, int startPage, int pageTotal,
			Class className, Object... params) throws Exception {
		// 得到总录数.
		logParams(sql, params);
		String countSql = "select count(*) from( " + sql + ")";
		// 得到总记录数.
		int countTotal = Integer.parseInt(getCountByJDBCsql(countSql, params)
				.toString());
		// 得到记录信息.
		List list = null;
		String jdbcSql = "select * from(select rownum num_0,tab_0.* from ("
				+ sql + ") tab_0 where rownum < =" + startPage * pageTotal
				+ ") where num_0 >" + (startPage - 1) * pageTotal;
		SimpleJdbcTemplate jdbcTemplate = this.connection();
		// 若总记录数为0
		if (countTotal == 0) {
			list = new ArrayList();
			if (className != null) {
				list = (List) PublicBean.getBeans(className, list);
			}
		} else {
			list = jdbcTemplate.queryForList(jdbcSql, params);
			if (className != null) {
				list = (List) PublicBean.getBeans(className, list);
			}
		}
		return new PageResult(countTotal, list);
	}

	/***
	 * 进行多表操作时 根据JDBCSQL得到数据,不分页
	 * 
	 * @param sql
	 * @param startPage
	 * @param pageTotal
	 * @param mapNames
	 * @param params参数
	 *            .
	 * @return list<Map>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	List getListByJDBSsql(String sql, Class className, Object... params)
			throws Exception {
		// try {
		logParams(sql, params);
		SimpleJdbcTemplate jdbcTemplate = this.connection();
		List list = jdbcTemplate.queryForList(sql, params);
		if (className != null) {
			list = (List) PublicBean.getBeans(className, list);
		}
		return list;
		// }
		// catch(Exception ex) {
		// throw ex;
		// }
	}

	/**
	 * 得到翻页结果
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            客户端Dao类查询参数
	 * @return 翻页结果
	 */
	@SuppressWarnings("unchecked")
	PageResult getListByPagination(String sql, int startPage, int pageTotal,
			Object... params) {
		// 打印信息.
		logParams(sql, params);
		Query query = getSession().createQuery(sql);
		Query queryCount = null;
		queryCount = getSession().createQuery("select count(*) " + sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
				queryCount.setParameter(i, params[i]);
			}
		}
		int totalRecords = (int) ((Long) queryCount.iterate().next())
				.intValue();
		query.setFirstResult((startPage - 1) * pageTotal);
		query.setMaxResults(pageTotal);
		List list = query.list();
		return new PageResult(totalRecords, list);
	}

	/**
	 * 得到翻页结果
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            客户端Dao类查询参数
	 * @return 翻页结果
	 */
	@SuppressWarnings("unchecked")
	PageResult getListByPaginationRowNum(String sql, int startRow, int pageTotal,
			Object... params) {
		// 打印信息.
		logParams(sql, params);
		Query query = getSession().createQuery(sql);
		Query queryCount = null;
		queryCount = getSession().createQuery("select count(*) " + sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
				queryCount.setParameter(i, params[i]);
			}
		}
		int totalRecords = (int) ((Long) queryCount.iterate().next())
				.intValue();
		query.setFirstResult(startRow);
		query.setMaxResults(pageTotal);
		List list = query.list();
		return new PageResult(totalRecords, list);
	}
	
	/**
	 * 打印参数信息
	 * 
	 * @param finalSQL
	 *            查询sql语句
	 * @param params
	 *            客户端Dao类查询参数
	 * @param ps
	 *            PagedStatement
	 */
	private void logParams(String finalSQL, Object[] params) {
		/************ 打印调试信息开始 *****************/
		log.info("finalSQL:" + finalSQL);
		if (params != null) {
			for (Object o : params) {
				log.info("params :" + o);
			}
		}
		/************ 打印调试信息结束 *****************/
	}

	/**
	 * 无状态批量删除数据，用于单独的批量删除操作，暂时不能和其它业务统一进行事务处理
	 * 
	 * @param entity
	 *            实体BEAN
	 * @param idValues
	 *            主健值集合
	 */
	@SuppressWarnings("unchecked")
	void batchDeleteData(Class entity, Collection idValues) {
		String tableName = AnnotationUtils.getMappedTableName(entity);
		String keyColumnName = AnnotationUtils.getMappedKeyColumnName(entity);
		StatelessSession session = getSessionFactory().openStatelessSession();
		String finalSQL = "delete from " + tableName + " where "
				+ keyColumnName + " = :value";
		Query query = session.createSQLQuery(finalSQL);
		Transaction ta = session.getTransaction();
		ta.begin();
		for (Object ob : idValues) {
			query.setParameter("value", ob);
			query.executeUpdate();
		}
		ta.commit();
		session.close();
	}

	int deleteWithJDBCsqL(String sql, Object... params) {
		SimpleJdbcTemplate jdbcTemplate = this.connection();
		this.logParams(sql, params);
		int a = jdbcTemplate.update(sql, params);
		return a;
	}

	/**
	 * 根据sql语句来进行update操作
	 * 
	 * @param sql
	 *            传统的jdbc sql语句
	 * @param params
	 *            问号参数数组
	 * @return 更新的记录数量
	 */
	int updateWithJdbcSql(String sql, Object... params) {
		int c = -1;
		this.logParams(sql, params);
		SimpleJdbcTemplate jdbcTemplate = this.connection();
		c = jdbcTemplate.update(sql, params);
		log.debug("updateWithSql更改的记录数量为：" + c);
		return c;
	}

	/**
	 * 根据sql语句来进行update操作
	 * 
	 * @param sql
	 *            传统的jdbc sql语句
	 * @param params
	 *            问号参数数组
	 * @return 更新的记录数量
	 */
	int updateOldWithJdbcSql(String sql, Object... params) {
		int c = -1;
		this.logParams(sql, params);
		Query query = this.getSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		c = query.executeUpdate();
		log.debug("updateWithSql更改的记录数量为：" + c);
		return c;
	}

	/**
	 * 根据hql语句来进行update操作
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            问号参数数组
	 * @return 更新的记录数量
	 */
	int updateWithHibernateHql(String hql, Object... params) {
		int c = -1;
		this.logParams(hql, params);
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		c = query.executeUpdate();
		log.debug("updateWithHql更改的记录数量为：" + c);
		return c;
	}

	/***
	 * 得到数据库的连接.
	 * 
	 * @return
	 * @throws SQLException
	 */
	private SimpleJdbcTemplate connection() {
		// if (con ==null || con.isClosed()){
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(
				SessionFactoryUtils.getDataSource(getSessionFactory()));
		// con
		// =SessionFactoryUtils.getDataSource(this.getSessionFactory()).getConnection();
		return jdbcTemplate;
	}

	int delete(String hql, Object... params) {
		logParams(hql, params);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}

	// 调用存储过程:不返回值
	void callProcedure(String call, Object... params) throws Exception {
		SimpleJdbcTemplate jdbcTemplate = this.connection();
		this.logParams(call, params);
		jdbcTemplate.update(call, params);
	}

	List callProcedureHasReturn(String call, Object... params) throws Exception {
		SimpleJdbcTemplate jdbcTemplate = this.connection();
		this.logParams(call, params);
		return jdbcTemplate.queryForList(call, params);
	}

	List getListByHqlLimit(String hql, int limitNum, Object... params) {
		logParams(hql, params);
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setMaxResults(limitNum);
		return query.list();
	}
}
