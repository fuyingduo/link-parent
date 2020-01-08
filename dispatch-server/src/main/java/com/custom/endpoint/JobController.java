package com.custom.endpoint;

import com.custom.base.BaseResult;
import com.custom.bo.TaskParamBO;
import com.custom.common.Pages;
import com.custom.entity.Job;
import com.custom.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * created by fuyd on 2020-01-07
 */
@RestController
@RequestMapping("/cloud/job")
public class JobController {

    @Autowired
    private IJobService iJobService;

    @GetMapping
    public BaseResult<Page<Job>> findJobs(Integer _index) {
        Page<Job> jobs = iJobService.findJobs(Pages.byDesc(_index));
        return BaseResult.ok(jobs);
    }

    @PostMapping
    public BaseResult<Integer> newTask(TaskParamBO bo) {
        Integer id = iJobService.newTask(bo);
        return BaseResult.ok(id);
    }

    @PutMapping(value = "/pause/{id}")
    public BaseResult<Boolean> pauseTask(@PathVariable Integer id) {
        iJobService.pauseTask(id);
        return BaseResult.ok(true);
    }

    @PutMapping(value = "/resume/{id}")
    public BaseResult<Boolean> resumeTask(@PathVariable Integer id) {
        iJobService.resumeTask(id);
        return BaseResult.ok(true);
    }

    @PutMapping(value = "/trigger/{id}")
    public BaseResult<Boolean> triggerTask(@PathVariable Integer id) {
        iJobService.triggerTask(id);
        return BaseResult.ok(true);
    }

    @DeleteMapping(value = "/{id}")
    public BaseResult<Boolean> removeTask(@PathVariable Integer id) {
        iJobService.removeTask(id);
        return BaseResult.ok(true);
    }
}
