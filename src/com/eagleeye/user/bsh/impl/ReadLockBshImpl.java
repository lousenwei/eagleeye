/**
 * 
 */
package com.eagleeye.user.bsh.impl;

import java.util.List;

import com.eagleeye.user.blh.IReadLockBlh;
import com.eagleeye.user.bsh.IReadLockBsh;

/**
 * @author wilson
 * 
 */
public class ReadLockBshImpl implements IReadLockBsh {
	IReadLockBlh readLockBlh;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eagleeye.user.bsh.IReadLockBsh#saveReadLocks(java.util.List)
	 */
	@Override
	public void saveReadLocks(List readLocks) {
		// TODO Auto-generated method stub
		readLockBlh.saveReadLocks(readLocks);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eagleeye.user.bsh.IReadLockBsh#getReadLocks(java.lang.String)
	 */
	@Override
	public List getReadLocks(String managerId) {
		// TODO Auto-generated method stub
		return readLockBlh.getReadLocks(managerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eagleeye.user.bsh.IReadLockBsh#deleteReadLocks(java.lang.String)
	 */
	@Override
	public void deleteReadLocks(String managerId) {
		// TODO Auto-generated method stub
		readLockBlh.deleteReadLocks(managerId);
	}

	public IReadLockBlh getReadLockBlh() {
		return readLockBlh;
	}

	public void setReadLockBlh(IReadLockBlh readLockBlh) {
		this.readLockBlh = readLockBlh;
	}

	@Override
	public long checkReadLock(String managerId, String url) {
		// TODO Auto-generated method stub
		return readLockBlh.checkReadLock(managerId, url);
	}

	@Override
	public long getCountByManagerId(String managerId) {
		// TODO Auto-generated method stub
		return readLockBlh.getCountByManagerId(managerId);
	}

}
