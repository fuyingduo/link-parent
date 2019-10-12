package com.monitor.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 产品条件查询函数接口
 * created by fuyd on 2019-07-15
 */
@FunctionalInterface
public interface ProductSearchFunction<T> {

    Page<T> query(Example<T> example, Pageable pageable);
}
