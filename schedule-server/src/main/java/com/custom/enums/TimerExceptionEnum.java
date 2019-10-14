package com.custom.enums;

import java.util.Arrays;

/**
 * Timer定时器异常枚举
 * created by fuyd on 2019-10-14
 */
public enum TimerExceptionEnum {

    PRIMARY_KEY_REPEAT(10001, "主键重复..."),

    TIMER_DOESN_EXIST(10002, "传入TaskId有误, 未能查询到结果..."),

    TAG_NONE_EMPTY(10003, "标签不能为空..."),

    EXPRESSION_NONE_EMPTY(10004, "表达式不能为空..."),

    TASK_NONE_EMPTY(10005, "TaskId不能为空..."),

    THREAD_POOL_INITIALIZE_FAILURE(10006, "线程池初始化失败..."),

    REGISTRATION_FAILED(10007, "定时器注册失败..."),

    STOP_FAILED(10008, "定时器停止失败...");

    private int code;
    private String message;

    TimerExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String message(int code) {
        return Arrays.stream(TimerExceptionEnum.values())
                .filter(e -> code == e.code).findAny().map(TimerExceptionEnum::getMessage).orElse("未知异常");
    }

    public int code() {
        return code;
    }

    private String getMessage() {
        return message;
    }

}
