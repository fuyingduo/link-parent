package com.monitor.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

/**
 * 默认交换机请求队列失败回调接口实现
 * created by fuyd on 2019-07-18
 */
public class DefaultReutrnedCallback implements IReutrnedCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReutrnedCallback.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.error("[MQ服务] exechange -> queue 失败 message:{}, replyCode:{}, " +
                "replyText:{}, exchange:{}, routingKey:{}", message, replyCode, replyText, exchange, routingKey);
    }
}
