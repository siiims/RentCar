package com.sims.rentcar.repo;

import com.sims.rentcar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    //find all available cars
    public List<Car> findAllByDeletedFalse();

    //same as above
    @Query("select c from Car c where c.deleted=false")
    public List<Car> findNotDeleted();
}
