package com.itechart.trucking.service;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.dao.ItemDao;
import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.Item;
import com.itechart.trucking.domain.ItemConsignment;
import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.service.dto.ItemConsignmentDto;
import com.itechart.trucking.service.dto.ItemDto;
import com.itechart.trucking.service.dto.UserDto;
import com.itechart.trucking.service.mapper.ItemMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link InvoiceService}
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ItemDao itemDao;

    private InvoiceDto invoiceDto;

    private User user;

    @Autowired
    private ItemMapper itemMapper;

    private static final String USER_NAME = "User";
    private static final String ITEM_NAME = "Item";
    private static final Integer ITEM_AMOUNT = 69;
    private static final Invoice.Status NEW_INVOICE_STATUS = Invoice.Status.DELIVERED;

    @Before
    public void setUp() {
        user = new User();
        user.setFirtstname(USER_NAME);
        userDao.addUser(user);

        Item item = new Item();
        item.setName(ITEM_NAME);
        item.setType(Item.Type.COUNT);
        itemDao.saveItem(item);

        invoiceDto = new InvoiceDto();
        invoiceDto.setLossActs(new ArrayList<>());

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        invoiceDto.setCreator(userDto);

        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        ItemConsignmentDto itemConsignmentDto = new ItemConsignmentDto();
        itemConsignmentDto.setItem(itemDto);
        itemConsignmentDto.setAmount(ITEM_AMOUNT);
        invoiceDto.setItemConsignments(Collections.singletonList(itemConsignmentDto));
    }

    @Test
    public void registerInvoiceShouldReturnRegisteredInvoiceWithId() {
        Optional<InvoiceDto> result = invoiceService.registerInvoice(invoiceDto, user);

        assertTrue("Expected the registered invoice", result.isPresent());

        assertNotNull("Expected some id value", result.get().getId());

        assertNotNull("Expected the containing user", result.get().getCreator());
        assertEquals("Expected the same user name", USER_NAME, result.get().getCreator().getFirtstname());

        assertEquals("Expected correct status", Invoice.Status.ISSUED, result.get().getStatus());

        assertNotNull("Expected non null ItemConsignment list", result.get().getItemConsignments());
        assertEquals("Expected the ItemConsignment list with size = 1",
                1,
                result.get().getItemConsignments().size());
        assertEquals("Expected the same item name",
                ITEM_NAME,
                result.get().getItemConsignments().get(0).getItem().getName());
        assertEquals("Expected the same item amount",
                ITEM_AMOUNT,
                result.get().getItemConsignments().get(0).getAmount());
        assertEquals("Expected correct status",
                ItemConsignment.Status.REGISTERED,
                result.get().getItemConsignments().get(0).getStatus());
    }

    @Test
    public void checkInvoiceShouldReturnCheckedOne() {
        Invoice invoice = new Invoice();
        invoice.setCreator(user);
        invoice.setIssueDate(LocalDate.now());
        invoiceDao.save(invoice);

        invoiceDto.setId(invoice.getId());

        Optional<InvoiceDto> result = invoiceService.checkInvoice(invoiceDto, user);

        assertTrue("Expected the checked invoice", result.isPresent());
        assertEquals("Expected the same user name", USER_NAME, result.get().getInspector().getFirtstname());
        assertEquals("Expected correct status", Invoice.Status.CHECKED, result.get().getStatus());
    }

    @Test
    public void checkInvoiceWithNonexistentIdShouldReturnNoInvoice() {
        invoiceDto.setId(666);

        Optional<InvoiceDto> result = invoiceService.checkInvoice(invoiceDto, user);

        assertFalse("No invoice expected", result.isPresent());
    }

    @Test
    public void getInvoiceByIdShouldReturnCorrespondOne() {
        Invoice invoice = new Invoice();
        invoice.setCreator(user);
        invoice.setIssueDate(LocalDate.now());
        invoiceDao.save(invoice);

        Optional<InvoiceDto> result = invoiceService.getInvoiceById(invoice.getId());

        assertTrue("Expected the invoice", result.isPresent());
    }

    @Test
    public void getInvoiceByIdShouldReturnNoObject() {
        Optional<InvoiceDto> result = invoiceService.getInvoiceById(666);

        assertFalse("Expected no invoice", result.isPresent());
    }

    @Test
    public void getAllInvoicesShouldReturnEmptyList() {
        List<InvoiceDto> result = invoiceService.getAllInvoices(1, 10);

        assertTrue("Expected the empty list", result.isEmpty());
    }

    @Test
    public void getAllInvoicesShouldReturnExactCount() {
        int size = 3;
        for (int i = 0; i < size; i++) {
            Invoice invoice = new Invoice();
            invoice.setCreator(user);
            invoice.setIssueDate(LocalDate.now());
            invoiceDao.save(invoice);
        }

        List<InvoiceDto> result = invoiceService.getAllInvoices(1, 10);

        assertEquals("Expected the same size of the result list", size, result.size());
    }

    @Test
    public void updateInvoiceShouldReturnUpdatedOne() {
        Invoice invoice = new Invoice();
        invoice.setCreator(user);
        invoice.setIssueDate(LocalDate.now());
        invoiceDao.save(invoice);

        invoiceDto.setId(invoice.getId());

        List<ItemConsignmentDto> itemConsignmentDtos = new ArrayList<>();
        int size = 3;
        for (int i = 0; i < size; i++) {
            Item item = new Item();
            item.setName(ITEM_NAME + " #" + i);
            item.setType(Item.Type.COUNT);
            itemDao.saveItem(item);

            ItemConsignmentDto itemConsignmentDto = new ItemConsignmentDto();
            itemConsignmentDto.setItem(itemMapper.itemToItemDto(item));
            itemConsignmentDtos.add(itemConsignmentDto);
        }
        invoiceDto.setItemConsignments(itemConsignmentDtos);

        invoiceDto.setStatus(NEW_INVOICE_STATUS);

        Optional<InvoiceDto> result = invoiceService.updateInvoice(invoiceDto);

        assertTrue("Expected the updated invoice", result.isPresent());

        assertEquals("Expected the same size of the ItemConsignment list",
                size,
                result.get().getItemConsignments().size());
        for (int i = 0; i < size; i++) {
            assertEquals("Expected the same item name",
                    ITEM_NAME + " #" + i,
                    result.get().getItemConsignments().get(i).getItem().getName());
        }

        assertEquals("Expected the new status value", NEW_INVOICE_STATUS, result.get().getStatus());
    }
}