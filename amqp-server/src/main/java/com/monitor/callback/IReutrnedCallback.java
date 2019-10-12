package com.monitor.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 交换机请求队列失败回调接口
 *
 * @see DefaultReutrnedCallback
 * created by fuyd on 2019-07-18
 */
public interface IReutrnedCallback extends RabbitTemplate.ReturnCallback {
}
