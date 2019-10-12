package com.monitor.common;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * created by fuyd on 2019-08-15
 */
@Service
public class HolidaySeasonService implements IHolidaySeasonService {

    @Override
    public void addHolidaySeason(List<String> festvivals, List<String> beondutys) {
        int year = LocalDate.now().getYear();
        HolidaySeasonJson file = HolidaySeasonComponent.getFile();
        if (null == file) {
            file = new HolidaySeasonJson();
        }
        List<HolidaySeasonJson.Holiday> holidays = file.getHolidays();
        if (CollectionUtils.isEmpty(holidays)) {
            HolidaySeasonJson.Holiday holiday = new HolidaySeasonJson.Holiday();
            holiday.setYear(LocalDate.now().getYear());
            HolidaySeasonJson.Holiday.HolidaySeason holidaySeason = new HolidaySeasonJson.Holiday.HolidaySeason();
            holidaySeason.setBeondutys(new ArrayList<>());
            holidaySeason.setFestvivals(new ArrayList<>());
            holiday.setHolidaySeason(holidaySeason);
            holidays = Collections.singletonList(holiday);
            file.setHolidays(holidays);
        }
        Optional<HolidaySeasonJson.Holiday> any = holidays.stream().filter(day -> day.getYear().equals(year)).findAny();
        if (!any.isPresent()) {
            HolidaySeasonJson.Holiday holiday = new HolidaySeasonJson.Holiday();
            holiday.setHolidaySeason(new HolidaySeasonJson.Holiday.HolidaySeason());
            holidays.add(holiday);
        }
        HolidaySeasonJson.Holiday holiday = any.get();
        HolidaySeasonJson.Holiday.HolidaySeason holidaySeason = holiday.getHolidaySeason();
        List<HolidaySeasonJson.Holiday.HolidaySeason.Festvivals> festvivalList = holidaySeason.getFestvivals();
        Map<Integer, List<String>> fMaps = festvivals.stream().collect(groupingBy(key -> {
            LocalDate parse = LocalDate.parse(key);
            return parse.getMonthValue();
        }));
        fMaps.keySet().forEach(key -> {
            List<String> selectDates = fMaps.get(key);
            if (!CollectionUtils.isEmpty(selectDates)) {
                List<HolidaySeasonJson.Holiday.HolidaySeason.Festvivals> list = selectDates.stream().map(day -> {
                    HolidaySeasonJson.Holiday.HolidaySeason.Festvivals festvival = new HolidaySeasonJson.Holiday.HolidaySeason.Festvivals();
                    festvival.setId(key);
                    festvival.setSelectDate(day);
                    return festvival;
                }).collect(toList());
                festvivalList.addAll(list);
            }
        });

        List<HolidaySeasonJson.Holiday.HolidaySeason.Beonduty> beondutyList = holidaySeason.getBeondutys();
        Map<Integer, List<String>> bMaps = beondutys.stream().collect(groupingBy(key -> {
            LocalDate parse = LocalDate.parse(key);
            return parse.getMonthValue();
        }));
        bMaps.keySet().forEach(key -> {
            List<String> selectDates = bMaps.get(key);
            if (!CollectionUtils.isEmpty(selectDates)) {
                List<HolidaySeasonJson.Holiday.HolidaySeason.Beonduty> beonduties = selectDates.stream().map(day -> {
                    HolidaySeasonJson.Holiday.HolidaySeason.Beonduty beonduty = new HolidaySeasonJson.Holiday.HolidaySeason.Beonduty();
                    beonduty.setId(key);
                    beonduty.setSelectDate(day);
                    return beonduty;
                }).collect(toList());
                beondutyList.addAll(beonduties);
            }
        });
        List<HolidaySeasonJson.Holiday.HolidaySeason.Festvivals> fList = festvivalList
                .stream().filter(distinctByKey(HolidaySeasonJson.Holiday.HolidaySeason.Festvivals::getSelectDate)).collect(toList());
        List<HolidaySeasonJson.Holiday.HolidaySeason.Beonduty> bList = beondutyList
                .stream().filter(distinctByKey(HolidaySeasonJson.Holiday.HolidaySeason.Beonduty::getSelectDate)).collect(toList());
        holidaySeason.setFestvivals(fList);
        holidaySeason.setBeondutys(bList);
        HolidaySeasonComponent.addFile(file);
    }

