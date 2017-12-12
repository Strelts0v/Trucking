package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Item;

import java.util.List;
import java.util.Optional;

/**
 * @author Quontico
 * @version 1.0
 * @since 2017-11-20
 */

public interface ItemDao {

    List<Item> findAllItems();

    List<Item> findAllItemsByPage(int pageNumber, int pageSize);

    Optional<Item> findItemById(Integer id);

    Optional<Item> findItemByName(String name);

    int getItemCount();

    Item addItem(Item item);

    void editItem(Item item);

    void saveItem(Item item);

    void deleteItem(Item item);
}
