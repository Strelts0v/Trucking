package com.itechart.trucking.service.dto;

/**
 * A DTO representing an act of loss.
 *
 * @author blink7
 * @version 1.3
 * @since 2017-11-23
 */
public class LossActDto {

    private ItemDto item;
    private Integer amount;

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
}