package com.monitor.controller.loginout;

import com.monitor.base.BaseResult;
import com.monitor.common.RedisService;
import com.monitor.constant.LoginConstants;
import com.monitor.enums.WhetherEnum;
import com.monitor.params.login.PasswordLoginUser;
import com.monitor.params.login.RegisterLoginUser;
import com.monitor.params.login.SmsLoginUser;
import com.monitor.service.loginout.ILoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 * created by fuyd on 2019-06-14
 */
@Api(tags = "user_login")
@RestController
@RequestMapping("/user/login")
public class LoginController {

    @Autowired
    @Qualifier("smsLoginService")
    private ILoginService smsLoginService;
    @Autowired
    @Qualifier("passwordLoginService")
    private ILoginService passwordLoginService;
    @Autowired
    @Qualifier("registerLoginService")
    private ILoginService registerLoginService;
    @Autowired
    private RedisService<String> redisService;

    @ApiOperation(value = "短信登陆", notes = "短信登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "smsCode", value = "短信验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "干扰码", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @GetMapping(value = "/sms")
    public BaseResult<String> smslogin(String username, String smsCode, String code) {
        SmsLoginUser loginUser = new SmsLoginUser(username, smsCode, code);
        String token = smsLoginService.login(loginUser);
        redisService.set(LoginConstants.REQUEST_HEADER_KEY + username, token);
        return BaseResult.ok(token);
    }

    @ApiOperation(value = "密码登陆", notes = "密码登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "干扰码", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @GetMapping(value = "/password")
    public BaseResult<String> passwordLogin(String username, String password, String code) {
        PasswordLoginUser loginUser = new PasswordLoginUser(username, password, code);
        String token = passwordLoginService.login(loginUser);
        redisService.set(LoginConstants.REQUEST_HEADER_KEY + username, token);
        return BaseResult.ok(token);
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "smsCode", value = "短信验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "干扰码", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功")
    })
    @PostMapping(value = "/register")
    public BaseResult<String> registerLogin(String username, String password,
                                            String confirmPassword, String smsCode, String code) {
        RegisterLoginUser loginUser = new RegisterLoginUser(username,
                password, confirmPassword, smsCode, code, WhetherEnum.IS.code());
        String token = registerLoginService.login(loginUser);
        redisService.set(LoginConstants.REQUEST_HEADER_KEY + username, token);
        return BaseResult.ok(token);
    }
}
