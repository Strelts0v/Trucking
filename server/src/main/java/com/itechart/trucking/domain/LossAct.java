package com.itechart.trucking.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author blink7
 * @version 1.1
 * @since 2017-11-22
 */
@Entity
@Table(name = "act_of_loss")
public class LossAct implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "invoices_id")
    private Invoice invoice;

    @Id
    @ManyToOne
    @JoinColumn(name = "items_id")
    private Item item;

    @Column(name = "item_amount")
    private int amount;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}