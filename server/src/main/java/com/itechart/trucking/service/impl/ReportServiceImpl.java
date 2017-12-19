package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.ReportDao;
import com.itechart.trucking.domain.Report;
import com.itechart.trucking.service.ReportService;
import com.itechart.trucking.service.dto.ReportDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.itechart.trucking.util.Utils.jsonToLocalDate;

/**
 * Service class for managing reports.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-12-19
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportDao reportDao;

    public ReportServiceImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public Optional<ReportDto> getReportByDate(String startDate, String endDate) {
        List<Report> result
                = reportDao.findResultsByDate(jsonToLocalDate(startDate), jsonToLocalDate(endDate));

        ReportDto resultDto = new ReportDto();
        result.forEach(resultDto::addLine);

        return Optional.of(resultDto);
    }
}
