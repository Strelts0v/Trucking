package com.itechart.trucking.dao.impl;


import com.itechart.trucking.Application;
import com.itechart.trucking.dao.CarDao;
import com.itechart.trucking.domain.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link JpaCarDao}
 *
 * @author vlad_sytyi
 * @version 1.0
 * @since 2017-11-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class JpaCarDaoTest {



    @Autowired
    private CarDao carDao;

    private Car car;

    private static String NAME="Renault";
    private static String NUMBER="2244 KE-7";
    private static Integer ID = 5;

    @Before
    public void setUp() {
        car = new Car();
        car.setName(NAME);
        car.setNumber(NUMBER);
        car.setId(ID);
    }

    @Test
    public void findCarByIdWhichNotExistsShouldThrowNullPointerException() throws Exception{
        Integer testId = 500;
        Optional<Car> testCar = carDao.findOne(testId);
        assertFalse("No object expected", testCar.isPresent());
    }

    @Test
    public void findOneShouldReturnCorrespondObject() throws Exception{
        int testId = 5;
        Optional<Car> testCar = carDao.findOne(testId);
        assertTrue("Expected an object", testCar.isPresent());
        assertEquals("Expected equals objects", car, testCar.orElse(new Car()));
    }




}
