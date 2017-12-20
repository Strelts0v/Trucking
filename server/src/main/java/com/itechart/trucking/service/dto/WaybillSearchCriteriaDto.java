package com.itechart.trucking.service.dto;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-12-18
 */
public class WaybillSearchCriteriaDto {

    private String from;
    private String to;
    private String invoiceNumber;
    private String issueDate;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "WaybillSearchCriteriaDto{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate='" + issueDate + '\'' +
                '}';
    }
}
