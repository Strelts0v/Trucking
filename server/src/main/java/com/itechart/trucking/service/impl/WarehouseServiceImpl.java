package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.WarehouseDao;
import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Gleb Streltsov
 * @version 1.0
 * @since 2017-11-19
 */
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseDao dao;

    @Override
    public Optional<Warehouse> getWarehouse(Integer id) {
        return dao.getWarehouseById(id);
    }

    @Override
    public List<Warehouse> getWarehousesByName(String namePattern) {
        return dao.findWarehousesByName(namePattern);
    }

    @Override
    public List<Warehouse> getWarehousesByPage(int pageNumber, int pageSize) {
        return dao.getWarehousesByPage(pageNumber, pageSize);
    }

    @Override
    public int getWarehouseCount() {
        return dao.getWarehouseCount();
    }

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        return dao.addWarehouse(warehouse);
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        dao.updateWarehouse(warehouse);
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) {
        dao.deleteWarehouse(warehouse);
    }
}
