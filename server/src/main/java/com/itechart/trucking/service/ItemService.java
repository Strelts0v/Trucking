package com.itechart.trucking.service;

import com.itechart.trucking.service.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> findAllItems();
}
