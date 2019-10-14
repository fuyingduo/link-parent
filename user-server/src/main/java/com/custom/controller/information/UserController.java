package com.custom.controller.information;

import com.custom.common.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fuyd on 2019-09-26
 */
@RestController
public class UserController {


    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //根据id获得单个User
    @Log(value = "用户信息")
    @RequestMapping(value = "/getUser/{id}")
    public String getUserById(@PathVariable String id, Integer cSchoolId, Integer cUserId) {
        logger.info("getUserById，它的id:" + id);
        return "hdn";
    }

    //获得多个user
    @Log(value = "用户信息")
    @RequestMapping(value = "/getUsers")
    public List<String> getUsers(@RequestParam("ids") List<String> ids) {
        logger.info("getUsers，它的ids:" + ids);
        return new ArrayList<>(ids);
    }

}
