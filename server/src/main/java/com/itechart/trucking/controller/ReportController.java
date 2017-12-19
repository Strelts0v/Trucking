package com.itechart.trucking.controller;

import com.itechart.trucking.service.ReportService;
import com.itechart.trucking.service.dto.ReportDto;
import com.itechart.trucking.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for managing reports.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-12-19
 */
@RestController
@RequestMapping("/api")
public class ReportController {

    private final Logger log = LoggerFactory.getLogger(ReportController.class);

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PutMapping("/report")
    public ResponseEntity<ReportDto> getReport(@RequestBody Map<String, String> dates) {
        log.debug("REST request to get report for dates: {} - {}", dates.get("startDate"), dates.get("endDate"));
        return ResponseUtil
                .wrapOrNotFound(reportService.getReportByDate(dates.get("startDate"), dates.get("endDate")));
    }
}
