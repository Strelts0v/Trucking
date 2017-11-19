package com.itechart.trucking.domain;

import javax.persistence.*;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-11-18
 */
@Entity
@Table(name = "checkpoints")
public class Checkpoint extends AbstractPersistentObject {

    @Column(name = "checkpoint_name")
    private String name;

    @Column(name = "checkpoint_lat")
    private String lat;

    @Column(name = "checkpoint_lng")
    private String lng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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