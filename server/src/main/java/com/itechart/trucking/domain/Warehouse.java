package com.itechart.trucking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gleb Streltsov
 * @version 1.1
 * @since 2017-11-15
 */
@Entity
@Table(name = "warehouses")
<<<<<<< HEAD
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
=======
public class Warehouse extends AbstractPersistentObject {
>>>>>>> dev

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
            String name, String country, String city, String street,
            String house, String lat, String lng) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.lat = lat;
        this.lng = lng;
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
}