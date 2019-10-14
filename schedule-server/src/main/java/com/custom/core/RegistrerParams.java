package com.custom.core;

/**
 * 定时器注册参数
 * created by fuyd on 2019-07-19
 */
public class RegistrerParams {

    private String taskId;
    private String expression;

    RegistrerParams(String taskId, String expression) {
        this.taskId = taskId;
        this.expression = expression;
    }

    String getTaskId() {
        return taskId;
    }

    String getExpression() {
        return expression;
    }


    @Override
    public String toString() {
        return "RegistrerParams{" +
                "taskId='" + taskId + '\'' +
                ", expression='" + expression + '\'' +
                '}';
    }
}
