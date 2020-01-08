package com.custom.service.impl;

import com.custom.enums.ServiceTypeEnum;
import com.custom.exception.HandleException;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by fuyd on 2020-01-07
 */
@Component
public class QuratzCoreBusiness {

    private static final Logger log = LoggerFactory.getLogger(QuratzCoreBusiness.class);

    @Autowired
    private Scheduler scheduler;
    private static final String TRIGGER_PEX = "trigger";

    void newTask(String jobName, String jobGroup, String description, String expression, Integer serviceType) {
        if (StringUtils.isEmpty(jobGroup)) {
            throw new HandleException(10001);
        }
        JobDetail jobDetail = JobBuilder.newJob(this.findClass(serviceType)).withIdentity(jobName, jobGroup)
                .withDescription(description).build();

        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(expression.trim());
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity((QuratzCoreBusiness.TRIGGER_PEX + jobName), jobGroup).startNow().withSchedule(builder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("[调度中心] 注册调度失败:{}", e.getMessage());
            throw new HandleException(10003);
        }
    }

    Boolean pauseTask(String jobName, String jobGroup) {
        if (StringUtils.isEmpty(jobGroup)) {
            throw new HandleException(10001);
        }
        try {
            scheduler.pauseJob(new JobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            log.error("[调度中心] 停止调度失败:{}", e.getMessage());
            throw new HandleException(20003);
        }
        return true;
    }

    Boolean resumeTask(String jobName, String jobGroup) {
        try {
            scheduler.resumeJob(new JobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            log.error("[调度中心] 恢复调度失败:{}", e.getMessage());
            throw new HandleException(20004);
        }
        return true;
    }

    Boolean triggerTask(String jobName, String jobGroup) {
        try {
            scheduler.triggerJob(new JobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            log.error("[调度中心] 触发调度失败:{}", e.getMessage());
            throw new HandleException(20006);
        }
        return true;
    }

    Boolean removeTask(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            // 停止触发
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            log.error("[调度中心] 移除调度失败:{}", e.getMessage());
            throw new HandleException(20005);
        }
        return true;
    }

    /**
     * 通过服务类型获取任务调度
     *
     * @param serviceType 服务类型 {@link ServiceTypeEnum}
     */
    private Class<? extends Job> findClass(Integer serviceType) {
        if (ServiceTypeEnum.SPRINGCLOUD.value() == serviceType) {
            return RestJobExecute.class;
        }
        if (ServiceTypeEnum.HTTP.value() == serviceType) {
            return HttpJobExecute.class;
        }
        throw new HandleException(10002);
    }
}
