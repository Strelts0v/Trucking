package com.itechart.trucking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gleb Streltsov
 * @version 1.1
 * @since 2017-11-15
 */
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "warehouse_name")
    private String name;

    @Column(name = "warehouse_country")
    private String country;

    @Column(name = "warehouse_city")
    private String city;

    @Column(name = "warehouse_street")
    private String street;

    @Column(name = "warehouse_house")
    private String house;

    @Column(name = "warehouse_lat")
    private String lat;

    @Column(name = "warehouse_lng")
    private String lng;

    public Warehouse() {
    }

    public Warehouse(
            int id, String name, String country, String city, String street,
            String house, String lat, String lng) {
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

        Warehouse warehouse = (Warehouse) o;

        if (id != warehouse.id) return false;
        if (name != null ? !name.equals(warehouse.name) : warehouse.name != null) return false;
        if (country != null ? !country.equals(warehouse.country) : warehouse.country != null) return false;
        if (city != null ? !city.equals(warehouse.city) : warehouse.city != null) return false;
        if (street != null ? !street.equals(warehouse.street) : warehouse.street != null) return false;
        if (house != null ? !house.equals(warehouse.house) : warehouse.house != null) return false;
        if (lat != null ? !lat.equals(warehouse.lat) : warehouse.lat != null) return false;
        return lng != null ? lng.equals(warehouse.lng) : warehouse.lng == null;
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
        final StringBuffer sb = new StringBuffer("Warehouse{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", house='").append(house).append('\'');
        sb.append(", lat='").append(lat).append('\'');
        sb.append(", lng='").append(lng).append('\'');
        sb.append('}');
        return sb.toString();
    }
}