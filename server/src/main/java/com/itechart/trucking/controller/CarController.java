package com.itechart.trucking.controller;


import com.itechart.trucking.domain.Car;
import com.itechart.trucking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("/get_cars")
    public List<Car>getAllCars(){
        return carService.findAll();
    }
}
