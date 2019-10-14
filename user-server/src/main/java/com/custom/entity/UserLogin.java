package com.custom.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * created by fuyd on 2019-07-01
 */
@Entity(name = "user_login")
@EntityListeners(AuditingEntityListener.class)
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "pass_word")
    private String passWord;
    private Integer type;
    @Column(name = "visit_jurisdiction")
    private Integer visitJurisdiction;
    @Column(name = "cr_date")
    @CreatedDate
    private LocalDateTime crDate;
    @Column(name = "up_data")
    @LastModifiedDate
    private LocalDateTime upData;
    @Column(name = "up_uid")
    @LastModifiedBy
    private Integer upUid;
    @Version
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVisitJurisdiction() {
        return visitJurisdiction;
    }

    public void setVisitJurisdiction(Integer visitJurisdiction) {
        this.visitJurisdiction = visitJurisdiction;
    }

    public LocalDateTime getCrDate() {
        return crDate;
    }

    public void setCrDate(LocalDateTime crDate) {
        this.crDate = crDate;
    }

    public LocalDateTime getUpData() {
        return upData;
    }

    public void setUpData(LocalDateTime upData) {
        this.upData = upData;
    }

    public Integer getUpUid() {
        return upUid;
    }

    public void setUpUid(Integer upUid) {
        this.upUid = upUid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", type=" + type +
                ", visitJurisdiction=" + visitJurisdiction +
                ", crDate=" + crDate +
                ", upData=" + upData +
                ", upUid=" + upUid +
                ", version=" + version +
                '}';
    }
}
