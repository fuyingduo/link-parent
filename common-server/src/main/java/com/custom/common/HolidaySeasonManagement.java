package com.custom.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * 初始化节假日数据
 * created by fuyd on 2019-08-15
 */
@Component
public class HolidaySeasonManagement {

    @Autowired
    private IHolidaySeasonService iHolidaySeasonService;

    @PostConstruct
    private void init() {
        List<String> festvivals = Arrays.asList(
                "2019-04-05", "2019-05-01", "2019-05-02", "2019-05-03",
                "2019-05-04", "2019-06-07", "2019-09-13", "2019-10-01",
                "2019-10-02", "2019-10-03", "2019-10-04", "2019-10-05",
                "2019-10-06", "2019-10-07");

        List<String> beondutys = Arrays.asList(
                "2019-04-28", "2019-05-05", "2019-09-26", "2019-10-12");
        iHolidaySeasonService.deleteHolidaySeason(festvivals, beondutys);
//        HolidaySeasonJson.Holiday.HolidaySeason holidaySeason = iHolidaySeasonService.findHolidaySeason();
//        List<HolidaySeasonJson.Holiday.HolidaySeason.Festvivals> festvivals = holidaySeason.getFestvivals();
//        if (null != festvivals) {
//            List<LocalDate> festvivalList = festvivals.stream().map(day -> LocalDate.parse(day.getSelectDate())).collect(toList());
//            MemoryConstants.FESTVIVALS.addAll(festvivalList);
//        }
//        List<HolidaySeasonJson.Holiday.HolidaySeason.Beonduty> beondutys = holidaySeason.getBeondutys();
//        if (null != beondutys) {
//            List<LocalDate> beondutyList = beondutys.stream().map(day -> LocalDate.parse(day.getSelectDate())).collect(toList());
//            MemoryConstants.BEONDUTY.addAll(beondutyList);
//        }
    }
}
