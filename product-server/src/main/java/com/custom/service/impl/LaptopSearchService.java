package com.custom.service.impl;

import com.custom.common.Querys;
import com.custom.dao.LaptopSearchRepository;
import com.custom.dao.ProductRepository;
import com.custom.entity.LaptopSearch;
import com.custom.entity.Product;
import com.custom.service.ILaptopSearchService;
import com.custom.service.ProductSearchFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

