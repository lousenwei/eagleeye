package com.eagleeye.user.bsh.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.user.bsh.IUserBsh;
import com.eagleeye.user.mo.StaffMO;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.SubUserInfo;
import com.taobao.api.request.SellercenterSubusersGetRequest;
import com.taobao.api.response.SellercenterSubusersGetResponse;

public class UserBshImpl implements IUserBsh {

	private Logger log = Logger.getLogger(UserBshImpl.class);

	@Override
	public List<String> loadUser(String userId) {
		// TODO Auto-generated method stub
		String sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_XML);
		SellercenterSubusersGetRequest req = new SellercenterSubusersGetRequest();
		req.setNick(userId);
		List<String> result = new ArrayList();
		result.add(userId);
		try {
			SellercenterSubusersGetResponse response = client.execute(req, sessionKey);
			if (response.getSubusers() != null && !response.getSubusers().isEmpty()) {
				for (SubUserInfo temp : response.getSubusers()) {
					result.add(temp.getNick());
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	@Override
	public List<StaffMO> loadUserMap(String userId) {
		// TODO Auto-generated method stub
		String sessionKey = (String) SessionManager.getSessionByKey(EagleConstants.TOPSESSIONKEY);
		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_XML);
		SellercenterSubusersGetRequest req = new SellercenterSubusersGetRequest();
		req.setNick(userId);
		List<StaffMO> result = new ArrayList();
		StaffMO manager = new StaffMO();
		manager.setStaffId(userId);
		result.add(manager);
		try {
			SellercenterSubusersGetResponse response = client.execute(req, sessionKey);
			if (response.getSubusers() != null && !response.getSubusers().isEmpty()) {
				for (SubUserInfo temp : response.getSubusers()) {
					StaffMO tempMo=new StaffMO();
					tempMo.setStaffId(temp.getNick());
					result.add(tempMo);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	public UserBshImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void findAllUser() {
		// TODO Auto-generated method stub

	}
}
