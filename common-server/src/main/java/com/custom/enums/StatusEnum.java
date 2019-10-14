package com.custom.enums;

import com.custom.base.BaseEnum;

/**
 * 状态
 * created by fuyd on 2019-07-10
 */
public enum StatusEnum implements BaseEnum {

    ACTIVATION(1, "激活"), CANCEL(0, "取消");

    private Integer code;
    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.message;
    }
}
