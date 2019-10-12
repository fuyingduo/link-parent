package com.monitor.service.impl;

import com.monitor.bo.Categories;
import com.monitor.bo.Classification;
import com.monitor.entity.ProductDict;
import com.monitor.enums.LevelEnum;
import com.monitor.service.IProductClassificationService;
import com.monitor.service.IProductDictService;
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
    public List<Classification> classificationList(Integer id) {
        try (Stream<ProductDict> dictStream = iProductDictService.findProductDictsByParentId(id)) {
            return dictStream.map(pd -> new Classification(pd.getId(), pd.getName(), nextDirectories(pd.getId()))).collect(toList());
        }
    }

    /**
     * 下级目录
     *
     * @param id 上级目录id
     */
    private List<Categories> nextDirectories(Integer id) {
        try (Stream<ProductDict> subStream = iProductDictService.findProductDictsByParentId(id)) {
            return subStream.map(Categories::new).collect(toList());
        }
    }
}
