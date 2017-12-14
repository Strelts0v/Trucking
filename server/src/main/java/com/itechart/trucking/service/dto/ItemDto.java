package com.itechart.trucking.service.dto;

/**
 * A DTO representing a item.
 *
 * @author blink7
 * @version 1.3
 * @since 2017-12-13
 */
public class ItemDto extends AbstractDto {

    private String name;
    private Integer price;
    private String unitCode;

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

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", unitCode='" + unitCode + '\'' +
                '}';
    }
}