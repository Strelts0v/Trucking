package com.itechart.trucking.domain;

import javax.persistence.*;

@Entity
@Table(name = "car_park")
public class Car extends AbstractPersistentObject {

    @Column(name = "car_name")
    private String name;

    @Column(name = "car_number")
    private String number;

    @Column(name = "car_type")
    @Convert(converter = TypeConverter.class)
    private Type type;

    @Column(name = "car_consumption")
    private int consumption;

    public enum Type {
        CARCASE,
        FRIDGE,
        TANK
    }

    public Car() {
    }

    public Car(String name, String number, Type type, int consumption) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.consumption = consumption;
    }

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    @Convert
    public static class TypeConverter implements AttributeConverter<Type, String> {

        @Override
        public String convertToDatabaseColumn(Type attribute) {
            switch (attribute) {
                case CARCASE:
                    return "C";
                case TANK:
                    return "T";
                case FRIDGE:
                    return "F";
                default:
                    throw new IllegalArgumentException("Unknown " + attribute);
            }
        }

        @Override
        public Type convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "C":
                    return Type.CARCASE;
                case "T":
                    return Type.TANK;
                case "F":
                    return Type.FRIDGE;
                default:
                    throw new IllegalArgumentException("Unknown " + dbData);
            }
        }
    }
}