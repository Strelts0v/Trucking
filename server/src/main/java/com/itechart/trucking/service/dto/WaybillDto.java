package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Waybill;

import java.util.List;

/**
 * A DTO representing a waybill.
 *
 * @author blink7
 * @version 1.3
 * @since 2017-11-23
 */
public class WaybillDto {

    private Integer id;
    private Integer invoiceId;
    private ClientDto client;
    private String departureDate;
    private UserDto driver;
    private CarDto car;
    private WarehouseDto from;
    private WarehouseDto to;
    private List<WaybillCheckpointDto> waybillCheckpoints;
    private Waybill.Status status;
    private String issueDate;

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

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
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
}