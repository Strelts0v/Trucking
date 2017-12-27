package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.CarDao;
import com.itechart.trucking.domain.Car;
import com.itechart.trucking.service.CarService;
import com.itechart.trucking.service.dto.CarDto;
import com.itechart.trucking.service.mapper.CarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Vlad Sytyi
 * @version 1.0
 * @since 2017-12-08
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
    private final CarDao carDao;
    private final CarMapper carMapper;

    @Autowired
    public CarServiceImpl(CarMapper carMapper, CarDao carDao) {
        this.carDao = carDao;
        this.carMapper = carMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        log.debug("Getting all cars from database");
        return carDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAllByPage(int offset, int recordPerPage) {
        log.debug("Getting all cars from database using pagination");
        return carDao.findAllByPage(offset, recordPerPage);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CarDto> findOne(Integer id) {
        log.debug("Getting only one car using ID field");
        return carDao.findOne(id).map(carMapper::carToCarDto);
    }
}
