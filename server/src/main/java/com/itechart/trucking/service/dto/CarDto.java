package com.itechart.trucking.service.dto;

/**
 * A DTO representing a car.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-22
 */
public class CarDto {

    private String name;
    private String number;
    private String type;
    private int consumption;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
}