package com.eagleeye.user.bsh.impl;

import java.util.List;

import com.eagleeye.user.blh.IManagerInfoBlh;
import com.eagleeye.user.bsh.IManagerInfoBsh;
import com.eagleeye.user.eo.ManagerInfoEO;

public class ManagerInfoBshImpl implements IManagerInfoBsh {
	IManagerInfoBlh managerInfoBlh;

	@Override
	public void saveManagerInfoEO(String shopId, String managerId, String status) {
		// TODO Auto-generated method stub
		managerInfoBlh.saveManagerInfoEO(shopId, managerId, status);
	}

	@Override
	public List<ManagerInfoEO> getManagerInfoEO(String managerId) {
		// TODO Auto-generated method stub
		return managerInfoBlh.getManagerInfoEO(managerId);
	}

	@Override
	public Boolean checkHasValidManager(String managerId) {
		// TODO Auto-generated method stub
		return managerInfoBlh.checkHasValidManager(managerId);
	}

	public IManagerInfoBlh getManagerInfoBlh() {
		return managerInfoBlh;
	}

	public void setManagerInfoBlh(IManagerInfoBlh managerInfoBlh) {
		this.managerInfoBlh = managerInfoBlh;
	}

	@Override
	public void saveManagerInfoEO(String shopId, String managerId,
			String status, String readPsd, String modifyPsd) {
		// TODO Auto-generated method stub
		managerInfoBlh.saveManagerInfoEO(shopId, managerId, status, readPsd,
				modifyPsd);
	}

	@Override
	public void updateManagerInfoEO(ManagerInfoEO managerInfoEo) {
		// TODO Auto-generated method stub
		managerInfoBlh.updateManagerInfoEO(managerInfoEo);
	}

	@Override
	public void saveReadPsd(String managerId, String readPsd) {
		// TODO Auto-generated method stub
		managerInfoBlh.saveReadPsd(managerId, readPsd);
	}

	@Override
	public void saveModifyPsd(String managerId, String modifyPsd) {
		// TODO Auto-generated method stub
		managerInfoBlh.saveModifyPsd(managerId, modifyPsd);
	}

	@Override
	public ManagerInfoEO getDistinctManagerInfoEO(String managerId) {
		return managerInfoBlh.getDistinctManagerInfoEO(managerId);
	}

}
