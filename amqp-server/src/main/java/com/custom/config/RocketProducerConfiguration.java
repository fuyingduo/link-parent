package com.custom.config;

import com.custom.service.impl.DefaultProducerTemplate;
import com.custom.service.ProducerTemplate;
import com.custom.utils.RocketCheck;
import com.custom.entity.RocketProperties;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by fuyd on 2019-11-05
 */
@Configuration
@EnableConfigurationProperties(RocketProperties.class)
public class RocketProducerConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(RocketProducerConfiguration.class);

    @Bean
    public DefaultMQProducer defaultMQProducer(RocketProperties rocketProperties) {
        if (!RocketCheck.producer(rocketProperties)) return null;

        RocketProperties.Producer propertiesProducer = rocketProperties.getProducer();
        DefaultMQProducer producer = new DefaultMQProducer(
                rocketProperties.getNameSpace(), rocketProperties.getDefaultGroup());
        producer.setCompressMsgBodyOverHowmuch(propertiesProducer.getCompressMsgBodyOverHowmuch());
        producer.setNamesrvAddr(rocketProperties.getNamesrvAddr());
        producer.setInstanceName(rocketProperties.getInstanceName());
        try {
            producer.start();
        } catch (MQClientException e) {
            RocketProducerConfiguration.LOG.error("MQClientException:{}", e);
        }
        return producer;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProducerTemplate producerTemplate(DefaultMQProducer defaultMQProducer) {
        return new DefaultProducerTemplate(defaultMQProducer);
    }
}
