package com.itechart.trucking.controller;

//import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.WarehouseService;
import com.itechart.trucking.service.dto.WarehouseDto;
import com.itechart.trucking.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    private final WarehouseService warehouseService;

    public WarehouseController (WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<WarehouseDto> getWarehouseById(@PathVariable int id) {
        return ResponseUtil.wrapOrNotFound(warehouseService.getWarehouse(id));
    }

    @GetMapping("/get_by_name")
    public ResponseEntity<List<WarehouseDto>> getWarehousesByName(@PathVariable String name) {
        final List<WarehouseDto> warehouseDtoList = warehouseService.getWarehousesByName(name);
        return new ResponseEntity<>(warehouseDtoList, HttpStatus.OK);
    }

    @GetMapping("/get_by_page")
    public ResponseEntity<List<WarehouseDto>> getWarehousesByPage(@PathVariable int page, @PathVariable int size) {
        final List<WarehouseDto> warehouseDtoList = warehouseService.getWarehousesByPage(page, size);
        return new ResponseEntity<>(warehouseDtoList, HttpStatus.OK);
    }

    @GetMapping("/size")
    public ResponseEntity<Integer> getTotalWarehousesSize() {
        final int size = warehouseService.getWarehouseCount();
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    /*@GetMapping("/add")
    public Warehouse addWarehouse(@PathVariable )

    @GetMapping("/update")

    @GetMapping("/delete")*/
}
