package com.custom.controller;

import com.custom.base.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by fuyd on 2019-10-16
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public BaseResult<String> findString() {
        return BaseResult.ok("1");
    }
}
