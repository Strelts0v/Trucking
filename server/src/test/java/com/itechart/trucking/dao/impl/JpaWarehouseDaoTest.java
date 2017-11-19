package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.WarehouseDao;
import com.itechart.trucking.domain.Warehouse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the JpaClientDao.
 *
 * @see JpaClientDao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class JpaWarehouseDaoTest {

    @Autowired
    private WarehouseDao dao;

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
        Optional<Warehouse> warehouse = dao.getWarehouseById(invalidWarehouseId);

        final String errorMessage = "Expected empty optional warehouse object";
        Assert.assertFalse(errorMessage, warehouse.isPresent());
    }

    @Test
    public void getWarehouseByIdShouldReturnCorrespondWarehouse() throws Exception {
        Warehouse expectedWarehouse = dao.addWarehouse(warehouse);
        final int addedWarehouseId = expectedWarehouse.getId();
        Optional<Warehouse> actualWarehouse = dao.getWarehouseById(addedWarehouseId);

        final String errorMessage = "Expected and actual warehouses are different";
        assertEquals(errorMessage, expectedWarehouse, actualWarehouse.orElse(new Warehouse()));
    }

    @Test
    public void findWarehousesByNameShouldReturnExactWarehouseCount() throws Exception {
        dao.addWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName("test name");
        dao.addWarehouse(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setName("test");
        dao.addWarehouse(warehouse2);

        final String searchNamePattern = "name";
        List<Warehouse> warehouseList = dao.findWarehousesByName(searchNamePattern);

        final int expectedWarehouseCount = 2;
        final String errorMessage = "Expected and actual warehouse size are different";
        assertEquals(errorMessage, expectedWarehouseCount, warehouseList.size());
    }

    @Test
    public void getWarehousesByExistedPageShouldReturnExactWarehouseCount() throws Exception {
        dao.addWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName("test name");
        dao.addWarehouse(warehouse1);

        final int page = 1;
        final int pageSize = 20;
        List<Warehouse> warehouseList = dao.getWarehousesByPage(page, pageSize);

        final int expectedWarehouseCount = 2;
        final String errorMessage = "Expected and actual warehouse size are different";
        assertEquals(errorMessage, expectedWarehouseCount, warehouseList.size());
    }

    @Test
    public void afterAddWarehouseGetWarehouseCountShouldReturnExactWarehouseCount() throws Exception {
        dao.addWarehouse(warehouse);
        final int actualWarehouseCount = dao.getWarehouseCount();
        final int expectedWarehouseCount = 1;

        final String errorMessage = "Expected and actual warehouse size are different";
        assertEquals(errorMessage, expectedWarehouseCount, actualWarehouseCount);
    }

    @Test
    public void afterUpdateWarehouseGetWarehouseByIdShouldReturnCorrespondWarehouse() throws Exception {
        dao.addWarehouse(warehouse);
        warehouse.setName("new test name");
        dao.updateWarehouse(warehouse);

        Optional<Warehouse> actualWarehouse = dao.getWarehouseById(warehouse.getId());

        final String errorMessage = "Expected and actual warehouse are different";
        assertEquals(errorMessage, warehouse, actualWarehouse.orElse(new Warehouse()));
    }

    @Test
    public void afterDeleteWarehouseGetWarehouseCountShouldReturnCorrespondWarehouseCount() throws Exception {
        dao.addWarehouse(warehouse);
        dao.deleteWarehouse(warehouse);
        final int expectedWarehouseCount = 0;
        final int actualWarehouseCount = dao.getWarehouseCount();

        final String errorMessage = "Expected and actual warehouse size are different";
        assertEquals(errorMessage, expectedWarehouseCount, actualWarehouseCount);
    }

    @Test
    public void deleteWarehouseWithNoIdShouldNotThrowException() throws Exception {
        dao.deleteWarehouse(warehouse);
    }

    @Ignore
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteWarehouseWithNotExistedIdShouldThrowException() throws Exception {
        warehouse.setId(1);
        dao.deleteWarehouse(warehouse);
    }

    @Ignore
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteWarehouseWithInvalidIdShouldThrowException() throws Exception {
        warehouse.setId(Integer.MAX_VALUE);
        dao.deleteWarehouse(warehouse);
    }
}