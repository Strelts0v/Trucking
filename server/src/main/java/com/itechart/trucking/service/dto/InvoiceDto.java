package com.itechart.trucking.service.dto;

import java.util.List;

/**
 * A DTO representing an invoice.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-19
 */
public class InvoiceDto {

    private List<ItemConsignmentDto> consignments;

    private List<LossActDto> lossActs;

    public List<ItemConsignmentDto> getConsignments() {
        return consignments;
    }

    public void setConsignments(List<ItemConsignmentDto> consignments) {
        this.consignments = consignments;
    }

    public List<LossActDto> getLossActs() {
        return lossActs;
    }

    public void setLossActs(List<LossActDto> lossActs) {
        this.lossActs = lossActs;
    }
}