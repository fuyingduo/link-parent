package com.custom.controller;

import com.custom.base.BaseResult;
import com.custom.bo.Categories;
import com.custom.bo.Classification;
import com.custom.service.IProductClassificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 产品分类
 * created by fuyd on 2019-07-11
 */
@Api(tags = "product_classification")
@RestController
@RequestMapping("/product/classification")
public class ProductClassificationController {

    @Autowired
    private IProductClassificationService iProductClassificationService;

    @ApiOperation(value = "一级分类列表", notes = "一级分类列表")
    @GetMapping(value = "/primary")
    public BaseResult<List<Categories>> primaryCategories() {
        return BaseResult.ok(iProductClassificationService.primaryCategories());
    }

    @ApiOperation(value = "一级分类下信息", notes = "一级分类下信息")
    @ApiImplicitParam(name = "id", value = "一级类目id", required = true, paramType = "query")
    @GetMapping(value = "/category")
    public BaseResult<List<Classification>> classificationList(@NotNull Integer id) {
        return BaseResult.ok(iProductClassificationService.classificationList(id));
    }
}
