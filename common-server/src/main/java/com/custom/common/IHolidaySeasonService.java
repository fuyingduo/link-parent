package com.custom.common;

import java.util.List;

/**
 * created by fuyd on 2019-08-15
 */
public interface IHolidaySeasonService {

    /**
     * 添加修改节假日
     *
     * @param festvivals 节假日
     * @param beondutys  串休日期
     */
    void addHolidaySeason(List<String> festvivals, List<String> beondutys);

    /**
     * 删除节假日
     *
     * @param festvivals 节假日
     * @param beondutys  串休日期
     */
    void deleteHolidaySeason(List<String> festvivals, List<String> beondutys);

    /**
     * 获取今年某月节假日
     *
     * @param month 月份
     */
    HolidaySeasonJson.Holiday.HolidaySeason findHolidaySeason(Integer month);

    /**
     * 获取今年所有假期
     */
    HolidaySeasonJson.Holiday.HolidaySeason findHolidaySeason();
}
