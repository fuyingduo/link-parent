package com.monitor.endpoint;

import com.monitor.base.FeignResult;
import com.monitor.core.DefaultRunnable;
import com.monitor.core.RegistrerParams;
import com.monitor.service.IDbScheduleService;
import com.monitor.utils.TaskUtil;
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
    public FeignResult addTimer() {
        RegistrerParams params = new RegistrerParams(TaskUtil.generate("TS", "* * * * * *"), "* * * * * *");
        Boolean ts = iDbScheduleService.addTimer(params, new DefaultRunnable());
        if (ts) {
            return FeignResult.ok(true);
        }
        return FeignResult.err(-1, "err");
    }

    @PutMapping
    public FeignResult addOrUpdateTimer() {
        RegistrerParams params = new RegistrerParams(TaskUtil.generate("TS", "* * * * * *"), "* * * * * *");
        Boolean aBoolean = iDbScheduleService.addOrUpdateTimer(params, new DefaultRunnable());
        if (aBoolean) {
            return FeignResult.ok(true);
        }
        return FeignResult.err(-1, "err");
    }

    @DeleteMapping
    public FeignResult shutdownTimer() {
        Boolean aBoolean = iDbScheduleService.shutdownTimer("TS424242424242");
        if (aBoolean) {
            return FeignResult.ok(true);
        }
        return FeignResult.err(-1, "err");
    }

}
