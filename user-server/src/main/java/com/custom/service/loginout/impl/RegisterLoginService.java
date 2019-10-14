package com.custom.service.loginout.impl;

import com.custom.dao.loginout.UserLoginRepository;
import com.custom.entity.UserLogin;
import com.custom.enums.WhetherEnum;
import com.custom.exception.HandleException;
import com.custom.jwt.Tokens;
import com.custom.params.login.ILoginUser;
import com.custom.params.login.RegisterLoginUser;
import com.custom.service.loginout.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户注册登陆
 * created by fuyd on 2019-07-04
 */
@Service("registerLoginService")
public class RegisterLoginService implements ILoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsLoginService.class);

    @Transactional
    @Override
    public String login(ILoginUser iLoginUser) {
        RegisterLoginUser loginUser = (RegisterLoginUser) iLoginUser;
        validation(loginUser);
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(loginUser.getUsername());
        userLogin.setPassWord(loginUser.getPassword());
        userLogin.setType(loginUser.getType());
        userLogin.setVisitJurisdiction(WhetherEnum.IS.code());
        UserLogin user = userLoginRepository.save(userLogin);
        return Tokens.builder()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setType(user.getType())
                .setVisitJurisdiction(user.getVisitJurisdiction())
                .build();
    }

    private void validation(RegisterLoginUser loginUser) {
        loginUser.check();
        UserLogin userName = userLoginRepository.findUserLoginByUserName(loginUser.getUsername());
        if (null != userName) {
            LOGGER.error("[注册登陆] 账号已被注册! userName:{}", userName);
            throw new HandleException(12001);
        }
    }
}
