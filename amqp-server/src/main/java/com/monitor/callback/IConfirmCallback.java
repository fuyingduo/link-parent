package com.monitor.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 请求交换机回调接口
 *
 * @see DefaultConfirmCallback
 * created by fuyd on 2019-07-18
 */
public interface IConfirmCallback extends RabbitTemplate.ConfirmCallback {
}
