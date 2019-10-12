package com.monitor.feign.server_user;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * created by fuyd on 2019-09-26
 */
@FeignClient(name = "user-server", path = "/user/batch")
public interface UserBatchService {


}
