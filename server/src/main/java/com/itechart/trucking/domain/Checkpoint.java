package com.itechart.trucking.domain;

import javax.persistence.*;

/**
 * @author blink7
 * @version 1.2
 * @since 2017-12-10
 */
@Entity
@Table(name = "checkpoints")
public class Checkpoint extends AbstractPersistentObject {

    @Column(name = "checkpoint_name")
    private String name;

    @Column(name = "checkpoint_addition_name")
    private String additionName;

    @Column(name = "checkpoint_place_id")
    private String placeId;

    @Column(name = "checkpoint_lat")
    private Double lat;

    @Column(name = "checkpoint_lng")
    private Double lng;

    public Checkpoint() {
    }

    public Checkpoint(String name, Double lat, Double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditionName() {
        return additionName;
    }

    public void setAdditionName(String additionName) {
        this.additionName = additionName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}