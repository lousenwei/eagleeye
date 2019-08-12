package com.eagleeye.user.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.bsh.IIncrementUpdateBsh;
import com.eagleeye.user.bsh.IManagerInfoBsh;
import com.eagleeye.user.bsh.ITotalUpdateBsh;
import com.eagleeye.user.bsh.IUserBsh;
import com.eagleeye.user.constant.UserConstant;
import com.eagleeye.user.eo.GroupMemberEO;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;
import com.eagleeye.user.eo.IncrementUpdateDetailEOId;
import com.eagleeye.user.eo.ManagerInfoEO;
import com.eagleeye.user.eo.TotalUpdateDetailEO;

@ManagedBean(name = "user")
@ViewScoped
public class UserController {

	private Logger log = Logger.getLogger(UserController.class);

	private IUserBsh userBsh;

	private IGroupMemberBsh groupMemberBsh;

	private IManagerInfoBsh managerInfoBsh;

	private IIncrementUpdateBsh incrementUpdateBsh;

	private ITotalUpdateBsh totalUpdateBsh;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAppKey() {
		return EagleConstants.APPKEY;
	}

	public String getBaseUrl() {
		return EagleConstants.BASEURL;
	}

	/**
	 * @return
	 */
	public boolean getHasUpdate() {

		if (SessionManager.getSessionByKey(EagleConstants.HAS_UPDATE) != null
				&& Boolean.FALSE.equals(SessionManager
						.getSessionByKey(EagleConstants.HAS_UPDATE))) {
			incrementUpdateBsh = (IIncrementUpdateBsh) EagleEyeServiceLocator
					.getBean("incrementUpdateBsh");
			IncrementUpdateDetailEO hasUpdate = incrementUpdateBsh
					.getIncrementUpdate((String) SessionManager
							.getSessionByKey(EagleConstants.TOPMANAGERID),
							DateUtil.getSimpleDate(DateUtil.getPreviousDay(
									new Date(),
									UserConstant.INCREMENT_UPDATE_GAP)));
			if (hasUpdate != null) {
				if (UserConstant.SUCCESS.equals(hasUpdate.getStatus())) {
					SessionManager.setSessionKeyValue(
							EagleConstants.HAS_UPDATE, true);
					return true;
				}
			} else {
				totalUpdateBsh = (ITotalUpdateBsh) EagleEyeServiceLocator
						.getBean("totalUpdateBsh");
				List<TotalUpdateDetailEO> hasTUpdate = totalUpdateBsh
						.getUnfinishedTotalUpdates((String) SessionManager
								.getSessionByKey(EagleConstants.TOPMANAGERID));
				if (hasTUpdate == null || hasTUpdate.isEmpty()) {
					SessionManager.setSessionKeyValue(
							EagleConstants.HAS_UPDATE, true);
					return true;
				}
			}
			SessionManager.setSessionKeyValue(EagleConstants.HAS_UPDATE, false);
			return false;
		}
		return true;
	}

