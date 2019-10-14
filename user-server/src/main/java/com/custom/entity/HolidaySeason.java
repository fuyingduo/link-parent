package com.custom.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * created by fuyd on 2019-08-15
 */
@Entity(name = "holiday_season")
public class HolidaySeason {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 类型 1 节假日 2 串休日期
     */
    private Integer type;
    /**
     * 选中日期
     */
    @Column(name = "selected_date")
    private LocalDate selectedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    @Override
    public String toString() {
        return "IHolidaySeasonService{" +
                "id=" + id +
                ", type=" + type +
                ", selectedDate=" + selectedDate +
                '}';
    }
}
