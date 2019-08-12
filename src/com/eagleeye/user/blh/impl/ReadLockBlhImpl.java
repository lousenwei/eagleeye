/**
 * 
 */
package com.eagleeye.user.blh.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.user.blh.IReadLockBlh;
import com.eagleeye.user.dao.ReadLockDAO;

/**
 * @author wilson
 * 
 */
public class ReadLockBlhImpl implements IReadLockBlh {
	private Logger log = Logger.getLogger(this.getClass());
	ReadLockDAO readLockDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eagleeye.user.blh.IReadLockBlh#saveReadLocks(java.util.List)
	 */
	@Override
	public void saveReadLocks(List readLocks) {
		// TODO Auto-generated method stub
		readLockDao.saveAllObject(readLocks);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eagleeye.user.blh.IReadLockBlh#getReadLocks(java.lang.String)
	 */
	@Override
	public List getReadLocks(String managerId) {
		// TODO Auto-generated method stub
		String sql = "from ReadLockMappingEO e where e.id.managerId=?";
		return readLockDao.getListData(sql, new Object[] { managerId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eagleeye.user.blh.IReadLockBlh#deleteReadLocks(java.lang.String)
	 */
	@Override
	public void deleteReadLocks(String managerId) {
		// TODO Auto-generated method stub
		String sql = "delete from read_lock_mapping where manager_id=?";
		try {
			readLockDao.deleteByJDBCsqL(sql, new Object[] { managerId });
		} catch (Exception e) {
			log.error(managerId + ":删除读锁数据失败\n" + e);
		}
	}

	/**
	 * 检查是否存在对应需要读锁的对象
	 * 
	 * @param managerId
	 * @param url
	 * @return
	 */
	public long checkReadLock(String managerId, String url) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder(
				"from ReadLockMappingEO e where e.id.managerId=? ");
		params.add(managerId);
		if (url != null && !url.isEmpty()) {
			sql.append("and e.id.readLockUrl=?");
			params.add(url);
		}else{
			return 0;
		}
		try {
			return readLockDao.getCount(sql.toString(), params.toArray());
		} catch (Exception e) {
			log.error(managerId + "\t" + url + ":检查读锁数据失败\n" + e);
			return -1;
		}
	}
	
	/**
	 * 根据淘宝ID检查读锁页面
	 * 
	 * @param managerId
	 * @return
	 */
	public long getCountByManagerId(String managerId) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder(
				"from ReadLockMappingEO e where e.id.managerId=? ");
		params.add(managerId);
		try {
			return readLockDao.getCount(sql.toString(), params.toArray());
		} catch (Exception e) {
			log.error(managerId + ":检查读锁数据失败\n" + e);
			return -1;
		}
	}

	public ReadLockDAO getReadLockDao() {
		return readLockDao;
	}

	public void setReadLockDao(ReadLockDAO readLockDao) {
		this.readLockDao = readLockDao;
	}

}
