package com.itechart.trucking.service;

import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.service.dto.InvoiceSearchCriteriaDto;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    Optional<InvoiceDto> registerInvoice(InvoiceDto invoiceDto, User currentUser);
    Optional<InvoiceDto> checkInvoice(InvoiceDto invoiceDto, User currentUser);
    Optional<InvoiceDto> getInvoiceById(Integer id);
    Optional<InvoiceDto> updateInvoice(InvoiceDto invoiceDto);
    List<InvoiceDto> getAllInvoices(int pageNumber, int pageSize);
    Optional<InvoiceDto> completeInvoice(InvoiceDto invoiceDto);
    Optional<InvoiceDto> createLossAct(InvoiceDto invoiceDto);
    List<InvoiceDto> getInvoicesBySearch(InvoiceSearchCriteriaDto criteria, int pageNumber, int pageSize);
    int getSearchSize(InvoiceSearchCriteriaDto criteria);
}
