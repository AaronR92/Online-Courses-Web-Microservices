package com.aaronr92.cars.service;

import com.aaronr92.cars.entity.CarManufacturer;
import com.aaronr92.cars.repository.CarManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
@RequiredArgsConstructor
public class CarManufacturerService {

    private final CarManufacturerRepository manufacturerRepository;

    public CarManufacturer findCarManufacturerById(Long id) {
        Optional<CarManufacturer> manufacturer = manufacturerRepository.findById(id);
        if (manufacturer.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This manufacturer does not exist");

        return manufacturerRepository.save(manufacturer.get());
    }

    public CarManufacturer findCarManufacturerByName(String name) {
        CarManufacturer manufacturer = manufacturerRepository
                .findCarManufacturerByNameIgnoreCase(name);
        if (manufacturer == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This manufacturer does not exist");

        return manufacturerRepository.save(manufacturer);
    }

    public CarManufacturer addManufacturer(CarManufacturer manufacturer) {
        if (manufacturerRepository.exists(Example.of(manufacturer, ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("name", ignoreCase()))))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This car manufacturer already exists");

        return manufacturerRepository.save(manufacturer);
    }

    public void deleteCarManufacturer(Long id) {
        manufacturerRepository.deleteById(id);
    }

}
