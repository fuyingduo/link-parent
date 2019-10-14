package com.custom.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * created by fuyd on 2019-07-08
 */
@Configuration
public class UserIDAuditorBean implements AuditorAware<Integer> {

    @Autowired
    private HttpServletRequest request;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserIDAuditorBean.class);

    @Override
    public Optional<Integer> getCurrentAuditor() {
        String loginId = request.getParameter("loginId");
        LOGGER.info("[审计功能] 当前用户loginId:{}", loginId);
        if (null == loginId) {
            return Optional.empty();
        }
        return Optional.of(Integer.valueOf(loginId));
    }
}
