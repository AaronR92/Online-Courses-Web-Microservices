package com.aaronr92.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This email is already registered");
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
}
