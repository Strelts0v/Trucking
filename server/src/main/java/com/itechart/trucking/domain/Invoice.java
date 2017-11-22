package com.itechart.trucking.domain;

import com.itechart.trucking.util.LocalDateAttributeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * An Invoice.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-18
 */
@Entity
@Table(name = "invoices")
public class Invoice extends AbstractPersistentObject {

    @Column(name = "invoice_issuedate")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate issueDate;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemConsignment> itemConsignments;

    @Column(name = "invoice_status")
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ISSUED;

    @OneToOne
    @JoinColumn(name = "users_id_creator")
    private User creator;

    @Column(name = "invoice_checkdate")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate checkDate;

    @OneToOne
    @JoinColumn(name = "users_id_inspector")
    private User inspector;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "waybills_id")
    private Waybill waybill;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<LossAct> lossActs;

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public List<ItemConsignment> getItemConsignments() {
        return itemConsignments;
    }

    public void setItemConsignments(List<ItemConsignment> itemConsignments) {
        this.itemConsignments = itemConsignments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public User getInspector() {
        return inspector;
    }

    public void setInspector(User inspector) {
        this.inspector = inspector;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public List<LossAct> getLossActs() {
        return lossActs;
    }

    public void setLossActs(List<LossAct> lossActs) {
        this.lossActs = lossActs;
    }

    public boolean removeLossAct(LossAct lossAct) {
        boolean result = lossActs.remove(lossAct);
        lossAct.setInvoice(null);
        return result;
    }

    public enum Status {
        ISSUED,
        CHECKED,
        DELIVERED
    }

    @Convert
    public static class StatusConverter implements AttributeConverter<Status, String> {

        @Override
        public String convertToDatabaseColumn(Status attribute) {
            switch (attribute) {
                case ISSUED:
                    return "I";
                case CHECKED:
                    return "C";
                case DELIVERED:
                    return "D";
                default:
                    throw new IllegalArgumentException("Unknown " + attribute);
            }
        }

        @Override
        public Status convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "I":
                    return Status.ISSUED;
                case "C":
                    return Status.CHECKED;
                case "D":
                    return Status.DELIVERED;
                default:
                    throw new IllegalArgumentException("Unknown " + dbData);
            }
        }
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "issueDate=" + issueDate +
                ", itemConsignments=" + itemConsignments +
                ", status=" + status +
                ", creator=" + creator +
                ", checkDate=" + checkDate +
                ", inspector=" + inspector +
                ", waybill=" + waybill +
                ", lossActs=" + lossActs +
                '}';
    }
}