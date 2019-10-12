package com.monitor.controller.address;

import com.monitor.base.BaseResult;
import com.monitor.common.Log;
import com.monitor.entity.UserAddress;
import com.monitor.service.address.IUserAddressService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 用户收货地址
 * created by fuyd on 2019-07-02
 */
@Api(tags = "user_address")
@RestController
@RequestMapping("/user/address/qr")
public class UserAddressController {

    @Autowired
    private IUserAddressService iUserAddressService;

    @ApiOperation(value = "用户地址列表", notes = "用户地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "用户id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 101, message = "请重新登陆")
    })
    @Log(value = "用户地址列表")
    @GetMapping(value = "/list")
    public BaseResult<Page<UserAddress>> addressList(Integer loginId, Integer page) {
        return BaseResult.ok(iUserAddressService.userAddresss(loginId, page));
    }

    @ApiOperation(value = "用户默认收货地址", notes = "用户默认收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "用户id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 101, message = "请重新登陆")
    })
    @GetMapping(value = "/default")
    public BaseResult<UserAddress> defaultAddress(Integer loginId) {
        return BaseResult.ok(iUserAddressService.userAddressDefault(loginId));
    }

    @ApiOperation(value = "地址详情", notes = "地址详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "地址id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 101, message = "请重新登陆")
    })
    @GetMapping(value = "/info/{id}")
    public BaseResult<UserAddress> addressInfo(@PathVariable(value = "id") Integer id) {
        return BaseResult.ok(iUserAddressService.userAddressInfo(id));
    }

    @ApiOperation(value = "添加收货地址", notes = "添加收货地址")
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 101, message = "请重新登陆")
    })
    @PostMapping(value = "/add")
    public BaseResult<UserAddress> addAddress(UserAddress userAddress) {
        return BaseResult.ok(iUserAddressService.insertUserAddress(userAddress));
    }

    @ApiOperation(value = "删除收货地址", notes = "删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "地址id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 101, message = "请重新登陆")
    })
    @DeleteMapping(value = "/delete/{id}")
    public BaseResult<Boolean> deleteUserAddress(@PathVariable(value = "id") Integer id, Integer loginId) {
        iUserAddressService.deleteUserAddress(id, loginId);
        return BaseResult.ok(true);
    }

    @ApiOperation(value = "更新收货地址", notes = "更新收货地址")
    @ApiResponses({
            @ApiResponse(code = 0, message = "请求成功"),
            @ApiResponse(code = 101, message = "请重新登陆")
    })
    @PutMapping(value = "/edit")
    public BaseResult<UserAddress> updateUserAddress(UserAddress userAddress) {
        return BaseResult.ok(iUserAddressService.updateUserAddress(userAddress));
    }
}
