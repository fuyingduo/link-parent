//package com.monitor.config.condition;
//
//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//
//import java.util.Collections;
//import java.util.List;
//
///**
// * created by fuyd on 2019-10-10
// */
//public class  SecurityPermitAllCondition implements Condition {
//
//    /**
//     * 忽略创建bean
//     */
//    private static final List<String> INTERCEPTS = Collections.singletonList("school-zuul-proxy");
//
//    /**
//     * 加载服务名
//     */
//    private static final String APPLICATION_KEY = "spring.application.name";
//
//    @Override
//    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        return !INTERCEPTS.contains(context.getEnvironment().getProperty(APPLICATION_KEY));
//    }
//}
