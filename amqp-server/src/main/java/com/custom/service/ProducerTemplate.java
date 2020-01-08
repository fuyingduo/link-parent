package com.custom.service;

/**
 * created by fuyd on 2019-11-06
 */
public interface ProducerTemplate {

    void send(String topic, String tag, String content);
}
