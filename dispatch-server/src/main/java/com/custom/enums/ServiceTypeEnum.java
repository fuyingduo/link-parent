package com.custom.enums;

import java.util.Arrays;

/**
 * created by fuyd on 2020-01-07
 */
public enum ServiceTypeEnum {

    SPRINGCLOUD(1, "spring_cloud"),
    HTTP(2, "http");

    private int value;
    private String msg;

    ServiceTypeEnum(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int value() {
        return value;
    }

    public String msg() {
        return msg;
    }

    public static String findMsg(int value) {
        return Arrays.stream(ServiceTypeEnum.values())
                .filter(e -> e.value == value).findAny().map(ServiceTypeEnum::msg).orElse(null);
    }
}
