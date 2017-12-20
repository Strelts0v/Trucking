package com.itechart.trucking.util.solr.repository;

import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.util.solr.document.SolrWarehouseDocument;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrWarehouseRepository extends SolrCrudRepository<SolrWarehouseDocument, String> {

    @Query("name:*?0* OR country:*?0* OR city:*?0* OR street:*?0* OR house:*?0*")
    List<Warehouse> searchWarehouses(String name, String country, String city,
                                     String street, String house);
}