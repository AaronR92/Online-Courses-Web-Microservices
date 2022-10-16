package com.aaronr92.cars.controller;

import com.aaronr92.cars.CarDto;
import com.aaronr92.cars.service.CarService;
import com.aaronr92.cars.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping(path = "{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.findCarById(carId));
    }

    @GetMapping
    public ResponseEntity<Car> getCarByName(@RequestParam String name) {
        return ResponseEntity.ok(carService.findCarByName(name));
    }

    @PostMapping
    public ResponseEntity<Car> addNewCar(@Valid @RequestBody CarDto carDto) {
        Car car = carService.addCar(carDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/v1/cars")
                .toUriString());
        return ResponseEntity.created(uri).body(car);
    }

    @DeleteMapping(path = "{carId}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,
                                         @RequestBody CarDto carDto) {
        Car car = carService.updateCar(id, carDto);
        return ResponseEntity.ok(car);
    }

}
