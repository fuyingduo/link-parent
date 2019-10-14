package com.custom.entity;

import javax.persistence.*;

/**
 * 产品介绍
 * created by fuyd on 2019-07-09
 */
@Entity(name = "product_introduce")
public class ProductIntroduce {

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
     * 内容
     */
    @Column(name = "content", nullable = false)
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "ProductIntroduce{" +
                "id=" + id +
                ", productId=" + productId +
                ", content='" + content + '\'' +
                ", sort=" + sort +
                '}';
    }
}
