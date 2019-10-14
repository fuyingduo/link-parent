package com.custom.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 默认请求交换机接口实现
 * created by fuyd on 2019-07-18
 */
public class DefaultConfirmCallback implements IConfirmCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfirmCallback.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        LOGGER.info("[MQ服务] ack:{}, cause:{}", ack, cause);
    }
}
