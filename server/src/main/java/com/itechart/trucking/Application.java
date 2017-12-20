package com.itechart.trucking;

import com.itechart.trucking.util.solr.document.SolrWarehouseDocument;
import com.itechart.trucking.util.solr.repository.SolrWarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private SolrWarehouseRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // Store customers
        repository.save(Arrays.asList(
                new SolrWarehouseDocument(1, "Warehouse #1", "Belarus", "Minsk", "Jasienina",25, 53.8393172, 27.4090443),
                new SolrWarehouseDocument(2, "Warehouse #2", "Russia", "Dmitrovskoye Shosse", "Jasienina", 22, 55.9413068, 37.5442847),
                new SolrWarehouseDocument(3, "Warehouse #1", "Russia", "Moges", "Jasienina",12, 54.627735, 39.7195548)));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
