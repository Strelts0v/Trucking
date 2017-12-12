package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.ItemDao;
import com.itechart.trucking.service.ItemService;
import com.itechart.trucking.service.dto.ItemDto;
import com.itechart.trucking.service.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Service class for managing items.
 *
 * @author Quontico
 * @version 1.0
 * @since 2017-12-12
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao, ItemMapper itemMapper) {
        this.itemDao = itemDao;
        this.itemMapper = itemMapper;
    }

    @Override
    public List<ItemDto> findAllItems() {
        return itemDao.findAllItems().stream().map(itemMapper::itemToItemDto).collect(Collectors.toList());
    }
}
