package com.custom.service.impl;

import com.custom.dao.ProductRepository;
import com.custom.entity.Product;
import com.custom.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 产品信息
 * created by fuyd on 2019-07-15
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> productInfo(Integer id) {
        return productRepository.findById(id);
    }
}
