package com.custom.params.login;

import com.custom.exception.HandleException;
import org.springframework.util.StringUtils;

/**
 * created by fuyd on 2019-10-08
 */
public class PasswordLoginUser implements ILoginUser {

    private String username;
    private String password;
    private String code;

    public PasswordLoginUser(String username, String password, String code) {
        this.username = username;
        this.password = password;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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
        // 用户名长度错误
        if (11 != username.length()) {
            throw new HandleException(11003);
        }
        // 密码为空
        if (StringUtils.isEmpty(password)) {
            throw new HandleException(11005);
        }
        // 验证码为空
        if (StringUtils.isEmpty(code)) {
            throw new HandleException(11004);
        }
    }
}
