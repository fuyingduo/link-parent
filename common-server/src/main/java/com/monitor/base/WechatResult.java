package com.monitor.base;

/**
 * created by fuyd on 2019-06-20
 */
public class WechatResult<T> {

    private int code;
    private T data;
    private String message;

    private WechatResult(T data) {
        this.code = 0;
        this.data = data;
        this.message = "successful";
    }

    private WechatResult(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> WechatResult<T> ok(T data) {
        return new WechatResult<>(data);
    }

    public static <T> WechatResult<T> err(String message) {
        return new WechatResult<>(-1, null, message);
    }

    public int code() {
        return code;
    }

    public T data() {
        return data;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return "WechatResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
