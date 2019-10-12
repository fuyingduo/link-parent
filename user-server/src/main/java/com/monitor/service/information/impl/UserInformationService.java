package com.monitor.service.information.impl;

import com.monitor.common.RedisService;
import com.monitor.constant.LoginConstants;
import com.monitor.dao.loginout.UserLoginRepository;
import com.monitor.entity.UserLogin;
import com.monitor.service.information.IUserInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * created by fuyd on 2019-07-03
 */
@Service
public class UserInformationService implements IUserInformation {

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private RedisService redisService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInformationService.class);

    @Override
    public Boolean updateLoginPassword(Integer loginId, String code, String newPass) {

        Optional<UserLogin> userLogin = userLoginRepository.findById(loginId);
        if (!userLogin.isPresent()) {
            LOGGER.error("[用户修改密码] 获取用户信息失败! loginId:{}", loginId);
            return false;
        }
        UserLogin user = userLogin.get();
        Optional<String> opl = redisService.get(LoginConstants.USER_PASS_CODE + user.getUserName(), String.class);
        if (!opl.isPresent() || !opl.get().equals(code)) {
            LOGGER.error("[用户修改密码] 验证码错误或不存在! code:{}", code);
            return false;
        }
        user.setPassWord(newPass);
        UserLogin save = userLoginRepository.save(user);
        return save.getPassWord().equals(newPass);
    }
}
