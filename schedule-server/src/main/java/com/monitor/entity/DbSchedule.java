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

    @Column(name = "task_id", columnDefinition = "varchar(100) comment '定时器唯一标识'", unique = true, nullable = false, updatable = false)
    private String taskId;

    @Column(columnDefinition = "varchar(30) comment 'cron表达式'", nullable = false)
    private String expression;

    @Column(name = "up_date", columnDefinition = "datetime comment '更新时间'", nullable = false)
    private LocalDateTime upDate;

    @Column(name = "perform_class", columnDefinition = "varchar(50) comment '业务处理实现类'", nullable = false)
    private String performClass;

    @Column(columnDefinition = "tinyint(4) comment '定时器状态 1启用 2禁用'", nullable = false)
    private Integer status;

    @Column(name = "application_name", columnDefinition = "varchar(40) comment '服务名'", nullable = false)
    private String applicationName;

    @Column(columnDefinition = "varchar(100) default '' comment '简介'")
    private String introduction;

    @Version
    @Column(columnDefinition = "int(20) default '0' comment '版本号'")
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

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
                ", applicationName='" + applicationName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", version=" + version +
                '}';
    }
}
