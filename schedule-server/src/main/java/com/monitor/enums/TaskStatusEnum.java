package com.monitor.enums;

/**
 * 定时器状态枚举
 * created by fuyd on 2019-07-19
 */
public enum TaskStatusEnum {

    /**
     * 启用
     */
    ENABLE(1),

    /**
     * 禁用
     */
    DISABLE(2);

    private Integer code;

    TaskStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }

    @Override
    public String toString() {
        return "TaskStatusEnum{" +
                "code=" + code +
                '}';
    }
}
