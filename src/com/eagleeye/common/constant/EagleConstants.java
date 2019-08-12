package com.eagleeye.common.constant;

import java.util.HashMap;
import java.util.Map;

import com.taobao.api.Constants;

public class EagleConstants extends Constants {
    public final static String TOPSESSIONKEY = "top_session";

    public final static String TOPPARAMETERS = "top_parameters";

    public final static String TOPMANAGERID = "top_manager_id";

    public final static String TOPSHOPID = "top_shop_id";

    public final static String TOPMENU = "menu_num";

    public final static String TOPSTAFFID = "top_staff_id";
    // OAUTH2.0 2013.5.31
    public final static String TOPCODE = "code";
    // 鹰眼
    //
    public final static String APPKEY = "12237851";

    public final static String APPSECRET = "6fe8d48b74cf76663dcd68b1f0321589";

    //	public final static String BASEURL = "http://whz002226.my3w.com/eagle/faces/index.xhtml";
    public final static String BASEURL = "http://39.98.254.172/eagle/faces/index.xhtml";

    public final static String SERVERURL = "http://gw.api.taobao.com/router/rest";

    public final static String OAUTHURL = "https://oauth.taobao.com/token";

    public final static String SECURITYTOKEN = "xncy4EQfgDbgTGPY4bO2/ig+fl4uhL4Zo6BHQoGmaUs=";

    public final static String SECURITYSERVERURL = "https://eco.taobao.com/router/rest";
    //沙箱
//	public final static String APPKEY = "1012237851";
//
//	public final static String APPSECRET = "sandboxb74cf76663dcd68b1f0321589";
//
//	public final static String BASEURL = "http://127.0.0.1:8080/eagle/faces/index.xhtml";
//
//	public final static String SERVERURL = "https://gw.api.tbsandbox.com/router/rest";
//
//	public final static String OAUTHURL="https://oauth.tbsandbox.com/authorize";

    public static String getBaseUrl() {
        return BASEURL;
    }

    // 刷刷宝
//	 public final static String APPKEY = "21624996";
//	
//	 public final static String APPSECRET =
//	 "aaf7d1705d627d3a61d6effed327cfc4";
//	
//	 // 回调地址
//	 public final static String BASEURL =
//	 "http://127.0.0.1:8080/eagle/faces/index.xhtml";

    public final static String DEFAULTCHARSET = Constants.CHARSET_UTF8;

    // 毫秒
    /**
     * 接口连接超时时间
     */
    public final static int CONNECT_TIMEOUT = 30000;

    /**
     * 接口读取超时时间
     */
    public final static int READ_TIMEOUT = 60000;

    public final static String TAOBAOESERVICEPREFIX = "cntaobao";

    public final static int DEFAULT_NUMBERSCALE = 2;

    public final static int DEFAULT_PERCENTSCALE = 4;

    public final static String DEFAULT_OWNER = "DIRECT-BUYER";

    public final static String PENDING_OWNER = "PENDING";

    public final static String NO_OWNER = "NOOWNER";

    public final static String NEED_CONFIG = "need_config";

    public final static String URI_PREFIX = "/eagle/";

    public final static String URI_PAGES_DICTIONARY = EagleConstants.URI_PREFIX + "pages/";

    public final static String URI_INDEX_PAGE = EagleConstants.URI_PAGES_DICTIONARY + "index.xhtml";

    public final static String SALARY_CALCULATE_BY_AMOUNT = "1";

    public final static String SALARY_CALCULATE_BY_NUM = "2";

    /**
     * 查询最大日期间隔
     */
    public final static long SELECT_DATE_GAP = 90;

    /**
     * 更新周期
     */
    public final static int UPDATE_GAP = 60;

    /**
     * 一次transaction的日期周期
     */
    public final static int ONE_TRANS_GAP = 3;

    /**
     * 数据导入时间间隔
     */
    public static final int UPDATE_TIME_END_GAP = 1;

    /**
     * 防错最大run年限
     */
    public final static int LIMITED_YEAR = 2020;

    /**
     * X+1天内业绩归属，交易归属使用
     */
    public final static int DEFAULT_SET_OWNER_PERIOD = 1;

