package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Warehouse;

import java.util.List;
import java.util.Optional;

/**
 * @author Gleb Streltsov
 * @version 1.0
 * @since 2017-11-15
 */
public interface WarehouseDao {

    /**
     * get warehouse by id from storage
     * @param id - unique id of existed warehouse
     * @return Optional warehouse object
     */
    Optional<Warehouse> getWarehouseById(Integer id);

    /**
     * gets list of warehouses from storage
     * @param namePattern - part of warehouse name for searching
     * @return list of found warehouse objects
     */
    List<Warehouse> findWarehousesByName(String namePattern);

    /**
     * Returns all instances of the {@link Warehouse} by the given page number and size.
     * @param pageNumber - offset of instances
     * @param pageSize - count of instances in page
     * @return list of warehouse objects per page
     */
    List<Warehouse> getWarehousesByPage(int pageNumber, int pageSize);

    /**
     * gets count of warehouses in storage
     * @return count of warehouse objects in storage
     */
    int getWarehouseCount();

    /**
     * adds new warehouse to the storage
     * @param warehouse - new warehouse object without id, if warehouse
     * contains id, then that warehouse instance in storage will be updated
     * @return added warehouse with id
     */
    Warehouse addWarehouse(Warehouse warehouse);

    /**
     * updates existed warehouse in storage
     * @param warehouse - warehouse with id, if warehouse doesn't contain
     * id, warehouse object will be saved in storage
     */
    void updateWarehouse(Warehouse warehouse);

    /**
     * deletes existed warehouse in storage
     * @param warehouse - existed warehouse in storage
     */
    void deleteWarehouse(Warehouse warehouse);
}