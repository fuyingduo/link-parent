package com.custom.params;

/**
 * 用户注册
 * created by fuyd on 2019-07-04
 */
public interface UserRegistration {

    /**
     * 用户名
     */
    String username();

    /**
     * 密码
     */
    String password();

    /**
     * 类型
     */
    Integer type();

    /**
     * 访问权限
     */
    Integer visitJurisdiction();
}
