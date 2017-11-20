package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.CarDao;
import com.itechart.trucking.dao.ClientDao;
import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.dao.WaybillDao;
import com.itechart.trucking.domain.Car;
import com.itechart.trucking.domain.User;
import com.itechart.trucking.domain.Waybill;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 20.11.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class JpaCarDaoTest {

    @Autowired
    private ClientDao dao;

    @Autowired
    private CarDao car;

    @Autowired
    private UserDao user;



    @Autowired
    private WaybillDao waybill;
}
