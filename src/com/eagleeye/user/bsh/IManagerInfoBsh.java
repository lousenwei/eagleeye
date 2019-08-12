package com.eagleeye.user.bsh;

import java.util.List;

import com.eagleeye.user.eo.ManagerInfoEO;

public interface IManagerInfoBsh {

	public void saveManagerInfoEO(String shopId, String managerId, String status);

	public List<ManagerInfoEO> getManagerInfoEO(String managerId);

	public Boolean checkHasValidManager(String managerId);

	// 2012-8-14,v1.7,增加密码设置新方法，begin
	/**
	 * 保存店主信息
	 * 
	 * @param shopId
	 * @param managerId
	 * @param status
	 * @param readPsd
	 * @param modifyPsd
	 */
	public void saveManagerInfoEO(String shopId, String managerId,
			String status, String readPsd, String modifyPsd);

	/**
	 * 更新店主信息
	 * 
	 * @param managerInfoEo
	 */
	public void updateManagerInfoEO(ManagerInfoEO managerInfoEo);

	/**
	 * 保存查询密码
	 * 
	 * @param managerId
	 * @param readPsd
	 */
	public void saveReadPsd(String managerId, String readPsd);

	/**
	 * 保存修改密码
	 * 
	 * @param managerId
	 * @param writePsd
	 */
	public void saveModifyPsd(String managerId, String modifyPsd);

	/**
	 * 得到唯一店主
	 * 
	 * @param managerId
	 * @return
	 */
	public ManagerInfoEO getDistinctManagerInfoEO(String managerId);
	// 2012-8-14,v1.7,增加密码设置新方法，end
}
