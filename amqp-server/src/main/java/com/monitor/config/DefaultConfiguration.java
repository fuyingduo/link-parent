package com.monitor.config;

import com.monitor.callback.DefaultConfirmCallback;
import com.monitor.callback.DefaultReutrnedCallback;
import com.monitor.callback.IConfirmCallback;
import com.monitor.callback.IReutrnedCallback;
import com.monitor.entity.MQProperties;
import com.monitor.listener.DefaultConnectionListener;
import com.monitor.listener.DefaultConsumerListener;
import com.monitor.listener.IChannelAwareMessageListener;
import com.monitor.listener.IConnectionListener;
import com.monitor.service.IForwardService;
import com.monitor.service.impl.DefaultForwardService;
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
