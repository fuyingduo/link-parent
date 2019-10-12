package com.monitor.core;

/**
 * created by fuyd on 2019-07-19
 */
@FunctionalInterface
public interface IRunnable extends Runnable {
    default String classType() {
        return this.getClass().getSimpleName();
    }
}
