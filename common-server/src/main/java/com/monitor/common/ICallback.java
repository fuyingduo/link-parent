package com.monitor.common;

import okhttp3.Callback;

/**
 * created by fuyd on 2019-07-26
 */
public interface ICallback extends Callback {

    default String getClassName() {
        return this.getClass().getSimpleName();
    }
}
