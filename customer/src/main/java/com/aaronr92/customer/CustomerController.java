package com.aaronr92.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        log.info("Customer registration {}", customer);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/v1/customers")
                .toUriString());
        return ResponseEntity.created(uri).body(customerService.registerCustomer(customer));
    }

    @GetMapping(path = "{customerId}")
    public ResponseEntity<Customer> findCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @GetMapping
    public ResponseEntity<Customer> findCustomer(@RequestParam String email) {
        return ResponseEntity.ok(customerService.findCustomerByEmail(email));
    }

    @GetMapping(path = "{id}/cars")
    public ResponseEntity<List<Car>> getCustomerCars(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerCars(id));
    }

    @PutMapping(path = "/{id}/cars")
    public ResponseEntity<List<Long>> updateCustomerCars(@PathVariable Long id,
                                                      @RequestParam("car_id") Long carId) {
        return ResponseEntity.ok(customerService.updateCustomerCars(id, carId));
    }
}
