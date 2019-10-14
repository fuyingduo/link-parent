package com.custom.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.custom.core.TaskProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * created by fuyd on 2019-07-22
 */
@Configuration
@EnableConfigurationProperties(TaskProperties.class)
public class DataSourceConfiguration {

    private final TaskProperties taskProperties;
    public DataSourceConfiguration(TaskProperties taskProperties) {
        this.taskProperties = taskProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "schdule", name = "persistence", havingValue = "true", matchIfMissing = true)
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(taskProperties.getDatasource().getDriverClassName());
        dataSource.setUrl(taskProperties.getDatasource().getUrl());
        dataSource.setUsername(taskProperties.getDatasource().getUsername());
        dataSource.setPassword(taskProperties.getDatasource().getPassword());
        return dataSource;
    }
}
