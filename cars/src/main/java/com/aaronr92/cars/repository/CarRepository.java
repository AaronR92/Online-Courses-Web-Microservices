package com.aaronr92.cars.repository;

import com.aaronr92.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findCarByName(String name);
}