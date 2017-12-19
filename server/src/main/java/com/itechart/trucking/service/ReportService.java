package com.itechart.trucking.service;

import com.itechart.trucking.service.dto.ReportDto;

import java.util.Optional;

public interface ReportService {

    Optional<ReportDto> getReportByDate(String startDate, String endDate);
}
