package com.aaronr92.cars.controller;

import com.aaronr92.cars.entity.CarManufacturer;
import com.aaronr92.cars.service.CarManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/manufacturers")
@RequiredArgsConstructor
public class CarManufacturerController {

    private final CarManufacturerService carManufacturerService;

    @GetMapping(path = "{id}")
    public ResponseEntity<CarManufacturer> getManufacturerById(
            @PathVariable Long id) {
        return ResponseEntity.ok(carManufacturerService.findCarManufacturerById(id));
    }

    @GetMapping
    public ResponseEntity<CarManufacturer> getManufacturerByName(
            @RequestParam String name) {
        return ResponseEntity.ok(carManufacturerService.findCarManufacturerByName(name));
    }

    @PostMapping
    public ResponseEntity<CarManufacturer> postCarManufacturer(
            @RequestBody CarManufacturer carManufacturer) {
        carManufacturer = carManufacturerService.addManufacturer(carManufacturer);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/v1/manufacturers")
                .toUriString());
        return ResponseEntity.created(uri).body(carManufacturer);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        carManufacturerService.deleteCarManufacturer(id);
        return ResponseEntity.noContent().build();
    }
}
