package com.monitor.core;

/**
 * created by fuyd on 2019-07-19
 */
public class RegistrerParams {

    private String taskId;
    private String expression;

    public RegistrerParams(String taskId, String expression) {
        this.taskId = taskId;
        this.expression = expression;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getExpression() {
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
