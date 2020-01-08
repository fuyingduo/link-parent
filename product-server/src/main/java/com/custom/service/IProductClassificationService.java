package com.custom.service;

import com.custom.bo.Categories;
import com.custom.bo.Classification;

import java.util.List;

/**
 * created by fuyd on 2019-07-11
 */
public interface IProductClassificationService {

    /**
     * 获取一级类目
     */
    List<Categories> primaryCategories();

    /**
     * 获取一级类目下详情信息
     *
     * @param id 字典id
     */
    List<Classification> classificationList(String id);
}
