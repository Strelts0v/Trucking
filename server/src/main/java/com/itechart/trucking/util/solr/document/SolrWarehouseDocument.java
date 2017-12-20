package com.itechart.trucking.util.solr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "warehouse")
public class SolrWarehouseDocument {

    @Id
    @Indexed(name = "id", type = "integer")
    private Integer id;

    @Indexed (name = "name", type = "string")
    private String name;

    @Indexed (name = "country", type = "string")
    private String country;

    @Indexed (name = "city", type = "string")
    private String city;

    @Indexed (name = "street", type = "string")
    private String street;

    @Indexed (name = "house", type = "string")
    private String house;

    @Indexed (name = "lat", type = "double")
    private Double lat;

    @Indexed (name = "lng", type = "double")
    private Double lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public SolrWarehouseDocument(Integer id, String name, String country, String city, String street, String house, Double lat, Double lng) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SolrWarehouseDocument{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", house='").append(house).append('\'');
        sb.append(", lat=").append(lat);
        sb.append(", lng=").append(lng);
        sb.append('}');
        return sb.toString();
    }
}