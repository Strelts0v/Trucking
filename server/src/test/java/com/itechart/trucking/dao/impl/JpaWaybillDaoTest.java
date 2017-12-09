package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.WaybillDao;
import com.itechart.trucking.domain.*;
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
 * Test class for {@link JpaWaybillDao}
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class JpaWaybillDaoTest {

    @Autowired
    private EntityManager em;

    private WaybillDao waybillDao;

    private Waybill waybill;
    private Client client;
    private User driver;
    private Car car;
    private Warehouse from;
    private Warehouse to;

    private static final LocalDate ISSUE_DATE = LocalDate.of(2017, 11, 18);

    @Before
    public void setUp() {
        waybillDao = new JpaWaybillDao(em);

        waybill = new Waybill();
        waybill.setIssueDate(ISSUE_DATE);

        client = new Client();
        em.persist(client);
        waybill.setClient(client);

        driver = new User();
        em.persist(driver);
        waybill.setDriver(driver);

        car = new Car();
        car.setType(Car.Type.CARCASE);
        em.persist(car);
        waybill.setCar(car);

        from = new Warehouse();
        em.persist(from);
        waybill.setFrom(from);

        to = new Warehouse();
        em.persist(to);
        waybill.setTo(to);
    }

    @Test
    public void findOneShouldReturnCorrespondObject() throws Exception {
        waybillDao.save(waybill);

        Optional<Waybill> result = waybillDao.findOne(waybill.getId());

        assertTrue("Expected an object", result.isPresent());
        assertEquals("Expected equals objects", waybill, result.orElse(new Waybill()));
    }

    @Test
    public void findOneNonExistentIdShouldReturnNoObject() throws Exception {
        Optional<Waybill> result = waybillDao.findOne(666);

        assertFalse("No object expected", result.isPresent());
    }

    @Test
    public void afterUpdateFindOneShouldReturnCorrespondObject() throws Exception {
        waybillDao.save(waybill);
        waybill.setStatus(Waybill.Status.COMPLETED);

        Optional<Waybill> result = waybillDao.findOne(waybill.getId());

        assertEquals("Expected equals objects", waybill, result.orElse(new Waybill()));
    }

    @Test
    public void findAllAndSizeShouldReturnExactCount() throws Exception {
        int size = 5;
        for (int i = 0; i < size; i++) {
            Waybill waybill = new Waybill();
            waybill.setIssueDate(ISSUE_DATE);
            waybill.setClient(client);
            waybill.setDriver(driver);
            waybill.setCar(car);
            waybill.setFrom(from);
            waybill.setTo(to);
            waybillDao.save(waybill);
        }

        List<Waybill> waybills = waybillDao.findAll();
        int resultSize = waybillDao.size();
        assertSame("Expected the same size of the result list", size, waybills.size());
        assertSame("Expected the same size of the result list", size, resultSize);
    }

    @Test
    public void deleteTest() throws Exception {
        waybillDao.save(waybill);
        int id = waybill.getId();

        waybillDao.delete(waybill);

        Optional<Waybill> result = waybillDao.findOne(id);
        assertFalse("No object expected", result.isPresent());
    }
}