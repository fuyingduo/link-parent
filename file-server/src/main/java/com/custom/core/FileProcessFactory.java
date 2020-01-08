package com.custom.core;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * 文件处理工厂类
 * created by fuyd on 2019-11-01
 */
public abstract class FileProcessFactory {
    /**
     * 字符串上传
     *
     * @param objectName 上传OSS完整路径
     * @param content    上传对象
     * @return PutObjectRequest
     */
    protected String upload(String objectName, String content) {
        if (StringUtils.isEmpty(content))
            throw new RuntimeException("content is null");

        this.check(objectName);
        PutObjectRequest request = new PutObjectRequest(this.bucketName(), objectName, new ByteArrayInputStream(content.getBytes()));
        this.OSSClient().putObject(request);
        return objectName;
    }

    /**
     * byte数组上传
     *
     * @param objectName 上传OSS完整路径
     * @param content    上传对象
     * @return PutObjectRequest
     */
    protected String upload(String objectName, byte[] content) {
        if (null == content || 0 == content.length)
            throw new RuntimeException("content is null");

        this.check(objectName);
        PutObjectRequest request = new PutObjectRequest(this.bucketName(), objectName, new ByteArrayInputStream(content));
        this.OSSClient().putObject(request);
        return objectName;
    }

    /**
     * 流文件上传
     *
     * @param objectName  上传OSS完整路径
     * @param inputStream 上传对象
     * @return PutObjectRequest
     */
    protected String upload(String objectName, InputStream inputStream) {
        if (null == inputStream)
            throw new RuntimeException("InputStream is null");

        this.check(objectName);
        PutObjectRequest request = new PutObjectRequest(this.bucketName(), objectName, inputStream);
        this.OSSClient().putObject(request);
        return objectName;
    }

    /**
     * 文件上传
     *
     * @param objectName 上传OSS完整路径
     * @param file       上传对象
     * @return PutObjectRequest
     */
    protected String upload(String objectName, File file) {
        if (null == file)
            throw new RuntimeException("file file null");

        this.check(objectName);
        PutObjectRequest request = new PutObjectRequest(this.bucketName(), objectName, file);
        this.OSSClient().putObject(request);
        return objectName;
    }

    /**
     * 必传项校验
     *
     * @param objectName 文件地址
     */
    private void check(String objectName) {
        if (StringUtils.isEmpty(objectName))
            throw new RuntimeException("objectName is empty");

        if (null == this.OSSClient())
            throw new RuntimeException("OSSClient is null");

        if (StringUtils.isEmpty(this.bucketName()))
            throw new RuntimeException("bucketName is empty");
    }

    protected abstract OSS OSSClient();

    protected abstract String bucketName();
}