    @Override
    public void deleteHolidaySeason(List<String> festvivals, List<String> beondutys) {
        int year = LocalDate.now().getYear();
        HolidaySeasonJson file = HolidaySeasonComponent.getFile();
        if (null == file) {
            return;
        }
        List<HolidaySeasonJson.Holiday> holidays = file.getHolidays();
        if (CollectionUtils.isEmpty(holidays)) {
            return;
        }
        Optional<HolidaySeasonJson.Holiday> any = holidays.stream().filter(day -> day.getYear().equals(year)).findAny();
        if (!any.isPresent()) {
            return;
        }
        HolidaySeasonJson.Holiday holiday = any.get();
        HolidaySeasonJson.Holiday.HolidaySeason holidaySeason = holiday.getHolidaySeason();
        if (null == holidaySeason) {
            return;
        }
        List<HolidaySeasonJson.Holiday.HolidaySeason.Festvivals> festvivalsList = holidaySeason.getFestvivals();
        festvivalsList.removeIf(day -> festvivals.contains(day.getSelectDate()));
        List<HolidaySeasonJson.Holiday.HolidaySeason.Beonduty> beondutyList = holidaySeason.getBeondutys();
        beondutyList.removeIf(day -> beondutys.contains(day.getSelectDate()));
        HolidaySeasonComponent.addFile(file);
    }

    @Override
    public HolidaySeasonJson.Holiday.HolidaySeason findHolidaySeason(Integer month) {
        HolidaySeasonJson.Holiday.HolidaySeason season = new HolidaySeasonJson.Holiday.HolidaySeason();
        HolidaySeasonJson.Holiday.HolidaySeason holidaySeason = this.findHolidaySeason();
        if (null == holidaySeason) {
            return season;
        }
        List<HolidaySeasonJson.Holiday.HolidaySeason.Festvivals> festvivals = holidaySeason.getFestvivals();
        if (!CollectionUtils.isEmpty(festvivals)) {
            List<HolidaySeasonJson.Holiday.HolidaySeason.Festvivals> festvivalsList = festvivals
                    .stream().filter(day -> day.getId().equals(month)).collect(toList());
            season.setFestvivals(festvivalsList);
        }
        List<HolidaySeasonJson.Holiday.HolidaySeason.Beonduty> beondutys = holidaySeason.getBeondutys();
        if (!CollectionUtils.isEmpty(beondutys)) {
            List<HolidaySeasonJson.Holiday.HolidaySeason.Beonduty> beondutyList = beondutys
                    .stream().filter(day -> day.getId().equals(month)).collect(toList());
            season.setBeondutys(beondutyList);
        }
        return season;
    }

    @Override
    public HolidaySeasonJson.Holiday.HolidaySeason findHolidaySeason() {
        int year = LocalDate.now().getYear();
        HolidaySeasonJson file = HolidaySeasonComponent.getFile();
        HolidaySeasonJson.Holiday.HolidaySeason season = new HolidaySeasonJson.Holiday.HolidaySeason();
        if (null == file) {
            return season;
        }
        List<HolidaySeasonJson.Holiday> holidays = file.getHolidays();
        if (CollectionUtils.isEmpty(holidays)) {
            return season;
        }
        Optional<HolidaySeasonJson.Holiday> any = holidays.stream().filter(day -> day.getYear().equals(year)).findAny();
        if (!any.isPresent()) {
            return season;
        }
        HolidaySeasonJson.Holiday holiday = any.get();
        return holiday.getHolidaySeason();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
