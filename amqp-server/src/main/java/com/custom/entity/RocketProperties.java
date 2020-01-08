package com.custom.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * created by fuyd on 2019-11-06
 */
@ConfigurationProperties(prefix = "rocket")
public class RocketProperties {

    /**
     * 组名称
     */
    private String defaultGroup;
    /**
     * 名称空间
     */
    private String nameSpace = "DEFAULT_TEST";
    /**
     * nameser地址
     */
    private String namesrvAddr;
    /**
     * 实例名称
     */
    private String instanceName = "DEFAUL_TEST";
    /**
     * 生产者配置
     */
    private Producer producer;
    /**
     * 消费者配置
     */
    private Consumer consumer;

    public static class Consumer {
        /**
         * 主题
         */
        private String topic;
        /**
         * 标签
         */
        private String tag;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    public static class Producer {
        /**
         * 压缩阀值
         */
        private int compressMsgBodyOverHowmuch = 4 * 1000;

        public int getCompressMsgBodyOverHowmuch() {
            return compressMsgBodyOverHowmuch;
        }

        public void setCompressMsgBodyOverHowmuch(int compressMsgBodyOverHowmuch) {
            this.compressMsgBodyOverHowmuch = compressMsgBodyOverHowmuch;
        }
    }

    public String getDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(String defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
