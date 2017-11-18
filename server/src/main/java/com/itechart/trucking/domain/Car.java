package com.itechart.trucking.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car_park")
public class Car implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcar")
    private int id;

    @Column(name = "car_name")
    private String name;

    @Column(name = "car_number")
    private String number;

    @Column(name = "car_type")
    @Convert(converter = TypeConverter.class)
    private CarType carType;

    @Column(name = "car_consumption")
    private int consumption;


    public enum CarType {

        CARCASE,
        FRIDGE,
        TANK
    }

    public Car() {

    }

    public Car(int id, String name, String number, CarType carType, int consumption) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.carType = carType;
        this.consumption = consumption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car carPark = (Car) o;

        if (id != carPark.id) return false;
        if (consumption != carPark.consumption) return false;
        if (name != null ? !name.equals(carPark.name) : carPark.name != null) return false;
        if (number != null ? !number.equals(carPark.number) : carPark.number != null) return false;
        return carType == carPark.carType;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (carType != null ? carType.hashCode() : 0);
        result = 31 * result + consumption;
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", carType=" + carType +
                ", consumption=" + consumption +
                '}';
    }

    @Convert
    public static class TypeConverter implements AttributeConverter<CarType, String> {

        @Override
        public String convertToDatabaseColumn(CarType attribute) {
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
        public  CarType convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "C":
                    return CarType.CARCASE;
                case "T":
                    return CarType.TANK;
                case "F":
                    return CarType.FRIDGE;
                default:
                    throw new IllegalArgumentException("Unknown " + dbData);
            }
        }
    }

}
