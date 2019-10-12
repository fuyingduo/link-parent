package com.monitor.dao;

import com.monitor.entity.ProductDict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

/**
 * created by fuyd on 2019-07-11
 */
public interface ProductDictRepository extends JpaRepository<ProductDict, Integer> {

    /**
     * 通过等级获取产品字典表
     *
     * @param level 等级
     *              {@link com.monitor.enums.LevelEnum}
     */
    Stream<ProductDict> findAllByLevel(Integer level);

    /**
     * 通过父id获取产品字典表
     *
     * @param parentId 父id
     */
    Stream<ProductDict> findAllByParentId(Integer parentId);
}
