package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.service.dto.InvoiceDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link Invoice} and its DTO called {@link InvoiceDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-21
 */
@Mapper
public interface InvoiceMapper {

    InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    List<InvoiceDto> invoicesToInvoiceDtos(List<Invoice> invoices);
}