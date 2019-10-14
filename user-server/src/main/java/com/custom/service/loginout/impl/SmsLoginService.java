package com.custom.service.loginout.impl;

import com.custom.dao.loginout.UserLoginRepository;
import com.custom.entity.UserLogin;
import com.custom.exception.HandleException;
import com.custom.jwt.Tokens;
import com.custom.params.login.ILoginUser;
import com.custom.params.login.SmsLoginUser;
import com.custom.service.loginout.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by fuyd on 2019-06-16
 */
@Service("smsLoginService")
public class SmsLoginService implements ILoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsLoginService.class);

    @Override
    public String login(ILoginUser iLoginUser) {
        SmsLoginUser loginUser = (SmsLoginUser) iLoginUser;
        loginUser.check();
        if (!"666666".equals(loginUser.getCode())) {
            LOGGER.error("[短信登陆] 短信验证码错误 code:{}", loginUser.getCode());
            throw new HandleException(11007);
        }
        UserLogin user = userLoginRepository.findUserLoginByUserName(loginUser.getUsername());
        if (null == user) {
            LOGGER.error("[短信登陆] 用户不存在 username:{}", loginUser.getUsername());
            throw new HandleException(11008);
        }
        return Tokens.builder()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setType(user.getType())
                .setVisitJurisdiction(user.getVisitJurisdiction())
                .build();
    }
}
