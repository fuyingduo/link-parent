package com.monitor.controller;

import com.monitor.base.BaseResult;
import com.monitor.entity.Product;
import com.monitor.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * created by fuyd on 2019-07-15
 */
@RestController
@RequestMapping("/product/info")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping(value = "/info/{productId}")
    public BaseResult<Product> productInfo(@PathVariable(value = "productId") Integer productId) {
        Optional<Product> opl = iProductService.productInfo(productId);
        return opl.map(BaseResult::ok).orElseGet(() -> BaseResult.err("获取信息失败"));
    }
}
