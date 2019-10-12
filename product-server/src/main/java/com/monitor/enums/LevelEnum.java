package com.monitor.enums;

/**
 * created by fuyd on 2019-07-11
 */
public enum LevelEnum {

    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3);

    private int code;

    LevelEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
