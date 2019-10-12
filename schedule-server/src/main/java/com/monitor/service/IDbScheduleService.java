package com.monitor.service;

import com.monitor.core.IRunnable;
import com.monitor.core.RegistrerParams;
import com.monitor.entity.DbSchedule;

import java.util.List;

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
     * 删除定时器
     *
     * @param taskId 唯一标识
     * @return success/full
     */
    Boolean shutdownTimer(String taskId);

    /**
     * 查询所有可用定时器
     *
     * @param hasCorrect 是否纠正数据库状态
     *                   {hasCorrect为true}查询实际已经注册定时器并纠正数据库中定时器状态
     * @return List
     */
    List<DbSchedule> availableTimerHasCorrect(Boolean hasCorrect);

    /**
     * 动态注册定时器
     *
     * @param iRunnable     线程接口
     * @param model         注册信息
     * @param hasPersistent 是否持久化
     */
    void dynamicRegistration(IRunnable iRunnable, RegistrerParams model, Boolean hasPersistent);
}
