package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.CarDao;
import com.itechart.trucking.dao.ClientDao;
import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.dao.WaybillDao;
import com.itechart.trucking.domain.Car;

import com.itechart.trucking.domain.User;
import com.itechart.trucking.domain.Waybill;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 20.11.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class JpaCarDaoTest {

    @Autowired
    private CarDao carDao;

    private Car car;

    @Before
    public void setUp() {
        car = new Car();
        car.setName("MAZ");
        car.setConsumption(50);
        car.setNumber("adu123AE");
        car.setType(Car.Type.TANK);
    }

    @Test
    public void getCarByIdShouldReturnNoCarTest() throws Exception {


        final int invalidCarId = 100500;
        Optional<Car> carTest = carDao.findOne(invalidCarId);

        final String errorMessage = "Expected empty optional car object";
        Assert.assertFalse(errorMessage, carTest.isPresent());

    }

    @Test
    public void getCarsShouldReturnNoCarTest() throws Exception {

        List<Car> carList = carDao.findAll();
        final String errorMessage = "Expected not empty optional car object";
        Assert.assertTrue(errorMessage,carList.isEmpty());
    }
}
