package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.ClientDao;
import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.dao.ItemDao;
import com.itechart.trucking.domain.*;
import com.itechart.trucking.service.InvoiceService;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.service.mapper.InvoiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing invoices.
 *
 * @author blink7
 * @version 1.4
 * @since 2017-12-13
 */
@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final InvoiceDao invoiceDao;
    private final ItemDao itemDao;
    private final ClientDao clientDao;

    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceServiceImpl(InvoiceDao invoiceDao, ItemDao itemDao, ClientDao clientDao,
                              InvoiceMapper invoiceMapper) {

        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.clientDao = clientDao;
        this.invoiceMapper = invoiceMapper;
    }

    /**
     * Register a new Invoice by a Dispatcher.
     *
     * @param invoiceDto
     * @param currentUser
     * @return registered invoice.
     */
    @Override
    public Optional<InvoiceDto> registerInvoice(InvoiceDto invoiceDto, User currentUser) {
        Invoice newInvoice = new Invoice();
        newInvoice.setIssueDate(LocalDate.now());
        newInvoice.setCreator(currentUser);

        clientDao.getClientById(invoiceDto.getClient().getId())
                .ifPresent(newInvoice::setClient);

        invoiceDto.getConsignments()
                .forEach(itemConsignmentDto -> itemDao.findItemById(itemConsignmentDto.getItem().getId())
                        .ifPresent(item -> newInvoice.addItem(item, itemConsignmentDto.getAmount())));

        invoiceDao.save(newInvoice);
        log.debug("Registered a new Invoice: {}", newInvoice);
        return Optional.of(newInvoice).map(invoiceMapper::invoiceToInvoiceDto);
    }

    /**
     * Check the Invoice by a Manager
     *
     * @param invoiceDto
     * @param currentUser
     * @return checked invoice.
     */
    @Override
    public Optional<InvoiceDto> checkInvoice(InvoiceDto invoiceDto, User currentUser) {
        return invoiceDao.findOne(invoiceDto.getId())
                .map(invoice -> {
                    invoice.setCheckDate(LocalDate.now());
                    invoice.setInspector(currentUser);
                    invoice.setStatus(Invoice.Status.CHECKED);

                    invoice.getItemConsignments().forEach(itemConsignment ->
                            invoiceDto.getConsignments()
                                    .stream()
                                    .filter(it ->
                                            Objects.equals(
                                                    it.getItem().getId(),
                                                    itemConsignment.getItem().getId())
                                    ).findFirst()
                                    .ifPresent(itemConsignmentDto -> {
                                        itemConsignment.setAmount(itemConsignmentDto.getAmount());
                                        itemConsignment.setStatus(itemConsignmentDto.getStatus());
                                    }));

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
    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceDto> getInvoiceById(Integer id) {
        return invoiceDao.findOne(id).map(invoiceMapper::invoiceToInvoiceDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceDto> getAllInvoices(int pageNumber, int pageSize) {
        return invoiceDao.findAllByPage(pageNumber, pageSize).stream()
                .map(invoiceMapper::invoiceToInvoiceDtoForList)
                .collect(Collectors.toList());
    }

    /**
     * Update information for a specific invoice, and return the modified invoice.
     *
     * @param invoiceDto invoice to update
     * @return updated invoice.
     */
    @Override
    public Optional<InvoiceDto> updateInvoice(InvoiceDto invoiceDto) {
        return invoiceDao.findOne(invoiceDto.getId()).map(invoice -> {
            List<ItemConsignment> itemConsignments = invoice.getItemConsignments();
            itemConsignments.clear();
            invoiceDto.getConsignments()
                    .forEach(itemConsignmentDto -> itemDao.findItemById(itemConsignmentDto.getItem().getId())
                            .ifPresent(item -> invoice.addItem(
                                    item,
                                    itemConsignmentDto.getAmount(),
                                    itemConsignmentDto.getStatus())
                            )
                    );

            List<LossAct> lossActs = invoice.getLossActs();
            lossActs.clear();
            invoiceDto.getLossActs()
                    .forEach(lossActDto -> itemDao.findItemById(lossActDto.getItem().getId())
                            .ifPresent(item -> invoice.addLossAct(item, lossActDto.getAmount()))
                    );

            log.debug("Changed Information for Invoice: {}", invoice);
            return invoice;
        }).map(invoiceMapper::invoiceToInvoiceDto);
    }

    @Override
    public Optional<InvoiceDto> completeInvoice(InvoiceDto invoiceDto) {
        return invoiceDao.findOne(invoiceDto.getId()).map(invoice -> {
            invoice.setStatus(Invoice.Status.DELIVERED);
            invoice.getWaybill().setStatus(Waybill.Status.COMPLETED);
            invoice.getWaybill().getDriver().setBusy(false);

            invoice.getItemConsignments().forEach(itemConsignment ->
                    invoiceDto.getConsignments()
                            .stream()
                            .filter(ic ->
                                    Objects.equals(
                                            ic.getItem().getId(),
                                            itemConsignment.getItem().getId())
                            ).findFirst()
                            .ifPresent(itemConsignmentDto -> {
                                itemConsignment.setAmount(itemConsignmentDto.getAmount());
                                itemConsignment.setStatus(itemConsignmentDto.getStatus());
                            }));

            log.debug("Completed Invoice: {}", invoice);
            return invoice;
        }).map(invoiceMapper::invoiceToInvoiceDto);
    }

    @Override
    public Optional<InvoiceDto> createLossAct(InvoiceDto invoiceDto) {
        return invoiceDao.findOne(invoiceDto.getId()).map(invoice -> {
            invoice.setLossActIssueDate(LocalDate.now());

            invoiceDto.getLossActs()
                    .forEach(lossActDto -> itemDao.findItemById(lossActDto.getItem().getId())
                            .ifPresent(item -> {
                                invoice.addLossAct(item, lossActDto.getAmount());
                                invoice.getItemConsignments()
                                        .stream()
                                        .filter(ic ->
                                                Objects.equals(
                                                        ic.getItem().getId(),
                                                        lossActDto.getItem().getId())
                                        ).findFirst()
                                        .ifPresent(itemConsignment ->
                                                itemConsignment.setAmount(
                                                        itemConsignment.getAmount() - lossActDto.getAmount()));
                            })
                    );

            log.debug("Created Act of Loss: {}", invoice.getLossActs());
            return invoice;
        }).map(invoiceMapper::invoiceToInvoiceDto);
    }
}