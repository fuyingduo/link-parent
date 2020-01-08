package com.custom.listener;

import org.apache.commons.io.Charsets;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * created by fuyd on 2019-11-06
 */
public class RocketMessageListener implements MessageListenerOrderly {
    private static final Logger LOG = LoggerFactory.getLogger(RocketMessageListener.class);

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        RocketMessageListener.LOG.info("RocketMQ消费监听... ");
        msgs.forEach(msg -> {
            String content = new String(msg.getBody(), Charsets.UTF_8);
            RocketMessageListener.LOG.info(String.format("RocketMQ 消费接收... \n [body]: %s", content));
        });
        return ConsumeOrderlyStatus.SUCCESS;
    }
}
