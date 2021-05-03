package com.sims.rentcar.controller;

import com.sims.rentcar.model.Car;
import com.sims.rentcar.model.User;
import com.sims.rentcar.repo.CarRepository;
import com.sims.rentcar.repo.RentRepository;
import com.sims.rentcar.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    public UserRepository userRepository;
    //privremeno
    @Autowired
    public CarRepository carRepository;
    @Autowired
    public RentRepository rentRepository;



    //add a user
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    //delete a user
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setDeleted(true);
            userRepository.save(user);
        }
    }
}
