package com.eagleeye.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.eagleeye.common.query.PageResult;

/***
 * 用户使用的查询.
 * 
 * @author wilson.lou
 * 
 */
public class BaseDao extends BaseUserDao {
	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 返回实体对象的class名称
	 * 
	 * @param clazz
	 *            实体对象
	 * @return name
	 */
	@SuppressWarnings("unchecked")
	private String getClassEntityName(Class clazz) {
		String name = "";
		if (clazz != null) {
			name = clazz.getSimpleName();
		}
		return name;
	}

	/**
	 * 获取查询语句
	 * 
	 * @param table
	 *            实体对象
	 * @return 查询语句
	 */
	@SuppressWarnings("unchecked")
	private String getHsqlFromStr(Class table) {
		String hsql = "from " + getClassEntityName(table);
		log.debug("hsql = " + hsql);
		return hsql;
	}

	/******************** 以下是各个业务模块可以调用的方法 ***********************/
	// 得到数据的条数.
	/**
	 * 带有参数查询，获取总的记录数
	 * 
	 * @param hql
	 *            查询语句
	 * @param parms
	 *            查询参数
	 * @return 记录数
	 */
	public long getCount(String hql, Object[] parms) {
		return super.getRecordsCount(hql, parms);
	}

	/**
	 * 根据Hibernate sql 带有参数查询，获取总的记录数
	 * 
	 * @param hqlName
	 *            查询语句
	 * @param params
	 *            参数值
	 * @return 记录数
	 */
	public long getCountBySqlName(String hqlSql, Object[] params) {
		return getCount(hqlSql, params);
	}

	/***
	 * 根据一般的jdbc sql得到总数.
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public long getCountWithSql(String sql, Object[] params) {
		return super.getCountsWithSql(sql, params);
	}

	/**
	 * 获取一张表的记录数量
	 * 
	 * @param table
	 *            实体对象
	 * @return 记录数
	 */
	@SuppressWarnings("unchecked")
	public long getCountByClass(Class table) {
		return getCount(getHsqlFromStr(table), null);
	}

