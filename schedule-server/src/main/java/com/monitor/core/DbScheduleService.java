package com.monitor.core;

import com.monitor.common.Querys;
import com.monitor.dao.DbScheduleRepository;
import com.monitor.entity.DbSchedule;
import com.monitor.enums.TaskStatusEnum;
import com.monitor.enums.TimerExceptionEnum;
import com.monitor.exception.TimerException;
import com.monitor.service.IDbScheduleService;
import com.monitor.service.IRunnable;
import com.monitor.utils.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.transaction.annotation.Transactional;
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
public class DbScheduleService implements IDbScheduleService, IDbScheduleEndpoint {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private RegisteredDataManagement scheduleInitializeSharedData;
    @Autowired
    private DbScheduleRepository dbScheduleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DbScheduleService.class);

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addTimer(String tag, String expression, IRunnable iRunnable, String introduction) {

        if (StringUtils.isEmpty(tag))
            throw new TimerException(TimerExceptionEnum.TAG_NONE_EMPTY.code());

        if (StringUtils.isEmpty(expression))
            throw new TimerException(TimerExceptionEnum.TAG_NONE_EMPTY.code());

        String taskId = TaskUtil.generate(applicationName, tag, expression);
        if (null != dbScheduleRepository.findDbScheduleByTaskId(taskId))
            throw new TimerException(TimerExceptionEnum.PRIMARY_KEY_REPEAT.code());

        DbSchedule dbSchedule = new DbSchedule();
        dbSchedule.setTaskId(taskId);
        dbSchedule.setExpression(expression);
        dbSchedule.setIntroduction(introduction);
        dbSchedule.setPerformClass(iRunnable.getClass().getName());
        dbSchedule.setApplicationName(applicationName);
        dbSchedule.setStatus(TaskStatusEnum.ENABLE.code());
        dbSchedule.setUpDate(LocalDateTime.now());
        dbScheduleRepository.save(dbSchedule);
        this.dynamicRegistration(iRunnable, new RegistrerParams(taskId, expression), true);
        return taskId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateTimer(String taskId, String expression) {
        DbSchedule dbSchedule = dbScheduleRepository.findDbScheduleByTaskIdAndStatus(taskId, TaskStatusEnum.DISABLE.code());
        if (null == dbSchedule) {
            LOGGER.error("[更新定时器] 失败... taskId:{} 定时器不存在", taskId);
            throw new TimerException(TimerExceptionEnum.TIMER_DOESN_EXIST.code());
        }
        dbSchedule.setExpression(expression);
        dbSchedule.setUpDate(LocalDateTime.now());
        return dbScheduleRepository.save(dbSchedule).getTaskId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean enableTimer(String taskId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DbSchedule dbSchedule = this.dbScheduleRepository.findDbScheduleByTaskIdAndStatus(taskId, TaskStatusEnum.DISABLE.code());
        if (null == dbSchedule) {
            LOGGER.error("[动态定时] 启用TaskId:{} 定时器失败，定时器不存在...", taskId);
            throw new TimerException(TimerExceptionEnum.TIMER_DOESN_EXIST.code());
        }
        String performClass = dbSchedule.getPerformClass();
        Class<? extends IRunnable> aClass = (Class<? extends IRunnable>) Class.forName(performClass);
        IRunnable iRunnable = aClass.newInstance();
        RegistrerParams registrerModel = new RegistrerParams(taskId, dbSchedule.getExpression());
        this.dynamicRegistration(iRunnable, registrerModel, true);
        LOGGER.debug("[动态定时] 定时器TaskId:{} 启用成功...", taskId);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean shutdownTimer(String taskId) {
        DbSchedule dbSchedule = this.dbScheduleRepository.findDbScheduleByTaskIdAndStatus(taskId, TaskStatusEnum.ENABLE.code());
        if (null != dbSchedule) {
            dbSchedule.setStatus(TaskStatusEnum.DISABLE.code());
            dbSchedule.setUpDate(LocalDateTime.now());
            this.dbScheduleRepository.save(dbSchedule);
        }
        return this.scheduleInitializeSharedData.shutdown(taskId);
    }

    @Override
    public Boolean deleteTimer(String taskId) {
        DbSchedule dbSchedule = this.dbScheduleRepository.findDbScheduleByTaskIdAndStatus(taskId, TaskStatusEnum.DISABLE.code());
        if (null == dbSchedule) {
            LOGGER.error("[动态定时] 启用TaskId:{} 定时器失败，定时器不存在...", taskId);
            throw new TimerException(TimerExceptionEnum.TIMER_DOESN_EXIST.code());
        }
        this.dbScheduleRepository.delete(dbSchedule);
        return this.scheduleInitializeSharedData.shutdown(taskId);
    }

    @Override
    public List<DbSchedule> availableTimerHasCorrect(Boolean hasCorrect) {

        if (null == this.dbScheduleRepository) {
            return new ArrayList<>();
        }
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

        this.parameterCalibration(model);

        ThreadPoolTaskScheduler scheduler = (ThreadPoolTaskScheduler) scheduleInitializeSharedData.getTaskRegistrar().getScheduler();
        if (null == scheduler) {
            LOGGER.error("[动态定时] 初始化线程池失败...");
            throw new TimerException(TimerExceptionEnum.THREAD_POOL_INITIALIZE_FAILURE.code());
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
        if (null == future) {
            throw new TimerException(TimerExceptionEnum.REGISTRATION_FAILED.code());
        }

        scheduleInitializeSharedData.getFutures().put(model.getTaskId(), future);
        scheduleInitializeSharedData.getHasBeenreistered().put(model.getTaskId(), model.getExpression());
        LOGGER.info("[动态定时] 注册成功! TaskId:{}, Expression:{}", model.getTaskId(), model.getExpression());
    }

    /**
     * 参数校验
     *
     * @param registrerModel 注册信息
     * @return success/fail
     */
    private void parameterCalibration(RegistrerParams registrerModel) {
        if (StringUtils.isEmpty(registrerModel.getTaskId()))
            throw new TimerException(TimerExceptionEnum.TASK_NONE_EMPTY.code());

        if (StringUtils.isEmpty(registrerModel.getExpression()))
            throw new TimerException(TimerExceptionEnum.EXPRESSION_NONE_EMPTY.code());
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
