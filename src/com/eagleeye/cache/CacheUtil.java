package com.eagleeye.cache;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.Messages;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
public abstract class CacheUtil {

	/** cache管理类实例 */
	private GeneralCacheAdministrator administrator = CacheAdministrator.getInstance();

	/** log4j instance. */
	protected Logger log = Logger.getLogger(this.getClass());

	/** 刷新周期 120天 */
	private int refreshPeriod = 360 * 24 * 60 * 60;

	/**
	 * 读取cache中的缓存对象
	 * 
	 * @param key
	 *            key值
	 * @param refreshPeriod
	 *            刷新周期，单位为S
	 * @return cache中对应key的对象
	 * @throws Exception
	 *             需要更新cache或者cache中不存在对应key的值
	 */
	private Object getFromCache(String key, int refreshPeriod) throws Exception {
		Object object = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug("key=" + key + " ; refreshPeriod=" + refreshPeriod);
			}
			object = administrator.getFromCache(key, refreshPeriod);
		} catch (NeedsRefreshException e) {
			throw new Exception(""+Messages.getString("CacheUtil_59_Label_0")+".");
		}
		return object;
	}

	/**
	 * 把缓存对象置入cache中
	 * 
	 * @param key
	 *            key值
	 * @param object
	 *            置入cache中的对象
	 */
	protected void putInCache(String key, Object object) {
		administrator.putInCache(key, object);
	}

	/**
	 * 返回缓存的list
	 * 
	 * @param key
	 *            key值
	 * @param refreshPeriod
	 *            刷新周期，单位为S
	 * @return ArrayList
	 * @throws ToogoSysException
	 */
	@SuppressWarnings("unchecked")
	protected List getCacheList(String key, int refreshPeriod) throws Exception {
		List list = null;
		try {
			// 从cache中读取数据
			list = (List) getFromCache(key, refreshPeriod);
		} catch (Exception e) {
			log.error("getCacheList :", e);
			throw new Exception(""+Messages.getString("CacheUtil_95_Label_0")+"!." + e.getMessage());
		}
		return list;
	}

	/**
	 * 返回缓存的list
	 * 
	 * @param key
	 *            key值
	 * @return ArrayList
	 * @throws ToogoSysException
	 */
	@SuppressWarnings("unchecked")
	protected List getCacheList(String key) throws Exception {
		return getCacheList(key, refreshPeriod);
	}

	/**
	 * 返回缓存的map
	 * 
	 * @param key
	 *            key值
	 * @param refreshPeriod
	 *            刷新周期，单位为S
	 * @return Map
	 * @throws UspSysException
	 */
	@SuppressWarnings("unchecked")
	protected Map getCacheMap(String key, int refreshPeriod) throws Exception {
		Map map = null;
		try {
			// 从cache中读取数据
			map = (Map) getFromCache(key, refreshPeriod);
		} catch (Exception e) {
			log.error("getCacheMap :", e);
			throw new Exception(""+Messages.getString("CacheUtil_131_Label_0")+"!." + e.getMessage());
		}
		return map;
	}

	/**
	 * 返回缓存的map
	 * 
	 * @param key
	 *            key值
	 * @return Map
	 * @throws UspSysException
	 */
	@SuppressWarnings("unchecked")
	protected Map getCacheMap(String key) throws Exception {
		return getCacheMap(key, refreshPeriod);
	}

	/**
	 * 返回缓存的Object
	 * 
	 * @param key
	 *            key值
	 * @return Object
	 * @throws Exception
	 */
	protected Object getCacheObject(String key) throws Exception {
		return getFromCache(key, refreshPeriod);
	}

	/**
	 * 刷新缓存
	 * 
	 * @throws Exception
	 */
	public abstract void refreshCache() throws Exception;

	/**
	 * 父类CacheUtil刷新指定key值的缓存操作，可以由子类重写次方法进行覆盖
	 * 
	 * @param key
	 *            缓存的key值
	 * @throws UspSysException
	 */
	public void refreshCache(String key) throws Exception {
		log.debug("父类CacheUtil刷新制定key值的缓存" + key);
	}
}