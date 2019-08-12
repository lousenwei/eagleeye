package com.eagleeye.user.bsh;

import java.util.List;

import com.eagleeye.user.mo.StaffMO;

public interface IUserBsh {
	public List<String> loadUser(String userId);

	public void findAllUser();
	
	public List<StaffMO> loadUserMap(String userId);
}
