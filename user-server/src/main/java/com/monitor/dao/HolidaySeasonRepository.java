package com.monitor.dao;

import com.monitor.entity.HolidaySeason;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by fuyd on 2019-08-15
 */
public interface HolidaySeasonRepository extends JpaRepository<HolidaySeason, Integer> {
}
