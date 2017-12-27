package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Waybill;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A DTO representing a waybill.
 *
 * @author blink7
 * @version 1.6
 * @since 2017-12-16
 */
public class WaybillDto {

    private Integer id;
    @NotNull
    private Integer invoiceId;
    private String invoiceNumber;
    private String departureDate;
    @NotNull
    private UserDto driver;
    @NotNull
    private CarDto car;
    @NotNull
    private WarehouseDto from;
    @NotNull
    private WarehouseDto to;
    @NotEmpty
    @NotNull
    private List<WaybillCheckpointDto> waybillCheckpoints;
    private Waybill.Status status;
    private String issueDate;
    private Integer distance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public UserDto getDriver() {
        return driver;
    }

    public void setDriver(UserDto driver) {
        this.driver = driver;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public WarehouseDto getFrom() {
        return from;
    }

    public void setFrom(WarehouseDto from) {
        this.from = from;
    }

    public WarehouseDto getTo() {
        return to;
    }

    public void setTo(WarehouseDto to) {
        this.to = to;
    }

    public List<WaybillCheckpointDto> getWaybillCheckpoints() {
        return waybillCheckpoints;
    }

    public void setWaybillCheckpoints(List<WaybillCheckpointDto> waybillCheckpoints) {
        this.waybillCheckpoints = waybillCheckpoints;
    }

    public Waybill.Status getStatus() {
        return status;
    }

    public void setStatus(Waybill.Status status) {
        this.status = status;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "WaybillDto{" +
                "id=" + id +
                ", invoiceId=" + invoiceId +
                ", departureDate='" + departureDate + '\'' +
                ", driver=" + driver +
                ", car=" + car +
                ", from=" + from +
                ", to=" + to +
                ", waybillCheckpoints=" + waybillCheckpoints +
                ", status=" + status +
                ", issueDate='" + issueDate + '\'' +
                '}';
    }
}