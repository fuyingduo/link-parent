package com.custom.service.impl;

import com.custom.bo.Categories;
import com.custom.bo.Classification;
import com.custom.entity.ProductDict;
import com.custom.enums.LevelEnum;
import com.custom.service.IProductClassificationService;
import com.custom.service.IProductDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 产品分类
 * created by fuyd on 2019-07-11
 */
@Service
public class ProductClassificationService implements IProductClassificationService {

    @Autowired
    private IProductDictService iProductDictService;

    @Override
    public List<Categories> primaryCategories() {
        return iProductDictService.categories(LevelEnum.LEVEL_1.getCode());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "root_cache", key = "getArgs()")
    public List<Classification> classificationList(String id) {
        try (Stream<ProductDict> dictStream = iProductDictService.findProductDictsByParentId(id)) {
            return dictStream.map(pd -> new Classification(pd.getId(), pd.getName(), nextDirectories(pd.getId().toString()))).collect(toList());
        }
    }

    /**
     * 下级目录
     *
     * @param id 上级目录id
     */
    private List<Categories> nextDirectories(String id) {
        try (Stream<ProductDict> subStream = iProductDictService.findProductDictsByParentId(id)) {
            return subStream.map(Categories::new).collect(toList());
        }
    }
}
