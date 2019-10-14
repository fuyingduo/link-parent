package com.custom.service.loginout.impl;

import com.custom.dao.loginout.UserLoginRepository;
import com.custom.entity.UserLogin;
import com.custom.exception.HandleException;
import com.custom.jwt.Tokens;
import com.custom.params.login.ILoginUser;
import com.custom.params.login.PasswordLoginUser;
import com.custom.service.loginout.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by fuyd on 2019-06-16
 */
@Service("passwordLoginService")
public class PasswordLoginService implements ILoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsLoginService.class);

    @Override
    public String login(ILoginUser iLoginUser) {
        PasswordLoginUser loginUser = (PasswordLoginUser) iLoginUser;
        loginUser.check();
        UserLogin user = userLoginRepository.findUserLoginByUserName(loginUser.getUsername());
        if (null == user) {
            LOGGER.error("[密码登陆] 登陆用户不存在 username:{}", loginUser.getUsername());
            throw new HandleException(11008);
        }
        if (!user.getPassWord().equals(loginUser.getPassword())) {
            LOGGER.error("[密码登陆] 输入密码不符 password:{}", loginUser.getPassword());
            throw new HandleException(11009);
        }
        return Tokens.builder()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setType(user.getType())
                .setVisitJurisdiction(user.getVisitJurisdiction())
                .build();
    }
}
