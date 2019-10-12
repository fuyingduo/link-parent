package com.monitor.enums;

import com.monitor.base.BaseEnum;

/**
 * created by fuyd on 2019-06-16
 */
public enum StatusCodeEnum implements BaseEnum {

    /**
     * 用户状态 1xxxx
     */
    NOT_LOGIN(101, "用户未登陆"),
    LOGON_FAILURE(10001, "登陆失败"),
    REGISTER_FAILURE(10002, "注册失败");

    private int code;
    private String msg;

    StatusCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
