package com.itechart.trucking.controller;


import com.itechart.trucking.domain.Car;
import com.itechart.trucking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

//    public List<Car>getCarsByPage(@RequestParam(value = "page", defaultValue = "1") String offset,String recordPerPage){
//        int offset = Integer.parseInt(page);
//        int recordsPerPage = Integer.parseInt(offset);
//        return carService.findAllByPage(recordsPerPage,)
//
//    }
    @RequestMapping("/get_cars")
    public List<Car>getAllCars(){
        return carService.findAll();
    }
}
