package com.itechart.trucking.controller;

import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.service.InvoiceService;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.itechart.trucking.util.Constants.*;

/**
 * REST controller for managing invoices.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-12-12
 */
@RestController
@RequestMapping("/api")
public class InvoiceController {

    private final Logger log = LoggerFactory.getLogger(InvoiceController.class);

    private final InvoiceService invoiceService;

    private final InvoiceDao invoiceDao;

    public InvoiceController(InvoiceService invoiceService, InvoiceDao invoiceDao) {
        this.invoiceService = invoiceService;
        this.invoiceDao = invoiceDao;
    }

    @GetMapping("/invoice/{id:" + NUMBER_REGEX + "}")
    public ResponseEntity<InvoiceDto> getInvoice(@PathVariable int id) {
        log.debug("REST request to get an Invoice for id: {}", id);
        return ResponseUtil.wrapOrNotFound(invoiceService.getInvoiceById(id));
    }

    @GetMapping("/invoices/{page:" + NUMBER_REGEX + "}/{size:" + NUMBER_REGEX + "}")
    public ResponseEntity<List<InvoiceDto>> getAllInvoices(@PathVariable int page, @PathVariable int size) {
        log.debug("REST request to get all Invoices for page: {} and size: {}", page, size);
        final List<InvoiceDto> invoices = invoiceService.getAllInvoices(page, size);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/invoices/size")
    public ResponseEntity<Integer> getTotalInvoicesSize() {
        final int size = invoiceDao.size();
        log.debug("REST request to get total Invoices size: {}", size);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }
}
