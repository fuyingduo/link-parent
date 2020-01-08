package com.custom.exception;

import com.custom.base.BaseResult;
import com.custom.exception.HandleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * created by fuyd on 2019-10-08
 */
@ControllerAdvice
public class GlobalExceoptionHandler {

    @ExceptionHandler(value = HandleException.class)
    @ResponseBody
    public BaseResult<String> handleException(HandleException e) {
        return BaseResult.err(e.getCode(), e.getMessage());
    }
}
