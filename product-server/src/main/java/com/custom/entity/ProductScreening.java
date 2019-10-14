package com.custom.entity;

import javax.persistence.*;

/**
 * 产品筛选列表
 * created by fuyd on 2019-07-10
 */
@Entity(name = "product_screening")
public class ProductScreening {

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
     * 类型 (颜色，版本...)
     */
    @Column(nullable = false)
    private Integer type;

    /**
     * 分类 (4g独显...)
     */
    @Column(nullable = false)
    private String classification;

    /**
     * 库存数量(-1 无限制)
     */
    @Column(nullable = false)
    private Long num;

    @Version
    private Integer version;

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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ProductScreening{" +
                "id=" + id +
                ", productId=" + productId +
                ", type=" + type +
                ", classification='" + classification + '\'' +
                ", num=" + num +
                ", version=" + version +
                '}';
    }
}
