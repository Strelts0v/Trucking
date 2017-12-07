package com.itechart.trucking.service.mapper;

import com.itechart.trucking.Application;
import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.Item;
import com.itechart.trucking.domain.LossAct;
import com.itechart.trucking.domain.Waybill;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Test class for {@link InvoiceMapper}
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class InvoiceMapperTest {

    @Autowired
    private InvoiceMapper invoiceMapper;

    private static final LocalDate ISSUE_DATE = LocalDate.of(2017, 11, 18);
    private static final String ITEM_NAME = "Item";
    private static final Integer ITEM_AMOUNT = 7;
    private static final LocalDate CHECK_DATE = LocalDate.of(2016, 10, 17);
    private static final LocalDate LOSS_ACT_DATE = LocalDate.of(2018, 12, 19);

    @Test
    public void shouldMapInvoiceToDto() {
        // Preparation
        Invoice invoice = new Invoice();
        invoice.setIssueDate(ISSUE_DATE);

        Item item = new Item();
        item.setName(ITEM_NAME);
        invoice.addItem(item, ITEM_AMOUNT);

        invoice.setCheckDate(CHECK_DATE);

        Waybill waybill = new Waybill();
        invoice.setWaybill(waybill);

        LossAct lossAct = new LossAct();
        lossAct.setItem(item);
        lossAct.setAmount(ITEM_AMOUNT);
        invoice.addLossAct(lossAct);

        invoice.setLossActIssueDate(LOSS_ACT_DATE);

        InvoiceDto invoiceDto = invoiceMapper.invoiceToInvoiceDto(invoice);

        // Tests
        assertNotNull("Expected the converted checkpoint", invoiceDto);
        assertEquals("Expected the same string representation of the date",
                ISSUE_DATE.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)), invoiceDto.getIssueDate());

        assertNotNull("Expected non null ItemConsignment list", invoiceDto.getItemConsignments());
        assertFalse("Expected non empty ItemConsignment list", invoiceDto.getItemConsignments().isEmpty());
        assertEquals("Expected the same name of the item after converting",
                ITEM_NAME, invoiceDto.getItemConsignments().get(0).getItem().getName());
        assertEquals("Expected the same amount of the items after converting",
                ITEM_AMOUNT, invoiceDto.getItemConsignments().get(0).getAmount());

        assertEquals("Expected the same string representation of the date",
                CHECK_DATE.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)), invoiceDto.getCheckDate());

        assertNotNull("Expected the converted waybill", invoiceDto.getWaybill());

        assertNotNull("Expected non null LossAct list", invoiceDto.getLossActs());
        assertFalse("Expected non empty LossAct list", invoiceDto.getLossActs().isEmpty());
        assertEquals("Expected the same name of the item after converting",
                ITEM_NAME, invoiceDto.getLossActs().get(0).getItem().getName());
        assertEquals("Expected the same amount of the items after converting",
                ITEM_AMOUNT, invoiceDto.getLossActs().get(0).getAmount());

        assertEquals("Expected the same string representation of the date",
                LOSS_ACT_DATE.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)),
                invoiceDto.getLossActIssueDate());
    }
}