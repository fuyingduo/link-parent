package com.custom.service.impl;

import com.custom.bo.TaskParamBO;
import com.custom.dao.JobRepository;
import com.custom.entity.Job;
import com.custom.enums.ServiceTypeEnum;
import com.custom.enums.StatusEnum;
import com.custom.exception.HandleException;
import com.custom.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * created by fuyd on 2020-01-07
 */
@Service
public class JobService implements IJobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private QuratzCoreBusiness quratzCoreBusiness;

    @Override
    public Page<Job> findJobs(Pageable page) {
        return jobRepository.findJobsByOrderByStatusAscCreateDateDesc(page);
    }

    @Override
    public Integer newTask(TaskParamBO bo) {

        if (null != jobRepository.findJobByName(bo.getName())) {
            throw new HandleException(20001);
        }
        Job job = jobRepository.save(bo.toJob());
        Integer jobId;
        if (null == (jobId = job.getId())) {
            throw new HandleException(20002);
        }
        String group = ServiceTypeEnum.findMsg(bo.getServiceType());
        quratzCoreBusiness.newTask(bo.getName(), group, bo.getDescription(), bo.getCron(), job.getServiceType());
        return jobId;
    }

    @Override
    public void pauseTask(Integer id) {
        Job job = this.findJob(id);
        job.setStatus(StatusEnum.NO.value());
        jobRepository.save(job);
        Boolean hasPause = quratzCoreBusiness.pauseTask(job.getName(), ServiceTypeEnum.findMsg(job.getServiceType()));
        if (!hasPause) {
            throw new HandleException(20003);
        }
    }

    @Override
    public void resumeTask(Integer id) {
        Job job = this.findJob(id);
        job.setStatus(StatusEnum.YES.value());
        jobRepository.save(job);
        Boolean hasResume = quratzCoreBusiness.resumeTask(job.getName(), ServiceTypeEnum.findMsg(job.getServiceType()));
        if (!hasResume) {
            throw new HandleException(20004);
        }
    }

    @Override
    public void removeTask(Integer id) {
        Job job = this.findJob(id);
        jobRepository.delete(job);
        Boolean hasRemove = quratzCoreBusiness.removeTask(job.getName(), ServiceTypeEnum.findMsg(job.getServiceType()));
        if (!hasRemove) {
            throw new HandleException(20005);
        }
    }

    @Override
    public void triggerTask(Integer id) {
        Job job = this.findJob(id);
        Boolean hasTrigger = quratzCoreBusiness.triggerTask(job.getName(), ServiceTypeEnum.findMsg(job.getServiceType()));
        if (!hasTrigger) {
            throw new HandleException(20006);
        }
    }

    @Override
    public Job findJobByParams(String name, Integer serviceType) {
        return jobRepository.findJobByNameAndServiceType(name, serviceType);
    }

    /**
     * 获取任务信息
     *
     * @param id {@link Job} 主键
     */
    private Job findJob(Integer id) {
        if (null == id) {
            throw new HandleException(30005);
        }
        Optional<Job> optional = jobRepository.findById(id);
        if (!optional.isPresent()) {
            throw new HandleException(20007);
        }
        return optional.get();
    }
}
