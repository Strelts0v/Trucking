package com.itechart.trucking;

import com.itechart.trucking.util.solr.document.SolrWarehouseDocumentDto;
import com.itechart.trucking.util.solr.repository.SolrWarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private SolrWarehouseRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        ArrayList<String> name1 = new ArrayList<>(); name1.add("Warehouse #1");
        ArrayList<String> name2 = new ArrayList<>(); name2.add("Warehouse #2");
        ArrayList<String> name3 = new ArrayList<>(); name3.add("Warehouse #3");

        ArrayList<String> country1 = new ArrayList<>(); country1.add("Belarus");
        ArrayList<String> country2 = new ArrayList<>(); country2.add("Russia");
        ArrayList<String> country3 = new ArrayList<>(); country3.add("Russia");

        ArrayList<String> city1 = new ArrayList<>(); city1.add("Minsk");
        ArrayList<String> city2 = new ArrayList<>(); city2.add("Moscow");
        ArrayList<String> city3 = new ArrayList<>(); city3.add("Ryazan");

        ArrayList<String> street1 = new ArrayList<>(); street1.add("Jasienina");
        ArrayList<String> street2 = new ArrayList<>(); street2.add("Dmitrovskoye Shosse");
        ArrayList<String> street3 = new ArrayList<>(); street3.add("Moges");

        ArrayList<String> house1 = new ArrayList<>(); house1.add("25");
        ArrayList<String> house2 = new ArrayList<>(); house2.add("22");
        ArrayList<String> house3 = new ArrayList<>(); house3.add("12");

        ArrayList<String> lat1 = new ArrayList<>(); lat1.add("53.8393172");
        ArrayList<String> lat2 = new ArrayList<>(); lat2.add("55.9413068");
        ArrayList<String> lat3 = new ArrayList<>(); lat3.add("54.627735");

        ArrayList<String> lng1 = new ArrayList<>(); lng1.add("27.4090443");
        ArrayList<String> lng2 = new ArrayList<>(); lng2.add("37.5442847");
        ArrayList<String> lng3 = new ArrayList<>(); lng3.add("39.7195548");

        // Store customers
        repository.save(Arrays.asList(
                new SolrWarehouseDocumentDto("1", name1, country1, city1, street1, house1, lat1, lng1),
                new SolrWarehouseDocumentDto("2", name2, country2, city2, street2, house2, lat2, lng2),
                new SolrWarehouseDocumentDto("3", name3, country3, city3, street3, house3, lat3, lng3)));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
