package com.custom.controller.account;

import com.custom.base.BaseResult;
import com.custom.service.account.IUserAccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * created by fuyd on 2019-07-09
 */
@Api(tags = "user_account")
@RestController
@RequestMapping("/user/account")
public class AccountController {

    @Autowired
    private IUserAccountService iUserAccountService;

    @ApiOperation(value = "是否开通账户", notes = "是否开通账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @GetMapping(value = "/exists")
    public BaseResult<Boolean> accountExists(Integer loginId) {
        return BaseResult.ok(iUserAccountService.accountExists(loginId));
    }

    @ApiOperation(value = "创建账户", notes = "创建账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @PostMapping(value = "/create")
    public BaseResult<Integer> createAccount(Integer loginId) {
        Integer account = iUserAccountService.createAccount(loginId);
        if (null == account) {
            return BaseResult.err("操作失败");
        }
        return BaseResult.ok(account);
    }

    @ApiOperation(value = "查询账户余额", notes = "查询账户余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @GetMapping(value = "/balance")
    public BaseResult<Long> balance(Integer loginId) {
        Long balance = iUserAccountService.balance(loginId);
        if (null == balance) {
            return BaseResult.err("账户未开通");
        }
        return BaseResult.ok(balance);
    }

    @ApiOperation(value = "增加金额", notes = "增加金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "金额", required = true, paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @PutMapping(value = "/increase")
    public BaseResult<Long> increaseTheBalance(Long amount, Integer loginId) {
        Long aLong = iUserAccountService.increaseTheBalance(loginId, amount);
        if (null == aLong) {
            return BaseResult.err("操作失败");
        }
        return BaseResult.ok(aLong);
    }

    @ApiOperation(value = "减少金额", notes = "减少金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "金额", required = true, paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @PutMapping(value = "/reduce")
    public BaseResult<Long> reduceTheBalance(Long amount, Integer loginId) {
        Long aLong = iUserAccountService.reduceTheBalance(loginId, amount);
        if (null == aLong) {
            return BaseResult.err("操作失败");
        }
        return BaseResult.ok(aLong);
    }

}
