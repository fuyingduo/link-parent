package com.monitor.enums;

import com.monitor.base.BaseEnum;

/**
 * 是否
 * created by fuyd on 2019-07-02
 */
public enum WhetherEnum implements BaseEnum {
    NO(0, "否"),
    IS(1, "是");


    private Integer code;
    private String msg;

    WhetherEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.msg;
    }
}
