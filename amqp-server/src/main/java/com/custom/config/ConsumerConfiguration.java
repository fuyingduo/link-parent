package com.custom.config;

import com.custom.entity.MQProperties;
import com.custom.listener.IChannelAwareMessageListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ客户端配置
 * created by fuyd on 2019-07-18
 */
@Configuration
public class ConsumerConfiguration {

    @Autowired
    private MQProperties mqProperties;

    @Bean
    public SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory,
                                                            IChannelAwareMessageListener iChannelAwareMessageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(mqProperties.getConsumer().getDefaultQueue());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(iChannelAwareMessageListener);
        return container;
    }
}
