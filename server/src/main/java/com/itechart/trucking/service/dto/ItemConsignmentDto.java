package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.ItemConsignment;

/**
 * A DTO representing a consignment.
 *
 * @author blink7
 * @version 1.3
 * @since 2017-12-13
 */
public class ItemConsignmentDto {

    private ItemDto item;
    private Integer amount;
    private ItemConsignment.Status status;

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ItemConsignment.Status getStatus() {
        return status;
    }

    public void setStatus(ItemConsignment.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemConsignmentDto{" +
                "item=" + item +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}