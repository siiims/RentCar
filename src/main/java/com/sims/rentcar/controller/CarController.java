package com.sims.rentcar.controller;

import com.sims.rentcar.model.Car;
import com.sims.rentcar.repo.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class CarController {

    @Autowired
    public CarRepository carRepository;
    
    public final static Logger logger = LoggerFactory.getLogger(CarController.class);

    // get a car
    @GetMapping("/car/{carId}")
    public Car getCar(@PathVariable Integer carId) {
        return carRepository.findById(carId).orElse(null);
    }


    //get all the cars
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carRepository.findAllByDeletedFalse();
    }

    //add a car
    @PostMapping("/car")
    public Car createCar(@RequestBody Car car) {
        logger.info("Creating a car");
        return carRepository.save(car);
    }

    //update a car
    @PutMapping("/car")
    public Car updateCar(@RequestBody Car car) {
        logger.debug("Updating a car");
        return carRepository.save(car);
    }

    //delete a car
    @DeleteMapping("/car/{carId}")
    public void deleteCar(@PathVariable Integer carId) {
        Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            car.setDeleted(true);
            carRepository.save(car);
        }
    }
}