	// 得到数据的条数END
	// 得到表的单条记录.
	/**
	 * 获取单条数据
	 * 
	 * @param clazz
	 *            Class
	 * @param s
	 *            Serializable主键
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	public Object get(Class clazz, Serializable s) {
		return super.getSingleObject(clazz, s);
	}

	/**
	 * 取得单条数据
	 * 
	 * @param table
	 *            实体对象
	 * @param code
	 *            数据code(主键)
	 * @throws Exception
	 * @return 单条数据
	 */
	@SuppressWarnings("unchecked")
	public Object getData(Class table, Serializable code) {
		return get(table, code);
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
	public Object load(Class clazz, Serializable s) {
		return super.loadSingleObject(clazz, s);
	}

	// 得到单条记录END

	// 得到数据集.
	/**
	 * 获取多条数据
	 * 
	 * @param sql
	 *            查询语句
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	public List getListData(String sql) {
		return super.find(sql);
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
	public List getListData(String sql, Object[] parms) {
		return super.find(sql, parms);
	}

	/**
	 * 根据表名得到该表的所有数据集.
	 * 
	 * @param table
	 *            相应的entity
	 * @return 所有数据列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getListPageAll(Class table) {
		return getListData(getHsqlFromStr(table));
	}

	// 得到数据集END
	/**
	 * 判断指定code的实体是否存在，如果存在的话，赋值给obj
	 * 
	 * @param obj
	 *            实体对象
	 * @throws Exception
	 * @return 单条数据
	 */
	public boolean locate(Object obj) throws Exception {
		Serializable idValue = AnnotationUtils.getVoKeyValue(obj);
		return locate(obj, idValue);
	}

	/**
	 * 判断指定code的实体是否存在，如果存在的话，赋值给obj
	 * 
	 * @param obj
	 *            实体对象
	 * @param code
	 *            数据code
	 * @throws Exception
	 * @return 单条数据
	 */
	public boolean locate(Object obj, Serializable code) throws Exception {
		if (obj != null && code != null) {
			Object newEntity = get(obj.getClass(), code);
			if (newEntity != null) {
				BeanUtils.copyProperties(obj, newEntity);
				return true;
			}
		}
		return false;
	}

	// 添加数据
	/**
	 * 增加数据
	 * 
	 * @param data
	 *            数据
	 * @throws Exception
	 */
	public void addData(Object data) {
		save(data);
	}

	/**
	 * 增加记录
	 * 
	 * @param obj
	 *            待保存对象
	 */
	public void save(Object obj) {
		super.saveSingleObject(obj);
	}

	/**
	 * 修改数据
	 * 
	 * @param data
	 *            数据
	 * @throws Exception
	 */
	public void updateData(Object data) {
		super.update(data);
	}

	/**
	 * 增加或者修改记录
	 * 
	 * @param obj
	 *            待保存或修改对象
	 */
	public void saveOrUpdate(Object obj) {
		super.saveOrUpdateSingleObject(obj);
	}

	/**
	 * 批量增加或者修改记录
	 * 
	 * @param obj
	 *            待保存或修改对象
	 */
	@SuppressWarnings("unchecked")
	public void saveOrUpdateBatch(Collection obj) {
		super.saveOrUpdateAll(obj);
	}

	/***
	 * 批量添加.
	 * 
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	public void saveAllObject(Collection obj) {
		for (Object date : obj) {
			this.addData(date);
		}
	}

	/***
	 * 批量删除。
	 * 
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	public void deleteAllObject(Collection obj) {
		super.deleteAll(obj);
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
	@SuppressWarnings("unchecked")
	public int updateWithSql(String sql, Object[] params) {
		return super.updateOldWithJdbcSql(sql, params);
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
	public int updateWithHql(String hql, Object[] params) {
		return super.updateWithHibernateHql(hql, params);
	}

	/**
	 * 判断对象是否为BaseEntity.class.
	 * 
	 * @param data
	 *            数据
	 * @throws Exception
	 */
	public void deleteData(Object data) {
		// Assert.isInstanceOf(BaseEntity.class, data);
		if (data != null) {
			log.info("删除对象: " + data.getClass().getSimpleName());
			super.delete(data);
		}
	}

	/**
	 * 批量删除数据
	 * 
	 * @param data
	 *            待删除对象集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteBatch(Collection data) {
		for (Object obj : data) {
			deleteData(obj);
		}
	}

	/**
	 * 翻页查询多表或单表查询.
	 * 
	 * @param sql
	 *            查询语句
	 * @param startPage
	 *            起始页
	 * @param pageSize
	 *            每页的最大记录数.
	 * @param params
	 *            客户端Dao类查询参数
	 * @return 翻页结果
	 */
	public PageResult getPageListInfo(String sql, int startPage, int pageSize,
			Object[] params) {
		return super.getListByPagination(sql, startPage, pageSize, params);
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
	public PageResult getListByPaginationRowNum(String sql, int startRow,
			int pageTotal, Object... params) {
		return super
				.getListByPaginationRowNum(sql, startRow, pageTotal, params);
	}

	/***
	 * 注:这些表不要用hibernate映射的类,直接使用数据库的表名. 根据JDBCSQL得到分面数据.
	 * 当输入className时,将返回对应的list<className>
	 * 否则将回返对应的List<Map<String(字段别名),Object>>,
	 * 
	 * @param sql
	 *            查询语句
	 * @param startPage
	 *            起始页
	 * @param pageSize
	 *            每页的最大记录数.
	 * @return list<Map<String,String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PageResult getPageinationByJDBC(String sql, int startPage,
			int pageSize, Class className, Object[] params) throws Exception {
		return super
				.getPageination(sql, startPage, pageSize, className, params);
	}

	/***
	 * 注:这些表不要用hibernate映射的类,不分页数据. 当输入className时,将返回对应的list<className>
	 * 否则将回返对应的List<Map<String(字段别名),Object>>,
	 * 
	 * @param sql
	 *            sql语句.
	 * @return list<Object>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getListByJDBCSqL(String sql, Class className, Object[] params)
			throws Exception {
		return super.getListByJDBSsql(sql, className, params);
	}

	/***
	 * 
	 * 注:这些表名不要用hibernate映射的类. 根据JDBC修改批处理修改记录.
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int updateByJDBCsql(String sql, Object... params) throws Exception {
		return super.updateWithJdbcSql(sql, params);
	}

	/**
	 * 注:这些表名不要用hibernate映射的类. 根据JDBC得到第一条记录.
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Object getValueByJDBCsql(String sql, Object[] params) {
		return super.getCountByJDBCsql(sql, params);
	}

	/***
	 * 根据jdbcSql删除信息.
	 */
	public int deleteByJDBCsqL(String sql, Object... params) throws Exception {
		return super.deleteWithJDBCsqL(sql, params);
	}

	/**
	 * 根据使用hibenate的JDBC得到查询返回:当含有多个字段时返回List<Object[]>;
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getListByJDBChHSql(String sql, Object[] params)
			throws Exception {
		return super.getListByJDBChSql(sql, params);
	}

	// 调用存储过程:不返回值
	public void callProcedure(String call, Object... params) throws Exception {
		super.callProcedure(call, params);
	}

	/*
	 * //获取JDBC 查询语句的ResultSet结果对象 public ResultSet getResultSetByJDBCSql(String
	 * sql, Object...params) throws Exception { return
	 * super.getResultSetByJDBCSql(sql, params); }
	 */
	public List callProcedureHasReturn(String call, Object... params)
			throws Exception {
		return super.callProcedureHasReturn(call, params);
	}

	/*
	 * hql获取指定数量的对象 (non-Javadoc)
	 * 
	 * @see
	 * com.eagleeye.common.dao.BaseUserDao#getListByHqlLimit(java.lang.String,
	 * int, java.lang.Object[])
	 */
	public List getListByHqlLimit(String hql, int limitNum, Object... params) {
		return super.getListByHqlLimit(hql, limitNum, params);
	}
}
