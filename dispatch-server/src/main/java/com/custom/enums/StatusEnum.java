package com.custom.enums;

/**
 * created by fuyd on 2020-01-07
 */
public enum StatusEnum {

    YES(1),
    NO(0);

    private int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
