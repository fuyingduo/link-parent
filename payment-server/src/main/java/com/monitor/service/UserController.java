package com.monitor.service;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * created by fuyd on 2019-09-26
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/findUser/{id}")
    public String findUser(@PathVariable String id) throws InterruptedException, ExecutionException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> user1 = userService.find(id);
        Future<String> defaultUser = userService.find("888");
        System.out.println(user1.get());
        System.out.println(defaultUser.get());
        context.close();
        return "SUCCESS";
    }

}
