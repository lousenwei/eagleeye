package com.eagleeye.common.taobao;

import com.eagleeye.common.constant.EagleConstants;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.security.SecurityClient;

import java.security.Security;

public class TaoBaoClientUtil {

    private static String SERVERURL = EagleConstants.SERVERURL;

    public static TaobaoClient getDefaultTaoBaoClient(String format) {

        // TaobaoClient client = new DefaultTaobaoClient(SERVERURL,
        // EagleConstants.APPKEY, EagleConstants.APPSECRET, format,
        // EagleConstants.CONNECT_TIMEOUT, EagleConstants.READ_TIMEOUT,
        // EagleConstants.SIGN_METHOD_MD5);
        TaobaoClient client = new DefaultTaobaoClient(SERVERURL,
                EagleConstants.APPKEY, EagleConstants.APPSECRET, format);
        return client;
    }

    public static SecurityClient getSecurityTaobaoClient() {
        SecurityClient client = new SecurityClient(new DefaultTaobaoClient(EagleConstants.SECURITYSERVERURL, EagleConstants.APPKEY, EagleConstants.APPSECRET), EagleConstants.SECURITYTOKEN);
        return client;
    }

    public static String getSERVERURL() {
        return SERVERURL;
    }

    public static void setSERVERURL(String sERVERURL) {
        SERVERURL = sERVERURL;
    }

}
