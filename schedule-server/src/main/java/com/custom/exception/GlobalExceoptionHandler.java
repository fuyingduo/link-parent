package com.custom.exception;

import com.custom.base.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * created by fuyd on 2019-10-08
 */
@ControllerAdvice
public class GlobalExceoptionHandler {

    @ExceptionHandler(value = TimerException.class)
    @ResponseBody
    public BaseResult<String> handleException(TimerException e) {
        return BaseResult.err(e.getCode(), e.getMessage());
    }
}
