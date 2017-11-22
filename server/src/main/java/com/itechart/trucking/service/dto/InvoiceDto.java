package com.itechart.trucking.service.dto;

import java.util.List;

/**
 * A DTO representing an invoice.
 *
 * @author blink7
 * @version 1.3
 * @since 2017-11-22
 */
public class InvoiceDto {

    private Integer id;
    private String issueDate;
    private List<ItemConsignmentDto> consignments;
    private String status;
    private UserDto creator;
    private String checkDate;
    private UserDto inspector;
    private WaybillDto waybill;
    private List<LossActDto> lossActs;
    private String lossActIssueDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
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

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
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

    public String getLossActIssueDate() {
        return lossActIssueDate;
    }

    public void setLossActIssueDate(String lossActIssueDate) {
        this.lossActIssueDate = lossActIssueDate;
    }
}