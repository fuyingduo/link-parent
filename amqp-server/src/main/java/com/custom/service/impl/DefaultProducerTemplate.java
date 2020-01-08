package com.custom.service.impl;

import com.custom.callback.RocketCallback;
import com.custom.service.ProducerTemplate;
import com.google.common.base.Charsets;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by fuyd on 2019-11-06
 */
public class DefaultProducerTemplate implements ProducerTemplate {

    private DefaultMQProducer defaultMQProducer;

    public DefaultProducerTemplate(DefaultMQProducer defaultMQProducer) {
        this.defaultMQProducer = defaultMQProducer;
    }

    private static final Logger LOG = LoggerFactory.getLogger(DefaultProducerTemplate.class);

    @Override
    public void send(String topic, String tag, String content) {
        Message message = new Message(topic, tag, content.getBytes(Charsets.UTF_8));
        MessageQueue messageQueue = new MessageQueue(topic, "broker-a", 1);
        try {
            defaultMQProducer.send(message, messageQueue, new RocketCallback(), 10 * 1000);
        } catch (MQClientException | InterruptedException | RemotingException e) {
            LOG.error(e.getMessage());
        }
    }
}
