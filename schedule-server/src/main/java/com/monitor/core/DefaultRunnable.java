package com.monitor.core;

import com.monitor.service.IRunnable;

/**
 * created by fuyd on 2019-07-19
 */
public class DefaultRunnable implements IRunnable {
    @Override
    public void run() {
        System.out.println(classType() + "---");
    }
}
