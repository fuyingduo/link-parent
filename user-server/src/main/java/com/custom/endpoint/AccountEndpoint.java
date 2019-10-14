package com.custom.endpoint;

import com.custom.base.FeignResult;
import com.custom.service.account.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by fuyd on 2019-09-23
 */
@RestController
@RequestMapping("/endpoint/user/account")
public class AccountEndpoint {

    @Autowired
    private IUserAccountService iUserAccountService;

    @GetMapping(value = "/exists")
    public FeignResult accountExists(Integer loginId) {
        return FeignResult.ok(iUserAccountService.accountExists(loginId));
    }

    @GetMapping(value = "/balance")
    public FeignResult balance(Integer loginId) {
        Long balance = iUserAccountService.balance(loginId);
        if (null == balance) {
            return FeignResult.err(-1, "账户未开通");
        }
        return FeignResult.ok(balance);
    }
}
