package com.aaronr92.cars.service;

import com.aaronr92.cars.CarDto;
import com.aaronr92.cars.entity.Car;
import com.aaronr92.cars.entity.CarManufacturer;
import com.aaronr92.cars.repository.CarManufacturerRepository;
import com.aaronr92.cars.repository.CarRepository;
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
public class CarService {

    private final CarRepository carRepository;
    private final CarManufacturerRepository manufacturerRepository;

    public Car addCar(CarDto carDto) {
        CarManufacturer manufacturer = manufacturerRepository.findCarManufacturerByNameIgnoreCase(
                carDto.getManufacturer());

        Car c = Car.fromCarDto(carDto);
        c.setManufacturer(manufacturer);

        if (carRepository.exists(Example.of(c, ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("name", ignoreCase())
                .withMatcher("power", ignoreCase())
                .withMatcher("description", ignoreCase())))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This car already exist");
        }

        manufacturer.addCar(c);
        manufacturerRepository.save(manufacturer);
        return carRepository.save(c);
    }

    public Car findCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);

        if (car.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Car does not exist");

        return car.get();
    }

    public Car findCarByName(String name) {
        Car car = carRepository.findCarByName(name);

        if (car == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Car does not exist");

        return car;
    }

    public void deleteCar(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Car does not exist");

        carRepository.deleteById(id);
    }
}
