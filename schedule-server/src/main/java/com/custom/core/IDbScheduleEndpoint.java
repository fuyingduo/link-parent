package com.custom.core;

import com.custom.entity.DbSchedule;
import com.custom.service.IRunnable;

import java.util.List;

/**
 * created by fuyd on 2019-10-12
 */
interface IDbScheduleEndpoint {

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
