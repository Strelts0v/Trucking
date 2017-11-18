package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    /**
     * gets all cars from database
     *
     * @return List of Cars
     */
    List<Car> findAll();

    /**
     * gets all cars from database with paging
     *
     * @param offset        - current page
     * @param recordPerPage - numbers of recordings in database
     * @return List of Cars for paging
     */
    List<Car> findAllByPage(int offset, int recordPerPage);

    /**
     * get car by id
     * @param id - id parameter of car
     * @return Optional Car object
     */
    Optional<Car> findOne(int id);
}
