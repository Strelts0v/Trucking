package com.itechart.trucking.util.solr.document;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.ArrayList;

@SolrDocument(solrCoreName = "warehouse")
public class SolrWarehouseDocumentDto {

    @Id
    @Field
    private String id;

    @Field
    private ArrayList<String> name;

    @Field
    private ArrayList<String> country;

    @Field
    private ArrayList<String> city;

    @Field
    private ArrayList<String> street;

    @Field
    private ArrayList<String> house;

    @Field
    private ArrayList<String> lat;

    @Field
    private ArrayList<String> lng;

    public SolrWarehouseDocumentDto(
            String id, ArrayList<String> name, ArrayList<String> country,
            ArrayList<String> city, ArrayList<String> street, ArrayList<String> house,
            ArrayList<String> lat, ArrayList<String> lng) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<String> country) {
        this.country = country;
    }

    public ArrayList<String> getCity() {
        return city;
    }

    public void setCity(ArrayList<String> city) {
        this.city = city;
    }

    public ArrayList<String> getStreet() {
        return street;
    }

    public void setStreet(ArrayList<String> street) {
        this.street = street;
    }

    public ArrayList<String> getHouse() {
        return house;
    }

    public void setHouse(ArrayList<String> house) {
        this.house = house;
    }

    public ArrayList<String> getLat() {
        return lat;
    }

    public void setLat(ArrayList<String> lat) {
        this.lat = lat;
    }

    public ArrayList<String> getLng() {
        return lng;
    }

    public void setLng(ArrayList<String> lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SolrWarehouseDocumentDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name=").append(name);
        sb.append(", country=").append(country);
        sb.append(", city=").append(city);
        sb.append(", street=").append(street);
        sb.append(", house=").append(house);
        sb.append(", lat=").append(lat);
        sb.append(", lng=").append(lng);
        sb.append('}');
        return sb.toString();
    }
}
