package com.custom.bo;

/**
 * 产品列表页
 * created by fuyd on 2019-07-10
 */
public class ProductList {

    private Integer id;
    // 名称
    private String name;
    // 标签
    private String label;
    // 单价
    private Long price;
    // 评论
    private Integer evaluation;
    // 用户
    private Integer uid;
    // 用户名
    private String uName;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", evaluation=" + evaluation +
                ", uid=" + uid +
                ", uName='" + uName + '\'' +
                '}';
    }
}
