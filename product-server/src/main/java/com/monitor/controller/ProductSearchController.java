package com.monitor.controller;

import com.monitor.base.BaseResult;
import com.monitor.common.Pages;
import com.monitor.entity.LaptopSearch;
import com.monitor.entity.Product;
import com.monitor.service.ILaptopSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by fuyd on 2019-07-15
 */
@RestController
@RequestMapping("/product/search")
public class ProductSearchController {

    @Autowired
    private ILaptopSearchService iLaptopSearchService;

    @GetMapping("/page/Laptop")
    public BaseResult<Page<Product>> searchPage(LaptopSearch laptopSearch, Integer index) {
        return BaseResult.ok(iLaptopSearchService.conditionsQuery(laptopSearch, Pages.byDesc(index)));
    }
}
