package com.itechart.trucking.domain;

import javax.persistence.*;

@Entity
@Table(name = "item_consignments")
public class ItemConsignment extends AbstractPersistentObject {

    @Column(name = "item_amount")
    private Integer amount;

    @Column(name = "item_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "items_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "invoices_id")
    private Invoice invoice;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
}