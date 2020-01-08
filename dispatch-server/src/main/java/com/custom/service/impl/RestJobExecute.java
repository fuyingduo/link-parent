package com.custom.service.impl;

import com.custom.enums.ServiceTypeEnum;
import com.custom.service.IJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * created by fuyd on 2020-01-07
 */
@Component
public class RestJobExecute implements Job {

    @Autowired
    private IJobService iJobService;
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(QuratzCoreBusiness.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        String name = context.getJobDetail().getKey().getName();
        String now = LocalDateTime.now().toString();
        log.info("[调度任务执行] 开始执行调度- 当前时间:{}, 执行任务:{} ....", now, name);
        int serviceType = ServiceTypeEnum.SPRINGCLOUD.value();
        com.custom.entity.Job job = iJobService.findJobByParams(name, serviceType);
        String json = restTemplate.getForObject(job.getServiceAddress(), String.class);
        log.info("[调度任务执行] 执行调度接收- 当前时间:{}, 执行任务:{}, 请求URL:{}, 返回结果:{}", now, name, job.getServiceAddress(), json);
    }
}
