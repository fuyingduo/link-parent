package com.monitor.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * created by fuyd on 2019-07-19
 */
@Entity(name = "db_schedule")
@EntityListeners(AuditingEntityListener.class)
public class DbSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "task_id")
    private String taskId;

    private String expression;

    @Column(name = "up_date")
    private LocalDateTime upDate;

    @Column(name = "perform_class")
    private String performClass;

    private Integer status;

    @Version
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public LocalDateTime getUpDate() {
        return upDate;
    }

    public void setUpDate(LocalDateTime upDate) {
        this.upDate = upDate;
    }

    public String getPerformClass() {
        return performClass;
    }

    public void setPerformClass(String performClass) {
        this.performClass = performClass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "DbSchedule{" +
                "id=" + id +
                ", taskId='" + taskId + '\'' +
                ", expression='" + expression + '\'' +
                ", upDate=" + upDate +
                ", performClass='" + performClass + '\'' +
                ", status=" + status +
                ", version=" + version +
                '}';
    }
}
