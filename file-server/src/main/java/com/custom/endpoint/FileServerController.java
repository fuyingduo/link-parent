package com.custom.endpoint;

import com.custom.base.FeignResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * created by fuyd on 2019-06-14
 */
@RestController
@RequestMapping(value = "/file", name = "file server")
public class FileServerController {

    private static final Logger LOG = LoggerFactory.getLogger(FileServerController.class);

    @PostMapping(value = "/upload")
    public FeignResult upLoad(HttpServletRequest request) {
        return FeignResult.ok("success");
    }
}
