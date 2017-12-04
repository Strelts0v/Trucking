package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Item;

/**
 * A DTO representing a item.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-23
 */
public class ItemDto extends AbstractDto {

    private String name;
    private Integer price;
    private Item.Type type;

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