package com.itechart.trucking.controller;

import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.service.InvoiceService;
import com.itechart.trucking.service.SecurityContextService;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.itechart.trucking.util.Constants.*;
import static com.itechart.trucking.auth.SecurityConstants.*;

/**
 * REST controller for managing invoices.
 *
 * @author blink7
 * @version 1.2
 * @since 2017-12-13
 */
@RestController
@RequestMapping("/api")
public class InvoiceController {

    private final Logger log = LoggerFactory.getLogger(InvoiceController.class);

    private final InvoiceService invoiceService;

    private final InvoiceDao invoiceDao;

    private final SecurityContextService securityContextService;

    public InvoiceController(InvoiceService invoiceService, InvoiceDao invoiceDao,
                             SecurityContextService securityContextService) {

        this.invoiceService = invoiceService;
        this.invoiceDao = invoiceDao;
        this.securityContextService = securityContextService;
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
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/invoices/size")
    public ResponseEntity<Integer> getTotalInvoicesSize() {
        final int size = invoiceDao.size();
        log.debug("REST request to get total Invoices size: {}", size);
        return ResponseEntity.ok(size);
    }

    @PostMapping("/invoice/register")
    @Secured(AUTHORIZED_ROLE_DISPATCHER)
    public ResponseEntity<InvoiceDto> registerInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {
        log.debug("REST request to register a new Invoice : {}", invoiceDto);
        return securityContextService.currentUser()
                .flatMap(user -> invoiceService.registerInvoice(invoiceDto, user))
                .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping("/invoice/check")
    @Secured(AUTHORIZED_ROLE_MANAGER)
    public ResponseEntity<InvoiceDto> checkInvoice(@RequestBody InvoiceDto invoiceDto) {
        log.debug("REST request to check the Invoice : {}", invoiceDto);
        return securityContextService.currentUser()
                .flatMap(user -> invoiceService.checkInvoice(invoiceDto, user))
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping("/invoice/complete")
    public ResponseEntity<InvoiceDto> completeInvoice(@RequestBody InvoiceDto invoiceDto) {
        log.debug("REST request to complete the Invoice : {}", invoiceDto);
        return securityContextService.currentUser()
                .flatMap(user -> invoiceService.completeInvoice(invoiceDto))
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping("/lossact/create")
    public ResponseEntity<InvoiceDto> createLossAct(@RequestBody InvoiceDto invoiceDto) {
        log.debug("REST request to create an Act of Loss : {}", invoiceDto);
        return securityContextService.currentUser()
                .flatMap(user -> invoiceService.createLossAct(invoiceDto))
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }
}
