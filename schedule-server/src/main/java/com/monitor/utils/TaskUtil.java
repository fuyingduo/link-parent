package com.monitor.utils;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by fuyd on 2019-07-22
 */
public class TaskUtil {

    // 秒 分 时 日 月 星期 年
    public static String generate(String applicationName, String prefix, @NotBlank String cron) {
        String str = cron.replaceAll(" ", "");
        byte[] bytes = str.getBytes();
        List<Integer> expressions = new ArrayList<>();
        for (byte aByte : bytes) {
            expressions.add((int) aByte);
        }
        prefix = prefix.toUpperCase();
        applicationName = applicationName.replaceAll("-", "").toUpperCase();
        String expression = expressions.stream().map(String::valueOf).collect(Collectors.joining());
        return String.format("%s_%s_%s", applicationName, prefix, expression);
    }
}
