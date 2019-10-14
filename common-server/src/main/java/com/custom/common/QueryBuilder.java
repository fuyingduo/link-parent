package com.custom.common;

import org.springframework.data.domain.Example;

/**
 * created by fuyd on 2019-07-08
 */
public interface QueryBuilder {

    /**
     * 匹配模糊查询
     *
     * @param containing key
     * @return
     */
    QueryBuilder containing(String... containing);

    /**
     * 匹配以...开始
     *
     * @param starting
     * @return
     */
    QueryBuilder starting(String... starting);

    /**
     * 匹配以...结束
     *
     * @param ending
     * @return
     */
    QueryBuilder ending(String... ending);

    /**
     * 包含
     *
     * @param contains
     * @return
     */
    QueryBuilder contains(String... contains);

    /**
     * 构建Example
     *
     * @param t   查询实体
     * @param <T>
     * @return
     */
    <T> Example<T> build(T t);
}
