package com.itechart.trucking.service.mapper;

import com.itechart.trucking.Application;
import com.itechart.trucking.domain.*;
import com.itechart.trucking.service.dto.WaybillDto;
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
 * Test class for {@link WaybillMapper}
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class WaybillMapperTest {

    @Autowired
    private WaybillMapper waybillMapper;

    private static final Integer INVOICE_ID = 666;
    private static final String CLIENT_NAME = "Client";
    private static final LocalDate DEPARTURE_DATE = LocalDate.of(2017, 11, 18);
    private static final String DRIVER_NAME = "Driver";
    private static final String CAR_NAME = "Car";
    private static final String WAREHOUSE_FROM_NAME = "From";
    private static final String CHECKPOINT_NAME = "Checkpoint";
    private static final LocalDate ISSUE_DATE = LocalDate.of(2018, 12, 19);

    @Test
    public void shouldMapWaybillToWaybillDto() {
        // Preparation
        Waybill waybill = new Waybill();
        waybill.setClient(new Client(CLIENT_NAME));
        waybill.setDepartureDate(DEPARTURE_DATE);

        Invoice invoice = new Invoice();
        invoice.setId(INVOICE_ID);
        waybill.setInvoice(invoice);

        User driver = new User();
        driver.setFirtstname(DRIVER_NAME);
        waybill.setDriver(driver);

        Car car = new Car();
        car.setName(CAR_NAME);
        waybill.setCar(car);

        Warehouse warehouseFrom = new Warehouse();
        warehouseFrom.setName(WAREHOUSE_FROM_NAME);
        waybill.setFrom(warehouseFrom);

        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setName(CHECKPOINT_NAME);
        waybill.addCheckpoint(checkpoint);

        waybill.setIssueDate(ISSUE_DATE);

        WaybillDto waybillDto = waybillMapper.waybillToWaybillDto(waybill);

        // Tests
        assertNotNull("Expected the converted waybill", waybillDto);

        assertNotNull("Expected the converted client within the waybill", waybillDto.getClient());
        assertEquals("Expected the same name of the client after converting",
                CLIENT_NAME, waybillDto.getClient().getName());

        assertEquals("Expected the same string representation of the date",
                DEPARTURE_DATE.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)),
                waybillDto.getDepartureDate());

        assertEquals("Expected the id of the nested invoice", INVOICE_ID, waybillDto.getInvoiceId());

        assertNotNull("Expected the converted user (driver)", waybillDto.getDriver());
        assertEquals("Expected the same first name of the user(driver) after converting",
                DRIVER_NAME, waybillDto.getDriver().getFirtstname());

        assertNotNull("Expected the converted car", waybillDto.getCar());
        assertEquals("Expected the same name of the car after converting",
                CAR_NAME, waybillDto.getCar().getName());

        assertNotNull("Expected the converted warehouse (from)", waybillDto.getFrom());
        assertEquals("Expected the same name of the warehouse after converting",
                WAREHOUSE_FROM_NAME, waybillDto.getFrom().getName());

        assertNotNull("Expected non null WaybillCheckpoint list", waybillDto.getWaybillCheckpoints());
        assertFalse("Expected non empty WaybillCheckpoint list", waybillDto.getWaybillCheckpoints().isEmpty());
        assertEquals("Expected the same name of the checkpoint after converting",
                CHECKPOINT_NAME, waybillDto.getWaybillCheckpoints().get(0).getCheckpoint().getName());

        assertEquals("Expected the same status name", waybill.getStatus(), waybillDto.getStatus());

        assertEquals("Expected the same string representation of the date",
                ISSUE_DATE.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)), waybillDto.getIssueDate());
    }
}