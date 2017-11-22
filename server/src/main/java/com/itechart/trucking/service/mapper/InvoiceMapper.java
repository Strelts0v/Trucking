package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.service.dto.InvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper for the entity {@link Invoice} and its DTO called {@link InvoiceDto}.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-22
 */
@Mapper
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "issueDate", target = "issueDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "checkDate", target = "checkDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "lossActIssueDate", target = "lossActIssueDate", dateFormat = "dd.MM.yyyy")
    InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    List<InvoiceDto> invoicesToInvoiceDtos(List<Invoice> invoices);
}