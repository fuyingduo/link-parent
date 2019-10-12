package com.monitor.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

/**
 * created by fuyd on 2019-09-26
 */
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;

    //请求合并的方法
    @HystrixCollapser(batchMethod = "findAll", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "5000")
    })
    public Future<String> find(String id) {
        //这里本应该访问服务提供者提供的/getUser/{id}接口
        logger.info("======执行了find方法========");
        return null;
    }


    //合并请求之后调用的方法
    @HystrixCommand
    public List<String> findAll(List<String> ids) {
        System.out.println("finaAll request:---------" + ids + "Thread.currentThread().getName():-------" + Thread.currentThread().getName());
        return restTemplate.getForObject("http://user-server/getUsers?ids={1}", List.class, StringUtils.join(ids, ","));
    }

    @CacheResult
    @HystrixCommand(commandKey = "cache")
    public String cache(String id) {
        System.out.println(1111);
        return id;
    }

}
