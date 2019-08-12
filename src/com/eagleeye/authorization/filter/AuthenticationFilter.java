package com.eagleeye.authorization.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.eagleeye.authorization.constant.AuthorizationConstant;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.bsh.IIncrementUpdateBsh;
import com.eagleeye.user.bsh.IManagerInfoBsh;
import com.eagleeye.user.bsh.IReadLockBsh;
import com.eagleeye.user.bsh.ITotalUpdateBsh;
import com.eagleeye.user.constant.UserConstant;
import com.eagleeye.user.eo.GroupMemberEO;
import com.eagleeye.user.eo.IncrementUpdateDetailEO;
import com.eagleeye.user.eo.IncrementUpdateDetailEOId;
import com.eagleeye.user.eo.ManagerInfoEO;
import com.eagleeye.user.eo.TotalUpdateDetailEO;
import com.taobao.api.internal.util.WebUtils;

public class AuthenticationFilter implements Filter {

    private Set<String> uncheckAuthenticationResource = new HashSet<String>();

//	private String authenticationFail = AuthorizationConstant.PRODUCT_CONTAINER_URL
//			+ "?appkey="
//			+ AuthorizationConstant.APPKEY
//			+ "&encode="
//			+ EagleConstants.CHARSET_UTF8;

    private Logger log = Logger.getLogger(AuthenticationFilter.class);

    private IGroupMemberBsh groupMemberBsh;

    private IManagerInfoBsh managerInfoBsh;

    private IIncrementUpdateBsh incrementUpdateBsh;

    private ITotalUpdateBsh totalUpdateBsh;

