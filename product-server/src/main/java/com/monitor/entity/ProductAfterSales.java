package com.monitor.entity;

import javax.persistence.*;

/**
 * 产品售后
 * created by fuyd on 2019-07-09
 */
@Entity(name = "product_after_sales")
public class ProductAfterSales {

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
     * 包装清单
     */
    @Column(name = "packing_list")
    private String packingList;

    /**
     * 价格说明
     */
    @Column(name = "price_that")
    private String priceThat;

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

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public String getPriceThat() {
        return priceThat;
    }

    public void setPriceThat(String priceThat) {
        this.priceThat = priceThat;
    }

    @Override
    public String toString() {
        return "ProductAfterSales{" +
                "id=" + id +
                ", productId=" + productId +
                ", packingList='" + packingList + '\'' +
                ", priceThat='" + priceThat + '\'' +
                '}';
    }
}
