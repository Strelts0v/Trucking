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
 * @version 1.1
 * @since 2017-11-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("dev_blink7")
public class JpaInvoiceDaoTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private InvoiceDao invoiceDao;

    private User creator;
    private Invoice invoice;

    private static final String FIRST_NAME = "Gag";
    private static final String LAST_NAME = "Fag";
    private static final LocalDate ISSUE_DATE = LocalDate.of(2017, 11, 18);

    @Before
    public void setUp() {
        //TODO: Replace with UserDao implementation.
        creator = em.find(User.class, 1);

        invoice = new Invoice();
        invoice.setIssueDate(ISSUE_DATE);
        invoice.setCreator(creator);
    }

    @Test
    public void findOneShouldReturnCorrespondObject() throws Exception {
        assertNull("Expected an empty version", invoice.getVersion());

        invoiceDao.save(invoice);
        Optional<Invoice> result = invoiceDao.findOne(invoice.getId());
        assertTrue("Expected an object", result.isPresent());

        String errorMessage = "Expected equals objects";
        assertEquals(errorMessage, invoice, result.orElse(new Invoice()));
        assertEquals(errorMessage, invoice.getCreator(), result.orElse(new Invoice()).getCreator());
        assertNotNull("Expected not null generated version number", result.orElse(new Invoice()).getVersion());
    }

    @Test
    public void findOneNotExistedIdShouldReturnNoObject() throws Exception {
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
    public void findAllShouldReturnExactCount() throws Exception {
        int size = 5;
        for (int i = 0; i < size; i++) {
            Invoice invoice = new Invoice();
            invoice.setCreator(creator);
            invoice.setIssueDate(ISSUE_DATE);
            invoiceDao.save(invoice);
        }

        List<Invoice> invoices = invoiceDao.findAll();
        assertSame("Expected the same size of the result list", size, invoices.size());
    }
}