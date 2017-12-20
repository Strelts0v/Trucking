package com.itechart.trucking.util.solr.service;

import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.dto.WarehouseSearchCriteriaDto;
import com.itechart.trucking.util.Utils;
import com.itechart.trucking.util.solr.document.SolrWarehouseDocumentDto;
import com.itechart.trucking.util.solr.repository.SolrWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolrWarehouseService{

    @Autowired
    private SolrWarehouseRepository solrWarehouseRepository;

    public void addWarehouse(Warehouse warehouse) {
        ArrayList<String> name = new ArrayList<>(); name.add(warehouse.getName());
        ArrayList<String> country = new ArrayList<>(); name.add(warehouse.getCountry());
        ArrayList<String> city = new ArrayList<>(); name.add(warehouse.getCity());
        ArrayList<String> street = new ArrayList<>(); name.add(warehouse.getStreet());
        ArrayList<String> house = new ArrayList<>(); name.add(warehouse.getHouse());
        ArrayList<String> lat = new ArrayList<>(); name.add(warehouse.getLat().toString());
        ArrayList<String> lng = new ArrayList<>(); name.add(warehouse.getLng().toString());

        SolrWarehouseDocumentDto warehouseDocument = new SolrWarehouseDocumentDto(
                warehouse.getId().toString(), name, country, city, street, house, lat, lng);
        solrWarehouseRepository.save(warehouseDocument);
    }

    public void updateWarehouse(Warehouse warehouse) {
        SolrWarehouseDocumentDto warehouseDocument = solrWarehouseRepository
                .findOne(warehouse.getId().toString());

        ArrayList<String> name = new ArrayList<>(); name.add(warehouse.getName());
        ArrayList<String> country = new ArrayList<>(); name.add(warehouse.getCountry());
        ArrayList<String> city = new ArrayList<>(); name.add(warehouse.getCity());
        ArrayList<String> street = new ArrayList<>(); name.add(warehouse.getStreet());
        ArrayList<String> house = new ArrayList<>(); name.add(warehouse.getHouse());

        warehouseDocument.setName(name);
        warehouseDocument.setCountry(country);
        warehouseDocument.setCity(city);
        warehouseDocument.setStreet(street);
        warehouseDocument.setHouse(house);
        solrWarehouseRepository.save(warehouseDocument);
    }

    public void deleteWarehouse(Warehouse warehouse) {
        SolrWarehouseDocumentDto warehouseDocument = solrWarehouseRepository
                .findOne(warehouse.getId().toString());
        solrWarehouseRepository.delete(warehouseDocument);
    }

    public List<Warehouse> searchWarehouses(WarehouseSearchCriteriaDto dto) {
        List<SolrWarehouseDocumentDto> docList = solrWarehouseRepository.searchWarehouses(
//                Utils.castToSearchPattern(dto.getName()),
//                Utils.castToSearchPattern(dto.getCountry()),
//                Utils.castToSearchPattern(dto.getCity()),
//                Utils.castToSearchPattern(dto.getStreet()),
//                Utils.castToSearchPattern(dto.getHouse())
                dto.getName(),
                dto.getCountry(),
                dto.getCity(),
                dto.getStreet()
        );

        return Utils.toWarehouseList(docList);
    }
}
