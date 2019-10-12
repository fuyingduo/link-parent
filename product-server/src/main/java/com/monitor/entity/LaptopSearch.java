package com.monitor.entity;

import javax.persistence.*;

/**
 * 笔记本电脑条件查询
 * created by fuyd on 2019-07-11
 */
@Entity(name = "laptop_search")
public class LaptopSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 产品id
     *
     * @see Product
     */
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    /**
     * 品牌
     */
    private Integer brand;

    /**
     * 内存
     */
    private Integer memory;

    /**
     * 处理器
     */
    private Integer processor;

    /**
     * 游戏性能
     */
    private Integer performance;

    /**
     * 系列
     */
    private Integer series;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getProcessor() {
        return processor;
    }

    public void setProcessor(Integer processor) {
        this.processor = processor;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "LaptopSearch{" +
                "id=" + id +
                ", productId=" + productId +
                ", brand=" + brand +
                ", memory=" + memory +
                ", processor=" + processor +
                ", performance=" + performance +
                ", series=" + series +
                '}';
    }
}
