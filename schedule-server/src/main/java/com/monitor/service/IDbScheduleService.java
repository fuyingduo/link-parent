package com.monitor.service;

/**
 * 定时器接口
 * created by fuyd on 2019-07-19
 */
public interface IDbScheduleService {


    /**
     * 添加定时器
     * 默认自动注册定时器，添加后立即生效，不需手动启用
     *
     * @param tag          标签
     *                     生成TaskId必要参数
     * @param expression   cron表达式
     * @param iRunnable    业务线程接口
     * @param introduction 简介
     * @return TaskId 唯一标识 更新/删除/关闭定时器必要参数
     */
    String addTimer(String tag, String expression, IRunnable iRunnable, String introduction);

    /**
     * 更新定时器
     * 默认更新定时器表达式, 必须提前关闭定时器否则更新失败{@see shutdownTimer}
     * 更新完成后默认定时器为关闭状态，需要手动启用
     *
     * @param taskId     定时器id
     *                   更新/删除/关闭定时器必要参数
     * @param expression 定时器表达式
     * @return TaskId 唯一标识
     */
    String updateTimer(String taskId, String expression);

    /**
     * 启用定时器
     * 在已关闭定时器中查找指定TaskId定时器并启用
     *
     * @param taskId 唯一标识
     *               更新/删除/关闭定时器必要参数
     * @return success/full
     */
    Boolean enableTimer(String taskId) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    /**
     * 禁用定时器
     * 在已启用定时器中查找指定TaskId定时器并关闭
     *
     * @param taskId 唯一标识
     *               更新/删除/关闭定时器必要参数
     * @return success/full
     */
    Boolean shutdownTimer(String taskId);

    /**
     * 删除定时器
     * 必须提前关闭定时器否则删除失败 {@see shutdownTimer}
     *
     * @param taskId 唯一标识
     *               更新/删除/关闭定时器必要参数
     * @return success/full
     */
    Boolean deleteTimer(String taskId);
}
