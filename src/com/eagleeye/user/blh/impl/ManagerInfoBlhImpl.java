package com.eagleeye.user.blh.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.eservice.blh.impl.AvgwaitTimeBlhImpl;
import com.eagleeye.user.blh.IManagerInfoBlh;
import com.eagleeye.user.dao.ManagerInfoDAO;
import com.eagleeye.user.eo.ManagerInfoEO;

public class ManagerInfoBlhImpl implements IManagerInfoBlh {
	private Logger log = Logger.getLogger(AvgwaitTimeBlhImpl.class);
	ManagerInfoDAO managerInfoDao;

	@Override
	public void saveManagerInfoEO(String shopId, String managerId, String status) {
		// TODO Auto-generated method stub
		ManagerInfoEO newManager = new ManagerInfoEO();
		newManager.setManagerId(managerId);
		newManager.setShopId(shopId);
		newManager.setStatus(status);
		// 2012-8-14,v1.7,填充默认密码
		newManager.setReadPsd(EagleConstants.DEFAULT_PSD);
		newManager.setModifyPsd(EagleConstants.DEFAULT_PSD);
		managerInfoDao.save(newManager);
	}

	@Override
	public List<ManagerInfoEO> getManagerInfoEO(String managerId) {
		// TODO Auto-generated method stub
		return managerInfoDao.getManagerInfoEO(managerId);
	}

	public void saveReadPsd(String managerId, String readPsd) {
		ManagerInfoEO manager = managerInfoDao
				.getDistinctManagerInfoEO(managerId);
		if (manager != null) {
			manager.setReadPsd(readPsd);
			managerInfoDao.updateData(manager);
		}
	}

	public Boolean checkHasValidManager(String managerId) {
		return managerInfoDao.checkHasValidManager(managerId);
	}

	public ManagerInfoDAO getManagerInfoDao() {
		return managerInfoDao;
	}

	public void setManagerInfoDao(ManagerInfoDAO managerInfoDao) {
		this.managerInfoDao = managerInfoDao;
	}

	@Override
	public void saveManagerInfoEO(String shopId, String managerId,
			String status, String readPsd, String modifyPsd) {
		// TODO Auto-generated method stub
		ManagerInfoEO newManager = new ManagerInfoEO();
		newManager.setManagerId(managerId);
		newManager.setShopId(shopId);
		newManager.setStatus(status);
		// 2012-8-14,v1.7,填充默认密码
		newManager.setReadPsd(readPsd);
		newManager.setModifyPsd(modifyPsd);
		managerInfoDao.save(newManager);
	}

	@Override
	public void updateManagerInfoEO(ManagerInfoEO managerInfoEo) {
		// TODO Auto-generated method stub
		managerInfoDao.updateData(managerInfoEo);
	}

	@Override
	public void saveModifyPsd(String managerId, String modifyPsd) {
		// TODO Auto-generated method stub
		ManagerInfoEO manager = managerInfoDao
				.getDistinctManagerInfoEO(managerId);
		if (manager != null) {
			manager.setModifyPsd(modifyPsd);
			managerInfoDao.updateData(manager);
		}
	}

	@Override
	public ManagerInfoEO getDistinctManagerInfoEO(String managerId) {
		return managerInfoDao.getDistinctManagerInfoEO(managerId);
	}
}
