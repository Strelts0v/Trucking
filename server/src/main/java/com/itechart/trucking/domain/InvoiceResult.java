package com.itechart.trucking.domain;

import com.itechart.trucking.util.LocalDateAttributeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * An Invoice Result.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-12-16
 */
@Entity
@Table(name = "invoices_result")
public class InvoiceResult implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_driver")
    private User driver;

    @Column(name = "complete_date")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate completeDate;

    @Column(name = "income")
    private Float income;

    @Column(name = "consumption")
    private Float consumption;

    public InvoiceResult() {
    }

    public InvoiceResult(Invoice invoice, Float income, Float consumption) {
        this.invoice = invoice;
        driver = invoice.getWaybill().getDriver();
        completeDate = invoice.getCompleteDate();
        this.income = income;
        this.consumption = consumption;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Float getConsumption() {
        return consumption;
    }

    public void setConsumption(Float consumption) {
        this.consumption = consumption;
    }
}
