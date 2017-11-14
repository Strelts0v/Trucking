package com.itechart.entity;


public class WareHouse {

    private int id;
    private String name;
    private String country;
    private String city;
    private String street;
    private String house;
    private String lat;
    private String lng;

    public WareHouse() {
    }

    public WareHouse(int id, String name, String country, String city, String street, String house, String lat, String lng) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.lat = lat;
        this.lng = lng;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WareHouse wareHouse = (WareHouse) o;

        if (id != wareHouse.id) return false;
        if (name != null ? !name.equals(wareHouse.name) : wareHouse.name != null) return false;
        if (country != null ? !country.equals(wareHouse.country) : wareHouse.country != null) return false;
        if (city != null ? !city.equals(wareHouse.city) : wareHouse.city != null) return false;
        if (street != null ? !street.equals(wareHouse.street) : wareHouse.street != null) return false;
        if (house != null ? !house.equals(wareHouse.house) : wareHouse.house != null) return false;
        if (lat != null ? !lat.equals(wareHouse.lat) : wareHouse.lat != null) return false;
        return lng != null ? lng.equals(wareHouse.lng) : wareHouse.lng == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
