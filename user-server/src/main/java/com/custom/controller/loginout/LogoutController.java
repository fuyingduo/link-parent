package com.custom.controller.loginout;

import com.custom.base.BaseResult;
import com.custom.service.loginout.ILogoutService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登出
 * created by fuyd on 2019-07-02
 */
@Api(tags = "user_logout")
@RestController
@RequestMapping(value = "/user/logout")
public class LogoutController {

    @Autowired
    private ILogoutService logoutService;

    @ApiOperation(value = "登出", notes = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header"),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @GetMapping(value = "/logout")
    public BaseResult<Boolean> logout(@RequestHeader(value = "token") String token) {
        if (logoutService.logout(token)) {
            return BaseResult.ok(true);
        }
        return BaseResult.err("登出失败");
    }
}
