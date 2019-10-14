package com.custom.config;

import com.custom.callback.DefaultConfirmCallback;
import com.custom.callback.DefaultReutrnedCallback;
import com.custom.callback.IConfirmCallback;
import com.custom.callback.IReutrnedCallback;
import com.custom.entity.MQProperties;
import com.custom.listener.DefaultConnectionListener;
import com.custom.listener.DefaultConsumerListener;
import com.custom.listener.IChannelAwareMessageListener;
import com.custom.listener.IConnectionListener;
import com.custom.service.IForwardService;
import com.custom.service.impl.DefaultForwardService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认基础配置
 * created by fuyd on 2019-07-18
 */
@Configuration
@EnableConfigurationProperties(MQProperties.class)
public class DefaultConfiguration {

    /**
     * 默认确认回调接口实现
     *
     * @return DefaultConfirmCallback
     */
    @Bean
    @ConditionalOnMissingBean
    public IConfirmCallback iConfirmCallback() {
        return new DefaultConfirmCallback();
    }

    /**
     * 默认失败回调接口实现
     *
     * @return DefaultReutrnedCallback
     */
    @Bean
    @ConditionalOnMissingBean
    public IReutrnedCallback iReutrnedCallback() {
        return new DefaultReutrnedCallback();
    }

    /**
     * 默认MQ连接监听实现
     *
     * @return DefaultConnectionListener
     */
    @Bean
    @ConditionalOnMissingBean
    public IConnectionListener iConnectionListener() {
        return new DefaultConnectionListener();
    }

    /**
     * 默认回调接口监听实现
     *
     * @return DefaultConsumerListener
     */
    @Bean
    @ConditionalOnMissingBean
    public IChannelAwareMessageListener iChannelAwareMessageListener() {
        return new DefaultConsumerListener();
    }

    /**
     * 默认转发接口实现
     *
     * @return DefaultForwardService
     */
    @Bean
    @ConditionalOnMissingBean
    public IForwardService iForwardService() {
        return new DefaultForwardService();
    }
}
