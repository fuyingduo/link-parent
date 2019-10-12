package com.monitor.listener;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * MQ消费者服务监听接口 默认实现
 *
 * @see DefaultConsumerListener
 * created by fuyd on 2019-07-18
 */
public interface IChannelAwareMessageListener extends ChannelAwareMessageListener {
}
