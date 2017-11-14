package com.itechart.entity;


public class CarPark {


    private int id;
    private String name;
    private String number;
    private Enum carType;
    private int consumption;

    public CarPark() {

    }

    public CarPark(int id, String name, String number, Enum carType, int consumption) {
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

    public Enum getCarType() {
        return carType;
    }

    public void setCarType(Enum carType) {
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

        CarPark carPark = (CarPark) o;

        if (id != carPark.id) return false;
        if (consumption != carPark.consumption) return false;
        if (name != null ? !name.equals(carPark.name) : carPark.name != null) return false;
        if (number != null ? !number.equals(carPark.number) : carPark.number != null) return false;
        return carType != null ? carType.equals(carPark.carType) : carPark.carType == null;
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
        return "CarPark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", carType=" + carType +
                ", consumption=" + consumption +
                '}';
    }

}
