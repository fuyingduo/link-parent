package com.custom.utils;

import com.custom.entity.RocketProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * created by fuyd on 2019-11-06
 */
public class RocketCheck {
    private static final Logger LOG = LoggerFactory.getLogger(RocketCheck.class);

    /**
     * 生产者参数校验
     *
     * @param rocketProperties 配置文件信息
     */
    public static boolean producer(RocketProperties rocketProperties) {
        return shared(rocketProperties);
    }

    /**
     * 消费者参数校验
     *
     * @param rocketProperties 配置文件信息
     */
    public static boolean consumer(RocketProperties rocketProperties) {
        if (!shared(rocketProperties)) {
            return false;
        }
        RocketProperties.Consumer consumer = rocketProperties.getConsumer();
        if (null == consumer) {
            RocketCheck.LOG.error("RockerMQ 启动失败 ...  原因:{无法读取到配置文件}");
            return false;
        }
        if (StringUtils.isEmpty(consumer.getTopic()) || StringUtils.isEmpty(consumer.getTag())) {
            RocketCheck.LOG.error("RockerMQ 启动失败 ...  原因:{}, {} 参数错误", consumer.getTopic(), consumer.getTag());
            return false;
        }
        return true;
    }

    /**
     * 公共部分校验
     *
     * @param rocketProperties 配置文件信息
     */
    private static boolean shared(RocketProperties rocketProperties) {
        if (null == rocketProperties) {
            RocketCheck.LOG.error("RockerMQ 启动失败 ...  原因:{无法读取到配置文件}");
            return false;
        }
        if (StringUtils.isEmpty(rocketProperties.getDefaultGroup())
                || StringUtils.isEmpty(rocketProperties.getNamesrvAddr())) {
            RocketCheck.LOG.error("RockerMQ 启动失败 ...  原因:{}, {} 参数错误",
                    rocketProperties.getDefaultGroup(), rocketProperties.getNamesrvAddr());
            return false;
        }
        return true;
    }

}
