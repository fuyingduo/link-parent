package com.custom.service.loginout;

/**
 * 用户登出
 *
 * @see com.custom.entity.UserLogin
 * created by fuyd on 2019-07-02
 */
public interface ILogoutService {

    /**
     * 用户登出
     */
    boolean logout(String token);
}
