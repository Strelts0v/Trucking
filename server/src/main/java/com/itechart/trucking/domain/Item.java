package com.itechart.trucking.domain;

import javax.persistence.*;

/**
 * @author Quontico
 * @version 1.2
 * @since 2017-12-13
 */
@Entity
@Table(name = "items")
public class Item extends AbstractPersistentObject {

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_price")
    private Integer price;

    @OneToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private ItemUnit unit;

    public Item() {
    }

    public Item(String name, Integer price, ItemUnit unit) {
        this.name = name;
        this.price = price;
        this.unit = unit;
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

    public ItemUnit getUnit() {
        return unit;
    }

    public void setUnit(ItemUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", unit=" + unit +
                '}';
    }
}