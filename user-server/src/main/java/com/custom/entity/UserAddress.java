package com.custom.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * created by fuyd on 2019-07-02
 */
@Entity(name = "user_address")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer uid;
    private String consignee;
    private String mobile;
    private Integer province;
    private Integer city;
    private Integer area;
    private String address;
    @Column(name = "default_address")
    private Integer defaultAddress;
    private Integer label;
    @Column(name = "cr_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime crDate;
    @Column(name = "up_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime upDate;

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

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
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

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", uid=" + uid +
                ", consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", area=" + area +
                ", address='" + address + '\'' +
                ", defaultAddress=" + defaultAddress +
                ", label=" + label +
                ", crDate=" + crDate +
                ", upDate=" + upDate +
                '}';
    }
}
