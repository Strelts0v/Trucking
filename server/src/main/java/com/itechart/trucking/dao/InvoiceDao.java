package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.InvoiceResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author blink7
 * @version 1.2
 * @since 2017-12-17
 */
public interface InvoiceDao {

    /**
     * Returns all instances of the {@link Invoice}.
     *
     * @return all invoices
     */
    List<Invoice> findAll();

    /**
     * Returns all instances of the {@link Invoice} by the given page number and size.
     *
     * @param pageNumber
     * @param pageSize
     * @return all invoices per page.
     */
    List<Invoice> findAllByPage(int pageNumber, int pageSize);

    /**
     * Retrieves an invoice by its id.
     *
     * @param id
     * @return the invoice with the given id.
     */
    Optional<Invoice> findOne(Integer id);

    /**
     * Saves a given invoice.
     *
     * @param invoice to save
     */
    void save(Invoice invoice);

    /**
     * Deletes a given invoice.
     *
     * @param invoice to delete
     */
    void delete(Invoice invoice);

    /**
     * @return the number of invoices in the DB
     */
    int size();

    InvoiceResult saveResult(InvoiceResult result);

    List<Invoice> searchInvoices(LocalDate issueDate, LocalDate checkDate, Invoice.Status status, String inspector,
                                 int pageNumber, int pageSize);

    int searchSize(LocalDate issueDate, LocalDate checkDate, Invoice.Status status, String inspector);
}
