package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Car;

/**
 * A DTO representing a car.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-23
 */
public class CarDto extends AbstractDto {

    private String name;
    private String number;
    private Car.Type type;
    private Float consumption;

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

    public Car.Type getType() {
        return type;
    }

    public void setType(Car.Type type) {
        this.type = type;
    }

    public Float getConsumption() {
        return consumption;
    }

    public void setConsumption(Float consumption) {
        this.consumption = consumption;
    }
}