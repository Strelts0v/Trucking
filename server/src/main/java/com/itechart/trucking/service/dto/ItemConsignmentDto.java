package com.itechart.trucking.service.dto;

/**
 * A DTO representing a consignment.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-19
 */
public class ItemConsignmentDto {

    private Integer itemId;

    private Integer amount;

    public ItemConsignmentDto() {
    }

    public ItemConsignmentDto(Integer itemId, Integer amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}