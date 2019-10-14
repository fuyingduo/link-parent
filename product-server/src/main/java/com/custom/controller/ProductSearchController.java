package com.custom.controller;

import com.custom.base.BaseResult;
import com.custom.common.Pages;
import com.custom.entity.LaptopSearch;
import com.custom.entity.Product;
import com.custom.service.ILaptopSearchService;
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
