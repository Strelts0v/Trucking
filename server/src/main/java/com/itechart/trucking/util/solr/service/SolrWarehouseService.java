package com.itechart.trucking.util.solr.service;

import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.util.solr.document.SolrWarehouseDocument;
import com.itechart.trucking.util.solr.repository.SolrWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolrWarehouseService{

    private SolrWarehouseDocument solrWarehouseDocument;

    @Autowired
    private SolrWarehouseRepository solrWarehouseRepository;

    public void addWarehouse(Warehouse warehouse) {
        solrWarehouseDocument = new SolrWarehouseDocument(warehouse.getId(), warehouse.getName(), warehouse.getCountry(), warehouse.getCity(),
                                    warehouse.getStreet(), warehouse.getHouse(), warehouse.getLat(), warehouse.getLng());
        solrWarehouseRepository.save(solrWarehouseDocument);
    }

    public void updateWarehouse(Warehouse warehouse) {
        solrWarehouseDocument = solrWarehouseRepository.findOne(String.valueOf(warehouse.getId()));
        solrWarehouseDocument.setName(warehouse.getName());
        solrWarehouseDocument.setCountry(warehouse.getCountry());
        solrWarehouseDocument.setCity(warehouse.getCity());
        solrWarehouseDocument.setStreet(warehouse.getStreet());
        solrWarehouseDocument.setHouse(warehouse.getHouse());
        solrWarehouseRepository.save(solrWarehouseDocument);
    }

    public void deleteWarehouse(Warehouse warehouse) {
        solrWarehouseDocument = solrWarehouseRepository.findOne(String.valueOf(warehouse.getId()));
        solrWarehouseRepository.delete(solrWarehouseDocument);
    }

    public List<Warehouse> searchWarehouses(String name, String country, String city, String street,
                                            String house) {
        return solrWarehouseRepository.searchWarehouses(name, country, city, street, house);
    }
}
