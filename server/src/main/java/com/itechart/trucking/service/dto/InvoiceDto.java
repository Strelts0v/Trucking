package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A DTO representing an invoice.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-20
 */
public class InvoiceDto {

    private Integer id;

    private LocalDate issueDate;

    private List<ItemConsignmentDto> consignments;

    private Invoice.Status status = Invoice.Status.ISSUED;

    private UserDto creator;

    private LocalDate checkDate;

    private UserDto inspector;

    private WaybillDto waybill;

    private List<LossActDto> lossActs;

    //TODO: Implement the constructor.
    public InvoiceDto(Invoice invoice) {
        this(invoice.getId(), invoice.getIssueDate(),
                invoice.getItemConsignments().stream()
                        .map(ItemConsignmentDto::new)
                        .collect(Collectors.toList()),
                invoice.getStatus(),
                new UserDto(invoice.getCreator()),
                invoice.getCheckDate(),
                new UserDto(invoice.getInspector()),
                null,
                invoice.getLossActs().stream()
                        .map(LossActDto::new)
                        .collect(Collectors.toList()));
    }

    public InvoiceDto(Integer id, LocalDate issueDate, List<ItemConsignmentDto> consignments,
                      Invoice.Status status, UserDto creator, LocalDate checkDate, UserDto inspector,
                      WaybillDto waybill, List<LossActDto> lossActs) {
        this.id = id;
        this.issueDate = issueDate;
        this.consignments = consignments;
        this.status = status;
        this.creator = creator;
        this.checkDate = checkDate;
        this.inspector = inspector;
        this.waybill = waybill;
        this.lossActs = lossActs;
    }

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