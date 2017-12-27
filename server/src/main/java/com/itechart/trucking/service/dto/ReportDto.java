package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Report;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO representing a report.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-12-19
 */
public class ReportDto {

    private List<String> completeDates = new ArrayList<>();
    private List<Double> income = new ArrayList<>();
    private List<Double> expenses = new ArrayList<>();
    private List<Double> profit = new ArrayList<>();

    public void addLine(Report report) {
        completeDates.add(report.getCompleteDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        income.add(report.getIncome());
        expenses.add(report.getConsumption());
        profit.add(BigDecimal.valueOf(report.getIncome())
                .subtract(BigDecimal.valueOf(report.getConsumption()))
                .doubleValue()
        );
    }

    public List<String> getCompleteDates() {
        return completeDates;
    }

    public void setCompleteDates(List<String> completeDates) {
        this.completeDates = completeDates;
    }

    public List<Double> getIncome() {
        return income;
    }

    public void setIncome(List<Double> income) {
        this.income = income;
    }

    public List<Double> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Double> expenses) {
        this.expenses = expenses;
    }

    public List<Double> getProfit() {
        return profit;
    }

    public void setProfit(List<Double> profit) {
        this.profit = profit;
    }
}
