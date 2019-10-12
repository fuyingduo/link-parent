package com.monitor.core;

import com.monitor.service.IDbScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * created by fuyd on 2019-07-19
 */
@Configuration
@EnableConfigurationProperties(TaskProperties.class)
public class ScheduleInitialization implements SchedulingConfigurer {

    /**
     * 配置文件信息
     */
    private final TaskProperties taskProperties;

    /**
     * 定时器共享数据接口
     */
    private RegisteredDataManagement registeredDataManagement;

    /**
     * 定时器注册实例
     */
    private ScheduledTaskRegistrar taskRegistrar;

    /**
     * 数据初始化接口
     */
    private IRegistrationDataInitialization iRegistrationDataInitialization;

    /**
     * 定时器线程池
     */
    private final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleInitialization.class);

    /**
     * 私有构造器
     *
     * @param taskProperties 配置文件信息
     */
    public ScheduleInitialization(TaskProperties taskProperties) {
        this.taskProperties = taskProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public RegisteredDataManagement scheduleInitializeSharedData() {
        this.registeredDataManagement = new RegisteredDataManagement();
        return registeredDataManagement;
    }

    @Bean
    @ConditionalOnMissingBean
    public IDbScheduleService iDbScheduleService() {
        return new DbScheduleService();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRegistrationDataInitialization iRegistrationDataInitialization() {
        this.iRegistrationDataInitialization = new RegistrationDataInitialization((IDbScheduleEndpoint) iDbScheduleService());
        return this.iRegistrationDataInitialization;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
        this.registeredDataManagement.setTaskRegistrar(taskRegistrar);
        this.threadPoolInitialize();
        this.iRegistrationDataInitialization.dataInitialization();
    }

    /**
     * 线程池初始化
     *
     * @see TaskProperties
     */
    private void threadPoolInitialize() {
        scheduler.setPoolSize(taskProperties.getThreadPool().getSize());
        scheduler.initialize();
        taskRegistrar.setScheduler(scheduler);
        ScheduleInitialization.LOG.info("[动态定时] 初始化线程池... poolSize:{}", taskProperties.getThreadPool().getSize());
    }
}
