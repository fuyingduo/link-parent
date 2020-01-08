package com.custom.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * created by fuyd on 2020-01-06
 */
@Entity(name = "dispatch_job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "dispatch_name", columnDefinition = "varchar(30) comment '任务名称'", unique = true, nullable = false, updatable = false)
    private String name;
    @Column(name = "dispatch_service_type", columnDefinition = "int(11) comment '服务类型 1 SpringCloud 2 http'", nullable = false, updatable = false)
    private Integer serviceType;
    @Column(name = "dispatch_service_address", columnDefinition = "varchar(100) comment '服务地址'", nullable = false, updatable = false)
    private String serviceAddress;
    @Column(name = "dispatch_cron", columnDefinition = "varchar(50) comment '表达式'", nullable = false)
    private String cron;
    @Column(name = "dispatch_status", columnDefinition = "tinyint(4) default '1' comment '状态 1启用 0禁用'", nullable = false)
    private Integer status;
    @Column(name = "dispatch_has_log", columnDefinition = "tinyint(4) default '0' comment '是否存储日志 1是 0否'", nullable = false)
    private Integer hasLog;
    @Column(name = "dispatch_description", columnDefinition = "varchar(100) comment '任务描述'")
    private String description;
    @CreatedDate
    @Column(name = "dispatch_create_date", columnDefinition = "datetime comment '创建时间'")
    private LocalDateTime createDate;
    @Column(name = "dispatch_update_date", columnDefinition = "datetime comment '创建时间'")
    private LocalDateTime updateDate;
    @Version
    @Column(columnDefinition = "int(20) default '0' comment '版本号'")
    private Integer version;

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

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHasLog() {
        return hasLog;
    }

    public void setHasLog(Integer hasLog) {
        this.hasLog = hasLog;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
