package com.custom.service.information;

/**
 * 用户个人信息
 *
 * @see com.custom.entity.UserLogin
 * created by fuyd on 2019-07-03
 */
public interface IUserInformation {

    /**
     * 修改用户登陆密码
     *
     * @param loginId 用户登陆id
     * @param code    短信验证码
     * @param newPass 新密码
     */
    Boolean updateLoginPassword(Integer loginId, String code, String newPass);
}
