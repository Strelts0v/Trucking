package com.itechart.trucking.util;

import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.util.solr.document.SolrWarehouseDocument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static LocalDate jsonToLocalDate(String date) {
        if (date == null || date.isEmpty()) {
            return  null;
        }
//        return LocalDate.parse(date.substring(0, date.indexOf('T')), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String castToSearchPattern(String searchValue){
        String searchTemplate;
        if(searchValue == null) {
            searchTemplate = "%";
        }
        else if(searchValue.equals("")){
            searchTemplate = "%";
        } else {
            searchTemplate = "%" + searchValue + "%";
        }
        return searchTemplate;
    }

    public static List<Warehouse> toWarehouseList(List<SolrWarehouseDocument> docList) {
        List<Warehouse> list = new ArrayList<>(docList.size());
        for(SolrWarehouseDocument doc : docList) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(doc.getId());
            warehouse.setName(doc.getName());
            warehouse.setCountry(doc.getCountry());
            warehouse.setCity(doc.getCity());
            warehouse.setStreet(doc.getStreet());
            warehouse.setHouse(doc.getHouse().toString());
            warehouse.setLat(doc.getLat());
            warehouse.setLng(doc.getLng());
            list.add(warehouse);
        }
        return list;
    }
}
