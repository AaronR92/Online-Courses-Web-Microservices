package com.aaronr92.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public Customer registerCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This email is already registered");
        customer.setFirstName(customer.getFirstName().trim());
        customer.setSecondName(customer.getSecondName().trim());
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This customer does not exist");
        return customer.get();
    }

    public Customer findCustomerByEmail(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This customer does not exist");
        }
        return customer;
    }

    public List<Car> getCustomerCars(Long id) {
        Customer customer = findCustomerById(id);

        List<Car> cars = new ArrayList<>();

        for (long carId :
                customer.getCars()) {
            Car car = restTemplate.getForObject("http://localhost:8090/api/v1/cars/{carId}",
                    Car.class, carId);
            cars.add(car);
        }

        return cars;
    }

    public List<Long> updateCustomerCars(Long id, Long carId) {
        Customer customer = findCustomerById(id);

        if (customer.getCars().contains(carId)) {
            customer.removeCar(carId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "Car successfully deleted");
        }

        customer.addCar(carId);

        customerRepository.save(customer);

        return customer.getCars();
    }
}
