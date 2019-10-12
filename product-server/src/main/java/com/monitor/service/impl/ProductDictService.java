package com.monitor.service.impl;

import com.monitor.bo.Categories;
import com.monitor.dao.ProductDictRepository;
import com.monitor.entity.ProductDict;
import com.monitor.service.IProductDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 产品字典表
 * created by fuyd on 2019-07-11
 */
@Service
public class ProductDictService implements IProductDictService {

    @Autowired
    private ProductDictRepository productDictRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categories> categories(Integer level) {
        try (Stream<ProductDict> stream = productDictRepository.findAllByLevel(level)) {
            return stream.map(Categories::new).collect(toList());
        }
    }

    @Override
    public Optional<ProductDict> findProductDictById(Integer id) {
        return productDictRepository.findById(id);
    }

    @Override
    public Stream<ProductDict> findProductDictsByParentId(Integer parentId) {
        return productDictRepository.findAllByParentId(parentId);
    }
}