	// 登录动作
	@Deprecated
	public void loginAction(ComponentSystemEvent e) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext ec = context.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) ec.getRequest();
			request.setCharacterEncoding(EagleConstants.CHARSET_UTF8);
			HttpServletResponse httpResponse = TaoBaoUtil.getHttpResponse();
			// 设置sessionkey
			String sessionKey = request
					.getParameter(EagleConstants.TOPSESSIONKEY);
			if (request.getParameter(EagleConstants.TOPSESSIONKEY) != null) {
				SessionManager.setSessionKeyValue(EagleConstants.TOPSESSIONKEY,
						sessionKey);
			}
			// 设置topparameters
			if (request.getParameter(EagleConstants.TOPPARAMETERS) != null) {
				String managerId = TaoBaoUtil.ParametersName(request
						.getParameter(EagleConstants.TOPPARAMETERS));
				if (managerId != null && !"".equals(managerId)) {
					SessionManager.setSessionKeyValue(
							EagleConstants.TOPMANAGERID, managerId);
					groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator
							.getBean("groupMemberBsh");
					GroupMemberEO member = groupMemberBsh
							.getFirstGroupMemberBlh(managerId);
					if (member != null) {
						SessionManager.setSessionKeyValue(
								EagleConstants.TOPSHOPID, member.getShopId());
						totalUpdateBsh = (ITotalUpdateBsh) EagleEyeServiceLocator
								.getBean("totalUpdateBsh");
						List<TotalUpdateDetailEO> unfinshed = totalUpdateBsh
								.getUnfinishedTotalUpdates(managerId);
						if (unfinshed != null && !unfinshed.isEmpty()) {
							for (TotalUpdateDetailEO temp : unfinshed) {
								temp.setSessionKey(sessionKey);
							}
							totalUpdateBsh.saveTotalUpdates(unfinshed);
						} else {
							// TODO:设置增量更新记录
							incrementUpdateBsh = (IIncrementUpdateBsh) EagleEyeServiceLocator
									.getBean("incrementUpdateBsh");
							Date updateTime = DateUtil.getSimpleDate(DateUtil
									.getPreviousDay(new Date(),
											UserConstant.INCREMENT_UPDATE_GAP));
							IncrementUpdateDetailEO todayUpdate = incrementUpdateBsh
									.getIncrementUpdate(managerId, updateTime);
							if (todayUpdate == null) {
								IncrementUpdateDetailEO incrementUpdate = new IncrementUpdateDetailEO();
								IncrementUpdateDetailEOId id = new IncrementUpdateDetailEOId();
								id.setManagerId(managerId);
								id.setUpdateTime(updateTime);
								incrementUpdate.setId(id);
								incrementUpdate
										.setSessionKey(request
												.getParameter(EagleConstants.TOPSESSIONKEY));
								incrementUpdate.setShopId(member.getShopId());
								incrementUpdate.setStatus(UserConstant.INIT);
								incrementUpdateBsh
										.saveIncrementUpdate(incrementUpdate);
							} else {
								todayUpdate
										.setSessionKey(request
												.getParameter(EagleConstants.TOPSESSIONKEY));
								incrementUpdateBsh
										.saveIncrementUpdate(todayUpdate);
							}
						}
					} else {
						managerInfoBsh = (IManagerInfoBsh) EagleEyeServiceLocator
								.getBean("managerInfoBsh");
						// check manager info,如果也没有数据，则表示为新购买用户，进入初始化页面
						List<ManagerInfoEO> managers = managerInfoBsh
								.getManagerInfoEO(managerId);
						if (managers == null) {
							// 首先插入manager信息
							String shopId = TaoBaoUtil.generateShopId();
							SessionManager.setSessionKeyValue(
									EagleConstants.TOPSHOPID, shopId);
							managerInfoBsh.saveManagerInfoEO(shopId, managerId,
									UserConstant.IS_VALID);
						} else {
							ManagerInfoEO manager = managers.get(0);
							SessionManager.setSessionKeyValue(
									EagleConstants.TOPSHOPID,
									manager.getShopId());
						}
						SessionManager.setSessionKeyValue(
								EagleConstants.NEED_CONFIG, true);
						// 导航至初始化页面
						httpResponse
								.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
										+ "FirstInit.xhtml");
					}
				}
			}
			// 导航至首页面
			httpResponse.sendRedirect(EagleConstants.URI_INDEX_PAGE);
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	// 登出动作
	public void logoutAction() {
		try {
			// 注销session
			SessionManager.invalidateSession();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public String getManagerId() {
		String managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
		if (managerId != null) {
			return managerId;
		}
		return "";
	}

	public String getCurrentUserId() {
		String userId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPSTAFFID);
		if (userId == null) {
			userId = (String) SessionManager
					.getSessionByKey(EagleConstants.TOPMANAGERID);
		}
		if (userId != null) {
			return userId;
		}
		return "";
	}

	public String getMenuContext() {
		String menu = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMENU);
		if (menu != null) {
			return menu;
		}
		return "0";
	}

	// load用户信息
	public void userLoad() {
		userBsh.loadUser(userId);
	}

	public IUserBsh getUserBsh() {
		return userBsh;
	}

	public void setUserBsh(IUserBsh userBsh) {
		this.userBsh = userBsh;
	}

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IIncrementUpdateBsh getIncrementUpdateBsh() {
		return incrementUpdateBsh;
	}

	public void setIncrementUpdateBsh(IIncrementUpdateBsh incrementUpdateBsh) {
		this.incrementUpdateBsh = incrementUpdateBsh;
	}

}
