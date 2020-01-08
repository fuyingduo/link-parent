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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
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
    public BaseResult<List<Classification>> classificationList(@NotNull String id) {
        return BaseResult.ok(iProductClassificationService.classificationList(id));
    }

    public static void main(String[] args) {
        Instant time = Instant.now();
        System.out.println(time.toEpochMilli());
        System.out.println(time.toString());
        int i = time.get(ChronoField.MILLI_OF_SECOND);
        System.out.println(i);
        long send = time.getLong(ChronoField.MILLI_OF_SECOND);
        long day = time.getLong(ChronoField.MICRO_OF_SECOND);
        System.out.println(send);
        System.out.println(day);
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(time, zoneId);
        System.out.println(localDateTime.toString());
    }
}
