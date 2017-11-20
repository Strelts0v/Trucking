package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.ItemConsignment;

/**
 * A DTO representing a consignment.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-20
 */
public class ItemConsignmentDto {

    private ItemDto itemDto;

    private Integer amount;

    public ItemConsignmentDto(ItemConsignment itemConsignment) {
    }

    public ItemConsignmentDto(ItemDto itemDto, Integer amount) {
        this.itemDto = itemDto;
        this.amount = amount;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}