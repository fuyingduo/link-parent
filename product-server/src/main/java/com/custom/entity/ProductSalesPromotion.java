package com.custom.entity;

import javax.persistence.*;

/**
 * 产品促销
 * created by fuyd on 2019-07-10
 */
@Entity(name = "product_sales_promotion")
public class ProductSalesPromotion {

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
     * 开始范围
     */
    @Column(name = "start_range", nullable = false)
    private Integer startRange;

    /**
     * 结束范围
     */
    @Column(name = "end_Range", nullable = false)
    private Integer endRange;

    /**
     * 折扣
     */
    @Column(nullable = false)
    private Long discount;

    /**
     * 是否默认
     */
    @Column(name = "the_default", nullable = false)
    private Integer theDefault;

    /**
     * 促销介绍
     */
    private String introduction;

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

    public Integer getStartRange() {
        return startRange;
    }

    public void setStartRange(Integer startRange) {
        this.startRange = startRange;
    }

    public Integer getEndRange() {
        return endRange;
    }

    public void setEndRange(Integer endRange) {
        this.endRange = endRange;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Integer getTheDefault() {
        return theDefault;
    }

    public void setTheDefault(Integer theDefault) {
        this.theDefault = theDefault;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ProductSalesPromotion{" +
                "id=" + id +
                ", productId=" + productId +
                ", startRange=" + startRange +
                ", endRange=" + endRange +
                ", discount=" + discount +
                ", theDefault=" + theDefault +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
