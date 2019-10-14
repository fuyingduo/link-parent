package com.custom.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * created by fuyd on 2019-06-19
 */
@Component
public class ApplicationContextManage implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 获取对象通过Class
     *
     * @param cls
     * @return Object
     * @throws BeansException
     */
    public static <C> Object getBean(Class<C> cls) throws BeansException {
        return applicationContext.getBean(cls);
    }

    /**
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextManage.applicationContext = applicationContext;

    }
}
