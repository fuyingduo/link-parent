package com.monitor.service.impl;

import com.monitor.common.Querys;
import com.monitor.core.IRunnable;
import com.monitor.core.RegisteredDataManagement;
import com.monitor.core.RegistrerParams;
import com.monitor.dao.DbScheduleRepository;
import com.monitor.entity.DbSchedule;
import com.monitor.enums.TaskStatusEnum;
import com.monitor.service.IDbScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import static java.util.stream.Collectors.toList;

/**
 * created by fuyd on 2019-07-19
 */
public class DbScheduleService implements IDbScheduleService {

    @Autowired
    private RegisteredDataManagement scheduleInitializeSharedData;
    @Autowired
    private DbScheduleRepository dbScheduleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DbScheduleService.class);

    @Override
    public Boolean addTimer(RegistrerParams registrerModel, IRunnable iRunnable) {
        String taskId = registrerModel.getTaskId();
        if (null != dbScheduleRepository.findDbScheduleByTaskIdAndStatus(taskId, TaskStatusEnum.ENABLE.code())) {
            LOGGER.warn("[动态定时] 已被注册 TaskId:{}", taskId);
            return false;
        }
        DbSchedule dbSchedule = new DbSchedule();
        BeanUtils.copyProperties(registrerModel, dbSchedule);
        this.insertDb(dbSchedule, iRunnable);
        dynamicRegistration(iRunnable, registrerModel, true);
        return true;
    }

    @Override
    public Boolean addOrUpdateTimer(RegistrerParams registrerModel, IRunnable iRunnable) {
        String taskId = registrerModel.getTaskId();
        DbSchedule dbSchedule = dbScheduleRepository.findDbScheduleByTaskId(taskId);
        if (null == dbSchedule) {
            dbSchedule = new DbSchedule();
        }
        this.scheduleInitializeSharedData.shutdown(taskId);
        BeanUtils.copyProperties(registrerModel, dbSchedule);
        this.insertDb(dbSchedule, iRunnable);
        dynamicRegistration(iRunnable, registrerModel, true);
        return true;
    }

    @Override
    public Boolean shutdownTimer(String taskId) {
        DbSchedule dbSchedule = this.dbScheduleRepository.findDbScheduleByTaskIdAndStatus(taskId, TaskStatusEnum.ENABLE.code());
        if (null != dbSchedule) {
            dbSchedule.setStatus(TaskStatusEnum.DISABLE.code());
            this.dbScheduleRepository.save(dbSchedule);
        }
        return this.scheduleInitializeSharedData.shutdown(taskId);
    }

    @Override
    public List<DbSchedule> availableTimerHasCorrect(Boolean hasCorrect) {

        List<DbSchedule> dbSchedules = this.dbHasBeenRegistereds();
        if (!hasCorrect) {
            return dbSchedules;
        }

        List<String> hasBeenRegistereds = this.hasBeenRegistereds();
        if (CollectionUtils.isEmpty(hasBeenRegistereds)) {
            List<DbSchedule> disableAll = dbSchedules.stream()
                    .peek(e -> e.setStatus(TaskStatusEnum.DISABLE.code())).collect(toList());
            this.dbScheduleRepository.saveAll(disableAll);
        }

        List<DbSchedule> correcteds = dbSchedules.stream().filter(schedule -> {
            if (hasBeenRegistereds.contains(schedule.getTaskId())) {
                return true;
            }
            schedule.setStatus(TaskStatusEnum.DISABLE.code());
            this.dbScheduleRepository.save(schedule);
            return false;
        }).collect(toList());
        DbScheduleService.LOGGER.info("[启用定时器] 请求完成...");
        return correcteds;
    }

    @Override
    public void dynamicRegistration(IRunnable iRunnable, RegistrerParams model, Boolean hasPersistent) {
        if (!this.parameterCalibration(model)) {
            LOGGER.error("[动态定时] 注册失败! 传入参数校验失败...");
            return;
        }

        ThreadPoolTaskScheduler scheduler = (ThreadPoolTaskScheduler) scheduleInitializeSharedData.getTaskRegistrar().getScheduler();
        if (null == scheduler) {
            LOGGER.error("[动态定时] 初始化线程池失败...");
            return;
        }
        String expression = scheduleInitializeSharedData.getHasBeenreistered().get(model.getTaskId());
        if (!StringUtils.isEmpty(expression)) {
            if (expression.equals(model.getExpression())) {
                return;
            }
            if (!scheduleInitializeSharedData.shutdown(model.getTaskId())) {
                return;
            }
        }
        ScheduledFuture<?> future = scheduler.schedule(iRunnable, new CronTrigger(model.getExpression()));
        if (null != future) {
            scheduleInitializeSharedData.getFutures().put(model.getTaskId(), future);
        }
        scheduleInitializeSharedData.getHasBeenreistered().put(model.getTaskId(), model.getExpression());
        LOGGER.info("[动态定时] 注册成功! TaskId:{}, Expression:{}", model.getTaskId(), model.getExpression());
    }

    /**
     * 插入数据库
     *
     * @param dbSchedule 定时器配置
     * @param iRunnable  业务逻辑接口
     */
    private void insertDb(DbSchedule dbSchedule, IRunnable iRunnable) {
        dbSchedule.setStatus(TaskStatusEnum.ENABLE.code());
        dbSchedule.setPerformClass(iRunnable.getClass().getName());
        dbSchedule.setUpDate(LocalDateTime.now());
        dbScheduleRepository.save(dbSchedule);
    }

    /**
     * 参数校验
     *
     * @param registrerModel 注册信息
     * @return success/fail
     */
    private boolean parameterCalibration(RegistrerParams registrerModel) {
        if (null == registrerModel) {
            LOGGER.error("[动态定时] 参数校验失败!");
            return false;
        }
        if (StringUtils.isEmpty(registrerModel.getTaskId())) {
            LOGGER.error("[动态定时] 参数校验失败! TaskId{NULL}");
            return false;
        }
        return !StringUtils.isEmpty(registrerModel.getExpression());
    }

    /**
     * 定时器中已注册
     *
     * @return List
     */
    private List<String> hasBeenRegistereds() {
        return new ArrayList<>(scheduleInitializeSharedData.getHasBeenreistered().keySet());
    }

    /**
     * 数据库中已启用
     *
     * @return List
     */
    private List<DbSchedule> dbHasBeenRegistereds() {
        DbSchedule dbSchedule = new DbSchedule();
        dbSchedule.setStatus(TaskStatusEnum.ENABLE.code());
        Example<DbSchedule> example = Querys.builder().build(dbSchedule);
        return dbScheduleRepository.findAll(example);
    }
}
