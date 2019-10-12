package com.monitor.entity;

import com.monitor.constant.MQConstant;

import javax.validation.constraints.NotBlank;

/**
 * 发送消息体
 * created by fuyd on 2019-07-17
 */
public class SendMessage {

    /**
     * 交换机名称
     */
    @NotBlank(message = "exchange属性为空")
    private String exchange;

    /**
     * 路由关键字
     */
    @NotBlank(message = "routingKey属性为空")
    private String routingKey;

    /**
     * 消息体
     */
    @NotBlank(message = "payload属性为空")
    private String payload;

    /**
     * 构造器
     *
     * @param payload 消息体
     */
    public SendMessage(String payload) {
        this.payload = payload;
        this.exchange = MQConstant.DEFAULT_EXCHANGE;
        this.routingKey = MQConstant.DEFAULT_ROUTING_KEY;
    }

    /**
     * 构造器
     *
     * @param exchange   交换机
     * @param routingKey 路由关键字
     * @param payload    消息体
     */
    public SendMessage(String exchange, String routingKey, String payload) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.payload = payload;
    }

    public String getExchange() {
        return exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "exchange='" + exchange + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
