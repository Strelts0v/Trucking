package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.ReportDao;
import com.itechart.trucking.domain.Report;
import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.ReportService;
import com.itechart.trucking.service.dto.ReportDto;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.itechart.trucking.util.Utils.jsonToLocalDate;

/**
 * Service class for managing reports.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-12-20
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    private final ReportDao reportDao;

    public ReportServiceImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public Optional<ReportDto> getReportByDate(String startDate, String endDate) {
        List<Report> result = reportDao.findResultsByDate(jsonToLocalDate(startDate), jsonToLocalDate(endDate));

        ReportDto resultDto = new ReportDto();
        result.forEach(resultDto::addLine);

        return Optional.of(resultDto);
    }

    @Override
    public Optional<byte[]> getExcelReport(String startDate, String endDate) {
        List<Report> result = reportDao.findResultsByDate(jsonToLocalDate(startDate), jsonToLocalDate(endDate));
        List<User> bestDrivers = reportDao.findFiveBestDrivers();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();

            //Head fonts
            XSSFFont fontGreenHead = createHeadFont(workbook, "#1B5E20");
            XSSFFont fontDeepOrangeHead = createHeadFont(workbook, "#BF360C");
            XSSFFont fontIndigoHead = createHeadFont(workbook, "#1A237E");
            XSSFFont fontWhiteHead = createHeadFont(workbook, "#FFFFFF");
            XSSFFont fontBlackHead = workbook.createFont();
            fontBlackHead.setFontHeightInPoints((short) 12);
            fontBlackHead.setBold(true);

            //Regular fonts
            XSSFFont fontGreen = createRegularFont(workbook, "#1B5E20");
            XSSFFont fontDeepOrange = createRegularFont(workbook, "#BF360C");
            XSSFFont fontIndigo = createRegularFont(workbook, "#1A237E");
            XSSFFont fontWhite = createRegularFont(workbook, "#FFFFFF");

            //Head styles
            XSSFCellStyle cellStyleGreenHead = createCellStyle(workbook, fontGreenHead,
                    "#C8E6C9", HorizontalAlignment.LEFT, "text", BorderStyle.THICK);
            XSSFCellStyle cellStyleDeepOrangeHead = createCellStyle(workbook, fontDeepOrangeHead,
                    "#FFCCBC", HorizontalAlignment.LEFT, "text", BorderStyle.THICK);
            XSSFCellStyle cellStyleIndigoHead = createCellStyle(workbook, fontIndigoHead,
                    "#C5CAE9", HorizontalAlignment.LEFT, "text", BorderStyle.THICK);
            XSSFCellStyle cellStyleWhiteHead = createCellStyle(workbook, fontBlackHead,
                    "#FFFFFF", HorizontalAlignment.CENTER, "text", BorderStyle.THICK);
            XSSFCellStyle cellStyleDeepPurpleHead = createCellStyle(workbook, fontWhiteHead,
                    "#7E57C2", HorizontalAlignment.CENTER, "text", BorderStyle.THICK);

            //Regular styles
            XSSFCellStyle cellStyleGreen = createCellStyle(workbook, fontGreen,
                    "#C8E6C9", HorizontalAlignment.RIGHT, "0.00", BorderStyle.THIN);
            XSSFCellStyle cellStyleDeepOrange = createCellStyle(workbook, fontDeepOrange,
                    "#FFCCBC", HorizontalAlignment.RIGHT, "0.00", BorderStyle.THIN);
            XSSFCellStyle cellStyleIndigo = createCellStyle(workbook, fontIndigo,
                    "#C5CAE9", HorizontalAlignment.RIGHT, "0.00", BorderStyle.THIN);
            XSSFCellStyle cellStyleDeepPurple = createCellStyle(workbook, fontWhite,
                    "#7E57C2", HorizontalAlignment.RIGHT, "0.00", BorderStyle.THIN);

            workbook.setSheetName(0, "P&L");

            Row row;
            Cell cell;
            final int colWidth = 3072;
            final int colWidth2 = 5120;

            row = sheet.createRow(0);
            for (int cellNum = 1; cellNum < result.size() + 1; cellNum++) {
                cell = row.createCell(cellNum);
                cell.setCellStyle(cellStyleWhiteHead);
                cell.setCellValue(
                        DateTimeFormatter.ofPattern("dd.MM.yyyy").format(result.get(cellNum - 1).getCompleteDate()));
                sheet.setColumnWidth(cellNum, colWidth2);
            }

            cell = row.createCell(result.size() + 1);
            cell.setCellStyle(cellStyleDeepPurpleHead);
            cell.setCellValue("Total");
            sheet.setColumnWidth(result.size() + 1, colWidth2);

            // Income
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyleGreenHead);
            cell.setCellValue("Income");
            sheet.setColumnWidth(0, colWidth);

            for (int cellNum = 1; cellNum < result.size() + 1; cellNum++) {
                cell = row.createCell(cellNum);
                cell.setCellStyle(cellStyleGreen);
                cell.setCellValue(result.get(cellNum - 1).getIncome());
            }

            cell = row.createCell(result.size() + 1);
            cell.setCellStyle(cellStyleDeepPurple);
            cell.setCellFormula("SUM(B2:" + CellReference.convertNumToColString(result.size()) + "2)");

            //Expenses
            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyleDeepOrangeHead);
            cell.setCellValue("Expenses");
            sheet.setColumnWidth(0, colWidth);

            for (int cellNum = 1; cellNum < result.size() + 1; cellNum++) {
                cell = row.createCell(cellNum);
                cell.setCellStyle(cellStyleDeepOrange);
                cell.setCellValue(result.get(cellNum - 1).getConsumption());
            }

            cell = row.createCell(result.size() + 1);
            cell.setCellStyle(cellStyleDeepPurple);
            cell.setCellFormula("SUM(B3:" + CellReference.convertNumToColString(result.size()) + "3)");

            //Profit
            row = sheet.createRow(3);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyleIndigoHead);
            cell.setCellValue("Profit");
            sheet.setColumnWidth(0, colWidth);

            for (int cellNum = 1; cellNum < result.size() + 2; cellNum++) {
                cell = row.createCell(cellNum);
                cell.setCellStyle(cellStyleIndigo);
                String colName = CellReference.convertNumToColString(cellNum);
                cell.setCellFormula(colName + "2-" + colName + "3");
                if (cellNum == result.size() + 1) {
                    cell.setCellStyle(cellStyleDeepPurple);
                }
            }

            //Best drivers
            sheet = workbook.createSheet();
            workbook.setSheetName(1, "Best drivers");

            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyleDeepPurpleHead);
            cell.setCellValue("First name");
            sheet.setColumnWidth(0, colWidth2);

            cell = row.createCell(1);
            cell.setCellStyle(cellStyleDeepPurpleHead);
            cell.setCellValue("Last name");
            sheet.setColumnWidth(1, colWidth2);

            cell = row.createCell(2);
            cell.setCellStyle(cellStyleDeepPurpleHead);
            cell.setCellValue("Middle name");
            sheet.setColumnWidth(2, colWidth2);

            for (int rowNum = 1; rowNum < bestDrivers.size() + 1; rowNum++) {
                row = sheet.createRow(rowNum);

                cell = row.createCell(0);
                cell.setCellStyle(cellStyleIndigo);
                cell.setCellValue(bestDrivers.get(rowNum - 1).getFirstName());

                cell = row.createCell(1);
                cell.setCellStyle(cellStyleIndigo);
                cell.setCellValue(bestDrivers.get(rowNum - 1).getLastName());

                cell = row.createCell(2);
                cell.setCellStyle(cellStyleIndigo);
                cell.setCellValue(bestDrivers.get(rowNum - 1).getMiddleName());
            }

            workbook.write(out);
            return Optional.of(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private XSSFCellStyle createCellStyle(XSSFWorkbook workbook, XSSFFont font, String backgroundColor,
                                          HorizontalAlignment alignment, String formatPattern,
                                          BorderStyle borderStyle) {

        XSSFCellStyle cellStyleGreenHead = workbook.createCellStyle();
        cellStyleGreenHead.setFont(font);
        cellStyleGreenHead.setDataFormat(HSSFDataFormat.getBuiltinFormat(formatPattern));
        cellStyleGreenHead.setAlignment(alignment);
        cellStyleGreenHead.setBorderTop(borderStyle);
        cellStyleGreenHead.setBorderRight(borderStyle);
        cellStyleGreenHead.setBorderBottom(borderStyle);
        cellStyleGreenHead.setBorderLeft(borderStyle);
        cellStyleGreenHead.setFillForegroundColor(new XSSFColor(java.awt.Color.decode(backgroundColor)));
        cellStyleGreenHead.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyleGreenHead;
    }

    private XSSFFont createRegularFont(XSSFWorkbook workbook, String color) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setColor(new XSSFColor(java.awt.Color.decode(color)));
        return font;
    }

    private XSSFFont createHeadFont(XSSFWorkbook workbook, String color) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        font.setColor(new XSSFColor(java.awt.Color.decode(color)));
        return font;
    }
}
