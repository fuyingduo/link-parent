package com.custom.service.loginout.impl;

import com.custom.common.RedisService;
import com.custom.constant.LoginConstants;
import com.custom.jwt.DefaultTokenBuilder;
import com.custom.service.loginout.ILogoutService;
import com.custom.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by fuyd on 2019-07-02
 */
@Service
public class LogoutService implements ILogoutService {

    @Autowired
    private RedisService redisService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutService.class);

    @Override
    public boolean logout(String token) {
        DefaultTokenBuilder tokenInfo = JwtUtil.validateToken(token);
        if (null == tokenInfo) {
            LOGGER.error("[用户登出] 解析token失败");
            return false;
        }
        return redisService.delete(LoginConstants.REQUEST_HEADER_KEY + tokenInfo.getUserName());
    }
}
