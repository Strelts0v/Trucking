package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Item;

/**
 * A DTO representing a item.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-20
 */
public class ItemDto {

    private Integer id;

    private String name;

    private Integer price;

    private Item.Type type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Item.Type getType() {
        return type;
    }

    public void setType(Item.Type type) {
        this.type = type;
    }
}