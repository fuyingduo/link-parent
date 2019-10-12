package com.monitor.entity;

import javax.persistence.*;

/**
 * 产品规格
 * created by fuyd on 2019-07-09
 */
@Entity(name = "product_specifications")
public class ProductSpecifications {

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
     * 类型(风格，工艺，适用人群...)
     */
    @Column(name = "type", nullable = false)
    private Integer type;

    /**
     * 标签(水洗， 免烫， 青年...)
     */
    @Column(name = "label", nullable = false)
    private Integer label;

    /**
     * 排序 (唯一索引)
     */
    @Column(unique = true, nullable = false)
    private Integer sort;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "ProductSpecifications{" +
                "id=" + id +
                ", productId=" + productId +
                ", type=" + type +
                ", label=" + label +
                ", sort=" + sort +
                '}';
    }
}
