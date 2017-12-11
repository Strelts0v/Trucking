package com.itechart.trucking.domain;

import com.itechart.trucking.util.LocalDateAttributeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author blink7
 * @version 1.2
 * @since 2017-12-11
 */
@Entity
@Table(name = "waybills")
public class Waybill extends AbstractPersistentObject {

    @OneToOne(mappedBy = "waybill", fetch = FetchType.EAGER)
    private Invoice invoice;

    @Column(name = "waybill_departure_date")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate departureDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id_driver")
    private User driver;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_park_id")
    private Car car;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouses_id_from")
    private Warehouse from;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouses_id_to")
    private Warehouse to;

    @OneToMany(mappedBy = "waybill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WaybillCheckpoint> waybillCheckpoints = new ArrayList<>();

    @Column(name = "waybill_status")
    @Convert(converter = StatusConverter.class)
    private Status status = Status.STARTED;

    @Column(name = "waybills_issuedate")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate issueDate;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Warehouse getFrom() {
        return from;
    }

    public void setFrom(Warehouse from) {
        this.from = from;
    }

    public Warehouse getTo() {
        return to;
    }

    public void setTo(Warehouse to) {
        this.to = to;
    }

    /**
     * Use this method only to get a {@link WaybillCheckpoint#checkpoint checkpoint}
     * and set {@link WaybillCheckpoint#checkDate checkDate} value.
     *
     * To add and remove a checkpoint use {@link #addCheckpoint(Checkpoint) addCheckpoint} method
     * and {@link #removeCheckpoint(Checkpoint) removeCheckpoint} method accordingly.
     *
     * @return a wrapper containing {@link Waybill} and {@link Checkpoint}.
     */
    public List<WaybillCheckpoint> getWaybillCheckpoints() {
        return waybillCheckpoints;
    }

    public void setWaybillCheckpoints(List<WaybillCheckpoint> waybillCheckpoints) {
        this.waybillCheckpoints = waybillCheckpoints;
    }

    public void addCheckpoint(Checkpoint checkpoint) {
        WaybillCheckpoint waybillCheckpoint = new WaybillCheckpoint(this, checkpoint);
        waybillCheckpoints.add(waybillCheckpoint);
    }

    public void removeCheckpoint(final Checkpoint checkpoint) {
        waybillCheckpoints.removeIf(waybillCheckpoint -> waybillCheckpoint.getWaybill().equals(this)
                && waybillCheckpoint.getCheckpoint().equals(checkpoint));
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Waybill{" +
                "invoice=" + invoice +
                ", departureDate=" + departureDate +
                ", driver=" + driver +
                ", car=" + car +
                ", from=" + from +
                ", to=" + to +
                ", waybillCheckpoints=" + waybillCheckpoints +
                ", status=" + status +
                ", issueDate=" + issueDate +
                '}';
    }

    public enum Status {
        STARTED,
        COMPLETED
    }

    @Convert
    public static class StatusConverter implements AttributeConverter<Status, String> {

        @Override
        public String convertToDatabaseColumn(Status attribute) {
            switch (attribute) {
                case STARTED:
                    return "S";
                case COMPLETED:
                    return "C";
                default:
                    throw new IllegalArgumentException("Unknown " + attribute);
            }
        }

        @Override
        public Status convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "S":
                    return Status.STARTED;
                case "C":
                    return Status.COMPLETED;
                default:
                    throw new IllegalArgumentException("Unknown " + dbData);
            }
        }
    }
}