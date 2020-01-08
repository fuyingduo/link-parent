package com.custom.core;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by fuyd on 2019-11-01
 */
@Configuration
@EnableConfigurationProperties(value = OssProperties.class)
public class Ossinitialize {

    private final OssProperties ossProperties;

    public Ossinitialize(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Bean
    public OSS ossClient() {
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        if (null != ossProperties.getOss().getMaxConnections()) {
            conf.setMaxConnections(ossProperties.getOss().getMaxConnections());
        }
        if (null != ossProperties.getOss().getSocketTimeout()) {
            conf.setSocketTimeout(ossProperties.getOss().getSocketTimeout());
        }
        if (null != ossProperties.getOss().getConnectionTimeout()) {
            conf.setConnectionTimeout(ossProperties.getOss().getConnectionTimeout());
        }
        return new OSSClientBuilder().build(ossProperties.getOss().getEndpoint(),
                ossProperties.getOss().getAccessKeyId(), ossProperties.getOss().getAccessKeySecret(), conf);
    }
}
