package com.monitor.service;

import com.monitor.core.RegistrerParams;

/**
 * 定时器接口
 * created by fuyd on 2019-07-19
 */
public interface IDbScheduleService {

    /**
     * 添加定时器
     *
     * @param registrerModel 请求参数
     * @param iRunnable      业务逻辑接口
     * @return success/full
     */
    Boolean addTimer(RegistrerParams registrerModel, IRunnable iRunnable);

    /**
     * 修改定时器
     *
     * @param registrerModel 请求参数
     * @param iRunnable      业务逻辑接口
     * @return success/full
     */
    Boolean addOrUpdateTimer(RegistrerParams registrerModel, IRunnable iRunnable);

    /**
     * 启用定时器
     *
     * @param taskId 唯一标识
     * @return success/full
     */
    Boolean enableTimer(String taskId) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    /**
     * 删除定时器
     *
     * @param taskId 唯一标识
     * @return success/full
     */
    Boolean shutdownTimer(String taskId);
}
