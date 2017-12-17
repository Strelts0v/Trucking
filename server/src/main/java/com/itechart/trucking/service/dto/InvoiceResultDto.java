package com.itechart.trucking.service.dto;

public class InvoiceResultDto {

    private UserDto driver;
    private String completeDate;
    private Float income;
    private Float consumption;

    public UserDto getDriver() {
        return driver;
    }

    public void setDriver(UserDto driver) {
        this.driver = driver;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Float getConsumption() {
        return consumption;
    }

    public void setConsumption(Float consumption) {
        this.consumption = consumption;
    }
}
