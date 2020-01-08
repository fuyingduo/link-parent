package com.custom.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by fuyd on 2020-01-02
 */
@Configuration
public class FeignClientsConfig {

    @Bean
    public Request.Options options() {
        return new Request.Options(50 * 1000, 120 * 1000);
    }
}
