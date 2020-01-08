package com.custom.service;

import com.custom.result.TransmissionResult;

import java.io.File;
import java.io.InputStream;

/**
 * created by fuyd on 2019-11-01
 */
public interface FileProcess {

    /**
     * 字符串上传
     *
     * @param objectName 存储路径
     * @param content    字符串
     */
    TransmissionResult transmission(String objectName, String content);

    /**
     * byte数组上传
     *
     * @param objectName 存储路径
     * @param content    byte数组
     */
    TransmissionResult transmission(String objectName, byte[] content);

    /**
     * 流上传
     *
     * @param objectName  存储路径
     * @param inputStream stream流
     */
    TransmissionResult transmission(String objectName, InputStream inputStream);

    /**
     * 文件上传
     *
     * @param objectName 存储路径
     * @param file       文件
     */
    TransmissionResult transmission(String objectName, File file);
}
