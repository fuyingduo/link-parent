package com.custom.service.impl;

import com.custom.entity.MQProperties;
import com.custom.entity.SendMessage;
import com.custom.service.IForwardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 转发服务实现
 * created by fuyd on 2019-07-17
 */
public class DefaultForwardService implements IForwardService {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private MQProperties mqProperties;
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultForwardService.class);

    @Override
    public Boolean send(String payload) {
        try {
            amqpTemplate.convertAndSend(mqProperties.getProducer().getDefaultExchange(),
                    mqProperties.getProducer().getDefaultRoutingKey(), payload);
        } catch (AmqpException e) {
            LOGGER.error("[MQ服务] 发送失败，失败信息:{}", e.getMessage());
            return false;
        }
        return null;
    }

    @Override
    public Boolean send(SendMessage message) {
        try {
            amqpTemplate.convertAndSend(message.getExchange(), message.getRoutingKey(), message.getPayload());
        } catch (AmqpException e) {
            LOGGER.error("[MQ服务] 发送失败，失败信息:{}", e.getMessage());
            return false;
        }
        return true;
    }
}
