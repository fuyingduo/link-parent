package com.monitor.service.loginout;

import com.monitor.params.login.ILoginUser;

/**
 * 用户登陆
 *
 * @see com.monitor.entity.UserLogin
 * created by fuyd on 2019-06-16
 */
public interface ILoginService {

    /**
     * 用户登陆
     */
    String login(ILoginUser iLoginUser);
}
