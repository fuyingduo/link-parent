package com.monitor.endpoint;

import com.monitor.base.FeignResult;
import com.monitor.core.DefaultRunnable;
import com.monitor.service.IDbScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * created by fuyd on 2019-07-26
 */
@RestController
@RequestMapping("/endpoint/schedule")
public class DbScheduleController {

    @Autowired
    private IDbScheduleService iDbScheduleService;

    @PostMapping
    public FeignResult addTimer(String tag, String cron, String introduction) {
        return FeignResult.ok(iDbScheduleService.addTimer(tag, cron, new DefaultRunnable(), introduction));
    }

    @PutMapping
    public FeignResult updateTimer(String taskId, String cron) {
        return FeignResult.ok(iDbScheduleService.updateTimer(taskId, cron));
    }

    @PutMapping(value = "/{taskId}/enable")
    public FeignResult enableTimer(@PathVariable String taskId) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Boolean aBoolean = iDbScheduleService.enableTimer(taskId);
        if (aBoolean) {
            return FeignResult.ok(true);
        }
        return FeignResult.err(-1, "err");
    }

    @PutMapping(value = "/{taskId}/shutdown")
    public FeignResult shutdownTimer(@PathVariable String taskId) {
        Boolean aBoolean = iDbScheduleService.shutdownTimer(taskId);
        if (aBoolean) {
            return FeignResult.ok(true);
        }
        return FeignResult.err(-1, "err");
    }

    @DeleteMapping
    public FeignResult deleteTimer(@PathVariable String taskId) {
        Boolean aBoolean = iDbScheduleService.deleteTimer(taskId);
        if (aBoolean) {
            return FeignResult.ok(true);
        }
        return FeignResult.err(-1, "err");
    }

}
