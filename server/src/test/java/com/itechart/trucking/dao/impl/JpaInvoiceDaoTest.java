package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link JpaInvoiceDao}
 *
 * @author blink7
 * @version 1.2
 * @since 2017-11-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class JpaInvoiceDaoTest {

    @Autowired
    private EntityManager em;

    private InvoiceDao invoiceDao;

    private User creator;
    private Invoice invoice;

    private static final String FIRST_NAME = "Gag";
    private static final String LAST_NAME = "Fag";
    private static final LocalDate ISSUE_DATE = LocalDate.of(2017, 11, 18);

    @Before
    public void setUp() {
        invoiceDao = new JpaInvoiceDao(em);

        creator = new User();
        creator.setFirstName(FIRST_NAME);
        creator.setLastName(LAST_NAME);
        //TODO: Replace with UserDao implementation.
        em.persist(creator);

        invoice = new Invoice();
        invoice.setIssueDate(ISSUE_DATE);
        invoice.setCreator(creator);
    }

    @Test
    public void findOneShouldReturnCorrespondObject() throws Exception {
        invoiceDao.save(invoice);

        Optional<Invoice> result = invoiceDao.findOne(invoice.getId());

        assertTrue("Expected an object", result.isPresent());
        assertEquals("Expected equals objects", invoice, result.orElse(new Invoice()));
    }

    @Test
    public void findOneNonExistentIdShouldReturnNoObject() throws Exception {
        Optional<Invoice> result = invoiceDao.findOne(666);

        assertFalse("No object expected", result.isPresent());
    }

    @Test
    public void afterUpdateFindOneShouldReturnCorrespondObject() throws Exception {
        invoiceDao.save(invoice);
        invoice.setStatus(Invoice.Status.CHECKED);

        Optional<Invoice> result = invoiceDao.findOne(invoice.getId());

        assertEquals("Expected equals objects", invoice, result.orElse(new Invoice()));
    }

    @Test
    public void findAllAndSizeShouldReturnExactCount() throws Exception {
        int size = 5;
        for (int i = 0; i < size; i++) {
            Invoice invoice = new Invoice();
            invoice.setCreator(creator);
            invoice.setIssueDate(ISSUE_DATE);
            invoiceDao.save(invoice);
        }

        List<Invoice> invoices = invoiceDao.findAll();
        int resultSize = invoiceDao.size();
        assertSame("Expected the same size of the result list", size, invoices.size());
        assertSame("Expected the same size of the result list", size, resultSize);
    }

    @Test
    public void deleteTest() throws Exception {
        invoiceDao.save(invoice);
        int id = invoice.getId();

        invoiceDao.delete(invoice);

        Optional<Invoice> result = invoiceDao.findOne(id);
        assertFalse("No object expected", result.isPresent());
    }
}