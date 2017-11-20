package com.itechart.trucking.service;

import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.dao.ItemDao;
import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.ItemConsignment;
import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.service.dto.ItemConsignmentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing invoices.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-19
 */
@Service
@Transactional
public class InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    private final InvoiceDao invoiceDao;
    private final ItemDao itemDao;

    public InvoiceService(InvoiceDao invoiceDao, ItemDao itemDao) {
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
    }

    /**
     * Register a new Invoice by a Dispatcher.
     *
     * @param invoiceDto
     * @param currentUser
     * @return the managed invoice.
     */
    public Invoice registerInvoice(InvoiceDto invoiceDto, User currentUser) {
        Invoice newInvoice = new Invoice();
        newInvoice.setIssueDate(LocalDate.now());
        newInvoice.setCreator(currentUser);

        List<ItemConsignment> newItemConsignments = new ArrayList<>(invoiceDto.getConsignments().size());
        for (ItemConsignmentDto itemConsignmentDto : invoiceDto.getConsignments()) {
            itemDao.findItemById(itemConsignmentDto.getItemDto().getId()).ifPresent(item -> {
                ItemConsignment newItemConsignment = new ItemConsignment();
                newItemConsignment.setItem(item);
                newItemConsignment.setAmount(itemConsignmentDto.getAmount());
                //TODO: Insert an Enum data type.
                newItemConsignment.setStatus("Accepted");
                newItemConsignments.add(newItemConsignment);
            });
        }
        newInvoice.setItemConsignments(newItemConsignments);

        invoiceDao.save(newInvoice);
        log.debug("Registered a new Invoice: {}", newInvoice);
        return newInvoice;
    }

    /**
     * Check the Invoice by a Manager
     *
     * @param invoiceDto
     * @param currentUser
     */
    public void checkInvoice(InvoiceDto invoiceDto, User currentUser) {
        invoiceDao.findOne(invoiceDto.getId()).ifPresent(invoice -> {
            invoice.setCheckDate(LocalDate.now());
            invoice.setInspector(currentUser);
            invoice.setStatus(Invoice.Status.CHECKED);
            log.debug("Checked the Invoice: {}", invoice);
        });
    }

    /**
     * Retrieves an invoice by its id.
     *
     * @param id
     * @return the invoice or {@literal null} if none found.
     */
    @Transactional(readOnly = true)
    public Invoice getInvoiceById(Integer id) {
        return invoiceDao.findOne(id).orElse(null);
    }
}