package com.itechart.trucking.service.dto;

/**
 * A DTO representing an act of loss.
 *
 * @author blink7
 * @version 1.2
 * @since 2017-11-22
 */
public class LossActDto {

    private ItemDto item;
    private int amount;

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}