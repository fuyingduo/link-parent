package com.custom.common;

/**
 * created by fuyd on 2019-07-08
 */
public class Querys {

    private Querys() {
    }

    public static QueryBuilder builder() {
        return new DefaultQueryBuilder();
    }
}
