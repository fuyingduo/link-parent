package com.monitor.common;

import java.lang.annotation.*;

/**
 * @Target({ElementType.METHOD, ElementType.TYPE}) 注解可作用在类/接口，方法上
 * @Retention(RetentionPolicy.RUNTIME) 运行时注解
 * @Inherited 可被子类继承
 * created by fuyd on 2019-09-27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Log {

    String value() default "";
}
