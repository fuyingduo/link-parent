package com.custom.enums;

import com.custom.base.BaseEnum;

/**
 * banner图类型
 * created by fuyd on 2019-07-10
 */
public enum BannerTypeEnum implements BaseEnum {

    PICTURE(1, "图片"),
    VIDEO(2, "视频");

    private Integer code;
    private String msg;

    BannerTypeEnum(Integer code, String msg) {
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
