package com.monitor.common;

import java.util.List;

/**
 * created by fuyd on 2019-08-15
 */
class HolidaySeasonJson {

    private List<Holiday> holidays;

    List<Holiday> getHolidays() {
        return holidays;
    }

    void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    public static class Holiday {
        private Integer year;
        private HolidaySeason holidaySeason;

        Integer getYear() {
            return year;
        }

        void setYear(Integer year) {
            this.year = year;
        }

        HolidaySeason getHolidaySeason() {
            return holidaySeason;
        }

        void setHolidaySeason(HolidaySeason holidaySeason) {
            this.holidaySeason = holidaySeason;
        }

        static class HolidaySeason {

            private List<Festvivals> festvivals;
            private List<Beonduty> beondutys;


            List<Festvivals> getFestvivals() {
                return festvivals;
            }

            void setFestvivals(List<Festvivals> festvivals) {
                this.festvivals = festvivals;
            }

            List<Beonduty> getBeondutys() {
                return beondutys;
            }

            void setBeondutys(List<Beonduty> beondutys) {
                this.beondutys = beondutys;
            }

            public static class Festvivals {
                private Integer id;
                private String selectDate;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                String getSelectDate() {
                    return selectDate;
                }

                void setSelectDate(String selectDate) {
                    this.selectDate = selectDate;
                }
            }

            public static class Beonduty {
                private Integer id;
                private String selectDate;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                String getSelectDate() {
                    return selectDate;
                }

                void setSelectDate(String selectDate) {
                    this.selectDate = selectDate;
                }
            }
        }

        @Override
        public String toString() {
            return "HolidaySeasonJson{" +
                    "year=" + year +
                    ", holidaySeason=" + holidaySeason +
                    '}';
        }
    }
}
