package com.monitor.service.impl;

import com.monitor.common.Querys;
import com.monitor.dao.LaptopSearchRepository;
import com.monitor.dao.ProductRepository;
import com.monitor.entity.LaptopSearch;
import com.monitor.entity.Product;
import com.monitor.service.ILaptopSearchService;
import com.monitor.service.ProductSearchFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * created by fuyd on 2019-07-15
 */
@Service
public class LaptopSearchService implements ILaptopSearchService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LaptopSearchRepository laptopSearchRepository;

    @Override
    public Page<Product> conditionsQuery(LaptopSearch laptopSearch, Pageable pageable) {
        ProductSearchFunction<LaptopSearch> function = (e, p) -> laptopSearchRepository.findAll(e, p);
        Page<LaptopSearch> page = function.query(Querys.builder().build(laptopSearch), pageable);
        List<Integer> ids = page.getContent().stream().map(LaptopSearch::getId).collect(toList());
        List<Product> products = productRepository.findAllByIdIn(ids);
        return new PageImpl<>(products);
    }
}

