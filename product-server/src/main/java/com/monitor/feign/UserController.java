package com.monitor.feign;

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
    private UserService userService;

    @RequestMapping(value = "/findUser/{id}")
    public String findUser(@PathVariable String id) throws InterruptedException, ExecutionException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> user1 = userService.find(id);
        System.out.println(user1.get());
        context.close();
        return user1.get();
    }

    @RequestMapping(value = "/cache/{id}")
    public String findCache(@PathVariable String id) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        for(int i = 0; i < 3; i++) {
            //调用同样的接口，调用接口才会走缓存
            String cache = userService.cache(i + "");
            System.out.println(cache);
        }
        context.close();
        return id;
    }

    @RequestMapping(value = "/cache/get/{id}")
    public String getchache(@PathVariable String id) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        return userService.cache(id);
    }

}
