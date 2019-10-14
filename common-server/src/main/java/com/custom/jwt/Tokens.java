package com.custom.jwt;

/**
 * Token构建者
 * created by fuyd on 2019-06-16
 */
public class Tokens {

    // 私有构造器
    private Tokens() {
    }

    public static TokenBuilder builder() {
        return new DefaultTokenBuilder();
    }
}
