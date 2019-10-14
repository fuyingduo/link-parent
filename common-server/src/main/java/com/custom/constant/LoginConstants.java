package com.custom.constant;

/**
 * created by fuyd on 2019-06-16
 */
public class LoginConstants {

    /**
     * zuulfilter 拦截器token (用户登录)
     */
    public static final String REQUEST_HEADER_KEY = "login_token";

    /**
     * 主题
     * {@link com.custom.util.JwtUtil}
     */
    public static final String SUBJECT = "user info";

    /**
     * token key
     * {@link com.custom.util.JwtUtil}
     */
    public static final String USER_LOGIN_TOKEN = "user-login-token";

    /**
     * 密钥
     * {@link com.custom.util.JwtUtil}
     */
    public static final String SECRTE = "WH#$%(!)(#*!()!KL<55$6><MQLMNQNQJQK EiLCJuYW1lIjoif>?N<:{LWPW";

    /**
     * token key
     * {@link com.custom.util.JwtUtil}
     */
    public static final String USER_INFO_KEY = "token";

    /**
     * 用户密码短信 (redis)
     */
    public static final String USER_PASS_CODE = "user_pass_code";
}
