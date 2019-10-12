package com.monitor.config;

import com.monitor.core.TaskProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by fuyd on 2019-07-22
 */
@Configuration
@EnableConfigurationProperties(TaskProperties.class)
public class JpaConfiguration {

    private final TaskProperties taskProperties;
    public JpaConfiguration(TaskProperties taskProperties) {
        this.taskProperties = taskProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "schdule", name = "persistence", havingValue = "true", matchIfMissing = true)
    public JpaProperties jpaProperties() {
        JpaProperties properties = new JpaProperties();
        properties.setDatabase(taskProperties.getJpa().getDatabase());
        properties.setShowSql(taskProperties.getJpa().getShowSql());
        properties.setProperties(taskProperties.getJpa().getProperties());
        properties.setDatabasePlatform(taskProperties.getJpa().getDatabasePlatform());
        return properties;
    }
}
