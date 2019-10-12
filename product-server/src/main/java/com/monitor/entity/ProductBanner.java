package com.monitor.entity;

import javax.persistence.*;

/**
 * 产品banner图
 * created by fuyd on 2019-07-10
 */
@Entity(name = "product_banner")
public class ProductBanner {

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
     * 类型
     *
     * @see com.monitor.enums.BannerTypeEnum
     */
    private Integer type;

    /**
     * 地址
     */
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ProductBanner{" +
                "id=" + id +
                ", productId=" + productId +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
