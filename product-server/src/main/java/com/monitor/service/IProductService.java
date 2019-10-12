package com.monitor.service;

import com.monitor.entity.Product;

import java.util.Optional;

/**
 * created by fuyd on 2019-07-10
 */
public interface IProductService {

    /**
     * 产品详情
     *
     * @param id 产品id
     */
    Optional<Product> productInfo(Integer id);
}