package com.custom.common;

/**
 * created by fuyd on 2019-07-29
 */
public class ExcelRt<T> {

    private int code;
    private T data;
    private String message;

    private ExcelRt(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> ExcelRt<T> ok(T data) {
        return new ExcelRt<>(0, data, null);
    }

    public static <T> ExcelRt<T> err(String message) {
        return new ExcelRt<>(-1, null, message);
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
