package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Report;
import com.itechart.trucking.domain.User;

import java.time.LocalDate;
import java.util.List;

/**
 * @author blink7
 * @version 1.1
 * @since 2017-12-21
 */
public interface ReportDao {

    List<Report> findResultsByDate(LocalDate startDate, LocalDate endDate);
    List<User> findFiveBestDrivers();
}
