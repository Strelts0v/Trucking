package com.itechart.trucking.util.solr.service;

import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.dto.WarehouseSearchCriteriaDto;
import com.itechart.trucking.util.solr.document.SolrWarehouseDocument;
import com.itechart.trucking.util.solr.repository.SolrWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolrWarehouseService{

    @Autowired
    private SolrWarehouseRepository solrWarehouseRepository;

    public void addWarehouse(Warehouse warehouse) {
        SolrWarehouseDocument warehouseDocument = new SolrWarehouseDocument(
                warehouse.getId(), warehouse.getName(), warehouse.getCountry(),
                warehouse.getCity(), warehouse.getStreet(), warehouse.getHouse(),
                warehouse.getLat(), warehouse.getLng());
        solrWarehouseRepository.save(warehouseDocument);
    }

    public void updateWarehouse(Warehouse warehouse) {
        SolrWarehouseDocument warehouseDocument = solrWarehouseRepository
                .findOne(warehouse.getId());
        warehouseDocument.setName(warehouse.getName());
        warehouseDocument.setCountry(warehouse.getCountry());
        warehouseDocument.setCity(warehouse.getCity());
        warehouseDocument.setStreet(warehouse.getStreet());
        warehouseDocument.setHouse(warehouse.getHouse());
        solrWarehouseRepository.save(warehouseDocument);
    }

    public void deleteWarehouse(Warehouse warehouse) {
        SolrWarehouseDocument warehouseDocument = solrWarehouseRepository
                .findOne(warehouse.getId());
        solrWarehouseRepository.delete(warehouseDocument);
    }

    public List<Warehouse> searchWarehouses(WarehouseSearchCriteriaDto dto) {
        return solrWarehouseRepository.searchWarehouses(dto.getName(), dto.getCountry(),
                dto.getCity(), dto.getStreet(), dto.getHouse());
    }
}
