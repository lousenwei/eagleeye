package com.eagleeye.cache;

import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 
 * cache管理类，单例模式，保证在整个application中只有一个instance
 * 
 */
public final class CacheAdministrator {

	/** cache管理类实例 */
	private static GeneralCacheAdministrator administrator;

	/**
	 * 私有构造函数
	 */
	private CacheAdministrator() {
		super();
	}

	/**
	 * 
	 * @return cache管理类实例
	 */
	public static GeneralCacheAdministrator getInstance() {
		if (administrator == null) {
			administrator = new GeneralCacheAdministrator();
		}
		return administrator;
	}
}
