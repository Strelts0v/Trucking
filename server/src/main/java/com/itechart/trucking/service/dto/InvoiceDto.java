package com.itechart.trucking.service.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * A DTO representing an invoice.
 *
 * @author blink7
 * @version 1.2
 * @since 2017-11-21
 */
public class InvoiceDto {

    private Integer id;

    private LocalDate issueDate;

    private List<ItemConsignmentDto> consignments;

    private String status;

    private UserDto creator;

    private LocalDate checkDate;

    private UserDto inspector;

    private WaybillDto waybill;

    private List<LossActDto> lossActs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public List<ItemConsignmentDto> getConsignments() {
        return consignments;
    }

    public void setConsignments(List<ItemConsignmentDto> consignments) {
        this.consignments = consignments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public UserDto getInspector() {
        return inspector;
    }

    public void setInspector(UserDto inspector) {
        this.inspector = inspector;
    }

    public WaybillDto getWaybill() {
        return waybill;
    }

    public void setWaybill(WaybillDto waybill) {
        this.waybill = waybill;
    }

    public List<LossActDto> getLossActs() {
        return lossActs;
    }

    public void setLossActs(List<LossActDto> lossActs) {
        this.lossActs = lossActs;
    }
}