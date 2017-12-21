package com.itechart.trucking.util;

import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.util.solr.document.SolrWarehouseDocumentDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static LocalDate jsonToLocalDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
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

    public static List<Warehouse> toWarehouseList(List<SolrWarehouseDocumentDto> docList) {
        List<Warehouse> list = new ArrayList<>(docList.size());
        for(SolrWarehouseDocumentDto doc : docList) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(Integer.parseInt(doc.getId()));
            warehouse.setName(doc.getName().get(0));
            warehouse.setCountry(doc.getCountry().get(0));
            warehouse.setCity(doc.getCity().get(0));
            warehouse.setStreet(doc.getStreet().get(0));
            warehouse.setHouse(doc.getHouse().toString());
            warehouse.setLat(Double.parseDouble(doc.getLat().get(0)));
            warehouse.setLng(Double.parseDouble(doc.getLng().get(0)));
            list.add(warehouse);
        }
        return list;
    }
}
