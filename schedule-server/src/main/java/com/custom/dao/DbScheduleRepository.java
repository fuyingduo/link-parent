package com.custom.dao;

import com.custom.entity.DbSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA
 * created by fuyd on 2019-07-19
 */
public interface DbScheduleRepository extends JpaRepository<DbSchedule, Integer> {

    /**
     * 通过TaskId查找定时器
     *
     * @param taskId 唯一标识
     * @return DbSchedule
     */
    DbSchedule findDbScheduleByTaskId(String taskId);

    /**
     * 通过TaskId/status查找已注册定时器
     *
     * @param taskId 唯一标识
     * @param status 状态
     * @return DbSchedule
     */
    DbSchedule findDbScheduleByTaskIdAndStatus(String taskId, Integer status);
}
