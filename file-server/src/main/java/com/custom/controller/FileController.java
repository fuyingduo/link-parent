package com.custom.controller;

import com.custom.base.BaseResult;
import com.custom.result.TransmissionResult;
import com.custom.service.VideoFileProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * created by fuyd on 2019-11-01
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private VideoFileProcess videoFileProcess;

    @PostMapping(value = "/upload")
    public BaseResult<TransmissionResult> upload(MultipartFile file) throws IOException {
        TransmissionResult url = videoFileProcess.transmission(file.getOriginalFilename(), file.getInputStream());
        return BaseResult.ok(url);
    }
}
