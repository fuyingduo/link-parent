package com.custom.listener;

import com.custom.util.JsonUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import java.util.Optional;

/**
 * 默认消费者监听器
 * created by fuyd on 2019-07-18
 */
public class DefaultConsumerListener implements IChannelAwareMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConsumerListener.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            Optional<String> opl = JsonUtil.parse(message.getBody(), String.class);
            LOGGER.info("[MQ服务] 接收消息:{}", opl.orElse(""));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
