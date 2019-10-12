package com.monitor.params.login;

import com.monitor.exception.HandleException;
import org.springframework.util.StringUtils;

/**
 * created by fuyd on 2019-10-08
 */
public class RegisterLoginUser implements ILoginUser {

    private String username;
    private String password;
    private String confirmPassword;
    private String smsCode;
    private String code;
    private Integer type;

    public RegisterLoginUser(String username, String password,
                             String confirmPassword, String smsCode, String code, Integer type) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.smsCode = smsCode;
        this.code = code;
        this.type = type;
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

    public Integer getType() {
        return type;
    }

    @Override
    public void check() {
        if (StringUtils.isEmpty(username)) {
            throw new HandleException(11001);
        }
        if (11 != username.length()) {
            throw new HandleException(11003);
        }
        if (StringUtils.isEmpty(password)) {
            throw new HandleException(11005);
        }
        if (StringUtils.isEmpty(confirmPassword)) {
            throw new HandleException(11006);
        }
        if (!password.equals(confirmPassword)) {
            throw new HandleException(11006);
        }
        if (StringUtils.isEmpty(smsCode)) {
            throw new HandleException(11007);
        }
        if (StringUtils.isEmpty(code)) {
            throw new HandleException(11004);
        }
    }
}
