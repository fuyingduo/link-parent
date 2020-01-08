package com.custom.config;

import com.custom.listener.RocketMessageListener;
import com.custom.utils.RocketCheck;
import com.custom.entity.RocketProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by fuyd on 2019-11-06
 */
@Configuration
@EnableConfigurationProperties(RocketProperties.class)
public class RocketConsumerConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(RocketConsumerConfiguration.class);

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer(RocketProperties rocketProperties) {
        if (!RocketCheck.consumer(rocketProperties)) return null;

        RocketProperties.Consumer propertiesConsumer = rocketProperties.getConsumer();
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                rocketProperties.getNameSpace(), rocketProperties.getDefaultGroup());
        consumer.setNamesrvAddr(rocketProperties.getNamesrvAddr());
        consumer.setInstanceName(rocketProperties.getInstanceName());
        consumer.setMessageListener(new RocketMessageListener());
        try {
            consumer.subscribe(propertiesConsumer.getTopic(), propertiesConsumer.getTag());
            consumer.start();
        } catch (MQClientException e) {
            LOG.error("RockerMQ 启动失败 ... 原因:{}", e.getErrorMessage());
        }
        return consumer;
    }
}
