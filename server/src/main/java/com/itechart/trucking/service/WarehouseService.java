package com.itechart.trucking.service;

import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.dto.WarehouseDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Gleb Streltsov
 * @version 1.0
 * @since 2017-11-19
 */
public interface WarehouseService {

    /**
     * get warehouse by id
     * @param id - unique id of existed warehouse
     * @return Optional warehouse object
     */
    Optional<WarehouseDto> getWarehouse(Integer id);

    /**
     * gets list of warehouses
     * @param namePattern - part of warehouse name for searching
     * @return list of found warehouse objects
     */
    List<WarehouseDto> getWarehousesByName(String namePattern);

    /**
     * Returns all instances of the {@link Warehouse} by the given page number and size.
     * @param pageNumber - offset of instances
     * @param pageSize - count of instances in page
     * @return list of warehouse objects per page
     */
    List<WarehouseDto> getWarehousesByPage(int pageNumber, int pageSize);

    /**
     * gets count of warehouses
     * @return count of warehouse objects
     */
    int getWarehouseCount();

    /**
     * adds new warehouse
     * @param warehouse - new warehouse object without id, if warehouse
     * contains id, then that warehouse instance will be updated
     * @return added warehouse with id
     */
    Warehouse addWarehouse(Warehouse warehouse);

    /**
     * updates existed warehouse
     * @param warehouse - warehouse with id, if warehouse doesn't contain
     * id, warehouse object will be saved
     */
    void updateWarehouse(Warehouse warehouse);

    /**
     * deletes existed warehouse in storage
     * @param warehouse - existed warehouse
     */
    void deleteWarehouse(Warehouse warehouse);
}
