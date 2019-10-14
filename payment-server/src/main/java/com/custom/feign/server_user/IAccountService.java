package com.custom.feign.server_user;

import com.custom.base.FeignResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户账户服务
 * created by fuyd on 2019-09-23
 */
@FeignClient(name = "user-server", path = "/endpoint/user/account")
public interface IAccountService {

    @GetMapping(value = "/exists", name = "账户是否存在")
    FeignResult accountExists(@RequestParam(value = "loginId") Integer loginId);

    @GetMapping(value = "/balance", name = "账户余额")
    FeignResult balance(@RequestParam(value = "loginId") Integer loginId);
}
