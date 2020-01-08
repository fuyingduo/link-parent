package com.custom.config;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

/**
 * created by fuyd on 2020-01-06
 */
public class TimeFormatUtil implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {
        return null;
    }
}
