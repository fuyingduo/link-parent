package com.custom.callback;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by fuyd on 2019-11-06
 */
public class RocketCallback implements SendCallback {

    private static final Logger LOG = LoggerFactory.getLogger(RocketCallback.class);
    private static final String MSG_ERROR = "RocketMQ failure %s";
    private static final String MSG_SUCCESS = "RocketMQ推送监听... \n [sendStatus]: %s \n [MsgId]: %s \n [BrolerName]: %s \n [QueueId]: %s \n [Topic]: %s";

    @Override
    public void onSuccess(SendResult sendResult) {
        MessageQueue messageQueue = sendResult.getMessageQueue();
        LOG.info(String.format(RocketCallback.MSG_SUCCESS
                , sendResult.getSendStatus()
                , sendResult.getMsgId()
                , messageQueue.getBrokerName()
                , messageQueue.getQueueId()
                , messageQueue.getTopic()));
    }

    @Override
    public void onException(Throwable e) {
        LOG.error(String.format(RocketCallback.MSG_ERROR, e.getMessage()));
    }
}
