package com.monitor.core;

import com.monitor.entity.DbSchedule;
import com.monitor.service.IRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 定时器已注册信息数据初始化
 * created by fuyd on 2019-07-25
 */
public class RegistrationDataInitialization implements IRegistrationDataInitialization {

    private final IDbScheduleEndpoint iDbScheduleEndpoint;
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationDataInitialization.class);

    RegistrationDataInitialization(IDbScheduleEndpoint iDbScheduleEndpoint) {
        this.iDbScheduleEndpoint = iDbScheduleEndpoint;
    }

    @Override
    public void dataInitialization() {
        List<DbSchedule> dbSchedules = this.iDbScheduleEndpoint.availableTimerHasCorrect(false);
        dbSchedules.forEach(db -> {
            String performClass = db.getPerformClass();
            try {
                Class<? extends IRunnable> aClass = (Class<? extends IRunnable>) Class.forName(performClass);
                IRunnable iRunnable = aClass.newInstance();
                this.iDbScheduleEndpoint.dynamicRegistration(iRunnable, new RegistrerParams(db.getTaskId(), db.getExpression()), false);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                RegistrationDataInitialization.LOG.error("[动态定时] 数据恢复失败 e:{}", e.getMessage());
            }
        });
    }
}
