package com.monitor.common;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * created by fuyd on 2019-09-27
 */
@Aspect
@Component
public class LogAspect {

    @Value("${spring.application.name}")
    private String applicationName;

    private static final String CR_USERID = "cUserId";
    private static final String DEVICE = "device";
    private static final String DEVICE_ID = "deviceId";
    private static final String CLIENT = "type";
    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.monitor.common.Log)")
    public void logPoinCut() {
    }

    @AfterReturning("logPoinCut()")
    public void insertLog(JoinPoint joinPoint) {

        CacheLog cacheLog = new CacheLog();
        cacheLog.setApplicationName(applicationName);
        cacheLog.setCrDate(LocalDateTime.now());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        cacheLog.setCrUserId(null == request.getParameter(CR_USERID) ? null : Integer.valueOf(request.getParameter(CR_USERID)));
        cacheLog.setDeivce(request.getHeader(DEVICE));
        cacheLog.setDeviceId(request.getHeader(DEVICE_ID));
        cacheLog.setClient(request.getHeader(CLIENT));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        cacheLog.setRemark(log.value());
        JSONObject params = this.toJson(joinPoint.getArgs(), signature.getParameterNames());
        cacheLog.setParams(params.toJSONString());

        String className = joinPoint.getTarget().getClass().getName();
        cacheLog.setClassMethod(className + "." + method.getName());
        LOG.info(cacheLog.toString());
    }

    private JSONObject toJson(Object[] args, String[] parameterNames) {
        JSONObject params = new JSONObject();
        for (int i = 0; i < parameterNames.length; i++) {
            params.put(parameterNames[i], args[i]);
        }
        return params;
    }
}
