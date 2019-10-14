package com.custom.service;

import com.custom.bo.Categories;
import com.custom.entity.ProductDict;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * created by fuyd on 2019-07-11
 */
public interface IProductDictService {

    /**
     * 指定级别字典信息
     *
     * @param level 等级
     *              {@link com.custom.enums.LevelEnum}
     */
    List<Categories> categories(Integer level);

    /**
     * 字典详情信息
     *
     * @param id 主键id
     */
    Optional<ProductDict> findProductDictById(Integer id);

    /**
     * 通过父id获取流信息
     *
     * @param parentId 父id
     */
    Stream<ProductDict> findProductDictsByParentId(Integer parentId);
}
