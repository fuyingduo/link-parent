package com.custom.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    RestTemplate restTemplate;

    //请求合并的方法
    @HystrixCollapser(batchMethod = "findAll",collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "3000")})
    public Future<String> find(final String id){
        //这里本应该访问服务提供者提供的/getUser/{id}接口
        logger.info("======执行了find方法========");
        return null;
    }



    //合并请求之后调用的方法
    @HystrixCommand
    public List<String> findAll(final List<String> ids){
        logger.info("======执行了findAll方法========"+ids);
        return restTemplate.getForObject("http://hello-service/getUsers?ids={1}", List.class, StringUtils.join(ids, ","));
    }

}
