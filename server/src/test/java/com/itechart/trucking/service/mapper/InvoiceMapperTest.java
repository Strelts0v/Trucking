package com.itechart.trucking.service.mapper;

import com.itechart.trucking.Application;
import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.Waybill;
import com.itechart.trucking.service.dto.InvoiceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Test class for {@link InvoiceMapper}
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("dev_blink7")
public class InvoiceMapperTest {

    private static final LocalDate ISSUE_DATE = LocalDate.of(2017, 11, 18);

    @Test
    public void shouldMapInvoiceToDto() {
        Invoice invoice = new Invoice();
        invoice.setIssueDate(ISSUE_DATE);

        Waybill waybill = new Waybill();
        waybill.setIssueDate(ISSUE_DATE);
        invoice.setWaybill(waybill);

//        InvoiceDto invoiceDto = InvoiceMapper
    }
}