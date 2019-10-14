package com.custom.bo;

import com.custom.entity.ProductDict;

import java.io.Serializable;

/**
 * 产品类目
 * created by fuyd on 2019-07-11
 */
public class Categories implements Serializable {

    private static final long serialVersionUID = -2893009315198966596L;
    private Integer id;
    private String name;

    public Categories(ProductDict productDict) {
        this.id = productDict.getId();
        this.name = productDict.getName();
    }

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

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
