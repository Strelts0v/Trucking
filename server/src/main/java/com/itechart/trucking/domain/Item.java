package com.itechart.trucking.domain;

import javax.persistence.*;

/**
 * @author Quontico
 * @version 1.0
 * @since 2017-11-20
 */

@Entity
@Table(name = "items")
public class Item extends AbstractPersistentObject {

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_price")
    private Integer price;

    @Column(name = "item_type")
    @Convert(converter = TypeConverter.class)
    private Type type;

    public enum Type {
        COUNT,
        VOLUME
    }

    public Item() {
    }

    public Item(String name, Integer price, Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Convert
    public static class TypeConverter implements AttributeConverter<Type, String> {

        @Override
        public String convertToDatabaseColumn(Type attribute) {
            switch (attribute) {
                case COUNT:
                    return "C";
                case VOLUME:
                    return "V";
                default:
                    throw new IllegalArgumentException("Unknown " + attribute);
            }
        }

        @Override
        public Type convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "C":
                    return Type.COUNT;
                case "V":
                    return Type.VOLUME;
                default:
                    throw new IllegalArgumentException("Unknown " + dbData);
            }
        }
    }
}