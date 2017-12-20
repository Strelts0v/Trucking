package com.itechart.trucking.controller;

//import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.WarehouseService;
import com.itechart.trucking.service.dto.WarehouseDto;
import com.itechart.trucking.util.ResponseUtil;
import com.itechart.trucking.util.solr.service.SolrWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.itechart.trucking.util.Constants.NUMBER_REGEX;

/**
 * REST controller for managing warehouses.
 *
 * @author Quontico
 * @version 1.0
 * @since 2017-12-12
 */
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final WarehouseService warehouseService;

    private final SolrWarehouseService solrWarehouseService;

    @Autowired
    public WarehouseController (WarehouseService warehouseService, SolrWarehouseService solrWarehouseService) {
        this.warehouseService = warehouseService;
        this.solrWarehouseService = solrWarehouseService;
    }

    @GetMapping("/get_warehouse/{id: " + NUMBER_REGEX + "}")
    public ResponseEntity<WarehouseDto> getWarehouseById(@PathVariable int id) {
        log.debug("REST request to get an Warehouse for id: {}", id);
        return ResponseUtil.wrapOrNotFound(warehouseService.getWarehouse(id));
    }

    @GetMapping("/get_by_name")
    public ResponseEntity<List<WarehouseDto>> getWarehousesByName(@PathVariable String name) {
        log.debug("REST request to get an Warehouse by name: {}", name);
        final List<WarehouseDto> warehouseDtoList = warehouseService.getWarehousesByName(name);
        return new ResponseEntity<>(warehouseDtoList, HttpStatus.OK);
    }

    @GetMapping("/get_warehouses/{page:" + NUMBER_REGEX + "}/{size:" + NUMBER_REGEX + "}")
    public ResponseEntity<List<WarehouseDto>> getWarehousesByPage(@PathVariable int page, @PathVariable int size) {
        log.debug("REST request to get an Warehouses page {1} size {2}", page, size);
        final List<WarehouseDto> warehouseDtoList =
                warehouseService.getWarehousesByPage(page, size);
        log.debug("Return warehouses", page, size);
        return new ResponseEntity<>(warehouseDtoList, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalWarehousesSize() {
        log.debug("REST request to get warehouse count");
        final int size = warehouseService.getWarehouseCount();
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    @PostMapping("/add_warehouse")
    public ResponseEntity<Warehouse> addWarehouse (@RequestBody Warehouse warehouse){
        log.debug("REST request for adding new Warehouse: {}", warehouse);
        Warehouse warehouseWithId = warehouseService.addWarehouse(warehouse);
        log.debug("Added Warehouse: " + warehouseWithId);
        solrWarehouseService.addWarehouse(warehouse);
        return new ResponseEntity<>(warehouseWithId, HttpStatus.CREATED);
    }

    @PutMapping("/update_warehouse")
    public ResponseEntity<Warehouse> updateWarehouse (@RequestBody Warehouse warehouse){
        log.debug("REST request for updating Warehouse: {}", warehouse);
        warehouseService.updateWarehouse(warehouse);
        log.debug("Updated Warehouse: " + warehouse);
        solrWarehouseService.updateWarehouse(warehouse);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @PostMapping("/delete_warehouse")
    public ResponseEntity<Void> deleteWarehouse (@RequestBody Warehouse warehouse){
        log.debug("REST request for deleting Warehouse: {}", warehouse);
        warehouseService.deleteWarehouse(warehouse);
        log.debug("Deleted Warehouse: " + warehouse);
        solrWarehouseService.deleteWarehouse(warehouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search_warehouses")
    public  ResponseEntity<List<Warehouse>> searchWarehouses (@PathVariable String name, @PathVariable String country,
                                      @PathVariable String city, @PathVariable String street, @PathVariable String house) {
        log.debug("REST request to search Warehouses by its parameters: {}", name, country, city, street, house);
        final List<Warehouse> warehouseList = solrWarehouseService.searchWarehouses(name, country, city, street, house);
        return new ResponseEntity<>(warehouseList, HttpStatus.OK);
    }
}
