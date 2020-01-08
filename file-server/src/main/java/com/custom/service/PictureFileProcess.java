package com.custom.service;

import com.aliyun.oss.OSS;
import com.custom.core.FileProcessFactory;
import com.custom.result.TransmissionResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * created by fuyd on 2019-11-01
 */
@Component
public class PictureFileProcess extends FileProcessFactory implements FileProcess {
    @Override
    protected OSS OSSClient() {
        return null;
    }

    @Override
    protected String bucketName() {
        return null;
    }

    @Override
    public TransmissionResult transmission(String objectName, String content) {
        return null;
    }

    @Override
    public TransmissionResult transmission(String objectName, byte[] content) {
        return null;
    }

    @Override
    public TransmissionResult transmission(String objectName, InputStream inputStream) {
        return null;
    }

    @Override
    public TransmissionResult transmission(String objectName, File file) {
        return null;
    }
}
