package com.itechart.trucking.dao;

import com.itechart.trucking.domain.ItemConsignment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Quontico
 * @version 1.0
 * @since 2017-11-20
 */

public interface ItemConsignmentDao {
    List<ItemConsignment> findAll();

    List<ItemConsignment> findAllByPage(int pageNumber, int pageSize);

    Optional<ItemConsignment> findItemConsignmentById(Integer id);

    //Optional<ItemConsignment> findItemInItemConsignmentByName(String name);

    void editItemConsignment(ItemConsignment itemConsignment);

    void saveItemConsignment(ItemConsignment itemConsignment);

    void deleteItemConsignment(ItemConsignment itemConsignment);
}
