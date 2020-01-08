package com.custom.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * created by fuyd on 2020-01-07
 */
@Slf4j
@Component
public class HttpJobExecute implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    }
}
