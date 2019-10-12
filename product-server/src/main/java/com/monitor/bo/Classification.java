package com.monitor.bo;

import java.io.Serializable;
import java.util.List;

/**
 * created by fuyd on 2019-07-11
 */
public class Classification implements Serializable {

    private static final long serialVersionUID = -2454484008643001115L;
    private Integer id;
    private String name;
    private List<Categories> levelCategories;

    public Classification(Integer id, String name, List<Categories> levelCategories) {
        this.id = id;
        this.name = name;
        this.levelCategories = levelCategories;
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

    public List<Categories> getLevelCategories() {
        return levelCategories;
    }

    public void setLevelCategories(List<Categories> levelCategories) {
        this.levelCategories = levelCategories;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", levelCategories=" + levelCategories +
                '}';
    }
}
