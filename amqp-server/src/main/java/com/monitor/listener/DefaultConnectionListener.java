package com.monitor.listener;

import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.Connection;

/**
 * 默认MQ连接监听器
 * created by fuyd on 2019-07-17
 */
public class DefaultConnectionListener implements IConnectionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConnectionListener.class);

    @Override
    public void onCreate(Connection connection) {
        LOGGER.info("[MQ服务] 创建MQ新连接...");
    }

    @Override
    public void onClose(Connection connection) {
        LOGGER.info("[MQ服务] 关闭MQ新连接...");
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        LOGGER.info("[MQ服务] 停止MQ新连接...");
    }
}
