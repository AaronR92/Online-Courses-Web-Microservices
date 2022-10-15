package com.aaronr92.cars.repository;

import com.aaronr92.cars.entity.CarManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarManufacturerRepository extends JpaRepository<CarManufacturer, Long> {

    CarManufacturer findCarManufacturerByNameIgnoreCase(String name);
}