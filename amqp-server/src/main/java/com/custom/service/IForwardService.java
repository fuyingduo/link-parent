package com.custom.service;

import com.custom.entity.SendMessage;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * 转发服务
 * created by fuyd on 2019-07-17
 */
public interface IForwardService {

    /**
     * 消息推送
     */
    Boolean send(@Validated @NotBlank String payload);

    /**
     * 消息推送
     */
    Boolean send(@Validated SendMessage message);
}
