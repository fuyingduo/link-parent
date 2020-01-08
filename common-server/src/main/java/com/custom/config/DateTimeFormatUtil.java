package com.custom.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DateTimeFormatUtil implements Converter<String, LocalDateTime> {

    private static final DateTimeFormatter[] DATE_FORMATS = {

            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM HH")
    };

    @Override
    public LocalDateTime convert(@NotNull String source) {
        if ("".equals(source)) {
            return null;
        }
        DateTimeFormatter formatter = Arrays.stream(DateTimeFormatUtil.DATE_FORMATS)
                .filter(datetime -> {
                    try {
                        LocalDateTime.parse(source, datetime);
                    } catch (Exception e) {
                        return false;
                    }
                    return true;
                }).findAny().orElse(null);
        return null == formatter ? null : LocalDateTime.parse(source, formatter);
    }
}
