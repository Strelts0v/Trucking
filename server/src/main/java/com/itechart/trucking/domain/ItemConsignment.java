package com.itechart.trucking.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Quontico
 * @version 1.0
 * @since 2017-11-24
 */

@Entity
@Table(name = "item_consignments")
public class ItemConsignment implements Serializable {

    @Column(name = "item_amount")
    private Integer amount;

    @Column(name = "item_status")
    @Convert(converter = TypeConverter.class)
    private Status status;

    public enum Status {
        REGISTERED,
        CHECKED,
        DELIVERED,
        LOST
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "items_id")
    private Item item;

    @Id
    @ManyToOne
    @JoinColumn(name = "invoices_id")
    //private Invoice invoice;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /*public Invoice getInvoice() {
        return invoice;
    }*/

    /*public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }*/

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Convert
    public static class TypeConverter implements AttributeConverter<Status, String> {

        @Override
        public String convertToDatabaseColumn(Status attribute) {
            switch (attribute) {
                case REGISTERED:
                    return "C";
                case CHECKED:
                    return "V";
                case DELIVERED:
                    return "D";
                case LOST:
                    return "L";
                default:
                    throw new IllegalArgumentException("Unknown " + attribute);
            }
        }

        @Override
        public Status convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "R":
                    return Status.REGISTERED;
                case "C":
                    return Status.CHECKED;
                case "D":
                    return Status.DELIVERED;
                case "L":
                    return Status.LOST;
                default:
                    throw new IllegalArgumentException("Unknown " + dbData);
            }
        }
    }
}