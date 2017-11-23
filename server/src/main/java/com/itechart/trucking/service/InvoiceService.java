package com.itechart.trucking.service;

import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.dao.ItemDao;
import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.ItemConsignment;
import com.itechart.trucking.domain.LossAct;
import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.service.mapper.InvoiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing invoices.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-23
 */
@Service
@Transactional
public class InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    private final InvoiceDao invoiceDao;
    private final ItemDao itemDao;

    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceService(InvoiceDao invoiceDao, ItemDao itemDao, InvoiceMapper invoiceMapper) {
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.invoiceMapper = invoiceMapper;
    }

    /**
     * Register a new Invoice by a Dispatcher.
     *
     * @param invoiceDto
     * @param currentUser
     * @return the managed invoice.
     */
    public Optional<InvoiceDto> registerInvoice(InvoiceDto invoiceDto, User currentUser) {
        Invoice newInvoice = new Invoice();
        newInvoice.setIssueDate(LocalDate.now());
        newInvoice.setCreator(currentUser);

        invoiceDto.getItemConsignments()
                .forEach(itemConsignmentDto -> itemDao.findItemById(itemConsignmentDto.getItem().getId())
                        .ifPresent(item -> newInvoice.addItem(item, itemConsignmentDto.getAmount()))
                );

        invoiceDao.save(newInvoice);
        log.debug("Registered a new Invoice: {}", newInvoice);
        return Optional.of(newInvoice).map(invoiceMapper::invoiceToInvoiceDto);
    }

    /**
     * Check the Invoice by a Manager
     *
     * @param invoiceDto
     * @param currentUser
     * @return the managed invoice.
     */
    public Optional<InvoiceDto> checkInvoice(InvoiceDto invoiceDto, User currentUser) {
        return invoiceDao.findOne(invoiceDto.getId())
                .map(invoice -> {
                    invoice.setCheckDate(LocalDate.now());
                    invoice.setInspector(currentUser);
                    invoice.setStatus(Invoice.Status.CHECKED);
                    log.debug("Checked the Invoice: {}", invoice);
                    return invoice;
                }).map(invoiceMapper::invoiceToInvoiceDto);
    }

    /**
     * Retrieves an invoice by its id.
     *
     * @param id
     * @return the invoice.
     */
    @Transactional(readOnly = true)
    public Optional<InvoiceDto> getInvoiceById(Integer id) {
        return invoiceDao.findOne(id).map(invoiceMapper::invoiceToInvoiceDto);
    }

    @Transactional(readOnly = true)
    public List<InvoiceDto> getAllInvoices(int pageNumber, int pageSize) {
        return invoiceDao.findAllByPage(pageNumber, pageSize).stream()
                .map(invoiceMapper::invoiceToInvoiceDto)
                .collect(Collectors.toList());
    }

    /**
     * Update information for a specific invoice, and return the modified invoice.
     *
     * @param invoiceDto invoice to update
     * @return updated invoice
     */
    public Optional<InvoiceDto> updateInvoice(InvoiceDto invoiceDto) {
        return invoiceDao.findOne(invoiceDto.getId()).map(invoice -> {
            List<ItemConsignment> itemConsignments = invoice.getItemConsignments();
            itemConsignments.clear();
            invoiceDto.getItemConsignments()
                    .forEach(itemConsignmentDto -> itemDao.findItemById(itemConsignmentDto.getItem().getId())
                            .ifPresent(item -> invoice.addItem(
                                    item,
                                    itemConsignmentDto.getAmount(),
                                    itemConsignmentDto.getStatus())
                            )
                    );

            invoice.setStatus(invoiceDto.getStatus());

            List<LossAct> lossActs = invoice.getLossActs();
            lossActs.clear();
            invoiceDto.getLossActs()
                    .forEach(lossActDto -> itemDao.findItemById(lossActDto.getItem().getId())
                            .ifPresent(item -> invoice.addLossAct(new LossAct(item, lossActDto.getAmount())))
                    );

            log.debug("Changed Information for Invoice: {}", invoice);
            return invoice;
        }).map(invoiceMapper::invoiceToInvoiceDto);
    }
}