package com.itechart.trucking.controller;

import com.itechart.trucking.dao.ItemDao;
import com.itechart.trucking.service.ItemService;
import com.itechart.trucking.service.dto.ItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing items.
 *
 * @author Quontico
 * @version 1.0
 * @since 2017-12-12
 */
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/get_items")
    public ResponseEntity<List<ItemDto>> findAllItems() {
        final List<ItemDto> itemDtoList = itemService.findAllItems();
        return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
    }
}
