package com.custom.service;

import com.aliyun.oss.OSS;
import com.custom.core.FileProcessFactory;
import com.custom.core.OssProperties;
import com.custom.result.TransmissionResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;

/**
 * created by fuyd on 2019-11-01
 */
@Component
public class VideoFileProcess extends FileProcessFactory implements FileProcess {

    private final OSS ossClient;
    private final OssProperties ossProperties;
    private static final String OBJECT_NAME = "%s/%s";

    public VideoFileProcess(OSS ossClient, OssProperties ossProperties) {
        this.ossClient = ossClient;
        this.ossProperties = ossProperties;
    }

    @Override
    public TransmissionResult transmission(String fileName, String content) {
        if (StringUtils.isEmpty(fileName))
            throw new RuntimeException(" params fileName can_t empty ");
        if (StringUtils.isEmpty(content))
            throw new RuntimeException(" file can_t null ");

        String url = super.upload(this.objectName(fileName), content);
        TransmissionResult result = new TransmissionResult();
        result.setUrl(url);
        return result;
    }

    @Override
    public TransmissionResult transmission(String objectName, byte[] content) {
        return null;
    }

    @Override
    public TransmissionResult transmission(String fileName, InputStream inputStream) {
        if (StringUtils.isEmpty(fileName))
            throw new RuntimeException(" params fileName can_t empty ");
        if (null == inputStream)
            throw new RuntimeException(" inputStream can_t null ");

        String url = super.upload(this.objectName(fileName), inputStream);
        TransmissionResult result = new TransmissionResult();
        result.setUrl(url);
        return result;
    }

    @Override
    public TransmissionResult transmission(String objectName, File file) {
        return null;
    }

    @Override
    protected OSS OSSClient() {
        return ossClient;
    }

    @Override
    protected String bucketName() {
        return ossProperties.getOss().getBucketName();
    }

    /**
     * 获取objectName
     *
     * @param fileName 文件名称
     */
    private String objectName(String fileName) {
        String directory = ossProperties.getDirectory().getVideo();
        if (StringUtils.isEmpty(directory)) {
            throw new RuntimeException("video file upload err, directory is empty");
        }
        return String.format(OBJECT_NAME, directory, fileName);
    }
}
