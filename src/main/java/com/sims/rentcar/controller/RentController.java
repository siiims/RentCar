package com.sims.rentcar.controller;

import com.sims.rentcar.model.Rent;
import com.sims.rentcar.repo.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RentController {

    @Autowired
    public RentRepository rentRepository;

    //add rent
    @PostMapping("/rent")
    public Rent createRent(@RequestBody Rent rent) {
        return rentRepository.save(rent);
    }

    //update rent
    @PutMapping("/rent")
    public Rent updateRent(@RequestBody Rent rent) {
        return rentRepository.save(rent);
    }
}
