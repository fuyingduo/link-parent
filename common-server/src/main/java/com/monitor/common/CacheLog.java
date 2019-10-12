package com.monitor.common;

import java.time.LocalDateTime;

/**
 * created by fuyd on 2019-09-27
 */
public class CacheLog {

    private Integer id;

    private Integer crUserId;

    private String applicationName;

    private String classMethod;

    private String params;

    private String remark;

    private String deivce;

    private String deviceId;

    private String client;

    private LocalDateTime crDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrUserId() {
        return crUserId;
    }

    public void setCrUserId(Integer crUserId) {
        this.crUserId = crUserId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeivce() {
        return deivce;
    }

    public void setDeivce(String deivce) {
        this.deivce = deivce;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDateTime getCrDate() {
        return crDate;
    }

    public void setCrDate(LocalDateTime crDate) {
        this.crDate = crDate;
    }

    @Override
    public String toString() {
        return "CacheLog{" +
                "id=" + id +
                ", crUserId=" + crUserId +
                ", applicationName='" + applicationName + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", params='" + params + '\'' +
                ", remark='" + remark + '\'' +
                ", deivce='" + deivce + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", client='" + client + '\'' +
                ", crDate=" + crDate +
                '}';
    }
}
