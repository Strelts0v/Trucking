package com.itechart.trucking.domain;

import java.time.LocalDate;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-12-19
 */
public class Report {

    private LocalDate completeDate;
    private Double income;
    private Double consumption;

    public Report(LocalDate completeDate, Double income, Double consumption) {
        this.completeDate = completeDate;
        this.income = income;
        this.consumption = consumption;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }
}
