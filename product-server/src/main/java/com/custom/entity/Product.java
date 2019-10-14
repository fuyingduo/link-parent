package com.custom.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * created by fuyd on 2019-07-09
 */
@Entity(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 详情介绍
     */
    private String details;

    /**
     * 商品类型
     */
    private Integer type;

    /**
     * 标签
     */
    private String label;

    /**
     * 封面
     */
    private String covers;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 单价
     */
    private Long price;

    /**
     * 评价
     */
    private Integer evaluation;

    /**
     * 创建时间
     */
    @Column(name = "cr_date")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    private LocalDateTime crDate;

    /**
     * 最后修改时间
     */
    @Column(name = "up_date")
    @LastModifiedDate
    @JsonFormat(locale = "yyyy-MM-dd HH:mm:dd")
    private LocalDateTime upDate;

    /**
     * 审核状态
     *
     * @see com.custom.enums.AuditStatusEnum
     */
    @Column(name = "audit_status")
    private Integer auditStatus;

    /**
     * 状态
     *
     * @see com.custom.enums.StatusEnum
     */
    private Integer status;

    /**
     * 乐观锁字段
     */
    @Version
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCovers() {
        return covers;
    }

    public void setCovers(String covers) {
        this.covers = covers;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
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

    public LocalDateTime getCrDate() {
        return crDate;
    }

    public void setCrDate(LocalDateTime crDate) {
        this.crDate = crDate;
    }

    public LocalDateTime getUpDate() {
        return upDate;
    }

    public void setUpDate(LocalDateTime upDate) {
        this.upDate = upDate;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", type=" + type +
                ", label='" + label + '\'' +
                ", covers='" + covers + '\'' +
                ", inventory=" + inventory +
                ", price=" + price +
                ", evaluation=" + evaluation +
                ", crDate=" + crDate +
                ", upDate=" + upDate +
                ", auditStatus=" + auditStatus +
                ", status=" + status +
                ", version=" + version +
                '}';
    }
}
