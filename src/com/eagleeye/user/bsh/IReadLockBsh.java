package com.eagleeye.user.bsh;

import java.util.List;

public interface IReadLockBsh {
	/**
	 * 批量保存需要读锁的对象
	 * 
	 * @param readLocks
	 */
	public void saveReadLocks(List readLocks);

	/**
	 * 根据managerId获取读锁对象
	 * 
	 * @param managerId
	 * @return
	 */
	public List getReadLocks(String managerId);

	/**
	 * 根据managerId删除其下需要读锁的对象
	 * 
	 * @param managerId
	 */
	public void deleteReadLocks(String managerId);

	/**
	 * 检查是否存在对应需要读锁的对象
	 * 
	 * @param managerId
	 * @param url
	 * @return
	 */
	public long checkReadLock(String managerId, String url);
	
	/**
	 * 根据淘宝ID检查读锁页面
	 * 
	 * @param managerId
	 * @return
	 */
	public long getCountByManagerId(String managerId);
}
