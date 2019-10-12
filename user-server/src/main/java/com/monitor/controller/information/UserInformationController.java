package com.monitor.controller.information;

import com.monitor.base.BaseResult;
import com.monitor.service.information.IUserInformation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户个人信息
 * created by fuyd on 2019-07-03
 */
@Api(tags = "user_information")
@RestController
@RequestMapping("/user/information")
public class UserInformationController {

    @Autowired
    private IUserInformation iUserInformation;

    @ApiOperation(value = "修改登录密码", notes = "修改登录密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "newPass", value = "新密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 101, message = "请重新登陆")
    })
    @PutMapping(value = "/password")
    public BaseResult<Boolean> updatePassword(String code, String newPass, Integer loginId) {
        if (iUserInformation.updateLoginPassword(loginId, code, newPass)) {
            return BaseResult.ok(true);
        }
        return BaseResult.err("修改失败");
    }
}
