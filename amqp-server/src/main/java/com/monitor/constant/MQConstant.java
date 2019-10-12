package com.monitor.constant;

/**
 * MQ常量信息
 * created by fuyd on 2019-07-18
 */
public class MQConstant {

    /**
     * 默认交换机
     *
     * @see com.monitor.entity.SendMessage
     */
    public static final String DEFAULT_EXCHANGE = "test_exchange";

    /**
     * 默认路由关键字
     *
     * @see com.monitor.entity.SendMessage
     */
    public static final String DEFAULT_ROUTING_KEY = "test_queue";
}
