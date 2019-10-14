package com.custom.controller;

import com.custom.base.BaseResult;
import com.custom.entity.ProductDict;
import com.custom.service.IProductDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * 产品字典
 * created by fuyd on 2019-07-11
 */
@Api(tags = "product_dict")
@RestController
@RequestMapping("/product/dict")
public class ProductDictController {

    @Autowired
    private IProductDictService iProductDictService;

    @ApiOperation(value = "字典详情", notes = "字典详情")
    @ApiImplicitParam(name = "id", value = "字典id", required = true, paramType = "query")
    @GetMapping(value = "/info")
    public BaseResult<ProductDict> dictInfo(@NotNull Integer id) {
        Optional<ProductDict> opl = iProductDictService.findProductDictById(id);
        return opl.map(BaseResult::ok).orElseGet(() -> BaseResult.err("获取信息失败"));
    }
}
