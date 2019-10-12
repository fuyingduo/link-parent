package com.monitor.base;


import java.io.Serializable;

/**
 * created by fuyd on 2019-06-16
 */
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -6755921133074472183L;
    private int code;
    private T data;
    private String message;

    private BaseResult() {
    }

    private BaseResult(T data) {
        this.code = 0;
        this.data = data;
        this.message = "successful";
    }

    private BaseResult(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> BaseResult<T> ok(T data) {
        return new BaseResult<>(data);
    }

    public static <T> BaseResult<T> err(String message) {
        return new BaseResult<>(-1, null, message);
    }

    public static <T> BaseResult<T> err(int code, String message) {
        return new BaseResult<>(code, null, message);
    }

    public static <T> BaseResult<T> err(int code, T data, String message) {
        return new BaseResult<>(code, data, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