    private IReadLockBsh readLockBsh;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpRequest.setCharacterEncoding(EagleConstants.CHARSET_UTF8);
        String requestResourceURI = httpRequest.getRequestURI();
        log.info("Request =>" + requestResourceURI);
        if (!requestResourceURI.contains(AuthorizationConstant.JSF_FILES)
                && !uncheckAuthenticationResource.contains(requestResourceURI
                .substring(requestResourceURI.indexOf("/", 1)))) {
            log.info("Doing session check!");
            if (httpRequest.getSession() == null
                    || httpRequest.getSession().getAttribute(
                    EagleConstants.TOPSESSIONKEY) == null) {
                httpResponse.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                        + "error.xhtml");
                return;
            }
        } else if (requestResourceURI.contains(AuthorizationConstant.HOME_PAGE)) {
            if (EagleConstants.CHARSET_UTF8.equalsIgnoreCase(httpRequest
                    .getParameter("encode"))) {
                httpResponse.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                        + "errorEncode.xhtml");
                return;
            } else {

                String responseString = "";
                Map<String, String> returnParams = new HashMap();
                String type = EagleConstants.AUTH_TOP;
                // TOP 2.0
                if (request.getParameter(EagleConstants.TOPPARAMETERS) != null) {
                    responseString = String.valueOf(request
                            .getParameter(EagleConstants.TOPPARAMETERS));
                }// Oauth 2.0
                else if (httpRequest
                        .getParameter(AuthorizationConstant.TOPCODE) != null) {
                    responseString = this
                            .getOauthResponseString(String.valueOf(httpRequest
                                    .getParameter(AuthorizationConstant.TOPCODE)));
                    type = EagleConstants.AUTH_OAUTH;
                }
                if (responseString == null || responseString.isEmpty()) {
                    httpResponse
                            .sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                                    + "error.xhtml");
                    return;
                }
                this.parseResponseString(responseString, returnParams, type);
                this.autoLoginAction(httpRequest, httpResponse, returnParams);
            }
            // 如果访问后台管理页面，必须等于管理员帐号，否则跳转至首页
        }
        if (requestResourceURI.contains(AuthorizationConstant.MANAGE_PAGE)) {
            Object managerID = httpRequest.getSession().getAttribute(
                    AuthorizationConstant.TOPMANAGERID);
            if (managerID == null
                    || (!AuthorizationConstant.ADMIN_ACCOUNT
                    .equalsIgnoreCase(String.valueOf(managerID)) && !AuthorizationConstant.ADMIN_ACCOUNT2
                    .equalsIgnoreCase(String.valueOf(managerID)))) {
                httpResponse.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                        + "errorEncode.xhtml");
                return;
            }
        }
        if (!requestResourceURI.contains(AuthorizationConstant.JSF_FILES)
                && (requestResourceURI.contains(EagleConstants.MENU_NUM_FIRST)
                || requestResourceURI
                .contains(EagleConstants.MENU_NUM_SECOND)
                || requestResourceURI
                .contains(EagleConstants.MENU_NUM_THIRD)
                || requestResourceURI
                .contains(EagleConstants.MENU_NUM_FOURTH)
                || requestResourceURI
                .contains(EagleConstants.MENU_NUM_FIFTH)
                || requestResourceURI
                .contains(EagleConstants.MENU_NUM_SIXTH)
                || requestResourceURI
                .contains(EagleConstants.MENU_NUM_SEVENTH) || requestResourceURI
                .contains(EagleConstants.MENU_NUM_EIGHT))) {
            // 解读锁
            // v 1.7 2012.8.23，begin
            this.checkReadLock(httpRequest, httpResponse);
            // v 1.7 2012.8.23，end
            this.setMenuNum(requestResourceURI, httpRequest);
        }
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        initialUncheckList(config);
    }

    /**
     * 返回Oauth 2.0响应字符串
     *
     * @param code
     * @return
     */
    private String getOauthResponseString(String code) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("grant_type", "authorization_code");
        param.put("code", code);
        param.put("client_id", EagleConstants.APPKEY);
        param.put("client_secret", EagleConstants.APPSECRET);
        param.put("redirect_uri", EagleConstants.BASEURL);
        param.put("scope", "item");
        param.put("view", "web");
        try {
            String responseJson = WebUtils.doPost(
                    EagleConstants.OAUTHURL, param, 3000, 3000);
            if (responseJson != null && !responseJson.isEmpty()) {
                return URLDecoder.decode(responseJson, "UTF-8");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     * 解析返回字符串
     *
     * @param responseString
     * @param params
     * @param type
     */
    private void parseResponseString(String responseString,
                                     Map<String, String> params, String type) {
        if (type.equals(EagleConstants.AUTH_TOP)) {
            TaoBaoUtil.convertBase64StringtoMap(params, responseString);
        } else if (type.equals(EagleConstants.AUTH_OAUTH)) {
            TaoBaoUtil.convertStringtoMapOAuth(params, responseString);
        }

    }

    /**
     * 检查读锁，v1.7，2012.8.24
     *
     * @param request
     * @param response
     */
    private void checkReadLock(HttpServletRequest request,
                               HttpServletResponse response) throws IOException, ServletException {
        Object readLock = request.getSession().getAttribute(
                EagleConstants.READ_LOCK);
        if (readLock == null || ((Boolean) readLock) == true) {
            readLockBsh = (IReadLockBsh) EagleEyeServiceLocator
                    .getBean("readLockBsh");
            String managerId = String.valueOf(request.getSession()
                    .getAttribute(EagleConstants.TOPMANAGERID));
            // 没有managerId，代表session过期
            if (managerId == null) {
                response.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                        + "error.xhtml");
                return;
            }
            // 获取对应url是否需要读锁
            String[] urls = request.getRequestURI().split("/");
            long count = readLockBsh.checkReadLock(managerId,
                    urls[urls.length - 1]);
            // 大于0，代表需要读锁
            if (count > 0) {
                response.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                        + "ReadModifyUnlock.xhtml");
                return;
            } else if (count < 0) {
                response.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                        + "error.xhtml");
                return;
            }
        }
    }

    private void initialUncheckList(FilterConfig config) {
        String uncheckList = config.getInitParameter("UNCHECK_RESOURCE_LIST");
        for (String uncheckResource : uncheckList.split(";")) {
            uncheckResource = StringUtils.trimWhitespace(uncheckResource);
            if (uncheckResource.length() > 0) {
                uncheckAuthenticationResource.add(uncheckResource);
            }
        }
    }

    /**
     * 返回自动登录参数
     *
     * @param in
     * @param params
     * @return
     */
    public Map getLoginParams(String in, Map<String, Object> params) {
        return params;
    }

    private void autoLoginAction(HttpServletRequest request,
                                 HttpServletResponse response, Map<String, String> params) {
        try {
            // 设置sessionkey
            String sessionKey = params.get(EagleConstants.TOPSESSIONKEY);
            HttpSession session = request.getSession();
            if (session != null && sessionKey != null) {
                session.setAttribute(EagleConstants.TOPSESSIONKEY, sessionKey);
                // 2012-8-15,v1.7，初始化读写锁为锁定，begin
                if (session.getAttribute(EagleConstants.READ_LOCK) == null) {
                    session.setAttribute(EagleConstants.READ_LOCK, true);
                }
                if (session.getAttribute(EagleConstants.MODIFY_LOCK) == null) {
                    session.setAttribute(EagleConstants.MODIFY_LOCK, true);
                }
                // end
                session.setAttribute(EagleConstants.HAS_UPDATE, false);
            } else if (session == null) {
                response.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                        + "error.xhtml");
                return;
            }
            // 设置topparameters
            if (params != null) {
                String managerId = params.get(EagleConstants.PARAMS_MANAGER);
                String staffId = params.get(EagleConstants.PARAMS_SUBSTAFF);
                if (managerId != null && !"".equals(managerId)) {
                    session.setAttribute(EagleConstants.TOPMANAGERID, managerId);
                    if (staffId != null && !staffId.isEmpty()) {
                        session.setAttribute(EagleConstants.TOPSTAFFID, staffId);
                    }
                    groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator
                            .getBean("groupMemberBsh");
                    GroupMemberEO member = groupMemberBsh
                            .getFirstGroupMemberBlh(managerId);
                    if (member != null) {
                        session.setAttribute(EagleConstants.TOPSHOPID,
                                member.getShopId());
                        totalUpdateBsh = (ITotalUpdateBsh) EagleEyeServiceLocator
                                .getBean("totalUpdateBsh");
                        List<TotalUpdateDetailEO> unfinshed = totalUpdateBsh
                                .getUnfinishedTotalUpdates(managerId);
                        if (unfinshed != null
                                && !unfinshed.isEmpty()
                                && !unfinshed.get(0).getSessionKey()
                                .equalsIgnoreCase(sessionKey)) {
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
                                if (params.get(EagleConstants.TOPSESSIONKEY) != null) {
                                    IncrementUpdateDetailEO incrementUpdate = new IncrementUpdateDetailEO();
                                    IncrementUpdateDetailEOId id = new IncrementUpdateDetailEOId();
                                    id.setManagerId(managerId);
                                    id.setUpdateTime(updateTime);
                                    incrementUpdate.setId(id);
                                    incrementUpdate.setSessionKey(params
                                            .get(EagleConstants.TOPSESSIONKEY));
                                    incrementUpdate.setShopId(member
                                            .getShopId());
                                    incrementUpdate
                                            .setStatus(UserConstant.INIT);
                                    incrementUpdateBsh
                                            .saveIncrementUpdate(incrementUpdate);
                                }
                            } else {
                                if (!todayUpdate
                                        .getSessionKey()
                                        .equalsIgnoreCase(
                                                params.get(EagleConstants.TOPSESSIONKEY))) {
                                    if (params
                                            .get(EagleConstants.TOPSESSIONKEY) != null) {
                                        todayUpdate
                                                .setSessionKey(params
                                                        .get(EagleConstants.TOPSESSIONKEY));
                                        incrementUpdateBsh
                                                .saveIncrementUpdate(todayUpdate);
                                    }
                                    if (UserConstant.SUCCESS.equals(todayUpdate
                                            .getStatus())) {
                                        session.setAttribute(
                                                EagleConstants.HAS_UPDATE, true);
                                    } else {
                                        session.setAttribute(
                                                EagleConstants.HAS_UPDATE,
                                                false);
                                    }
                                }
                            }
                            // v1.7，2012.8.24，查看是否设置读锁页面，否则设置为已解锁,begin
                            readLockBsh = (IReadLockBsh) EagleEyeServiceLocator
                                    .getBean("readLockBsh");
                            long count = readLockBsh
                                    .getCountByManagerId(managerId);
                            if (count == 0) {
                                session.setAttribute(EagleConstants.READ_LOCK,
                                        false);
                            }
                            // 密码为空的，则解锁修改
                            managerInfoBsh = (IManagerInfoBsh) EagleEyeServiceLocator
                                    .getBean("managerInfoBsh");
                            List<ManagerInfoEO> managers = managerInfoBsh
                                    .getManagerInfoEO(managerId);
                            if (managers != null && !managers.isEmpty()) {
                                ManagerInfoEO manager = managers.get(0);
                                if (manager.getModifyPsd() == null
                                        || manager.getModifyPsd().isEmpty()) {
                                    session.setAttribute(
                                            EagleConstants.MODIFY_LOCK, false);
                                }
                                if (manager.getReadPsd() == null
                                        || manager.getReadPsd().isEmpty()) {
                                    session.setAttribute(
                                            EagleConstants.READ_LOCK, false);
                                }
                            }
                            // end
                        }
                    } else {
                        // 2013-5-26,v1.8，初始化读写锁为非锁定，begin
                        session.setAttribute(EagleConstants.READ_LOCK, false);
                        session.setAttribute(EagleConstants.MODIFY_LOCK, false);
                        // end
                        managerInfoBsh = (IManagerInfoBsh) EagleEyeServiceLocator
                                .getBean("managerInfoBsh");
                        // check manager info,如果也没有数据，则表示为新购买用户，进入初始化页面
                        List<ManagerInfoEO> managers = managerInfoBsh
                                .getManagerInfoEO(managerId);
                        if (managers == null || managers.isEmpty()) {
                            // 首先插入manager信息
                            String shopId = TaoBaoUtil.generateShopId();
                            session.setAttribute(EagleConstants.TOPSHOPID,
                                    shopId);
                            managerInfoBsh.saveManagerInfoEO(shopId, managerId,
                                    UserConstant.IS_VALID);
                        } else {
                            ManagerInfoEO manager = managers.get(0);
                            session.setAttribute(EagleConstants.TOPSHOPID,
                                    manager.getShopId());
                        }
                        session.setAttribute(EagleConstants.NEED_CONFIG, true);
                        // 导航至初始化页面
                        response.sendRedirect(EagleConstants.URI_PAGES_DICTIONARY
                                + "FirstInit.xhtml");
                        return;
                    }
                }
            }
            // 导航至首页面
            response.sendRedirect(EagleConstants.URI_INDEX_PAGE);
            return;
        } catch (Exception ex) {
            log.error(request.getSession().getAttribute(
                    EagleConstants.TOPMANAGERID)
                    + " "
                    + request.getSession().getAttribute(
                    EagleConstants.TOPPARAMETERS)
                    + " "
                    + request.getRequestURI() + "\n" + ex);
        }
    }

    /**
     * 根据url获取menunum
     *
     * @param url
     */
    public void setMenuNum(String url, HttpServletRequest httpRequest) {
        if (url.contains(EagleConstants.MENU_NUM_FIRST)) {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 0);
        } else if (url.contains(EagleConstants.MENU_NUM_SECOND)) {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 1);
        } else if (url.contains(EagleConstants.MENU_NUM_THIRD)) {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 2);
        } else if (url.contains(EagleConstants.MENU_NUM_FOURTH)) {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 3);
        } else if (url.contains(EagleConstants.MENU_NUM_FIFTH)) {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 4);
        } else if (url.contains(EagleConstants.MENU_NUM_SIXTH)) {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 5);
        } else if (url.contains(EagleConstants.MENU_NUM_SEVENTH)) {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 6);
        } else if (url.contains(EagleConstants.MENU_NUM_EIGHT)) {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 7);
        } else {
            httpRequest.getSession().setAttribute(EagleConstants.TOPMENU, 0);
        }
    }
}
