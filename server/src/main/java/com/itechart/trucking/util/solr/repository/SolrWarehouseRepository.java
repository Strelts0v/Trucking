package com.itechart.trucking.util.solr.repository;

import com.itechart.trucking.util.solr.document.SolrWarehouseDocument;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrWarehouseRepository extends SolrCrudRepository<SolrWarehouseDocument, Integer> {

    // @Query("name:*?0* AND country:*?1* AND city:*?2* AND street:*?3* AND house:*?4*")
    @Query("name:*?0* AND country:*?1* AND city:*?2* AND street:*?3*")
    List<SolrWarehouseDocument> searchWarehouses(String name, String country, String city,
                                                 String street);

    //Page<Product> findByNameOrDescription(@Boost(2) String name, String description, Pageable page);

//    List<SolrWarehouseDocument> searchWarehouses(@Boost(5) String name, String country,
//                                                 String city, String street, String house);

}