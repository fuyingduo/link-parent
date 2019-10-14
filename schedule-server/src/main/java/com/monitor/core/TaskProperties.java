package com.monitor.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.orm.jpa.vendor.Database;

import java.util.Map;

/**
 * created by fuyd on 2019-07-19
 */
@ConfigurationProperties(prefix = "schdule")
public class TaskProperties {

    private ThreadPool threadPool;

    private Boolean persistence = Boolean.TRUE;

    private Datasource datasource;

    private Jpa jpa;

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public Boolean getPersistence() {
        return persistence;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public Jpa getJpa() {
        return jpa;
    }

    public void setJpa(Jpa jpa) {
        this.jpa = jpa;
    }

    public void setPersistence(Boolean persistence) {
        this.persistence = persistence;
    }

    /**
     * 线程池
     */
    public static class ThreadPool {

        private int size;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    /**
     * 数据源
     */
    public static class Datasource {

        private String url;
        private String username;
        private String password;
        private String driverClassName;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }
    }

    /**
     * JPA
     */
    public static class Jpa {

        private Boolean showSql;
        private Map<String, String> properties;
        private String databasePlatform;
        private Database database;

        public Boolean getShowSql() {
            return showSql;
        }

        public void setShowSql(Boolean showSql) {
            this.showSql = showSql;
        }

        public Map<String, String> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, String> properties) {
            this.properties = properties;
        }

        public String getDatabasePlatform() {
            return databasePlatform;
        }

        public void setDatabasePlatform(String databasePlatform) {
            this.databasePlatform = databasePlatform;
        }

        public Database getDatabase() {
            return database;
        }

        public void setDatabase(Database database) {
            this.database = database;
        }
    }
}
