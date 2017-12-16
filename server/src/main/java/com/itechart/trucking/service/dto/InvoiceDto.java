package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Invoice;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A DTO representing an invoice.
 *
 * @author blink7
 * @version 1.5
 * @since 2017-12-13
 */
public class InvoiceDto extends AbstractDto {

    @NotNull
    private ClientDto client;
    private String issueDate;
    @NotNull
    @NotEmpty
    private List<ItemConsignmentDto> consignments;
    private Invoice.Status status;
    private UserDto creator;
    private String checkDate;
    private UserDto inspector;
    private WaybillDto waybill;
    private List<LossActDto> lossActs;
    private String lossActIssueDate;

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
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

    public Invoice.Status getStatus() {
        return status;
    }

    public void setStatus(Invoice.Status status) {
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

    @Override
    public String toString() {
        return "InvoiceDto{" +
                "client=" + client +
                ", issueDate='" + issueDate + '\'' +
                ", consignments=" + consignments +
                ", status=" + status +
                ", creator=" + creator +
                ", checkDate='" + checkDate + '\'' +
                ", inspector=" + inspector +
                ", waybill=" + waybill +
                ", lossActs=" + lossActs +
                ", lossActIssueDate='" + lossActIssueDate + '\'' +
                '}';
    }
}