    /**
     * 业务归属1，客户付款前X小时第一个与之沟通的客服(default)
     */
    public final static String BELONG_TYPE_PAY_FIRST_TALK = "1";

    /**
     * 业务归属2，客户付款前X小时最后一个与之沟通的客服
     */
    public final static String BELONG_TYPE_PAY_LAST_TALK = "2";

    /**
     * 业务归属3，客户交易创建前X小时第一个与之沟通的客服
     */
    public final static String BELONG_TYPE_CREATE_FIRST_TALK = "3";

    /**
     * 业务归属4，客户交易创建前X小时最后一个与之沟通的客服
     */
    public final static String BELONG_TYPE_CREATE_LAST_TALK = "4";

    /**
     * 业务归属5，客户交易创建前X小时与客户对话最多的客服
     */
    public final static String BELONG_TYPE_CREATE_MOST_TALK = "5";

    /**
     * 业务归属6，客户交易付款前X小时与客户对话最多的客服
     */
    public final static String BELONG_TYPE_PAY_MOST_TALK = "6";

    /**
     * 业务归属7，插旗
     */
    public final static String BELONG_TYPE_FLAG = "7";

    /**
     * 重跑三次的错误代码
     */
    public final static String[] RERUN_ERROR_CODES = {"620", "15", "520"};

    public final static String ORDERBY_DESC = " desc ";

    public final static String ORDERBY_ASC = " asc ";

    /**
     * 增量交易，每次交易归属循环交易个数
     */
    public final static int NUM_PER_ROUND = 50;

    public final static String MENU_NUM_FIRST = "eagle/pages/shop/";

    public final static String MENU_NUM_SECOND = "eagle/pages/staffDtl/";

    public final static String MENU_NUM_THIRD = "eagle/pages/staff/";

    public final static String MENU_NUM_FOURTH = "eagle/pages/rank/";

    public final static String MENU_NUM_FIFTH = "eagle/pages/detail/";

    public final static String MENU_NUM_SIXTH = "eagle/pages/traderate/";

    public final static String MENU_NUM_SEVENTH = "eagle/pages/salary/";

    public final static String MENU_NUM_EIGHT = "eagle/pages/setting/";

    // 查询昨天
    public final static String QUERY_TYPE_LAST_DAY = "1";

    // 查询上周
    public final static String QUERY_TYPE_LAST_WEEK = "2";

    // 查询上月
    public final static String QUERY_TYPE_LAST_MONTH = "3";

    // 付费code
    public final static String PAYMENT_CODE = "ts-15029";

    // 付费子code
    public final static String PAYMENT_ITEM_CODE = "ts-15029-1";

    public final static String GENERAL_PAYMENT_TEMPLATE = "通用模版";

    /**
     * 2012-8-14，v1.7，默认读写密码
     */
    public final static String DEFAULT_PSD = "";

    /**
     * 2012-8-15，v1.7,读锁
     */
    public final static String READ_LOCK = "top_read_lock";

    /**
     * 2012-8-15，v1.7,写锁
     */
    public final static String MODIFY_LOCK = "top_writ_lock";

    public final static String PARAMS_MANAGER = "visitor_nick";

    public final static String PARAMS_SUBSTAFF = "sub_taobao_user_nick";

    /**
     * 2013-5-26，v1.8,检测更新是否完成
     */
    public final static String HAS_UPDATE = "has_updated";

    public final static String AUTH_TOP = "top";

    public final static String AUTH_OAUTH = "oauth";

    public final static Map<String, String> OAUTH_PARAM_MAP = new HashMap();

    static {
        OAUTH_PARAM_MAP.put("taobao_user_nick", EagleConstants.PARAMS_MANAGER);
        OAUTH_PARAM_MAP.put("sub_taobao_user_nick", EagleConstants.PARAMS_SUBSTAFF);
        OAUTH_PARAM_MAP.put("access_token", EagleConstants.TOPSESSIONKEY);
    }

    public final static String CHART_YEAR = "2014";

    public final static String CHART_TITLE = "鹰眼";

    // flag color and value
    public final static Long FLAG_RED = 1L;
    public final static Long FLAG_YELLOW = 2L;
    public final static Long FLAG_GREEN = 3L;
    public final static Long FLAG_BLUE = 4L;
    public final static Long FLAG_PUPPLE = 5L;
}
