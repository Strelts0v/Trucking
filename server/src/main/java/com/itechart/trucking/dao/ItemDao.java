package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Quontico
 * @version 1.0
 * @since 2017-11-20
 */

public interface ItemDao {

    List<Item> findAll();

    List<Item> findAllByPage(int pageNumber, int pageSize);

    Optional<Item> findItemById(Integer id);

    Optional<Item> findItemByName(String name);

    void editItem(Item item);

    void saveItem(Item item);

    void deleteItem(Item item);
}
