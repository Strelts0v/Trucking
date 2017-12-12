package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.WarehouseDao;
import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.WarehouseService;
import com.itechart.trucking.service.dto.WarehouseDto;
import com.itechart.trucking.service.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Gleb Streltsov
 * @version 1.0
 * @since 2017-11-19
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseDao warehouseDao;

    private final WarehouseMapper warehouseMapper;

    public WarehouseServiceImpl(WarehouseDao warehouseDao, WarehouseMapper warehouseMapper) {
        this.warehouseDao = warehouseDao;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public Optional<WarehouseDto> getWarehouse(Integer id) {
        return warehouseDao.getWarehouseById(id).map(warehouseMapper::warehouseToWarehouseDto);
    }

    @Override
    public List<WarehouseDto> getWarehousesByName(String namePattern) {
        return warehouseDao.findWarehousesByName(namePattern).stream()
                .map(warehouseMapper::warehouseToWarehouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WarehouseDto> getWarehousesByPage(int pageNumber, int pageSize) {
        return warehouseDao.getWarehousesByPage(pageNumber, pageSize).stream()
                .map(warehouseMapper::warehouseToWarehouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public int getWarehouseCount() {
        return warehouseDao.getWarehouseCount();
    }

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseDao.addWarehouse(warehouse);
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        warehouseDao.updateWarehouse(warehouse);
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) {
        warehouseDao.deleteWarehouse(warehouse);
    }
}
