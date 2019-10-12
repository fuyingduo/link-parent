package com.monitor.entity;

import javax.persistence.*;

/**
 * 产品字典表
 * created by fuyd on 2019-07-11
 */
@Entity(name = "product_dict")
public class ProductDict {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类别
     */
    private Integer level;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "ProductDict{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", parentId=" + parentId +
                ", sort=" + sort +
                '}';
    }
}
