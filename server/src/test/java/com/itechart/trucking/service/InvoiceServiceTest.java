package com.itechart.trucking.service;

import com.itechart.trucking.Application;
import com.itechart.trucking.domain.Item;
import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.service.dto.ItemConsignmentDto;
import com.itechart.trucking.service.dto.ItemDto;
import com.itechart.trucking.service.dto.UserDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link InvoiceService}
 *
 * @author blink7
 * @version 0.1
 * @since 2017-11-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("dev_blink7")
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    private InvoiceDto invoiceDto;

    private User user;

    private static final Integer USER_ID = 666;
    private static final String USER_NAME = "User";
    private static final Integer ITEM_ID = 7;
    private static final String ITEM_NAME = "Item";
    private static final Integer ITEM_AMOUNT = 69;

    @Before
    public void setUp() {
        invoiceDto = new InvoiceDto();

        UserDto userDto = new UserDto();
        userDto.setId(USER_ID);
        invoiceDto.setCreator(userDto);

        ItemDto itemDto = new ItemDto();
        itemDto.setId(ITEM_ID);
        ItemConsignmentDto itemConsignmentDto = new ItemConsignmentDto();
        itemConsignmentDto.setItem(itemDto);
        itemConsignmentDto.setAmount(ITEM_AMOUNT);
        invoiceDto.setItemConsignments(Collections.singletonList(itemConsignmentDto));

        user = new User();
        user.setId(USER_ID);
        user.setFirtstname(USER_NAME);

        Item item = new Item();
        item.setId(ITEM_ID);
        item.setName(ITEM_NAME);
    }

    @Test
    @Ignore
    public void registerInvoiceShouldReturnRegisteredInvoiceWithId() {
        Optional<InvoiceDto> result = invoiceService.registerInvoice(invoiceDto, user);

        assertTrue("Expected the registered invoice", result.isPresent());

        assertNotNull("Expected the containing user", result.get().getCreator());
        assertEquals("Expected the same user id", USER_ID, result.get().getCreator().getId());
        assertEquals("Expected the same user name", USER_NAME, result.get().getCreator().getFirtstname());

        assertNotNull("Expected non null ItemConsignment list", result.get().getItemConsignments());
        assertEquals("Expected the ItemConsignment list with size = 1",
                1,
                result.get().getItemConsignments().size());
        assertEquals("Expected the same item id",
                ITEM_ID,
                result.get().getItemConsignments().get(0).getItem().getId());
        assertEquals("Expected the same item name",
                ITEM_NAME,
                result.get().getItemConsignments().get(0).getItem().getName());
        assertEquals("Expected the same item amount",
                ITEM_AMOUNT,
                result.get().getItemConsignments().get(0).getAmount());
    }
}