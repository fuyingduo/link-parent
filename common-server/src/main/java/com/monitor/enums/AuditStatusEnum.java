package com.monitor.enums;

import com.monitor.base.BaseEnum;

/**
 * 审核
 * created by fuyd on 2019-07-10
 */
public enum AuditStatusEnum implements BaseEnum {
    WAITING(1, "待审核"),
    THROUGH(2, "已通过"),
    REFUSED(3, "已拒绝"),
    UNDO(4, "已撤销");

    private Integer code;
    private String msg;

    AuditStatusEnum(Integer code, String msg) {
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
