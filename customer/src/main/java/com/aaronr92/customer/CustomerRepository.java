package com.aaronr92.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
