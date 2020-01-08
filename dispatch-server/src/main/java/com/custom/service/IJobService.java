package com.custom.service;

import com.custom.bo.TaskParamBO;
import com.custom.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * created by fuyd on 2020-01-07
 */
public interface IJobService {

    /**
     * 获取任务列表
     */
    Page<Job> findJobs(Pageable page);

    /**
     * 新建任务
     *
     * @param bo 请求参数
     */
    Integer newTask(TaskParamBO bo);

    /**
     * 停止任务
     *
     * @param id 任务{@link Job} 主键
     */
    void pauseTask(Integer id);

    /**
     * 恢复任务
     *
     * @param id 任务{@link Job} 主键
     */
    void resumeTask(Integer id);

    /**
     * 移除任务
     *
     * @param id 任务{@link Job} 主键
     */
    void removeTask(Integer id);

    /**
     * 触发任务
     *
     * @param id 任务{@link Job} 主键
     */
    void triggerTask(Integer id);

    /**
     * 参数查询
     *
     * @param name        任务名称
     * @param serviceType 任务类型 {@link com.custom.enums.ServiceTypeEnum}
     */
    Job findJobByParams(String name, Integer serviceType);
}
