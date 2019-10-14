package com.custom.base;

import com.custom.util.FestivalsUtil;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 日历模版工具类
 * created by fuyd on 2019-09-24
 */
public class CalendarTemplate {

    /**
     * map key补零最大限制
     */
    private static final int ZERO_MAX = 10;

    /**
     * map key补零
     */
    private static final String ZERO_PREFIX = "0";

    /**
     * 默认日期开始时间
     */
    private static final Integer DEFAULT_START = 1;

    /**
     * 默认map value值
     */
    private static final Integer DEFAULT_STATUS = 7;

    /**
     * 28 天日期/2月份
     */
    private static final Integer DATE_28 = 28;

    /**
     * 29天日期/2月份
     */
    private static final Integer DATE_29 = 29;

    /**
     * 30天日期/4，6，9，11月份
     */
    private static final Integer DATE_30 = 30;

    /**
     * 31天日期/1，3，5，7，8，10，12月份
     */
    private static final Integer DATE_31 = 31;

    /**
     * 31天日期MAP
     */
    private static final ConcurrentMap<String, Integer> TEMPLATE_31 = new ConcurrentHashMap<>();

    /**
     * 30天日期MAP
     */
    private static final ConcurrentMap<String, Integer> TEMPLATE_30 = new ConcurrentHashMap<>();

    /**
     * 29天日期MAP
     */
    private static final ConcurrentMap<String, Integer> TEMPLATE_29 = new ConcurrentHashMap<>();

    /**
     * 28天日期MAP
     */
    private static final ConcurrentMap<String, Integer> TEMPLATE_28 = new ConcurrentHashMap<>();

    /**
     * 私有无参构造器
     */
    private CalendarTemplate() {
    }

    /**
     * MAP初始化
     */
    private static void initialize() {
        List<Integer> list = Stream
                .iterate(CalendarTemplate.DEFAULT_START, day -> day + 1).limit(DATE_28).collect(toList());
        Map<String, Integer> maps = list.stream()
                .collect(Collectors.toMap(CalendarTemplate::zeroPad, v -> CalendarTemplate.DEFAULT_STATUS));
        CalendarTemplate.TEMPLATE_28.putAll(maps);
        maps.put(String.valueOf(CalendarTemplate.DATE_29), CalendarTemplate.DEFAULT_STATUS);
        CalendarTemplate.TEMPLATE_29.putAll(maps);
        maps.put(String.valueOf(CalendarTemplate.DATE_30), CalendarTemplate.DEFAULT_STATUS);
        CalendarTemplate.TEMPLATE_30.putAll(maps);
        maps.put(String.valueOf(CalendarTemplate.DATE_31), CalendarTemplate.DEFAULT_STATUS);
        CalendarTemplate.TEMPLATE_31.putAll(maps);
    }

    /**
     * MAP key 补零操作
     *
     * @param key key
     */
    private static String zeroPad(int key) {
        if (CalendarTemplate.ZERO_MAX > key) {
            return CalendarTemplate.ZERO_PREFIX + key;
        }
        return String.valueOf(key);
    }

    /**
     * 是否需要初始化MAP
     */
    private static void hasInitialize() {
        if (CalendarTemplate.TEMPLATE_28.isEmpty()) {
            CalendarTemplate.initialize();
        }
        if (CalendarTemplate.TEMPLATE_29.isEmpty()) {
            CalendarTemplate.initialize();
        }
        if (CalendarTemplate.TEMPLATE_30.isEmpty()) {
            CalendarTemplate.initialize();
        }
        if (CalendarTemplate.TEMPLATE_31.isEmpty()) {
            CalendarTemplate.initialize();
        }
    }

    /**
     * 获取日期模版
     *
     * @param date 日期
     */
    public static ConcurrentMap<String, Integer> findTemp(LocalDate date) {
        CalendarTemplate.hasInitialize();
        LocalDate localDate = date.with(TemporalAdjusters.lastDayOfMonth());
        int lastDay = localDate.getDayOfMonth();
        if (CalendarTemplate.DATE_28 == lastDay) {
            return CalendarTemplate.TEMPLATE_28;
        }
        if (CalendarTemplate.DATE_29 == lastDay) {
            return CalendarTemplate.TEMPLATE_29;
        }
        if (CalendarTemplate.DATE_30 == lastDay) {
            return CalendarTemplate.TEMPLATE_30;
        }
        if (CalendarTemplate.DATE_31 == lastDay) {
            return CalendarTemplate.TEMPLATE_31;
        }
        return null;
    }

    /**
     * 更改MAP状态
     *
     * @param maps   日期MAP
     * @param keys   Map 键
     * @param status 更改状态
     */
    public static void change(@NonNull ConcurrentMap<String, Integer> maps,
                              @NonNull List<String> keys, @NonNull Integer status) {
        keys.forEach(key -> {
            Integer value = maps.get(key);
            if (null == value) {
                return;
            }
            maps.put(key, status);
        });
    }

    /**
     * 补充节假日状态
     *
     * @param maps      日期MAP
     * @param localDate 日期
     * @param status    更改状态
     */
    public static void supplementHoliday(@NonNull ConcurrentMap<String, Integer> maps,
                                         @NonNull LocalDate localDate, @NonNull Integer status) {
        LocalDate begin = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = localDate.with(TemporalAdjusters.lastDayOfMonth());
        long between = ChronoUnit.DAYS.between(begin, end);
        List<LocalDate> months = Stream.iterate(begin, day -> day.plusDays(1)).limit(between).collect(toList());
        List<String> holidays = months.parallelStream().map(day -> {
            String key = day.format(DateTimeFormatter.ofPattern("dd"));
            if (FestivalsUtil.isHoliday(day)) {
                return key;
            }
            if (FestivalsUtil.isWeekend(day)) {
                return key;
            }
            return null;
        }).collect(toList());
        CalendarTemplate.change(maps, holidays, status);
    }

}
