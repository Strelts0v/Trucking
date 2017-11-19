package com.itechart.trucking.service;

import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.Item;
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

    public InvoiceService(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    /**
     * Issue a new Invoice by a Dispatcher.
     *
     * @param invoiceDto
     * @param currentUser
     * @return the managed invoice.
     */
    public Invoice issueInvoice(InvoiceDto invoiceDto, User currentUser) {
        Invoice newInvoice = new Invoice();
        newInvoice.setIssueDate(LocalDate.now());
        newInvoice.setCreator(currentUser);

        List<ItemConsignment> newItemConsignments = new ArrayList<>(invoiceDto.getConsignments().size());
        for (ItemConsignmentDto itemConsignmentDto : invoiceDto.getConsignments()) {
            //TODO: Retrieve an Item from DB by id.
            Item item = null;
            ItemConsignment newItemConsignment = new ItemConsignment();
            newItemConsignment.setIdItem(item);
            newItemConsignment.setAmount(itemConsignmentDto.getAmount());
            //TODO: Insert an Enum.
            newItemConsignment.setStatus("Accepted");
            newItemConsignments.add(newItemConsignment);
        }
        newInvoice.setItemConsignments(newItemConsignments);

        invoiceDao.save(newInvoice);
        log.debug("Issued the new Invoice by a Manager: {}", newInvoice);
        return newInvoice;
    }
}