package com.custom.controller;

import com.custom.core.PushMessage;
import com.custom.service.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by fuyd on 2019-11-05
 */
@RestController
@RequestMapping("/rocket")
public class RocketController {

    @Autowired
    ProducerTemplate producerTemplate;

    @GetMapping(value = "/send")
    public void send() {
        PushMessage pushMessage = new PushMessage();
        pushMessage.setId(1);
        pushMessage.setName("zhangsan");
        pushMessage.setMessage("xiaoxi");
        producerTemplate.send("TTAM123", "tag123", pushMessage.toJSONString());
    }
}
