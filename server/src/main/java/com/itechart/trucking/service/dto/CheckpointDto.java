package com.itechart.trucking.service.dto;

/**
 * A DTO representing a checkpoint.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-23
 */
public class CheckpointDto extends AbstractDto {

    private String name;
    private String lat;
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