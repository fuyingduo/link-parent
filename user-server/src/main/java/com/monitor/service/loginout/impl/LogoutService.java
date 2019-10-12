package com.monitor.service.loginout.impl;

import com.monitor.common.RedisService;
import com.monitor.constant.LoginConstants;
import com.monitor.jwt.DefaultTokenBuilder;
import com.monitor.service.loginout.ILogoutService;
import com.monitor.util.JwtUtil;
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
