package com.monitor.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时器共享数据
 * created by fuyd on 2019-07-19
 */
public class RegisteredDataManagement {

    /**
     * 初始化定时器任务
     */
    private ScheduledTaskRegistrar taskRegistrar;

    /**
     * 已注册定时器信息
     */
    volatile private ConcurrentHashMap<String, String> hasBeenreistered;

    /**
     * 已注册定时器设置
     */
    volatile private ConcurrentHashMap<String, ScheduledFuture<?>> futures;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisteredDataManagement.class);

    /**
     * 初始化已注册信息
     *
     * @param taskRegistrar 注册实例
     */
    void setTaskRegistrar(ScheduledTaskRegistrar taskRegistrar) {
        this.getHasBeenreistered();
        this.getFutures();
        this.taskRegistrar = taskRegistrar;
    }

    /**
     * 获取注册实例
     */
    ScheduledTaskRegistrar getTaskRegistrar() {
        return taskRegistrar;
    }

    /**
     * 获取已注册定时器信息
     */
    ConcurrentHashMap<String, String> getHasBeenreistered() {
        if (null == hasBeenreistered) {
            hasBeenreistered = new ConcurrentHashMap<>();
        }
        return hasBeenreistered;
    }

    /**
     * 获取已注册定时器设置
     */
    ConcurrentHashMap<String, ScheduledFuture<?>> getFutures() {
        if (null == futures) {
            futures = new ConcurrentHashMap<>();
        }
        return futures;
    }

    /**
     * 定时器关闭
     *
     * @param taskId 唯一标识
     */
    boolean shutdown(String taskId) {
        ScheduledFuture<?> future = this.futures.get(taskId);
        if (null == future) {
            LOGGER.warn("[动态定时] 内存中没有taskId:{} 定时器在执行，无法停止!", taskId);
            return true;
        }
        if (!future.cancel(true)) {
            LOGGER.error("[动态定时] 尝试停止taskId:{} 定时器失败", taskId);
            return false;
        }
        this.hasBeenreistered.remove(taskId);
        this.futures.remove(taskId);
        RegisteredDataManagement.LOGGER.info("[动态定时] TaskId:{} 定时器成功关闭...", taskId);
        return true;
    }

}
