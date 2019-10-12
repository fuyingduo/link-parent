package com.monitor.params;

/**
 * APP 注册参数
 * created by fuyd on 2019-07-04
 */
public class AppUserRegistration implements UserRegistration {

    private String username;
    private String password;
    private Integer type;
    private Integer visitJurisdiction;

    public AppUserRegistration(String username, String password, Integer type, Integer visitJurisdiction) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.visitJurisdiction = visitJurisdiction;
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public Integer type() {
        return this.type;
    }

    @Override
    public Integer visitJurisdiction() {
        return this.visitJurisdiction;
    }
}
