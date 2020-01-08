package com.custom.bo;

import com.custom.entity.Job;
import com.custom.enums.StatusEnum;
import com.custom.exception.HandleException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * created by fuyd on 2020-01-07
 */
public class TaskParamBO {

    /**
     * 任务名称
     */
    private String name;
    /**
     * 服务类型 1SpringCloud 2http
     */
    private Integer serviceType;
    /**
     * 服务地址
     */
    private String serviceAddress;
    /**
     * 表达式
     */
    private String cron;
    /**
     * 是否启用日志
     */
    private Integer hasLog;
    /**
     * 任务描述
     */
    private String description;

    /**
     * 转换Job实体对象
     */
    public Job toJob() {
        Job job = new Job();
        if (StringUtils.isEmpty(this.name))
            throw new HandleException(30001);
        job.setName(this.name);

        if (null == this.serviceType)
            throw new HandleException(30002);
        job.setServiceType(this.serviceType);

        if (StringUtils.isEmpty(this.serviceAddress))
            throw new HandleException(30003);
        job.setServiceAddress(this.serviceAddress);

        if (StringUtils.isEmpty(cron))
            throw new HandleException(30004);
        job.setCron(this.cron);

        job.setHasLog(this.hasLog);
        job.setDescription(this.description);
        job.setStatus(StatusEnum.YES.value());
        return job;
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
}
