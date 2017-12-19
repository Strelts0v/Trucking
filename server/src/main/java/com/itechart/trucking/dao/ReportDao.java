package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Report;

import java.time.LocalDate;
import java.util.List;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-12-19
 */
public interface ReportDao {

    List<Report> findResultsByDate(LocalDate startDate, LocalDate endDate);
}
