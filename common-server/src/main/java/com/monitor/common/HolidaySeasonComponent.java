package com.monitor.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * created by fuyd on 2019-08-16
 */
public class HolidaySeasonComponent {

    private static final String PATH = "/fuyd/weoil/holidaySeason.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static void addFile(HolidaySeasonJson json) {
        try {
            FileUtils.writeStringToFile(new File(PATH), JSON.toJSONString(json), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HolidaySeasonJson getFile() {
        File file = new File(PATH);
        if (!file.exists()) {
            return null;
        }
        InputStream inputStream;
        Object o = null;
        try {
            inputStream = new FileInputStream(file);
            o = JSON.parseObject(
                    inputStream,
                    StandardCharsets.UTF_8,
                    HolidaySeasonJson.class,
                    Feature.AutoCloseSource,
                    Feature.AllowComment,
                    Feature.AllowSingleQuotes,
                    Feature.UseBigDecimal
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return MAPPER.convertValue(o, HolidaySeasonJson.class);
    }

    public static void main(String[] args) {
        List<List<LocalDate>> maps = new ArrayList<>();
        int index = 3;
        List<LocalDate> dateList = Arrays.asList(
                LocalDate.of(2019, 8, 20),
                LocalDate.of(2019, 8, 18),
                LocalDate.of(2019, 8, 17),
                LocalDate.of(2019, 8, 3),
                LocalDate.of(2019, 8, 4),
                LocalDate.of(2019, 8, 6),
                LocalDate.of(2019, 8, 7),
                LocalDate.of(2019, 8, 8),
                LocalDate.of(2019, 8, 11),
                LocalDate.of(2019, 8, 12),
                LocalDate.of(2019, 8, 14),
                LocalDate.of(2019, 8, 16),
                LocalDate.of(2019, 8, 2),
                LocalDate.of(2019, 8, 26),
                LocalDate.of(2019, 8, 21),
                LocalDate.of(2019, 8, 25),
                LocalDate.of(2019, 8, 30),
                LocalDate.of(2019, 8, 29),
                LocalDate.of(2019, 8, 28),
                LocalDate.of(2019, 8, 2),
                LocalDate.of(2019, 8, 1));
        List<LocalDate> list = dateList.stream().sorted().collect(Collectors.toList());
        LocalDate first = list.get(0);
        LocalDate last = list.get(dateList.size() - 1);
        while (!first.equals(last)) {
            List<LocalDate> dates = new ArrayList<>();
            dates.add(first);
            LocalDate nextDay = first.plus(1, ChronoUnit.DAYS);
            while (dateList.contains(nextDay)) {
                dates.add(nextDay);
                first = nextDay;
                nextDay = nextDay.plus(1, ChronoUnit.DAYS);
            }
            LocalDate finalFirst = first;
            Optional<LocalDate> nextGroup = list.stream().filter(day -> day.isAfter(finalFirst)).findFirst();
            if (nextGroup.isPresent()) {
                first = nextGroup.get();
            }
            maps.add(dates);
        }
        List<List<LocalDate>> allDays = maps.stream().filter(ls -> ls.size() >= index).collect(Collectors.toList());
        String allstr = allDays.stream().map(d -> d.stream().map(LocalDate::toString).collect(Collectors.joining(","))).collect(Collectors.joining(","));
        System.out.println(JSON.toJSONString(allDays));
    }

}
