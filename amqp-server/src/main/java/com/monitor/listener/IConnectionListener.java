package com.monitor.listener;

import org.springframework.amqp.rabbit.connection.ConnectionListener;

/**
 * MQ连接监听器接口 默认实现
 *
 * @see DefaultConnectionListener
 * created by fuyd on 2019-07-18
 */
public interface IConnectionListener extends ConnectionListener {
}
