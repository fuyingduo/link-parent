package com.monitor.params.login;

import com.monitor.exception.HandleException;
import org.springframework.util.StringUtils;

/**
 * created by fuyd on 2019-10-08
 */
public class SmsLoginUser implements ILoginUser {

    // 登陆用户名（手机号）
    private String username;
    // 短信验证码
    private String smsCode;
    // 干扰码
    private String code;

    public SmsLoginUser(String username, String smsCode, String code) {
        this.username = username;
        this.smsCode = smsCode;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void check() {
        // 用户名为空
        if (StringUtils.isEmpty(username)) {
            throw new HandleException(11001);
        }
        // 验证码为空
        if (StringUtils.isEmpty(smsCode)) {
            throw new HandleException(11002);
        }
        // 用户名长度错误
        if (11 != username.length()) {
            throw new HandleException(11003);
        }
        // 验证码为空
        if (StringUtils.isEmpty(code)) {
            throw new HandleException(11004);
        }
    }
}
