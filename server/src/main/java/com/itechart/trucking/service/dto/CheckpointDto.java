package com.itechart.trucking.service.dto;

/**
 * A DTO representing a checkpoint.
 *
 * @author blink7
 * @version 1.2
 * @since 2017-12-10
 */
public class CheckpointDto extends AbstractDto {

    private String name;
    private String additionName;
    private String placeId;
    private Double lat;
    private Double lng;

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