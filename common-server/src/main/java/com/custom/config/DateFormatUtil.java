package com.custom.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * created by fuyd on 2020-01-06
 */
public class DateFormatUtil implements Converter<String, LocalDate> {

    private static final DateTimeFormatter[] DATE_FORMATS = {

            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM")
    };


    @Override
    public LocalDate convert(@NotNull String source) {
        if ("".equals(source)) {
            return null;
        }
        DateTimeFormatter formatter = Arrays.stream(DateFormatUtil.DATE_FORMATS)
                .filter(date -> {
                    try {
                        LocalDate.parse(source, date);
                    } catch (Exception e) {
                        return false;
                    }
                    return true;
                })
                .findAny().orElse(null);
        return null == formatter ? null : LocalDate.parse(source, formatter);
    }
}

