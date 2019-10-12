package com.monitor.service;

import com.monitor.entity.LaptopSearch;
import com.monitor.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * created by fuyd on 2019-07-15
 */
public interface ILaptopSearchService {

    Page<Product> conditionsQuery(LaptopSearch laptopSearch, Pageable pageable);
}
