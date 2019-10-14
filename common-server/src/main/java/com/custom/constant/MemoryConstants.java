package com.custom.constant;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * 内存中常量
 * created by fuyd on 2019-08-15
 */
public class MemoryConstants {

    /**
     * 节假日
     */
    public static final Set<LocalDate> FESTVIVALS = new HashSet<>();

    /**
     * 调休日期
     */
    volatile public static Set<LocalDate> BEONDUTY = new HashSet<>();
}
