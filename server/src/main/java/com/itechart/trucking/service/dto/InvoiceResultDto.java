package com.itechart.trucking.service.dto;

/**
 * A DTO representing an InvoiceResult.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-12-17
 */
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
