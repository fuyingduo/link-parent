package com.custom.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * created by fuyd on 2019-11-01
 */
@ConfigurationProperties(prefix = "link")
public class OssProperties {

    private Oss oss;
    private Directory directory;

    public Oss getOss() {
        return oss;
    }

    public void setOss(Oss oss) {
        this.oss = oss;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    /**
     * ali OSS 配置文件
     */
    public static class Oss {
        // 端点名称
        private String endpoint;
        // oss key
        private String accessKeyId;
        // oss secret
        private String accessKeySecret;
        // 存储空间
        private String bucketName;
        // 最大连接数
        private Integer maxConnections;
        // socket超市时间
        private Integer socketTimeout;
        // 天街超市时间
        private Integer connectionTimeout;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public Integer getMaxConnections() {
            return maxConnections;
        }

        public void setMaxConnections(Integer maxConnections) {
            this.maxConnections = maxConnections;
        }

        public Integer getSocketTimeout() {
            return socketTimeout;
        }

        public void setSocketTimeout(Integer socketTimeout) {
            this.socketTimeout = socketTimeout;
        }

        public Integer getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(Integer connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }
    }

    /**
     * 文件上传目录
     */
    public static class Directory {
        // 视频目录
        private String video;
        // 音频目录
        private String audio;
        // 图片目录
        private String picture;
        // 文档目录
        private String document;

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }
    }
}
