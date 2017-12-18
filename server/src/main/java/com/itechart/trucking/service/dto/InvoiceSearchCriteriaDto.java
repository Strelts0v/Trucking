package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.Invoice;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-12-17
 */
public class InvoiceSearchCriteriaDto {

    private String issueDate;
    private String checkDate;
    private Invoice.Status status;
    private String inspector;

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Invoice.Status getStatus() {
        return status;
    }

    public void setStatus(Invoice.Status status) {
        this.status = status;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    @Override
    public String toString() {
        return "InvoiceSearchCriteriaDto{" +
                "issueDate=" + issueDate +
                ", checkDate=" + checkDate +
                ", status=" + status +
                ", inspector='" + inspector + '\'' +
                '}';
    }
}
