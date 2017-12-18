package com.itechart.trucking.service.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.WarehouseService;
import com.itechart.trucking.service.dto.WarehouseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the ClientServiceImpl.
 *
 * @see ClientServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class WarehouseServiceImplTest {

    @Autowired
    private WarehouseService service;

    private Warehouse warehouse;

    private static final String WAREHOUSE_NAME = "warehouseName";

    @Before
    public void setUp(){
        warehouse = new Warehouse();
        warehouse.setName(WAREHOUSE_NAME);
    }

    @Test
    public void getWarehouseyNotExistedIdShouldReturnNoWarehouseTest() throws Exception {
        final int invalidWarehouseId = 5;
        Optional<WarehouseDto> warehouse = service.getWarehouse(invalidWarehouseId);

        final String errorMessage = "Expected empty optional warehouse object";
        Assert.assertFalse(errorMessage, warehouse.isPresent());
    }

    @Test
    public void getWarehouseByIdShouldReturnCorrespondWarehouse() throws Exception {
        Warehouse expectedWarehouse = service.addWarehouse(warehouse);
        final int addedWarehouseId = expectedWarehouse.getId();
        Optional<WarehouseDto> actualWarehouse = service.getWarehouse(addedWarehouseId);

        final String errorMessage = "Expected and actual warehouses are different";
        assertEquals(errorMessage, expectedWarehouse, actualWarehouse.orElse(new WarehouseDto()));
    }

    @Test
    public void findWarehousesByNameShouldReturnExactWarehouseCount() throws Exception {
        service.addWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName("test name");
        service.addWarehouse(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setName("test");
        service.addWarehouse(warehouse2);

        final String searchNamePattern = "name";
        List<WarehouseDto> warehouseList = service.getWarehousesByName(searchNamePattern);

        final int expectedWarehouseCount = 2;
        final String errorMessage = "Expected and actual warehouse size are different";
        assertEquals(errorMessage, expectedWarehouseCount, warehouseList.size());
    }

    @Test
    public void getWarehousesByExistedPageShouldReturnExactWarehouseCount() throws Exception {
        service.addWarehouse(warehouse);

        final int page = 1;
        final int pageSize = 20;
        List<WarehouseDto> warehouseList = service.getWarehousesByPage(page, pageSize);

        final int expectedWarehouseCount = 1;
        final String errorMessage = "Expected and actual warehouse size are different";
        assertEquals(errorMessage, expectedWarehouseCount, warehouseList.size());
    }

    @Test
    public void afterAddWarehouseGetWarehouseCountShouldReturnExactWarehouseCount() throws Exception {
        service.addWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName("test name");
        service.addWarehouse(warehouse1);

        final int actualWarehouseCount = service.getWarehouseCount();
        final int expectedWarehouseCount = 2;

        final String errorMessage = "Expected and actual warehouse size are different";
        assertEquals(errorMessage, expectedWarehouseCount, actualWarehouseCount);
    }

    @Test
    public void afterUpdateWarehouseGetWarehouseByIdShouldReturnCorrespondWarehouse() throws Exception {
        service.addWarehouse(warehouse);
        warehouse.setName("new test name");
        service.updateWarehouse(warehouse);

        Optional<WarehouseDto> actualWarehouse = service.getWarehouse(warehouse.getId());

        final String errorMessage = "Expected and actual warehouse are different";
        assertEquals(errorMessage, warehouse, actualWarehouse.orElse(new WarehouseDto()));
    }

    @Test
    public void afterDeleteWarehouseGetWarehouseCountShouldReturnCorrespondWarehouseCount() throws Exception {
        service.addWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName("test name");
        service.addWarehouse(warehouse1);

        service.deleteWarehouse(warehouse);

        final int expectedWarehouseCount = 1;
        final int actualWarehouseCount = service.getWarehouseCount();

        final String errorMessage = "Expected and actual warehouse size are different";
        assertEquals(errorMessage, expectedWarehouseCount, actualWarehouseCount);
    }

    @Test
    public void deleteWarehouseWithNoIdShouldNotThrowException() throws Exception {
        service.deleteWarehouse(warehouse);
    }
}