package com.monitor.exception;

import com.monitor.enums.TimerExceptionEnum;

/**
 * 定时器异常处理类
 * created by fuyd on 2019-10-14
 */
public class TimerException extends RuntimeException {

    // 异常状态码
    private Integer code;
    // 异常信息
    private String message;

    /**
     * 默认构造器
     */
    public TimerException(Integer code) {
        super();
        this.code = code;
        this.message = TimerExceptionEnum.message(code);
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
